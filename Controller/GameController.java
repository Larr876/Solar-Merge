package Controller;

import View.GameFrame;
import View.GamePanel;
import java.awt.event.*;
import javax.swing.*;
import model.GameWorld;

public class GameController implements ActionListener, KeyListener {

    private GameWorld world;
    private GamePanel panel;
    private GameFrame frame;
    private Timer gameLoop;

    public GameController(GameWorld world, GamePanel panel, GameFrame frame) {
        this.world = world;
        this.panel = panel;
        this.frame = frame;

        gameLoop = new Timer(16, this);
    }

    public void startGame() {
        gameLoop.start();
    }

    public void update() {
        world.update();
        panel.repaint();
    }
    
    public void handleKeyPress(int keyCode) {
        if (keyCode == KeyEvent.VK_R) {
            world.reset();
        }

        if (keyCode == KeyEvent.VK_M) {
            world.reset();
            frame.showMenu();
        }
        if (world.isGameOver()) return;
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                world.moveSpawnPosition(-20);
                break;

            case KeyEvent.VK_RIGHT:
                world.moveSpawnPosition(20);
                break;

            case KeyEvent.VK_SPACE:
                world.dropPlanet();
                break;
        }
        panel.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
    }
  
    @Override
    public void keyPressed(KeyEvent e) {
        handleKeyPress(e.getKeyCode());
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}
