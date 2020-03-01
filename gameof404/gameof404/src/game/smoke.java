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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Hieu
 */
public class smoke extends JPanel {

    double x, y;
    int timebh = 15; // thời gian mất khói
    BufferedImage icon;
    double size;

    public smoke(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            icon = ImageIO.read(new File(""));
            g2.translate(x, y);
            g2.drawImage(icon, (int)(-size/2), (int)(-size/2), (int) size, (int) size, null);
            size -= 2;
            timebh--;
        } catch (IOException ex) {
            Logger.getLogger(smoke.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
