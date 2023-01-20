package com.project.revolvingcabinet.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.revolvingcabinet.common.Constants;
import com.project.revolvingcabinet.common.Messages;

/**
 * 读取配置文件，取其中的值
 * */
public class PropertyUtil {
    /**logger*/
    private static Logger logger = LoggerFactory.getLogger(PropertyUtil.class.getName());

    /** 属性文件对象 */
    private static Properties props;
    /** 加载文件 */
    static{
        loadProps();
    }

    synchronized static private void loadProps(){
        props = new Properties();
        InputStream inputStream=null;
        try {
            // 通过当前类加载器的getResourceAsStream方法获取输入流对象
            inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(Constants.PROP_FILE_MESSAGE_PATH);
            BufferedReader bf = new BufferedReader(new  InputStreamReader(inputStream));
            props.load(bf);
        } catch (FileNotFoundException e) {
            logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_003));
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != inputStream) {
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.error(Messages.getErrorMsg(Messages.MSG_E_LOG_004));
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据传入的key获得属性值
     * @param key 关键值
     * */
    public static String getProperty(String key){
        if(null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

}
