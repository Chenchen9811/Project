package com.project.revolvingcabinet.service.impl;

import com.project.revolvingcabinet.dao.ArchiveCabinetMapper;
import com.project.revolvingcabinet.service.ArchiveCabinetService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ArchiveCabinetServiceImpl implements ArchiveCabinetService {

    @Resource
    private ArchiveCabinetMapper archiveCabinetMapper;


    /**
     * 通过档案柜编号查询档案柜id
     * @param cabinetCode 档案柜编号
     * @return 档案柜id
     */
    @Override
    public String findCabinetIdByCabinetCode(String cabinetCode) {
        return String.valueOf(archiveCabinetMapper.selectCabinetIdByCabinetCode(cabinetCode));
    }
}
