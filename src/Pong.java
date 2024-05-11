// James Yue
// 5/10/24

// I attempted to replicate Atari's Pong game, which was released in 1972.
// Pong is a two-player game. A ball bounces back and forth between the player's paddles.
// If the paddle does not hit the ball and reaches the end of the wall, the other player scores.

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.event.*;

public class Pong implements ActionListener, KeyListener {
    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;
    private static final int TOP_OF_WINDOW = 23;
    private static final int DELAY_IN_MILLISEC = 20;
    private static final int STEP_SIZE = 50;

    // Instance variables that allows the Pong class to access data from other classes
    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball b;
    private PongView window;

    public Pong() {
        // Initializing the instance variable by setting their parameter
        paddleLeft = new Paddle(50, 300,30, 8,40,Color.WHITE);
        paddleRight = new Paddle(850, 300,30, 8,40,Color.WHITE);
        b = new Ball(600, 300, 5,5, 10, Color.WHITE);

        window = new PongView(this, paddleLeft, paddleRight, b);
        window.addKeyListener(this);

        Timer clock = new Timer(DELAY_IN_MILLISEC, this);
        clock.start();
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    // Accessing keyboard input
    public void keyPressed(KeyEvent e) {
        int topOfPane = window.getInsets().top;
        switch(e.getKeyCode())
        {
            // Space bar = begin game
            case KeyEvent.VK_SPACE:
                window.setGameStarted(true);
                break;

            // Key controls for left paddle
            // A key = move paddle up
            case KeyEvent.VK_A:
                paddleLeft.shiftY(-STEP_SIZE, topOfPane, PongView.SCREEN_HEIGHT);
                break;
            // Z key = move paddle down
            case KeyEvent.VK_Z:
                paddleLeft.shiftY(STEP_SIZE, 0, PongView.SCREEN_HEIGHT);
                break;

            // Key controls for right paddle
            // Up arrow key = move paddle up
            case KeyEvent.VK_UP:
                paddleRight.shiftY(-STEP_SIZE, topOfPane, PongView.SCREEN_HEIGHT);
                break;
            // Down arrow key = move paddle down
            case KeyEvent.VK_DOWN:
                paddleRight.shiftY(STEP_SIZE, 0, PongView.SCREEN_HEIGHT);
                break;
        }
        // To correspond with the user's input, repaint the window
        window.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        b.move();
        b.bounce(0, MAX_WIDTH, TOP_OF_WINDOW, MAX_HEIGHT);
        checkContact();
        window.repaint();
    }

    public void checkContact() {
        if (paddleLeft.getX() + paddleLeft.getWidth()  + 10 > b.getX() && paddleLeft.getY() < b.getY() + 10 && paddleLeft.getY() + paddleLeft.getHeight()  + 10 > b.getY() + (b.getRadius() * 2)) {
            b.setDirection(-1 * b.getDx());
        }

        if (paddleRight.getX() < b.getX() + (b.getRadius() * 2) + 10 && paddleRight.getY() < b.getY() + 10 && paddleRight.getY() + paddleRight.getHeight() + 10 > b.getY() + (b.getRadius() * 2)) {
            b.setDirection(-1 * b.getDx());
        }

        // If player one scores, reset the ball to the middle and bounce it toward player two
        if (b.isScoreLeft()) {
            b.setBallLocation(450, 300);
            b.setDirection(-1 * b.getDx());
        }
        // If player two scores, reset the ball to the middle and bounce it toward player one
        if (b.isScoreRight()) {
            b.setBallLocation(450, 300);
            b.setDirection(-1 * b.getDx());
        }
    }

    public boolean isGameOver() {
        // Checks if any player has reached the point threshold to win the game
        if (b.getScoreOne() >= 9 || b.getScoreTwo() >= 9) {
            return true;
        }
        return false;
    }

    public String checkWinner() {
        String winner = "";
        // Determines what player has a higher score
        if (b.getScoreOne() < b.getScoreTwo()) {
            winner = "Player 1     Wins!";
        }
        else {
            winner = "Player 2     Wins!";
        }
        return winner;
    }

    public static void main(String[] args) {
        Pong game = new Pong();
    }
}