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
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 *
 * @author creuma
 */
public class PainelJogoBola extends JPanel implements ActionListener, Runnable {

    
    //Bloco de declaração de variáveis
    Bola bolaPrincipal,circuloPontos,circuloRecuarPosX;
    Quadrado quadrado;
    Teclado teclado = new Teclado();
    Thread thread;
    public AffineTransform atf;
    private int xMovimentQuadrado = 300,xMovimentCirculoPontos= 300, xMovimentCirculoPosicao = 300;
    private int firstObstaculo;
    private int secondObstaculo;
    private int thirdObstaculo;
    int numObstaculos=3;
    private boolean flagObst1,flagObst2, flagObst3;
    public int xMoviment1 = 400;
    int i=1, i2=1,i3=1;
    
    //Métodos Getters e Setters  
    public int getxMovimentQuadrado() {
        return xMovimentQuadrado;
    }

    public void setxMovimentQuadrado(int xMovimentQuadrado) {
        this.xMovimentQuadrado = xMovimentQuadrado;
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

     public int getxMovimentCirculoPontos() {
        return xMovimentCirculoPontos;
    }

    public void setxMovimentCiruloPontos(int xMovimentCirculoPontos) {
        this.xMovimentCirculoPontos = xMovimentCirculoPontos;
    }

    public int getxMovimentCiruloPosicao() {
        return xMovimentCirculoPosicao;
    }

    public void setxMovimentCiruloPosicao(int xMovimentCiruloPosicao) {
        this.xMovimentCirculoPosicao = xMovimentCiruloPosicao;
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

    
   //Constructor da classe
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
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(Color.WHITE);
        
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Estrada do jogo
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 277, 770, 80);

        // Circulo Pontos(Magenta)
        atf = g2d.getTransform();
        g2d.translate(xMovimentCirculoPontos,0);
        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circuloPontos.getPosX(), circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        g2d.setTransform(atf);
        
        // Circulo Recuar -10 na Posição(Azul)
        atf = g2d.getTransform();
        g2d.translate(xMovimentCirculoPosicao,0);
        g2d.setPaint(new Color(37,153,179));     
        g2d.fillOval(circuloRecuarPosX.getPosX(),circuloRecuarPosX.getPosY(),circuloRecuarPosX.getWidth(),circuloRecuarPosX.getHeight());
        g2d.setTransform(atf);
        
        //Quadrado vermelho de fim-de-jogo
        atf = g2d.getTransform();
        g2d.translate(xMovimentQuadrado,0);
        g2d.setPaint(Color.RED);    
        g2d.fillRect(quadrado.getPosX(),quadrado.getPosY(),quadrado.getWidth(),quadrado.getHeight());
        g2d.setTransform(atf);
        
        //Bola principal dp jogo(amarela)
        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bolaPrincipal.getPosX(), bolaPrincipal.getPosY(), bolaPrincipal.getWidth(), bolaPrincipal.getHeight());
       
        }
   
     //Função responsável por gerar a ordem em que os obstáculos irão entrar
        public int gerarOrdemDosObstaculos(int antRand, boolean firstObstacle,int antRand1,boolean lastObs)
        {  
            
            int rand = new Random().nextInt(4);
            while(true)
            {
                if(firstObstacle && rand != 0 && lastObs==false) break;
                if(rand != 0 && rand!= antRand && lastObs==false)break;
                else rand = new Random().nextInt(4);
                
                if(rand != 0 && rand!= antRand1 && lastObs==true)
                    if(rand != antRand)
                        break;
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
        
        // Função responsável por posicionar o circulo pontos no jogo
       public void posicionarCirculoPontos()
        {
           if(getxMovimentCirculoPontos() == -400)
               setxMovimentCiruloPontos(300);
        }
       
       // Função responsável por posicionar o circulo posição no jogo
       public void posicionarCirculoPosicao()
        {
           if(getxMovimentCiruloPosicao() == -400)
               setxMovimentCiruloPosicao(300);
        }
         
       //Função responsável por actualizar o teclado
      public void atualizar() {

        if (teclado.isCima()) 
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() - 2);
              
        if (teclado.isBaixo()) 
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() + 2);
        
        if (teclado.isFrente()) 
            bolaPrincipal.setPosX(bolaPrincipal.getPosX() + 2);
       
    }
      
    // Função da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent arg0) {

    }
    
    public void posicionarFirsObs(int first)
    {
    
        if(firstObstaculo == 1 )
        {
            if(i==1)
            {
                setxMovimentCiruloPontos(300);
                i--;
            }
                 
            if(getxMovimentCirculoPontos() == 250)
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
                 
            setxMovimentCiruloPontos(getxMovimentCirculoPontos()-5);
                  
          }
    }
    
    public void verificarNextObs2(boolean flagObst2)
    {
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
    
    
    }        

    // Função responsável por fazer o código rodar
    @Override
    public void run() {

        try {
  
            firstObstaculo = 1;
            secondObstaculo = 2;
            thirdObstaculo = 3;

            
          while (true) 
          {    
              posicionarFirsObs(firstObstaculo);
              verificarNextObs2(flagObst2);
                          
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
                      setxMovimentCiruloPontos(getxMovimentCirculoPontos()-4);
                  }    
                  
              }    
              
              
            this.atualizar();
            this.repaint();
            Thread.sleep(13);
           }

        } catch (InterruptedException e) {
            System.out.println("Erro!");
        }
    }
 
}
