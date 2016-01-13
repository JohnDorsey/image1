package com.github.JohnDorsey.image1;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Main {


    static BufferedImage img = new BufferedImage(64, 64, BufferedImage.TYPE_INT_RGB);
    static JFrame window = new JFrame();
    static Graphics g;// = img.getGraphics();
    //frame.setVisible(true);
    //Graphics g = frame.getGraphics();

    public static void main(String[] args) {
        window.setSize(512, 512);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        g = window.getGraphics();
        //img.setRGB(64, 64, 192);
        show(img);
        //for (int z = 1; z < 16; z += 16) {
            for (int x = 0; x < 64; x++) {
                for (int y = 0; y < 64; y++) {
                    //img.setRGB(x + 256, y + 256, (x * (x + z)) + (y * (y + z)) + (z + x + y));
                    img.setRGB(x, y, ((x + y + 15) % 30) + 145);
                }
            }
            show(img);
        //}



        WackoArray testwa = new WackoArray();
        testwa.writeUp(new byte[] {0,4,5,3,3}, (byte) 5);



        int cp = 0;
        byte[][] toCompressR = new byte[img.getWidth()][img.getHeight()];
        byte[][] toCompressG = new byte[img.getWidth()][img.getHeight()];
        byte[][] toCompressB = new byte[img.getWidth()][img.getHeight()];
        for (int i = 0; i < img.getWidth(); i++) {
            for (int ii = 0; ii < img.getHeight(); ii++) {
                cp = img.getRGB(i, ii);
                toCompressB[i][ii] = (byte) (cp % 65536);
                img.setRGB(i, ii, cp - toCompressB[i][ii]);

                cp = img.getRGB(i, ii);
                toCompressG[i][ii] = (byte) (cp % 256);
                img.setRGB(i, ii, cp - toCompressG[i][ii]);

                cp = img.getRGB(i, ii);
                toCompressR[i][ii] = (byte) (cp);
                img.setRGB(i, ii, cp - toCompressR[i][ii]);
            }
        }

        Narrow testNarrowR = new Narrow();
        testNarrowR.writeUp(toCompressR);
        Narrow testNarrowG = new Narrow();
        testNarrowR.writeUp(toCompressG);
        Narrow testNarrowB = new Narrow();
        testNarrowR.writeUp(toCompressB);
        //testNarrow.writeUp(new byte[][] {{4, 6, 6, 18}, {6, 6, 18, 16}, {4, 6, 16, 18}, {18, 16, 6, 4} });




        //g.drawImage(img, 0, 0, 256, 256, null);
        //window.add(g);
        //window.update(g);

    }

    public static void show(BufferedImage toShow) {
        g.drawImage(toShow, 0, 0, 512, 512, null);
        //window.update(g);
    }

}
