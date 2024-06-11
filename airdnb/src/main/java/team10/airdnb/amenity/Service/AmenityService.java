package team10.airdnb.amenity.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team10.airdnb.accommodation.entity.Accommodation;
import team10.airdnb.accommodation.repository.AccommodationRepository;
import team10.airdnb.amenity.entity.Amenity;
import team10.airdnb.amenity.repository.AmenityRepository;

import java.util.List;

@Service
public class AmenityService {
    private final AmenityRepository amenityRepository;
    private final AccommodationRepository accommodationRepository;

    @Autowired
    public AmenityService(AmenityRepository amenityRepository, AccommodationRepository accommodationRepository) {
        this.amenityRepository = amenityRepository;
        this.accommodationRepository = accommodationRepository;
    }

    // 새로운 어메니티 생성
    public Amenity createAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }


}
