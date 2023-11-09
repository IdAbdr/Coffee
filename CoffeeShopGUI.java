import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CoffeeShopGUI extends JFrame {
    private CoffeeShopFacade coffeeShopFacade;
    private DefaultListModel<String> orderListModel;

    private JTextField customerNameField;
    private JComboBox<String> coffeeTypeComboBox;
    private JTextField milkTypeField;
    private JTextField sugarTypeField;
    private JCheckBox addSyrupCheckBox;
    private JComboBox<String> syrupTypeComboBox;
    private JCheckBox addCinnamonCheckBox;
    private JLabel totalPriceLabel;

    public CoffeeShopGUI() {
        coffeeShopFacade = new CoffeeShopFacade();
        orderListModel = new DefaultListModel<>();

        setTitle("Coffee Shop Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        customerNameField = new JTextField();
        coffeeTypeComboBox = new JComboBox<>(new String[]{"Espresso", "Cappuccino"});
        milkTypeField = new JTextField();
        sugarTypeField = new JTextField();
        addSyrupCheckBox = new JCheckBox("Add Syrup");
        syrupTypeComboBox = new JComboBox<>(new String[]{"Vanilla", "Caramel"});
        syrupTypeComboBox.setEnabled(false);
        addCinnamonCheckBox = new JCheckBox("Add Cinnamon");
        totalPriceLabel = new JLabel("Total Price: $0.0");

        JButton createOrderButton = new JButton("Create Order");

        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(customerNameField);
        inputPanel.add(new JLabel("Coffee Type:"));
        inputPanel.add(coffeeTypeComboBox);
        inputPanel.add(new JLabel("Milk Type:"));
        inputPanel.add(milkTypeField);
        inputPanel.add(new JLabel("Sugar Type:"));
        inputPanel.add(sugarTypeField);
        inputPanel.add(addSyrupCheckBox);
        inputPanel.add(syrupTypeComboBox);
        inputPanel.add(addCinnamonCheckBox);
        inputPanel.add(totalPriceLabel);

        add(inputPanel, BorderLayout.NORTH);
        add(createOrderButton, BorderLayout.CENTER);

        addSyrupCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean syrupCheckBoxSelected = addSyrupCheckBox.isSelected();
                syrupTypeComboBox.setEnabled(syrupCheckBoxSelected);
            }
        });


        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String customerName = customerNameField.getText();
                String coffeeType = (String) coffeeTypeComboBox.getSelectedItem();
                String milkType = milkTypeField.getText();
                String sugarType = sugarTypeField.getText();
                boolean addSyrup = addSyrupCheckBox.isSelected();
                String syrupType = (String) syrupTypeComboBox.getSelectedItem();
                boolean addCinnamon = addCinnamonCheckBox.isSelected();

                // Вызов фасада для создания заказа
                coffeeShopFacade.createOrderWithSyrup(customerName, coffeeType, milkType, sugarType, addSyrup, addCinnamon, syrupType);

                // Получить созданный заказ
                List<CoffeeOrder> orders = coffeeShopFacade.getOrders();
                CoffeeOrder order = orders.get(orders.size() - 1); // Получить последний заказ

                // Рассчитать общую стоимость заказа
                double totalPrice = order.getCoffee().getCost();
                totalPriceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));

                // Формирование текста заказа
                StringBuilder orderDetails = new StringBuilder();
                orderDetails.append("Order placed by ").append(customerName).append(" for ").append(coffeeType);

                if (addSyrup) {
                    orderDetails.append(" with Syrup (").append(syrupType).append(")");
                }

                if (addCinnamon) {
                    orderDetails.append(" with Cinnamon");
                }

                orderDetails.append(". Total Price: $").append(totalPrice);

                // Отображение сообщения о заказе
                JOptionPane.showMessageDialog(null, orderDetails.toString());

                // Обновление списка заказов и очистка полей ввода
                updateOrderList();
                clearInputFields();
            }
        });


        JList<String> orderList = new JList<>(orderListModel);
        JScrollPane orderScrollPane = new JScrollPane(orderList);

        add(orderScrollPane, BorderLayout.SOUTH);
    }

    private void updateOrderList() {
        List<CoffeeOrder> orders = coffeeShopFacade.getOrders();
        orderListModel.clear();
        for (CoffeeOrder order : orders) {
            orderListModel.addElement(order.getCoffeeType() + " - " + order.getState().getClass().getSimpleName());
        }
    }

    private void clearInputFields() {
        customerNameField.setText("");
        milkTypeField.setText("");
        sugarTypeField.setText("");
        addSyrupCheckBox.setSelected(false);
        syrupTypeComboBox.setEnabled(false);
        addCinnamonCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CoffeeShopGUI coffeeShopGUI = new CoffeeShopGUI();
                coffeeShopGUI.setVisible(true);
            }
        });
    }
}
