package Model;

import java.text.DateFormat;

public class Feedback {
    private Integer FeedbackID;
    private Integer RentalID;
    private Integer CustomerID;

    private Integer CarID;
    private Integer NotaAcordata;
    private String Comentariu;
    private DateFormat DataFeedback;

    public Integer getFeedbackID() {
        return FeedbackID;
    }

    public void setFeedbackID(Integer feedbackID) {
        FeedbackID = feedbackID;
    }

    public Integer getRentalID() {
        return RentalID;
    }

    public void setRentalID(Integer rentalID) {
        RentalID = rentalID;
    }

    public Integer getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(Integer customerID) {
        CustomerID = customerID;
    }

    public Integer getCarID() {
        return CarID;
    }

    public void setCarID(Integer carID) {
        CarID = carID;
    }

    public Integer getNotaAcordata() {
        return NotaAcordata;
    }

    public void setNotaAcordata(Integer notaAcordata) {
        NotaAcordata = notaAcordata;
    }

    public String getComentariu() {
        return Comentariu;
    }

    public void setComentariu(String comentariu) {
        Comentariu = comentariu;
    }

    public DateFormat getDataFeedback() {
        return DataFeedback;
    }

    public void setDataFeedback(DateFormat dataFeedback) {
        DataFeedback = dataFeedback;
    }
}
