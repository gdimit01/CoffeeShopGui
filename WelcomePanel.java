import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class WelcomePanel extends JPanel {
    private JButton startButton;
    private JButton menuButton;
    private JButton exitButton;

    public WelcomePanel(JFrame frame) {
        setLayout(new GridBagLayout());
        setBackground(Color.BLACK); // Set background color to Black
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel welcomeLabel = new JLabel("Welcome to the Coffee Shop!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 36));
        welcomeLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, gbc);

        // Add an image underneath the welcome message
        try {
            URL imageUrl = new URL("https://cdn.mage.space/enhanced/esrgan/ace8972dcbc1444aa4502fb3d23f8463.png");
            Image image = ImageIO.read(imageUrl);
            ImageIcon originalIcon = new ImageIcon(image);
            Image scaledImage = originalIcon.getImage().getScaledInstance(350, 600, Image.SCALE_SMOOTH);
            ImageIcon coffeeShopImage = new ImageIcon(scaledImage);
            JLabel imageLabel = new JLabel(coffeeShopImage);
            gbc.gridy = 1;
            gbc.weighty = 0.6;
            add(imageLabel, gbc);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add the start button
        startButton = new JButton("Start Ordering");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setBackground(Color.BLACK);
        startButton.setForeground(Color.WHITE);
        startButton.setBorderPainted(true);
        startButton.setFocusPainted(false);
        startButton.setOpaque(true);
        startButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 2;
        gbc.weighty = 0.1;
        add(startButton, gbc);

         // Add the menu button
        menuButton = new JButton("Menu List");
        menuButton.setFont(new Font("Arial", Font.PLAIN, 20));
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
        menuButton.setBorderPainted(true);
        menuButton.setFocusPainted(false);
        menuButton.setOpaque(true);
        menuButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 3;
        gbc.weighty = 0.1;
        add(menuButton, gbc);

        // Add the exit button
        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("Arial", Font.PLAIN, 20));
        exitButton.setBackground(Color.BLACK);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorderPainted(true);
        exitButton.setFocusPainted(false);
        exitButton.setOpaque(true);
        exitButton.setPreferredSize(new Dimension(200, 40));
        gbc.gridy = 4;
        gbc.weighty = 0.1;
        add(exitButton, gbc);

        
    //highlight start button
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

//highlight over menulist
menuButton.addMouseListener(new MouseAdapter() {
    @Override
public void mouseEntered(java.awt.event.MouseEvent evt) {
    menuButton.setBackground(new Color(255, 215, 0));
    menuButton.setForeground(Color.BLACK);
}


    @Override
public void mouseExited(java.awt.event.MouseEvent evt) {
    menuButton.setBackground(Color.BLACK);
    menuButton.setForeground(Color.WHITE);
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
        // Add an ActionListener to the start button to start the program
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new CoffeeOrderPanel(frame));
                frame.pack();
            }
        });

       // Add an ActionListener to the menu button to show the menu list
menuButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        frame.setContentPane(new MenuListPanel(frame));
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