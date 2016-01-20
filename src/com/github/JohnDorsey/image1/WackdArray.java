package com.github.JohnDorsey.image1;

import java.util.BitSet;

/**
 * Created by John on 1/13/16.
 */
public class WackdArray {


    //terms invented on the spot:
    //wacko: a number with an unusual range that wastes certain values when stored pure
    //wackd: a wacko that simply has an unusual number of bits, or can be stored with less bits
        //storing 5, 7, 3, 4, as 101111011100 instead of 00000101000001110000001100000100
    //wackp: a wacko that has an unusual precision, which may be combined with other wackps of known precision by multiplication
        //storing 5, 7, 3, 4, as the number 4 + (3*(7)) + (7*(7^2)) + (5*(7^3)) with no waste

    public byte[] up; //the decoded, displayable data.
    public byte[] down; //the encoded, savable data.

    public WackdArray() {
    }

    public void writeUp(byte[]toWrite, byte max) { //this does math to find number of bits used in a wackd of a certain max value, then redirects to real method
        int wackdLength;
        for (wackdLength = 8; wackdLength > 0; wackdLength--) {
            if ((max & (1 << wackdLength)) > 0) { wackdLength++; break; }
        }

        writeUp(toWrite, wackdLength);
    }

    public void writeUp(byte[] toWrite, int wackdLength) { //this determines down based on a declaration of up
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
        for (int i = 0; i < (nDown.length() + 7); i++ ) {
            addingNow = Lengthy.setBit(addingNow, i % 8, nDown.get(i));
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


    public void writeDown(byte[] toWrite, byte max) { //this does math to determine the number of bits in a number of given max value, then redirects to real method
        int wackdLength;
        for (wackdLength = 8; wackdLength > 0; wackdLength--) {
            if ((max & (1 << wackdLength)) > 0) { wackdLength++; break; }
        }

        writeDown(toWrite, wackdLength);
    }

    public void writeDown(byte[] toWrite, int wackdLength) { // this determines up based on a declaration of down

        down = new byte[toWrite.length];
        for (int i = 0; i < toWrite.length; i++) { down[i] = toWrite[i]; }
        up = new byte[(down.length * 8) / wackdLength]; //the number of bytes in up will be the number of times the length of a wacko can fit into the provided array

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