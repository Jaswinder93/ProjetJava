package game;

enum Type {
    MAGIC,
    POISON
}

public class Box {

    private final Type TYPE;

    private int currentPosition;

    // CONSTRUCTORS

    public Box(Type type) {
        this.TYPE = type;
    }

    // GETTERS

    public Type getType() {
        return this.TYPE;
    }

}
