package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jframe;
    //GameWindow TEM UM GamePanel
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(gamePanel); //Jframe adicione componente gamePanel, o qual foi agregado ao GameWindow!
        jframe.setLocationRelativeTo(null);
        jframe.pack();
        jframe.setResizable(false);
        jframe.setVisible(true);
    }
}
