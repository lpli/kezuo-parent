/*
 * Copyright (c) 111.com Inc. All rights  reserved.
 *
 *
 */

package com.kezuo.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author liliping
 * @Date 2018/8/22
 **/
public class Crc8Util {

    public enum CrcType {
        HIGH(1, "高位"), LOW(0, "低位");
        private int code;
        private String desc;

        CrcType(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode() {
            return code;
        }

        public String getDesc() {
            return desc;
        }
    }

    public static byte[] crc8_high_tab = new byte[]{0x0, (byte) 0x80, (byte) 0xe5, 0x65, 0x2f, (byte) 0xaf,
            (byte) 0xca, 0x4a, 0x5e, (byte) 0xde, (byte) 0xbb, 0x3b, 0x71, (byte) 0xf1, (byte) 0x94, 0x14, (byte) 0xbc,
            0x3c, 0x59, (byte) 0xd9, (byte) 0x93, 0x13, 0x76, (byte) 0xf6, (byte) 0xe2, 0x62, 0x7, (byte) 0x87,
            (byte) 0xcd, 0x4d, 0x28, (byte) 0xa8, (byte) 0x9d, 0x1d, 0x78, (byte) 0xf8, (byte) 0xb2, 0x32, 0x57,
            (byte) 0xd7, (byte) 0xc3, 0x43, 0x26, (byte) 0xa6, (byte) 0xec, 0x6c, 0x9, (byte) 0x89, 0x21, (byte) 0xa1,
            (byte) 0xc4, 0x44, 0xe, (byte) 0x8e, (byte) 0xeb, 0x6b, 0x7f, (byte) 0xff, (byte) 0x9a, 0x1a, 0x50,
            (byte) 0xd0, (byte) 0xb5, 0x35, (byte) 0xdf, 0x5f, 0x3a, (byte) 0xba, (byte) 0xf0, 0x70, 0x15, (byte) 0x95,
            (byte) 0x81, 0x1, 0x64, (byte) 0xe4, (byte) 0xae, 0x2e, 0x4b, (byte) 0xcb, 0x63, (byte) 0xe3, (byte) 0x86,
            0x6, 0x4c, (byte) 0xcc, (byte) 0xa9, 0x29, 0x3d, (byte) 0xbd, (byte) 0xd8, 0x58, 0x12, (byte) 0x92,
            (byte) 0xf7, 0x77, 0x42, (byte) 0xc2, (byte) 0xa7, 0x27, 0x6d, (byte) 0xed, (byte) 0x88, 0x8, 0x1c,
            (byte) 0x9c, (byte) 0xf9, 0x79, 0x33, (byte) 0xb3, (byte) 0xd6, 0x56, (byte) 0xfe, 0x7e, 0x1b, (byte) 0x9b,
            (byte) 0xd1, 0x51, 0x34, (byte) 0xb4, (byte) 0xa0, 0x20, 0x45, (byte) 0xc5, (byte) 0x8f, 0xf, 0x6a,
            (byte) 0xea, 0x5b, (byte) 0xdb, (byte) 0xbe, 0x3e, 0x74, (byte) 0xf4, (byte) 0x91, 0x11, 0x5, (byte) 0x85,
            (byte) 0xe0, 0x60, 0x2a, (byte) 0xaa, (byte) 0xcf, 0x4f, (byte) 0xe7, 0x67, 0x2, (byte) 0x82, (byte) 0xc8,
            0x48, 0x2d, (byte) 0xad, (byte) 0xb9, 0x39, 0x5c, (byte) 0xdc, (byte) 0x96, 0x16, 0x73, (byte) 0xf3,
            (byte) 0xc6, 0x46, 0x23, (byte) 0xa3, (byte) 0xe9, 0x69, 0xc, (byte) 0x8c, (byte) 0x98, 0x18, 0x7d,
            (byte) 0xfd, (byte) 0xb7, 0x37, 0x52, (byte) 0xd2, 0x7a, (byte) 0xfa, (byte) 0x9f, 0x1f, 0x55, (byte) 0xd5,
            (byte) 0xb0, 0x30, 0x24, (byte) 0xa4, (byte) 0xc1, 0x41, 0xb, (byte) 0x8b, (byte) 0xee, 0x6e, (byte) 0x84,
            0x4, 0x61, (byte) 0xe1, (byte) 0xab, 0x2b, 0x4e, (byte) 0xce, (byte) 0xda, 0x5a, 0x3f, (byte) 0xbf,
            (byte) 0xf5, 0x75, 0x10, (byte) 0x90, 0x38, (byte) 0xb8, (byte) 0xdd, 0x5d, 0x17, (byte) 0x97, (byte) 0xf2,
            0x72, 0x66, (byte) 0xe6, (byte) 0x83, 0x3, 0x49, (byte) 0xc9, (byte) 0xac, 0x2c, 0x19, (byte) 0x99,
            (byte) 0xfc, 0x7c, 0x36, (byte) 0xb6, (byte) 0xd3, 0x53, 0x47, (byte) 0xc7, (byte) 0xa2, 0x22, 0x68,
            (byte) 0xe8, (byte) 0x8d, 0xd, (byte) 0xa5, 0x25, 0x40, (byte) 0xc0, (byte) 0x8a, 0xa, 0x6f, (byte) 0xef,
            (byte) 0xfb, 0x7b, 0x1e, (byte) 0x9e, (byte) 0xd4, 0x54, 0x31, (byte) 0xb1};

