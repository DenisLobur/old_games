package safecracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.CookieHandler;

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

    }

    private void onePlayerRadioButtonActionPerformed(ActionEvent e) {

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
            }
            canClick = true;
            numberClicks = 0;
        } else {
            startStopButton.setText("Start Game");
            messageTextField.setText("Game Stopped");
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
        if (!whoWon.equals("")) {
            messageTextField.setText(whoWon + " wins!");
            startStopButton.doClick();
            return;
        } else if (numberClicks == 9) {
            messageTextField.setText("It's a draw");
            startStopButton.doClick();
            return;
        }
    }
}
