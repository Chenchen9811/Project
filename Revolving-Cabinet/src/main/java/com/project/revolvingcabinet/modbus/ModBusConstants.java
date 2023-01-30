package com.project.revolvingcabinet.modbus;

public interface ModBusConstants {


    /**
     * 波特率
     */
    int BAUD_RATE = 115200;

    /**
     * 数据位
     */
    int DATAd_BITS = 8;

    /**
     * 停止位
     */
    int STOP_BIT = 1;

    /**
     * 奇偶校验位
     */
    int PARITY = 0;

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

    /**
     * 温度读入地址偏移量
     */
    int ADDRESS_OFFSET_TEMPERATURE = 6;


    /**
     * 湿度读入地址偏移量
     */
    int ADDRESS_OFFSET_HUMIDITY = 7;

    /**
     * PLC通讯信号地址偏移量
     */
    int ADDRESS_OFFSET_PLC_SIGNAL = 1;

    /**
     * RFID通讯信号地址偏移量
     */
    int ADDRESS_OFFSET_RFID_SIGNAL = 0;

    /**
     * 光幕通讯信号地址偏移量
     */
    int ADDRESS_OFFSET_LIGHT_CURTAIN_SIGNAL = 10;

    /**
     * 点动开门地址偏移量
     */
    int ADDRESS_OFFSET_OPEN_DOOR = 2;

    /**
     * 开门到位信号地址偏移量
     */
    int ADDRESS_OFFSET_DOOR_OPENED = 11;

    /**
     * 点动关门地址偏移量
     */
    int ADDRESS_OFFSET_CLOSE_DOOR = 3;

    /**
     * 关门到位信号地址偏移量
     */
    int ADDRESS_OFFSET_DOOR_CLOSED = 12;


    /**
     * 停止命令信号地址偏移量
     */
    int ADDRESS_OFFSET_STOP = 15;





}
