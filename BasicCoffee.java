public class BasicCoffee implements CoffeeDecorator{
    @Override
    public String getDescription() {
        return "Basic Coffee";
    }

    @Override
    public double getCost() {
        return 2.0;
    }
}
