package parkhausprototype;

import java.math.BigDecimal;
import java.util.Optional;

public class Utils {
	public static Double round(Number src, int decimalPlaces) {

		return Optional.ofNullable(src)
			.map(Number::doubleValue)
			.map(BigDecimal::new)
			.map(dbl -> dbl.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP))
			.map(BigDecimal::doubleValue)
			.orElse(null);
	}
}
