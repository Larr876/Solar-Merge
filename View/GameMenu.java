package View;

import java.awt.*;
import javax.swing.*;

public class GameMenu extends JPanel {

    public GameMenu(GameFrame frame) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.BLACK);

        JLabel title = new JLabel("SOLAR MERGE");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Arial", Font.BOLD, 48));
        title.setForeground(Color.ORANGE);

        JButton startButton = createButton("Start Game");
        JButton exitButton = createButton("Exit");

        startButton.addActionListener(e -> frame.showGame());
        exitButton.addActionListener(e -> System.exit(0));

        add(Box.createVerticalGlue());
        add(title);
        add(Box.createRigidArea(new Dimension(0, 50)));
        add(startButton);
        add(Box.createRigidArea(new Dimension(0, 20)));
        add(exitButton);
        add(Box.createVerticalGlue());
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 20));
        button.setMaximumSize(new Dimension(200, 50));
        return button;
    }
}