package com.project.revolvingcabinet;

import com.project.revolvingcabinet.dao.ArchiveBoxMapper;
import com.project.revolvingcabinet.dao.DevPosMapper;
import com.project.revolvingcabinet.dao.LoginInfoMapper;
import com.project.revolvingcabinet.dao.SysUserMapper;
import com.project.revolvingcabinet.entity.DevPos;
import com.project.revolvingcabinet.entity.LoginInfo;
import com.project.revolvingcabinet.entity.SysUser;
import com.project.revolvingcabinet.modbus.ModbusUtils;

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
import java.sql.Timestamp;


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
    }

}
