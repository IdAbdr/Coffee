import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

class CoffeeOrder extends Observable{
    private String coffeeType;
    private CoffeeDecorator coffee;
    private OrderState currentState;

    public CoffeeOrder(String coffeeType, CoffeeDecorator coffee){
        this.coffeeType=coffeeType;
        this.coffee=coffee;
        this.currentState=new WaitingState();
    }

    public void printOrder(){
        System.out.println("Coffee Type: "+coffeeType);
        System.out.println("Extras: "+coffee.getDescription());
        System.out.println("Cost: "+coffee.getCost());
        System.out.println("Order State: "+currentState.getClass().getSimpleName());
    }

    public void setState(OrderState state){
        currentState=state;
    }

    public void processOrder(){
        currentState.handle(this);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (currentState instanceof WaitingState){
                    transitionToReadyState();
                } else if (currentState instanceof ReadyState) {
                    transitionToDeliveredState();
                }
                setChanged();
                notifyObservers();
                timer.cancel();
            }
        }, currentState instanceof WaitingState ? 60000 : 120000);
    }

    public String getCoffeeType(){
        return coffeeType;
    }

    private void transitionToReadyState(){
        currentState = new ReadyState();
    }

    private void transitionToDeliveredState(){
        currentState = new DeliveredState();
    }

    public OrderState getState() {
        return currentState;
    }

    public CoffeeDecorator getCoffee() {
        return coffee;
    }

    public void setCoffee(CoffeeDecorator coffee) {
        this.coffee = coffee;
    }
}