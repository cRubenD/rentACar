import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Cars extends JDialog{
    private JPanel carsPanel;
    private JTextField modelTextField;
    private JTextField anTextField;
    private JTextField numarInmatriculareTextField;
    private JTextField disponibilitateTextField;
    private JTextField locatieTextField;
    private JButton Back;
    private JLabel iconLabel;
    private JButton inchiriazaButton;
    private JButton stergeButton;
    private JComboBox comboBoxMoveCar;
    private JLabel moveCarLabel;
    private int selectedCarID;


    public Cars(JDialog parent, String selectedCar) throws SQLException {
        super(parent);
        setTitle("Cars");
        setContentPane(carsPanel);
        setMinimumSize(new Dimension(950, 700));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        stergeButton.setVisible(false);

        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.setVisible(true);
                if (parent instanceof Admin) {
                    moveCarLabel.setVisible(false);
                    comboBoxMoveCar.setVisible(false);
                    stergeButton.setVisible(false);
                }

            }
        });
        loadDataFromDatabase(selectedCar);
        loadCarImage(selectedCar);

        if (parent instanceof Admin) {
            stergeButton.setVisible(true); // Face butonul vizibil
        } else {
            stergeButton.setVisible(false); // Ascunde butonul
        }
        if (parent instanceof Admin) {
            comboBoxMoveCar.setVisible(true);
            moveCarLabel.setVisible(true);
        } else {
            moveCarLabel.setVisible(false);
            comboBoxMoveCar.setVisible(false);
        }
        stergeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
                String user = "root";
                String password = "Formula15!";
                try {

                    // Apelul funcției prin intermediul unei instrucțiuni SELECT
                    try (Connection connection = DriverManager.getConnection(url, user, password)) {
                        String sql = "SELECT getCarIDByModel(?) AS car_id";
                        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                            preparedStatement.setString(1, selectedCar);

                            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                                if (resultSet.next()) {
                                    int carID = resultSet.getInt("car_id");

                                    // Acum poți apela procedura stocată pentru ștergere
                                    try (CallableStatement callableStatement = connection.prepareCall("{CALL deleteCarById(?)}")) {
                                        callableStatement.setInt(1, carID);
                                        callableStatement.execute();
                                    }

                                    JOptionPane.showMessageDialog(carsPanel, "Mașina a fost ștearsă cu succes!");
                                    stergeButton.setVisible(false);
                                    dispose(); // Închide fereastra după ștergere
                                    ((Admin) parent).updateComboBoxCars();
                                    parent.setVisible(true);
                                } else {
                                    JOptionPane.showMessageDialog(carsPanel, "Mașina nu a fost găsită în baza de date!");
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(carsPanel, "Eroare la ștergerea mașinii!");
                }
            }
        });

        comboBoxMoveCar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedLocation = comboBoxMoveCar.getSelectedItem().toString();
                System.out.println("Selected lovation: " + selectedLocation);
            }
        });
        comboBoxMoveCar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    String selectedLocation = comboBoxMoveCar.getSelectedItem().toString();
                    // Assuming you have a method to map location names to location IDs
                    int locationID = getLocationID(selectedLocation);
                    updateCarLocation(locationID);
                }
            }
        });
    }
    public static int getLocationID(String locationName) {
        String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        String user = "root";
        String password = "Formula15!";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String sql = "SELECT LocationID FROM locations WHERE NumeLocatie = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, locationName);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getInt("LocationID");

                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1; // Return -1 if location ID is not found
    }
    private void updateCarLocation(int newLocationID) {
        String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        String user = "root";
        String password = "Formula15!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            try (CallableStatement callableStatement = connection.prepareCall("{CALL updateCarLocation(?, ?)}")) {
                callableStatement.setInt(1, selectedCarID);
                callableStatement.setInt(2, newLocationID);
                callableStatement.execute();

                JOptionPane.showMessageDialog(carsPanel, "Locația mașinii a fost actualizată cu succes!");
                // You may want to update the displayed information accordingly
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(carsPanel, "Eroare la actualizarea locației mașinii!");
        }
    }

    private void loadDataFromDatabase(String selectedCar) throws SQLException {
        String url = "jdbc:mysql://127.0.0.1:3306/rentacar2";
        String user = "root";
        String password = "Formula15!";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String locationSql = "SELECT DISTINCT NumeLocatie FROM locations";
            try (PreparedStatement locationStatement = connection.prepareStatement(locationSql);
                 ResultSet locationResultSet = locationStatement.executeQuery()) {

                // Clear and populate the combo box with location names
                comboBoxMoveCar.removeAllItems();
                while (locationResultSet.next()) {
                    String locationName = locationResultSet.getString("NumeLocatie");
                    comboBoxMoveCar.addItem(locationName);
                }
            }
            String sql = "SELECT cars.*, locations.NumeLocatie FROM cars " +
                    "INNER JOIN locations ON cars.locationId = locations.LocationID " +
                    "WHERE cars.Model = ?"; // Modify this query

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, selectedCar);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        // Retrieve data from the ResultSet and set it in text fields
                        String model = resultSet.getString("Model");
                        int an = resultSet.getInt("An");
                        String NumarInmatriculare = resultSet.getString("NumarInmatriculare");
                        int disponibilitate;
                        disponibilitate = resultSet.getInt("Disponibilitate");
                        String disponibil;
                        if(disponibilitate == 1)
                            disponibil = "Disponibil";
                        else
                            disponibil = "Momentan indisponibil";
                        String numeLocatie = resultSet.getString("NumeLocatie");

                        modelTextField.setText(model);
                        anTextField.setText(String.valueOf(an));
                        numarInmatriculareTextField.setText(NumarInmatriculare);
                        disponibilitateTextField.setText(disponibil);
                        locatieTextField.setText(numeLocatie);
                        selectedCarID = resultSet.getInt("CarID");

                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadCarImage(String selectedCar) {
        // Assuming you have image files named with the car model (e.g., "car1.jpg", "car2.jpg")
        ImageIcon carImage = new ImageIcon("C:\\Users\\Gabi\\Desktop\\UTCN\\An II\\Sem I\\BD\\Proiect\\Prj\\inchirieriAuto\\src\\Images\\" + selectedCar + ".jpeg");
        iconLabel.setIcon(carImage);
    }

    public static void main(String[] args) throws SQLException {
        Cars myForm = new Cars(null, " ");
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
