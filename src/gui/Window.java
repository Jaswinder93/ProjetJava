package gui;

import gui.animal.Bear;
import gui.animal.Deer;
import gui.animal.Dog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Window {

    private static Player player;

    private JFrame window;

    Random random = new Random();

    // TITLE SCREEN

    private JLabel titleLabel;
    private JButton startButton;

    // GAME SCREEN

    private JPanel mapPanel, playerPanel, compassPanel, interfacePanel;
    private static ArrayList<Region> regionPanel;
    private JButton mainMenuButton, sleepButton, eatButton, drinkButton, takeButton; // Main interface
    private JButton tameButton, huntButton, takeWaterButton, grabBoxButton, returnButton; // Take interface
    private JButton bearButton, dogButton, deerButton; // Tame & Hunt interface
    private JButton northButton, eastButton, southButton, westButton; // Compass
    private JLabel compassLabel;
    private JTextArea displayedText;
    private JButton nextTextButton;
    private HashMap<Integer, Box> boxLocalization;

    private final String FONT = "Book Antiqua";
    private final int TITLE_FONT_SIZE = 70;
    private final int MAIN_TEXT_FONT_SIZE = 40;
    private final int TEXT_FONT_SIZE = 16;
    private final int SMALL_TEXT_FONT_SIZE = 14;

    // SLEEP SCREEN

    private JButton previousButton, nextButton, validateSleepButton;
    private JLabel sleepLabel, sleepHLabel, sleepTimeLabel;
    private int sleepTime;

    // DEATH SCREEN

    private JLabel deathLabel;
    private JTextArea resultText; // TODO score when dead

    // CONSTRUCTORS

    public Window() {
        this.init();
    }

    // METHODS

    private void init() {
        this.window = new JFrame("Kanji");
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);
        this.window.setResizable(false);
        this.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.window.getContentPane().setBackground(Color.BLACK);
        this.window.setLayout(null);

        this.createTitleScreen();

        this.window.setVisible(true);
    }
    private void launchGame() {
        this.createMapPanel();
        this.actualizePlayerPanel();
    }

    public void createTitleScreen() {
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.titleLabel = new Label("Kanji", 320, 100, 200, 90, TITLE_FONT_SIZE);
        this.window.add(this.titleLabel);

        this.startButton = new Button("Start", 350, 400, 100, 50, MAIN_TEXT_FONT_SIZE);
        this.startButton.addActionListener(e -> {
            this.titleLabel.setVisible(false);
            this.startButton.setVisible(false);
            this.createGameScreen();
       });
        this.window.add(this.startButton);
    }
    private void createGameScreen() {
        this.window.setSize(1200, 600);
        this.window.setLocationRelativeTo(null);

        this.mapPanel = new Panel(50, 50, 800, 400, true);
        this.mapPanel.setLayout(new GridLayout(3, 4));
        this.window.add(this.mapPanel);

        this.playerPanel = new Panel(900, 50, 250, 480, true);
        this.window.add(this.playerPanel);

        this.displayedText = new JTextArea(Text.WELCOME_STRING_1());
        this.displayedText.setPreferredSize(new Dimension(230, 400));
        this.displayedText.setFont(new Font(FONT, Font.PLAIN, SMALL_TEXT_FONT_SIZE));
        this.displayedText.setLineWrap(true);
        this.displayedText.setWrapStyleWord(true);
        this.displayedText.setEnabled(false);
        this.displayedText.setBackground(Color.BLACK);
        this.displayedText.setForeground(Color.WHITE);
        this.playerPanel.add(this.displayedText);

        this.nextTextButton = new Button("Next",200, 50, TEXT_FONT_SIZE);
        this.nextTextButton.addActionListener(e -> {
            this.displayedText.setText(Text.WELCOME_STRING_2());
            this.nextTextButton.setText("Start Adventure");
            this.nextTextButton.removeActionListener(this.nextTextButton.getActionListeners()[0]);
            this.nextTextButton.addActionListener(e1 -> {
                this.mapPanel.setBorder(null);
                this.nextTextButton.setVisible(false);
                this.launchGame();
            });
        });
        this.playerPanel.add(this.nextTextButton);

        this.interfacePanel = new Panel(50, 480, 800, 50, false);
        this.interfacePanel.setLayout(null);
        this.window.add(this.interfacePanel);

        this.mainMenuButton = new Button("Main Menu", 0, 0, 100, 50, TEXT_FONT_SIZE);
        this.mainMenuButton.addActionListener(e -> {
            this.mapPanel.setVisible(false);
            this.playerPanel.setVisible(false);
            this.interfacePanel.setVisible(false);
            this.createTitleScreen();
        });
        this.interfacePanel.add(this.mainMenuButton);

        this.compassPanel = new Panel(600, 0, 200, 50, false);
        this.compassPanel.setLayout(new BorderLayout());
        this.interfacePanel.add(this.compassPanel);
    }
    private void createDeathScreen() {
        this.mapPanel.setVisible(false);
        this.playerPanel.setVisible(false);
        this.interfacePanel.setVisible(false);

        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.deathLabel = new Label("Vous êtes mort", 260, 100, 300, 90, MAIN_TEXT_FONT_SIZE);
        this.window.add(this.deathLabel);
    }

    private void createMapPanel() {
        this.initializeRegionPanel();

        player = new Player();
        regionPanel.get(player.getCurrentPosition()).add(player);

        this.initializeCompassPanel();
        this.initializeInterfaceButtons();
        this.initializeBoxes();
        this.initializeAnimal();
        this.showMainInterface();
    }
    private void createSleepTimeChoicePanel() {
        if (player.getStamina() == 100 || player.getHunger() == 0 || player.getThirst() == 0) {
            this.mapPanel.setVisible(true);
            this.playerPanel.setVisible(true);
            this.interfacePanel.setVisible(true);

            return;

            // TODO Add explanation text : "could not sleep when stamina = 100"
        }

        this.sleepLabel = new Label("Dormir: ", 360, 200, 600, 90, TITLE_FONT_SIZE);
        this.window.add(this.sleepLabel);

        this.sleepHLabel = new Label("h", 660, 200, 100, 90, TITLE_FONT_SIZE);
        this.window.add(this.sleepHLabel);

        this.sleepTimeLabel = new Label(String.valueOf(this.sleepTime), 620, 200, 100, 90, TITLE_FONT_SIZE);
        this.window.add(this.sleepTimeLabel);

        this.previousButton = new Button("<", 500, 300, 80, 50, MAIN_TEXT_FONT_SIZE);
        this.previousButton.addActionListener(e -> this.removeOneHour());
        this.window.add(this.previousButton);

        this.nextButton = new Button(">", 600, 300, 80, 50, MAIN_TEXT_FONT_SIZE);
        this.nextButton.addActionListener(e -> this.addOneHour());
        this.window.add(this.nextButton);

        this.validateSleepButton = new Button("Dormir", 500, 380, 160, 50, MAIN_TEXT_FONT_SIZE);
        this.validateSleepButton.addActionListener(e -> {
            player.sleep(this.sleepTime);
            this.sleepTime = 0;
            this.sleepLabel.setVisible(false);
            this.sleepHLabel.setVisible(false);
            this.sleepTimeLabel.setVisible(false);
            this.previousButton.setVisible(false);
            this.nextButton.setVisible(false);
            this.validateSleepButton.setVisible(false);

            this.mapPanel.setVisible(true);
            this.playerPanel.setVisible(true);
            this.interfacePanel.setVisible(true);

            this.actualizePlayerPanel();
        });
        this.window.add(this.validateSleepButton);
    }

    private void initializeInterfaceButtons() {
        this.initializeMainInterfaceButtons();
        this.initializeTakeInterfaceButtons();
        this.initializeAnimalInterfaceButtons();
        this.returnButton = new Button("Retour", 480, 0, 100, 50, TEXT_FONT_SIZE);
        this.returnButton.setVisible(false);
        this.interfacePanel.add(this.returnButton);
        this.interfacePanel.update(this.interfacePanel.getGraphics());
    }
    private void initializeMainInterfaceButtons() {
        this.sleepButton = new Button("Dormir" , 120, 0, 100, 50, TEXT_FONT_SIZE);
        this.sleepButton.addActionListener(e -> {
            this.mapPanel.setVisible(false);
            this.playerPanel.setVisible(false);
            this.interfacePanel.setVisible(false);
            this.createSleepTimeChoicePanel();
        });
        this.interfacePanel.add(this.sleepButton);
        this.sleepButton.setVisible(false);

        this.eatButton = new Button("Manger", 240, 0, 100, 50, TEXT_FONT_SIZE);
        this.eatButton.addActionListener(e -> {
            player.eat();
            this.actualizePlayerPanel();
        });
        this.interfacePanel.add(this.eatButton);
        this.eatButton.setVisible(false);

        this.drinkButton = new Button("Boire", 360, 0, 100, 50, TEXT_FONT_SIZE);
        this.drinkButton.addActionListener(e -> {
            player.drink();
            this.actualizePlayerPanel();
        });
        this.interfacePanel.add(this.drinkButton);
        this.drinkButton.setVisible(false);

        this.takeButton = new Button("Prendre", 480, 0, 100, 50, TEXT_FONT_SIZE);
        this.takeButton.addActionListener(e -> {
            this.hideMainInterface();
            this.showTakeInterface();
        });
        this.interfacePanel.add(this.takeButton);
        this.takeButton.setVisible(false);
    }
    private void initializeTakeInterfaceButtons() {
        this.tameButton = new Button("Dresser", 0, 0, 100, 50, TEXT_FONT_SIZE);
        this.tameButton.addActionListener(e -> {
            this.tame();
        });
        this.interfacePanel.add(this.tameButton);
        this.tameButton.setVisible(false);

        this.huntButton = new Button("Chasser", 120, 0, 100, 50, TEXT_FONT_SIZE);
        this.huntButton.addActionListener(e -> {
            this.hideTakeInterface();
            this.showHuntInterface();
        });
        this.interfacePanel.add(this.huntButton);
        this.huntButton.setVisible(false);

        this.takeWaterButton = new Button("Prendre eau", 240, 0, 100, 50, TEXT_FONT_SIZE);
        this.takeWaterButton.addActionListener(e -> {
            player.takeWater();
            this.actualizePlayerPanel();
        });
        this.interfacePanel.add(this.takeWaterButton);
        this.takeWaterButton.setVisible(false);

        this.grabBoxButton = new Button("Prendre boite", 360, 0, 100, 50, TEXT_FONT_SIZE);
        this.grabBoxButton.addActionListener(e -> {
            player.takeBox(this.getBoxType());
            this.removeBox();
            this.actualizePlayerPanel();
        });
        this.interfacePanel.add(this.grabBoxButton);
        this.grabBoxButton.setVisible(false);
    }
    private void initializeAnimalInterfaceButtons() {
        this.bearButton = new Button("Ours", 120, 0, 100, 50, TEXT_FONT_SIZE);
        this.interfacePanel.add(this.bearButton);
        this.bearButton.setVisible(false);

        this.dogButton = new Button("Chien", 240, 0, 100, 50, TEXT_FONT_SIZE);
        this.interfacePanel.add(this.dogButton);
        this.dogButton.setVisible(false);

        this.deerButton = new Button("Cerf", 360, 0, 100, 50, TEXT_FONT_SIZE);
        this.interfacePanel.add(this.deerButton);
        this.deerButton.setVisible(false);
    }

    private void initializeRegionPanel() {
        ArrayList<String> names = new ArrayList<>();
        names.add("North Swamp");     // 0
        names.add("Mountain");        // 1
        names.add("River");           // 2
        names.add("North Clearing");  // 3
        names.add("South Swamp");     // 4
        names.add("North Lake");      // 5
        names.add("West Clearing");   // 6
        names.add("East Clearing");   // 7
        names.add("Abandoned Hut");   // 8
        names.add("South Lake");      // 9
        names.add("Forest Entry");    // 10
        names.add("Mine");            // 11

        regionPanel = new ArrayList<>();
        for (String name : names) {
            Region region = new Region(name);
            regionPanel.add(region);
            this.mapPanel.add(region);
        }
    }
    private void initializeCompassPanel() {
        this.compassLabel = new JLabel("COMPASS");
        this.compassLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.compassPanel.add(this.compassLabel, BorderLayout.CENTER);

        this.northButton = this.initializeCompassButton("N", 0, 15, BorderLayout.NORTH);
        this.northButton.addActionListener(e -> {
            this.move(Direction.NORTH);
        });
        this.eastButton = this.initializeCompassButton("E", 50, 0, BorderLayout.EAST);
        this.eastButton.addActionListener(e -> {
            this.move(Direction.EAST);
        });
        this.southButton = this.initializeCompassButton("S", 0, 15, BorderLayout.SOUTH);
        this.southButton.addActionListener(e -> {
            this.move(Direction.SOUTH);
        });
        this.westButton = this.initializeCompassButton("W", 50, 0, BorderLayout.WEST);
        this.westButton.addActionListener(e -> {
            this.move(Direction.WEST);
        });
    }
    private JButton initializeCompassButton(String text, int width, int height, String borderLayout) {
        JButton button = new Button(text, width, height, SMALL_TEXT_FONT_SIZE);
        this.compassPanel.add(button, borderLayout);

        return button;
    }
    private void initializeBoxes() {
        this.boxLocalization = new HashMap<>();

        // Magic Boxes
        for (int i = 0; i < 3; i++) {
            int position;
            do {
                position = random.nextInt(11);
            } while (this.boxLocalization.get(position) != null);
            Box box = new Box(100, Type.MAGIC, "MAGIC BOX");
            regionPanel.get(position).addBox(box);
            this.boxLocalization.put(position, box);
        }

        // Poison Boxes
        for (int i = 0; i < 3; i++) {
            int position;
            do {
                position = random.nextInt(11);
            } while (this.boxLocalization.get(position) != null);
            Box box = new Box(100, Type.POISON, "POISON BOX");
            regionPanel.get(position).addBox(box);
            this.boxLocalization.put(position, box);
        }

    }
    private void initializeAnimal() {
        for (Region region : regionPanel) {

            // Bear
            int bearChance = random.nextInt(100);
            if (bearChance < 30) {
                int bearQuantity = random.nextInt(2);
                for (int i = 0; i < bearQuantity; i++) {
                    region.addAnimal(new Bear());
                }
            }

            // Dogs
            int dogChance = random.nextInt(100);
            if (dogChance < 50) {
                int dogQuantity = random.nextInt(3);
                for (int i = 0; i < dogQuantity; i++) {
                    region.addAnimal(new Dog());
                }
            }

            // Dogs
            int deerChance = random.nextInt(100);
            if (deerChance < 80) {
                int deerQuantity = random.nextInt(4);
                for (int i = 0; i < deerQuantity; i++) {
                    region.addAnimal(new Deer());
                }
            }

        }
    }

    private void showMainInterface() {
        this.mainMenuButton.setVisible(true);
        this.sleepButton.setVisible(true);
        this.eatButton.setVisible(true);
        this.drinkButton.setVisible(true);
        this.takeButton.setVisible(true);
        this.interfacePanel.update(this.interfacePanel.getGraphics());
    }
    private void hideMainInterface() {
        this.mainMenuButton.setVisible(false);
        this.sleepButton.setVisible(false);
        this.eatButton.setVisible(false);
        this.drinkButton.setVisible(false);
        this.takeButton.setVisible(false);
        this.interfacePanel.update(this.interfacePanel.getGraphics());
    }

    private void showTakeInterface() {
        if (this.regionHasDog()) {
            this.tameButton.setVisible(true);
        }
        if (this.regionHasAnimal()) {
            this.huntButton.setVisible(true);
        }
        if (this.regionHasWater()) {
            this.takeWaterButton.setVisible(true);
        }
        if (this.regionHasBox()) {
            this.grabBoxButton.setVisible(true);
        }

        for (ActionListener al : this.returnButton.getActionListeners()) {
            this.returnButton.removeActionListener(al);
        }
        this.returnButton.addActionListener(e -> {
            this.hideTakeInterface();
            this.showMainInterface();
        });
        this.returnButton.setVisible(true);
        this.interfacePanel.update(this.interfacePanel.getGraphics());
    }
    private void hideTakeInterface() {
        if (this.tameButton != null) {
            this.tameButton.setVisible(false);
        }
        if (this.huntButton != null) {
            this.huntButton.setVisible(false);
        }
        if (this.takeWaterButton != null) {
            this.takeWaterButton.setVisible(false);
        }
        if (this.grabBoxButton != null) {
            this.grabBoxButton.setVisible(false);
        }
    }

    private void showHuntInterface() {
        if (this.regionHasBear()) {
            this.bearButton.setVisible(true);
        }
        if (this.regionHasDog()) {
            this.dogButton.setVisible(true);
        }
        if (this.regionHasDeer()) {
            this.deerButton.setVisible(true);
        }

        for (ActionListener al : this.returnButton.getActionListeners()) {
            this.returnButton.removeActionListener(al);
        }
        this.returnButton.addActionListener(e -> {
            this.hideAnimalInterface();
            this.showTakeInterface();
        });
        this.returnButton.setVisible(true);
        this.interfacePanel.update(this.interfacePanel.getGraphics());

        // Bear
        for (ActionListener al : this.bearButton.getActionListeners()) {
            this.bearButton.removeActionListener(al);
        }
        this.bearButton.addActionListener(e -> {
            this.killBear();
            this.actualizePlayerPanel();
        });

        // Dog
        for (ActionListener al : this.dogButton.getActionListeners()) {
            this.dogButton.removeActionListener(al);
        }
        this.dogButton.addActionListener(e -> {
            this.killDog();
            this.actualizePlayerPanel();
        });

        // Deer
        for (ActionListener al : this.deerButton.getActionListeners()) {
            this.deerButton.removeActionListener(al);
        }
        this.deerButton.addActionListener(e -> {
            this.killDeer();
            this.actualizePlayerPanel();
        });
    }
    private void hideAnimalInterface() {
        if (this.bearButton != null) {
            this.bearButton.setVisible(false);
        }
        if (this.dogButton != null) {
            this.dogButton.setVisible(false);
        }
        if (this.deerButton != null) {
            this.deerButton.setVisible(false);
        }
    }

    private boolean regionHasBear() { return this.getNbBears() > 0; }
    private boolean regionHasDog() {
        return this.getNbDogs() > 0;
    }
    private boolean regionHasDeer() { return this.getNbDeers() > 0; }
    private boolean regionHasAnimal() {
        return this.getNbBears() > 0 || this.getNbDogs() > 0 ||this.getNbDeers() > 0;
    }
    private boolean regionHasWater() {
        return player.getCurrentPosition() == 2 || player.getCurrentPosition() == 5 || player.getCurrentPosition() == 9;
    }
    private boolean regionHasBox() {
        return regionPanel.get(player.getCurrentPosition()).containBox();
    }

    private void tame() {
        // TODO Tame
        if (regionHasDog()) {
            int tame = random.nextInt(100);
            if (tame < Dog.getRiposteChance()) {
                player.setHealth(player.getHealth() - Dog.getAttack());
                this.checkAlive();
            } else {
                // TODO Add dog to player
                this.removeDog();
            }
        }
    }
    private void removeDog() {
        regionPanel.get(player.getCurrentPosition()).removeDog();
        if (!this.regionHasDog()) {
            this.tameButton.setVisible(false);
        }
        this.actualizePlayerPanel();
    }

    private void killBear() {
        HuntResult result = regionPanel.get(player.getCurrentPosition()).killBear();
        if (result == HuntResult.KILL) {
            if (player.getFood() + Bear.getFood() + player.getWeight() > player.getMaxWeight()) {
                System.out.println("DEBUG: No more room for the food");
            } else {
                player.setFood(player.getFood() + Bear.getFood());
            }
        } else if (result == HuntResult.RIPOSTE) {
            player.setHealth(player.getHealth() - Bear.getAttack());
            this.checkAlive();
        } else if (result == HuntResult.NO_ANIMAL) {
            // TODO Print on menu
            System.out.println("DEBUG: No bear");
        }
        this.showHuntInterface();
    }
    private void killDog() {
        HuntResult result = regionPanel.get(player.getCurrentPosition()).killDog();
        if (result == HuntResult.KILL) {
            if (player.getFood() + Dog.getFood() + player.getWeight() > player.getMaxWeight()) {
                System.out.println("DEBUG: No more room for the food");
            } else {
                player.setFood(player.getFood() + Dog.getFood());
            }
        } else if (result == HuntResult.RIPOSTE) {
            player.setHealth(player.getHealth() - Dog.getAttack());
            this.checkAlive();
        } else if (result == HuntResult.NO_ANIMAL) {
            // TODO Print on menu
            System.out.println("DEBUG: No dog");
        }
        this.showHuntInterface();
    }
    private void killDeer() {
        HuntResult result = regionPanel.get(player.getCurrentPosition()).killDeer();
        if (result == HuntResult.KILL) {
            if (player.getFood() + Deer.getFood() + player.getWeight() > player.getMaxWeight()) {
                System.out.println("DEBUG: No more room for the food");
            } else {
                player.setFood(player.getFood() + Deer.getFood());
            }
        } else if (result == HuntResult.RIPOSTE) {
            player.setHealth(player.getHealth() - Deer.getAttack());
            this.checkAlive();
        } else if (result == HuntResult.NO_ANIMAL) {
            // TODO Print on menu
            System.out.println("DEBUG: No deer");
        }
        this.showHuntInterface();
    }

    private int getNbBears() {
        return regionPanel.get(player.getCurrentPosition()).getNbBears();
    }
    private int getNbDogs() {
        return regionPanel.get(player.getCurrentPosition()).getNbDogs();
    }
    private int getNbDeers() {
        return regionPanel.get(player.getCurrentPosition()).getNbDeers();
    }

    private Type getBoxType() {
        return regionPanel.get(player.getCurrentPosition()).getBox().getType();
    }
    private void removeBox() {
        regionPanel.get(player.getCurrentPosition()).removeBox();
        this.grabBoxButton.setVisible(false);
    }

    private void actualizePlayerPanel() {
        this.displayedText.setText(Text.PLAYER_STATUS());
    }

    private void move(Direction direction) {
        int position = player.getCurrentPosition();
        if (player.move(direction)) {
            regionPanel.get(position).remove(player);
            regionPanel.get(position).repaint();
            regionPanel.get(player.getCurrentPosition()).add(player);
            regionPanel.get(player.getCurrentPosition()).repaint();
        }
        this.showMainInterface();
        this.actualizePlayerPanel();
        this.checkAlive();
    }
    private void checkAlive() {
        player.checkAlive();
        if (!player.isAlive()) {
            this.createDeathScreen();
        }
    }

    public static String displayCurrentPositionInfos() {
        return "Région\n" +
                "\n" +
                " # " + (regionPanel.get(player.getCurrentPosition()).containBox() ? "Il y a une boite ici !" : "Il ne semble pas y avoir de boite") +
                "\n\n" +
                "Animaux\n" +
                "\n" +
                " # Ours: " + (regionPanel.get(player.getCurrentPosition()).getNbBears()) + "\n" +
                " # Chiens: " + (regionPanel.get(player.getCurrentPosition()).getNbDogs()) + "\n" +
                " # Cerfs: " + (regionPanel.get(player.getCurrentPosition()).getNbDeers());
    }

    private void removeOneHour() {
        this.sleepTime -= 1;
        if (this.sleepTime < 0) {
            this.sleepTime = 0;
        }

        if (this.sleepTime < 10) {
            this.sleepHLabel.setBounds(660, 200, 100, 90);
            this.sleepHLabel.update(this.sleepHLabel.getGraphics());
        }

        this.sleepTimeLabel.setText(String.valueOf(this.sleepTime));
        this.sleepTimeLabel.update(this.sleepTimeLabel.getGraphics());
    }
    private void addOneHour() {
        this.sleepTime += 1;
        if (this.sleepTime > 12) {
            this.sleepTime = 12;
        }

        if (this.sleepTime > 9) {
            this.sleepHLabel.setBounds(700, 200, 100, 90);
            this.sleepHLabel.update(this.sleepHLabel.getGraphics());
        }

        this.sleepTimeLabel.setText(String.valueOf(this.sleepTime));
        this.sleepTimeLabel.update(this.sleepTimeLabel.getGraphics());
    }

    // GETTERS
    
    public static Player getPlayer() {
        return player;
    }

}
