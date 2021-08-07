package brickyard.tracker.bean;

public class DivisionBean {
    private int id;
    private String name, commissionerate;

    public String getCommissionerate() { return commissionerate; }

    public void setCommissionerate(String commissionerate) { this.commissionerate = commissionerate; }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public DivisionBean(int id, String name, String commissionerate) {
        this.id = id;
        this.name = name;
        this.commissionerate = commissionerate;
    }

    public DivisionBean(String name, String commissionerate) {
        this.name = name;
        this.commissionerate = commissionerate;
    }
}