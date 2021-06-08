package gui.animal;

enum Role {
    PREY,
    PREDATOR
}

public abstract class Animal {

    private final String species;
    private final Role role;
    private final boolean tamable;
    private static int riposteChance;
    private static int attack;
    private static int food;

    // CONSTRUCTORS

    public Animal(String species, Role role, boolean tamable, int riposteChance, int attack, int food) {
        this.species = species;
        this.role = role;
        this.tamable = tamable;
        Animal.riposteChance = riposteChance;
        Animal.attack = attack;
        Animal.food = food;
    }

    // METHODS

    public boolean isPrey() {
        return this.role == Role.PREY;
    }
    public boolean isPredator() {
        return this.role == Role.PREDATOR;
    }

    // GETTERS

    public String getSpecies() { return this.species; }
    public Role getRole() {
        return this.role;
    }
    public boolean isTamable() { return this.tamable; }
    public static int getRiposteChance() { return riposteChance; }
    public static int getAttack() { return attack; }
    public static int getFood() { return food; }

}
