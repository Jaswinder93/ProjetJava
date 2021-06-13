package gui.animal;

public class Dog extends Animal {

    private static int riposteChance;
    private static int attack;
    private static int food;

    public Dog() {
        super("Dog", Role.PREDATOR);
        Dog.riposteChance = 50;
        Dog.attack = 30;
        Dog.food = 50;
    }

    public static int getRiposteChance() { return riposteChance; }
    public static int getAttack() { return attack; }
    public static int getFood() { return food; }

}
