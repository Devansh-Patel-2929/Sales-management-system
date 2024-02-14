import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductRemovalForm {
    private JTextField productIDField;
    private JButton removeButton;

    public ProductRemovalForm() {
        JFrame frame = new JFrame("Product Removal Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 150);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel productIDLabel = new JLabel("Product ID:");
        productIDField = new JTextField(10);
        removeButton = new JButton("Remove from Database");

        panel.add(productIDLabel);
        panel.add(productIDField);
        panel.add(removeButton);

        frame.add(panel);
        frame.setVisible(true);

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeProductFromDatabase();
            }
        });
    }

    private void removeProductFromDatabase() {


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProductDatabase", "root", "krishna10#")) {
            String sql = "DELETE FROM Products WHERE ProductID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int productID = Integer.parseInt(productIDField.getText());
            preparedStatement.setInt(1, productID);

            int rowsDeleted = preparedStatement.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Product removed from the database.");
            } else {
                JOptionPane.showMessageDialog(null, "Product with ID " + productID + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to remove product from the database.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductRemovalForm());
    }
}
