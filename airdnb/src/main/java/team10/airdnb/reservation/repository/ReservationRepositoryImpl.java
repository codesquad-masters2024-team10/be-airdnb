package team10.airdnb.reservation.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import team10.airdnb.reservation.dto.ReservationAccommodationDto;

import java.time.LocalDate;
import java.util.List;

import static team10.airdnb.accommodation.entity.QAccommodation.*;
import static team10.airdnb.reservation.entity.QReservation.*;

@Repository
@RequiredArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public boolean isDateRangeAvailable(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {

        return queryFactory.selectFrom(reservation)
                .where(
                        reservation.accommodation.id.eq(accommodationId)
                                .and(
                                        reservation.checkInDate.loe(checkOutDate)
                                                .and(reservation.checkOutDate.goe(checkInDate))
                                )
                                .and(
                                        reservation.checkInDate.ne(checkOutDate)
                                )
                                .and(
                                        reservation.checkOutDate.ne(checkInDate)
                                )
                )
                .fetch().isEmpty();

    }

    @Override
    public List<ReservationAccommodationDto> findReservationAccommodationDTOsByMemberId(String memberId) {

        return queryFactory
                .select(Projections.constructor(
                        ReservationAccommodationDto.class,
                        reservation.id.as("reservationId"),
                        reservation.isConfirmed,
                        reservation.deleted,
                        reservation.checkInDate,
                        reservation.checkOutDate,
                        reservation.capacity,
                        reservation.totalPrice,
                        accommodation.name.as("accommodationName"),
                        accommodation.accommodationDescription.as("accommodationDescription"),
                        accommodation.accommodationImages.as("accommodationImages"),
                        accommodation.address.city.as("city"),
                        accommodation.address.district.as("district"),
                        accommodation.address.neighborhood.as("neighborhood"),
                        accommodation.address.streetName.as("streetName"),
                        accommodation.address.detailedAddress.as("detailedAddress"),
                        accommodation.memberId
                ))
                .from(reservation)
                .join(reservation.accommodation, accommodation)
                .where(reservation.member.id.eq(memberId))
                .fetch();
    }
}


