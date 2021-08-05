package brickyard.tracker.bean;

public class BrickyardBean {
    private int id;
    private String name, division, circle, sector;

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

    public void setName(String name) {
        this.name = name;
    }


    public BrickyardBean(int id, String name, String division, String circle, String sector) {
        this.id = id;
        this.name = name;
        this.division = division;
        this.circle = circle;
        this.sector = sector;
    }

    public BrickyardBean(String name, String division, String circle, String sector) {
        this.name = name;
        this.division = division;
        this.circle = circle;
        this.sector = sector;
    }
}