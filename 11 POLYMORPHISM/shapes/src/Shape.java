public abstract class Shape {
    private double perimeter;
    private double area;

    public void setPerimeter(double perimeter) {
        this.perimeter = perimeter;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getPerimeter() {
        return this.perimeter;
    }

    public double getArea() {
        return this.area;
    }

    abstract void calculatePerimeter();
    abstract void calculateArea();
}
