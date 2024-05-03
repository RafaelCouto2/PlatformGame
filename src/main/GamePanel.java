package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.*;
import static utils.Constants.Directions.*;

public class GamePanel extends JPanel {

    private float xDelta = 100, yDelta = 100;
    private MouseInputs mouseInputs;
    private BufferedImage img;
    private BufferedImage[][] animation;
    private int aniTick, aniIndex, aniSpeed = 15;
    private int playerAction = IDLE;
    private int playerDirection = -1;
    private boolean moving = false;
    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        importImg();
        loadAnimation();
    }

    private void loadAnimation() {

        animation = new BufferedImage[9][6];
        for(int j = 0; j < animation.length; j++) {
            for (int i = 0; i < animation[j].length; i++) {
                animation[j][i] = img.getSubimage(i * 64, j* 40, 64, 40);
            }
        }

    }

    private void importImg(){
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    public void setDirection(int direction){
        this.playerDirection = direction;
        moving = true;
    }

    public void setMoving(boolean moving){
        this.moving = moving;
    }
    private void updateAnimationTick() {
        aniTick++;
        if(aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if(aniIndex >= getSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }
    }
    private void setAnimation() {
        if(moving) {
            playerAction = RUNNING;
        } else playerAction = IDLE;
    }
    private void updatePos() {
        if(moving){
            switch(playerDirection){
                case LEFT:
                    xDelta-=5;
                    break;
                case UP:
                    yDelta-=5;
                    break;
                case RIGHT:
                    xDelta+=5;
                    break;
                case DOWN:
                    yDelta+=5;
                    break;
            }
        }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        updateAnimationTick();
        setAnimation();
        updatePos();
        g.drawImage(animation[playerAction][aniIndex], (int)xDelta,(int) yDelta,256,160,null);
    }




}