package com.kezuo.entity.dto;

import java.util.Date;

public class UseWaterRecordMsg  {

    private Integer recodeType;//记录类型
    private Date recodeTm;//记录时间，后面为记录数据
    private String openUserNo;//开启用户编号
    private Date openTm;
    private Integer openWay;
    private String closeUserNo;//关闭用户编号
    private Date closeTm;
    private Integer closeWay;
    private Integer meterMode;//计量模式，0：起停，1：调试，2：计
    private Float userElecNum;//用电量
    private Float userWaterNum;
    private Float userTmNum;//用时，单位分钟。
    private Float userMoneyNum;
    private Float beginWaterNum;//启动用水量
    private Float endWaterNum;
    private Float beginElecNum;//启动电表量
    private Float endElecNum;
    private Float totalElecNum;//总用电量
    private Float totalWaterNum;
    private Float totalTmNum;
    private Float totalMoneyNum;

    public Integer getRecodeType() {
        return recodeType;
    }

    public void setRecodeType(Integer recodeType) {
        this.recodeType = recodeType;
    }

    public Date getRecodeTm() {
        return recodeTm;
    }

    public void setRecodeTm(Date recodeTm) {
        this.recodeTm = recodeTm;
    }

    public String getOpenUserNo() {
        return openUserNo;
    }

    public void setOpenUserNo(String openUserNo) {
        this.openUserNo = openUserNo;
    }

    public Date getOpenTm() {
        return openTm;
    }

    public void setOpenTm(Date openTm) {
        this.openTm = openTm;
    }

    public Integer getOpenWay() {
        return openWay;
    }

    public void setOpenWay(Integer openWay) {
        this.openWay = openWay;
    }

    public String getCloseUserNo() {
        return closeUserNo;
    }

    public void setCloseUserNo(String closeUserNo) {
        this.closeUserNo = closeUserNo;
    }

    public Date getCloseTm() {
        return closeTm;
    }

    public void setCloseTm(Date closeTm) {
        this.closeTm = closeTm;
    }

    public Integer getCloseWay() {
        return closeWay;
    }

    public void setCloseWay(Integer closeWay) {
        this.closeWay = closeWay;
    }

    public Integer getMeterMode() {
        return meterMode;
    }

    public void setMeterMode(Integer meterMode) {
        this.meterMode = meterMode;
    }

    public Float getUserElecNum() {
        return userElecNum;
    }

    public void setUserElecNum(Float userElecNum) {
        this.userElecNum = userElecNum;
    }

    public Float getUserWaterNum() {
        return userWaterNum;
    }

    public void setUserWaterNum(Float userWaterNum) {
        this.userWaterNum = userWaterNum;
    }

    public Float getUserTmNum() {
        return userTmNum;
    }

    public void setUserTmNum(Float userTmNum) {
        this.userTmNum = userTmNum;
    }

    public Float getUserMoneyNum() {
        return userMoneyNum;
    }

    public void setUserMoneyNum(Float userMoneyNum) {
        this.userMoneyNum = userMoneyNum;
    }

    public Float getBeginWaterNum() {
        return beginWaterNum;
    }

    public void setBeginWaterNum(Float beginWaterNum) {
        this.beginWaterNum = beginWaterNum;
    }

    public Float getEndWaterNum() {
        return endWaterNum;
    }

    public void setEndWaterNum(Float endWaterNum) {
        this.endWaterNum = endWaterNum;
    }

    public Float getBeginElecNum() {
        return beginElecNum;
    }

    public void setBeginElecNum(Float beginElecNum) {
        this.beginElecNum = beginElecNum;
    }

    public Float getEndElecNum() {
        return endElecNum;
    }

    public void setEndElecNum(Float endElecNum) {
        this.endElecNum = endElecNum;
    }

    public Float getTotalElecNum() {
        return totalElecNum;
    }

    public void setTotalElecNum(Float totalElecNum) {
        this.totalElecNum = totalElecNum;
    }

    public Float getTotalWaterNum() {
        return totalWaterNum;
    }

    public void setTotalWaterNum(Float totalWaterNum) {
        this.totalWaterNum = totalWaterNum;
    }

    public Float getTotalTmNum() {
        return totalTmNum;
    }

    public void setTotalTmNum(Float totalTmNum) {
        this.totalTmNum = totalTmNum;
    }

    public Float getTotalMoneyNum() {
        return totalMoneyNum;
    }

    public void setTotalMoneyNum(Float totalMoneyNum) {
        this.totalMoneyNum = totalMoneyNum;
    }
}
