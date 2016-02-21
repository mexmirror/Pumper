package ch.chiodo.pumper.persistence;

import android.provider.BaseColumns;

public final class DeviceContract {
    /**
     * To prevent someone from accidentally instantiating the contract class,
     * provide an empty constructor
     */
    public DeviceContract(){}

    public static abstract class Device implements BaseColumns {
        public static final String TABLE_NAME = "device";
        public static final String COLUMN_NAME_DEVICE_ID = "deviceId";
    }

}
