package gui.animal;

public class Bear extends Animal {

    private static int riposteChance;
    private static int attack;
    private static int food;

    public Bear() {
        super("Bear", Role.PREDATOR);
        Bear.riposteChance = 80;
        Bear.attack = 50;
        Bear.food = 100;
    }

    public static int getRiposteChance() { return riposteChance; }
    public static int getAttack() { return attack; }
    public static int getFood() { return food; }
}
