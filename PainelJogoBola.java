//package JogoBola;


//package JogoBola;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author creuma
 */
public class PainelJogoBola extends JPanel implements ActionListener, Runnable {

    
    //Bloco de declaração de variáveis
    Bola bolaPrincipal,circuloPontos,circuloRecuar;
    Quadrado quadrado;
    Teclado teclado = new Teclado();
    Thread thread;
    public AffineTransform atf;
    private int xMovimentQuadrado = 2,xMovimentCirculoPontos=2, xMovimentCirculoPosicao = 2;
    private boolean flagObst1=false,flagObst2=false, flagObst3=false;
    boolean flagColidiuPontos = false, flagColidiuRecuar = false, flagColidiuQuadrado = false;
    boolean gameOverFlag=false;
    int pontos;
    int posInicialX=0,posFinalX=630;
    int controlXPontos = 0,controlXPos=0, controlXFimdeJogo=0;
    boolean flagRedesenharCirculoPontos = false;
    
    //Métodos Getters e Setters  
    public int getxMovimentQuadrado() {
        return xMovimentQuadrado;
    }

    public void setxMovimentQuadrado(int xMovimentQuadrado) {
        this.xMovimentQuadrado = xMovimentQuadrado;
    }
    
    public int getxMovimentCirculoPontos() {
        return xMovimentCirculoPontos;
    }

    public void setxMovimentCiruloPontos(int xMovimentCirculoPontos) {
        this.xMovimentCirculoPontos = xMovimentCirculoPontos;
    }

    public int getxMovimentCirculoPosicao() {
        return xMovimentCirculoPosicao;
    }

    public void setxMovimentCirculoPosicao(int xMovimentCirculoPosicao) {
        this.xMovimentCirculoPosicao = xMovimentCirculoPosicao;
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
      
        thread = new Thread(this);
        thread.start();
        
        bolaPrincipal = new Bola(20, 290, 40, 40,0,false);
        circuloPontos = new Bola(500,290,40,40,1,false);
        quadrado = new Quadrado(800,290,40,40,2,false);
        circuloRecuar = new Bola(800,290,40,40,3,false);
        posInicialX = bolaPrincipal.getPosX();
        this.setFocusable(true);
        addKeyListener(teclado);
    }

    // Método que irá pintar os gráficos
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        this.setBackground(new Color(0,0,0));
        
        // Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
   
        // Estrada do jogo
        g2d.setPaint(new Color(0, 0, 0));
        g2d.fillRect(0, 277, 770, 80);

        // Circulo Pontos(Magenta)
        if (!flagColidiuPontos) {
            desenharCirculoPontos(g2d);
        }
        
        // Circulo Recuar -10 na Posição(Azul)
        desenharCirculoRecuar(g2d);
        
        //Quadrado vermelho de fim-de-jogo
        desenharQuadrado(g2d);   
        
        //Bola principal dp jogo(amarela)

        g2d.setPaint(Color.YELLOW);
        g2d.fillOval(bolaPrincipal.getPosX(), bolaPrincipal.getPosY(), bolaPrincipal.getWidth(), bolaPrincipal.getHeight());

       
        // String Pontos (Quadrado Magenta)
        g2d.setPaint(Color.MAGENTA);  
        g2d.fillRect(20,30, 150, 30);
        
        g2d.setPaint(Color.WHITE);
        g2d.setFont(new Font("SANS-SERIF",Font.BOLD, 18));
        g2d.drawString("Pontos: "+ pontos, 25, 50);
        
        // Painel de Game Over
        if(gameOverFlag)
            gameOverScreen(g2d);
        
