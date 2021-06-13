package game;

import game.animal.Bear;
import game.animal.Deer;
import game.animal.Dog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Window {

    private static Player player;
    private static String username;
    private static int score;

    private JFrame window;

    Random random = new Random();

    // TITLE SCREEN

    private JLabel titleLabel, usernameLabel;
    private JTextField usernameField;
    private JButton startButton, highScoreButton;

    // HIGH SCORE SCREEN

    private JLabel highScoreLabel;
    private JTextArea highScoreText;
    private JButton menuButton;

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
    private JTextArea infosUser;

    private final String FONT = "Book Antiqua";
    private final int TITLE_FONT_SIZE = 70;
    private final int MAIN_TEXT_FONT_SIZE = 40;
    private final int TEXT_FONT_SIZE = 16;
    private final int SMALL_TEXT_FONT_SIZE = 14;

    // SLEEP SCREEN

    private JButton previousButton, nextButton, validateSleepButton;
    private JLabel sleepLabel, sleepHLabel, sleepTimeLabel;
    private int sleepTime;

    // WIN & DEATH SCREEN

    private JLabel winLabel, deathLabel, scoreLabel;

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

    // TODO button rejouer
    // TODO zdsq / fleche

    private void createTitleScreen() {
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.titleLabel = new Label("Kanji", 320, 100, 200, 90, TITLE_FONT_SIZE);
        this.window.add(this.titleLabel);

        this.usernameLabel = new Label("Username (sans espaces) :  ", 170, 350, 200, 30, TEXT_FONT_SIZE);
        this.window.add(this.usernameLabel);

        this.usernameField = new JTextField();
        this.usernameField.setFont(new Font("Book Antiqua", Font.PLAIN, TEXT_FONT_SIZE));
        this.usernameField.setMargin(new Insets(0, 10, 0, 0));
        this.usernameField.setCaretColor(Color.BLACK);
        this.usernameField.setSelectedTextColor(Color.WHITE);
        this.usernameField.setBounds(360, 350, 150, 30);
        this.usernameField.setBackground(Color.BLACK);
        this.usernameField.setForeground(Color.WHITE);
        this.window.add(usernameField);

        this.startButton = new Button("Jouer", 200, 400, 150, 50, MAIN_TEXT_FONT_SIZE);
        this.startButton.addActionListener(e -> {
            this.start();
        });
        this.window.add(this.startButton);

        this.highScoreButton = new Button("High Scores", 360, 400, 250, 50, MAIN_TEXT_FONT_SIZE);
        this.highScoreButton.addActionListener(e -> {
            this.titleLabel.setVisible(false);
            this.usernameLabel.setVisible(false);
            this.usernameField.setVisible(false);
            this.startButton.setVisible(false);
            this.highScoreButton.setVisible(false);
            this.createHighScoreScreen();
        });
        this.window.add(this.highScoreButton);
    }
    private void createHighScoreScreen() {
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.highScoreLabel = new Label("High Scores", 200, 80, 400, 90, TITLE_FONT_SIZE);
        this.window.add(this.highScoreLabel);

        this.highScoreText = new JTextArea(Text.HIGH_SCORES(sortByValue(Objects.requireNonNull(this.readFile()))));
        this.highScoreText.setBounds(320, 200, 180, 220);
        this.highScoreText.setFont(new Font(FONT, Font.PLAIN, TEXT_FONT_SIZE));
        this.highScoreText.setLineWrap(true);
        this.highScoreText.setWrapStyleWord(true);
        this.highScoreText.setEnabled(false);
        this.highScoreText.setBackground(Color.BLACK);
        this.highScoreText.setForeground(Color.WHITE);
        this.window.add(this.highScoreText);

        this.menuButton = new Button("Menu", 320, 420, 100, 20, TEXT_FONT_SIZE);
        this.menuButton.addActionListener(e -> {
            this.highScoreLabel.setVisible(false);
            this.highScoreText.setVisible(false);
            this.menuButton.setVisible(false);
            this.titleLabel.setVisible(true);
            this.usernameLabel.setVisible(true);
            this.usernameField.setVisible(true);
            this.startButton.setVisible(true);
            this.highScoreButton.setVisible(true);
        });
        this.window.add(this.menuButton);
    }
    private void createGameScreen() {
        username = this.usernameField.getText();
        score = 0;

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

        this.nextTextButton = new Button("Suivant",200, 50, TEXT_FONT_SIZE);
        this.nextTextButton.addActionListener(e -> {
            this.displayedText.setText(Text.WELCOME_STRING_2());
            this.nextTextButton.setText("Commencer l'aventure");
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

        this.mainMenuButton = new Button("Menu", 0, 0, 100, 50, TEXT_FONT_SIZE);
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

        this.infosUser = new JTextArea();
        this.infosUser.setPreferredSize(new Dimension(230, 50));
        this.infosUser.setFont(new Font(FONT, Font.PLAIN, SMALL_TEXT_FONT_SIZE));
        this.infosUser.setLineWrap(true);
        this.infosUser.setWrapStyleWord(true);
        this.infosUser.setEnabled(false);
        this.infosUser.setBackground(Color.BLACK);
        this.infosUser.setVisible(false);
        this.playerPanel.add(this.infosUser);
    }
    private void createDeathScreen() {
        this.writeFile();

        this.mapPanel.setVisible(false);
        this.playerPanel.setVisible(false);
        this.interfacePanel.setVisible(false);

        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.deathLabel = new Label("Vous êtes mort", 260, 220, 300, 90, MAIN_TEXT_FONT_SIZE);
        this.window.add(this.deathLabel);

        this.scoreLabel = new Label("Votre score: " + score, 340, 280, 300, 90, TEXT_FONT_SIZE);
        this.window.add(this.scoreLabel);
    }
    private void createWinScreen() {
        this.writeFile();

        this.mapPanel.setVisible(false);
        this.playerPanel.setVisible(false);
        this.interfacePanel.setVisible(false);

        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.winLabel = new Label("Vous avez gagné !", 240, 220, 360, 90, MAIN_TEXT_FONT_SIZE);
        this.window.add(this.winLabel);

        this.scoreLabel = new Label("Votre score: " + score, 340, 280, 300, 90, TEXT_FONT_SIZE);
        this.window.add(this.scoreLabel);

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

            this.setInfosUserText("Vous n'êtes pas assez fatigué pour dormir.");

            return;
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
            this.sleep();
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
            this.eat();
        });
        this.interfacePanel.add(this.eatButton);
        this.eatButton.setVisible(false);

        this.drinkButton = new Button("Boire", 360, 0, 100, 50, TEXT_FONT_SIZE);
        this.drinkButton.addActionListener(e -> {
            this.drink();
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
            this.takeWater();
        });
        this.interfacePanel.add(this.takeWaterButton);
        this.takeWaterButton.setVisible(false);

        this.grabBoxButton = new Button("Prendre boite", 360, 0, 100, 50, TEXT_FONT_SIZE);
        this.grabBoxButton.addActionListener(e -> {
            this.grabBox();
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
        this.compassLabel = new JLabel("BOUSSOLE");
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
            if (bearChance < 50) {
                int bearQuantity = random.nextInt(5);
                for (int i = 0; i < bearQuantity; i++) {
                    region.addAnimal(new Bear());
                }
            }

            // Dogs
            int dogChance = random.nextInt(100);
            if (dogChance < 70) {
                int dogQuantity = random.nextInt(8);
                for (int i = 0; i < dogQuantity; i++) {
                    region.addAnimal(new Dog());
                }
            }

            // Dogs
            int deerChance = random.nextInt(100);
            if (deerChance < 80) {
                int deerQuantity = random.nextInt(10);
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

    private void start() {
        if (!this.usernameField.getText().contains(" ") && !this.usernameField.getText().equals("")) {
            this.titleLabel.setVisible(false);
            this.usernameLabel.setVisible(false);
            this.usernameField.setVisible(false);
            this.startButton.setVisible(false);
            this.highScoreButton.setVisible(false);
            this.createGameScreen();
        }
    }

    private boolean isTake() {
        if (!this.regionHasDog() && !this.regionHasAnimal() && !this.regionHasWater() && !this.regionHasBox()) {
            this.setInfosUserText("Il n'y a rien à prendre ici.");
            return false;
        }
        return true;
    }
    private void showTakeInterface() {

        if (!this.isTake()) {
            this.showMainInterface();
            return;
        }

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
        if (this.isTake()) {
            this.returnButton.addActionListener(e -> {
                this.hideAnimalInterface();
                this.showTakeInterface();
            });
        } else {
            this.returnButton.addActionListener(e -> {
                this.hideAnimalInterface();
                this.showMainInterface();
            });
        }

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

    private void eat() {
        if (player.getHunger() < player.getMaxHunger()) {
            player.eat();
            this.actualizePlayerPanel();
        } else {
            this.setInfosUserText("Vous n'avez pas faim.");
        }
    }
    private void drink() {
        if (player.getThirst() < player.getMaxThirst()) {
            player.drink();
            this.actualizePlayerPanel();
        } else {
            this.setInfosUserText("Vous n'avez pas soif.");
        }
    }
    private void sleep() {
        this.infosUser.setVisible(false);
        if (player.sleep(this.sleepTime) == SleepState.ATTACKED) {
            this.setInfosUserText("Vous avez été attaqué pendant votre sommeil...");
        }

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
    }

    private void tame() {
        if (regionHasDog()) {
            int tame = random.nextInt(100);
            if (tame < Dog.getRiposteChance()) {
                player.setHealth(player.getHealth() - Dog.getAttack());
                this.checkAlive();
                this.actualizePlayerPanel();
                this.setInfosUserText("Le chien vous a attaqué !");
            } else {
                this.removeDog();
                this.setScore(100);
            }
        }
    }
    private void removeDog() {
        regionPanel.get(player.getCurrentPosition()).removeDog();
        player.addDog();
        if (!this.regionHasDog()) {
            this.tameButton.setVisible(false);
        }
        this.actualizePlayerPanel();
    }

    private void takeWater() {
        if (player.getWeight() + 10 <= player.getMaxWeight()) {
            player.takeWater();
            this.actualizePlayerPanel();
        } else {
            this.setInfosUserText("Vous n'avez plus de place ! Adoptez des chiens pour en avoir.");
        }
    }
    private void grabBox() {
        if (player.getWeight() + 100 <= player.getMaxWeight()) {
            player.takeBox(this.getBoxType());
            this.removeBox();
            this.actualizePlayerPanel();
        } else {
            this.setInfosUserText("Vous n'avez plus de place ! Adoptez des chiens pour en avoir.");
        }
    }

    private void killBear() {
        HuntResult result = regionPanel.get(player.getCurrentPosition()).killBear();
        if (result == HuntResult.KILL) {
            if (player.getFood() + Bear.getFood() + player.getWeight() > player.getMaxWeight()) {
                this.setInfosUserText("Il n'y a plus de place pour la nourriture");
            } else {
                player.setFood(player.getFood() + Bear.getFood());
                this.setScore(500);
                if (!this.regionHasBear()) {
                    this.bearButton.setVisible(false);
                }
            }
        } else if (result == HuntResult.RIPOSTE) {
            player.setHealth(player.getHealth() - Bear.getAttack());
            this.checkAlive();
        }
        this.showHuntInterface();
    }
    private void killDog() {
        HuntResult result = regionPanel.get(player.getCurrentPosition()).killDog();
        if (result == HuntResult.KILL) {
            if (player.getFood() + Dog.getFood() + player.getWeight() > player.getMaxWeight()) {
                this.setInfosUserText("Il n'y a plus de place pour la nourriture");
            } else {
                player.setFood(player.getFood() + Dog.getFood());
                this.setScore(300);
                if (!this.regionHasDog()) {
                    this.dogButton.setVisible(false);
                }
            }
        } else if (result == HuntResult.RIPOSTE) {
            player.setHealth(player.getHealth() - Dog.getAttack());
            this.checkAlive();
        }
        this.showHuntInterface();
    }
    private void killDeer() {
        HuntResult result = regionPanel.get(player.getCurrentPosition()).killDeer();
        if (result == HuntResult.KILL) {
            if (player.getFood() + Deer.getFood() + player.getWeight() > player.getMaxWeight()) {
                this.setInfosUserText("Il n'y a plus de place pour la nourriture");
            } else {
                player.setFood(player.getFood() + Deer.getFood());
                this.setScore(100);
                if (!this.regionHasDeer()) {
                    this.deerButton.setVisible(false);
                }
            }
        } else if (result == HuntResult.RIPOSTE) {
            player.setHealth(player.getHealth() - Deer.getAttack());
            this.checkAlive();
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
        if (this.getBoxType() == Type.MAGIC) {
            this.setScore(200);
            if (player.getNbMagicBox() == 1) {
                this.createWinScreen();
            }
        } else {
            this.setScore(-50);
            if (player.getNbPoisonBox() == 3) {
                this.createDeathScreen();
            }
        }
        regionPanel.get(player.getCurrentPosition()).removeBox();
        this.grabBoxButton.setVisible(false);
    }

    private void actualizePlayerPanel() {
        this.displayedText.setText(Text.PLAYER_STATUS());
    }

    private void move(Direction direction) {
        this.infosUser.setVisible(false);
        int position = player.getCurrentPosition();
        if (player.move(direction)) {
            regionPanel.get(position).remove(player);
            regionPanel.get(position).repaint();
            regionPanel.get(player.getCurrentPosition()).add(player);
            regionPanel.get(player.getCurrentPosition()).repaint();
            this.setScore(-10);
        } else if (player.getStamina() == 0) {
            this.setInfosUserText("Vous êtes trop fatigué pour vous déplacer !");
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
                " # " + (regionPanel.get(player.getCurrentPosition()).containBox() ? "Il y a une boite ici !" : "Il ne semble pas y avoir de boite") +
                "\n\n" +
                "Animaux\n" +
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

    private void writeFile() {
        try {
            HashMap<String, Integer> mapScore = this.readFile();
            assert mapScore != null;
            mapScore.put(username, score);
            mapScore = sortByValue(mapScore);
            FileWriter fw = new FileWriter("scores.txt");
            int cpt = 0;
            for (Map.Entry<String, Integer> entry : mapScore.entrySet()) {
                fw.write(" -- " + entry.getKey() + " : " + entry.getValue() + "\n");
                if (cpt++ == 9) {
                    break;
                }
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("DEBUG: Writing file error");
            e.printStackTrace();
        }
    }
    private HashMap<String, Integer> readFile() {
        HashMap<String, Integer> mapScore = new HashMap<>();
        try {
            File file = new File("scores.txt");
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            FileReader fr = new FileReader("scores.txt");
            Scanner sc = new Scanner(fr);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] spl = data.split(" ");
                String name = spl[2];
                int value = Integer.parseInt(spl[4]);
                mapScore.put(name, value);
            }
            return mapScore;
        } catch (IOException e) {
            System.out.println("DEBUG: Reading file error");
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            return mapScore;
        }

        return null;
    }
    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list = new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort(Map.Entry.comparingByValue());
        Collections.reverse(list);

        // Put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> a : list) {
            temp.put(a.getKey(), a.getValue());
        }
        return temp;
    }

    // GETTERS
    
    public static Player getPlayer() {
        return player;
    }
    public static int getScore() { return score; }
    public static String getUsername() { return username; }
    public static Region getCurrentRegion() {
        return regionPanel.get(player.getCurrentPosition());
    }

    // SETTERS

    private void setInfosUserText(String text) {
        this.infosUser.setText(text);
        this.infosUser.setVisible(true);
        this.actualizePlayerPanel();
    }
    private void setScore(int score) {
        Window.score += score;
        if (Window.score < 0) {
            Window.score = 0;
        }
    }

}
