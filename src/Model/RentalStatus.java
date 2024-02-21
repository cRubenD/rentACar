package Model;

public class RentalStatus {
    private Integer RentalStatusId;
    private Integer RentalID;
    private String Status;

    public Integer getRentalStatusId() {
        return RentalStatusId;
    }

    public void setRentalStatusId(Integer rentalStatusId) {
        RentalStatusId = rentalStatusId;
    }

    public Integer getRentalID() {
        return RentalID;
    }

    public void setRentalID(Integer rentalID) {
        RentalID = rentalID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
