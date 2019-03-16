public class Kitten extends Cat {
    public Kitten(String name, int age) {
        this(name, age, "Female");
    }

    public Kitten(String name, int age, String gender) {
        super(name, age, gender);
    }

    @Override
    public String produceSound() {
        return "Miau";
    }
}
