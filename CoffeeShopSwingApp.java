import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CoffeeShopSwingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coffee Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel nameLabel = new JLabel("Your Name:");
        JTextField nameTextField = new JTextField(20);

        JLabel coffeeLabel = new JLabel("Coffee Type:");
        String[] coffeeTypes = {"Espresso", "Cappuccino"};
        JComboBox<String> coffeeComboBox = new JComboBox<>(coffeeTypes);

        JCheckBox milkCheckBox = new JCheckBox("Milk");
        JCheckBox sugarCheckBox = new JCheckBox("Sugar");
        JCheckBox syrupCheckBox = new JCheckBox("Syrup");

        JLabel syrupLabel = new JLabel("Syrup Type:");
        String[] syrupTypes = {"Vanilla", "Caramel"};
        JComboBox<String> syrupComboBox = new JComboBox<>(syrupTypes);
        syrupComboBox.setEnabled(false);

        JCheckBox cinnamonCheckBox = new JCheckBox("Cinnamon");

        JLabel priceLabel = new JLabel("Total Price: $0.0");

        JButton orderButton = new JButton("Place Order");

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(coffeeLabel);
        panel.add(coffeeComboBox);
        panel.add(milkCheckBox);
        panel.add(sugarCheckBox);
        panel.add(syrupCheckBox);
        panel.add(syrupLabel);
        panel.add(syrupComboBox);
        panel.add(cinnamonCheckBox);
        panel.add(priceLabel);
        panel.add(orderButton);

        syrupCheckBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                syrupComboBox.setEnabled(syrupCheckBox.isSelected());
            }
        });

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String coffeeType = (String) coffeeComboBox.getSelectedItem();
                List<String> extras = new ArrayList<>();
                double totalPrice = 0.0;

                if (milkCheckBox.isSelected()) {
                    extras.add("Milk");
                    totalPrice += 0.5;
                }
                if (sugarCheckBox.isSelected()) {
                    extras.add("Sugar");
                    totalPrice += 0.1;
                }
                if (syrupCheckBox.isSelected()) {
                    String selectedSyrup = (String) syrupComboBox.getSelectedItem();
                    extras.add("Syrup (" + selectedSyrup + ")");
                    totalPrice += 0.3;
                }
                if (cinnamonCheckBox.isSelected()) {
                    extras.add("Cinnamon");
                    totalPrice += 0.2;
                }

                totalPrice += (coffeeType.equals("Espresso")) ? 2.0 : 2.3;  // Set coffee base price

                StringBuilder orderDetails = new StringBuilder();
                orderDetails.append("Order placed by ").append(name).append(" for ").append(coffeeType);

                if (!extras.isEmpty()) {
                    orderDetails.append(" with ");
                    for (int i = 0; i < extras.size(); i++) {
                        orderDetails.append(extras.get(i));
                        if (i < extras.size() - 1) {
                            orderDetails.append(", ");
                        }
                    }
                }

                orderDetails.append(". Total Price: $").append(totalPrice);

                priceLabel.setText("Total Price: $" + String.format("%.2f", totalPrice));

                JOptionPane.showMessageDialog(frame, orderDetails.toString());
            }
        });

        frame.setVisible(true);
    }
}
