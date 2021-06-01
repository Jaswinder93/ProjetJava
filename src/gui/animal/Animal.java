package gui.animal;

enum Role {
    PREY,
    PREDATOR
}

public abstract class Animal {

    private final String species;
    private final Role role;
    private final boolean tamable;

    // CONSTRUCTORS

    public Animal(String species, Role role, boolean tamable) {
        this.species = species;
        this.role = role;
        this.tamable = tamable;
    }

    // METHODS

    public boolean isPrey() {
        return this.role == Role.PREY;
    }
    public boolean isPredator() {
        return this.role == Role.PREDATOR;
    }

    // GETTERS

    public String getSpecie() { return this.species; }
    public Role getRole() {
        return this.role;
    }
    public boolean isTamable() { return this.tamable; }

}
