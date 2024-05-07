// James Yue

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
    private static final int STEP_SIZE = 75;

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball b;
    private PongView window;

    public Pong() {
        paddleLeft = new Paddle(50, 300,10, 8,40,Color.WHITE);
        paddleRight = new Paddle(850, 300,10, 8,40,Color.WHITE);
        b = new Ball(600, 300, 5,5, 10, Color.WHITE);

        window = new PongView(this, paddleLeft, paddleRight, b);
        window.addKeyListener(this);

        Timer clock = new Timer(DELAY_IN_MILLISEC, this);
        clock.start();
    }

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
            case KeyEvent.VK_SPACE:
                window.setGameStarted(true);
                break;
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
        if (paddleLeft.getX() + paddleLeft.getWidth() > b.getX() && paddleLeft.getY() < b.getY() && paddleLeft.getY() + paddleLeft.getHeight() > b.getY() + (b.getRadius() * 2)) {
            b.setDirection(-1 * b.getDx());
            // move the ball beyond (past) the paddle
        }

        if (paddleRight.getX() < b.getX() + (b.getRadius() * 2) && paddleRight.getY() < b.getY() && paddleRight.getY() + paddleRight.getHeight() > b.getY() + (b.getRadius() * 2)) {
            b.setDirection(-1 * b.getDx());
        }

        if (b.isScoreLeft()) {
            b.setBallLocation(450, 300);
            b.setDirection(-1 * b.getDx());
        }
        if (b.isScoreRight()) {
            b.setBallLocation(450, 300);
            b.setDirection(-1 * b.getDx());
        }
    }

    public boolean isGameOver() {
        if (b.getScoreOne() >= 9 || b.getScoreTwo() >= 9) {
            return true;
        }
        return false;
    }

    public String checkWinner() {
        String winner = "";
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