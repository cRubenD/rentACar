package Model;

import java.text.DateFormat;

public class InsurancePolicies {
    private Integer InsurancePolicyID;
    private Integer CarID;
    private String TipAsigurare;
    private Double SumaAsigurata;
    private DateFormat DataStart;
    private DateFormat DataFinish;

    public Integer getInsurancePolicyID() {
        return InsurancePolicyID;
    }

    public void setInsurancePolicyID(Integer insurancePolicyID) {
        InsurancePolicyID = insurancePolicyID;
    }

    public Integer getCarID() {
        return CarID;
    }

    public void setCarID(Integer carID) {
        CarID = carID;
    }

    public String getTipAsigurare() {
        return TipAsigurare;
    }

    public void setTipAsigurare(String tipAsigurare) {
        TipAsigurare = tipAsigurare;
    }

    public Double getSumaAsigurata() {
        return SumaAsigurata;
    }

    public void setSumaAsigurata(Double sumaAsigurata) {
        SumaAsigurata = sumaAsigurata;
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
}
