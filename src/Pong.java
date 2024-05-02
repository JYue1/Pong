// James Yue

// TODO:
    // Bounce the ball in the opposite direction if the ball hits the paddle
    // Make the opening Window with Welcome screen and following screens
    // Create a isGameOver() method
    // Update the score of the game when someone scores
    // Make a ending window with the ability to replay the game or exit

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong implements ActionListener, KeyListener {
    private static final int MAX_WIDTH = 900;
    private static final int MAX_HEIGHT = 600;
    private static final int TOP_OF_WINDOW = 23;
    private static final int DELAY_IN_MILLISEC = 20;
    private static final int STEP_SIZE = 75;

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball b;
    private PongView window;

    public Pong() {
        paddleLeft = new Paddle(50, 300,10, 8,40,Color.WHITE);
        paddleRight = new Paddle(850, 300,10, 8,40,Color.WHITE);
        b = new Ball(600, 300, 5,5, 10, Color.WHITE);

        this.window = new PongView(paddleLeft, paddleRight, b);
        window.addKeyListener(this);

        Timer clock = new Timer(DELAY_IN_MILLISEC, this);
        clock.start();
    }

//    public void run() {
//
//    }

    public void keyTyped(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    public void keyReleased(KeyEvent e) {
        // Nothing required for this program.
        // However, as a KeyListener, this class must supply this method
    }

    public void keyPressed(KeyEvent e) {
        // The keyCode lets you know which key was pressed
        int topOfPane = window.getInsets().top;
        switch(e.getKeyCode())
        {
            // Key controls for left paddle
            case KeyEvent.VK_A:
                paddleLeft.shiftY(-STEP_SIZE, topOfPane, PongView.SCREEN_HEIGHT);
                break;
            case KeyEvent.VK_Z:
                paddleLeft.shiftY(STEP_SIZE, 0, PongView.SCREEN_HEIGHT);
                break;
            // Key controls for right paddle
            case KeyEvent.VK_UP:
                paddleRight.shiftY(-STEP_SIZE, topOfPane, PongView.SCREEN_HEIGHT);
                break;
            case KeyEvent.VK_DOWN:
                paddleRight.shiftY(STEP_SIZE, 0, PongView.SCREEN_HEIGHT);
                break;
        }
        window.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        b.move();
        b.bounce(0, MAX_WIDTH, TOP_OF_WINDOW, MAX_HEIGHT);
        checkContact();
        window.repaint();
    }

    public void checkContact() {
        if (paddleLeft.getX() + paddleLeft.getWidth() > b.getX() && paddleLeft.getY() <= b.getY() && paddleLeft.getY() + paddleLeft.getHeight() >= b.getY() + (2 * b.getRadius())) {
            b.setDirection(-1 * b.getDx());
            // move the ball beyond (past) the paddle

        }
        if (paddleRight.getX() + paddleRight.getWidth() <= b.getX() && paddleRight.getY() <= b.getY() + (2 * b.getRadius())  && paddleRight.getY() + paddleRight.getHeight() >= b.getY() + (2 * b.getRadius())) {
            b.setDirection(-1 * b.getDx());
        }
    }
    public static void main(String[] args) {
        Pong game = new Pong();
        // game.run();
    }
}