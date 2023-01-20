package com.project.revolvingcabinet.common;

public class Constants {
    /**CookieUtil共通，默认缓存时间，2小时*/
    public static final int COOKIE_EXPIRE_SHORT_TIME = 60 * 60 * 2;
    /**CookieUtil共通，默认缓存时间，12小时*/
    public static final int COOKIE_EXPIRE_LONG_TIME = 60 * 60 * 12;
    /**CookieUtil共通，默认缓存时间，24小时(一天)*/
    public static final int COOKIE_EXPIRE_SHORT_DAY = 60 * 60 * 24;
    /**CookieUtil共通，默认缓存时间，3天*/
    public static final int COOKIE_EXPIRE_MIDDLE_DAY = 60 * 60 * 24 * 3;
    /**CookieUtil共通，默认缓存时间，7天*/
    public static final int COOKIE_EXPIRE_LONG_DAY = 60 * 60 * 24 * 7;
    /**PropertyUtil共通，读取配置信息中的message*/
    public static final String PROP_FILE_MESSAGE_PATH = "messages.properties";
    /**token*/
    public static final String TOKEN = "token";
    /**userID*/
    public static final String USER_ID = "userId";
    /**用户权限*/
    public static final String AUTH_FLAG = "authFlag";
    /**分割标志:,*/
    public static final String SPLIT_SIGN = ",";
    /**时间格式：yyyy/MM/dd HH:mm:ss*/
    public static final String YYYY_MM_DD_HH_MM_SS="yyyy/MM/dd HH:mm:ss";
    /**时间格式：yyyy/MM/dd HH:mm*/
    public static final String YYYY_MM_DD_HH_MM_A="yyyy/MM/dd HH:mm";
    /**时间格式：yyyy-MM-dd HH:mm*/
    public static final String YYYY_MM_DD_HH_MM="yyyy-MM-dd HH:mm";
    /**时间格式：yyyy-MM-dd HH*/
    public static final String YYYY_MM_DD_HH="yyyy-MM-dd HH";
    /**时间格式：yyyy-MM-dd HH:mm*/
    public static final String YYYY_MM_DD_HH_MMSS="yyyy-MM-dd HH:mm:ss";
    /**时间格式：yyyyMMddHHmm*/
    public static final String YYYYMMDDHHMM="yyyyMMddHHmm";
    /**时间格式：yyyyMMddHHmmss*/
    public static final String YYYYMMDDHHMMSS="yyyyMMddHHmmss";
    /**日期格式：yyyy-MM-dd*/
    public static final String YYYY_MM_DD="yyyy-MM-dd";
    /**日期格式：yyyy-MM*/
    public static final String YYYY_MM="yyyy-MM";
    /**日期格式：yyyyMMdd*/
    public static final String YYYYMMDD="yyyyMMdd";
    /**Excel文件格式.xls*/
    public static final String EXCEL_FILE_TYPE_XLS = ".xls";
    /**Excel文件格式.xlsx*/
    public static final String EXCEL_FILE_TYPE_XLSX = ".xlsx";
    /**设置初始密码*/
    public static final String DEFAULT_PASSWORD = "HD123456";
    /**分割标志:.*/
    public static final String SPLIT_SIGN_POINT = ".";
    /**连接标志:_*/
    public static final String CONCAT_SIGN_POINT = "_";
    /**连接标志:至*/
    public static final String CONCAT_SIGN_POINT_WAVE = "至";
    /**用户信息模板文件名*/
    public static final String EXCEL_MODEL_FILE_NAME = "model.xlsx";
    /**用户信息模板文件名*/
    public static final String EXCEL_MODEL_FILE_PATH = "/templates/";
    /**未知错误*/
    public static final String UNKNOW_ERROR = "未知错误";
    /**非法字符*/
    public static final String ILLEGAL_CHARACTERS = "非法字符";
    /**图表x轴数组关键字*/
    public static final String CHART_XDATA_KEY = "xData";
    /**图表y轴数组关键字*/
    public static final String CHART_YDATA_KEY = "YData";
    /**图表渲染数组关键字*/
    public static final String CHART_SERIES_KEY = "series";
    /**设置x轴数据显示间隔*/
    public static final String SHOW_XDATA_INTERVAL = "interval";
    /**刻度数*/
    public static final float SPLIT_NUM = 10;
    /**平均值*/
    public static final String AVG = "avg";
    /**采集周期*/
    public static final String COLLECT_PERIOD = "COLLECT_PERIOD";
    /**错误*/
    public static final String ERROR = "error";
    /**提示信息*/
    public static final String MSG = "msg";
    /**节点 ID前缀字符串名 */
    public static final String PRE_NODE_STR = "Lnode";
    /**DTU ID前缀字符串名 */
    public static final String PRE_DTU_STR = "dtu";
    /**定位器 ID前缀字符串名 */
    public static final String PRE_LOC_STR = "loc";
    /**传感器 ID前缀字符串名 */
    public static final String PRE_SENSOR_STR = "sensor";
    /**微处理器 ID前缀字符串名 */
    public static final String PRE_PRO_STR = "processor";
    /**各ID后缀字符串初始值 */
    public static final String INIT_NUMBER_STR = "001";
    /**生成随机字符串的库字符串 */
    public static final String RANDOM_FROM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    /**userID的长度 */
    public static final int USER_ID_LENGTH = 10;
    /**温度：最低报警值 */
    public static final String TEMP_MIN = "TEMP_MIN";
    /**温度：最高报警值 */
    public static final String TEMP_MAX = "TEMP_MAX";
    /**湿度：最低报警值 */
    public static final String HUM_MIN = "HUM_MIN";
    /**湿度：最高报警值 */
    public static final String HUM_MAX = "HUM_MAX";
    /**光照：最低报警值 */
    public static final String ILL_MIN = "ILL_MIN";
    /**光照：最高报警值 */
    public static final String ILL_MAX = "ILL_MAX";
    /**噪声：最低报警值 */
    public static final String NOISE_MIN = "NOISE_MIN";
    /**噪声：最高报警值 */
    public static final String NOISE_MAX = "NOISE_MAX";
    /**盐度：最低报警值 */
    public static final String SAL_MIN = "SAL_MIN";
    /**盐度：最高报警值 */
    public static final String SAL_MAX = "SAL_MAX";
    /**唯一海洋节点*/
    public static final String NODE_ID_FOR_SEA = "Lnode001";
}
