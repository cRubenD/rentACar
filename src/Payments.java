import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Payments extends JDialog {
    private JPanel PaymentsPanel;
    private JTextArea textArea1;
    private JButton exitButton;

    public Payments(JDialog parent, String loginName){
        super(parent);
        setTitle("Payments");
        setContentPane(PaymentsPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        textArea1.setEditable(false);

        String sql = "SELECT Cars.Model, Cars.NumarInmatriculare, Payments.DataPlatii, Payments.SumaPlatita, " +
                "PaymentMethods.MetodaPlata, PaymentMethods.Descriere " +
                "FROM Cars " +
                "JOIN Rentals ON Cars.CarID = Rentals.CarID " +
                "JOIN Customers ON Rentals.CustomerID = Customers.CustomerID " +
                "JOIN Payments ON Rentals.RentalID = Payments.RentalID " +
                "JOIN PaymentMethods ON Payments.PaymentMethodID = PaymentMethods.PaymentMethodID " +
                "WHERE Customers.Nume = ?";
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
                textArea1.append("DataPlatii: " + result.getString("DataPlatii") + "\n");
                textArea1.append("SumaPlatita: " + result.getString("SumaPlatita") + "\n");
                textArea1.append("MetodaPlata: " + result.getString("MetodaPlata") + "\n");
                textArea1.append("Descriere: " + result.getString("Descriere") + "\n\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException appropriately
        }

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CustomerDashboard customerDashboard = new CustomerDashboard((JDialog) Payments.this,loginName);
                customerDashboard.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {

    }
}


