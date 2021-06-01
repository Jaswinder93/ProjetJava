package gui;

public class Text {

    public static String WELCOME_STRING_1() {
        return "Bienvenue dans la fôret !\n" +
                "Aujourd'hui vous allez devoir faire preuve de courage...\n" +
                "... car à tout moment, vous pouvez mourir !\n" +
                "En vous baladant dans la fôret, vous serez amenés à trouver des boites secretes.\n" +
                "Celles-ci peuvent vous faire gagner (boites magiques), ou vous faire perdre (boites poison).\n" +
                "C'est vous et votre chance !\n" +
                "Pour gagner, il vous suffit de ramasser trois boites magiques.\n" +
                "De la même maniere, si vous ramassez trois boites contenant du poison, vous perdez.\n" +
                "On vous indiquera les effets des boites à chaque fois.\n";
    }
    public static String WELCOME_STRING_2() {
        return "Certaines boites sont trop lourdes pour vous, ce sont celles qui pèsent " +
                "plus de 100 grammes. Malheureusement, vous ne connaîtrez jamais leur effet.\n" +
                "De plus, vous avez une certaine masse totale à ne pas dépasser.\n" +
                "Vous ne pouvez porter plus de 700 grammes.\n" +
                "Vous pouvez adopter des chiens pour qu'ils vous tiennent compagnie.\n" +
                "De même, vous pouvez les libérer si vous en êtes lassé.\n";
    }

    public static String PLAYER_STATUS() {
        return "Statut\n" +
                "\n" +
                " # Santé: " + Window.getPlayer().getHealth() + " / " + Window.getPlayer().getMaxHealth() + "\n" +
                " # Faim: " + Window.getPlayer().getHunger() + " / " + Window.getPlayer().getMaxHunger() + "\n" +
                " # Soif: " + Window.getPlayer().getThirst() + " / " + Window.getPlayer().getMaxThirst() + "\n" +
                " # Energie: " + Window.getPlayer().getStamina() + " / " + Window.getPlayer().getMaxStamina() + "\n" +
                " # Poids: " + Window.getPlayer().getWeight() + " / " + Window.getPlayer().getMaxWeight() + "\n" +
                " # Nourriture: " + Window.getPlayer().getFood() + "\n" +
                " # Eau: " + Window.getPlayer().getWater() + "\n" +
                " # Boite magique: " + Window.getPlayer().getNbMagicBox() + " / 3\n" +
                " # Boite poison: " + Window.getPlayer().getNbPoisonBox() + " / 3\n" +
                "\n" +
                Window.displayCurrentPositionInfos();
    }

}
