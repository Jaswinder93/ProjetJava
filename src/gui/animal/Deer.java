package gui.animal;

public class Deer extends Animal {

    private static int riposteChance;
    private static int attack;
    private static int food;

    public Deer() {
        super("Deer", Role.PREY);
        Deer.riposteChance = 10;
        Deer.attack = 20;
        Deer.food = 20;
    }

    public static int getRiposteChance() { return riposteChance; }
    public static int getAttack() { return attack; }
    public static int getFood() { return food; }

}