    public static byte[] crc8_low_tab = new byte[]{(byte) 0x0, (byte) 0x9e, (byte) 0x73, (byte) 0xed, (byte) 0xa9,
            (byte) 0x37, (byte) 0xda, (byte) 0x44, (byte) 0x1d, (byte) 0x83, (byte) 0x6e, (byte) 0xf0, (byte) 0xb4,
            (byte) 0x2a, (byte) 0xc7, (byte) 0x59, (byte) 0x3a, (byte) 0xa4, (byte) 0x49, (byte) 0xd7, (byte) 0x93,
            (byte) 0xd, (byte) 0xe0, (byte) 0x7e, (byte) 0x27, (byte) 0xb9, (byte) 0x54, (byte) 0xca, (byte) 0x8e,
            (byte) 0x10, (byte) 0xfd, (byte) 0x63, (byte) 0x74, (byte) 0xea, (byte) 0x7, (byte) 0x99, (byte) 0xdd,
            (byte) 0x43, (byte) 0xae, (byte) 0x30, (byte) 0x69, (byte) 0xf7, (byte) 0x1a, (byte) 0x84, (byte) 0xc0,
            (byte) 0x5e, (byte) 0xb3, (byte) 0x2d, (byte) 0x4e, (byte) 0xd0, (byte) 0x3d, (byte) 0xa3, (byte) 0xe7,
            (byte) 0x79, (byte) 0x94, (byte) 0xa, (byte) 0x53, (byte) 0xcd, (byte) 0x20, (byte) 0xbe, (byte) 0xfa,
            (byte) 0x64, (byte) 0x89, (byte) 0x17, (byte) 0xa7, (byte) 0x39, (byte) 0xd4, (byte) 0x4a, (byte) 0xe,
            (byte) 0x90, (byte) 0x7d, (byte) 0xe3, (byte) 0xba, (byte) 0x24, (byte) 0xc9, (byte) 0x57, (byte) 0x13,
            (byte) 0x8d, (byte) 0x60, (byte) 0xfe, (byte) 0x9d, (byte) 0x3, (byte) 0xee, (byte) 0x70, (byte) 0x34,
            (byte) 0xaa, (byte) 0x47, (byte) 0xd9, (byte) 0x80, (byte) 0x1e, (byte) 0xf3, (byte) 0x6d, (byte) 0x29,
            (byte) 0xb7, (byte) 0x5a, (byte) 0xc4, (byte) 0xd3, (byte) 0x4d, (byte) 0xa0, (byte) 0x3e, (byte) 0x7a,
            (byte) 0xe4, (byte) 0x9, (byte) 0x97, (byte) 0xce, (byte) 0x50, (byte) 0xbd, (byte) 0x23, (byte) 0x67,
            (byte) 0xf9, (byte) 0x14, (byte) 0x8a, (byte) 0xe9, (byte) 0x77, (byte) 0x9a, (byte) 0x4, (byte) 0x40,
            (byte) 0xde, (byte) 0x33, (byte) 0xad, (byte) 0xf4, (byte) 0x6a, (byte) 0x87, (byte) 0x19, (byte) 0x5d,
            (byte) 0xc3, (byte) 0x2e, (byte) 0xb0, (byte) 0xff, (byte) 0x61, (byte) 0x8c, (byte) 0x12, (byte) 0x56,
            (byte) 0xc8, (byte) 0x25, (byte) 0xbb, (byte) 0xe2, (byte) 0x7c, (byte) 0x91, (byte) 0xf, (byte) 0x4b,
            (byte) 0xd5, (byte) 0x38, (byte) 0xa6, (byte) 0xc5, (byte) 0x5b, (byte) 0xb6, (byte) 0x28, (byte) 0x6c,
            (byte) 0xf2, (byte) 0x1f, (byte) 0x81, (byte) 0xd8, (byte) 0x46, (byte) 0xab, (byte) 0x35, (byte) 0x71,
            (byte) 0xef, (byte) 0x2, (byte) 0x9c, (byte) 0x8b, (byte) 0x15, (byte) 0xf8, (byte) 0x66, (byte) 0x22,
            (byte) 0xbc, (byte) 0x51, (byte) 0xcf, (byte) 0x96, (byte) 0x8, (byte) 0xe5, (byte) 0x7b, (byte) 0x3f,
            (byte) 0xa1, (byte) 0x4c, (byte) 0xd2, (byte) 0xb1, (byte) 0x2f, (byte) 0xc2, (byte) 0x5c, (byte) 0x18,
            (byte) 0x86, (byte) 0x6b, (byte) 0xf5, (byte) 0xac, (byte) 0x32, (byte) 0xdf, (byte) 0x41, (byte) 0x5,
            (byte) 0x9b, (byte) 0x76, (byte) 0xe8, (byte) 0x58, (byte) 0xc6, (byte) 0x2b, (byte) 0xb5, (byte) 0xf1,
            (byte) 0x6f, (byte) 0x82, (byte) 0x1c, (byte) 0x45, (byte) 0xdb, (byte) 0x36, (byte) 0xa8, (byte) 0xec,
            (byte) 0x72, (byte) 0x9f, (byte) 0x1, (byte) 0x62, (byte) 0xfc, (byte) 0x11, (byte) 0x8f, (byte) 0xcb,
            (byte) 0x55, (byte) 0xb8, (byte) 0x26, (byte) 0x7f, (byte) 0xe1, (byte) 0xc, (byte) 0x92, (byte) 0xd6,
            (byte) 0x48, (byte) 0xa5, (byte) 0x3b, (byte) 0x2c, (byte) 0xb2, (byte) 0x5f, (byte) 0xc1, (byte) 0x85,
            (byte) 0x1b, (byte) 0xf6, (byte) 0x68, (byte) 0x31, (byte) 0xaf, (byte) 0x42, (byte) 0xdc, (byte) 0x98,
            (byte) 0x6, (byte) 0xeb, (byte) 0x75, (byte) 0x16, (byte) 0x88, (byte) 0x65, (byte) 0xfb, (byte) 0xbf,
            (byte) 0x21, (byte) 0xcc, (byte) 0x52, (byte) 0xb, (byte) 0x95, (byte) 0x78, (byte) 0xe6, (byte) 0xa2,
            (byte) 0x3c, (byte) 0xd1, (byte) 0x4f};

