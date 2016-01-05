package com.github.JohnDorsey.image1;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class Main {


    static BufferedImage img = new BufferedImage(512, 512, BufferedImage.TYPE_INT_RGB);
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
        for (int x = 0; x < 512; x++) {
            for (int y = 0; y < 512; y++) {
                img.setRGB(x, y, (x * y) * (x + y + 1) + x + y);
            }
        }
        show (img);
        //g.drawImage(img, 0, 0, 256, 256, null);
        //window.add(g);
        //window.update(g);

    }

    public static void show(BufferedImage toShow) {
        g.drawImage(toShow, 0, 0, 512, 512, null);
        //window.update(g);
    }

}
