package main;

import entities.Player;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private Player player;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    public Game(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    private void initClasses() {
        player = new Player(200,200);
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void update(){
        //WILL HAVE ALL THE UPDATE METHODS FROM OBJECTS
        player.update();
    }
    public void render(Graphics g){
        player.render(g);
    }
    @Override
    public void run() {
        //Game Loop thread!
        //Calculador de FPS. Verificará o valor em nanosegundos do frame atual, menos o frame anterior e dirá se atualizará ou não.
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        long previousTime = System.nanoTime();
        int updates = 0;
        int frames = 0;

        while(true){
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(deltaU >= 1){
                //UPDATES

                update();
                updates++;
                deltaU--;
            }
            if(deltaF >= 1){
                //FRAMES

                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if(System.currentTimeMillis() - lastCheck >= 1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }
    public void windowsFocusLost() { //GRANTS THAT ALL THE BOOLEANS VALUES TO BE FALSE IF GAME`S WINDOW LOST FOCUSES BY ALT TAB ONE
        player.reseDirBooleans();
    }

    //GETTERS

    public Player getPlayer() {
        return this.player;
    }


}
