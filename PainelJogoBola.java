package JogoBola;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

/**
 *
 * @author creuma
 */
public class PainelJogoBola extends JPanel implements ActionListener, Runnable {

    Bola bolaPrincipal,circuloPontos,circuloRecuarPosX;
    Triangulo triangulo;
    Quadrado quadrado;
    Teclado teclado = new Teclado();
    Thread thread;
    AffineTransform atf ;

    public PainelJogoBola() {
      
        new Thread(this).start();
        
        bolaPrincipal = new Bola(20, 290, 40, 40);
        circuloPontos = new Bola(80,290,40,40);
        quadrado = new Quadrado(130,290,40,40);
        circuloRecuarPosX = new Bola(180,290,40,40);
        
        this.setFocusable(true);
        addKeyListener(teclado);
      
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 277, 770, 80);

        atf = new AffineTransform();
        g2d.getTransform();
        g2d.translate(bolaPrincipal.getPosX(), bolaPrincipal.getPosY());
        g2d.setTransform(atf);
      
        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circuloPontos.getPosX(), circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        
        g2d.setPaint(Color.RED);    
        g2d.fillRect(quadrado.getPosX(),quadrado.getPosY(),quadrado.getWidth(),quadrado.getHeight());

        g2d.setPaint(new Color(37,153,179));     
        g2d.fillOval(circuloRecuarPosX.getPosX(),circuloRecuarPosX.getPosY(),circuloRecuarPosX.getWidth(),circuloRecuarPosX.getHeight());
        
        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bolaPrincipal.getPosX(), bolaPrincipal.getPosY(), bolaPrincipal.getWidth(), bolaPrincipal.getHeight());
        
        }
   
    public void atualizar() {

        if (teclado.isCima()) 
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() - 2);
        
        if (teclado.isBaixo()) 
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() + 2);
        
        if (teclado.isFrente()) 
            bolaPrincipal.setPosX(bolaPrincipal.getPosX() + 2);
        

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    @Override
    public void run() {

        try {

            while (true) {
                this.atualizar();
                this.repaint();
                thread.sleep(13);
            }

        } catch (InterruptedException e) {
            System.out.println("Erro!");
        }
    }

}
