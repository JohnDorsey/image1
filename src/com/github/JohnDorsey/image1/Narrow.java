package com.github.JohnDorsey.image1;

import java.util.BitSet;

/**
 * Created by John on 1/7/16.
 */
public class Narrow {

    public byte[][] up;
    public byte[] down;

    public byte range;
    public byte lowest;
    public BitSet used;

    public Narrow() {

    }

    public void writeDown(byte[] toWrite) {

    }

    public void writeUp(byte[][] toWrite) {

        up = new byte[toWrite.length][toWrite[0].length];

        range = toWrite[0][0];
        lowest = toWrite[0][0];
        for (int i = 0; i < toWrite.length; i++) {
            for (int ii = 0; ii < toWrite[0].length; ii++) {
                up[i][ii] = toWrite[i][ii];
                range = (byte) Math.max(range, toWrite[i][ii]);
                lowest = (byte) Math.min(lowest, toWrite[i][ii]);
            }
        }
        for (int i = 0; i < toWrite.length; i++) {
            for (int ii = 0; ii < toWrite[0].length; ii++) {
                toWrite[i][ii] -= lowest;
                System.out.print(toWrite[i][ii] + ",");
            }
            System.out.print("\n");
        }
        range -= lowest;

        used = new BitSet();

        for (byte te = 1; te < range; te++) {
            test: for (int i = 0; i < toWrite.length; i++) {
                for (int ii = 0; ii < toWrite[0].length; ii++) {
                   if (toWrite[i][ii] == te) { used.set(te - 1, true); break test; }
                }
            }
        }

        System.out.println(lowest + " " + range + " " + used.toString());

        System.out.print(lowest + " " + range + " " + (byte) used.length() );
        for (int i = 0; i < used.length(); i++) {
            System.out.print(" " + used.get(i));
        }
        System.out.print("\n");

        down[0] = lowest;
        down[1] = range;
        

    }

    //public byte[] readDown() {

    //}

    //public int[][] readUp() {

    //}



}
