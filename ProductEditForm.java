import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductEditForm {
    private JTextField productIDField;
    private JTextField updatedValueField;
    private JComboBox<String> fieldToUpdateComboBox;
    private JButton updateButton;

    public ProductEditForm() {
        JFrame frame = new JFrame("Product Edit Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel productIDLabel = new JLabel("Product ID:");
        productIDField = new JTextField(10);
        JLabel fieldToUpdateLabel = new JLabel("Field to Update:");
        String[] fieldOptions = {"ProductName", "Price", "Quantity", "Category"};
        fieldToUpdateComboBox = new JComboBox<>(fieldOptions);
        JLabel updatedValueLabel = new JLabel("Updated Value:");
        updatedValueField = new JTextField(20);
        updateButton = new JButton("Update Database");

        panel.add(productIDLabel);
        panel.add(productIDField);
        panel.add(fieldToUpdateLabel);
        panel.add(fieldToUpdateComboBox);
        panel.add(updatedValueLabel);
        panel.add(updatedValueField);
        panel.add(updateButton);

        frame.add(panel);
        frame.setVisible(true);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateProductInDatabase();
            }
        });
    }

    private void updateProductInDatabase() {

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ProductDatabase", "root", "krishna10#")) {
            String fieldToUpdate = fieldToUpdateComboBox.getSelectedItem().toString();
            String sql = "UPDATE Products SET " + fieldToUpdate + " = ? WHERE ProductID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, updatedValueField.getText());
            preparedStatement.setInt(2, Integer.parseInt(productIDField.getText()));

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(null, "Product updated in the database.");
            } else {
                JOptionPane.showMessageDialog(null, "Product with ID " + productIDField.getText() + " not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: Unable to update product in the database.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductEditForm());
    }
}
