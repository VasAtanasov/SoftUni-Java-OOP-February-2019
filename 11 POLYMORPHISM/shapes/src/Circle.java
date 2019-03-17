public class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
        this.calculateArea();
        this.calculatePerimeter();
    }

    public final double getRadius() {
        return this.radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    void calculatePerimeter() {
        super.setPerimeter(2 * Math.PI * this.getRadius());
    }

    @Override
    void calculateArea() {
        super.setArea(2 * Math.PI * Math.PI);
    }
}
