package brickyard.tracker.bean;

public class CategoryBean {
    private int id, type;
    private String name;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CategoryBean(int id, String name, int type) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public CategoryBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategoryBean(String name, int type) {
        this.type = type;
        this.name = name;
    }
}