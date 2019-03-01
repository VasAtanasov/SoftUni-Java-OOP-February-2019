public enum SeasonType {
    Autumn(1), Spring(2), Winter(3), Summer(4);

    private int coefficient;

    SeasonType(int coefficient) {
        this.coefficient = coefficient;
    }

    public int getCoefficient() {
        return this.coefficient;
    }
}
