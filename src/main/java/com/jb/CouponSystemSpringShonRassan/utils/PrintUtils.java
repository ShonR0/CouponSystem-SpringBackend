package com.jb.CouponSystemSpringShonRassan.utils;

public class PrintUtils {
    private static final int SIZE = 100;
    public static void title(String content){
        int len = content.length();
        int left = (SIZE - len) / 2;
        System.out.print("@".repeat(left));
        System.out.print(" " + content + " ");
        System.out.println("@".repeat(left));
    }
}
