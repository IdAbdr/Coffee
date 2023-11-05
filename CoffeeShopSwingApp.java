import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeShopSwingApp {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Coffee Shop");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel nameLabel = new JLabel("Your Name:");
        JTextField nameTextField = new JTextField(20);

        JLabel coffeeLabel = new JLabel("Coffee Type:");
        String[] coffeeTypes = {"Espresso", "Cappuccino"};
        JComboBox<String> coffeeComboBox = new JComboBox<>(coffeeTypes);

        JButton orderButton = new JButton("Place Order");

        panel.add(nameLabel);
        panel.add(nameTextField);
        panel.add(coffeeLabel);
        panel.add(coffeeComboBox);
        panel.add(orderButton);

        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameTextField.getText();
                String coffeeType = (String) coffeeComboBox.getSelectedItem();
                JOptionPane.showMessageDialog(frame, "Order placed by " + name + " for " + coffeeType);
            }
        });

        frame.setVisible(true);
    }
}
