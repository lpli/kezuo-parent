/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.util.mytool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.BeanUtils;

/**
 * 公共工具类
 *
 * @author 袁源
 */
public class CommUtils {

    /**
     * 判断对象是否为NULL
     *
     * @param obj 任意对象
     * @return boolean true 对象为NULL false 对象不为空
     */
    public static boolean isNull(Object obj) {
        boolean result = false;
        if (obj != null) {
            if (obj instanceof String) {
                if (((String) obj).trim().isEmpty()) {
                    result = true;
                }
            } else if (obj instanceof Collection) {
                if (((Collection) obj).isEmpty()) {
                    result = true;
                }
            } else if (obj instanceof Map) {
                if (((Map) obj).isEmpty()) {
                    result = true;
                }
            } else if (obj.getClass().isArray()) {
                if (Array.getLength(obj) <= 0) {
                    return true;
                }
            }

        } else {
            result = true;
        }
        return result;
    }

    /**
     * 判断对象是否为NULL
     *
     * @param obj 任意对象
     * @return boolean true 对象不为NULL， false 对象为空
     */
    public static boolean notNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 获得一个UUID
     *
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * MD5
     *
     * @param source 待产生MD5的byte数组
     * @return String MD5值
     */
    public static String getMD5(byte[] source) {
        String s = null;
        char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            md.update(source);
            byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
            // 用字节表示就是 16 个字节
            char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
            // 所以表示成 16 进制需要 32 个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
                // 转换成 16 进制字符的转换
                byte byte0 = tmp[i]; // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
                // >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
            }
            s = new String(str); // 换后的结果转换为字符串

        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 设置MD5，zlzuo
     *
     * @param inStr
     * @return
     */
    public static String setMD5(String inStr) {
        char[] arr = inStr.toCharArray();
        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (char) (arr[i] ^ 't');
            arr[i] = (char) (arr[i] ^ 'h');
        }
        String outStr = new String(arr);
        return outStr;
    }

    /**
     * check MD5
     *
     * @param inStr
     * @throws java.lang.Exception
     */
    public static void checkMD5(String inStr) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dateExp = format.parse(setMD5(inStr));
        Date dateNow = new Date();
        if (dateNow.after(dateExp)) {
            throw new RuntimeException("checkMD5异常！");
        }
    }

    /**
     * 根据固定值，序列和key长度返回生成好的完整的key
     *
     * @param fixchar 补0左边的字符
     * @param charLen key长度
     * @param SeqNumStr 序列值
     * @return String 生成的key
     */
    public static String fillZeroChar(String fixchar, Integer charLen, String SeqNumStr) {
        Integer filllen = charLen - fixchar.length() - SeqNumStr.length();
        String fillstr = "";
        for (int i = 0; i < filllen; i++) {
            fillstr = fillstr + "0";
        }
        return fixchar + fillstr + SeqNumStr;
    }
    private static final SimpleDateFormat sdf = new SimpleDateFormat();

    /**
     * 产生校验码
     *
     * @return String 一位校验嘛
     */
    public static String createCheckNum(String str) {
        int s = 0;
        int p = 0;
        for (int i = 0; i < str.length(); i++) {
            s = p % 11 + Integer.parseInt(str.substring(i, i + 1));
            if (s % 10 == 0) {
                p = 20;
            } else {
                p = (s % 10) * 2;
            }
        }
        p = (11 - p % 11) % 10;
        return Integer.toString(p);
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
     *
     * @param v1 被除数
     * @param v2 除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获得对象的指定属性的值 该属性必须是protected或者public，如果是private则必须有对应的get方法，否则获取不到值
     *
     * @param obj 对象
     * @param fieldName 对象中的属性名
     * @return 属性值
     */
    public static Object getObjectFieldValue(Object obj, String fieldName) {
        if (obj == null || isNull(fieldName)) {
            return null;
        }
        //
        Object resutl = null;

        try {
            //获得该对象字段的get方法
            Method methos = obj.getClass().getDeclaredMethod("get" + firstCharToUpperCase(fieldName));
            if (methos != null) {
                //通过方法获得该属性值
                resutl = methos.invoke(obj);
            } else {
                //获得该字段
                Field field = obj.getClass().getDeclaredField(fieldName);
                resutl = field.get(obj);
            }
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            return resutl;
        }
    }

    /**
     * 判断两个对象的值是否相等 value1和value2可以是
     * String,Integer,Long,java.util.Date,Double,Float,BigDecimal
     *
     * @param value1 对象1
     * @param value2 对象2
     * @return boolean true 相等 boolean false 不相等
     */
    public static boolean checkTwoObjectValue(Object value1, Object value2) {
        boolean result = false;
        if (value1 == null || value2 == null) {
            return false;
        }

        if (value1 instanceof String && value2 instanceof String) {
            if (((String) value1).equals((String) value2)) {
                result = true;
            } else {
                result = false;
            }
        } else if (value1 instanceof Integer && value2 instanceof Integer) {
            if (((Integer) value1).compareTo((Integer) value2) == 0) {
                result = true;
            } else {
                result = false;
            }
        } else if (value1 instanceof Long && value2 instanceof Long) {
            if (((Long) value1).compareTo((Long) value2) == 0) {
                result = true;
            } else {
                result = false;
            }
        } else if (value1 instanceof Double && value2 instanceof Double) {
            if (((Double) value1).compareTo((Double) value2) == 0) {
                result = true;
            } else {
                result = false;
            }
        } else if (value1 instanceof Float && value2 instanceof Float) {
            if (((Float) value1).compareTo((Float) value2) == 0) {
                result = true;
            } else {
                result = false;
            }
        } else if (value1 instanceof BigDecimal && value2 instanceof BigDecimal) {
            if (((BigDecimal) value1).compareTo((BigDecimal) value2) == 0) {
                result = true;
            } else {
                result = false;
            }
        } else if (value1 instanceof java.util.Date && value2 instanceof java.util.Date) {
            if (((java.util.Date) value1).compareTo((java.util.Date) value2) == 0) {
                result = true;
            } else {
                result = false;
            }
        }

        return result;
    }

    /**
     * 根据对象映射到map
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> getObjectToMap(Object obj) throws Exception {
        Map<String, Object> map = null;
        if (null != obj) {
            map = new HashMap<String, Object>();

            Field[] fds = obj.getClass().getDeclaredFields();
            String fieldName;
            for (Field f : fds) {
                fieldName = f.getName();
                fieldName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

                Method mt = obj.getClass().getDeclaredMethod(fieldName);
                Object o = mt.invoke(obj);
                if (null != o) {
                    map.put(f.getName(), o);
                }
            }
        }
        return map;
    }

    /**
     * 首字母大写 str为NULL或者是空字符串则返回NULL
     *
     * @param str 字符串
     * @return 首字母转换成大写的字符串
     */
    public static String firstCharToUpperCase(String str) {
        if (isNull(str)) {
            return null;
        }

        String upperCase = str.toUpperCase();

        return upperCase.substring(0, 1) + str.substring(1);
    }

    /**
     * 设置内存分页数据
     *
     * @param startCount
     * @param pageSize
     * @param list
     * @return
     */
    public static List<?> setListPageDate(Integer startCount, Integer pageSize, List<?> list) {
        List<?> pageList = null;
        //设置内存分页数据
        if (null != list) {
            startCount = (null == startCount) ? 0 : startCount;
            pageSize = (null == pageSize) ? Integer.MAX_VALUE : pageSize;
            Integer maxSize = startCount + pageSize;
            Integer total = list.size();
            if (maxSize < total) {
                pageList = list.subList(startCount, maxSize);
            } else {
                pageList = list.subList(startCount, list.size());
            }
        }
        return (null == pageList) ? new ArrayList<Class<?>>() : pageList;
    }

    /**
     * 获取总页数
     *
     * @param total 总记录数
     * @param max 每页显示记录树
     * @return
     */
    public static int getPageSize(int total, int max) {
        int count = 0;
        if (total % max == 0) {
            count = total / max;
        } else {
            count = total / max + 1;
        }
        return count;
    }

    /**
     * 获取当前
     *
     * @param begin 开始记录数
     * @param max 每页最大显示记录数
     * @return
     */
    public static int getCurrPageSize(int begin, int max) {
        begin += max;
        int currPageSize = begin / max;
        return currPageSize;
    }

    /**
     * 计算经纬度两点之间的距离,经度(正:东经 负:西经)、纬度(正:北纬 负:南纬)
     *
     * @param lng1 经度1
     * @param lat1 纬度1
     * @param lng2 经度2
     * @param lat2 纬度2
     * @return 距离(单位米)
     */
    public static double getDistanceOfTwoPoints(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = lat1 * Math.PI / 180.0;
        double radLat2 = lat2 * Math.PI / 180.0;
        double radLng1 = lng1 * Math.PI / 180.0;
        double radLng2 = lng2 * Math.PI / 180.0;
        double a = radLat1 - radLat2;
        double b = radLng1 - radLng2;
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    public static void main(String[] args){
        try {
//            checkMD5("2017-11-22");
//            Logger.getLogger(CommUtils.class.getName()).info("校验成功");
            System.out.println(CommUtils.getMD5("@Wh123456".getBytes()));
        } catch (Exception ex) {
            Logger.getLogger(CommUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String readFileByLine(String filePath) {
        File file = new File(filePath);
        // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
        BufferedReader buf = null;
        String result = null;
        try {
            // FileReader:用来读取字符文件的便捷类。
            //buf = new BufferedReader(new FileReader(file));
            buf = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
            String temp = null;
            while ((temp = buf.readLine()) != null) {
                result = temp;
            }
        } catch (Exception e) {
        } finally {
            if (buf != null) {
                try {
                    buf.close();
                } catch (IOException e) {
                    e.getStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 判断字符串是否为整数
     *
     * @param value
     * @return
     */
    public static boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否为浮点数
     *
     * @param value
     * @return
     */
    public static boolean isDouble(String value) {
        try {
            Double.parseDouble(value);
            if (value.contains(".")) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否为数字
     *
     * @param value
     * @return
     */
    public static boolean isNumber(String value) {
        return isInteger(value) || isDouble(value);
    }

    /**
     * 获得返回map
     *
     * @return
     */
    public static Map<String, Object> getReturnMap() {
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("flag", false);
        return returnMap;
    }

    /**
     * 设置flag为true
     *
     * @param returnMap
     */
    public static void setReturnMapFlagTrue(Map<String, Object> returnMap) {
        returnMap.put("flag", true);
    }

    /**
     * 设置info信息
     *
     * @param returnMap
     * @param obj
     */
    public static void setReturnMapInfo(Map<String, Object> returnMap, Object obj) {
        returnMap.put("info", obj);
    }

    /**
     * 设置数据到map
     *
     * @param queryMap
     * @param key
     * @param obj
     */
    public static void setMapParam(Map<String, Object> queryMap, String key, Object obj) {
        if (!CommUtils.isNull(obj)) {
            queryMap.put(key, obj);
        }
    }

    
    /**
     * 驼峰格式字符串转换为下划线格式字符串
     * 
     * @param param
     * @return
     */
    public static String camelToUnderline(String param) {
        if (param == null || "".equals(param.trim())) {
            return "";
        }
        int len = param.length();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            char c = param.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
