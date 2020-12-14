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

    // Declaração de variáveis
    Bola bolaPrincipal,circuloPontos,circuloRecuarPosX;
    Quadrado quadrado;
    Teclado teclado = new Teclado();
    Thread thread;
    public AffineTransform atf;
    int diferX = 18, diferY=18;
    // 900 para que fique já do lado direito e faça a translação de forma smooth (suave)
    private int xMovimentQuadrado = 300,xMovimentCiruloPontos= 300, xMovimentCiruloPosicao = 300;
   // private int xMovimentQuadrado = 500,xMovimentCiruloPontos=300, xMovimentCiruloPosicao =500;
    private int nextObstaculoCome = 450;
    private int firstObstaculo;
    private int secondObstaculo;
    private int thirdObstaculo;
    int numObstaculos=3;
    private boolean flagObst1,flagObst2, flagObst3;
    public int xMoviment1 = 400;
    
    
    // Getters e Setters  
    public int getxMovimentQuadrado() {
        return xMovimentQuadrado;
    }

    public void setxMovimentQuadrado(int xMovimentQuadrado) {
        this.xMovimentQuadrado = xMovimentQuadrado;
    }

    public int getNextObstaculoCome() {
        return nextObstaculoCome;
    }

    public void setNextObstaculoCome(int nextObstaculoCome) {
        this.nextObstaculoCome = nextObstaculoCome;
    }

    public int getFirstObstaculo() {
        return firstObstaculo;
    }

    public void setFirstObstaculo(int firstObstaculo) {
        this.firstObstaculo = firstObstaculo;
    }

    public int getSecondObstaculo() {
        return secondObstaculo;
    }

    public void setSecondObstaculo(int secondObstaculo) {
        this.secondObstaculo = secondObstaculo;
    }

    public int getThirdObstaculo() {
        return thirdObstaculo;
    }

    public void setThirdObstaculo(int thirdObstaculo) {
        this.thirdObstaculo = thirdObstaculo;
    }

     public int getxMovimentCiruloPontos() {
        return xMovimentCiruloPontos;
    }

    public void setxMovimentCiruloPontos(int xMovimentCiruloPontos) {
        this.xMovimentCiruloPontos = xMovimentCiruloPontos;
    }

    public int getxMovimentCiruloPosicao() {
        return xMovimentCiruloPosicao;
    }

    public void setxMovimentCiruloPosicao(int xMovimentCiruloPosicao) {
        this.xMovimentCiruloPosicao = xMovimentCiruloPosicao;
    }

        public boolean isFlagObst1() {
        return flagObst1;
    }

    public void setFlagObst1(boolean flagObst1) {
        this.flagObst1 = flagObst1;
    }

    public boolean isFlagObst2() {
        return flagObst2;
    }

    public void setFlagObst2(boolean flagObst2) {
        this.flagObst2 = flagObst2;
    }

    public boolean isFlagObst3() {
        return flagObst3;
    }

    public void setFlagObst3(boolean flagObst3) {
        this.flagObst3 = flagObst3;
    }

   //Constructor  
    public PainelJogoBola() {
      
        new Thread(this).start();
        
        bolaPrincipal = new Bola(20, 290, 40, 40,0);
        circuloPontos = new Bola(450,290,40,40,1);
        quadrado = new Quadrado(500,290,40,40,2);
        circuloRecuarPosX = new Bola(800,290,40,40,3);
        
        this.setFocusable(true);
        addKeyListener(teclado);
    }

    // Método que irá pintar os gráficos
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g2d = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Estrada do jogo
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 277, 770, 80);

        // Circulo Pontos(Magenta)
        atf = g2d.getTransform();
        g2d.translate(xMovimentCiruloPontos,0);

        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circuloPontos.getPosX(), circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        g2d.setTransform(atf);
        
        atf = g2d.getTransform();
        g2d.translate(xMovimentCiruloPosicao,0);
        
        g2d.setPaint(new Color(37,153,179));     
        g2d.fillOval(circuloRecuarPosX.getPosX(),circuloRecuarPosX.getPosY(),circuloRecuarPosX.getWidth(),circuloRecuarPosX.getHeight());
        g2d.setTransform(atf);
        
        atf = g2d.getTransform();
        g2d.translate(xMovimentQuadrado,0);
        
        g2d.setPaint(Color.RED);    
        g2d.fillRect(quadrado.getPosX(),quadrado.getPosY(),quadrado.getWidth(),quadrado.getHeight());
       
        g2d.setTransform(atf);
        
        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bolaPrincipal.getPosX(), bolaPrincipal.getPosY(), bolaPrincipal.getWidth(), bolaPrincipal.getHeight());
       
        }
   
     //Função responsável por gerar a ordem em que os obstáculos irão entrar
        public int gerarOrdemDosObstaculos()
        {  
            int rand = new Random().nextInt(4);
            while(true)
            {
                if(rand != 0) break;
                else rand = new Random().nextInt(4);
            }
           
            return rand;
        } 
   

        // Função responsável por posicionar o quadrado no jogo
        public void posicionarQuadrado()
        {
           if(getxMovimentQuadrado() == -400)
               setxMovimentQuadrado(300);
        }    
        
       public void posicionarCirculoPontos()
        {
           if(getxMovimentCiruloPontos() == -400)
               setxMovimentCiruloPontos(300);
        }
       
       public void posicionarCirculoPosicao()
        {
           if(getxMovimentCiruloPosicao() == -400)
               setxMovimentCiruloPosicao(300);
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
  
            firstObstaculo = 1;
            secondObstaculo = 2;
            thirdObstaculo = 3;
              
          while (true) 
          {    
              
            /*if(numObstaculos != 0)
            { 
             
              if(firstObstaculo == 1 )
              {
                 if(i==1)
                 {
                     setxMovimentCiruloPontos(300);
                     i--;
                 }
                 
                 if(getxMovimentCiruloPontos() == 250)
                 {  
                    if(secondObstaculo == 2)
                    {  
                        flagObst2 = true;
                        quadrado.setObstaculoActivo(true);
                    }
                    else if(secondObstaculo == 3)
                    {    
                        flagObst2 = true;
                        circuloRecuarPosX.setObstaculoActivo(true);
                    }

                 }
                 
                 setxMovimentCiruloPontos(getxMovimentCiruloPontos()-5);
                  
                }
              
              if(flagObst2)
              {
                  if(quadrado.isObstaculoActivo())
                  {    
                        if(i2==1)
                        {
                           setxMovimentQuadrado(350);
                           i2--;
                        } 
                    
                        if(getxMovimentQuadrado() == 252)
                        { 
                            if(thirdObstaculo == 3)
                            {  
                                flagObst3 = true;
                                quadrado.setObstaculoActivo(true);
                            }
                           
                        }

                    setxMovimentQuadrado(getxMovimentQuadrado()-4);
                    
                  }  
                  else if(circuloRecuarPosX.isObstaculoActivo())
                  {
                        if(i3==1)
                        {
                           setxMovimentCiruloPosicao(350);
                           i3--;
                        }    
                        
                        if(getxMovimentQuadrado() == 252)
                        { 
                            if(thirdObstaculo == 2)
                            {  
                                flagObst3 = true;
                                quadrado.setObstaculoActivo(true);
                            }
                            else if(thirdObstaculo == 1)
                            {    
                                flagObst3 = true;
                                circuloPontos.setObstaculoActivo(true);
                            }  
                        }

                        setxMovimentCiruloPosicao(getxMovimentCiruloPosicao()-4);
                    
                   }
                               
              }  
              
              i3 = i2=1;
              if(flagObst3)
              {
                  
                  if(quadrado.isObstaculoActivo())
                  {
                      if(i2==1)
                        {
                           setxMovimentQuadrado(350);
                           i2--;
                        }
                      setxMovimentQuadrado(getxMovimentCiruloPosicao()-4);
                  }
                  else if(circuloPontos.isObstaculoActivo())
                  {
                      if(i3==1)
                        {
                           setxMovimentCiruloPontos(350);
                           i3--;
                        }
                      setxMovimentCiruloPontos(getxMovimentCiruloPontos()-4);
                  }    
                  
              }    
              
            }   
*/
             
            this.atualizar();
            this.repaint();
            Thread.sleep(13);
           }

        } catch (InterruptedException e) {
            System.out.println("Erro!");
        }
    }
 
}
