import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MatchPics extends JFrame {

    private JPanel gamePanel = new JPanel();
    private JLabel[] photoLabel = new JLabel[20];
    private JPanel resultPanel = new JPanel();
    private JLabel player1Label = new JLabel();
    private JLabel player2Label = new JLabel();
    private JTextField[] scoreTextField = new JTextField[2];
    private JLabel messageLabel = new JLabel();
    private JPanel playersPanel = new JPanel();
    private ButtonGroup playersButtonGroup = new ButtonGroup();
    private JRadioButton twoPlayersRadioButton = new JRadioButton();
    private JRadioButton onePlayerRadioButton = new JRadioButton();
    private JPanel playWhoPanel = new JPanel();
    private ButtonGroup playWhoButtonGroup = new ButtonGroup();
    private JRadioButton playAloneRadioButton = new JRadioButton();
    private JRadioButton playComputerRadioButton = new JRadioButton();
    private JPanel difficultyPanel = new JPanel();
    private ButtonGroup difficultyButtonGroup = new ButtonGroup();
    private JRadioButton easiestRadioButton = new JRadioButton();
    private JRadioButton easyRadioButton = new JRadioButton();
    private JRadioButton hardRadioButton = new JRadioButton();
    private JRadioButton hardestRadioButton = new JRadioButton();
    private JPanel buttonsPanel = new JPanel();
    private JButton startStopButton = new JButton();
    private JButton exitButton = new JButton();

    public static void main(String[] args) {
        new MatchPics().show();
    }

    public MatchPics() {
        setTitle("Match Pics");
        setResizable(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints;

        gamePanel.setPreferredSize(new Dimension(625, 530));
        gamePanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 5;
        getContentPane().add(gamePanel, gridBagConstraints);

        for (int i = 0; i < 20; i++) {
            photoLabel[i] = new JLabel();
            photoLabel[i].setPreferredSize(new Dimension(150, 100));
            photoLabel[i].setOpaque(true);
            photoLabel[i].setBackground(Color.WHITE);
            gridBagConstraints = new GridBagConstraints();
            gridBagConstraints.gridx = i % 4;
            gridBagConstraints.gridy = i / 4;
            gridBagConstraints.insets = new Insets(5, 5, 0, 0);
            if (gridBagConstraints.gridx == 3) {
                gridBagConstraints.insets = new Insets(5, 5, 0, 5);
            }
            if (gridBagConstraints.gridy == 4) {
                gridBagConstraints.insets = new Insets(5, 5, 5, 0);
            }
            if (gridBagConstraints.gridx == 3 && gridBagConstraints.gridy == 4) {
                gridBagConstraints.insets = new Insets(5, 5, 5, 5);
            }
            gamePanel.add(photoLabel[i], gridBagConstraints);
            photoLabel[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    photoLabelMousePressed(e);
                }
            });
        }

        resultPanel.setPreferredSize(new Dimension(160, 140));
        resultPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        getContentPane().add(resultPanel, gridBagConstraints);

        player1Label.setText("Player 1");
        player1Label.setFont(new Font("Arial", Font.BOLD, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        resultPanel.add(player1Label, gridBagConstraints);

        scoreTextField[0] = new JTextField();
        scoreTextField[0].setPreferredSize(new Dimension(100, 25));
        scoreTextField[0].setText("0");
        scoreTextField[0].setEditable(false);
        scoreTextField[0].setBackground(Color.WHITE);
        scoreTextField[0].setHorizontalAlignment(SwingConstants.CENTER);
        scoreTextField[0].setFont(new Font("Arial", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        resultPanel.add(scoreTextField[0], gridBagConstraints);

        player2Label.setText("Player 2");
        player2Label.setFont(new Font("Arial", Font.BOLD, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        resultPanel.add(player2Label, gridBagConstraints);

        scoreTextField[1] = new JTextField();
        scoreTextField[1].setPreferredSize(new Dimension(100, 25));
        scoreTextField[1].setText("0");
        scoreTextField[1].setEditable(false);
        scoreTextField[1].setBackground(Color.WHITE);
        scoreTextField[1].setHorizontalAlignment(SwingConstants.CENTER);
        scoreTextField[1].setFont(new Font("Arial", Font.PLAIN, 16));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        resultPanel.add(scoreTextField[1], gridBagConstraints);

        messageLabel.setPreferredSize(new Dimension(160, 40));
        messageLabel.setOpaque(true);
        messageLabel.setBackground(Color.YELLOW);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setText("");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
        resultPanel.add(messageLabel, gridBagConstraints);

        playersPanel.setPreferredSize(new Dimension(160, 75));
        playersPanel.setBackground(Color.CYAN);
        playersPanel.setBorder(BorderFactory.createTitledBorder("Number of Players?"));
        playersPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(playersPanel, gridBagConstraints);

        twoPlayersRadioButton.setText("Two Players");
        twoPlayersRadioButton.setBackground(Color.CYAN);
        twoPlayersRadioButton.setSelected(true);
        playersButtonGroup.add(twoPlayersRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(twoPlayersRadioButton, gridBagConstraints);
        twoPlayersRadioButton.addActionListener(e -> twoPlayersRadioButtonActionPerformed(e));

        onePlayerRadioButton.setText("One Player");
        onePlayerRadioButton.setBackground(Color.CYAN);
        playersButtonGroup.add(onePlayerRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playersPanel.add(onePlayerRadioButton, gridBagConstraints);
        onePlayerRadioButton.addActionListener(e -> onePlayerRadioButtonActionPerformed(e));

        playWhoPanel.setPreferredSize(new Dimension(160, 75));
        playWhoPanel.setBackground(Color.CYAN);
        playWhoPanel.setBorder(BorderFactory.createTitledBorder("Play Who?"));
        playWhoPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(playWhoPanel, gridBagConstraints);
        playAloneRadioButton.setText("Play Alone");
        playAloneRadioButton.setBackground(Color.CYAN);
        playAloneRadioButton.setSelected(true);
        playWhoButtonGroup.add(playAloneRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playWhoPanel.add(playAloneRadioButton, gridBagConstraints);
        playAloneRadioButton.addActionListener(e -> playAloneRadioButtonActionPerformed(e));

        playComputerRadioButton.setText("Play Computer");
        playComputerRadioButton.setBackground(Color.CYAN);
        playWhoButtonGroup.add(playComputerRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        playWhoPanel.add(playComputerRadioButton, gridBagConstraints);
        playComputerRadioButton.addActionListener(e -> playComputerRadioButtonActionPerformed(e));

        difficultyPanel.setPreferredSize(new Dimension(160, 125));
        difficultyPanel.setBackground(Color.CYAN);
        difficultyPanel.setBorder(BorderFactory.createTitledBorder("Difficulty?"));
        difficultyPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(5, 10, 5, 10);
        getContentPane().add(difficultyPanel, gridBagConstraints);

        easiestRadioButton.setText("Easiest");
        easiestRadioButton.setBackground(Color.CYAN);
        easiestRadioButton.setSelected(true);
        difficultyButtonGroup.add(easiestRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(easiestRadioButton, gridBagConstraints);

        easiestRadioButton.addActionListener(e -> easiestRadioButtonActionPerformed(e));
        easyRadioButton.setText("Easy");
        easyRadioButton.setBackground(Color.CYAN);
        easyRadioButton.setSelected(true);
        difficultyButtonGroup.add(easyRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(easyRadioButton, gridBagConstraints);
        easyRadioButton.addActionListener(e -> easyRadioButtonActionPerformed(e));

        hardRadioButton.setText("Hard");
        hardRadioButton.setBackground(Color.CYAN);
        hardRadioButton.setSelected(true);
        difficultyButtonGroup.add(hardRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(hardRadioButton, gridBagConstraints);
        hardRadioButton.addActionListener(e -> hardRadioButtonActionPerformed(e));

        hardestRadioButton.setText("Hardest");
        hardestRadioButton.setBackground(Color.CYAN);
        hardestRadioButton.setSelected(true);
        difficultyButtonGroup.add(hardestRadioButton);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        difficultyPanel.add(hardestRadioButton, gridBagConstraints);
        hardestRadioButton.addActionListener(e -> hardestRadioButtonActionPerformed(e));

        buttonsPanel.setPreferredSize(new Dimension(160, 70));
        buttonsPanel.setLayout(new GridBagLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        getContentPane().add(buttonsPanel, gridBagConstraints);

        startStopButton.setText("Start Game");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        buttonsPanel.add(startStopButton, gridBagConstraints);
        startStopButton.addActionListener(e -> startStopButtonActionPerformed(e));

        exitButton.setText("Exit");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        buttonsPanel.add(exitButton, gridBagConstraints);
        exitButton.addActionListener(e -> exitButtonActionPerformed(e));


        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * screenSize.width - getWidth()), (int) (0.5 * screenSize.height - getHeight()), getWidth(), getHeight());
    }

    private void exitForm(WindowEvent e) {
        System.exit(0);
    }

    private void photoLabelMousePressed(MouseEvent e) {

    }

    private void twoPlayersRadioButtonActionPerformed(ActionEvent e) {

    }

    private void onePlayerRadioButtonActionPerformed(ActionEvent e) {

    }

    private void playAloneRadioButtonActionPerformed(ActionEvent e) {

    }

    private void playComputerRadioButtonActionPerformed(ActionEvent e) {

    }

    private void easiestRadioButtonActionPerformed(ActionEvent e) {

    }

    private void easyRadioButtonActionPerformed(ActionEvent e) {

    }

    private void hardRadioButtonActionPerformed(ActionEvent e) {

    }

    private void hardestRadioButtonActionPerformed(ActionEvent e) {

    }

    private void startStopButtonActionPerformed(ActionEvent e) {

    }

    private void exitButtonActionPerformed(ActionEvent e) {

    }
}
