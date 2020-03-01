/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Vatcan extends JPanel {

    private BufferedImage vc;
    double x, y;
    double goc = 0;
    Random rd = new Random();
    Random rd2 = new Random();
    ArrayList<diem> dot;

    public Vatcan(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            g2d.translate(x, y);
            vc = ImageIO.read(new File("D:\\gameof404\\gameof404\\src\\image\\mapTile_049.png"));
            g2d.drawImage(vc, -50, -50, 100, 100, null);
        } catch (IOException ex) {
            Logger.getLogger(Vatcan.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            int j = (i + 1) % 7;
            //kiểm tra miền
            gtbl = Mien(d, dot.get(j), xet);
            for (int k = 0; k < 6; k++) {
                if ((k != i) && (k != j)) {
                    if (gtbl != Mien(dot.get(i), dot.get(j), dot.get(k))) {
                        kq = false;
                        break;
                    }
                }
            }

            i++;
            if (i == 7) {
                break;
            }
        }
        return kq;
    }

    public void setdot() {
        double gocao;
        ArrayList<diem> dottemp = new ArrayList<>();
        dot = new ArrayList<>();
        dottemp.add(new diem(-3,-31));
        dottemp.add(new diem(11,-31));
        dottemp.add(new diem(27,-5));
        dottemp.add(new diem(30,20));
        dottemp.add(new diem(0,25));
        dottemp.add(new diem(-29,20));
        dottemp.add(new diem(-22,-16));
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
