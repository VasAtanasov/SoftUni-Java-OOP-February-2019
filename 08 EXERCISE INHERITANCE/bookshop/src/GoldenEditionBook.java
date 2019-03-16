public class GoldenEditionBook extends Book {


    public GoldenEditionBook(String author, String title, double price) {
        super(author, title, price);
    }

    @Override
    protected void setPrice(double price) {
        super.setPrice(price * 1.3);
    }

    @Override
    public double getPrice() {
        return super.getPrice();
    }
}
