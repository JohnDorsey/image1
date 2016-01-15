package com.github.JohnDorsey.image1;

import java.util.BitSet;

/**
 * Created by John on 1/13/16.
 */
public class WackdArray {


    public byte[] up;
    public byte[] down;

    public WackdArray() {

    }

    public void writeUp(byte[]toWrite, byte max) {
        int wackdLength;
        for (wackdLength = 8; wackdLength > 0; wackdLength--) {
            if ((max & (1 << wackdLength)) > 0) { wackdLength++; break; }
        }

        writeUp(toWrite, wackdLength);
    }

    public void writeUp(byte[]toWrite, int wackdLength) {
        System.out.println("WackdArray writing up...");

        up = new byte[toWrite.length];

        System.out.println("the wackdLength is " + wackdLength);


        for (int i = 0; i < toWrite.length; i++) {
            System.out.println(byteToString(toWrite[i]) + " IN " + i);
        }

        down = new byte[((toWrite.length * wackdLength) / 8 ) + 1];
        BitSet nDown = new BitSet();

        for (int i = 0; i < toWrite.length; i++) {
            for (int bi = 0; bi < wackdLength; bi++) {
                nDown.set((i * wackdLength) + bi, getBit(toWrite[i], bi));
            }
        }

        byte addingNow = 0;
        //for (int i = 0; i <= nDown.length(); i++) {
        //    addingNow = setBit(addingNow, i%8, nDown.get(i));
        //    if (i%8 == 7) { down[i / 8] = addingNow; addingNow = 0; }
        //    System.out.println(i + "=" + nDown.get(i));
        //}
        for (int i = 0; i < (nDown.length() + 7); i++ ) {
            //for (int ii = 0; ii < 8; ii++) {
            addingNow = setBit(addingNow, i % 8, nDown.get(i));
            //System.out.print("nDown" + i + "is" + nDown.get(i));
            //}
            if ((i+1) % 8 == 0) {
                System.out.println(byteToString(addingNow) + " ADDING TO INDEX " + (i / 8));
                down[(i) / 8] = addingNow;
                addingNow = 0;
            }
        }

        for (int i = 0; i < down.length; i++) {
            System.out.println(byteToString(down[i]) + " OUT " + i);
        }

    }


    public void writeDown(byte[] toWrite, byte max) {
        int wackdLength;
        for (wackdLength = 8; wackdLength > 0; wackdLength--) {
            if ((max & (1 << wackdLength)) > 0) { wackdLength++; break; }
        }

        writeDown(toWrite, wackdLength);
    }

    public void writeDown(byte[] toWrite, int wackdLength) {

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

    public static String byteToString(byte input) {
        String result = "";
        for (int i = 0; i < 8; i++) {
            result = ((getBit(input, i))? "1" : "0") + result;
        }
        return result;
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