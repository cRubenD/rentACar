package Model;

public class Cars {

    private Integer CarID;
    private String Model;
    private Integer An;
    private String NumarInmatriculare;
    private boolean Disponibilitate;
    private Integer locationId;
    public Cars() {
    }

    public Integer getCarID() {
        return CarID;
    }

    public void setCarID(Integer carID) {
        CarID = carID;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        Model = model;
    }

    public Integer getAn() {
        return An;
    }

    public void setAn(Integer an) {
        An = an;
    }

    public String getNumarInmatriculare() {
        return NumarInmatriculare;
    }

    public void setNumarInmatriculare(String numarInmatriculare) {
        NumarInmatriculare = numarInmatriculare;
    }

    public boolean isDisponibilitate() {
        return Disponibilitate;
    }

    public void setDisponibilitate(boolean disponibilitate) {
        Disponibilitate = disponibilitate;
    }
}
