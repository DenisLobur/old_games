package safecracker;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

    private Image image;

    public ImagePanel(Image image) {
        this.image = image;
    }

    public void paintComponent(Graphics g) {
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
