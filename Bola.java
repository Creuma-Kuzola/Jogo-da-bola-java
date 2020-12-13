package JogoBola;

public class Bola {
    
    private int posX, posY;
    private int width, height;
    private int numObstaculo;
    private int velocidadeX;
    
    public Bola(int posX, int posY, int width, int height,int numObstaculo) {
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

    public int getNumObstaculo() {
        return numObstaculo;
    }

    public void setNumObstaculo(int numObstaculo) {
        this.numObstaculo = numObstaculo;
    }
    
     public int getVelocidadeX() {
        return velocidadeX;
    }

    public void setVelocidadeX(int velocidadeX) {
        this.velocidadeX = velocidadeX;
    }

    
}
