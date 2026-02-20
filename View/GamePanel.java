package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import model.Boundary;
import model.GameWorld;
import model.Planet;
import model.PlanetType;

public class GamePanel extends JPanel{

    private GameWorld world;
    private Map<PlanetType, Image> images;
    private Image bg;

    public GamePanel(GameWorld world) {
        this.world = world;
        loadImage();

        setPreferredSize(new Dimension(GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT));
        setBackground(Color.BLACK);
    }

    private void loadImage() {
        // bg = new ImageIcon(getClass().getResource("/img/bg.jpg")).getImage();
        // images.put(PlanetType.MERCURY, new ImageIcon(getClass().getResource("/img/mercury.jpg")).getImage());
        // images.put(PlanetType.MARS, new ImageIcon(getClass().getResource("/img/mars.jpg")).getImage());
        // images.put(PlanetType.VENUS, new ImageIcon(getClass().getResource("/img/venus.jpg")).getImage());
        // images.put(PlanetType.EARTH, new ImageIcon(getClass().getResource("/img/earth.jpg")).getImage());
        // images.put(PlanetType.NEPTUNE, new ImageIcon(getClass().getResource("/img/neptune.jpg")).getImage());
        // images.put(PlanetType.URANUS, new ImageIcon(getClass().getResource("/img/uranus.jpg")).getImage());
        // images.put(PlanetType.SATURN, new ImageIcon(getClass().getResource("/img/saturn.jpg")).getImage());
        // images.put(PlanetType.JUPITER, new ImageIcon(getClass().getResource("/img/jupiter.jpg")).getImage());
        // images.put(PlanetType.SUN, new ImageIcon(getClass().getResource("/img/sun.jpg")).getImage());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        drawPlanets(g);
        drawScore(g);
        drawSpawnCursor(g);
        drawBoundary(g);
        if (world.isGameOver()) {
            drawGameOver(g);
        }
    }

    private void drawBackground(Graphics g) {
        // g.drawImage(bg, 0, 0, GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT, null);
    }

    private void drawPlanets(Graphics g) {
    //     for (Planet p : world.getPlanets()) {
    //         Image img = images.get(p.getType());

                // int x = (int) p.getX() - (int) p.getRadius();
    //         int y = (int) p.getY() - (int) p.getRadius();
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

    private void drawGameOver(Graphics g) {
        g.setColor(Color.YELLOW);
        g.setFont(new Font("Impact", Font.BOLD, 70));
        g.drawString("Game Over", GameConfig.BOARDWIDTH / 2 - 150, 70);

        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("press r to reset", GameConfig.BOARDWIDTH / 2 - 70, 90);

        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("press m to return to menu", GameConfig.BOARDWIDTH / 2 - 70, 110);
    }
}
