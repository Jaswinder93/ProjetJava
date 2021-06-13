package game;

import java.util.HashMap;
import java.util.Map;

public class Text {

    public static String WELCOME_STRING_1() {
        return "Bienvenue dans la fôret !\n" +
                "Aujourd'hui vous allez devoir faire preuve de courage...\n" +
                "... car à tout moment, vous pouvez mourir !\n" +
                "En vous baladant dans la fôret, vous serez amenés à trouver des boites secretes.\n" +
                "Celles-ci peuvent vous faire gagner (boites magiques), ou vous faire perdre (boites poison).\n" +
                "C'est vous et votre chance !\n" +
                "Pour gagner, il vous suffit de ramasser trois boites magiques.\n" +
                "De la même maniere, si vous ramassez trois boites contenant du poison, vous perdez.\n";
    }
    public static String WELCOME_STRING_2() {
        return "Vous ne connaitrez jamais l'effet d'une boite avant de l'avoir ramassée.\n" +
                "De plus, celles-ci pesent toutes 100 grammes, il faudra vous organiser et dompter des chiens pour" +
                "leur faire porter vos affaires.\n\n" +
                "Vous avez une certaine masse totale à ne pas dépasser.\n" +
                "Faites donc attention à vos affaire.\n\n" +
                "Vous devrez vous nourir et boire, pour cela domptez la nature !\n\n" +
                "Mais attention la nature veut votre mort.";
    }

    public static String PLAYER_STATUS() {
        return "Username: " + Window.getUsername() +
                "\n\n" +
                "Score: " + Window.getScore() +
                "\n\n" +
                "Statut\n" +
                " # Santé: " + Window.getPlayer().getHealth() + " / " + Window.getPlayer().getMaxHealth() + "\n" +
                " # Faim: " + Window.getPlayer().getHunger() + " / " + Window.getPlayer().getMaxHunger() + "\n" +
                " # Soif: " + Window.getPlayer().getThirst() + " / " + Window.getPlayer().getMaxThirst() + "\n" +
                " # Energie: " + Window.getPlayer().getStamina() + " / " + Window.getPlayer().getMaxStamina() + "\n" +
                " # Poids: " + Window.getPlayer().getWeight() + " / " + Window.getPlayer().getMaxWeight() + "\n" +
                " # Nourriture: " + Window.getPlayer().getFood() + "\n" +
                " # Eau: " + Window.getPlayer().getWater() + "\n" +
                " # Boite magique: " + Window.getPlayer().getNbMagicBox() + " / 3\n" +
                " # Boite poison: " + Window.getPlayer().getNbPoisonBox() + " / 3\n" +
                " # Chiens: " + Window.getPlayer().getNbDogs() + "\n" +
                "\n" +
                Window.displayCurrentPositionInfos();
    }

    public static String HIGH_SCORES(HashMap<String, Integer> mapScore) {
        String str = "";
        for (Map.Entry<String, Integer> a : mapScore.entrySet()) {
            str += " # " + a.getKey() + " => " + a.getValue() + "\n";
        }
        return str;
    }

}
