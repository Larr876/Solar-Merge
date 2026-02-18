package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Boundary;
import model.GameWorld;
import model.Planet;

public class GamePanel extends JPanel{

    private GameWorld world;
    // private Map<PlanetType, Image> images;
    private Image bg;

    public GamePanel(GameWorld world) {
        this.world = world;
        // bg = new ImageIcon(getClass().getResource("/img/bg.jpg")).getImage();

        setPreferredSize(new Dimension(GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT));
        setBackground(Color.BLACK);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        drawPlanets(g);
        drawScore(g);
        drawSpawnCursor(g);
        drawBoundary(g);
    }

    private void drawBackground(Graphics g) {
        // g.drawImage(bg, 0, 0, GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT, null);
    }

    private void drawPlanets(Graphics g) {
    //     for (Planet p : world.getPlanets()) {
    //         Image img = images.get(p.getType());

    //         int x = (int) p.getX();
    //         int y = (int) p.getY();
    //         int size = (int)(p.getRadius() * 2);

    //         g.drawImage(img, x, y, size, size, null);
    //     }
         for (Planet p : world.getPlanets()) {
            Image img = new ImageIcon(getClass().getResource("/img/world.png")).getImage();

            int x = (int) p.getX();
            int y = (int) p.getY();
            int size = (int)(p.getRadius() * 2);

            g.drawImage(img, x - (int) p.getRadius(), y - (int) p.getRadius(), size, size, null);
        }
    }

    private void drawScore(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Score: " + world.getScore(), 20, 30);
        g.drawString("Highest score: " + world.getHighestScore(), 20, 60);
    }

    private void drawSpawnCursor(Graphics g) {
        int x = (int) world.getSpawnX();
        Image img = new ImageIcon(getClass().getResource("/img/world.png")).getImage();
        int radius = (int) world.getNextPlanet().getRadius();
        int size = radius * 2;

        g.setColor(Color.YELLOW);
        g.drawLine(x, (int) Boundary.TOP, x, (int) Boundary.FLOOR);
        g.drawImage(img, x - radius, (int) Boundary.TOP - radius, size, size, null);
    }

    private void drawBoundary(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawLine((int) Boundary.LEFT, (int) Boundary.TOP, (int) Boundary.LEFT, (int) Boundary.FLOOR);
        g.drawLine((int) Boundary.RIGHT, (int) Boundary.TOP, (int) Boundary.RIGHT, (int) Boundary.FLOOR);
        g.drawLine((int) Boundary.LEFT, (int) Boundary.FLOOR, (int) Boundary.RIGHT, (int) Boundary.FLOOR);
    }
}
