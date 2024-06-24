package team10.airdnb.accommodation.util;

import java.math.BigDecimal;

public class BigDecimalUtils {

    public static boolean isGreaterThan(BigDecimal value, BigDecimal compareTo) {
        return value != null && value.compareTo(compareTo) > 0;
    }

}
