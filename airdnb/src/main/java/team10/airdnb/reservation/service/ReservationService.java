package team10.airdnb.reservation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import team10.airdnb.accommodation.entity.Accommodation;
import team10.airdnb.accommodation.exception.AccommodationIdNotFoundException;
import team10.airdnb.accommodation.repository.AccommodationRepository;
import team10.airdnb.member.entity.Member;
import team10.airdnb.member.exception.MemberIdNotFoundException;
import team10.airdnb.member.repository.MemberRepository;
import team10.airdnb.reservation.controller.request.ReservationCreateRequest;
import team10.airdnb.reservation.entity.Reservation;
import team10.airdnb.reservation.repository.ReservationRepository;

@Slf4j
@RequiredArgsConstructor
@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final AccommodationRepository accommodationRepository;

    public Reservation createReservation(ReservationCreateRequest request) {
        Member member = getMemberById(request.memberId());

        Accommodation accommodation = getAccommodationById(request.accommodationId());

        Reservation reservation = request.toEntity(member, accommodation);

        return reservationRepository.save(reservation);
    }

    private Member getMemberById(String memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(MemberIdNotFoundException::new);
    }

    private Accommodation getAccommodationById(long accommodationId) {
        return accommodationRepository.findById(accommodationId)
                .orElseThrow(AccommodationIdNotFoundException::new);
    }
}
