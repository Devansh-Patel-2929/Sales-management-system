import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class DatabaseDisplayApp {
    private Connection connection;
    private String dbUrl = "jdbc:mysql://localhost:3306/productdatabase";
    private String dbUsername = "root";
    private String dbPassword = "XXXXXXX";

    private JFrame frame;
    private JTable table;
    private JScrollPane scrollPane;
    private JButton refreshButton;

    public DatabaseDisplayApp() {
        try {
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("DB not connected ****************");
            // Handle the database connection error
        }

        frame = new JFrame("Database Records Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a table model and fetch data from the database
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Product ID", "Product Name", "Price", "Quantity", "Total", "Category"});

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= 6; i++) {
                    row.add(resultSet.getObject(i));
                }
                tableModel.addRow(row);
            }
            

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception
        }

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTableData();
            }
        });

        // Create the table and add it to a scroll pane
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(refreshButton);

        // Add the scroll pane to the frame
        frame.add(scrollPane);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private void refreshTableData() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0); // Clear the existing table data

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Products");

            while (resultSet.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= 6; i++) {
                    row.add(resultSet.getObject(i));
                }
                tableModel.addRow(row);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the SQL exception
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DatabaseDisplayApp();
        });
    }
}
