package com.github.JohnDorsey.image1;

import java.io.ByteArrayOutputStream;
import java.util.BitSet;

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

    public static String bytesToString(byte[] input) {
        String result = "";
        for (byte cb : input) {
            result += byteToString(cb) + ",";
        }
        return result;
    }

    public static String byteToString(byte input) {
        String result = "";
        for (int i = 0; i < 8; i++) {
            result = ((getBit(input, i))? "1" : "0") + result;
        }
        return result;
    }

    public static byte[] bitSetToBytes(BitSet input) {
        byte[] result = new byte[(input.length()+7) / 8];
        byte addingNow = 0;
        for (int i = 0; i < (input.length() + 7); i++ ) {
            addingNow = Lengthy.setBit(addingNow, i % 8, input.get(i));
            if ((i+1) % 8 == 0) {
                result[(i) / 8] = addingNow;
                addingNow = 0;
            }
        }
        return result;
    }

    public static BitSet bytesToBitSet(byte[] input) {
        BitSet result = new BitSet();
        for (int i = 0; i < input.length; i++) {
            for (int bi = 0; bi < 8; bi++) {
                result.set((i * 8) + bi, getBit(input[i], bi));
            }
        }
        return result;
    }

    public static byte[] addArrays(byte[] array1, byte[] array2) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            outputStream.write(array1);
            outputStream.write(array2);
        } catch (java.io.IOException ioe) { System.out.println("Langthy.addArrays(byte[], byte[]): IOException: " + ioe); }
        return outputStream.toByteArray();
    }





}
