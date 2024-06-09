package team10.airdnb.accommodation_type.exception;

import team10.airdnb.error.ErrorCode;
import team10.airdnb.exception.BusinessException;

public class AccommodationTypeNameDuplicateException extends BusinessException {
    public AccommodationTypeNameDuplicateException(ErrorCode errorCode) {
        super(errorCode);
    }
}