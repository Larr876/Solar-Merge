package View;

import Controller.GameController;
import java.awt.CardLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.GameWorld;

public class GameFrame extends JFrame{

    private CardLayout cardLayout;
    private JPanel container;

    private GamePanel gamePanel;
    private GameMenu menuPanel;

    public GameFrame() throws HeadlessException {
        cardLayout = new CardLayout();
        container = new JPanel(cardLayout);

        this.setTitle("Solar Merge");
        this.setSize(GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameWorld world = new GameWorld();
        gamePanel = new GamePanel(world);
        menuPanel = new GameMenu(this);

        GameController controller = new GameController(world, gamePanel, this);
        controller.startGame();

        gamePanel.addKeyListener(controller);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        this.add(gamePanel);
        this.pack();
        this.setVisible(true);

        container.add(gamePanel, "Game");
        container.add(menuPanel, "Menu");
        this.add(container);

        showMenu();
    }

    public void showMenu() {
        cardLayout.show(container, "Menu");
    }

    public void showGame() {
        cardLayout.show(container, "Game");
        gamePanel.requestFocusInWindow();
    }
}
