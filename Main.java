import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CoffeeShopFacade coffeeShopFacade = new CoffeeShopFacade();

        while (true) {
            System.out.println("CoffeeShop Console App");
            System.out.println("1. Create Order");
            System.out.println("2. View Orders");
            System.out.println("3. Check Order Status");
            System.out.println("4. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter your name: ");
                    String customerName = scanner.nextLine();

                    System.out.print("Enter coffee type (Espresso or Cappuccino): ");
                    String coffeeType = scanner.nextLine();

                    System.out.print("Enter milk type (Almond, Regular, Coconut): ");
                    String milkType = scanner.nextLine();

                    System.out.print("Enter sugar type (White, Brown, Splenda): ");
                    String sugarType = scanner.nextLine();

                    System.out.print("Add syrup? (yes or no): ");
                    String addSyrupInput = scanner.nextLine();
                    boolean addSyrup = addSyrupInput.equalsIgnoreCase("yes");
                    String syrupType = null;

                    if (addSyrup) {
                        System.out.print("Enter syrup type (Vanilla, Caramel): ");
                        syrupType = scanner.nextLine();
                    }

                    System.out.print("Add cinnamon? (yes or no): ");
                    String addCinnamonInput = scanner.nextLine();
                    boolean addCinnamon = addCinnamonInput.equalsIgnoreCase("yes");

                    coffeeShopFacade.createOrderWithSyrup(customerName, coffeeType, milkType, sugarType, addSyrup, addCinnamon, syrupType);

                    System.out.println("Order created");
                    break;

                case 2:
                    System.out.println("Orders:");
                    List<CoffeeOrder> ordersList = coffeeShopFacade.getOrders();
                    for (int i = 0; i < ordersList.size(); i++) {
                        System.out.println("Order" + (i + 1) + ":");
                        ordersList.get(i).printOrder();
                        System.out.println("------------");
                    }
                    break;

                case 3:
                    System.out.println("Enter the order number to check its status: ");
                    int orderNumber = scanner.nextInt();
                    scanner.nextLine();
                    List<CoffeeOrder> orders = coffeeShopFacade.getOrders();

                    if (orderNumber >= 1 && orderNumber <= orders.size()) {
                        CoffeeOrder order = orders.get(orderNumber - 1);
                        order.processOrder();
                    } else {
                        System.out.println("Invalid order number");
                    }
                    break;

                case 4:
                    System.out.println("Exiting CoffeeShopApp. Have a nice day!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
