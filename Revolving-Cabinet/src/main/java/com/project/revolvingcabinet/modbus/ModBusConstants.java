package com.project.revolvingcabinet.modbus;

public interface ModBusConstants {

    /**
     * 从站地址
     */
    int SLAVE_ID = 1;

    /**
     * 当前层地址偏移量
     */
    int ADDRESS_OFFSET_CUR_LAYER = 1;

    /**
     * 最大RFID数量地址偏移量
     */
    int ADDRESS_OFFSET_MAX_RFID= 4;

    /**
     * 总层数地址偏移量
     */
    int ADDRESS_OFFSET_TOTAL_LAYER = 0;

    /**
     * 盘点开始地址偏移量
     */
    int ADDRESS_OFFSET_START_INVENTORY = 20;

    /**
     * 盘点开始信号地址偏移量
     */
    int ADDRESS_OFFSET_INVENTORY_START_SIGNAL = 2;

    /**
     * 读取RFID卡号起始地址偏移量，4个字节
     */
    int ADDRESS_OFFSET_READ_RFID_START = 8;

    /**
     * 目标层修改和读取地址偏移量
     */
    int ADDRESS_OFFSET_TARGET_LAYER = 2;

    /**
     * 移层地址偏移量
     */
    int ADDRESS_OFFSET_MOVE_LAYER = 14;
}
