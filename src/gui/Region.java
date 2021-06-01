package gui;

import gui.animal.Animal;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class Region extends JPanel {

    private Box box;
    private ArrayList<Animal> animals = new ArrayList<>();

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

    public void addAnimal(Animal animal) {
        this.animals.add(animal);
    }

    public int getNbBears() {
        int nb = 0;
        for (Animal animal : animals) {
            if (animal.getSpecie().equals("Bear")) {
                nb += 1;
            }
        }
        return nb;
    }
    public int getNbDogs() {
        int nb = 0;
        for (Animal animal : animals) {
            if (animal.getSpecie().equals("Dog")) {
                nb += 1;
            }
        }
        return nb;
    }
    public int getNbDeers() {
        int nb = 0;
        for (Animal animal : animals) {
            if (animal.getSpecie().equals("Deer")) {
                nb += 1;
            }
        }
        return nb;
    }

    // GETTERS

    public Box getBox() {
        return this.box;
    }

}
