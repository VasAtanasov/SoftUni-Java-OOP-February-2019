abstract class Food {
    private int points;

    public Food(int points) {
        this.setPoints(points);
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }

    @Override
    public String toString() {
        return String.format("%s",this.getClass().getSimpleName());
    }
}
