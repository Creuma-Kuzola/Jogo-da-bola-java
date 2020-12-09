/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JogoBola;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;

/**
 *
 * @author creuma
 */
public class PainelJogoBola extends JPanel {
 
    Ellipse2D bola;
    @Override
    public void paintComponent(Graphics g){
        
       super.paintComponent(g);
       Graphics2D g2d = (Graphics2D) g;
       pintarEstrada(g2d);
       pintarBola(g2d);
       
    }
    
    public void pintarEstrada(Graphics2D g2d){
       
       g2d.setPaint(new Color(0,0,0));
       g2d.fillRect(0,277,750,80);
    }
    
    public void pintarBola(Graphics2D g2d){
        
        bola = new Ellipse2D.Double(20,290,40,40); 
       g2d.setPaint(Color.YELLOW);
       g2d.fill(bola);
    }
    
}
