package safecracker;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Random;

public class Safecracker extends JFrame {

    private ImagePanel safePanel = new ImagePanel(new ImageIcon("assets/screenshot.jpg").getImage());
    private JPanel comboPanel = new JPanel();
    private JPanel keyPanel = new JPanel();
    private JPanel optionsPanel = new JPanel();
    private ButtonGroup digitsButtonGroup = new ButtonGroup();
    private JRadioButton twoDigitsRadioButton = new JRadioButton();
    private JRadioButton threeDigitsRadioButton = new JRadioButton();
    private JRadioButton fourDigitsRadioButton = new JRadioButton();
    private JTextField[] comboTextField = new JTextField[4];
    private JButton[] keyButton = new JButton[9];
    private JPanel buttonsPanel = new JPanel();
    private JButton startStopButton = new JButton();
    private JButton exitButton = new JButton();
    private JPanel resultsPanel = new JPanel();
    private JScrollPane resultsPane = new JScrollPane();
    private JTextArea resultsTextArea = new JTextArea();

    private int numberDigits;
    private String secretCombo;
    private Random myRandom = new Random();
    private int digitsEntered;
    private String enteredCombo;

    private AudioClip wrongSound;
    private AudioClip correctSound;

    public static void main(String[] args) {
        new Safecracker().show();
    }

    public Safecracker() {
        setTitle("Safecracker");
//        setResizable(false);
        setSize(200, 200);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        safePanel.setPreferredSize(new Dimension(330, 420));
        safePanel.setLayout(new GridBagLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        getContentPane().add(safePanel, gridBagConstraints);

        comboPanel.setPreferredSize(new Dimension(160, 110));
        safePanel.setLayout(new GridBagLayout());
        comboPanel.setBackground(Color.YELLOW);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(110, 0, 0, 0);
        safePanel.add(comboPanel, gridBagConstraints);

        for (int i = 0; i < 4; i++) {
            comboTextField[i] = new JTextField();
            comboTextField[i].setPreferredSize(new Dimension(32, 48));
            comboTextField[i].setEditable(false);
            comboTextField[i].setBackground(Color.WHITE);
            comboTextField[i].setText("0");
            comboTextField[i].setHorizontalAlignment(SwingConstants.CENTER);
            comboTextField[i].setFont(new Font("Arial", Font.BOLD, 18));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = i;
            gridBagConstraints.gridy = 0;
            comboPanel.add(comboTextField[i], gridBagConstraints);
        }

        keyPanel.setPreferredSize(new Dimension(160, 110));
        keyPanel.setLayout(new GridBagLayout());
        keyPanel.setBackground(Color.YELLOW);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        safePanel.add(keyPanel, gridBagConstraints);

        for (int i = 0; i < 9; i++) {
            keyButton[i] = new JButton();
            keyButton[i].setText(String.valueOf(i + 1));
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = i % 3;
            gridBagConstraints.gridy = i / 3;
            keyPanel.add(keyButton[i], gridBagConstraints);
            keyButton[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keyButtonActionPerformed(e);
                }
            });
        }

