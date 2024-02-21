import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Rentals extends JDialog {
    private JButton exitButton;
    private JTextArea textArea1;
    private JTextField textField1;
    private JTextField textField2;
    private JPanel rentalsPanel;

    public Rentals(JDialog parent, ResultSet resultSet, String loginName) {
        super(parent);
        setTitle("Rentals Panel");
        setContentPane(rentalsPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        displayRentalDataInFields(resultSet);
        textArea1.setEditable(false);
        textField1.setEditable(false);
        textField2.setEditable(false);

        String sql = "SELECT Model, NumarInmatriculare, DataStart, DataFinish, SumaTotala " +
                "FROM Cars, Rentals, customers " +
                "WHERE Rentals.CustomerID = customers.CustomerID " +
                "AND Rentals.CarID = cars.CarID " +
                "AND customers.Nume = ?";


        try (Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/rentacar2", "root", "Formula15!");
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, loginName);
            ResultSet result = preparedStatement.executeQuery();

            // Clear existing text in textArea1
            textArea1.setText("");

            while (result.next()) {
                // Append data to textArea1
                textArea1.append("Model: " + result.getString("Model") + "\n");
                textArea1.append("NumarInmatriculare: " + result.getString("NumarInmatriculare") + "\n");
                textArea1.append("DataStart: " + result.getString("DataStart") + "\n");
                textArea1.append("DataFinish: " + result.getString("DataFinish") + "\n");
                textArea1.append("SumaTotala: " + result.getString("SumaTotala") + "\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void displayRentalDataInFields(ResultSet resultSet) {
        try {
            StringBuilder resultText = new StringBuilder();
            while (resultSet.next()) {
                textField1.setText(resultSet.getString(2));
                textField2.setText(resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error displaying rental data.");
        }
    }


    public static void main(String[] args) {
        // This is just for testing the Rentals clas
    }


    // This is just for testing the Rentals class
    private static ResultSet getTestResultSet() {
        // Implement this method to return a ResultSet with test data
        return null;
    }
}
