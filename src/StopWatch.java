import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StopWatch extends JFrame {

    private JButton startButton = new JButton();
    private JButton stopButton = new JButton();
    private JButton exitButton = new JButton();

    private JLabel startLabel = new JLabel();
    private JLabel stopLabel = new JLabel();
    private JLabel elapsedLabel = new JLabel();

    private JTextField startText = new JTextField();
    private JTextField stopText = new JTextField();
    private JTextField elapsedText = new JTextField();

    private long startTime;
    private long stopTime;
    private double elapsedTime;


    public static void main(String[] args) {
        new StopWatch().show();
    }

    private StopWatch(){
        setTitle("StopWatch Application");
        setSize(300, 300);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitForm(e);
            }
        });

        getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints gridConstraints = new GridBagConstraints();

        startButton.setText("Start Timing");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        getContentPane().add(startButton, gridConstraints);
        startButton.addActionListener(this::startButtonActionPerformed);

        stopButton.setText("Stop Timing");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 1;
        getContentPane().add(stopButton, gridConstraints);
        stopButton.addActionListener(this::stopButtonActionPerformed);

        exitButton.setText("Exit");
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 2;
        getContentPane().add(exitButton, gridConstraints);
        exitButton.addActionListener(this::exitButtonActionPerformed);

        startLabel.setText("Start Time");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        getContentPane().add(startLabel, gridConstraints);

        stopLabel.setText("Stop Time");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        getContentPane().add(stopLabel, gridConstraints);

        elapsedLabel.setText("Elapsed Time");
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 2;
        getContentPane().add(elapsedLabel, gridConstraints);

        startText.setText("");
        startText.setColumns(15);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        getContentPane().add(startText, gridConstraints);

        stopText.setText("");
        stopText.setColumns(15);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 1;
        getContentPane().add(stopText, gridConstraints);

        elapsedText.setText("");
        elapsedText.setColumns(15);
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 2;
        getContentPane().add(elapsedText, gridConstraints);
        pack();
    }

    private void startButtonActionPerformed(ActionEvent e) {
        startTime = System.currentTimeMillis();
        startText.setText(String.valueOf(startTime));
        stopText.setText("");
        elapsedText.setText("");
    }

    private void stopButtonActionPerformed(ActionEvent e) {
        stopTime = System.currentTimeMillis();
        stopText.setText(String.valueOf(stopTime));
        elapsedTime = (stopTime - startTime) / 1000.0;
        elapsedText.setText(String.valueOf(elapsedTime));
    }

    private void exitButtonActionPerformed(ActionEvent e) {
        System.exit(0);
    }

    private void exitForm(WindowEvent e) {
        System.exit(0);
    }




}
