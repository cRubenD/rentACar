package Model;

import java.text.DateFormat;

public class Rentals {
    private Integer RentalID;
    private Integer CarID;
    private Integer CustomerID;
    private Integer EmployeeID;
    private DateFormat DataStart;
    private DateFormat DataFinish;
    private Double SumaTotala;

    public Integer getRentalID() {
        return RentalID;
    }

    public void setRentalID(Integer rentalID) {
        RentalID = rentalID;
    }

    public Integer getCarID() {
        return CarID;
    }

    public void setCarID(Integer carID) {
        CarID = carID;
    }

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer customerID) {
        CustomerID = customerID;
    }

    public Integer getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        EmployeeID = employeeID;
    }

    public DateFormat getDataStart() {
        return DataStart;
    }

    public void setDataStart(DateFormat dataStart) {
        DataStart = dataStart;
    }

    public DateFormat getDataFinish() {
        return DataFinish;
    }

    public void setDataFinish(DateFormat dataFinish) {
        DataFinish = dataFinish;
    }

    public Double getSumaTotala() {
        return SumaTotala;
    }

    public void setSumaTotala(Double sumaTotala) {
        SumaTotala = sumaTotala;
    }
}
