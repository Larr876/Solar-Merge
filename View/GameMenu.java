package View;

import java.awt.*;
import javax.swing.*;

public class GameMenu extends JPanel {

    public GameMenu(GameFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        JLabel title = new JLabel("SOLAR MERGE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(GameFont.get(120f));
        title.setForeground(Color.YELLOW);
        

        JButton startButton = createButton("Start");
        JButton exitButton = createButton("Exit");

        startButton.addActionListener(e -> frame.showGame());
        exitButton.addActionListener(e -> System.exit(0));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 80)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setFont(GameFont.get(50f));
        button.setMaximumSize(new Dimension(200, 50));
        return button;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawBackground(g);
        drawStar(g);
    }

    private void drawBackground(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        GradientPaint gp = new GradientPaint(0, 0, new Color(10, 10, 40),0, GameConfig.BOARDHEIGHT, new Color(4, 4, 77));

        g2.setPaint(gp);
        g2.fillRect(0, 0, GameConfig.BOARDWIDTH, GameConfig.BOARDHEIGHT);
    }

    private void drawStar(Graphics g) {
        g.setColor(Color.WHITE);

        for (int i = 0; i < 100; i++) {
            int starX = (int)(Math.random() * GameConfig.BOARDWIDTH);
            int starY = (int)(Math.random() * GameConfig.BOARDHEIGHT);
            g.fillRect(starX, starY, 2, 2);
        }
    }
}