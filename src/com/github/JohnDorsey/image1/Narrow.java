package com.github.JohnDorsey.image1;

import com.sun.tools.javac.util.ArrayUtils;
//import org.apache.commons.lang3;

import java.util.BitSet;

/**
 * Created by John on 1/7/16.
 */
public class Narrow {

    public byte[] up; //decoded, displayable data
    public byte[] down; //encoded, savable data.
                        //both are always stored while the program is running. it is impossible to change one without also changing the other.
    public WackdArray contentWD = new WackdArray();

    public byte range; //the range of values present
    public byte lowest; //the lowest value present
    public BitSet used; //an index of whether or not each value is present, for all values within the range.

    public Narrow() {
    }

    public void writeDown(byte[] toWrite) { //determines decoded ("up") form based on declaration of encoded ("down") form
        down = new byte[toWrite.length]; //actual encoded version was just declared, no need to do more work later

        System.out.println("       Narrow writing toWrite: " + Lengthy.bytesToString(toWrite));

        byte[] downt = new byte[toWrite.length - 2];

        for (int i = 0; i < toWrite.length; i++) {
            down[i] = toWrite[i];
        }

        System.out.println("          Narrow writing down: " + Lengthy.bytesToString(toWrite) + " identical?");

        lowest = down[0];
        range = down[1];

        for (int i = 2; i < range; i++) {
            downt[i - 2] = down[i];
        }

        used = Lengthy.bytesToBitSet(downt);

        byte[] downWD = new byte[(down.length - range) - 2];

        for (int i = range + 2; i < down.length; i++) {
            downWD[i - range - 2] = down[i];
        }

        contentWD.writeDown(downWD, range);

        up = contentWD.readUp();

        for (int val = 0; val < used.length(); val++) {
            for (int i = 0; i < up.length; i++) {
                if (up[i] == val && (!used.get(val))) {
                    up[i]++;
                }
            }
        }

        for (int i = 0; i < up.length; i++) {
            //up[i] += lowest;
        }

        System.out.print("Narrow writeDown resulting up: " + Lengthy.bytesToString(up));


    }



    public void writeUp(byte[] toWrite) { //determines encoded ("down") form based on a declaration of decoded ("up") form



        up = new byte[toWrite.length];

        System.out.println("            Narrow writing up: " + Lengthy.bytesToString(toWrite));

        range = toWrite[0];
        lowest = toWrite[0];

        for (int i = 0; i < toWrite.length; i++) { //find the range and lowest value, as well as copy into the up array...
            up[i]= toWrite[i]; //...actual decoded version was just declared, no need to do more work later
            range = (byte) Math.max(range, toWrite[i]);
            lowest = (byte) Math.min(lowest, toWrite[i]);
        }

        System.out.println("            Narrow writing up: " + Lengthy.bytesToString(up) + " Identical?");


        for (int i = 0; i < toWrite.length; i++) {
            toWrite[i] -= lowest;
            System.out.print(toWrite[i] + ",");
        }

        System.out.print("\n");

        range -= lowest; //up until this point, range had been equal to the highest value present.

        used = new BitSet();

        for (byte te = 1; te < range; te++) { //this populates used, the record of whether each value between the lowest and highest is present.
            test: for (int i = 0; i < toWrite.length; i++) { //this tests for a value
                if (toWrite[i] == te) { used.set(te - 1, true); break test; }
            }
        }

        //System.out.println(lowest + " " + range + " " + used.toString());
        //System.out.print(lowest + " " + range + " " + (byte) used.length() );
        //for (int i = 0; i < used.length(); i++) {
        //    System.out.print(" " + used.get(i));
        //}
        //System.out.print("\n");

        byte[] abwUsed = Lengthy.bitSetToBytes(used);

        down = new byte[abwUsed.length + 2];
        down[0] = lowest;
        down[1] = range;
        for (int i = 0; i < abwUsed.length; i++) {
            down[i + 2] = abwUsed[i];
        }


        //System.out.println((((range+7) / 8) + 2 )+ "should be" + down.length);

        contentWD.writeUp(toWrite, range);
        down = Lengthy.addArrays(down, contentWD.readDown());

        System.out.println();
        for (byte cb : down) {
            System.out.print(Byte.toUnsignedInt(cb) + " ");
        }

        System.out.println("\n" + "Narrow writeUp resulting down: " + Lengthy.bytesToString(down));

    }

    public byte[] readDown() { //returns encoded form
        return down;
    }

    public byte[] readUp() { //returns decoded form
        return up;
    }



}
