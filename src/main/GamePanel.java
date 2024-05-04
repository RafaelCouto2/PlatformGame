package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.JPanel;
import java.awt.*;


public class GamePanel extends JPanel {
    private Game game;
    private MouseInputs mouseInputs;
    public GamePanel(Game game) {
        this.game = game;
        mouseInputs = new MouseInputs(this);

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }

    private void setPanelSize(){
        Dimension size = new Dimension(1280, 800);
        this.setMinimumSize(size);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    public void updateGame() {

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    //GETTERS

    public Game getGame() {
        return this.game;
    }

}