    /**
     * 高位单字节crc8 x7+x6+x5+x2+1 1110 0101
     *
     * @param ch
     * @return
     */
    public static byte singleByteHighCrc(byte ch) {
        byte crc = ch;
        for (int i = 7; i > 0; --i) {
            if ((crc & 0x80) != 0) {
                crc = (byte) ((crc << 1) ^ 0x31);
            } else {
                crc = (byte) (crc << 1);
            }
        }
        return crc;
    }

    /**
     * 低位单字节crc8 x7+x6+x5+x2+1 1010 0111
     *
     * @param ch
     * @return
     */
    public static byte singleByteLowCrc(byte ch) {
        byte crc = ch;
        for (int i = 7; i > 0; --i) {
            if ((crc & 0x01) != 0) {
                crc = (byte) ((crc >> 1) ^ 0xA7);
            } else {
                crc = (byte) (crc >> 1);
            }
        }
        return crc;
    }

    /**
     * 获取crc8�?
     *
     * @param type 0 低位�?1高位
     * @return
     */
    public static String getCrc8Tab(CrcType type) {
        StringBuilder strb = new StringBuilder();
        for (int i = 0; i <= 0xff; i++) {
            if ((i % 16) == 0) {
                strb.append("\n");
            }
            if (type == CrcType.HIGH) {
                strb.append("(byte)").append(String.format("%#x", singleByteHighCrc((byte) (i & 0xff)))).append(",");
            } else {
                strb.append("(byte)").append(String.format("%#x", singleByteLowCrc((byte) (i & 0xff)))).append(",");
            }

        }
        return strb.substring(0, strb.length() - 1);
    }

