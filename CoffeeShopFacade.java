import java.util.List;

public class CoffeeShopFacade {
    private CoffeeShop coffeeShop;

    public CoffeeShopFacade() {
        this.coffeeShop = CoffeeShop.getInstance();
    }

    public void createOrderWithSyrup(String customerName, String coffeeType, String milkType, String sugarType, boolean addSyrup, boolean addCinnamon, String syrupType) {
        CustomerOrderObserver customerObserver = new CustomerOrderObserver(customerName);

        CoffeeDecorator coffee = new BasicCoffee();
        coffee = new ExtraDecorator(coffee, "Milk (" + milkType + ")", 0.5);
        coffee = new ExtraDecorator(coffee, "Sugar (" + sugarType + ")", 0.1);

        if (addSyrup) {
            coffee = new ExtraDecorator(coffee, "Syrup (" + syrupType + ")", 0.3);
        }

        if (addCinnamon) {
            coffee = new ExtraDecorator(coffee, "Cinnamon", 0.2);
        }

        CoffeeOrder order = new CoffeeOrder(coffeeType, coffee);
        coffeeShop.addOrder(order);

        order.addObserver(customerObserver);
    }

    public List<CoffeeOrder> getOrders() {
        return coffeeShop.getOrders();
    }
}
