package ch.chiodo.pumper.model;

import java.util.Date;

public class Execution {
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
}
