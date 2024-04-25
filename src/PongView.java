// James Yue

import javax.swing.*;
import java.awt.*;

public class PongView extends JFrame {
    public static int SCREEN_WIDTH = 900;
    public static int SCREEN_HEIGHT = 600;

    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball b;

    public PongView(Paddle leftPaddle, Paddle rightPaddle, Ball b) {
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.b = b;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pong Game");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        b.draw(g);
    }
}
