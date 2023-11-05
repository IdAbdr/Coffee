public class WaitingState implements OrderState{
    @Override
    public void handle(CoffeeOrder order) {
        System.out.println("Order is in waiting state.");
    }
}
