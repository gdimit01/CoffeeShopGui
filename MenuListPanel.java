import javax.swing.*;
import java.awt.*;

public class MenuListPanel extends JPanel {
    public MenuListPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        String[][] coffeeData = {
            {"Espresso", "£2.0"},
            {"Latte", "£3.5"},
            {"Cappuccino", "£3.0"}
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
    }
}