        optionsPanel.setPreferredSize(new Dimension(200, 100));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("Options:"));
        optionsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(optionsPanel, gridBagConstraints);

        twoDigitsRadioButton.setText("Two Digits in Combination");
        twoDigitsRadioButton.setSelected(true);
        digitsButtonGroup.add(twoDigitsRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        optionsPanel.add(twoDigitsRadioButton, gridBagConstraints);

        threeDigitsRadioButton.setText("Three Digits in Combination");
        digitsButtonGroup.add(threeDigitsRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        optionsPanel.add(threeDigitsRadioButton, gridBagConstraints);

        fourDigitsRadioButton.setText("Four Digits in Combination");
        digitsButtonGroup.add(fourDigitsRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        optionsPanel.add(fourDigitsRadioButton, gridBagConstraints);

        buttonsPanel.setPreferredSize(new Dimension(200, 70));
        buttonsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        getContentPane().add(buttonsPanel, gridBagConstraints);
        startStopButton.setText("Start Game");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(startStopButton, gridBagConstraints);
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startStopButtonActionPerformed(e);
            }
        });
        exitButton.setText("Exit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        buttonsPanel.add(exitButton, gridBagConstraints);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });

        resultsPanel.setPreferredSize(new Dimension(200, 250));
        resultsPanel.setBorder(BorderFactory.createTitledBorder("Results"));
        resultsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        getContentPane().add(resultsPanel, gridBagConstraints);

        resultsTextArea.setEditable(false);
        resultsTextArea.setBackground(Color.WHITE);
        resultsPane.setPreferredSize(new Dimension(180, 220));
        resultsPane.setViewportView(resultsTextArea);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        resultsPanel.add(resultsPane, gridBagConstraints);
        pack();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println("screensize: width " + screenSize.width + " height " + screenSize.height);
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())), getWidth(), getHeight());
        setKeyButtons(false);

        try {
            wrongSound = Applet.newAudioClip(new URL("file:" + "assets/uhoh.wav"));
            correctSound = Applet.newAudioClip(new URL("file:" + "assets/owin31.wav"));
        } catch (Exception e) {
            System.out.println("Error loading sound files");
        }
    }

    private void exitForm(WindowEvent e) {
        System.exit(0);
    }

    private void keyButtonActionPerformed(ActionEvent e) {
        String n;
        n = e.getActionCommand();
        keyButton[Integer.valueOf(n).intValue() - 1].setEnabled(false);
        if (digitsEntered == 0) {
            comboTextField[0].setText("");
            comboTextField[1].setText("");
            comboTextField[2].setText("");
            comboTextField[3].setText("");
        }
        enteredCombo += n;
        digitsEntered++;
        comboTextField[digitsEntered - 1].setText(n);
        if (digitsEntered == numberDigits) {
            for (int i = 0; i < keyButton.length; i++) {
                keyButton[i].setEnabled(true);
                System.out.println("enteredCombo: " + enteredCombo);
            }
            resultsTextArea.setText("Entered: " + enteredCombo + "\n");
            if (enteredCombo.equals(secretCombo)) {
                correctSound.play();
                startStopButton.doClick();
            } else {
                wrongSound.play();
                int numberRight = 0;
                for (int i = 0; i < numberDigits; i++) {
                    n = String.valueOf(enteredCombo.charAt(i));
                    for (int j = 0; j < numberDigits; j++) {
                        if (n.equals(String.valueOf(secretCombo.charAt(j)))) {
                            numberRight++;
                        }
                    }
                }
                int positionRight = 0;
                for (int i = 0; i < numberDigits; i++) {
                    if (secretCombo.charAt(i) == enteredCombo.charAt(i)) {
                        positionRight++;
                    }
                }
                resultsTextArea.append(String.valueOf(numberRight) + " digits correct" + "\n");
                resultsTextArea.append(String.valueOf(positionRight) + " in correct position" + "\n");
                resultsTextArea.append("Try again..." + "\n\n");
                enteredCombo = "";
                digitsEntered = 0;
            }
        }
    }

    private void startStopButtonActionPerformed(ActionEvent e) {
        if (startStopButton.getText().equals("Start Game")) {
            startStopButton.setText("Stop Game");
            twoDigitsRadioButton.setEnabled(false);
            threeDigitsRadioButton.setEnabled(false);
            fourDigitsRadioButton.setEnabled(false);
            exitButton.setEnabled(false);
            setKeyButtons(true);
            resultsTextArea.setText("");
            if (twoDigitsRadioButton.isSelected()) {
                numberDigits = 2;
            } else if (threeDigitsRadioButton.isSelected()) {
                numberDigits = 3;
            } else {
                numberDigits = 4;
            }
            for (int i = 0; i < comboTextField.length; i++) {
                comboTextField[i].setVisible(true);
                comboTextField[i].setText("");
            }
            if (numberDigits != 4) {
                for (int i = numberDigits; i < 4; i++) {
                    comboTextField[i].setVisible(false);
                }
            }

            secretCombo = "";
            int j;
            boolean uniqueDigit;
            for (int i = 0; i < numberDigits; i++) {
                do {
                    j = myRandom.nextInt(9) + 1;
                    uniqueDigit = true;
                    if (i != 0) {
                        for (int k = 0; k < i; k++) {
                            if (String.valueOf(secretCombo.charAt(k)).equals(String.valueOf(j))) {
                                uniqueDigit = false;
                            }
                        }
                    }
                } while (!uniqueDigit);
                secretCombo += String.valueOf(j);
            }
            System.out.println("secretCombo: " + secretCombo);
        } else {
            if (enteredCombo.equals(secretCombo)) {
                resultsTextArea.append("That's it!" + "\n");
            } else {
                resultsTextArea.append("Game stopped" + "\n");
            }
            resultsTextArea.append("Combination: " + secretCombo);
            startStopButton.setText("Start Game");
            twoDigitsRadioButton.setEnabled(true);
            threeDigitsRadioButton.setEnabled(true);
            fourDigitsRadioButton.setEnabled(true);
            exitButton.setEnabled(true);
            setKeyButtons(false);
        }

        enteredCombo = "";
        digitsEntered = 0;

    }

    private void exitButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void setKeyButtons(boolean enabled) {
        for (int i = 0; i < keyButton.length; i++) {
            keyButton[i].setEnabled(enabled);
        }
    }
}
