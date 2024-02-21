package Model;

import java.text.DateFormat;

public class Payments {
    private Integer PaymentID;
    private Integer RentalID;
    private Integer PaymentMethodID;
    private DateFormat DataPlatii;
    private Double SumaPlatita;

    public Integer getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(Integer paymentID) {
        PaymentID = paymentID;
    }

    public Integer getRentalID() {
        return RentalID;
    }

    public void setRentalID(Integer rentalID) {
        RentalID = rentalID;
    }

    public Integer getPaymentMethodID() {
        return PaymentMethodID;
    }

    public void setPaymentMethodID(Integer paymentMethodID) {
        PaymentMethodID = paymentMethodID;
    }

    public DateFormat getDataPlatii() {
        return DataPlatii;
    }

    public void setDataPlatii(DateFormat dataPlatii) {
        DataPlatii = dataPlatii;
    }

    public Double getSumaPlatita() {
        return SumaPlatita;
    }

    public void setSumaPlatita(Double sumaPlatita) {
        SumaPlatita = sumaPlatita;
    }
}
