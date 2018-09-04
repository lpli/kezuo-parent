/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.common;

/**
 * @date 2017-12-15
 * @Description: 后台统一返回实体类
 * @author pole
 */
public class RespRes {

    private String code;  //返回代码  200成功，500失败
    private String msg;   //返回信息
    private Object data;  //返回数据信息，额外数据
    
    public static final String CODE200 = "200";
    public static final String CODE500 = "500";
    public static final String SYS_ERROR_MSG = "系统错误，请联系管理员！";
    
    public RespRes() {

    }

    public RespRes(String code) {
        this.code = code;
    }

    public RespRes(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 返回正确，无数据信息
     *
     * @return
     */
    public static RespRes ok() {
        return new RespRes(CODE200, "", null);
    }

    /**
     * 正确返回，带有数据
     *
     * @param data
     * @return
     */
    public static RespRes ok(Object data) {
        return new RespRes(CODE200, "", data);
    }

    /**
     * 返回错误，并将错误信息封装
     *
     * @param msg
     * @return
     */
    public static RespRes fail(String msg) {
        return new RespRes(CODE500, msg, null);
    }

    /**
     * 返回错误，并将错误信息,数据封装
     *
     * @param msg
     * @param data
     * @return
     */
    public static RespRes fail(String msg, Object data) {
        return new RespRes(CODE500, msg, data);
    }

    public static RespRes fail(Object data) {
        return new RespRes(CODE500, "操作错误，请联系管理员", data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

