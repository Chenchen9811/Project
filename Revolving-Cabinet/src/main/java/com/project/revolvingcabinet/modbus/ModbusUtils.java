package com.project.revolvingcabinet.modbus;


import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.msg.*;
import gnu.io.CommPortIdentifier;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ModbusUtils {
    private static Logger log = LoggerFactory.getLogger(ModbusUtils.class);
    /**
     * 工厂。
     */
    static ModbusFactory modbusFactory;

    private CommPortIdentifier portId;

    private static Enumeration<CommPortIdentifier> portList;

    String portName;

    static {
        if (modbusFactory == null) {
            modbusFactory = new ModbusFactory();
        }
    }

    /**
     * 获取master
     *
     * @return
     * @throws ModbusInitException
     */
    public static ModbusMaster getMaster(String host, int port) throws ModbusInitException {
        IpParameters params = new IpParameters();
        params.setHost(host);
        params.setPort(port);
        //
        // modbusFactory.createRtuMaster(wapper); //RTU 协议
        // modbusFactory.createUdpMaster(params);//UDP 协议
        // modbusFactory.createAsciiMaster(wrapper);//ASCII 协议
        ModbusMaster master = modbusFactory.createTcpMaster(params, false);// TCP 协议
        master.init();

        return master;
    }

    public static ModbusMaster getRtuIpMaster(String host, int port) throws ModbusInitException {
        IpParameters params = new IpParameters();
        params.setHost(host);
        params.setPort(port);
        params.setEncapsulated(true);
        ModbusMaster master = modbusFactory.createTcpMaster(params, false);
        try {
            //设置超时时间
            master.setTimeout(1000);
            //设置重连次数
            master.setRetries(3);
            //初始化
            master.init();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return master;
    }

    /**
     *
     * @param portName 串口名
     * @param baudRate 波特率
     * @param dataBits 数据位
     * @param stopBits 中止位
     * @param parity   校验位
     * @return
     * @throws ModbusInitException
     */
    public static ModbusMaster getSerialPortRtuMaster(String portName, Integer baudRate, Integer dataBits,
                                                      Integer stopBits, Integer parity){
        // 设置串口参数，串口是COM1，波特率是9600
        // SerialPortWrapperImpl wrapper = new SerialPortWrapperImpl("COM2", 9600,SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE, 0, 0);
        SerialPortWrapperImpl wrapper = new SerialPortWrapperImpl(portName, baudRate,
                dataBits, stopBits, parity, 0, 0);
        ModbusMaster master = modbusFactory.createRtuMaster(wrapper);
        try {
            //设置超时时间
            master.setTimeout(1000);
            //设置重连次数
            master.setRetries(3);
            //初始化
            master.init();
        } catch (ModbusInitException e) {
            log.error("串口连接异常~");
            e.printStackTrace();
        }
        return master;
    }
    /**
     *
     * @param portName 串口名
     * @param baudRate 波特率
     * @param dataBits 数据位
     * @param stopBits 中止位
     * @param parity   校验位
     * @return
     * @throws ModbusInitException
     */
    public static ModbusMaster getSerialPortAsciiMaster(String portName, Integer baudRate, Integer dataBits,
                                                        Integer stopBits, Integer parity){
        // 设置串口参数，串口是COM1，波特率是9600
        // SerialPortWrapperImpl wrapper = new SerialPortWrapperImpl("COM2", 9600,SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE, 0, 0);
        SerialPortWrapperImpl wrapper = new SerialPortWrapperImpl(portName, baudRate,
                dataBits, stopBits, parity, 0, 0);
        ModbusMaster master = modbusFactory.createAsciiMaster(wrapper);
        try {
            //设置超时时间
            master.setTimeout(1000);
            //设置重连次数
            master.setRetries(3);
            //初始化
            master.init();
        } catch (ModbusInitException e) {
            log.error("串口连接异常~");
            e.printStackTrace();
        }
        return master;
    }


    /**
     * 读取[01 Coil Status 0x]类型 开关数据
     *
     * @param slaveId slaveId
     * @param offset  位置
     * @return 读取值
     * @throws ModbusTransportException 异常
     * @throws ErrorResponseException   异常
     * @throws ModbusInitException      异常
     */
    public static Boolean readCoilStatus(ModbusMaster master, int slaveId, int offset)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        // 01 Coil Status
        BaseLocator<Boolean> loc = BaseLocator.coilStatus(slaveId, offset);
        Boolean value = master.getValue(loc);
        return value;
    }



    /**
     * 读取[03 Holding Register类型 2x]模拟量数据
     *
     * @param slaveId  slave Id
     * @param offset   位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return
     * @throws ModbusTransportException 异常
     * @throws ErrorResponseException   异常
     * @throws ModbusInitException      异常
     */
    public static Number readHoldingRegister(ModbusMaster master, int slaveId, int offset, int dataType)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        // 03 Holding Register类型数据读取
        BaseLocator<Number> loc = BaseLocator.holdingRegister(slaveId, offset, dataType);
        Number value = master.getValue(loc);
        return value;
    }

    /**
     * 读取[04 Input Registers 3x]类型 模拟量数据
     *
     * @param slaveId  slaveId
     * @param offset   位置
     * @param dataType 数据类型,来自com.serotonin.modbus4j.code.DataType
     * @return 返回结果
     * @throws ModbusTransportException 异常
     * @throws ErrorResponseException   异常
     * @throws ModbusInitException      异常
     */
    public static Number readInputRegisters(ModbusMaster master, int slaveId, int offset, int dataType)
            throws ModbusTransportException, ErrorResponseException, ModbusInitException {
        // 04 Input Registers类型数据读取
        BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);
        Number value = master.getValue(loc);
        return value;
    }


    /**
     * 06功能码，写单个寄存器（字）
     * @param master
     * @param slaveId 从站地址
     * @param offset 写入位置
     * @param bit 写入的值
     * @throws ModbusTransportException
     */
    public static void writeSingleRegister06(ModbusMaster master, int slaveId, int offset, int bit)
            throws ModbusTransportException {
        WriteRegisterRequest request = new WriteRegisterRequest(slaveId, offset, bit);
        log.info("slaveId:{}", request.getSlaveId());
        WriteRegisterResponse response = (WriteRegisterResponse) master.send(request);
        if (response != null && response.isException()) {
            throw new ModbusTransportException(response.getExceptionMessage());
        }
    }

    /**
     * 05 功能码，写输出状态(布尔值)
     * @param master
     * @param slaveId 从站地址
     * @param writeOffset 写入地址
     * @param writeValue 写入值
     * @throws ModbusTransportException
     */
    public static void writeOutputState05(ModbusMaster master, int slaveId, int writeOffset, boolean writeValue) throws ModbusTransportException {
        WriteCoilRequest request = new WriteCoilRequest(slaveId, writeOffset, writeValue);
        WriteCoilResponse response = (WriteCoilResponse) master.send(request);
        if (response != null && response.isException()) {
            throw new ModbusTransportException(response.getExceptionMessage());
        }
    }


    /**
     * 01功能码，读单个输出状态（布尔量），地址范围  00001~00027
     * @param master
     * @param slaveId 从站地址
     * @param startOffset 起始地址
     * @return
     * @throws ModbusTransportException
     */
    public static Boolean readOutputState01(ModbusMaster master, int slaveId, int startOffset) throws ModbusTransportException {
        ReadCoilsRequest request = new ReadCoilsRequest(slaveId, startOffset, 1);
        ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);
        if (response!= null && response.isException()) {
            throw new ModbusTransportException(response.getExceptionMessage());
        } else if(response!= null){
            return response.getBooleanData()[0];
        } else return null;
    }


    /**
     * 02功能码，读输入状态（布尔量）
     * @param master
     * @param slaveId 从站地址
     * @param startOffset 起始地址
     * @return
     * @throws ModbusTransportException
     */
    public static Boolean readInputState02(ModbusMaster master, int slaveId, int startOffset) throws ModbusTransportException {
        ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, startOffset, 1);
        ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);
        if (response!= null && response.isException()) {
            throw new ModbusTransportException(response.getExceptionMessage());
        } else if(response!= null){
            return response.getBooleanData()[0];
        } else return null;
    }

    /**
     * 03功能码，读单个保持寄存器
     * @param master
     * @param slaveId 从站地址
     * @param startOffset 起始地址
     * @return
     * @throws ModbusTransportException
     */
    public static Short readSingleHoldingRegisterValue03(ModbusMaster master, int slaveId, int startOffset) throws ModbusTransportException {
        ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, startOffset, 1);
        ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse)master.send(request);
        if (response != null && response.isException()) {
            return null;
        } else if (response != null && response.getShortData().length > 0) {
            return response.getShortData()[0];
        }
        return null;
    }


    /**
     * 03功能码，读多个保持寄存器
     * @param master
     * @param slaveId 从站地址
     * @param startOffset 读取的起始地址
     * @param numberOfRegisters 读取的寄存器数量
     * @return
     * @throws ModbusTransportException
     */
    public static short[] readMultiHoldingRegistersValue03(ModbusMaster master, int slaveId, int startOffset, int numberOfRegisters) throws ModbusTransportException {
        ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, startOffset, numberOfRegisters);
        ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse)master.send(request);
        if (response != null && response.isException()) {
            return null;
        } else if (response != null) {
            return response.getShortData();
        }
        return null;
    }

    /**
     * 04功能码，读单个输入寄存器
     * @param master
     * @param slaveId 从站地址
     * @param startOffset 读取的起始地址
     * @return
     * @throws ModbusTransportException
     */
    public static Short readSingleInputRegisterValue04(ModbusMaster master, int slaveId, int startOffset) throws ModbusTransportException {
        ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId,startOffset,1);
        ReadInputRegistersResponse response = (ReadInputRegistersResponse) master.send(request);
        if (response!= null && response.isException()) {
            throw new ModbusTransportException(response.getExceptionMessage());
        } else if(response!= null){
            return response.getShortData()[0];
        }
        return null;
    }

    /**
     * 04功能码，读多个输入寄存器
     * @param master
     * @param slaveId 从站地址
     * @param startOffset 起始地址
     * @param numberOfRegisters 读取的寄存器数量
     * @return
     * @throws ModbusTransportException
     */
    public static short[] readMultiInputRegistersValue04(ModbusMaster master, int slaveId, int startOffset, int numberOfRegisters) throws ModbusTransportException {
        ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, startOffset, numberOfRegisters);
        ReadInputRegistersResponse response = (ReadInputRegistersResponse) master.send(request);
        if (response!= null && response.isException()) {
            throw new ModbusTransportException(response.getExceptionMessage());
        } else if(response!= null){
            return response.getShortData();
        }
        return null;
    }

    /**
     * 获取可用端口的端口号
     * @return
     */
    public static String getAvailablePortName() {
        List<String> systemPorts = new ArrayList<>();
        portList = CommPortIdentifier.getPortIdentifiers();
        while (portList.hasMoreElements()) {
            systemPorts.add(portList.nextElement().getName());
        }
        return systemPorts.get(0);
    }


}

