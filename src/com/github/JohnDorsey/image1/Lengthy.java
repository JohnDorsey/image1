package com.github.JohnDorsey.image1;

/**
 * Created by John on 1/15/16.
 */
public class Lengthy {




    public static byte setBit(byte input, int index, boolean newValue) {
        if (getBit(input, index) != newValue) {
            return toggleBit(input, index);
        } else { return input; }
    }

    public static byte toggleBit(byte input, int index) {
        return (byte) (input ^ (1 << index));
    }

    public static boolean getBit(byte input, int index) {
        return ((input & (1 << index)) > 0);
    }

    public static String byteToString(byte input) {
        String result = "";
        for (int i = 0; i < 8; i++) {
            result = ((getBit(input, i))? "1" : "0") + result;
        }
        return result;
    }

}
