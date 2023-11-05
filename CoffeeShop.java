import java.util.ArrayList;
import java.util.List;

public class CoffeeShop {
    private static CoffeeShop instance;
    private List<CoffeeOrder> orders = new ArrayList<>();

    private CoffeeShop(){

    }

    public static CoffeeShop getInstance(){
        if (instance==null){
            instance= new CoffeeShop();
        }
        return instance;
    }

    public void addOrder(CoffeeOrder order){
        orders.add(order);
    }

    public List<CoffeeOrder> getOrders(){
        return orders;
    }
}
