package JogoBola;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author creuma
 */
public class PainelJogoBola extends JPanel implements ActionListener, Runnable {

    Bola bola,circulo;
    Triangulo triangulo;
    Rectangulo rectangulo;
    Teclado teclado = new Teclado();
    Thread thread;
    public AffineTransform atf;

    public PainelJogoBola() {
        
        //thread = new Thread(this);
        new Thread(this).start();
        bola = new Bola(20, 290, 40, 40);
        circulo = new Bola(80,290,40,40);
        rectangulo = new Rectangulo(130,290,40,40);
        //triangulo = new Triangulo();
        this.setFocusable(true);
        addKeyListener(teclado);
       // thread.start();
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

        atf = g2d.getTransform();
        
        g2d.translate(bola.getPosX(), bola.getPosY());
        g2d.setTransform(atf);
      
        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circulo.getPosX(), circulo.getPosY(), circulo.getWidth(), circulo.getHeight());
        
        g2d.setPaint(new Color(37,153,179));       
        g2d.fillRect(rectangulo.getPosX(),rectangulo.getPosY(),rectangulo.getWidth(),rectangulo.getHeight());

        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bola.getPosX(), bola.getPosY(), bola.getWidth(), bola.getHeight());
        
        }
   
    public void atualizar() {

        if (teclado.isCima()) 
            bola.setPosY(bola.getPosY() - 2);
        
        if (teclado.isBaixo()) 
            bola.setPosY(bola.getPosY() + 2);
        
        if (teclado.isFrente()) 
            bola.setPosX(bola.getPosX() + 2);
        

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
