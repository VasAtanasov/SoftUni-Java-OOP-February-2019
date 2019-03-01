public final class PriceCalculator {
    public static double calculatePrice(double pricePerDay, int days, DiscountType discountType, SeasonType season) {
        return days * pricePerDay * season.getCoefficient() * (1 - (discountType.getDiscount() / 100.0));
    }
}
