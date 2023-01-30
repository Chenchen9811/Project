package com.project.revolvingcabinet.common;


import com.project.revolvingcabinet.utils.PropertyUtil;

/**
 * message存放所有的提示信息、错误信息等
 * */
public class Messages {
    /************************ 出现异常时的提示信息 *********************/
    /**MSG-E-LOG-001：参数验证失败。*/
    public static final String MSG_E_LOG_001 = "MSG-E-LOG-001";
    /**MSG-E-LOG-002：数据查找失败。*/
    public static final String MSG_E_LOG_002 = "MSG-E-LOG-002";
    /**MSG-E-LOG-003：找不到该文件。*/
    public static final String MSG_E_LOG_003 = "MSG-E-LOG-003";
    /**MSG-E-LOG-004：文件流关闭异常。*/
    public static final String MSG_E_LOG_004 = "MSG-E-LOG-004";
    /**MSG-E-LOG-005：token生成失败。*/
    public static final String MSG_E_LOG_005 = "MSG-E-LOG-005";
    /**MSG-E-LOG-006：数据插入失败。*/
    public static final String MSG_E_LOG_006 = "MSG-E-LOG-006";
    /**MSG-E-LOG-007：数据更新失败。*/
    public static final String MSG_E_LOG_007 = "MSG-E-LOG-007";
    /**MSG-E-LOG-008：数据库操作失败。*/
    public static final String MSG_E_LOG_008 = "MSG-E-LOG-008";
    /**MSG-E-LOG-009：字符串匹配失败。*/
    public static final String MSG_E_LOG_009 = "MSG-E-LOG-009";
    /**MSG-E-LOG-010：用户信息获取失败。*/
    public static final String MSG_E_LOG_010 = "MSG-E-LOG-010";
    /**MSG-E-LOG-011：文件信息不能为空。*/
    public static final String MSG_E_LOG_011 = "MSG-E-LOG-011";
    /**MSG-E-LOG-012：管理员信息不能为空。*/
    public static final String MSG_E_LOG_012 = "MSG-E-LOG-012";
    /**MSG-E-LOG-013：必须是xls/xlsx格式的文件。*/
    public static final String MSG_E_LOG_013 = "MSG-E-LOG-013";
    /**MSG-E-LOG-014：文件流获取异常。*/
    public static final String MSG_E_LOG_014 = "MSG-E-LOG-014";
    /**MSG-E-LOG-015：excel文件解析异常。*/
    public static final String MSG_E_LOG_015 = "MSG-E-LOG-015";
    /**MSG-E-LOG-016：用户删除失败。*/
    public static final String MSG_E_LOG_016 = "MSG-E-LOG-016";
    /**MSG-E-LOG-017：当前管理员ID获取失败。*/
    public static final String MSG_E_LOG_017 = "MSG-E-LOG-017";
    /**MSG-E-LOG-018：分页信息获取失败。*/
    public static final String MSG_E_LOG_018 = "MSG-E-LOG-018";
    /**MSG-E-LOG-019：节点信息获取失败。*/
    public static final String MSG_E_LOG_019 = "MSG-E-LOG-019";
    /**MSG-E-LOG-020：DTU信息获取失败。*/
    public static final String MSG_E_LOG_020 = "MSG-E-LOG-020";
    /**MSG-E-LOG-021：定位器信息获取失败。*/
    public static final String MSG_E_LOG_021 = "MSG-E-LOG-021";
    /**MSG-E-LOG-022：传感器信息获取失败。*/
    public static final String MSG_E_LOG_022 = "MSG-E-LOG-022";
    /**MSG-E-LOG-023：微处理器信息获取失败。*/
    public static final String MSG_E_LOG_023 = "MSG-E-LOG-023";
    /**MSG-E-LOG-024：当前没有已被注册的节点。*/
    public static final String MSG_E_LOG_024 = "MSG-E-LOG-024";
    /**MSG-E-LOG-025：采集周期不存在，请您先设置采集周期。*/
    public static final String MSG_E_LOG_025 = "MSG-E-LOG-025";
    /**MSG-E-LOG-026：最新数据未更新时间过长，请检查数据获取是否存在异常。*/
    public static final String MSG_E_LOG_026 = "MSG-E-LOG-026";
    /**MSG-E-LOG-027：当前节点还未被注册。*/
    public static final String MSG_E_LOG_027 = "MSG-E-LOG-027";
    /**MSG-E-LOG-028：当前节点暂未获得任何参数。*/
    public static final String MSG_E_LOG_028 = "MSG-E-LOG-028";
    /**MSG-E-LOG-029：请输入结束时间。*/
    public static final String MSG_E_LOG_029 = "MSG-E-LOG-029";
    /**MSG-E-LOG-030：请输入开始时间。*/
    public static final String MSG_E_LOG_030 = "MSG-E-LOG-030";
    /**MSG-E-LOG-031：开始时间不能大于结束时间。*/
    public static final String MSG_E_LOG_031 = "MSG-E-LOG-031";
    /**MSG-E-LOG-032：检索当前异常信息出错。*/
    public static final String MSG_E_LOG_032 = "MSG-E-LOG-032";
    /**MSG-E-LOG-033：开始日期和结束日期都不能为空。*/
    public static final String MSG_E_LOG_033 = "MSG-E-LOG-033";
    /**MSG-E-LOG-034：指定日期不能为空。*/
    public static final String MSG_E_LOG_034 = "MSG-E-LOG-034";
    /**MSG-E-LOG-035：用户名已经存在！*/
    public static final String MSG_E_LOG_035 = "MSG-E-LOG-035";
    /**MSG-E-LOG-036：输入的新用户名不能为空！*/
    public static final String MSG_E_LOG_036 = "MSG-E-LOG-036";
    /**MSG-E-LOG-037：用户名的长度不能超过20！*/
    public static final String MSG_E_LOG_037 = "MSG-E-LOG-037";
    /**MSG-E-LOG-038：新用户名不能与旧用户名一致，请重新输入！*/
    public static final String MSG_E_LOG_038 = "MSG-E-LOG-038";
    /**MSG-E-LOG-039：用户创建失败！*/
    public static final String MSG_E_LOG_039 = "MSG-E-LOG-039";
    /**MSG-E-LOG-040：必须指定一个节点！*/
    public static final String MSG_E_LOG_040 = "MSG-E-LOG-040";
    /**MSG-E-LOG-041：温度的最低警报值不得大于最高警报值！*/
    public static final String MSG_E_LOG_041 = "MSG-E-LOG-041";
    /**MSG-E-LOG-042：湿度的最低警报值不得大于最高警报值！*/
    public static final String MSG_E_LOG_042 = "MSG-E-LOG-042";
    /**MSG-E-LOG-043：光照的最低警报值不得大于最高警报值！*/
    public static final String MSG_E_LOG_043 = "MSG-E-LOG-043";
    /**MSG-E-LOG-044：噪声的最低警报值不得大于最高警报值！*/
    public static final String MSG_E_LOG_044 = "MSG-E-LOG-044";
    /**MSG-E-LOG-045：盐度的最低警报值不得大于最高警报值！*/
    public static final String MSG_E_LOG_045 = "MSG-E-LOG-045";
    /**MSG-E-LOG-046：暂无海洋节点最新数据！*/
    public static final String MSG_E_LOG_046 = "MSG-E-LOG-046";
    /**MSG-E-LOG-047：暂无最新数据！*/
    public static final String MSG_E_LOG_047 = "MSG-E-LOG-047";
    /**MSG-E-LOG-048：当前节点暂未获得最新数据！*/
    public static final String MSG_E_LOG_048 = "MSG-E-LOG-048";

