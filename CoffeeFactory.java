public class CoffeeFactory {
    public static CoffeeDecorator createCoffee(String coffeeType){
        if ("Espresso".equalsIgnoreCase(coffeeType)){
            return new BasicCoffee();
        } else if ("Cappuccino".equalsIgnoreCase(coffeeType)) {
            return new ExtraDecorator(new BasicCoffee(), "Foam", 0.3);
        }else {
            throw new IllegalArgumentException("Unknown coffee type");
        }
    }
}
