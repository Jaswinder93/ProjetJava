package game;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Button extends JButton {

    private final String FONT = "Book Antiqua";

    public Button(String text, int x, int y, int width, int height, int fontSize) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font(FONT, Font.PLAIN, fontSize));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBorder(new LineBorder(Color.WHITE));
    }

    public Button(String text, int width, int height, int fontSize) {
        this.setText(text);
        this.setPreferredSize(new Dimension(width, height));
        this.setFont(new Font(FONT, Font.PLAIN, fontSize));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.WHITE);
        this.setFocusPainted(false);
        this.setBorder(new LineBorder(Color.WHITE));
    }

}
