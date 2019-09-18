import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.Random;

public class TicTacToe extends JFrame {
    private JTextField messageTextField = new JTextField();
    private JPanel gamePanel = new JPanel();
    private JTextField[] boxTextField = new JTextField[9];
    private JLabel[] gridLabel = new JLabel[4];
    private JPanel playersPanel = new JPanel();
    private ButtonGroup playersButtonGroup = new ButtonGroup();
    private JRadioButton onePlayerRadioButton = new JRadioButton();
    private JRadioButton twoPlayersRadioButton = new JRadioButton();
    private JPanel firstPanel = new JPanel();
    private ButtonGroup firstButtonGroup = new ButtonGroup();
    private JRadioButton youFirstRadioButton = new JRadioButton();
    private JRadioButton computerFirstRadioButton = new JRadioButton();
    private JPanel computerPanel = new JPanel();
    private ButtonGroup computerButtonGroup = new ButtonGroup();
    private JRadioButton randomRadioButton = new JRadioButton();
    private JRadioButton smartRadioButton = new JRadioButton();
    private JPanel buttonsPanel = new JPanel();
    private JButton startStopButton = new JButton();
    private JButton exitButton = new JButton();

    private boolean xTurn;
    private boolean canClick = false;
    private int numberClicks;

    private String[] possibleWins = new String[8];
    private boolean gameOver;
    private Random myRandom = new Random();

    private AudioClip drawSound;
    private AudioClip winSound;

    public static void main(String[] args) {
        new TicTacToe().show();
    }

