// James Yue

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong implements  ActionListener {
    private static final int MAX_WIDTH = 1200;
    private static final int MAX_HEIGHT = 600;
    private static final int TOP_OF_WINDOW = 0;


    private Ball b;
    private Paddle p;
    private PongView window;

    public Pong() {
        b = new Ball(600, 300, 5,5, 10, Color.WHITE);
        window = new PongView(b);

    }






    public void actionPerformed(ActionEvent e) {
        b.move();
        b.bounce(0, MAX_WIDTH,  TOP_OF_WINDOW, MAX_HEIGHT);
        window.repaint();
    }



    public static void main(String[] args) {
        Pong game = new Pong();
    }
}