        if(flagRedesenharCirculoPontos)
            desenharCirculoPontos(g2d);
        }
    
    public void desenharCirculoPontos( Graphics2D g2d)
    {
        atf = g2d.getTransform();
        g2d.setPaint(Color.MAGENTA);
        g2d.fillOval(circuloPontos.getPosX(), circuloPontos.getPosY(), circuloPontos.getWidth(), circuloPontos.getHeight());
        g2d.setTransform(atf);
    }        

    public void desenharCirculoRecuar( Graphics2D g2d)
    {
        atf = g2d.getTransform();
        g2d.setPaint(new Color(37,153,179));
        g2d.fillOval(circuloRecuar.getPosX(),circuloRecuar.getPosY(),circuloRecuar.getWidth(),circuloRecuar.getHeight());
        g2d.setTransform(atf);
    }   
    
    public void desenharQuadrado( Graphics2D g2d)
    {
        atf = g2d.getTransform();
        g2d.setPaint(Color.RED);  
        g2d.fillRect(quadrado.getPosX(),quadrado.getPosY(),quadrado.getWidth(),quadrado.getHeight());
        g2d.setTransform(atf);

    }   
       //Função responsável por actualizar o teclado
      public void atualizar() {

        if (teclado.isCima()) 
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() - 4);
              
        if (teclado.isBaixo()) 
            bolaPrincipal.setPosY(bolaPrincipal.getPosY() + 4);
        
        if (teclado.isFrente()) 
            bolaPrincipal.setPosX(bolaPrincipal.getPosX() + 4);
       
    }
      
    // Função da interface ActionListener
    @Override
    public void actionPerformed(ActionEvent arg0) {

    }
    
    // Função que tem como objectivo posicionar o primeiro obstáculo
    public void posicionarPrimeiroObstaculo()
    {
        if(circuloPontos != null)
        {    
            if(flagObst1 == false)
            {
                circuloPontos.setPosX(700);
                flagObst1 = true;
            }
                
            if(circuloPontos.getPosX() == 610)      
               flagObst2 = true;
             
            if(circuloPontos.getPosX() <= -300)
            {
                circuloPontos.setPosX(700);    
                flagColidiuPontos = false;
                    
                if(controlXPontos == 15)
                {    
                    xMovimentCirculoPontos= 2;
                    controlXPontos = 1;
                }
                controlXPontos +=1;
                xMovimentCirculoPontos +=1; 
     
            }   
            circuloPontos.setPosX(circuloPontos.getPosX() - xMovimentCirculoPontos); 
               
       }          
            
    }
    
    // Função que tem como objectivo posicionar o segundo obstáculo
    public void posicionarSegundoObstaculo()
    {
        
        if(flagObst2 )
        {    
            if(circuloRecuar != null)
            {   
                if(circuloRecuar.getPosX() == 610)
                    flagObst3 =true; 
                
                if(circuloRecuar.getPosX() <= -300)
                {  
                   circuloRecuar.setPosX(800);
                   if(controlXPos == 7)
                   {    
                       xMovimentCirculoPosicao= 2;
                       controlXPos = 1;
                   }
                   controlXPos +=1;
                   xMovimentCirculoPosicao+=1;
                }
                 
                circuloRecuar.setPosX(circuloRecuar.getPosX() - xMovimentCirculoPosicao);
            } 
            
      }

  }   
    
    // Função que tem como objectivo posicionar o terceiro obstáculo
    public void posicionarTerceiroObstaculo()
    {
        if(flagObst3)
        {    
           if(quadrado != null)    
           {    
               if(quadrado.getPosX() <= -300)
               {   
                   System.out.println("controlXFimdeJogo: "+controlXFimdeJogo);
                   if(controlXFimdeJogo == 11)
                   {    
                       xMovimentQuadrado= 2;
                       controlXFimdeJogo = 1;
                   }
                   controlXFimdeJogo +=1;
                   xMovimentQuadrado+=1;
                   quadrado.setPosX(800);
                 
               }   
               
               quadrado.setPosX(quadrado.getPosX() -xMovimentQuadrado);
           }
           
       }

   }   
    
    //Função que verifica a colisão que acontece com o circulo pontos
    public void verificarColisaoCirculoPontos()
    {
       
        if (bolaPrincipal != null) {
            
            int bordaDirPrincipal = bolaPrincipal.getPosX() + bolaPrincipal.getWidth();
            int bordaEsquerdaPrincipal = bolaPrincipal.getPosX();
            int bordaBaixoPrincipal = bolaPrincipal.getPosY() + bolaPrincipal.getHeight();

            int bordaDirPontos = circuloPontos.getPosX() + circuloPontos.getWidth();
            int bordaEsquerdaPontos = circuloPontos.getPosX();
            int bordaTopoPontos = circuloPontos.getPosY();

            if(flagColidiuPontos == false)
            {    
                if(bordaBaixoPrincipal >= bordaTopoPontos) {
                    
                    if((bordaDirPrincipal >= bordaEsquerdaPontos && bordaDirPrincipal <= bordaDirPontos) 
                    ||(bordaEsquerdaPrincipal >= bordaEsquerdaPontos && bordaEsquerdaPrincipal <= bordaDirPontos))

                    {
                        flagColidiuPontos = true;
                        pontos+=5;
                        flagRedesenharCirculoPontos = true;
                    }
                }       
            }    
        }
      
    }    

    //Função que verifica a colisão que acontece com o círculo que faz recuar -10 na posição actual
    public void verificarColisaoCirculoRecuarPos()
    {
       
        if (circuloRecuar != null) {
            
            int bordaDirPrincipal = bolaPrincipal.getPosX() + bolaPrincipal.getWidth();
            int bordaEsquerdaPrincipal = bolaPrincipal.getPosX();
            int bordaBaixoPrincipal = bolaPrincipal.getPosY() + bolaPrincipal.getHeight();
            
            int bordaDirRecuar = circuloRecuar.getPosX() + circuloRecuar.getWidth();
            int bordaEsquerdaRecuar = circuloRecuar.getPosX();
            int bordaTopoRecuar = circuloRecuar.getPosY();
            
            if(flagColidiuRecuar == false)
            {    
                
                if(bordaBaixoPrincipal >= bordaTopoRecuar) {
                    if((bordaDirPrincipal >= bordaEsquerdaRecuar && bordaDirPrincipal <= bordaDirRecuar) 
                        ||(bordaEsquerdaPrincipal >= bordaEsquerdaRecuar && bordaEsquerdaPrincipal <= bordaDirRecuar))
                        {
                            
                            if(bolaPrincipal.getPosX() - 50 < 0){
                                gameOverFlag = true;
                            }
                            bolaPrincipal.setPosX(bolaPrincipal.getPosX()-50);

                        }
                }
            }    
        }
      
    }    
    
    //Função que verifica a colisão que acontece com o quadrado de fim de jogo 
    public void verificarColisaoQuadrado()
    {
        
        if (quadrado != null) {
            
            int bordaDirPrincipal = bolaPrincipal.getPosX() + bolaPrincipal.getWidth();
            int bordaEsquerdaPrincipal = bolaPrincipal.getPosX();
            int bordaBaixoPrincipal = bolaPrincipal.getPosY() + bolaPrincipal.getHeight();
            
            int bordaDirQuadrado = quadrado.getPosX() + quadrado.getWidth();
            int bordaEsquerdaQuadrado = quadrado.getPosX();
            int bordaTopoQuadrado = quadrado.getPosY();

            if(flagColidiuQuadrado == false)
            {    
               
                if(bordaBaixoPrincipal >= bordaTopoQuadrado) {
                   if((bordaDirPrincipal >= bordaEsquerdaQuadrado && bordaDirPrincipal <= bordaDirQuadrado) 
                    ||(bordaEsquerdaPrincipal >= bordaEsquerdaQuadrado && bordaEsquerdaPrincipal <= bordaDirQuadrado))
                    {
                            gameOverFlag=true;

                    }
                
                }


            }    
        }
      
    }
    
    //Painel da Tela de Fim de jogo
    public void gameOverScreen(Graphics2D g2d) {
        
        g2d.setPaint(Color.RED);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("ROBOTO", Font.BOLD, 50));
        FontMetrics metrics = getFontMetrics(g2d.getFont());
        g2d.drawString("GAME OVER",
                (getWidth() - metrics.stringWidth("GAME OVER"))/2,
                (getHeight())/2);
    
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 25));
        g2d.drawString("Pontos: " + this.pontos,
                (getWidth() - metrics.stringWidth("Score: " + this.pontos))/2,
                ((getHeight())/2) + 45);
    }
    
    
    public void situcoesExcepcionaisDeFimDeJogo()
    {
        if(bolaPrincipal!= null)
        {
           if(bolaPrincipal.getPosX() >= 630)
              gameOverFlag=true;
           
           if(bolaPrincipal.getPosY() >= 300)
              gameOverFlag=true;
           
           if(bolaPrincipal.getPosY() <= 0)
              gameOverFlag=true;
           
        }    
    }        
      
     
    // Função responsável por fazer o código rodar
    @Override
    public void run() {

        try {
            
            while (!gameOverFlag) 
            {   

                posicionarPrimeiroObstaculo();
                verificarColisaoCirculoPontos();
                posicionarSegundoObstaculo();
                posicionarTerceiroObstaculo();  
               
                verificarColisaoCirculoRecuarPos();
                verificarColisaoQuadrado();   
                situcoesExcepcionaisDeFimDeJogo();
                atualizar();
                repaint();
                Thread.sleep(13);
            }
            thread.join();

          }catch (InterruptedException e) {
              System.out.println("Erro!");
          }
    
    }
 
   
}
