package brickyard.tracker.bean;

import java.io.Serializable;

public class RecordBean implements Serializable {

    private int id;
    private long longDate;
    private String division, circle, sector, brickyardName, address, area, brickType, status, financialYear, installment1, installment2, installment3;
    private String preDueAmount, preVatPaidAmount, totalPaidAmount, currentDueAmount, note;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public long getLongDate() { return longDate; }
    public void setLongDate(long longDate) { this.longDate = longDate; }
    public String getDivision() { return division; }
    public void setDivision(String division) { this.division = division; }
    public String getCircle() { return circle; }
    public void setCircle(String circle) { this.circle = circle; }
    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }
    public String getBrickyardName() { return brickyardName; }
    public void setBrickyardName(String brickyardName) { this.brickyardName = brickyardName; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }
    public String getBrickType() { return brickType; }
    public void setBrickType(String brickType) { this.brickType = brickType; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getFinancialYear() { return financialYear; }
    public void setFinancialYear(String financialYear) { this.financialYear = financialYear; }
    public String getInstallment1() { return installment1; }
    public void setInstallment1(String installment1) { this.installment1 = installment1; }
    public String getInstallment2() { return installment2; }
    public void setInstallment2(String installment2) { this.installment2 = installment2; }
    public String getInstallment3() { return installment3; }
    public void setInstallment3(String installment3) { this.installment3 = installment3; }
    public String getPreDueAmount() { return preDueAmount; }
    public void setPreDueAmount(String preDueAmount) { this.preDueAmount = preDueAmount; }
    public String getPreVatPaidAmount() { return preVatPaidAmount; }
    public void setPreVatPaidAmount(String preVatPaidAmount) { this.preVatPaidAmount = preVatPaidAmount; }
    public String getTotalPaidAmount() { return totalPaidAmount; }
    public void setTotalPaidAmount(String totalPaidAmount) { this.totalPaidAmount = totalPaidAmount; }
    public String getCurrentDueAmount() { return currentDueAmount; }
    public void setCurrentDueAmount(String currentDueAmount) { this.currentDueAmount = currentDueAmount; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }



    public RecordBean(int id, long longDate, String division, String circle, String sector, String brickyardName, String address, String area, String brickType, String status,
           String financialYear, String installment1, String installment2, String installment3, String preDueAmount, String preVatPaidAmount, String totalPaidAmount, String currentDueAmount, String note) {

        this.id = id;
        this.longDate = longDate;
        this.division = division;
        this.circle = circle;
        this.sector = sector;
        this.brickyardName = brickyardName;
        this.address = address;
        this.area = area;
        this.brickType = brickType;
        this.status = status;
        this.financialYear = financialYear;
        this.installment1 = installment1;
        this.installment2 = installment2;
        this.installment3 = installment3;
        this.preDueAmount = preDueAmount;
        this.preVatPaidAmount = preVatPaidAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.currentDueAmount = currentDueAmount;
        this.note = note;
    }

    public RecordBean(long longDate, String division, String circle, String sector, String brickyardName, String address, String area, String brickType, String status,
           String financialYear, String installment1, String installment2, String installment3, String preDueAmount, String preVatPaidAmount, String totalPaidAmount, String currentDueAmount, String note) {

        this.longDate = longDate;
        this.division = division;
        this.circle = circle;
        this.sector = sector;
        this.brickyardName = brickyardName;
        this.address = address;
        this.area = area;
        this.brickType = brickType;
        this.status = status;
        this.financialYear = financialYear;
        this.installment1 = installment1;
        this.installment2 = installment2;
        this.installment3 = installment3;
        this.preDueAmount = preDueAmount;
        this.preVatPaidAmount = preVatPaidAmount;
        this.totalPaidAmount = totalPaidAmount;
        this.currentDueAmount = currentDueAmount;
        this.note = note;
    }
}
