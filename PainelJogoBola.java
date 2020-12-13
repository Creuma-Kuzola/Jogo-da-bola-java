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
import java.util.Random;

/**
 *
 * @author creuma
 */
public class PainelJogoBola extends JPanel implements ActionListener, Runnable {

    Bola bolaPrincipal,circuloPontos,circuloRecuarPosX;
    Quadrado quadrado;
    Teclado teclado = new Teclado();
    Thread thread;
    public AffineTransform atf ;
    private int angulo;
    Ellipse2D circuloInterior;
    int diferX = 18, diferY=18;
    private int xMovimentQuadrado = 300;
    
    // Getters e Setters  
    public int getAngulo() {
        return angulo;
    }

    public void setAngulo(int angulo) {
        this.angulo = angulo;
    }
    
    public int getxMovimentQuadrado() {
        return xMovimentQuadrado;
    }

    public void setxMovimentQuadrado(int xMovimentQuadrado) {
        this.xMovimentQuadrado = xMovimentQuadrado;
    }

   //Construtor  
    public PainelJogoBola() {
      
        new Thread(this).start();
        
        bolaPrincipal = new Bola(20, 290, 40, 40,0);
        circuloPontos = new Bola(450,290,40,40,1);
        quadrado = new Quadrado(300,290,40,40,2);
        circuloRecuarPosX = new Bola(180,290,40,40,3);
        
        this.setFocusable(true);
        addKeyListener(teclado);
    }

    // Método que irá pintar os gráficos
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Estrada do jogo
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 277, 770, 80);

        atf = new AffineTransform();
        g2d.getTransform();
        
        g2d.translate(bolaPrincipal.getPosX(), bolaPrincipal.getPosY());
        //atf.rotate(Math.toRadians(angulo),38,308);
       // g2d.rotate(Math.toRadians(angulo),, ERROR);
       
        g2d.setTransform(atf);
      
        /*
        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circuloPontos.getPosX(), circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        
        g2d.setPaint(new Color(37,153,179));     
        g2d.fillOval(circuloRecuarPosX.getPosX(),circuloRecuarPosX.getPosY(),circuloRecuarPosX.getWidth(),circuloRecuarPosX.getHeight());
        */
        
        g2d.getTransform();
        atf = new AffineTransform();
        g2d.translate(xMovimentQuadrado,0);
        
        g2d.setPaint(Color.RED);    
        g2d.fillRect(quadrado.getPosX(),quadrado.getPosY(),quadrado.getWidth(),quadrado.getHeight());
       
         g2d.setTransform(atf);
        
        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bolaPrincipal.getPosX(), bolaPrincipal.getPosY(), bolaPrincipal.getWidth(), bolaPrincipal.getHeight());
        
        }
   
        // Função responsável por posicionar o quadrado no jogo
        public void posicionarQuadrado()
        {
           if(getxMovimentQuadrado() == -400)
               setxMovimentQuadrado(300);
        }        
    
        //Função responsável por gerar a ordem em que obstáculos irão entrar
        public int gerarOrdemDosObstaculos()
        {  
            return new Random().nextInt(4);
        }        
        
       //Função responsável por actualizar o teclado
      public void atualizar() {

        if (teclado.isCima()) 
        {
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() - 2);
             //atf.rotate(Math.toRadians(angulo),bolaPrincipal.getPosX()+diferX,bolaPrincipal.getPosY()+diferY);
        }    
            
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
                
                this.gerarOrdemDosObstaculos();
                this.posicionarQuadrado();
                this.atualizar();
                this.repaint();
                Thread.sleep(13);
                setAngulo(getAngulo()+1);
                //System.out.println("xMovimentQuadrado: "+xMovimentQuadrado);
                xMovimentQuadrado -= 5;
            }

        } catch (InterruptedException e) {
            System.out.println("Erro!");
        }
    }



}
