package ch.chiodo.pumper.persistence;

import android.provider.BaseColumns;

public final class ExerciseContract {
    /**
     * To prevent someone from accidentally instantiating the contract class,
     * provide an empty constructor
     */
    public ExerciseContract(){}
    public abstract static class Exercise implements BaseColumns{
        public static final String TABLE_NAME = "exercise";
        public static final String COLUMN_NAME_WEIGHT = "weight";
        public static final String COLUMN_NAME_REPETITION = "repetition";
        public static final String COLUMN_NAME_DEVICE = "device";
        public static final String COLUMN_NAME_TRAINING = "training";
    }
}
