package com.project.revolvingcabinet.entity;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Date;

public class SysUser {

    private Long userId;

    private String realName;

    private String nickName;

    private Long roleId;

    private String account;

    private String password;

    private Long avatar;

    private Date birthday;

    private String sex;

    private String email;

    private String phone;

    private String tel;

    private String superAdminFlag;

    private Integer statusFlag;

    private String lastLoginIp;

    private String lastLoginTime;

    private String userType;

    private String cabinetId;

    private String cabinetCode;

    private Timestamp createTime;

    private Long createUser;

    private Timestamp updateTime;

    private Long updateUser;

    private String delFlag;

    private Timestamp delTime;

    private Long delUser;

    private String portrait;

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", realName='" + realName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", roleId=" + roleId +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", avatar=" + avatar +
                ", birthday=" + birthday +
                ", sex='" + sex + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", tel='" + tel + '\'' +
                ", superAdminFlag='" + superAdminFlag + '\'' +
                ", statusFlag=" + statusFlag +
                ", lastLoginIp='" + lastLoginIp + '\'' +
                ", lastLoginTime='" + lastLoginTime + '\'' +
                ", userType='" + userType + '\'' +
                ", cabinetId='" + cabinetId + '\'' +
                ", cabinetCode='" + cabinetCode + '\'' +
                ", createTime=" + createTime +
                ", createUser=" + createUser +
                ", updateTime=" + updateTime +
                ", updateUser=" + updateUser +
                ", delFlag='" + delFlag + '\'' +
                ", delTime=" + delTime +
                ", delUser=" + delUser +
                ", portrait='" + portrait + '\'' +
                '}';
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setAvatar(Long avatar) {
        this.avatar = avatar;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    public void setDelUser(Long delUser) {
        this.delUser = delUser;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }




    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public Date getBirthday() {
        return birthday;
    }



    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public void setDelTime(Timestamp delTime) {
        this.delTime = delTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSuperAdminFlag() {
        return superAdminFlag;
    }

    public void setSuperAdminFlag(String superAdminFlag) {
        this.superAdminFlag = superAdminFlag;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public Long getAvatar() {
        return avatar;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public Long getUpdateUser() {
        return updateUser;
    }

    public Long getDelUser() {
        return delUser;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(String cabinetId) {
        this.cabinetId = cabinetId;
    }

    public String getCabinetCode() {
        return cabinetCode;
    }

    public void setCabinetCode(String cabinetCode) {
        this.cabinetCode = cabinetCode;
    }





    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }


    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public Timestamp getDelTime() {
        return delTime;
    }
}
