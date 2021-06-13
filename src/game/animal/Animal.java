package game.animal;

enum Role {
    PREY,
    PREDATOR
}

public abstract class Animal {

    private final String species;


    // CONSTRUCTORS

    public Animal(String species) {
        this.species = species;
    }

    // GETTERS

    public String getSpecies() { return this.species; }

}
