public class DeliveredState implements OrderState{
    @Override
    public void handle(CoffeeOrder order) {
        System.out.println("Order has been delivered");
    }
}
