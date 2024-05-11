// James Yue

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    // Instance variables
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int radius;
    private Color color;
    // Keeping track of player scores
    private double scoreOne = 0;
    private double scoreTwo = 0;

    public Ball(int x, int y, int dx, int dy, int radius, Color color) {
        // Initializing instance variables
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
    }

    // Allows the ball to move to a different location
    public void move() {
        x = x + dx;
        y = y + dy;
    }

    // Getter methods
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDx() {
        return dx;
    }
    public int getRadius() {
        return radius;
    }

    public int getScoreOne() {
        if (x - radius <= 0 && dx < 0) {
            scoreOne += 0.5;
        }
        int score = (int) scoreOne;
        return score;
    }

    public int getScoreTwo() {
        if (x + radius >= 900 && dx > 0) {
            scoreTwo += 0.5;
        }
        int score = (int) scoreTwo;
        return score;
    }

    // Setter methods
    public void setDirection(int direction) {
        dx = direction;
    }

    public void setBallLocation(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }

    // Allows the ball to bounce when it hits the top or bottom of the window
    public void bounce(int xLow, int xHigh, int yLow, int yHigh) {
        if ((y - radius <= yLow && dy < 0) || (y + radius >= yHigh && dy > 0)) {
            dy = -dy;
        }
    }

    // Checks if the ball has hit the left side of the window
    public boolean isScoreLeft() {
        if (x - radius < 0 && dx < 0) { // 1
            return true;
        }
        return false;
    }

    // Checks if the ball has hit the right side of the window
    public boolean isScoreRight() {
        if (x + radius > 900 && dx > 0) { // 899
            return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}