    /************************ 正常运行时的提示信息 *********************/
    /**MSG-S-LOG-001：登录成功。*/
    public static final String MSG_S_LOG_001 = "MSG-S-LOG-001";
    /**MSG-S-LOG-002：退出成功。*/
    public static final String MSG_S_LOG_002 = "MSG-S-LOG-002";
    /**MSG-S-LOG-003：搜索成功。*/
    public static final String MSG_S_LOG_003 = "MSG-S-LOG-003";
    /**MSG-S-LOG-004：全部注册或更新成功。*/
    public static final String MSG_S_LOG_004 = "MSG-S-LOG-004";
    /**MSG-S-LOG-005：全部删除成功。*/
    public static final String MSG_S_LOG_005 = "MSG-S-LOG-005";
    /**MSG-S-LOG-006：更新成功。*/
    public static final String MSG_S_LOG_006 = "MSG-S-LOG-006";
    /**MSG-S-LOG-007：当天还没有任何监测数据，请耐心等待几分钟...。*/
    public static final String MSG_S_LOG_007 = "MSG-S-LOG-007";
    /**MSG-S-LOG-008：您未指定统计日期区间，请选择日期类型！*/
    public static final String MSG_S_LOG_008 = "MSG-S-LOG-008";
    /**MSG-S-LOG-009：您的信息更新成功！*/
    public static final String MSG_S_LOG_009 = "MSG-S-LOG-009";
    /**MSG-S-LOG-010：密码更新成功，请重新登录！*/
    public static final String MSG_S_LOG_010 = "MSG-S-LOG-010";
    /**MSG-S-LOG-011：用户创建成功！*/
    public static final String MSG_S_LOG_011 = "MSG-S-LOG-011";
    /**MSG-S-LOG-012：开门成功！*/
    public static final String MSG_S_LOG_012 = "MSG-S-LOG-012";
    /**MSG-S-LOG-013：关门成功！*/
    public static final String MSG_S_LOG_013 = "MSG-S-LOG-013";
    /**MSG-S-LOG-014：移层成功！*/
    public static final String MSG_S_LOG_014 = "MSG-S-LOG-014";
    /**MSG-S-LOG-014：发送停止指令成功！*/
    public static final String MSG_S_LOG_015 = "MSG-S-LOG-015";

