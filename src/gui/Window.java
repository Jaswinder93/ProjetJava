package gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
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
    private JButton mainMenuButton;
    private JButton northButton, eastButton, southButton, westButton;
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

    private JButton sleepButton, previousButton, nextButton, validateSleepButton;
    private JLabel sleepLabel, sleepHLabel, sleepTimeLabel;
    private int sleepTime;

    // DEATH SCREEN

    private JLabel deathLabel;
    private JTextArea resultText;

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

    public void createTitleScreen() {
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.titleLabel = new JLabel("Kanji");
        this.titleLabel.setBounds(320, 100, 200, 90);
        this.titleLabel.setFont(new Font(FONT, Font.PLAIN, TITLE_FONT_SIZE));
        this.titleLabel.setForeground(Color.WHITE);
        this.window.add(this.titleLabel);

        this.startButton = new JButton("Start");
        this.startButton.setBounds(350, 400, 100, 50);
        this.startButton.setFont(new Font(FONT, Font.PLAIN, MAIN_TEXT_FONT_SIZE));
        this.startButton.setBackground(Color.BLACK);
        this.startButton.setForeground(Color.WHITE);
        this.startButton.setFocusPainted(false);
        this.startButton.setBorder(new LineBorder(Color.WHITE));
        this.startButton.addActionListener(e -> {
//            this.window.remove(this.titleLabel);
            this.titleLabel.setVisible(false);
//            this.window.remove(this.startButton);
            this.startButton.setVisible(false);
            this.createGameScreen();
       });
        this.window.add(this.startButton);
    }
    private void createGameScreen() {
        this.window.setSize(1200, 600);
        this.window.setLocationRelativeTo(null);

        this.mapPanel = new JPanel();
        this.mapPanel.setBounds(50, 50, 800, 400);
        this.mapPanel.setBackground(Color.BLACK);
        this.mapPanel.setBorder(new LineBorder(Color.WHITE));
        this.mapPanel.setLayout(new GridLayout(3, 3));
        this.window.add(this.mapPanel);

        this.playerPanel = new JPanel();
        this.playerPanel.setBounds(900, 50, 250, 480);
        this.playerPanel.setBackground(Color.BLACK);
        this.playerPanel.setBorder(new LineBorder(Color.WHITE));
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

        this.nextTextButton = new JButton("Next");
        this.nextTextButton.setPreferredSize(new Dimension(200, 50));
        this.nextTextButton.setFont(new Font(FONT, Font.PLAIN, TEXT_FONT_SIZE));
        this.nextTextButton.setBackground(Color.BLACK);
        this.nextTextButton.setForeground(Color.WHITE);
        this.nextTextButton.setFocusPainted(false);
        this.nextTextButton.setBorder(new LineBorder(Color.WHITE));
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

        this.interfacePanel = new JPanel();
        this.interfacePanel.setBounds(50, 480, 800, 50);
        this.interfacePanel.setBackground(Color.BLACK);
        this.interfacePanel.setLayout(null);
        this.window.add(this.interfacePanel);

        this.mainMenuButton = new JButton("Main Menu");
        this.mainMenuButton.setBounds(0, 0, 100, 50);
        this.mainMenuButton.setFont(new Font(FONT, Font.PLAIN, TEXT_FONT_SIZE));
        this.mainMenuButton.setBackground(Color.BLACK);
        this.mainMenuButton.setForeground(Color.WHITE);
        this.mainMenuButton.setFocusPainted(false);
        this.mainMenuButton.setBorder(new LineBorder(Color.WHITE));
        this.mainMenuButton.addActionListener(e -> {
            this.mapPanel.setVisible(false);
            this.playerPanel.setVisible(false);
            this.interfacePanel.setVisible(false);
//            this.mainMenuButton.setVisible(false);
//            this.compassPanel.setVisible(false);
//            this.sleepButton.setVisible(false);
            this.createTitleScreen();
        });
        this.interfacePanel.add(this.mainMenuButton);

        this.compassPanel = new JPanel();
        this.compassPanel.setBounds(600, 0, 200, 50);
        this.compassPanel.setBackground(Color.BLACK);
        this.compassPanel.setLayout(new BorderLayout());
        this.interfacePanel.add(this.compassPanel);
    }

    private void launchGame() {
        this.createMapPanel();
        this.setPlayerPanel();
    }

    private void createMapPanel() {
        this.initializeRegionPanel();

        player = new Player();
        regionPanel.get(player.getCurrentPosition()).add(player);

        this.initializeCompassPanel();

        this.sleepButton = new JButton("Dormir");
        this.sleepButton.setBounds(120, 0, 100, 50);
        this.sleepButton.setFont(new Font(FONT, Font.PLAIN, TEXT_FONT_SIZE));
        this.sleepButton.setBackground(Color.BLACK);
        this.sleepButton.setForeground(Color.WHITE);
        this.sleepButton.setFocusPainted(false);
        this.sleepButton.setBorder(new LineBorder(Color.WHITE));
        this.sleepButton.addActionListener(e -> {
            this.mapPanel.setVisible(false);
            this.playerPanel.setVisible(false);
            this.interfacePanel.setVisible(false);
            this.createSleepTimeChoicePanel();
        });
        this.interfacePanel.add(this.sleepButton);
        this.interfacePanel.update(this.interfacePanel.getGraphics());

        this.initializeBoxes();
    }
    private void initializeRegionPanel() {
        ArrayList<String> names = new ArrayList<>();
        names.add("North Swamp"); // 0
        names.add("Mountain"); // 1
        names.add("River"); // 2
        names.add("North Clearing"); // 3
        names.add("South Swamp"); // 4
        names.add("North Lake"); // 5
        names.add("West Clearing"); // 6
        names.add("East Clearing"); // 7
        names.add("Abandoned Hut"); // 8
        names.add("South Lake"); // 9
        names.add("Forest Entry"); // 10
        names.add("Mine"); // 11

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
            System.out.println("DEBUG: [Window.java] => NORTH");
            this.move(Direction.NORTH);
        });
        this.eastButton = this.initializeCompassButton("E", 50, 0, BorderLayout.EAST);
        this.eastButton.addActionListener(e -> {
            System.out.println("DEBUG: [Window.java] => EAST");
            this.move(Direction.EAST);
        });
        this.southButton = this.initializeCompassButton("S", 0, 15, BorderLayout.SOUTH);
        this.southButton.addActionListener(e -> {
            System.out.println("DEBUG: [Window.java] => SOUTH");
            this.move(Direction.SOUTH);
        });
        this.westButton = this.initializeCompassButton("W", 50, 0, BorderLayout.WEST);
        this.westButton.addActionListener(e -> {
            System.out.println("DEBUG: [Window.java] => WEST");
            this.move(Direction.WEST);
        });
    }
    private JButton initializeCompassButton(String text, int width, int height, String borderLayout) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(width, height));
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(Color.WHITE));
        this.compassPanel.add(button, borderLayout);

        return button;
    }
    private void initializeBoxes() {
        this.boxLocalization = new HashMap<>();

        for (int i = 0; i < 3; i++) {
            int position;
            do {
                position = random.nextInt(11);
                System.out.println("DEBUG: Box created at position " + position);
            } while (this.boxLocalization.get(position) != null);
            Box box = new Box(100, Type.MAGIC, "Box1");
            regionPanel.get(position).addBox(box);
            this.boxLocalization.put(position, box);
        }

    }

    private void setPlayerPanel() {
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

        this.setPlayerPanel();
        if (!player.isAlive()) {
            this.mapPanel.setVisible(false);
            this.playerPanel.setVisible(false);
            this.interfacePanel.setVisible(false);
            this.createDeathPanel();
        }
    }
    public static String displayCurrentPositionInfos() {
        return
        "Région\n" +
        "\n" +
        " # " + (regionPanel.get(player.getCurrentPosition()).containBox() ? "Il y a une boite ici !" : "Il ne semble pas y avoir de boite");
    }
    private void createDeathPanel() {
        this.window.setSize(800, 600);
        this.window.setLocationRelativeTo(null);

        this.deathLabel = new JLabel("Vous êtes mort");
        this.deathLabel.setBounds(260, 100, 300, 90);
        this.deathLabel.setFont(new Font(FONT, Font.PLAIN, MAIN_TEXT_FONT_SIZE));
        this.deathLabel.setForeground(Color.WHITE);
        this.window.add(this.deathLabel);
    }

    private void createSleepTimeChoicePanel() {
        if (player.getStamina() == 100 || player.getHunger() == 0 || player.getThirst() == 0) {
            this.mapPanel.setVisible(true);
            this.playerPanel.setVisible(true);
            this.interfacePanel.setVisible(true);

            return;

            // TODO Add explantion text : "could not sleep when stamina = 100"
        }

        this.sleepLabel = new JLabel("Dormir: ");
        this.sleepLabel.setBounds(360, 200, 600, 90);
        this.sleepLabel.setFont(new Font(FONT, Font.PLAIN, TITLE_FONT_SIZE));
        this.sleepLabel.setForeground(Color.WHITE);
        this.window.add(this.sleepLabel);

        this.sleepHLabel = new JLabel("h");
        this.sleepHLabel.setBounds(660, 200, 100, 90);
        this.sleepHLabel.setFont(new Font(FONT, Font.PLAIN, TITLE_FONT_SIZE));
        this.sleepHLabel.setForeground(Color.WHITE);
        this.window.add(this.sleepHLabel);

        this.sleepTimeLabel = new JLabel(String.valueOf(this.sleepTime));
        this.sleepTimeLabel.setBounds(620, 200, 100, 90);
        this.sleepTimeLabel.setFont(new Font(FONT, Font.PLAIN, TITLE_FONT_SIZE));
        this.sleepTimeLabel.setForeground(Color.WHITE);
        this.window.add(this.sleepTimeLabel);

        this.previousButton = new JButton("<");
        this.previousButton.setBounds(500, 300, 80, 50);
        this.previousButton.setFont(new Font(FONT, Font.PLAIN, MAIN_TEXT_FONT_SIZE));
        this.previousButton.setBackground(Color.BLACK);
        this.previousButton.setForeground(Color.WHITE);
        this.previousButton.setFocusPainted(false);
        this.previousButton.setBorder(new LineBorder(Color.WHITE));
        this.previousButton.addActionListener(e -> {
            this.removeOneHour();
        });
        this.window.add(this.previousButton);

        this.nextButton = new JButton(">");
        this.nextButton.setBounds(600, 300, 80, 50);
        this.nextButton.setFont(new Font(FONT, Font.PLAIN, MAIN_TEXT_FONT_SIZE));
        this.nextButton.setBackground(Color.BLACK);
        this.nextButton.setForeground(Color.WHITE);
        this.nextButton.setFocusPainted(false);
        this.nextButton.setBorder(new LineBorder(Color.WHITE));
        this.nextButton.addActionListener(e -> {
            this.addOneHour();
        });
        this.window.add(this.nextButton);

        this.validateSleepButton = new JButton("Dormir");
        this.validateSleepButton.setBounds(500, 380, 160, 50);
        this.validateSleepButton.setFont(new Font(FONT, Font.PLAIN, MAIN_TEXT_FONT_SIZE));
        this.validateSleepButton.setBackground(Color.BLACK);
        this.validateSleepButton.setForeground(Color.WHITE);
        this.validateSleepButton.setFocusPainted(false);
        this.validateSleepButton.setBorder(new LineBorder(Color.WHITE));
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

            this.setPlayerPanel();
        });
        this.window.add(this.validateSleepButton);
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
