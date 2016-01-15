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
            System.out.println(Lengthy.byteToString(toWrite[i]) + " IN " + i);
        }

        down = new byte[((toWrite.length * wackdLength) / 8 ) + 1];
        BitSet nDown = new BitSet();

        for (int i = 0; i < toWrite.length; i++) {
            for (int bi = 0; bi < wackdLength; bi++) {
                nDown.set((i * wackdLength) + bi, Lengthy.getBit(toWrite[i], bi));
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
            addingNow = Lengthy.setBit(addingNow, i % 8, nDown.get(i));
            //System.out.print("nDown" + i + "is" + nDown.get(i));
            //}
            if ((i+1) % 8 == 0) {
                System.out.println(Lengthy.byteToString(addingNow) + " ADDING TO INDEX " + (i / 8));
                down[(i) / 8] = addingNow;
                addingNow = 0;
            }
        }

        for (int i = 0; i < down.length; i++) {
            System.out.println(Lengthy.byteToString(down[i]) + " OUT " + i);
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

        down = new byte[toWrite.length];
        for (int i = 0; i < toWrite.length; i++) { down[i] = toWrite[i]; }
        up = new byte[(down.length * 8) / wackdLength];

        BitSet nUp = new BitSet();
        for (int i = 0; i < toWrite.length; i++) {
            for (int bi = 0; bi < 8; bi++) {
                nUp.set((i * 8) + bi, Lengthy.getBit(toWrite[i], bi));
            }
        }

        for (int i = 0; i < up.length; i++) {
            for (int bi = 0; bi < wackdLength; bi++) {
                up[i] = Lengthy.setBit(up[i], bi, nUp.get((i * wackdLength) + bi));
            }
        }

        for (int i = 0; i < up.length; i++) {
            System.out.println(Lengthy.byteToString(up[i]) + " writeDown up");
        }


    }


    public byte[] readUp() {
        return up;
    }

    public byte[] readDown() {
        return down;
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