package brickyard.tracker.bean;

public class CircleBean {
    private int id;
    private String name, commissionerate, division;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDivision() { return division; }

    public void setDivision(String division) { this.division = division; }

    public String getCommissionerate() { return commissionerate; }

    public void setCommissionerate(String commissionerate) { this.commissionerate = commissionerate; }

    public CircleBean(int id, String name, String commissionerate, String division) {
        this.id = id;
        this.name = name;
        this.division = division;
        this.commissionerate = commissionerate;
    }

    public CircleBean(String name, String commissionerate, String division) {
        this.name = name;
        this.division = division;
        this.commissionerate = commissionerate;
    }
}