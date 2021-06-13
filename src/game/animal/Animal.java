package game.animal;

enum Role {
    PREY,
    PREDATOR
}

public abstract class Animal {

    private final String species;
    private final Role role;


    // CONSTRUCTORS

    public Animal(String species, Role role) {
        this.species = species;
        this.role = role;
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

}