    /**
     * 获取单字�? crc校验�?
     *
     * @param data 数据
     * @param type 类型
     * @return
     */
    public static byte getCrc(byte[] data, CrcType type) {
        byte crc = 0x00;
        for (byte d : data) {
            if (type == CrcType.HIGH) {
                crc = crc8_high_tab[0xff & (crc ^ d)];
            } else {
                crc = crc8_low_tab[0xff & (crc ^ d)];
            }
        }
        return crc;
    }

    /**
     * 字符转换为字�?
     *
     * @param c
     * @return
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制字符串转字节数组
     *
     * @param hex
     * @return
     */
    public static byte[] hexString2Bytes(String hex) {
        if ((hex == null) || (hex.equals(""))) {
            return null;
        } else if (hex.length() % 2 != 0) {
            return null;
        } else {
            hex = hex.toUpperCase();
            int len = hex.length() / 2;
            byte[] b = new byte[len];
            char[] hc = hex.toCharArray();
            for (int i = 0; i < len; i++) {
                int p = 2 * i;
                b[i] = (byte) (charToByte(hc[p]) << 4 | charToByte(hc[p + 1]));
            }
            return b;
        }

    }

    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
            'f'};

    public static String byte2HexString(byte[] data) {
        char[] buf = new char[data.length * 2];
        int a = 0;
        int index = 0;
        for (byte b : data) {
            // 使用除与取余进行转换
            if (b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }

    public static String byte2HexString(byte data) {
        return byte2HexString(new byte[]{data});
    }

    public static byte[] float2byte(float f) {

        // 把float转换为byte[]
        int fbit = Float.floatToIntBits(f);

        byte[] b = new byte[4];
        for (int i = 0; i < 4; i++) {
            b[i] = (byte) (fbit >> (24 - i * 8));
        }

        // 翻转数组
        int len = b.length;
        // 建立一个与源数组元素类型相同的数组
        byte[] dest = new byte[len];
        // 为了防止修改源数组，将源数组拷贝一份副本
        System.arraycopy(b, 0, dest, 0, len);
        byte temp;
        // 将顺位第i个与倒数第i个交换
        for (int i = 0; i < len / 2; ++i) {
            temp = dest[i];
            dest[i] = dest[len - i - 1];
            dest[len - i - 1] = temp;
        }

        return dest;
    }

    public static String formatHexString(String hexStr) {
        if (hexStr.length() % 2 != 0) {
            System.out.println("数据不正确");
            return null;
        }
        int splitLen = 2;
        int n = hexStr.length() / 2;
        List<String> list = new ArrayList<String>(n);
        for (int i = 0; i < n; i++) {
            list.add(hexStr.substring(i * splitLen, (i + 1) * splitLen));
        }
        return StringUtils.join(list, " ");
    }

    public static void main(String[] args) {
        String hexStr = "00786712000102f2";

//		System.out.println(Integer.toHexString(0xff & getCrc(hexString2Bytes(hexStr), CrcType.HIGH)));
        // System.out.println(formatHexString(byte2HexString(float2byte(324f))));;

        System.out.println(getCrc8Tab(CrcType.HIGH));
    }
}
