package safecracker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TicTacToe extends JFrame {
    private JTextField messageTextField = new JTextField();
    private JPanel gamePanel = new JPanel();

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
        setBounds((int)(0.5*(screenSize.width - getWidth())), (int)(0.5*screenSize.height - getHeight()), getWidth(), getHeight());

        messageTextField.setPreferredSize(new Dimension(280,50));
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
        pack();
    }

    private void exitForm(WindowEvent e){
        System.exit(0);
    }
}
