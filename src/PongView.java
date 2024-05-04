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

//        g.setColor(Color.WHITE);
//        g.setFont(new Font("Arial", Font.BOLD, 40));
//        g.drawString("Welcome to Pong!" , 280, 150);
//        g.drawString("Controls" , 370, 245);
//        g.drawString("Player 1:", 100, 325);
//        g.drawString("Player 2:", 625, 325);
//        g.setFont(new Font("Arial", Font.BOLD, 25));
//        g.drawString("A and X", 125, 375);
//        g.drawString("Arrow Keys", 640, 375);

        g.setColor(Color.WHITE);
        for (int i = 0; i < 19; i++) {
            g.fillRect(450, 45 + (i * 30), 15, 15);
            g.drawRect(450, 45 + (i * 30), 15, 15);
        }

        leftPaddle.draw(g);
        rightPaddle.draw(g);
        b.draw(g);
    }
}
