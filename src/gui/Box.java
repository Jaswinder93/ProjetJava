package gui;

enum Type {
    MAGIC,
    NEUTRAL,
    POISON
}

public class Box {

    private final int WEIGHT;
    private final Type TYPE;
    private final String name;

    private int currentPosition;

    // CONSTRUCTORS

    public Box(int weight, Type type, String name) {
        this.WEIGHT = weight;
        this.TYPE = type;
        this.name = name;
    }

}
