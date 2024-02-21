import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class Login extends JDialog{
    private JPanel loginPanel;
    private JTextField tfPassword;
    private JTextField tfUsername;
    private JComboBox comboBoxUser;
    private JButton nextLogin;
    private JButton registerButton;
    private String Role;

    public Login(JDialog parent) {
        super(parent);
        setTitle("Login");
        setContentPane(loginPanel);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        nextLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Name = tfUsername.getText();
                String Password = tfPassword.getText();
                if("Admin".equals(Role) && Name.equals("admin") && Password.equals("admin")) {
                    Admin admin = new Admin((JDialog) Login.this);
                    admin.setVisible(true);
                    if (verifyCredentials(Name, Password)) {
                        JOptionPane.showMessageDialog(Login.this, "Login successful!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(Login.this, "Invalid credentials. Try again.");
                    }
                }
                if (verifyCredentials(Name, Password)) {
                    JOptionPane.showMessageDialog(Login.this, "Login successful!");
                    // Open another form or perform additional actions
                    dispose();
                    if("Customer".equals(Role)) {
                        CustomerDashboard customerDashboard = new CustomerDashboard((JDialog) Login.this, Name);
                        customerDashboard.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid credentials. Try again.");
                }

                // Clear the password field for security
                tfPassword.setText("");
            }

        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Registration registration = new Registration((JDialog) Login.this);
                registration.setVisible(true);
            }
        });
        if (comboBoxUser != null) {
            comboBoxUser.setModel(new DefaultComboBoxModel<>(new String[]{"Admin", "Employee", "Customer"}));
            comboBoxUser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedRole = comboBoxUser.getSelectedItem().toString();
                    System.out.println("Selected Role: " + selectedRole);
                    Role = selectedRole;
                }
            });
        }
    }
    private boolean verifyCredentials(String Nume, String Password) {
        final String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        final String user = "root";
        final String dbPassword = "Formula15!";

        String sql = "SELECT 'Customers' AS UserType, Nume, Password FROM Customers WHERE Nume = ? AND Password = ? " +
                "UNION " +
                "SELECT 'Employees' AS UserType, Nume, Password FROM Employees WHERE Nume = ? AND Password = ?";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, Nume);
            preparedStatement.setString(2, Password);
            preparedStatement.setString(3, Nume);
            preparedStatement.setString(4, Password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there is a matching record, credentials are valid
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }



    public static void main(String[] args) {
        Login myForm = new Login(null);
        myForm.setVisible(true);
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
