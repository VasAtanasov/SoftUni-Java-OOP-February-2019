public class Rectangle {
    private Point topLeft;
    private Point bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public boolean contains(Point point) {
        return (point.getX() >= topLeft.getX() && point.getX() <= bottomRight.getX()) &&
                (point.getY() >= topLeft.getY() && point.getY() <= bottomRight.getY());
    }
}