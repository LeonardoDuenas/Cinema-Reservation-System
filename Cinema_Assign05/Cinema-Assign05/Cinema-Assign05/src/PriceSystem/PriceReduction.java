package PriceSystem;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
public class PriceReduction {
    private static final Map<PriceReductionCode, Double> priceReductions = new HashMap<>();

    //price reductions for each code
    static {
        priceReductions.put(PriceReductionCode.STUDENT_DISCOUNT, 0.2); // 20% off for students
        priceReductions.put(PriceReductionCode.FIRST_NATION_DISCOUNT, 0.15); // 15% off for senior citizens
        priceReductions.put(PriceReductionCode.EARLY_CUSTOMER_DISCOUNT, 0.1); // 10% off for early birds
    }

    //discounted price
    public static boolean isValidDiscountCode(PriceReductionCode discountCode) {
        // Check if the discount code is one of the predefined codes
        return priceReductions.containsKey(discountCode);
    }
    public static double calculateDiscountedPrice(double originalPrice, PriceReductionCode code) {
        double reduction = priceReductions.getOrDefault(code, 0.0); // Get reduction percentage
        return originalPrice * (1 - reduction); // Calculate discounted price
    }
    public static double getDiscountPercentage(PriceReductionCode discountCode) {
        double reduction = priceReductions.getOrDefault(discountCode, 0.0);
        return reduction * 100;
    }
}
