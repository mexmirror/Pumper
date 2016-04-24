package ch.chiodo.pumper.model;

import org.joda.time.DateTime;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Execution implements Updateable<Execution>{
    private long id;
    private DateTime date;
    private Training training;

    public Execution(Training training, DateTime date){
        this.date = date;
        this.training = training;
    }

    public Execution(Training training){
        this(training, new DateTime());
    }

    public DateTime getDate() {
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
