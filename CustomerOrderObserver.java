import java.util.Observable;
import java.util.Observer;

public class CustomerOrderObserver implements OrderObserver, Observer {
    private String name;
    public CustomerOrderObserver(String name){
        this.name=name;
    }

    @Override
    public void update(CoffeeOrder order) {
        System.out.println(name+": Order for"+order.getCoffeeType()+"is ready.");
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}