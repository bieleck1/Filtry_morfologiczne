
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class ImageScreen extends JPanel {

    private BufferedImage image;

    public ImageScreen() {
        image = null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
