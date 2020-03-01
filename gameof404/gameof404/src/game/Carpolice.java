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
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Admin
 */
public class Carpolice extends JPanel {

    BufferedImage carpolice;
    double x = 0, y = 0;
    double goc = 0;
    double gocmt = 0;
    double vantoc = 3;
    double vantocquay = 1.5;
    Random rd = new Random();
    Random rd2 = new Random();
    Cardraw cd = new Cardraw();
    ArrayList<diem> dot;

    public Carpolice() {
        if (rd.nextBoolean()) {
            x = rd2.nextInt(1920);

        } else {

            y = rd2.nextInt(1080);
        }
    }

//    public void randomCar() {
//        Random rd = new Random();
//        if (check1 == false) {
//            x1 = rd.nextInt(1920);
//            y1 = rd.nextInt(1080);
////            check1 = true;
//        }
//
//    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        try {

            carpolice = ImageIO.read(new File("D:\\gameof404\\gameof404\\src\\image\\car_black_small_5.png"));
            g2d.translate(x, y);
            g2d.rotate(Math.toRadians(gocmt));
            g2d.drawImage(carpolice, -32, -20, 64, 40, null);
        } catch (IOException ex) {
            Logger.getLogger(Carpolice.class.getName()).log(Level.SEVERE, null, ex);
        }
        setdot();
//        for (diem s : dot) {
//            System.out.println(s.x + "-" + s.y);
//        }
        System.out.println(goc);
    }

    public void VanToc1() {
        setGoc();
        rotate();
        x = x + vantoc * Math.cos(Math.toRadians(goc));
        y = y + vantoc * Math.sin(Math.toRadians(goc));

    }

    public void setGoc() { // cd.x cd.y
//        double khoangcanh = Math.sqrt((x - cd.x) * (x - cd.x) + (y - cd.y) * (y - cd.y));
//
//        if (y > cd.y) {
//            gocmt = Math.toDegrees(-Math.acos((cd.x - x) / khoangcanh));
//        }
//        if (y < cd.y) {
//            gocmt = Math.toDegrees(Math.acos((cd.x - x) / khoangcanh));
//        }
//        if (y == cd.y && x == cd.x) {
//            gocmt = Math.toDegrees(Math.PI / 2);
//        }
//        if (y == cd.y) {
//            gocmt = 0;
//        }
//        System.out.println(gocmt);
        
        
        
        if (((cd.y - y) > 0) && ((cd.x - x) > 0)) {
            gocmt = Math.toDegrees(Math.atan((cd.y - y) / (cd.x - x)));
        } else if (((cd.y - y) <= 0) && ((cd.x - x) < 0)) {
            gocmt = Math.toDegrees(Math.atan((cd.y - y) / (cd.x - x))) - 180;
        } else if (((cd.y - y) <= 0) && ((cd.x - x) > 0)) {
            gocmt = Math.toDegrees(Math.atan((cd.y - y) / (cd.x - x)));
        } else if (((cd.y - y) > 0) && ((cd.x - x) < 0)) {
            gocmt = Math.toDegrees(Math.atan((cd.y - y) / (cd.x - x))) + 180;
        } else if (((cd.y - y) > 0) && ((cd.x - x) == 0)) {
            gocmt = 90;
        } else if (((cd.y - y) <= 0) && ((cd.x - x) == 0)) {
            gocmt = 270;
        }
    }

    public void rotate() {
        if(goc > 180)
        {
            goc = -180;
        }
        if(goc < -180)
        {
            goc = 180;
        }
        if ((goc-gocmt >= 0)&&(goc-gocmt <= 180)) {
            goc -= vantocquay;
        }
        else if ((goc-gocmt <= 0)&&(goc-gocmt >= -180)) {
            goc += vantocquay;
        }
        else if (goc-gocmt < -180)
        {
            goc -= vantocquay;
        }
        else if (goc-gocmt > 180)
        {
            goc += vantocquay;
        }
        else
        {
            goc+= vantocquay;
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
