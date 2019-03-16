public class Seat implements Car, Sellable {
    private String model;
    private String color;
    private Integer horsePower;
    private String countryProduced;
    private Double price;

    public Seat(String model, String color, Integer horsePower, String countryProduced) {
        this(model, color, horsePower, countryProduced, 0D);
    }

    public Seat(String model, String color, Integer horsePower, String countryProduced, Double price) {
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.countryProduced = countryProduced;
        this.price = price;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.horsePower;
    }

    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return String.format("This is Leon produced in %s and have %d tires", this.countryProduced, this.TIRES);
    }
}
