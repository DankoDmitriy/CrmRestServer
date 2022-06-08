package com.danko.crm.service.constant;

public final class ValidationRegexp {
    public static final String CITY_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,65}$";

    public static final String DEPARTMENT_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,45}$";

    public static final String POSITION_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,255}$";
    public static final String POSITION_DESCRIPTION_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,255}$";
    public static final int POSITION_SUBORDINATION_LEVEL_MIN = 1;
    public static final int POSITION_SUBORDINATION_LEVEL_MAX = 100;

    public static final String PHONE_TYPE_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,55}$";

    public static final String EMPLOYEE_PHONE_NUMBER_REGEXP = "^[0-9\\s]{1,55}$";

    public static final String EMPLOYEE_FIRST_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,45}$";
    public static final String EMPLOYEE_LAST_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,45}$";
    public static final String EMPLOYEE_PATRONYMIC_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,45}$";
    public static final String EMPLOYEE_USER_NAME_REGEXP = "^[a-zA-Z0-9]{1,255}$";
    public static final String EMPLOYEE_TG_ID_REGEXP = "^[0-9]{0,255}$";
    public static final String EMPLOYEE_COMMENT_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s]{1,255}$";
    public static final String EMPLOYEE_PROXY_REGEXP = "^[a-zA-ZА-Яа-я0-9\\s-]{0,70}$";

    public static final String CAR_NUMBER_REGEXP = "^[a-zA-ZА0-9\\s-]{9,45}$";
    public static final String CAR_NUMBER_OTHER = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-]{0,255}$";

    public static final String LTD_BANK_REQUISITES_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-]{0,500}$";

    public static final String LTD_CONTRACT_NUMBER_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-]{0,100}$";
    public static final String LTD_CONTRACT_OTHER_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-]{0,255}$";

    public static final String LTD_NAME_FULL_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";
    public static final String LTD_NAME_SHORT_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";
    public static final String LTD_ADDRESS_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";
    public static final String LTD_UNP_REGEXP = "^[a-zA-ZА-Яа-я0-9.:\\s-]{0,45}$";

    public static final int LTD_INSTANCE_TYPE_MIN = 0;
    public static final int LTD_INSTANCE_TYPE_MAX = 1;
    public static final int LTD_INSTANCE_DISTANCE_MIN = 0;
    public static final int LTD_INSTANCE_DISTANCE_MAX = 5000;
    public static final int LTD_INSTANCE_TELECOM_CABINET_MIN = 0;
    public static final int LTD_INSTANCE_TELECOM_CABINET_MAX = 100;
    public static final int LTD_INSTANCE_UPS_MIN = 0;
    public static final int LTD_INSTANCE_UPS_MAX = 1000;
    public static final int LTD_INSTANCE_SERVER_MIN = 0;
    public static final int LTD_INSTANCE_SERVER_MAX = 1000;
    public static final int LTD_INSTANCE_SWITCHS_MIN = 0;
    public static final int LTD_INSTANCE_SWITCHS_MAX = 1000;
    public static final int LTD_INSTANCE_WORKPLACE_MIN = 0;
    public static final int LTD_INSTANCE_WORKPLACE_MAX = 1000;
    public static final int LTD_INSTANCE_EQUIPMENT_MIN = 0;
    public static final int LTD_INSTANCE_EQUIPMENT_MAX = 1000;
    public static final String LTD_INSTANCE_OTHERS_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";
    public static final String LTD_INSTANCE_ADDRESS_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";

    public static final int TICKET_TYPE_ACTION_MIN = -1;
    public static final int TICKET_TYPE_ACTION_MAX = 1;
    public static final int TICKET_TYPE_PRIORITY_MIN = 1;
    public static final int TICKET_TYPE_PRIORITY_MAX = 100;
    public static final String TICKET_TYPE_NAME_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,100}$";

    public static final int TICKET_OPEN_STATUS_MIN = 0;
    public static final int TICKET_OPEN_STATUS_MAX = 1;
    public static final int TICKET_SERVER_MIN = 0;
    public static final int TICKET_SERVER_MAX = 1000;
    public static final int TICKET_UPS_MIN = 0;
    public static final int TICKET_UPS_MAX = 1000;
    public static final int TICKET_SWITCHS_MIN = 0;
    public static final int TICKET_SWITCHS_MAX = 1000;
    public static final int TICKET_WORKPLACE_MIN = 0;
    public static final int TICKET_WORKPLACE_MAX = 1000;
    public static final int TICKET_EQUIPMENT_MIN = 0;
    public static final int TICKET_EQUIPMENT_MAX = 1000;
    public static final String TICKET_JOB_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";
    public static final String TICKET_OTHER_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,255}$";
    public static final String TICKET_SYSTEM_TICKET_ID_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,70}$";
    public static final String TICKET_SYSTEM_WAYBILL_REGEXP = "^[a-zA-ZА-Яа-я0-9,.:;!?\\s-\"]{0,70}$";

    private ValidationRegexp() {
    }
}
