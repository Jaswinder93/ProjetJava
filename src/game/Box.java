package game;

enum Type {
    MAGIC,
    POISON
}

public class Box {

    private final int WEIGHT;
    private final Type TYPE;
    private final String NAME;

    private int currentPosition;

    // CONSTRUCTORS

    public Box(int weight, Type type, String name) {
        this.WEIGHT = weight;
        this.TYPE = type;
        this.NAME = name;
    }

    // GETTERS

    public Type getType() {
        return this.TYPE;
    }

}
