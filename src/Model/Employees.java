package Model;

import java.text.DateFormat;

public class Employees {
    public Integer EmployeeID;
    public String Nume;
    public String Functie;
    public DateFormat DataAngajarii;
    public String Password;

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        EmployeeID = employeeID;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getFuctie() {
        return Functie;
    }

    public void setFuctie(String fuctie) {
        Functie = fuctie;
    }

    public DateFormat getDataAngajarii() {
        return DataAngajarii;
    }

    public void setDataAngajarii(DateFormat dataAngajarii) {
        DataAngajarii = dataAngajarii;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
