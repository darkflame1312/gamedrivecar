/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import static game.Cardraw.x;
import static game.Cardraw.y;
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
 * @author Admin
 */
public class Background extends JPanel{
    private BufferedImage bg;
    double x, y;

    public Background(double x, double y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g.create();
        try {
    
            bg = ImageIO.read(new File("D:\\gameof404\\gameof404\\src\\image\\medievalTile_57.png"));  
            g2d.drawImage(bg,(int) x , (int) y, 1920, 1080, null);
        } catch (IOException ex) {
            Logger.getLogger(Cardraw.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
