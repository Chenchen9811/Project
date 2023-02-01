package com.project.revolvingcabinet;

import com.project.revolvingcabinet.config.SchedulerTaskConfig;
import com.project.revolvingcabinet.dao.*;
import com.project.revolvingcabinet.entity.*;
import com.project.revolvingcabinet.modbus.ModbusUtils;

import com.project.revolvingcabinet.service.ArchiveCabinetService;
import com.project.revolvingcabinet.utils.CommonUtil;
import com.project.revolvingcabinet.utils.PasswordUtil;
import com.project.revolvingcabinet.utils.RevolvingCabinetConstants;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;


@SpringBootTest
@RunWith(SpringRunner.class)
public class RevolvingCabinetApplicationTests implements RevolvingCabinetConstants {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Resource
    private LoginInfoMapper loginInfoMapper;

    @Resource
    private DevPosMapper devPosMapper;

    @Resource
    private ArchiveBoxMapper archiveBoxMapper;

    @Resource
    private SchedulerTaskConfig schedulerTaskConfig;

    @Resource
    private CabinetDataMonitorMapper cabinetDataMonitorMapper;

    @Resource
    private InventoryLayerMapper inventoryLayerMapper;

    @Resource
    private ArchiveCabinetService archiveCabinetService;

    @Resource
    private InventoryMapper inventoryMapper;

    @Test
    public void contextLoads() throws ModbusTransportException, ModbusInitException, ErrorResponseException {
        ModbusMaster master = ModbusUtils.getSerialPortRtuMaster("COM1", 115200, 8, 1, 0);
//        short[] shorts = ModbusUtils.readMultiInputRegistersValue04(master, 1, 0, 5);
//        for (short num : shorts) {
//            System.out.println(num);
//        }
//        ModbusUtils.writeOutputState05(master, 1, 0, true);
//        ModbusUtils.writeSingleRegister06(master, 1, 1, 2);
    }

    @Test
    public void testSysUser() {
        SysUser user = sysUserMapper.selectSysUserByRealName("档案管理员");
        System.out.println(user);
        SysUser qxkj = sysUserMapper.selectSysUserByAccount("qxkj");
        System.out.println(qxkj);
    }

    @Test
    public void testLoginInfoMapper() {
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginTime(new Timestamp(System.currentTimeMillis()));
        loginInfo.setUserId(1111L);
        loginInfo.setLoginState("0");
        loginInfo.setVersion(0);
        System.out.println(loginInfoMapper.insertLoginInfo(loginInfo));
    }

    @Test
    public void testPasswordUtil() throws Exception {
//        String decrypt = PasswordUtil.decrypt("hE91mW0MvQh1ffs46bnMFA==");
//        System.out.println(decrypt);
        String encrypt = PasswordUtil.encrypt("123456");
        System.out.println(encrypt);
    }

    @Test
    public void test() {
//        String s = sysUserMapper.selectRoleNameByRoleId(1436961386461757442L);
//        System.out.println(s);
        System.out.println((System.currentTimeMillis() + 8*3600*1000) * 10000 + 621355968000000000L);
    }

    @Test
    public void testDevPosMapper() {
//        DevPos devPos = new DevPos();
//        devPos.setId(CommonUtil.generateId());
//        devPos.setLayerNo(1);
//        devPos.setColumnNo(1);
//        devPos.setBoxNo1("Abc");
//        devPos.setBoxStatus(1);
//        devPos.setBoxNo1StatusFlag(1);
//        devPos.setStatusFlag(1);
//        devPos.setBoxRfid1("Abcd");
//        devPos.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        devPos.setCreateUser(4123L);
//        devPos.setCabinetCode(CABINET_CODE);
//        devPos.setCabinetId("111111L");
//        devPosMapper.insertDevPos(devPos);
        DevPos devPos = new DevPos();
        devPos.setId(CommonUtil.generateId());
        devPos.setLayerNo(2);
        devPos.setColumnNo(2);
        devPos.setStatusFlag(DEV_POS_STATUS_NOT_SCANNED); // 未扫描
        devPos.setCreateTime(new Timestamp(System.currentTimeMillis()));
        devPos.setCreateUser(13424L);
        devPos.setCabinetCode(CABINET_CODE);
        devPos.setCabinetId("111111L");
        devPosMapper.insertDevPos(devPos);
    }

    @Test
    public void testArchiveBoxMapper() {
        //int i = archiveBoxMapper.updateArchiveBoxAfterInventory(ARCHIVE_BOX_STATUS_IN_CABINET, 637967629332207784L, 1, 1, 1 + "-" + 1);
        //System.out.println(i);
        List<ArchiveBox> archiveBoxList = archiveBoxMapper.selectArchiveBoxByKeyWord("1-2");
        System.out.println(archiveBoxList.get(0));
    }

    @Test
    public void testIntToBigDecimal() {
        System.out.println(schedulerTaskConfig.intToBigDecimal(3450));
    }

    @Test
    public void testCabinetDataMonitor() {
        CabinetDataMonitor cabinetDataMonitor = new CabinetDataMonitor();
        cabinetDataMonitor.setId(CommonUtil.generateId());
        cabinetDataMonitor.setTemperature(new BigDecimal("34.50"));
        cabinetDataMonitor.setHumidity(new BigDecimal("52.20"));
        cabinetDataMonitor.setPlcSignal(1);
        cabinetDataMonitor.setRfidSignal(1);
        cabinetDataMonitor.setLightCurtainStatus(1);
        cabinetDataMonitor.setCreateTime(new Timestamp(System.currentTimeMillis()));
        cabinetDataMonitorMapper.insertMonitorData(cabinetDataMonitor);
    }


    @Test
    public void testInventoryLayerMapper() {
        InventoryLayer inventoryLayer = new InventoryLayer();
        inventoryLayer.setId(638106079519760000L);
//        inventoryLayer.setInventoryId(11111232312321L);
//        inventoryLayer.setStartLayerNo(2);
//        inventoryLayer.setCreateUser(4211312L);
//        inventoryLayer.setCreateTime(new Timestamp(System.currentTimeMillis()));
        inventoryLayer.setVacancyNum(2);
        inventoryLayer.setEndLayerNo(5);
        inventoryLayer.setStatusFlag(3);
        inventoryLayer.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        inventoryLayer.setUpdateUser(4211312L);
        System.out.println(inventoryLayerMapper.updateInventoryLayerInfo(inventoryLayer));
    }

    @Test
    public void testInventoryMapper() {
        ArchiveCabinet archiveCabinet = archiveCabinetService.getArchiveCabinet();
        Inventory inventory = new Inventory();
        inventory.setInventoryId(638107865863270000L);
        inventory.setStorageNoStart(10);
        inventory.setInventoryType(1);
        inventory.setInventoryMethods(1);
        inventory.setStatusFlag(1);
        Timestamp startTime = new Timestamp(System.currentTimeMillis());
        inventory.setStartTime(startTime);
        inventory.setCreateTime(startTime);
        inventory.setCreateUser(2222L);
        inventory.setCabinetId(String.valueOf(archiveCabinet.getCabinetId()));
        inventory.setCabinetCode(archiveCabinet.getCabinetCode());

        inventory.setStorageNoEnd(20);
        inventory.setStatusFlag(2);
        Timestamp endTime = new Timestamp(System.currentTimeMillis());
        inventory.setEndTime(endTime);
        inventory.setUpdateTime(endTime);
        inventory.setUpdateUser(222L);
        System.out.println(inventoryMapper.updateInventoryInfo(inventory));
    }

    @Test
    public void testGenerateId() {
        System.out.println(CommonUtil.generateId());
    }

}
