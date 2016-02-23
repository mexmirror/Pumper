package ch.chiodo.pumper.model;

import java.util.Date;

public class Execution {
    private long id;
    private Date date;
    private Training training;

    public Execution(Training training, Date date){
        this.date = date;
    }

    public Execution(Training training){
        this(training, new Date());
    }

    public Date getDate() {
        return date;
    }

    public Training getTraining() {
        return training;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void update(long id, Training training, Date date) {
        this.id = id;
        this.training = training;
        this.date = date;
    }
}
