package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Region extends JPanel {

    private Box box;

    // CONSTRUCTORS

    public Region(String name) {
        this.setBackground(Color.BLACK);
        this.setBorder(new LineBorder(Color.WHITE));
        this.setName(name);
        this.setLayout(null);

        JLabel label = new JLabel(this.getName());
        label.setBounds(0, 0, 200, 50);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(label);
    }

    // METHODS

    public void addBox(Box box) {
        if (this.box == null) {
            this.box = box;
        } else {
            System.out.println("DEBUG: [Region.java] => Already a box in " + this.getName());
        }
    }
    public void removeBox() {
        if (this.box != null) {
            this.box = null;
        } else {
            System.out.println("DEBUG: [Region.java] => No box in " + this.getName());
        }
    }
    public Boolean containBox() {
        return (box != null);
    }

    // GETTERS

    public Box getBox() {
        return this.box;
    }

}
