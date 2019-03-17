public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
        this.calculateArea();
        this.calculatePerimeter();
    }

    public double getWidth() {
        return this.width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return this.height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    void calculatePerimeter() {
        super.setPerimeter(2 * this.getHeight() + 2 * this.getWidth());
    }

    @Override
    void calculateArea() {
        super.setArea(this.getWidth() * this.getHeight());
    }
}
