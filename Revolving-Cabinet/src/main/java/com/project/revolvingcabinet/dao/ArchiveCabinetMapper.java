package com.project.revolvingcabinet.dao;

import com.project.revolvingcabinet.entity.ArchiveCabinet;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArchiveCabinetMapper {

    /**
     * 通过档案柜编号查询档案柜id
     * @param cabinetCode 档案柜编号
     * @return 档案柜id
     */
    Integer selectCabinetIdByCabinetCode(String cabinetCode);

    /**
     * 查找档案柜信息，一个下位机就只对应一个档案柜，因此直接selct * 就行
     * @return
     */
    ArchiveCabinet selectCabinetByAll();
}
