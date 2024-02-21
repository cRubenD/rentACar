import Model.Customers;
import Model.Employees;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Registration extends JDialog{
    private JButton Back;
    private JPanel RegistrationPanel;
    private JButton registerButton;
    private JButton cancelButton;
    private JLabel LabelNumeEmpl;
    private JLabel LabelFunctieEmpl;
    private JLabel LabelNumeCust;
    private JLabel LabelAdresaCust;
    private JLabel LabelEmailCust;
    private JTextField textFieldNumeEmpl;
    private JTextField textFieldFunctieEmpl;
    private JTextField textFieldNumeCust;
    private JTextField textFieldAdresaCust;
    private JTextField textFieldEmailCust;
    private JTextField textFieldTelCust;
    private JLabel LabelTelCust;
    private JRadioButton radioButtonEmployee;
    private JRadioButton radioButtonCustomer;
    private JLabel LabelPassword;
    private JTextField textFieldPassword;
    private JTextField textFieldPassword2;
    private JLabel LabelPassword2;
    private ButtonGroup buttonGroup;

    public Registration(JDialog parent){
        super(parent);
        //
        buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButtonEmployee);
        buttonGroup.add(radioButtonCustomer);

        LabelNumeEmpl.setVisible(false);
        LabelFunctieEmpl.setVisible(false);
        LabelNumeCust.setVisible(false);
        LabelAdresaCust.setVisible(false);
        LabelEmailCust.setVisible(false);
        LabelTelCust.setVisible(false);

        LabelPassword2.setVisible(false);
        textFieldPassword2.setVisible(false);
        LabelPassword.setVisible(false);
        textFieldPassword.setVisible(false);

        textFieldNumeEmpl.setVisible(false);
        textFieldFunctieEmpl.setVisible(false);
        textFieldNumeCust.setVisible(false);
        textFieldAdresaCust.setVisible(false);
        textFieldEmailCust.setVisible(false);
        textFieldTelCust.setVisible(false);
        //
        setTitle("Registration");
        setContentPane(RegistrationPanel);
        setMinimumSize(new Dimension(750, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        radioButtonEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the "Empl" labels and text fields
                    LabelNumeEmpl.setVisible(true);
                    LabelFunctieEmpl.setVisible(true);
                    LabelPassword.setVisible(true);
                    textFieldNumeEmpl.setVisible(true);
                    textFieldFunctieEmpl.setVisible(true);
                    textFieldPassword.setVisible(true);


                    // Hide the "Cust" labels and text fields
                    LabelNumeCust.setVisible(false);
                    LabelAdresaCust.setVisible(false);
                    LabelEmailCust.setVisible(false);
                    LabelTelCust.setVisible(false);
                    LabelPassword2.setVisible(false);
                    textFieldNumeCust.setVisible(false);
                    textFieldAdresaCust.setVisible(false);
                    textFieldEmailCust.setVisible(false);
                    textFieldTelCust.setVisible(false);
                    textFieldPassword2.setVisible(false);

            }
        });
        radioButtonCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            // Show the "Cust" labels and text fields
                LabelNumeCust.setVisible(true);
                LabelAdresaCust.setVisible(true);
                LabelEmailCust.setVisible(true);
                LabelTelCust.setVisible(true);
                LabelPassword2.setVisible(true);
                textFieldNumeCust.setVisible(true);
                textFieldAdresaCust.setVisible(true);
                textFieldEmailCust.setVisible(true);
                textFieldTelCust.setVisible(true);
                textFieldPassword2.setVisible(true);
                // Hide the "Empl" labels and text fields
                LabelNumeEmpl.setVisible(false);
                LabelFunctieEmpl.setVisible(false);
                LabelPassword.setVisible(false);
                textFieldNumeEmpl.setVisible(false);
                textFieldFunctieEmpl.setVisible(false);
                textFieldPassword.setVisible(false);
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get data from text fields
                if (radioButtonCustomer.isSelected()) {
                    System.out.println("Customer selected");
                    // ... (rest of the customer registration logic)
                } else if (radioButtonEmployee.isSelected()) {
                    System.out.println("Employee selected");
                    // ... (rest of the employee registration logic)
                } else {
                    System.out.println("No selection");
                }
                // Get data from text fields
                if (radioButtonCustomer.isSelected()) {
                    String Nume = textFieldNumeCust.getText();
                    String Adresa = textFieldAdresaCust.getText();
                    String Telefon = textFieldTelCust.getText();  // You might want to add a JTextField for Telefon as well
                    String Email = textFieldEmailCust.getText();
                    String Password = textFieldPassword2.getText();

                    // Add customer to the database
                    Customers customers = addCustomerToDatabase(Nume, Adresa, Telefon, Email, Password);

                    if (customers != null) {
                        System.out.println("Successful registration of: " + customers.Nume);
                    } else {
                        System.out.println("Registration canceled");
                    }
                } else if(radioButtonEmployee.isSelected()) {
                    String Nume = textFieldNumeEmpl.getText();
                    String Functie = textFieldFunctieEmpl.getText();
                    String Password = textFieldPassword.getText();
                    Employees employee = addEmployeeToDatabase(Nume, Functie, Password);

                    if (employee != null) {
                        System.out.println("Successful registration of employee: " + employee.Nume);
                    } else {
                        System.out.println("Registration canceled");
                    }
                }
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.setVisible(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.setVisible(true);
            }
        });
    }

    private void registerCustomer(){

    }

    public Customers customers;
    private Customers addCustomerToDatabase(String Nume, String Adresa, String Telefon, String Email, String Password){
        Customers customers = null;
        final String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        final String user = "root";
        final String password = "Formula15!";

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Customers (Nume, Adresa, Telefon, Email, Password)" + "VALUES(?, ? ,?, ?, ?)";
            //System.out.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Nume);
            preparedStatement.setString(2, Adresa);
            preparedStatement.setString(3, Telefon);
            preparedStatement.setString(4, Email);
            preparedStatement.setString(5, Password);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                customers = new Customers();
                customers.Nume = Nume;
                customers.Adresa = Adresa;
                customers.Telefon = Telefon;
                customers.Email = Email;
                customers.Password = Password;
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
        return customers;
    }

    public Employees employees;
    private Employees addEmployeeToDatabase(String Nume, String Functie, String Password){
        Employees employees = null;
        final String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        final String user = "root";
        final String password = "Formula15!";

        Connection connection;
        try {
            connection = DriverManager.getConnection(url, user, password);
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO Employees (Nume, Functie, Password)" + "VALUES(?, ?, ?)";
            //System.out.
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Nume);
            preparedStatement.setString(2, Functie);
            preparedStatement.setString(3, Password);

            int addedRows = preparedStatement.executeUpdate();
            if(addedRows > 0){
                employees = new Employees();
                employees.Nume = Nume;
                employees.Functie = Functie;
                employees.Password = Password;
            }
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            connection = null;
        }
        return employees;
    }

    public static void main(String[] args) {
        Registration myForm = new Registration(null);
        Customers customers = myForm.customers;
        if (customers != null){
            System.out.println("Successful registrion of: " + customers.Nume);
        }
        else{
            System.out.println("Registration canceled");
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
