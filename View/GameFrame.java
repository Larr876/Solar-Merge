package View;

import Controller.GameController;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import model.GameWorld;

public class GameFrame extends JFrame{

    public GameFrame() throws HeadlessException {
        this.setTitle("Solar Merge");
        this.setSize(GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameWorld world = new GameWorld();
        GamePanel panel = new GamePanel(world);
        GameController controller = new GameController(world, panel);

        controller.startGame();
        panel.addKeyListener(controller);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        this.add(panel);
        this.pack();
        this.setVisible(true);
    }
}
