package com.project.revolvingcabinet.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArchiveCabinetMapper {

    /**
     * 通过档案柜编号查询档案柜id
     * @param cabinetCode 档案柜编号
     * @return 档案柜id
     */
    Integer selectCabinetIdByCabinetCode(String cabinetCode);
}
