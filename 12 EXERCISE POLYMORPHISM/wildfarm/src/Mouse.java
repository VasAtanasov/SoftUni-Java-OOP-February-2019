public class Mouse extends Mammal {
    public Mouse(String animalName, String animalType, double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    public void eat(Food food) {
        if (! "Vegetable".equals(food.getClass().getSimpleName())) {
            throw new IllegalArgumentException("Mice are not eating that type of food!");
        }
        this.setFoodEaten(this.getFoodEaten() + food.getQuantity());
    }

    @Override
    public String makeSound() {
        return "SQUEEEAAAK!";
    }
}
