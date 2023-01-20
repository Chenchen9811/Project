package com.project.revolvingcabinet.service;

import com.project.revolvingcabinet.entity.ArchiveCabinet;

public interface ArchiveCabinetService {


    /**
     * 通过档案柜编号查询档案柜id
     * @param cabinetCode 档案柜编号
     * @return 档案柜id
     */
    String findCabinetIdByCabinetCode(String cabinetCode);
}
