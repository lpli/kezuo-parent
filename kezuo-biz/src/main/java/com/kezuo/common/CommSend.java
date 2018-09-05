package com.kezuo.common;

import com.kezuo.entity.Device;
import com.kezuo.core.dto.LinkCheckMessage;
import com.kezuo.core.dto.ObjectMessage;
import com.kezuo.core.dto.RealtimeMessage;
import com.kezuo.core.dto.RegisterMessage;

public class CommSend {

//    /**
//     * 获取客户端ID
//     *
//     * @param device
//     * @return
//     */
//    public static String getClientIdFromDevice(Device device) {
//        return device.getDistrictCode() + device.getDeviceNo();
//    }

    /**
     * 获取站址(1-60000)
     *
     * @param device
     * @return
     */
    public static String getStcdFromDevice(Device device) {
        String distCode = device.getDistrictCode();
        String distShortCode = distCode.substring(distCode.length() - 2, distCode.length());//截取村编码后2位
        return distShortCode + device.getDeviceNo();
    }

    /**
     * 设置默认常用信息
     *
     * @param om
     */
    public static void setDeviceUpMsgCommInfo(ObjectMessage om) {
        om.setDirect(ConstsKezuo.DIRECT_UP);
        om.setDiv(ConstsKezuo.DIV_ONE);
        om.setFcb(3);
        om.setFunctionCode(ConstsKezuo.FUNCTIONCODE_DEVICE);
        om.setProductNo(ConstsKezuo.PRODUCTNO);
        om.setProductPwd(ConstsKezuo.PRODUCTPWD);
        om.setMonth(7);
        om.setYear(18);
    }

    /**
     * 根据设备返回链路消息
     *
     * @param device
     * @return
     */
    public static ObjectMessage getLinkCheckMsgFromDevice(Device device) {
        LinkCheckMessage link = new LinkCheckMessage();
        CommSend.setDeviceUpMsgCommInfo(link);
        link.setStation(Integer.valueOf(CommSend.getStcdFromDevice(device)));
        return link;
    }

    /**
     * 根据设备返回serial
     *
     * @param device
     * @return
     */
    public static String getSerialFromDevice(Device device) {
        return "55102018204" + device.getDistrictCode() + device.getDeviceNo();
    }


    /**
     * 根据设备返回注册消息
     *
     * @param device
     * @return
     */
    public static ObjectMessage getRegMsgFromDevice(Device device) {
        RegisterMessage reg = new RegisterMessage();
        CommSend.setDeviceUpMsgCommInfo(reg);
        reg.setStation(Integer.valueOf(CommSend.getStcdFromDevice(device)));
        String serial = "55102018204" + device.getDistrictCode() + device.getDeviceNo();
        if (serial.length() < 32) {
            for (int i = 0, len = 32 - serial.length(); i < len; i++) {
                serial = "0" + serial;
            }
        }
        reg.setSerial(serial);
        return reg;
    }

    /**
     * 根据设备返回实时消息
     *
     * @param device
     * @return
     */
    public static ObjectMessage getRealMsgFromDevice(Device device) {
        //实时信息
        RealtimeMessage rm = new RealtimeMessage();
        CommSend.setDeviceUpMsgCommInfo(rm);
        rm.setStation(Integer.valueOf(CommSend.getStcdFromDevice(device)));
        rm.setChannelNum(2);
        rm.setData(new float[]{0, device.getWaterUsed().floatValue()});
        return rm;
    }

    /**
     * 初始化设备，创建客户端，发送链路和注册信息
     *
     * @param device
     * @return
     */
    public static void initDeviceClient(Device device) {
        //实时信息
    }
}
