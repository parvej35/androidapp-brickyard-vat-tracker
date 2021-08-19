package brickyard.tracker.bean;

public class BrickyardBean {
    private int id;
    private String name, address, commissionerate, division, circle, sector, area, type, status;

    public String getCommissionerate() { return commissionerate; }

    public void setCommissionerate(String commissionerate) { this.commissionerate = commissionerate; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public String getCircle() { return circle; }

    public void setCircle(String circle) { this.circle = circle; }

    public String getSector() { return sector; }

    public void setSector(String sector) { this.sector = sector; }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getArea() { return area; }

    public void setArea(String area) { this.area = area; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public BrickyardBean(int id, String name, String address, String commissionerate, String division, String circle, String sector, String area, String type, String status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.commissionerate = commissionerate;
        this.division = division;
        this.circle = circle;
        this.sector = sector;
        this.area = area;
        this.type = type;
        this.status = status;
    }

    public BrickyardBean(String name, String address, String commissionerate, String division, String circle, String sector, String area, String type, String status) {
        this.name = name;
        this.address = address;
        this.commissionerate = commissionerate;
        this.division = division;
        this.circle = circle;
        this.sector = sector;
        this.area = area;
        this.type = type;
        this.status = status;
    }
}