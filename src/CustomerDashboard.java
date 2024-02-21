import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDashboard extends JDialog {
    private JPanel customerDashboardPanel;
    private JButton paymentsButton;
    private JLabel LabelName;
    private JButton rentStatsButton;
    private JButton exitButton;
    private JComboBox comboBoxCars;

    public CustomerDashboard(JDialog parent, String loginName) {
        super(parent);
        setTitle("Customer Dashboard");
        setContentPane(customerDashboardPanel);
        setMinimumSize(new Dimension(550, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        LabelName.setText("Welcome, " + loginName);

        if(comboBoxCars != null){
            String[] carsFromDatabase = fetchCarsFromDatabase();
            comboBoxCars.setModel(new DefaultComboBoxModel<>(carsFromDatabase));
            comboBoxCars.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedCar = comboBoxCars.getSelectedItem().toString();
                    System.out.println("Selected car: " + selectedCar);
                }
            });

            // Adaugă un MouseListener pentru a detecta dublu-clicul
            comboBoxCars.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        // Dublu-clic detectat
                        dispose();
                        String selectedCar = comboBoxCars.getSelectedItem().toString();
                        Cars cars= null;
                        try {
                            cars = new Cars( (JDialog) CustomerDashboard.this, selectedCar);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }
                        cars.setVisible(true);
                    }
                }
            });
        }
        // Add your action listeners for buttons here
        paymentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Payments payments = new Payments((JDialog) CustomerDashboard.this, loginName);
                payments.setVisible(true);
            }
        });

        rentStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the stored procedure with the logged-in customer's name
                try {
                    String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
                    String user = "root";
                    String password = "Formula15!";

                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        // Prepare and execute the stored procedure
                        try (CallableStatement callableStatement = connection.prepareCall("{call GetCustomerRentalStats(?)}")) {
                            callableStatement.setString(1, loginName);

                            // Execute the stored procedure and get the result set
                            try (ResultSet resultSet = callableStatement.executeQuery()) {
                                // Display the result in a new Rentals form
                                Rentals rentalsForm = new Rentals(CustomerDashboard.this, resultSet, loginName);
                                rentalsForm.setVisible(true);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(CustomerDashboard.this, "Error retrieving rental statistics.");
                }
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login login = new Login((JDialog) CustomerDashboard.this);
                login.setVisible(true);
            }
        });
    }
    private String[] fetchCarsFromDatabase() {
        List<String> carsList = new ArrayList<>();

        String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        String user = "root";
        String password = "Formula15!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT DISTINCT model FROM cars";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    carsList.add(resultSet.getString("model"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return carsList.toArray(new String[0]);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CustomerDashboard customerForm = new CustomerDashboard(null, " ");
                customerForm.setVisible(true);
            }
        });
        String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        String user = "root";
        String password = "Formula15!";

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            //System.out.
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
    }
}
