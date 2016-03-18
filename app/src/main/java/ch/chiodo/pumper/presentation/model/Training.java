package ch.chiodo.pumper.presentation.model;

public class Training {
    private long id;
    private String name;

    public Training(String name){
        this.name = name;
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void update(Training other){
        this.name = other.name;
    }
}
