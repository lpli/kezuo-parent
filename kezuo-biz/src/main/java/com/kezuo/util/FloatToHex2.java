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
public class FloatToHex2 {

    public static void main(String[] args) {
      
        try {
            String sum = "66f46146";
            System.out.print("正向 to float : ");
            System.out.println(Float.intBitsToFloat(Integer.valueOf(sum.trim(), 16)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sum2 = "4661f466";
            System.out.print("反向 to float : ");
            System.out.println(Float.intBitsToFloat(Integer.valueOf(sum2.trim(), 16)));
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

}
