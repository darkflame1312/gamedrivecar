/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Cardraw extends JPanel {

    private BufferedImage car;
    public static double x = 900, y = 500;
    private static double goc = 0, vantoc = 4.5;
    ArrayList<diem> dot;
    static int diem = 0;
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        try {

            car = ImageIO.read(new File("D:\\gameof404\\gameof404\\src\\image\\car_yellow_small_1.png"));
            g2d.translate(x, y);
            g2d.rotate(Math.toRadians(goc));
            g2d.drawImage(car, -32, -20, 64, 40, null); // getWidth()*0.1
        } catch (IOException ex) {
            Logger.getLogger(Cardraw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VanToc() {

        if ((x >= 0) && (x <= 1920) && (y >= 0) && (y <= 1080)) {
            x = x + vantoc * Math.cos(Math.toRadians(goc));
            y = y + vantoc * Math.sin(Math.toRadians(goc));
        } else if (x < 0) {
            x = 1920;
        } else if (x > 1920) {
            x = 0;
        } else if (y < 0) {
            y = 1080;
        } else if (y > 1080) {
            y = 0;
        }
    }

    public static void rotateL() {
        goc -= 22.5;
    }

    public static void rotateR() {
        goc += 22.5;
    }

    public boolean Mien(diem a, diem b, diem c) {
        double gt = (c.x - a.x) * (b.y - a.y) - (c.y - a.y) * (b.x - a.x);
        if (gt < 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean kiemtravacham(diem xet) {
        boolean kq = true;
        int i = 0;
        boolean gtbl;

        for (diem d : dot) {
            //xét điểm
            int j = (i + 1) % 4;
            //kiểm tra miền
            gtbl = Mien(d, dot.get(j), xet);
            for (int k = 0; k < 3; k++) {
                if ((k != i) && (k != j)) {
                    if (gtbl != Mien(dot.get(i), dot.get(j), dot.get(k))) {
                        kq = false;
                        break;
                    }
                }
            }

            i++;
            if (i == 4) {
                break;
            }
        }
        return kq;
    }

    public void setdot() {
        double gocao;
        ArrayList<diem> dottemp = new ArrayList<>();
        dot = new ArrayList<>();
        dottemp.add(new diem(-32, -20));
        dottemp.add(new diem(32, -20));
        dottemp.add(new diem(32, 20));
        dottemp.add(new diem(-32, 20));
        for (diem d : dottemp) {
            if ((d.y > 0) && (d.x > 0)) {
                gocao = Math.toDegrees(Math.atan(d.y / d.x));
            } else if ((d.y <= 0) && (d.x < 0)) {
                gocao = Math.toDegrees(Math.atan(d.y / d.x)) + 180;
            } else if ((d.y <= 0) && (d.x > 0)) {
                gocao = Math.toDegrees(Math.atan(d.y / d.x));
            } else if ((d.y > 0) && (d.x < 0)) {
                gocao = Math.toDegrees(Math.atan(d.y / d.x)) + 180;
            } else if ((d.y > 0) && (d.x == 0)) {
                gocao = 90;
            } else {
                gocao = 270;
            }
            gocao += goc;
            dot.add(new diem((sqrt(pow(d.y, 2) + pow(d.x, 2)) * Math.cos(Math.toRadians(gocao))) + x, sqrt(pow(d.y, 2) + pow(d.x, 2)) * Math.sin(Math.toRadians(gocao)) + y));
        }
    }
}
