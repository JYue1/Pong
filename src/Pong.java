// James Yue

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
    private static final int STEP_SIZE = 10;

    private Paddle paddleLeft;
    private Paddle paddleRight;
    private Ball b;
    private PongView window;

    public Pong() {
        paddleLeft = new Paddle(200, 300,10, 20,40,Color.WHITE);
        paddleRight = new Paddle(700, 300,10, 20,40,Color.WHITE);

        b = new Ball(600, 300, 5,5, 10, Color.WHITE);

        this.window = new PongView(paddleLeft, paddleRight, b);
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
        switch(e.getKeyCode())
        {
            case KeyEvent.VK_S:
                // int topOfPane = window.getInsets().top;
                paddleLeft.shiftY(STEP_SIZE, 0, PongView.SCREEN_WIDTH);
                break;
        }
        window.repaint();
    }

    public void actionPerformed(ActionEvent e) {
        b.move();
        b.bounce(0, MAX_WIDTH, TOP_OF_WINDOW, MAX_HEIGHT);
        window.repaint();
    }

    public static void main(String[] args) {
        Pong game = new Pong();
    }
}
