package Model;

public class PaymentMethods {
    private Integer PaymentMethodID;
    private String MetodaPlata;
    private String Descriere;

    public Integer getPaymentMethodID() {
        return PaymentMethodID;
    }

    public void setPaymentMethodID(Integer paymentMethodID) {
        PaymentMethodID = paymentMethodID;
    }

    public String getMetodaPlata() {
        return MetodaPlata;
    }

    public void setMetodaPlata(String metodaPlata) {
        MetodaPlata = metodaPlata;
    }

    public String getDescriere() {
        return Descriere;
    }

    public void setDescriere(String descriere) {
        Descriere = descriere;
    }
}
