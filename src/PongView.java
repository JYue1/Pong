// James Yue
// Add double buffer?

import javax.swing.*;
import java.awt.*;

public class PongView extends JFrame {

    private final int WINDOW_WIDTH = 1200;
    private final int WINDOW_HEIGHT = 600;

    private Ball b;
    private Paddle p;

    public PongView(Ball b) {
        this.b = b;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Pong Game");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        b.draw(g);
    }
}
