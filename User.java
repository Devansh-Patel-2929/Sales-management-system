import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.*;


public class User {

    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public User() {
        frame = new JFrame("User Menu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel mainPanel = new JPanel();
        JButton menuButton = new JButton("Menu");
        JButton exitButton = new JButton("Exit"); // Add an "Exit" button

        mainPanel.add(menuButton);
        mainPanel.add(exitButton); // Add the "Exit" button
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        mainPanel.add(new JLabel("Inventory Management System"));;
        mainPanel.add(new JLabel("Made BY:-"));
        mainPanel.add(new JLabel("Devansh Patel - 228"));
        mainPanel.add(new JLabel("Ayush Singh - 231"));
        mainPanel.add(new JLabel("Shahank Prasad - 234"));
        Component[] labels = mainPanel.getComponents();
        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(new Font("Monospaced", Font.BOLD, 18));
        }

        menuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MyGUI menuClass = new MyGUI();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Close the program
            }
        });

        cardPanel.add(mainPanel, "main");

        // Create a menu panel (replace this with your menu content)
        JPanel menuPanel = new JPanel();
        // Add menu items and functionality here


        cardPanel.add(menuPanel, "menu");

        frame.add(cardPanel);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new User();
        });
    }
}
