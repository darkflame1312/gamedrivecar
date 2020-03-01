/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Hieu
 */
public class ManHinh extends JFrame implements Runnable {

    Ve ve = new Ve();
    Cardraw cd = new Cardraw();
    Carpolice cp = new Carpolice();

    boolean left = false;
    boolean right = false;

    public ManHinh() {
        Thread thr = new Thread(this);
        thr.start();
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int x = gd.getDisplayMode().getWidth();
        int y = gd.getDisplayMode().getHeight();
        setSize(x, y);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        add(ve);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_A) {
//                    cd.rotateL();
                    left = true;
                }
                if (ke.getKeyCode() == KeyEvent.VK_D) {
                    right = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_A) {
//                    cd.rotateL();
                    left = false;
                }
                if (ke.getKeyCode() == KeyEvent.VK_D) {
                    right = false;
                }
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ve.mousePressed(e);
            }
        });
    }

    public static void main(String[] args) {
        ManHinh mh = new ManHinh();
    }

    @Override
    public void run() {
        while (true) {
            if (left) {
                cd.rotateL();
            }
            if (right) {
                cd.rotateR();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(ManHinh.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
