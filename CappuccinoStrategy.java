public class CappuccinoStrategy implements CoffeeStrategy{
    @Override
    public void prepare() {
        System.out.println("Preparing a Cappuccino");
    }
}
