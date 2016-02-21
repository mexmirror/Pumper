package ch.chiodo.pumper.persistence;

import android.provider.BaseColumns;

public final class ExecutionContract {
    /**
     * To prevent someone from accidentally instantiating the contract class,
     * provide an empty constructor
     */
    public ExecutionContract(){}
    public static abstract class Execution implements BaseColumns{
        public static final String TABLE_NAME = "execution";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TRAINING = "training";
    }
}
