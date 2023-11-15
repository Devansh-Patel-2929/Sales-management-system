import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MyGUI implements ActionListener {
    private JFrame frame;
    private JButton updateButtoI;
    private JButton addButtoI;
    private JButton removeButtoI;
    private JButton showDataButtoI;
    private JButton backButtoI;

    public MyGUI() {
        frame = new JFrame("My GUI");
        frame.setLayout(new FlowLayout());

        updateButtoI = new JButton("Update Item");
        addButtoI = new JButton("Add Item");
        removeButtoI = new JButton("Remove Item");
        showDataButtoI = new JButton("Show Data");
        backButtoI = new JButton("Back");

        updateButtoI.addActionListener(this);
        addButtoI.addActionListener(this);
        removeButtoI.addActionListener(this);
        showDataButtoI.addActionListener(this);
        backButtoI.addActionListener(this);

        frame.add(updateButtoI);
        frame.add(addButtoI);
        frame.add(removeButtoI);
        frame.add(showDataButtoI);
        frame.add(backButtoI);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

 @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButtoI) {
             ProductEditForm updateItemClass = new ProductEditForm();
            // Call the necessary methods or perform actions in the UpdateItemClasI
        } else if (e.getSource() == addButtoI) {
            ProductEntryForm addItemClass = new ProductEntryForm();
            // Call the necessary methods or perform actions in the AddItemClasI
        } else if (e.getSource() == removeButtoI) {
            ProductRemovalForm removeItemClass = new ProductRemovalForm();
            // Call the necessary methods or perform actions in the RemoveItemClasI
        } else if (e.getSource() == showDataButtoI) {
            DatabaseDisplayApp showDataClass = new DatabaseDisplayApp();
            // Call the necessary methods or perform actions in the ShowDataClasI
    }
    else if (e.getSource() == backButtoI) {
            System.exit(0);
            // Call the necessary methods or perform actions in the ShowDataClasI
    }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MyGUI();
            }
        });
    }
}
