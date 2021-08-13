package brickyard.tracker.bean;

public class SectorBean {
    private int id;
    private String name, commissionerate, division, circle;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public String getCommissionerate() { return commissionerate; }

    public void setCommissionerate(String commissionerate) { this.commissionerate = commissionerate; }

    public String getCircle() { return circle; }

    public void setCircle(String circle) { this.circle = circle; }

    public SectorBean(int id, String name, String commissionerate, String division, String circle) {
        this.id = id;
        this.name = name;
        this.commissionerate = commissionerate;
        this.division = division;
        this.circle = circle;
    }

    public SectorBean(String name, String commissionerate, String division, String circle) {
        this.name = name;
        this.commissionerate = commissionerate;
        this.division = division;
        this.circle = circle;
    }
}