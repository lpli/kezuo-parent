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
public class FloatToHex {

    public static void main(String[] args) {
        String s = "3E1E9E9F";
        Float value = Float.intBitsToFloat(Integer.valueOf(s.trim(), 16));
        System.out.println(value);

        Float f = 324.0f;
        System.out.print("324.0f to hex : ");
        System.out.println(Integer.toHexString(Float.floatToIntBits(f)));
        System.out.println(Float.floatToIntBits(f));

        Float fmax = Float.MAX_VALUE;
        System.out.print("Float.MAX_VALUE to hex : ");
        System.out.println(Integer.toHexString(Float.floatToIntBits(fmax)));
        System.out.println(Float.floatToIntBits(fmax));

        System.out.print("120 to hex : ");
        System.out.println(Integer.toHexString(120));

        try {
            String sum = "9a99a543";
            System.out.print("String（9a99a543） to float : ");
            System.out.println(Float.intBitsToFloat(Integer.valueOf(sum.trim(), 16)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sum2 = "43a5999a";
            System.out.print("String（43a5999a） to float : ");
            System.out.println(Float.intBitsToFloat(Integer.valueOf(sum2.trim(), 16)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sum = "b50eb43e";
            System.out.print("String（b50eb43e） to float : ");
            System.out.println(Float.intBitsToFloat(Integer.valueOf(sum.trim(), 16)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sum2 = "3eb40eb5";
            System.out.print("String（3eb40eb5） to float : ");
            System.out.println(Float.intBitsToFloat(Integer.valueOf(sum2.trim(), 16)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }

}
