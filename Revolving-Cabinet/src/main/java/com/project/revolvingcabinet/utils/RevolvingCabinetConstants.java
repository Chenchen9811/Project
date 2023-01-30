package com.project.revolvingcabinet.utils;

public interface RevolvingCabinetConstants {
    /**
     * 档案盒状态：在库
     */
    int ARCHIVE_BOX_STATUS_IN_CABINET = 4;

    /**
     * 档案盒状态：标签损坏
     */
    int ARCHIVE_BOX_STATUS_RFID_BROKEN = 10;

    /**
     * 档案盒状态：已借出
     */
    int ARCHIVE_BOX_STATUS_BORROWED = 12;

    /**
     * 档案盒状态：已移交
     */
    int ARCHIVE_BOX_STATUS_REMOVED = 16;

    /**
     * 盘库后的储位状态：该储位的天线是坏的
     */
    String INVENTORY_POS_STATUS_ANTENNA_BROKEN = "该储位的天线是坏的!";

    /**
     * 盘库后的储位状态：该储位是空的
     */
    String INVENTORY_POS_STATUS_IS_EMPTY = "该储位是空的!";

    /**
     * 盘库后的储位状态：该储位有档案盒没有标签或者档案盒的标签是坏的
     */
    String INVENTORY_POS_STATUS_EXCEPTION = "档案盒没有标签或者标签是坏的!";

    /**
     * 盘库后的储位状态；该储位有档案盒并且标签是正常的
     */
    String INVENTORY_POS_STATUS_NORMAL = "该储位有档案盒并且标签是正常的!";

    /**
     * 储位状态1：正常
     */
    int DEV_POS_STATUS_NORMAL = 1;

    /**
     * 储位状态2：未扫描
     */
    int DEV_POS_STATUS_NOT_SCANNED = 2;

    /**
     * 储位状态3：异常
     */
    int DEV_POS_STATUS_EXCEPTION = 3;

    /**
     * 储位状态4：锁定
     */
    int DEV_POS_STATUS_LOCKED = 4;

    /**
     * 储位状态5：保留
     */
    int DEV_POS_STATUS_RETAINED = 5;

    /**
     * 储位盘点状态1：待盘点
     */
    int INVENTORY_STATUS_POS_WAIT_CHECK = 1;

    /**
     * 储位盘点状态2：盘点中
     */
    int INVENTORY_STATUS_POS_CHECKING = 2;

    /**
     * 储位盘点状态3：盘点成功
     */
    int INVENTORY_STATUS_POS_SUCCESS = 3;

    /**
     * 储位盘点状态4：盘点异常
     */
    int INVENTORY_STATUS_POS_EXCEPTION = 4;

    /**
     * 层盘库状态1：待盘点
     */
    int INVENTORY_STATUS_LAYER_NOT_INVENTORY = 1;

    /**
     * 层盘库状态2： 盘点中
     */
    int INVENTORY_STATUS_LAYER_CHECKING = 2;

    /**
     * 层盘库状态3： 已盘点
     */
    int INVENTORY_STATUS_LAYER_FINISHED = 3;


    /**
     * 档案柜编号
     */
    String CABINET_CODE = "abc123456cabinet";
}
