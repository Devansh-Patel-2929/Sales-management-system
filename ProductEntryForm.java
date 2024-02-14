import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductEntryForm {
    private JTextField productNameField;
    private JTextField priceField;
    private JTextField quantityField;
    private JTextField totalField; // Field name corrected
    private JTextField categoryField;

    public ProductEntryForm() {
        JFrame frame = new JFrame("Product Entry Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        JLabel productNameLabel = new JLabel("Product Name:");
        productNameField = new JTextField(20);
        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(10);
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityField = new JTextField(10);
        JLabel totalLabel = new JLabel("Total:"); // Field name corrected
        totalField = new JTextField(10); // Field name corrected
        JLabel categoryLabel = new JLabel("Category:");
        categoryField = new JTextField(20);

        JButton addButton = new JButton("Add to Database");

        panel.add(productNameLabel);
        panel.add(productNameField);
        panel.add(priceLabel);
        panel.add(priceField);
        panel.add(quantityLabel);
        panel.add(quantityField);
        panel.add(totalLabel);
        panel.add(totalField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(addButton);

        frame.add(panel);
        frame.setVisible(true);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProductToDatabase();
            }
        });
    }

    private void addProductToDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProductDatabase", "root", "krishna10#")) {
            String sql = "INSERT INTO Products (ProductName, Price, Quantity, Total, Category) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, productNameField.getText());
            preparedStatement.setBigDecimal(2, new BigDecimal(priceField.getText()));
            preparedStatement.setInt(3, Integer.parseInt(quantityField.getText()));
            preparedStatement.setBigDecimal(4, new BigDecimal(totalField.getText()));
            preparedStatement.setString(5, categoryField.getText());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected == 1) {
                JOptionPane.showMessageDialog(null, "Product added to the database.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to add product to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to add the product to the database.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductEntryForm());
    }
}
