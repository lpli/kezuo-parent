/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.common;

import com.kezuo.Client;
import com.kezuo.entity.Device;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zlzuo
 */
public class ConstsKezuo {

    //客户端信息
    public static String HOST = "tripnet.unilogger.cn";//主机IP
    public static int PORT = 10260;//主机端口


    //INT DIRECT, INT DIV, INT FCB, INT FUNCTIONCODE, INT PRODUCTNO, INT PRODUCTPWD, INT MONTH, INT YEAR;
    public static int DIRECT_UP = 1;
    public static int DIRECT_DOWN = 0;
    public static int DIV_MORE = 1;
    public static int DIV_ONE = 0;
    public static int FUNCTIONCODE_DEVICE = 1;
    public static int FUNCTIONCODE_PLAT = 0;
    public static int PRODUCTNO = 120;
    public static int PRODUCTPWD = 6;
    public static int MONTH = 7;
    public static int YEAR = 18;
    public static int INTERVAL = 60;

    //客户端集合
    public static final ConcurrentHashMap<String, Client> CLIENT_MAP = new ConcurrentHashMap<>();

    public static final ConcurrentHashMap<String, Device> DEVICE_MAP = new ConcurrentHashMap<>();

    public static int DAYS_PRE = -2;//向前推的天数
    public static final int CLIENT_NUM_LIMIT = 100;//测试阶段限制数较小,设置-1，表示没有限制，以max准
    public static final int CLIENT_NUM_MAX = 300;//最大客户端数量

    /**
     * @param size
     * @return
     */
    public static int calcClentNum(int size) {
        if (CLIENT_NUM_LIMIT > 0 && CLIENT_NUM_LIMIT < size) {
            return CLIENT_NUM_LIMIT;
        }
        if (size <= CLIENT_NUM_MAX) {
            return size;
        } else {
            return CLIENT_NUM_MAX;
        }
    }
}
