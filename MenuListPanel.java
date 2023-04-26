import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuListPanel extends JPanel {
    public MenuListPanel(JFrame frame) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[][] coffeeData = {
                {"Espresso", "£2.0"},
                {"Latte", "£3.5"},
                {"Cappuccino", "£3.0"},
                {"Americano", "£1.0"},
                {"Tea", "£1.5"},
                {"Hot Chocolate", "£1.75"}
        };

        String[] columnNames = {"Coffee Type", "Price"};

        JTable menuTable = new JTable(coffeeData, columnNames);
        menuTable.setPreferredScrollableViewportSize(new Dimension(500, 100));
        menuTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(menuTable);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(scrollPane, gbc);

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new WelcomePanel(frame));
                frame.pack();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(backButton, gbc);
    }
}
