public class EspressoStrategy implements CoffeeStrategy{
    @Override
    public void prepare() {
        System.out.println("Preparing Espresso");
    }
}
