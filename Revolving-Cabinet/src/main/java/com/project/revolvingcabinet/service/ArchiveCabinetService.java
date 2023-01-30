package com.project.revolvingcabinet.service;

import com.project.revolvingcabinet.entity.ArchiveCabinet;

import java.math.BigDecimal;

public interface ArchiveCabinetService {


    /**
     * 通过档案柜编号查询档案柜id
     * @param cabinetCode 档案柜编号
     * @return 档案柜id
     */
    String findCabinetIdByCabinetCode(String cabinetCode);

    /**
     * 查找档案柜信息，一个下位机就只对应一个档案柜
     * @return
     */
    ArchiveCabinet getArchiveCabinet();

    /**
     * 获取档案柜的温度
     * @return
     */
    BigDecimal getArchiveCabinetTemperature();
}
