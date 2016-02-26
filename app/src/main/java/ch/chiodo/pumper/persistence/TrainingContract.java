package ch.chiodo.pumper.persistence;


import android.provider.BaseColumns;

public final class TrainingContract {
    /**
     * To prevent someone from accidentally instantiating the contract class,
     * provide an empty constructor
     */
    public TrainingContract(){}
    public static abstract class Training implements BaseColumns {
        public static final String TABLE_NAME = "training";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
