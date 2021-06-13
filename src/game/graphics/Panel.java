package game.graphics;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Panel extends JPanel {

    public Panel(int x, int y, int width, int height, boolean borderLine) {
        this.setBounds(x, y, width, height);
        this.setBackground(Color.BLACK);
        if (borderLine) {
            this.setBorder(new LineBorder(Color.WHITE));
        }
    }

}
