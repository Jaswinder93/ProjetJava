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
    private int weight;
    private int maxWeight = 1000;
    private int food = 20;
    private int water = 20;
    private int nbMagicBox = 0;
    private int nbPoisonBox = 0;

    // GAME RELATIVE

    private int currentPosition;

    // CONSTRUCTORS

    public Player() {
        this.setBackground(Color.YELLOW);
        this.setBounds(95, 75, 10, 10);
        this.currentPosition = 10;
        this.weight = this.food + this.water;
    }

    // METHODS

    public boolean move(Direction direction) {
        switch (direction) {
            case NORTH:
                if (this.currentPosition - 4 < 0) {
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition -= 4;
                    return true;
                }

            case EAST:
                if (this.currentPosition + 1 == 4 || this.currentPosition + 1 == 8 || this.currentPosition + 1 == 12) {
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition += 1;
                    return true;
                }

            case SOUTH:
                if (this.currentPosition + 4 > 11) {
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition += 4;
                    return true;
                }

            case WEST:
                if (this.currentPosition - 1 == -1 || this.currentPosition - 1 == 3 || this.currentPosition - 1 == 7) {
                    return false;
                } else {
                    if (this.checkHealthCondition()) {
                        return false;
                    }
                    this.currentPosition -= 1;
                    return true;
                }

            default:
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
            this.checkAlive();
        }

        return false;
    }
    public void checkAlive() {
        if (this.health <= 0) {
            this.health = 0;
            this.alive = false;
        }
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
    public void eat() {
        int quantity = this.maxHunger - this.hunger;
        if (quantity > 10) {
            quantity = 10;
        }
        if (this.food >= quantity && this.hunger < this.maxHunger) {
            this.hunger += quantity;
            this.food -= quantity;
            this.weight -= quantity;
        }
    }
    public void drink() {
        int quantity = this.maxThirst - this.thirst;
        if (quantity > 10) {
            quantity = 10;
        }
        if (this.water >= quantity && this.thirst < this.maxThirst) {
            this.thirst += quantity;
            this.water -= quantity;
            this.weight -= quantity;
        }
    }

    public void takeWater() {
        if (this.stamina > 0) {
            this.water += 10;
            this.weight += 10;
            this.stamina -= 5;
        }
    }
    public void takeBox(Type type) {
        switch (type) {
            case MAGIC -> this.nbMagicBox += 1;
            case POISON -> this.nbPoisonBox += 1;
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
    public int getFood() { return this.food; }
    public int getWater() { return this.water; }
    public int getNbMagicBox() {
        return this.nbMagicBox;
    }
    public int getNbPoisonBox() {
        return this.nbPoisonBox;
    }

    public int getCurrentPosition() {
        return this.currentPosition;
    }

    // SETTERS

    public void setHealth(int health) { this.health = health; }
    public void setFood(int food) { this.food = food; }

}
