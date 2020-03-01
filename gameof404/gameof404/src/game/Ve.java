/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
public class Ve extends JPanel implements Runnable, MouseListener {

    Rectangle playBtn = new Rectangle(850, 300, 200, 90);
    Rectangle guidBtn = new Rectangle(850, 500, 200, 90);
    Rectangle quitBtn = new Rectangle(850, 700, 200, 90);

    static int call = 0;
    ArrayList<Carpolice> police = new ArrayList<>();
    int timed = 1600; // thời gian địch xuất hiện
    int timevc = 1600; // thời gian vật cản xuất hiện
    Random rd = new Random();
    Random rd2 = new Random();
    int ran;
    Cardraw cd = new Cardraw();
    Carpolice cp = new Carpolice();
    ArrayList<Background> bach_go_rao = new ArrayList<>();
    ArrayList<Vatcan> trap = new ArrayList<>();

    double cam_x = 1920 / 2; // điều chỉnh toàn bộ vật thể sao cho người chơi ở giữa màn hình
    double cam_y = 1080 / 2;

    public Ve() {
        Thread threadVe = new Thread(this);
        threadVe.start();
        trap.add(new Vatcan(100, 300));
        trap.add(new Vatcan(100, 200));
        trap.add(new Vatcan(800, 300));
        bach_go_rao.add(new Background(0, 0));
        bach_go_rao.add(new Background(-1920, -1080));
        bach_go_rao.add(new Background(0, -1080));
        bach_go_rao.add(new Background(1920, -1080));
        bach_go_rao.add(new Background(1920, 0));
        bach_go_rao.add(new Background(1920, 1080));
        bach_go_rao.add(new Background(0, 1080));
        bach_go_rao.add(new Background(-1920, 1080));
        bach_go_rao.add(new Background(-1920, 0));
    }

    @Override
    public void paint(Graphics g) {
        // ve nen
        for (Background bg : bach_go_rao) {
            bg.paint(g);
        }
        switch (call) {
            case 0:
                menu(g);
                break;
            case 1:
                gameplay(g);
                break;
            case 2:
                help(g);
                break;
            case 3:
                gameover(g);
                break;
        }
    }

    public void menu(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Font f0 = new Font("Arial", Font.BOLD, 80); // TODO Auto-generated catch block
        g2.setFont(f0);
        g2.setColor(Color.orange);
        g2.drawString("CU-CHUOI", 730, 200);
        Font f1 = new Font("Arial", Font.BOLD, 50);
        g2.setFont(f1);
        g2.setColor(Color.white);
        g2.drawString("Start", 890, 370);
        g2.draw(playBtn);
        g2.drawString("Help", 890, 570);
        g2.draw(guidBtn);
        g2.drawString("Quit", 890, 770);
        g2.draw(quitBtn);
    }

    public void help(Graphics g) {

    }

    public void gameover(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        Font f0 = new Font("Arial", Font.BOLD, 80);
        g2.setFont(f0);
        g2.setColor(Color.orange);
        g2.drawString("GAMEOVER", 730, 200);
        Font f1 = new Font("Arial", Font.BOLD, 50);
        g2.setFont(f1);
        g2.setColor(Color.white);
        g2.drawString("Score:" + cd.diem, 890, 370);
    }

