public class ReadyState implements OrderState{
    @Override
    public void handle(CoffeeOrder order) {
        System.out.println("Order is ready for pickup.");
    }
}
