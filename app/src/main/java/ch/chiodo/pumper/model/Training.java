package ch.chiodo.pumper.model;

import java.util.ArrayList;
import java.util.List;

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

    public void update(long id, String name){
        this.id = id;
        this.name = name;
    }
}