    public void gameplay(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        //điều khiển cam đuổi theo người chơi
        cam_x = cd.x - (1920 / 2);
        cam_y = cd.y - (1080 / 2);
        cd.x -= cam_x;
        cd.y -= cam_y;
        for (Carpolice d : police) {
            d.x -= cam_x;
            d.y -= cam_y;
        }
        for (Vatcan tr : trap) {
            tr.x -= cam_x;
            tr.y -= cam_y;
        }
        for (Background bg : bach_go_rao) {
            bg.x -= cam_x;
            bg.y -= cam_y;
            if (bg.x < -1920) {
                bg.x = 1920;
            }
            if (bg.y < -1080) {
                bg.y = 1080;
            }
            if (bg.x > 1920) {
                bg.x = -1920;
            }
            if (bg.y > 1080) {
                bg.y = -1080;
            }
        }

        //vẽ vật cản
        for (Vatcan tr : trap) {
            tr.paint(g);
            if ((tr.x < -1920) || (tr.x > 3840) || (tr.y < -1080) || (tr.y > 2160)) //thêm điều kiện xóa vật cản
            {
                trap.remove(tr);
            }
        }
        if (timevc % 1600 == 0) {
            ran = rd2.nextInt(7);
            switch (ran) {
                case 0:
                    trap.add(new Vatcan(rd.nextInt(1920) - 1920, rd.nextInt(1080) - 1080));
                    break;
                case 1:
                    trap.add(new Vatcan(rd.nextInt(1920), rd.nextInt(1080) - 1080));
                    break;
                case 2:
                    trap.add(new Vatcan(rd.nextInt(1920) + 1920, rd.nextInt(1080) - 1080));
                    break;
                case 3:
                    trap.add(new Vatcan(rd.nextInt(1920) + 1920, rd.nextInt(1080)));
                    break;
                case 4:
                    trap.add(new Vatcan(rd.nextInt(1920) + 1920, rd.nextInt(1080) + 1080));
                    break;
                case 5:
                    trap.add(new Vatcan(rd.nextInt(1920), rd.nextInt(1080) + 1080));
                    break;
                case 6:
                    trap.add(new Vatcan(rd.nextInt(1920) - 1920, rd.nextInt(1080) + 1080));
                    break;
                case 7:
                    trap.add(new Vatcan(rd.nextInt(1920) - 1920, rd.nextInt(1080)));
                    break;
            }
            timevc = 0;
        }
        //vẽ người chơi
        cd.paint(g);
        cd.VanToc();
        cd.setdot(); // tao diem va cham cho nguoi choi
        // vẽ địch
        if (timed % 160 == 0) {
            police.add(new Carpolice()); // them quan dich
            timed = 0;
        }

        for (Carpolice d : police) { // dich di chuyen
            d.VanToc1();
        }
        for (Carpolice d : police) {
            d.paint(g); // ve quan dich
            for (Carpolice d1 : police) {
                if (police.indexOf(d) != police.indexOf(d1)) {
                    d1.setdot();  // tao diem va cham cho quan dich
                    for (diem di : d1.dot) {
                        if (d.kiemtravacham(new diem(di.x, di.y))) { //quan dich cham vao nhau thi bien mat
                            police.remove(d);
                            police.remove(d1);
                            break;
                        }
                    }
                }
            }
        }
        // vẽ vật cản
        for (Carpolice d : police) {
            for (Vatcan tr : trap) {
                tr.setdot(); // vẽ điểm va chạm
                for (diem di : tr.dot) {
                    if (d.kiemtravacham(di)) { // nếu địch đâm vào vật cản thì biến mất
                        police.remove(d);
                    }
                    if (cd.kiemtravacham(di)) { // nếu mình đàm vào vật cản thì gameover
                        call = 3;
                    }
                }
            }
            for (diem di : cd.dot) {
                if (d.kiemtravacham(di)) {
                    call = 3;
                }
            }
        }

        //biến thời gian
        timevc++;
        timed++;
    }

    @Override
    public void run() {
        while (true) {
            repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ve.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mx = e.getXOnScreen();
        int my = e.getYOnScreen();
        if (call == 0) {

            if (mx >= 850 && mx <= 1050) {
                if (my >= 300 && my <= 390) {
                    call = 1;
                }
            }
            if (mx >= 850 && mx <= 1050) {
                if (my >= 500 && my <= 590) {
                    call = 2;
                }
            }
            if (mx >= 850 && mx <= 1050) {
                if (my >= 700 && my <= 790) {
                    System.exit(1);
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
