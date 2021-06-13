package game.graphics;

import javax.swing.*;
import java.awt.*;

public class Label extends JLabel {

    public Label(String text, int x, int y, int width, int height, int fontSize) {
        this.setText(text);
        this.setBounds(x, y, width, height);
        this.setFont(new Font("Book Antiqua", Font.PLAIN, fontSize));
        this.setForeground(Color.WHITE);
    }

}
