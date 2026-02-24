package View;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;

public class GameFont {

    private static Font baseFont;

    static {
        try {
            baseFont = Font.createFont(Font.TRUETYPE_FONT,GameFont.class.getResourceAsStream("/font/Pixel.ttf"));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            baseFont = new Font("Arial", Font.BOLD, 20);
        }
    }

    public static Font get(float size) {
        return baseFont.deriveFont(size);
    }
}