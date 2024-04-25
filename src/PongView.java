// James Yue

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

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

        createBufferStrategy(2);
    }

    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;

        Graphics g2 = null;

        try {
            g2 = bf.getDrawGraphics();
            // myPaint does the actual drawing, as described in ManyBallsView
            myPaint(g2);
        }
        finally {
            // It is best to dispose() a Graphics object when done with it.
            g2.dispose();
        }

        // Shows the contents of the backbuffer on the screen.
        bf.show();

        //Tell the System to do the Drawing now, otherwise it can take a few extra ms until
        //Drawing is done which looks very jerky
        Toolkit.getDefaultToolkit().sync();
    }

    public void myPaint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
        leftPaddle.draw(g);
        rightPaddle.draw(g);
        b.draw(g);
    }
}
