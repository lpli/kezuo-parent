package com.kezuo.common;

import com.kezuo.entity.Device;
import com.kezuo.core.dto.LinkCheckMessage;
import com.kezuo.core.dto.ObjectMessage;
import com.kezuo.core.dto.RealtimeMessage;
import com.kezuo.core.dto.RegisterMessage;
import com.kezuo.entity.dto.UseWaterRecordMsg;
import com.kezuo.util.Crc8Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

public class CommSend {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

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


    // 把一个字符串的第一个字母大写、效率是最高的、
    public static String getMethodName(String fildeName) {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    /**
     * 获取对象的16进制字符串
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static String getHexStringFromObject(Object object) throws Exception {
        StringBuilder sb = new StringBuilder();
        Field[] fields = null;
        //Method[] methods = UseWaterRecordMsg.class.getMethods();
        if (object instanceof UseWaterRecordMsg) {
            fields = UseWaterRecordMsg.class.getDeclaredFields();
        }
        if (fields == null) {
            return null;
        }
        Field field;
        Method m;
        for (int i = 0; i < fields.length; i++) {
            field = fields[i];
            field.setAccessible(true);
//            System.out.println(field.getName());
            if (field.getGenericType().toString().equals("class java.lang.Integer")) {
                m = (Method) object.getClass().getMethod("get" + CommSend.getMethodName(field.getName()));
                Integer val = (Integer) m.invoke(object);
                if (val != null) {
                    sb.append(Crc8Util.byte2HexString((byte) (val & 0xff)));
                } else {
                    sb.append("00 00 00 00");
                }
            } else if (field.getGenericType().toString().equals("class java.lang.String")) {
                m = (Method) object.getClass().getMethod("get" + CommSend.getMethodName(field.getName()));
                String val = (String) m.invoke(object);
                if (val != null) {
                    sb.append(val);
                } else {
                    sb.append("00 00 00 00 00 00");
                }
            } else if (field.getGenericType().toString().equals("class java.lang.Float")) {
                m = (Method) object.getClass().getMethod("get" + CommSend.getMethodName(field.getName()));
                Float val = (Float) m.invoke(object);
                if (val != null) {
                    sb.append(Crc8Util.byte2HexString(Crc8Util.float2byte(val)));
                } else {
                    sb.append("00 00 00 00");
                }
            } else if (field.getGenericType().toString().equals("class java.util.Date")) {
                m = (Method) object.getClass().getMethod("get" + CommSend.getMethodName(field.getName()));
                Date val = (Date) m.invoke(object);
                Calendar cal = Calendar.getInstance();
                cal.setTime(val);
                sb.append(Crc8Util.byte2HexString((byte) ((cal.get(Calendar.YEAR) - 2000) & 0xff)));
                sb.append(Crc8Util.byte2HexString((byte) ((cal.get(Calendar.MONTH) + 1) & 0xff)));
                sb.append(Crc8Util.byte2HexString((byte) (cal.get(Calendar.DAY_OF_MONTH) & 0xff)));
                sb.append(Crc8Util.byte2HexString((byte) (cal.get(Calendar.HOUR_OF_DAY) & 0xff)));
                sb.append(Crc8Util.byte2HexString((byte) (cal.get(Calendar.MINUTE) & 0xff)));
                sb.append(Crc8Util.byte2HexString((byte) (cal.get(Calendar.SECOND) & 0xff)));
            }
        }
        return sb.toString();
    }
}
