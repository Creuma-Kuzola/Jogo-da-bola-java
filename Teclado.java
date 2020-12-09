/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author creuma
 */
public class Teclado implements KeyListener{

    private boolean cima;
    private boolean frente;
    private boolean baixo;
   
    
    // Getters and Setters
    
     public boolean isCima() {
        return cima;
    }

    public void setCima(boolean cima) {
        this.cima = cima;
    }

    public boolean isFrente() {
        return frente;
    }

    public void setFrente(boolean frente) {
        this.frente = frente;
    }

    public boolean isBaixo() {
        return baixo;
    }

    public void setBaixo(boolean baixo) {
        this.baixo = baixo;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();
        
        if(key == KeyEvent.VK_RIGHT)
            frente = true;
        if(key == KeyEvent.VK_UP)
            cima= true;
        if(key == KeyEvent.VK_DOWN)
            baixo = true;
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT)
            frente = false;
        if(key == KeyEvent.VK_UP)
            cima = false;
        if(key == KeyEvent.VK_DOWN)
            baixo = false;
        
    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {
        
        
    }

   
}