    public TicTacToe() {
        setTitle("Tic Tac Toe");
        getContentPane().setBackground(Color.WHITE);
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints;


        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * screenSize.height - getHeight()), getWidth(), getHeight());

        messageTextField.setPreferredSize(new Dimension(280, 50));
        messageTextField.setEditable(false);
        messageTextField.setBackground(Color.YELLOW);
        messageTextField.setForeground(Color.BLUE);
        messageTextField.setText("X's Move");
        messageTextField.setHorizontalAlignment(SwingConstants.CENTER);
        messageTextField.setFont(new Font("Arial", Font.BOLD, 24));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(messageTextField, gridBagConstraints);

        gamePanel.setPreferredSize(new Dimension(280, 280));
        gamePanel.setBackground(Color.WHITE);
        gamePanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        getContentPane().add(gamePanel, gridBagConstraints);

        for (int i = 0; i < 9; i++) {
            boxTextField[i] = new JTextField();
            boxTextField[i].setPreferredSize(new Dimension(80, 80));
            boxTextField[i].setEnabled(false);
            boxTextField[i].setBackground(Color.WHITE);
            boxTextField[i].setHorizontalAlignment(SwingConstants.CENTER);
            boxTextField[i].setFont(new Font("Arial", Font.BOLD, 48));
            boxTextField[i].setBorder(null);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = 2 * (i % 3);
            gridBagConstraints.gridy = 2 * (i / 3);
            gamePanel.add(boxTextField[i], gridBagConstraints);
            boxTextField[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    boxTextFieldMousePressed(e);
                }
            });
        }

        gridLabel[0] = new JLabel();
        gridLabel[0].setPreferredSize(new Dimension(280, 10));
        gridLabel[0].setOpaque(true);
        gridLabel[0].setBackground(Color.BLUE);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gamePanel.add(gridLabel[0], gridBagConstraints);

        gridLabel[1] = new JLabel();
        gridLabel[1].setPreferredSize(new Dimension(280, 10));
        gridLabel[1].setOpaque(true);
        gridLabel[1].setBackground(Color.BLUE);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 5;
        gridBagConstraints.insets = new Insets(5, 0, 5, 0);
        gamePanel.add(gridLabel[1], gridBagConstraints);

        gridLabel[2] = new JLabel();
        gridLabel[2].setPreferredSize(new Dimension(10, 280));
        gridLabel[2].setOpaque(true);
        gridLabel[2].setBackground(Color.BLUE);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        gamePanel.add(gridLabel[2], gridBagConstraints);

        gridLabel[3] = new JLabel();
        gridLabel[3].setPreferredSize(new Dimension(10, 280));
        gridLabel[3].setOpaque(true);
        gridLabel[3].setBackground(Color.BLUE);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        gridBagConstraints.insets = new Insets(0, 5, 0, 5);
        gamePanel.add(gridLabel[3], gridBagConstraints);

        playersPanel.setPreferredSize(new Dimension(160, 75));
        playersPanel.setBackground(Color.WHITE);
        playersPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        playersPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(playersPanel, gridBagConstraints);

        twoPlayersRadioButton.setText("Two Players");
        twoPlayersRadioButton.setBackground(Color.WHITE);
        twoPlayersRadioButton.setSelected(true);
        playersButtonGroup.add(twoPlayersRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(twoPlayersRadioButton, gridBagConstraints);
        twoPlayersRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                twoPlayersRadioButtonActionPerformed(e);
            }
        });

        onePlayerRadioButton.setText("One Player");
        onePlayerRadioButton.setBackground(Color.WHITE);
        playersButtonGroup.add(onePlayerRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(onePlayerRadioButton, gridBagConstraints);
        onePlayerRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onePlayerRadioButtonActionPerformed(e);
            }
        });

        firstPanel.setPreferredSize(new Dimension(160, 75));
        firstPanel.setBackground(Color.WHITE);
        firstPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        firstPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(firstPanel, gridBagConstraints);

        youFirstRadioButton.setText("You First");
        youFirstRadioButton.setBackground(Color.WHITE);
        youFirstRadioButton.setSelected(true);
        firstButtonGroup.add(youFirstRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        firstPanel.add(youFirstRadioButton, gridBagConstraints);

        computerFirstRadioButton.setText("Computer First");
        computerFirstRadioButton.setBackground(Color.WHITE);
        firstButtonGroup.add(computerFirstRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        firstPanel.add(computerFirstRadioButton, gridBagConstraints);

        computerPanel.setPreferredSize(new Dimension(160, 75));
        computerPanel.setBackground(Color.WHITE);
        computerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        computerPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(computerPanel, gridBagConstraints);
        randomRadioButton.setText("Random Computer");
        randomRadioButton.setBackground(Color.WHITE);
        randomRadioButton.setSelected(true);
        computerButtonGroup.add(randomRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        computerPanel.add(randomRadioButton, gridBagConstraints);
        smartRadioButton.setText("Smart Computer");
        smartRadioButton.setBackground(Color.WHITE);
        computerButtonGroup.add(smartRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        computerPanel.add(smartRadioButton, gridBagConstraints);

        buttonsPanel.setPreferredSize(new Dimension(160, 70));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        getContentPane().add(buttonsPanel, gridBagConstraints);
        startStopButton.setText("Start Game");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(startStopButton, gridBagConstraints);
        startStopButton.addActionListener(new ActionListener() {
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
            public void actionPerformed(ActionEvent e) {
                exitButtonActionPerformed(e);
            }
        });

        messageTextField.setText("Game Stopped");
        youFirstRadioButton.setEnabled(false);
        computerFirstRadioButton.setEnabled(false);
        randomRadioButton.setEnabled(false);
        smartRadioButton.setEnabled(false);

        pack();

        possibleWins[0] = "012";
        possibleWins[1] = "345";
        possibleWins[2] = "678";
        possibleWins[3] = "036";
        possibleWins[4] = "147";
        possibleWins[5] = "258";
        possibleWins[6] = "048";
        possibleWins[7] = "246";

        try {
            drawSound = Applet.newAudioClip(new URL("file:" + "assets/uhoh.wav"));
            winSound = Applet.newAudioClip(new URL("file:" + "assets/owin31.wav"));
        } catch (Exception e) {
            System.out.println("Error loading sound files");
        }
    }

    private void exitForm(WindowEvent e) {
        System.exit(0);
    }

    private void boxTextFieldMousePressed(MouseEvent e) {
        if (canClick) {
            int i;
            Point p = e.getComponent().getLocation();
            for (i = 0; i < 9; i++) {
                if (p.x == boxTextField[i].getX() && p.y == boxTextField[i].getY()) {
                    break;
                }
            }
            markClickedBox(i);
        }
    }

    private void twoPlayersRadioButtonActionPerformed(ActionEvent e) {
        youFirstRadioButton.setEnabled(false);
        computerFirstRadioButton.setEnabled(false);
        randomRadioButton.setEnabled(false);
        smartRadioButton.setEnabled(false);
    }

    private void onePlayerRadioButtonActionPerformed(ActionEvent e) {
        youFirstRadioButton.setEnabled(true);
        computerFirstRadioButton.setEnabled(true);
        randomRadioButton.setEnabled(true);
        smartRadioButton.setEnabled(true);
    }

    private void startStopButtonActionPerformed(ActionEvent e) {
        if (startStopButton.getText().equals("Start Game")) {
            startStopButton.setText("Stop Game");
            twoPlayersRadioButton.setEnabled(false);
            onePlayerRadioButton.setEnabled(false);
            youFirstRadioButton.setEnabled(false);
            computerFirstRadioButton.setEnabled(false);
            randomRadioButton.setEnabled(false);
            smartRadioButton.setEnabled(false);
            exitButton.setEnabled(false);
            xTurn = true;
            messageTextField.setText("X's Turn");
            for (int i = 0; i < 9; i++) {
                boxTextField[i].setText("");
                boxTextField[i].setBackground(Color.WHITE);
            }
            canClick = true;
            numberClicks = 0;
            gameOver = false;
            if (computerFirstRadioButton.isSelected()) {
                computerTurn();
            }
        } else {
            startStopButton.setText("Start Game");
            if (!gameOver) {
                messageTextField.setText("Game Stopped");
            }
            twoPlayersRadioButton.setEnabled(true);
            onePlayerRadioButton.setEnabled(true);
            if (onePlayerRadioButton.isSelected()) {
                youFirstRadioButton.setEnabled(true);
                computerFirstRadioButton.setEnabled(true);
                randomRadioButton.setEnabled(true);
                smartRadioButton.setEnabled(true);
            }
            exitButton.setEnabled(true);
            canClick = false;
        }
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void markClickedBox(int i) {
        String whoWon = "";
        if (!boxTextField[i].getText().equals("")) {
            return;
        }
        numberClicks++;
        if (xTurn) {
            boxTextField[i].setText("X");
            xTurn = false;
            messageTextField.setText("O's Turn");
        } else {
            boxTextField[i].setText("O");
            xTurn = true;
            messageTextField.setText("X's Turn");
        }
        whoWon = checkForWin();
        if (!whoWon.equals("")) {
            winSound.play();
            messageTextField.setText(whoWon + " wins!");
            gameOver = true;
            startStopButton.doClick();
            return;
        } else if (numberClicks == 9) {
            drawSound.play();
            messageTextField.setText("It's a draw");
            gameOver = true;
            startStopButton.doClick();
            return;
        }
        if (onePlayerRadioButton.isSelected()) {
            if ((xTurn && computerFirstRadioButton.isSelected()) || (!xTurn && youFirstRadioButton.isSelected())) {
                computerTurn();
            }
        }
    }

    private String checkForWin() {
        String winner = "";
        int[] boxNumber = new int[3];
        String[] mark = new String[3];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 3; j++) {
                boxNumber[j] = Integer.valueOf(String.valueOf(possibleWins[i].charAt(j))).intValue();
                mark[j] = boxTextField[boxNumber[j]].getText();
            }
            if (mark[0].equals(mark[1]) && mark[0].equals(mark[2]) && mark[1].equals(mark[2]) && !mark[0].equals("")) {
                winner = mark[0];
                for (int j = 0; j < 3; j++) {
                    boxTextField[boxNumber[j]].setBackground(Color.RED);
                }
            }
        }

        return winner;
    }

    private void computerTurn() {
        int selectedBox;
        int i, n;
        int j, k;
        String computerMark, playerMark, markToFind;
        int[] boxNumber = new int[3];
        String[] mark = new String[3];
        int emptyBox;
        int[] bestMoves = {4, 0, 2, 6, 8, 1, 3, 5, 7};
        if (randomRadioButton.isSelected()) {
            n = myRandom.nextInt(9 - numberClicks) + 1;
            i = 0;
            for (selectedBox = 0; selectedBox < 9; selectedBox++) {
                if (boxTextField[selectedBox].getText().equals("")) {
                    i++;
                }
                if (i == n) {
                    break;
                }
            }
            markClickedBox(selectedBox);
        } else {
            if (computerFirstRadioButton.isSelected()) {
                computerMark = "X";
                playerMark = "O";
            } else {
                computerMark = "O";
                playerMark = "X";
            }

            for (k = 1; k <= 2; k++) {
                if (k == 1) {
                    markToFind = computerMark;
                } else {
                    markToFind = playerMark;
                }
                for (i = 0; i < 8; i++) {
                    n = 0;
                    emptyBox = 0;
                    for (j = 0; j < 3; j++) {
                        boxNumber[j] = Integer.valueOf(String.valueOf(possibleWins[i].charAt(j))).intValue();
                        mark[j] = boxTextField[boxNumber[j]].getText();
                        if (mark[j].equals(markToFind)) {
                            n++;
                        } else if (mark[j].equals("")) {
                            emptyBox = boxNumber[j];
                        }
                    }
                    if (n == 2 && emptyBox != 0) {
                        markClickedBox(emptyBox);
                        return;
                    }
                }
            }
            for (i = 0; i < 9; i++) {
                if (boxTextField[bestMoves[i]].getText().equals("")) {
                    markClickedBox(bestMoves[i]);
                    return;
                }
            }
        }
    }
}
