package gui;

import javax.swing.*;
import java.awt.*;

enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST
}

public class Player extends JPanel {

//    private final ArrayList<Chien> chiens;
//    private Chien chienAdoptableVoulu;
//    private Chien chienLiberable;

    // STATS

    private int health = 50;
    private int maxHealth = 50;
    private boolean alive = true;
    private int hunger = 100;
    private int maxHunger = 100;
    private int thirst = 100;
    private int maxThirst = 100;
    private int stamina = 100;
    private int maxStamina = 100;
    private int weight = 0;
    private int maxWeight = 1000;
    private int nbMagicBox = 0;
    private int nbPoisonBox = 0;

    // GAME RELATIVE

    private int currentPosition;

    // CONSTRUCTORS

    public Player() {
        this.setBackground(Color.YELLOW);
        this.setBounds(95, 75, 10, 10);
        this.currentPosition = 10;
    }

    // METHODS

    public boolean move(Direction direction) {
        switch (direction) {
            case NORTH:
                if (this.currentPosition - 4 < 0) {
                    System.out.println("DEBUG: [Player.java] => Cannot move north");
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition -= 4;
                    System.out.println("DEBUG: Current position => " + currentPosition);
                    return true;
                }

            case EAST:
                if (this.currentPosition + 1 == 4 || this.currentPosition + 1 == 8 || this.currentPosition + 1 == 12) {
                    System.out.println("DEBUG: [Player.java] => Cannot move east");
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition += 1;
                    System.out.println("DEBUG: Current position => " + currentPosition);
                    return true;
                }

            case SOUTH:
                if (this.currentPosition + 4 > 11) {
                    System.out.println("DEBUG: [Player.java] => Cannot move south");
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition += 4;
                    System.out.println("DEBUG: Current position => " + currentPosition);
                    return true;
                }

            case WEST:
                if (this.currentPosition - 1 == -1 || this.currentPosition - 1 == 3 || this.currentPosition - 1 == 7) {
                    System.out.println("DEBUG: [Player.java] => Cannot move west");
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition -= 1;
                    System.out.println("DEBUG: Current position => " + currentPosition);
                    return true;
                }

            default:
                System.out.println("DEBUG: [Player.java] => Incorrect direction");
                return false;
        }

    }
    private boolean checkHealthCondition() {
        this.stamina -= 10;
        if (this.stamina < 0) {
            this.stamina = 0;
            return true;
        }

        this.hunger -= 5;
        if (this.hunger < 0) {
            this.hunger = 0;
        }
        this.thirst -= 5;
        if (this.thirst < 0) {
            this.thirst = 0;
        }

        if (this.hunger == 0 || this.thirst == 0) {
            this.health -= 10;
            if (this.health < 0 || this.health == 0) {
                this.health = 0;
                this.alive = false;
            }
        }

        return false;
    }
    public void sleep(int time) {
        for (int i = 0; i < time; i++) {
            this.stamina += 5;
            if (this.stamina > this.maxStamina) {
                this.stamina = this.maxStamina;
            }
            this.hunger -= 2;
            if (this.hunger < 0) {
                this.hunger = 0;
            }
            this.thirst -= 2;
            if (this.thirst < 0) {
                this.thirst = 0;
            }
            // TODO For each hour can be attacked (%)
        }
    }

    // GETTERS

    public int getHealth() {
        return this.health;
    }
    public int getMaxHealth() {
        return this.maxHealth;
    }
    public boolean isAlive() {
        return this.alive;
    }
    public int getHunger() {
        return this.hunger;
    }
    public int getMaxHunger() {
        return this.maxHunger;
    }
    public int getThirst() {
        return this.thirst;
    }
    public int getMaxThirst() {
        return this.maxThirst;
    }
    public int getStamina() {
        return this.stamina;
    }
    public int getMaxStamina() {
        return this.maxStamina;
    }
    public int getWeight() {
        return this.weight;
    }
    public int getMaxWeight() {
        return this.maxWeight;
    }
    public int getNbMagicBox() {
        return this.nbMagicBox;
    }
    public int getNbPoisonBox() {
        return this.nbPoisonBox;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

}
