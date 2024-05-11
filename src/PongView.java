// James Yue

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class PongView extends JFrame {
    public static int SCREEN_WIDTH = 900;
    public static int SCREEN_HEIGHT = 600;

    // Instance variables
    private final Pong p;
    private final Paddle leftPaddle;
    private final Paddle rightPaddle;
    private final Ball b;
    private boolean gameStarted = false;

    public PongView(Pong p, Paddle leftPaddle, Paddle rightPaddle, Ball b) {
        // Initializing the instance variables
        this.p = p;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.b = b;

        // Four essentials lines of code to display the window and what actions it should take
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Pong Game");
        this.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.setVisible(true);

        // Creating a double buffer to smooth out the ball's animation
        createBufferStrategy(2);
    }

    // Implementing the double buffer
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;

        Graphics g2 = null;
        try {
            g2 = bf.getDrawGraphics();
            myPaint(g2);
        }
        finally {
            g2.dispose();
        }
        bf.show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void myPaint(Graphics g) {
        // The background of the window is black
        g.setColor(Color.black);
        g.fillRect(0,0, SCREEN_WIDTH, SCREEN_HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 60));

        // Determines whether the space bar has been pressed
        if (gameStarted) {
            g.setColor(Color.WHITE);
            // The lines going down the middle
            for (int i = 0; i < 19; i++) {
                g.fillRect(450, 45 + (i * 30), 15, 15);
                g.drawRect(450, 45 + (i * 30), 15, 15);
            }
            // Drawing the game's objects
            leftPaddle.draw(g);
            rightPaddle.draw(g);
            b.draw(g);
            // Drawing the players scores at the top
            String scoreOne = "" + b.getScoreOne();
            g.drawString(scoreOne, 550, 100);
            String scoreTwo = "" + b.getScoreTwo();
            g. drawString(scoreTwo, 350, 100);
        }
        // The window's homepage of instructions
        else {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.drawString("Welcome to Pong!" , 280, 150);
            g.drawString("Controls:" , 370, 245);
            g.drawString("Player 1:", 100, 325);
            g.drawString("Player 2:", 625, 325);
            g.setFont(new Font("Arial", Font.BOLD, 25));
            g.drawString("Press the SPACE BAR to begin" , 275, 500);
            g.drawString("A and Z", 125, 375);
            g.drawString("Arrow Keys", 640, 375);
        }
        // Checks if a player has won the game
        if (p.isGameOver()) {
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            // After checking the winner, the window will display the winner
            g.drawString(p.checkWinner(), 280, 220);
            b.setBallLocation(450, 300);
        }
    }
}