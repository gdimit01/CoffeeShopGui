import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoffeeOrderPanel extends JPanel {
    private JComboBox<String> coffeeTypeComboBox;
    private JComboBox<String> sizeComboBox;
    private JButton addButton;
    private JButton placeOrderButton;
    private JTextArea orderSummaryTextArea;
    private double totalPrice = 0.0;

    public CoffeeOrderPanel(JFrame frame) {
        setLayout(new BorderLayout());

        // Add these two lines to set the size of the panel
        Dimension panelSize = new Dimension(700, 600);
        setPreferredSize(panelSize);

        String[] coffeeTypes = {"Espresso", "Latte", "Cappuccino", "Americano", "Tea", "Hot Chocolate"};
        String[] sizes = {"Small", "Medium", "Large"};

        coffeeTypeComboBox = new JComboBox<>(coffeeTypes);
        sizeComboBox = new JComboBox<>(sizes);

        addButton = new JButton("Add to Order");
        placeOrderButton = new JButton("Place Order");
        orderSummaryTextArea = new JTextArea(10, 30);
        orderSummaryTextArea.setEditable(false);

        JPanel controlPanel = new JPanel();
        controlPanel.add(new JLabel("Coffee Type: "));
        controlPanel.add(coffeeTypeComboBox);
        controlPanel.add(new JLabel("Size: "));
        controlPanel.add(sizeComboBox);
        controlPanel.add(addButton);
        controlPanel.add(placeOrderButton);

        JScrollPane scrollPane = new JScrollPane(orderSummaryTextArea);

        add(controlPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String coffeeType = (String) coffeeTypeComboBox.getSelectedItem();
                String size = (String) sizeComboBox.getSelectedItem();
                double price = calculatePrice(coffeeType, size);

                orderSummaryTextArea.append(coffeeType + " - " + size + " - £" + price + "\n");
                totalPrice += price;
            }
        });

        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderSummaryTextArea.append("\nTotal: £" + totalPrice + "\n");
                JOptionPane.showMessageDialog(null, "Order placed! Total: £" + totalPrice);
                orderSummaryTextArea.setText("");
                totalPrice = 0.0;
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new WelcomePanel(frame));
                frame.pack();
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(backButton, BorderLayout.SOUTH);
    }
    

    private double calculatePrice(String coffeeType, String size) {
        double basePrice;

        switch (coffeeType) {
            case "Espresso":
                basePrice = 2.0;
                break;
            case "Latte":
                basePrice = 3.5;
                break;
            case "Cappuccino":
                basePrice = 3.0;
                break;
            default:
                basePrice = 0.0;
            case "Americano":
                basePrice = 1.0;
                break;
            case "Tea":
                basePrice = 1.5;
                break;
            case "Hot Chocolate":
                basePrice = 1.75;
                break;
        }

        double sizeMultiplier;

        switch (size) {
            case "Small":
                sizeMultiplier = 1.0;
                break;
            case "Medium":
                sizeMultiplier = 1.25;
                break;
            case "Large":
                sizeMultiplier = 1.5;
                break;
            default:
                sizeMultiplier = 1.0;
        }

        return basePrice * sizeMultiplier;
    }
    
}

