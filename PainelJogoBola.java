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
    int diferX = 18, diferY=18;
    // 900 para que fique já do lado direito e faça a translação de forma smooth (suave)
    private int xMovimentQuadrado = 300,xMovimentCiruloPontos= 300, xMovimentCiruloPosicao = 300;
   // private int xMovimentQuadrado = 500,xMovimentCiruloPontos=300, xMovimentCiruloPosicao =500;
    private int nextObstaculoCome = 450;
    private int firstObstaculo;
    private int secondObstaculo;
    private int thirdObstaculo;
    int rand,numObstaculos=3;
    private boolean flagObst1,flagObst2, flagObst3;
    private boolean quadr,circPos, circPont;
    int i=1,i2=1,i3=1,i4=1;
    Graphics2D g2d;
    public int xMoviment1 = 400;
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

       public boolean isQuadr() {
        return quadr;
    }

    public void setQuadr(boolean quadr) {
        this.quadr = quadr;
    }

    public boolean isCircPos() {
        return circPos;
    }

    public void setCircPos(boolean circPos) {
        this.circPos = circPos;
    }

    public boolean isCircPont() {
        return circPont;
    }

    public void setCircPont(boolean circPont) {
        this.circPont = circPont;
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

        atf = new AffineTransform();
        g2d.getTransform();
        
        g2d.translate(bolaPrincipal.getPosX(), bolaPrincipal.getPosY());
        g2d.setTransform(atf);
      
        // Circulo Pontos(Magenta)
        g2d.getTransform();
        atf = new AffineTransform();
        g2d.translate(xMovimentCiruloPontos,0);

        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circuloPontos.getPosX(), circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        g2d.setTransform(atf);
        
        g2d.getTransform();
        atf = new AffineTransform();
        g2d.translate(xMovimentCiruloPosicao,0);
        
        g2d.setPaint(new Color(37,153,179));     
        g2d.fillOval(circuloRecuarPosX.getPosX(),circuloRecuarPosX.getPosY(),circuloRecuarPosX.getWidth(),circuloRecuarPosX.getHeight());
        g2d.setTransform(atf);
        
        g2d.getTransform();
        atf = new AffineTransform();
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
      
    public void redesenharQuadrado(int posX)
    {
         //System.out.println("Entrei");
         g2d.getTransform();
         g2d.setPaint(Color.MAGENTA);  
         g2d.translate(xMoviment1,0);
         g2d.fillOval(posX, 290,40, 40);
         g2d.getTransform();
    }
    
   /*   
    public void obstaculosJogo(Graphics2D g2d, int xPos,int cod)
    {
       
        if(cod == 1)
        {  
           System.out.println("Entrei");
           g2d.setPaint(Color.MAGENTA);  
           g2d.translate(xMoviment1,0);
           g2d.fillOval(xMoviment1, circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        }  
        else if(cod == 2)
        {      
            g2d.setPaint(Color.RED);
            g2d.translate(xMovimentQuadrado,0);
            g2d.fillRect(xPos, quadrado.getPosY(), quadrado.getWidth(), quadrado.getHeight());
        }   
        else
        {  
           g2d.setPaint(new Color(37,153,179));     
           g2d.translate(xMovimentCiruloPosicao,0);
           g2d.fillOval(xPos, circuloRecuarPosX.getPosY(), circuloRecuarPosX.getWidth(), circuloRecuarPosX.getHeight());
        }  
       
        g2d.getTransform();
    }        
*/
    public void desenharMesmoObjectoPosDiferente(Graphics2D g2d)
    {
        paintComponent(g2d);
        
    }        
    
    @Override
    public void actionPerformed(ActionEvent arg0) {

    }

    @Override
    public void run() {

        try {
  
            firstObstaculo = 1;
            secondObstaculo = 1;
            thirdObstaculo = 3;
              
          while (true) 
          {    
              
            if(numObstaculos != 0)
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
                    else 
                    {
                        flagObst2 = true;
                        circuloPontos.setObstaculoActivo(true);
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

                    setxMovimentQuadrado(getxMovimentQuadrado()-4);
                    
                  }  
                  else if(circuloRecuarPosX.isObstaculoActivo())
                  {
                        if(i3==1)
                        {
                           setxMovimentCiruloPosicao(350);
                           i3--;
                        }    

                        setxMovimentCiruloPosicao(getxMovimentCiruloPosicao()-4);
                    
                   }
                  else 
                  {
                      if(i4 == 1)
                      {
                          redesenharQuadrado(400);
                          i--;
                      }    
                        
                      //setxMovimentCiruloPontos(getxMovimentCiruloPontos()-5);
                      xMoviment1 -=10;
                  }   
              
              
              }    
              
            }   
              /*
                else if(firstObstaculo == 2)
                {
                  if(getxMovimentQuadrado() == 252)
                    if(secondObstaculo == 2)
                    {   setFlagObst2(true);
                        numObstaculos--;
                    }
                  setxMovimentQuadrado(getxMovimentQuadrado()-4);
                }    
                else
                {
                   setxMovimentCiruloPosicao(getxMovimentCiruloPosicao()-4);
                
                }
               /*case 1:
                        
                        
                        break;
                    case 2:
                        setFlagObst1(true);
                        numObstaculos--;
                        break;
                    default:
                        setFlagObst1(true);
                        numObstaculos--;
                        break;*/  
                    
              /*if(flagObst1 == true)
              { 
                  
                  
                  if(getxMovimentCiruloPontos() == 250)
                    if(secondObstaculo == 2)
                    {   setFlagObst2(true);
                        numObstaculos--;
                    }
                  setxMovimentCiruloPontos(getxMovimentCiruloPontos()-5); 
              } 
               
              if(flagObst2 == true)
              {
                if(getxMovimentCiruloPosicao()== 252)
                  if(thirdObstaculo == 3)    
                    flagObst3 = true;
                setxMovimentCiruloPosicao(getxMovimentCiruloPosicao()-4);
                
              }  
                
              if(flagObst3 == true)
                  setxMovimentQuadrado(getxMovimentQuadrado()-4);*/
                
            /*} */
              
            
             
            this.atualizar();
            this.repaint();
            Thread.sleep(13);
           }

        } catch (InterruptedException e) {
            System.out.println("Erro!");
        }
    }
 
}
