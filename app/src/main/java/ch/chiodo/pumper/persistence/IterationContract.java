package ch.chiodo.pumper.persistence;

import android.provider.BaseColumns;

public final class IterationContract {
    /**
     * To prevent someone from accidentally instantiating the contract class,
     * provide an empty constructor
     */
    public IterationContract(){}

    public static abstract class Iteration implements BaseColumns{
        public final static String TABLE_NAME = "iteration";
        public final static String COLUMN_NAME_WEIGHT = "weight";
        public final static String COLUMN_NAME_REPETITION = "repetition";
        public final static String COLUMN_NAME_EXECUTION = "execution";
        public final static String COLUMN_NAME_EXERCISE = "exercise";
    }
}
