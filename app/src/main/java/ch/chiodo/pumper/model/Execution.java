package ch.chiodo.pumper.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Execution implements Updateable<Execution>{
    private long id;
    private Calendar date;
    private Training training;

    public Execution(Training training, Calendar date){
        this.date = date;
        this.training = training;
    }

    public Execution(Training training){
        this(training, GregorianCalendar.getInstance());
    }

    public Calendar getDate() {
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

    public void update(Execution other) {
        this.training = other.training;
        this.date = other.date;
    }
}
