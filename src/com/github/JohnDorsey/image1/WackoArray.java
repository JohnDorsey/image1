package com.github.JohnDorsey.image1;

import java.util.BitSet;

/**
 * Created by John on 1/13/16.
 */
public class WackoArray {


    public byte[] up;
    public byte[] down;

    public WackoArray() {

    }

    public void writeUp(byte[]toWrite, byte max) {
        System.out.println("WackoArray writing up...");

        up = new byte[toWrite.length];


        int wackoLength;
        for (wackoLength = 8; wackoLength > 0; wackoLength--) {
            if ((max & (1 << wackoLength)) > 0) { wackoLength++; break; }
        }

        System.out.println("the wackoLength is " + wackoLength);

        BitSet nDown = new BitSet();

        for (int i = 0; i < toWrite.length; i++) {
            for (int bi = 0; bi < wackoLength; bi++) {
                nDown.set((i * wackoLength) + bi, getBit(toWrite[i], bi));
            }
        }



        byte addingNow = 0;
        for (int i = 0; i < nDown.length(); i++) {
            addingNow = setBit(addingNow, i%8, nDown.get(i));
            if (i%8 == 7) { down[i - (i%8)] = addingNow; addingNow = 0; }
            System.out.println(i + "=" + nDown.get(i));
        }




    }


    public void writeDown() {

    }


    public byte[] readUp() {
        return up;
    }

    public void readDown() {

    }


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




}





/*

    public void writeUp(byte[] toWrite) {

        up = new byte[toWrite.length];
        byte highest = 0;

        for (int i = 0; i < toWrite.length; i++) {
            up[i] = toWrite[i];
            highest = (byte) Math.max(highest, toWrite[i]);
        }

        int wackoLength

        for (int i = 8; i > 0; i--) {
            if ((highest & (1 << i)) == 1) { i++; break; }
        }





    }

    */