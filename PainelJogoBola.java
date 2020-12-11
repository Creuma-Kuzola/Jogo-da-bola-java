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

    Bola bola;
    static Teclado teclado = new Teclado();
    Thread thread;
    public AffineTransform atf;
    int bolaPositionX = 0, bolaPositionY = 0;

    // Getters and Setters

    public PainelJogoBola() {
        thread = new Thread(this);
        bola = new Bola(20, 290, 40, 40);
        this.setFocusable(true);
        addKeyListener(teclado);
        thread.start();
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 277, 750, 80);

        atf = g2d.getTransform();
        g2d.translate(bola.getPosX(), bola.getPosY());
        g2d.setTransform(atf);

        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bola.getPosX(), bola.getPosY(), bola.getWidth(), bola.getHeight());
        // pintarBola(g2d);

    }
    /*
     * public void pintarEstrada(Graphics2D g2d){
     * 
     * g2d.setPaint(new Color(0,0,0)); g2d.fillRect(0,277,750,80); }
     * 
     * public void pintarBola(Graphics2D g2d){ atf = g2d.getTransform();
     * g2d.translate(bolaPositionX+20,bolaPositionY+290); g2d.setTransform(atf);
     * g2d.setPaint(Color.YELLOW); g2d.fill(bola);
     * 
     * }
     */

    public void atualizar() {

        if (teclado.isCima()) {
            bola.setPosY(bola.getPosY() - 5);
            System.out.println(bola.getPosY());
        }
        if (teclado.isBaixo()) {
            bola.setPosY(bola.getPosY() + 5);
        }
        if (teclado.isFrente()) {
            bola.setPosX(bola.getPosX() + 5);
        }

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    @Override
    public void run() {

        try {

            while (true) {
                this.atualizar();
                // thread.sleep(150);
                this.repaint();

                Thread.sleep(13);
            }

        } catch (Exception e) {
            System.out.println("Erro!");
        }
    }

}
