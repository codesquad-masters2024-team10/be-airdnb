package team10.airdnb.accommodation_type.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team10.airdnb.accommodation_type.entity.AccommodationType;

import java.util.Optional;

public interface AccommodationTypeRepository extends JpaRepository<AccommodationType, Long> {
    Optional<AccommodationType> findByName(String name);
}