    /************************ 各页面出现异常时的提示信息 *********************/
    /**logincontroller：表单无数据！*/
    public static final String MSG_E_LOGIN_001 = "MSG-E-LOGIN-001";
    /**logincontroller：页面用户是否存在*/
    public static final String MSG_E_LOGIN_002 = "MSG-E-LOGIN-002";
    /**logincontroller：页面密码对错检证*/
    public static final String MSG_E_LOGIN_003 = "MSG-E-LOGIN-003";

    /************************ 各参数验证出现异常时的提示信息 *********************/
    /**MSG-E-PA-001 : 用户iD不能为空*/
    public static final String MSG_E_PA_001 = "MSG-E-PA-001";
    /**MSG-E-PA-002 : 用户ID的长度必须为10位！*/
    public static final String MSG_E_PA_002 = "MSG-E-PA-002";
    /**MSG-E-PA-003 : 用户名的长度不能超过20*/
    public static final String MSG_E_PA_003 = "MSG-E-PA-003";
    /**MSG-E-PA-004 : 手机号码的格式不正确！*/
    public static final String MSG_E_PA_004 = "MSG-E-PA-004";
    /**MSG-E-PA-005 : 邮箱不能为空！*/
    public static final String MSG_E_PA_005 = "MSG-E-PA-005";
    /**MSG-E-PA-006 : 邮箱的格式不正确！*/
    public static final String MSG_E_PA_006 = "MSG-E-PA-006";
    /**MSG-E-PA-007 : 用户权限只能是管理员或者普通用户！*/
    public static final String MSG_E_PA_007 = "MSG-E-PA-007";
    /**MSG-E-PA-008 : 用户名不能为空！*/
    public static final String MSG_E_PA_008 = "MSG-E-PA-008";
    /**MSG-E-PA-009 : DTU_ID不能为空！*/
    public static final String MSG_E_PA_009 = "MSG-E-PA-009";
    /**MSG-E-PA-010 : DTU产品型号的长度不能超过50位！*/
    public static final String MSG_E_PA_010 = "MSG-E-PA-010";
    /**MSG-E-PA-011 : DTU生产厂家的长度不能超过50位！*/
    public static final String MSG_E_PA_011 = "MSG-E-PA-011";
    /**MSG-E-PA-012 : 连接方式不存在！*/
    public static final String MSG_E_PA_012 = "MSG-E-PA-012";
    /**MSG-E-PA-013 : 工作模式不存在！*/
    public static final String MSG_E_PA_013 = "MSG-E-PA-013";
    /**MSG-E-PA-014 : DTU信息不存在，请检查其对应的节点是否已经注册！*/
    public static final String MSG_E_PA_014 = "MSG-E-PA-014";
    /**MSG-E-PA-015 : 定位器ID不能为空！*/
    public static final String MSG_E_PA_015 = "MSG-E-PA-015";
    /**MSG-E-PA-016 : 定位器产品型号长度不能超过50位！*/
    public static final String MSG_E_PA_016 = "MSG-E-PA-016";
    /**MSG-E-PA-017 : 定位器生产厂家长度不能超过50位！*/
    public static final String MSG_E_PA_017 = "MSG-E-PA-017";
    /**MSG-E-PA-018 : 定位方式不存在！*/
    public static final String MSG_E_PA_018 = "MSG-E-PA-018";
    /**MSG-E-PA-019 : 定位器信息不存在，请检查其对应的节点是否已经注册！*/
    public static final String MSG_E_PA_019 = "MSG-E-PA-019";
    /**MSG-E-PA-020 : 传感器ID不能为空！*/
    public static final String MSG_E_PA_020 = "MSG-E-PA-020";
    /**MSG-E-PA-021 : 传感器产品型号的长度不能超过50！*/
    public static final String MSG_E_PA_021 = "MSG-E-PA-021";
    /**MSG-E-PA-022 : 传感器生产厂家的长度不能超过50位！*/
    public static final String MSG_E_PA_022 = "MSG-E-PA-022";
    /**MSG-E-PA-023 : 输出方式不存在！*/
    public static final String MSG_E_PA_023 = "MSG-E-PA-023";
    /**MSG-E-PA-024 : 传感器信息不存在，请检查其对应的节点是否已经注册！*/
    public static final String MSG_E_PA_024 = "MSG-E-PA-024";
    /**MSG-E-PA-025 : 微处理器ID不能为空！*/
    public static final String MSG_E_PA_025 = "MSG-E-PA-025";
    /**MSG-E-PA-026 : 微处理器产品型号的长度不能超过50位！*/
    public static final String MSG_E_PA_026 = "MSG-E-PA-026";
    /**MSG-E-PA-027 : 微处理器生产厂家的长度不能超过50位！*/
    public static final String MSG_E_PA_027 = "MSG-E-PA-027";
    /**MSG-E-PA-028 : 微处理器位数不正确！*/
    public static final String MSG_E_PA_028 = "MSG-E-PA-028";
    /**MSG-E-PA-029 : 微处理器信息不存在，请检查其对应的节点是否已经注册！*/
    public static final String MSG_E_PA_029 = "MSG-E-PA-029";
    /**MSG-E-PA-030 : 工作电压的长度不能超过10位！*/
    public static final String MSG_E_PA_030 = "MSG-E-PA-030";
    /**MSG-E-PA-031 : 用户姓名不能为空*/
    public static final String MSG_E_PA_031 = "MSG-E-PA-031";
    /**MSG-E-PA-032 : 用户姓名长度不能超过20位！*/
    public static final String MSG_E_PA_032 = "MSG-E-PA-032";
    /**MSG-E-PA-033 : 新密码和确认密码不一致，请重新输入！*/
    public static final String MSG_E_PA_033 = "MSG-E-PA-033";
    /**MSG-E-PA-034 : 新密码不能与旧密码相同，请重新输入！*/
    public static final String MSG_E_PA_034 = "MSG-E-PA-034";

    /************************ 阿里云物联网平台出现异常时的提示信息 *********************/
    /**MSG-E-ALI-001 : 阿里云物联网平台连接失败，请您确保平台开启，并保证网络连接正常！*/
    public static final String MSG_E_ALI_001= "MSG-E-ALI-001";

    /**
     * 根据设置的key从配置文件中获取对应的message内容
     * @param msgCode 信息对应的code
     * */
    public static String getErrorMsg(String msgCode) {
        return PropertyUtil.getProperty(msgCode);
    }


    public static String getSuccessMsg(String msgCode) {
        return PropertyUtil.getProperty(msgCode);
    }
}
