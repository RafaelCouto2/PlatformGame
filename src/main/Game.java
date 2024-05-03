package main;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    public Game(){

        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        //Game Loop thread!
        //Calculador de FPS. Verificará o valor em nanosegundos do frame atual, menos o frame anterior e dirá se atualizará ou não.
        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime(), actualFrame = System.nanoTime();
        int frames = 0;
        long lastCheck = System.currentTimeMillis();
        while(true){
            actualFrame = System.nanoTime();
            if(actualFrame - lastFrame >= timePerFrame){
                gamePanel.repaint();
                lastFrame = actualFrame;
                frames++;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
    }
}
