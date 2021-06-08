package gui;

import gui.animal.Animal;
import gui.animal.Bear;
import gui.animal.Deer;
import gui.animal.Dog;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

enum HuntResult {
    RIPOSTE,
    KILL,
    NO_ANIMAL
}

public class Region extends JPanel {

    Random random = new Random();

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

    public HuntResult killBear() {
        if (this.getNbBears() > 0) {
            int riposte = random.nextInt(100);
            if (riposte < Bear.getRiposteChance()) {
                System.out.println("DEBUG: Bear riposte");
                return HuntResult.RIPOSTE;
            } else {
                for (Animal animal : this.animals) {
                    if (animal.getSpecies().equals("Bear")) {
                        this.animals.remove(animal);
                        return HuntResult.KILL;
                    }
                }
                return HuntResult.NO_ANIMAL;
            }
        } else {
            return HuntResult.NO_ANIMAL;
        }
    }
    public HuntResult killDog() {
        if (this.getNbDogs() > 0) {
            int riposte = random.nextInt(100);
            if (riposte < Dog.getRiposteChance()) {
                System.out.println("DEBUG: Dog riposte");
                return HuntResult.RIPOSTE;
            } else {
                for (Animal animal : this.animals) {
                    if (animal.getSpecies().equals("Dog")) {
                        this.animals.remove(animal);
                        return HuntResult.KILL;
                    }
                }
                return HuntResult.NO_ANIMAL;
            }
        } else {
            return HuntResult.NO_ANIMAL;
        }
    }
    public HuntResult killDeer() {
        if (this.getNbDeers() > 0) {
            int riposte = random.nextInt(100);
            if (riposte < Deer.getRiposteChance()) {
                System.out.println("DEBUG: Deer riposte");
                return HuntResult.RIPOSTE;
            } else {
                for (Animal animal : this.animals) {
                    if (animal.getSpecies().equals("Deer")) {
                        this.animals.remove(animal);
                        return HuntResult.KILL;
                    }
                }
                return HuntResult.NO_ANIMAL;
            }
        } else {
            return HuntResult.NO_ANIMAL;
        }
    }

    public int getNbBears() {
        int nb = 0;
        for (Animal animal : this.animals) {
            if (animal.getSpecies().equals("Bear")) {
                nb += 1;
            }
        }
        return nb;
    }
    public int getNbDogs() {
        int nb = 0;
        for (Animal animal : animals) {
            if (animal.getSpecies().equals("Dog")) {
                nb += 1;
            }
        }
        return nb;
    }
    public int getNbDeers() {
        int nb = 0;
        for (Animal animal : animals) {
            if (animal.getSpecies().equals("Deer")) {
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
