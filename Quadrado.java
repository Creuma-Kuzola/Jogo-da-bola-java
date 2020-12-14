/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JogoBola;

/**
 *
 * @author creuma
 */
public class Quadrado {
    
    private int posX, posY;
    private int width, height;
    private int velocidadeX;
    private int numObstaculo;
    private int xMovimentAntecessor;
    private boolean obstaculoActivo;
    
    public Quadrado(int posX, int posY, int width, int height,int numObstaculo) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.numObstaculo = numObstaculo;
    }
    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getVelocidadeX() {
        return velocidadeX;
    }

    public void setVelocidadeX(int velocidadeX) {
        this.velocidadeX = velocidadeX;
    }
    
     public int getNumObstaculo() {
        return numObstaculo;
    }

    public void setNumObstaculo(int numObstaculo) {
        this.numObstaculo = numObstaculo;
    }
    
    public int getxMovimentAntecessor() {
        return xMovimentAntecessor;
    }

    public void setxMovimentAntecessor(int xMovimentAntecessor) {
        this.xMovimentAntecessor = xMovimentAntecessor;
    }

    public boolean isObstaculoActivo() {
        return obstaculoActivo;
    }

    public void setObstaculoActivo(boolean obstaculoActivo) {
        this.obstaculoActivo = obstaculoActivo;
    }
    
    
}
