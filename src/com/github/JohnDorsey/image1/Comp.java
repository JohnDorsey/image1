package com.github.JohnDorsey.image1;

import java.util.BitSet;

/**
 * Created by John on 1/5/16.
 */
public class Comp {

    public int type;
    public int[][] content;
    public BitSet narrow_used;
    public int narrow_highest;

    public Comp(int nType, int[][] nContent) {
        type = nType;
        switch (type) {
            case 0: { content = nContent; } break;
            case 1: { content = nContent; narrow(); } break;
        }



    }

    public void narrow() {
        for (int[] column : content) {
            for (int value : column) {
                narrow_highest = Math.max(narrow_highest, value);
            }
        }
    }
    
    public int[][] read() {

        switch (type) {
            case 0: { return content; }
        }

    }

}
