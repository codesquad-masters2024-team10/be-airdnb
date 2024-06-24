package team10.airdnb.accommodation.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import team10.airdnb.accommodation.controller.request.SearchAccommodationRequest;
import team10.airdnb.accommodation.entity.Accommodation;
import team10.airdnb.accommodation.entity.embedded.QAccommodationFee;
import team10.airdnb.utils.BigDecimalUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static team10.airdnb.accommodation.entity.QAccommodation.*;
import static team10.airdnb.reservation.entity.QReservation.*;

@Repository
@RequiredArgsConstructor
public class AccommodationRepositoryImpl implements AccommodationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Accommodation> findAvailableAccommodations(SearchAccommodationRequest request, Pageable pageable) {
        QAccommodationFee accommodationFee = accommodation.accommodationFee;

        // 동적으로 where절 생성
        BooleanBuilder builder = new BooleanBuilder();

        Long capacity = request.capacity();
        BigDecimal minDayRate = request.minDayRate();
        BigDecimal maxDayRate = request.maxDayRate();
        LocalDate checkInDate = request.checkInDate();
        LocalDate checkOutDate = request.checkOutDate();

        if (capacity != null && capacity > 0) {
            builder.and(accommodation.maxCapacity.goe(capacity));
        }
        if (BigDecimalUtils.isGreaterThan(minDayRate, BigDecimal.ZERO)) {
            builder.and(accommodationFee.dayRate.goe(minDayRate));
        }
        if (BigDecimalUtils.isGreaterThan(maxDayRate, BigDecimal.ZERO)) {
            builder.and(accommodationFee.dayRate.loe(maxDayRate));
        }

        if (checkInDate != null && checkOutDate != null) {
            builder.and(
                    reservation.checkInDate.notBetween(checkInDate, checkOutDate)
                            .and(reservation.checkOutDate.notBetween(checkInDate, checkOutDate))
                            .or(reservation.checkInDate.goe(checkOutDate))
                            .or(reservation.checkOutDate.loe(checkInDate))
                            .or(reservation.accommodation.id.isNull())  // Reservation table에 없는 경우도 예약이 가능하므로 추가
            );
        }

        JPAQuery<Accommodation> query = queryFactory.selectFrom(accommodation)
                .leftJoin(reservation).on(reservation.accommodation.eq(accommodation))
                .where(builder);

        long total = query.fetch().size(); // 전체 개수 조회

        List<Accommodation> accommodations = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(accommodations, pageable, total);
    }
}
