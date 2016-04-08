package ch.chiodo.pumper.persistence.dbcontract;

import android.provider.BaseColumns;

public final class DeviceSettingContract {
    /**
     * To prevent someone from accidentally instantiating the contract class,
     * provide an empty constructor
     */
    public DeviceSettingContract(){}
    public static abstract class DeviceSetting implements BaseColumns {
        public static final String TABLE_NAME = "device_setting";
        public static final String COLUMN_NAME_DEVICE = "device";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_VALUE = "value";
    }
}
