/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kezuo.util;

/**
 *
 * @author zlzuo
 */
public class Crc8 {

    public static int hexToInt(String hex) { //16进制转为10进制  
        int i = Integer.parseInt(hex, 16);
        return i;
    }

    public static String hexToBinaryString(String hex) {//16进制转为2进制  
        int i = Integer.parseInt(hex, 16);
        return Integer.toBinaryString(i);
    }

    public static String binTohex(String bin) { //2进制转为16进制  
        int i = Integer.parseInt(bin, 2);
        return Integer.toHexString(i);
    }

    public static int getCRCChecker10(String data, String hex) { //取得检验和（10进制）  
        int check10 = 0;
        String s = hexToBinaryString(hex);
        for (int i = 0; i < s.length() - 1; i++) { //根据多项式最高次幂加多少个0  
            data += "0";
        }
        int a = Integer.valueOf(data, 2);
        int b = hexToInt(hex);
        check10 = a % b; //求余运算，得到校验和  
        return check10;
    }

    public static String getCRCChecker16(String data, String hex) { //取得检验和（16进制）  
        int check16 = getCRCChecker10(data, hex);
        return Integer.toHexString(check16);
    }

    public static String getCRCChecker2(String data, String hex) { //取得检验和（2进制）  
        int check2 = getCRCChecker10(data, hex);
        return Integer.toBinaryString(check2);
    }

    public static String getSendData(String data, String hex) {//取得发送的数据 元数据（二进制）+二进制校验和  
        String send = data;
        send += getCRCChecker2(data, hex);
        return send;
    }

    public static String getXOR(String data1, String data2) { //将2个二进制异或，返回一个二进制数  
        String XORString = "";
        int a = Integer.valueOf(data1, 2);
        int b = Integer.valueOf(data2, 2);
        int xor = a ^ b;
        XORString = Integer.toBinaryString(xor);
        return XORString;
    }

    public static String getHexXORBin2(String hex, String bin) { //将1个16进制数和1个二进制异或，返回一个二进制数  
        String XORString = "";
        int a = hexToInt(hex);
        int b = Integer.valueOf(bin, 2);
        int xor = a ^ b;
        XORString = Integer.toBinaryString(xor);
        return XORString;
    }

    public static String getHexXORBin(String hex, String bin) { //将1个16进制数和1个二进制异或，返回一个16进制数  
        String XORString = "";
        int a = hexToInt(hex);
        int b = Integer.valueOf(bin, 2);
        int xor = a ^ b;
        XORString = binTohex(Integer.toBinaryString(xor));
        return XORString;
    }

    public static boolean Check1(String receiver, String CRCCheck) { //判断接收的数据是否正确，接收的数据%校验和 是否整除  
        boolean flag = false;
        int rec = Integer.valueOf(receiver, 2);
        int ch = Integer.valueOf(CRCCheck, 2);
        if ((rec % ch) == 0) {
            flag = true;
        }
        return flag;
    }

    public static boolean Check(String receiver, String gxhex) { //判断接收的数据是否正确，接收的数据% G(x) 是否整除  
        boolean flag = false;
        int rec = Integer.valueOf(receiver, 2);
        //System.out.println(rec);  
        int gx = hexToInt(gxhex);
        //System.out.println(gx);  
        if ((rec % gx) == 0) {
            flag = true;
        }
        return flag;
    }

    public static String getMod(String bin, String hex) { //将一个2进制数和一个16进制数 取模运算  返回1个2进制序列  
        int rec = Integer.valueOf(bin, 2);
        int gx = hexToInt(hex);
        int mod = rec % gx;
        String s = Integer.toBinaryString(mod);
        return s;
    }
    
    

    public static void main(String[] args) {
        boolean flag = Check("1010011110010010", "107");
        System.out.println(flag);
        String s = getMod("1010011110010010", "107");
        System.out.println(s);
        String t = getMod("11010110110000", "13");
        System.out.println(t);
         

    }
}
