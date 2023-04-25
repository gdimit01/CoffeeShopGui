import javax.swing.*;
import org.w3c.dom.events.MouseEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import java.awt.Dimension;

//TO DO: add menu Panel with vivid content

public class CoffeeShopGUI {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Coffee Shop");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new WelcomePanel(frame));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    static class WelcomePanel extends JPanel {
    private JButton startButton;
    private JButton exitButton;

    public WelcomePanel(JFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(Color.DARK_GRAY); // Set background color to dark gray
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Welcome to the Coffee Shop!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.BLUE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, gbc);

        // Add an image underneath the welcome message
        // Add an image underneath the welcome message
        // Add an image underneath the welcome message
        try {
            URL imageUrl = new URL("https://cdn.mage.space/enhanced/esrgan/d74889fd55c34a59b70e54309ec9d59d.png");
            Image image = ImageIO.read(imageUrl);
            ImageIcon originalIcon = new ImageIcon(image);
            Image scaledImage = originalIcon.getImage().getScaledInstance(400, 600, Image.SCALE_SMOOTH);
            ImageIcon coffeeShopImage = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(coffeeShopImage);
            gbc.gridy = 1;
            gbc.weighty = 0.6;
            add(imageLabel, gbc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        startButton = new JButton("Start Ordering");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);
        startButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        add(startButton, gbc);

        // Add the exit button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorderPainted(false);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(true);
        exitButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(exitButton, gbc);

        startButton.addMouseListener(new MouseAdapter() {
    @Override
public void mouseEntered(java.awt.event.MouseEvent evt) {
    startButton.setBackground(new Color(255, 215, 0));
    startButton.setForeground(Color.BLACK);
}


    @Override
public void mouseExited(java.awt.event.MouseEvent evt) {
    startButton.setBackground(Color.BLACK);
    startButton.setForeground(Color.WHITE);
}

});

exitButton.addMouseListener(new MouseAdapter() {
    @Override
public void mouseEntered(java.awt.event.MouseEvent evt) {
    exitButton.setBackground(new Color(255, 215, 0));
    exitButton.setForeground(Color.BLACK);
}


    @Override
public void mouseExited(java.awt.event.MouseEvent evt) {
    exitButton.setBackground(Color.BLACK);
    exitButton.setForeground(Color.WHITE);
}

});

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new CoffeeOrderPanel());
                frame.pack();
            }
        });
        // Add an ActionListener to the exit button to exit the program
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}



    static class CoffeeOrderPanel extends JPanel {
        private JComboBox<String> coffeeTypeComboBox;
        private JComboBox<String> sizeComboBox;
        private JButton addButton;
        private JButton placeOrderButton;
        private JTextArea orderSummaryTextArea;
        private double totalPrice = 0.0;

        public CoffeeOrderPanel() {
            setLayout(new BorderLayout());

        // Add these two lines to set the size of the panel
        Dimension panelSize = new Dimension(700, 600);
        setPreferredSize(panelSize);

            String[] coffeeTypes = {"Espresso", "Latte", "Cappuccino"};
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
}
