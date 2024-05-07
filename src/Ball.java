// James Yue

import java.awt.Color;
import java.awt.Graphics;

public class Ball {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private int radius;
    private Color color;
    private int scoreOne = 0;
    private int scoreTwo = 0;

    public Ball(int x, int y, int dx, int dy, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
    }

    public void move() {
        x = x + dx;
        y = y + dy;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getDx() {
        return dx;
    }
    public int getDy() {
        return dy;
    }
    public int getRadius() {
        return radius;
    }
    public void setDirection(int direction) {
        dx = direction;
    }

    public void setBallLocation(int newX, int newY) {
        this.x = newX;
        this.y = newY;
    }


    // Make the bounce only if it hits the paddle or the top and bottom. The game will end if it hits the ends of the window
    // Reset the game and update the score.
    // if (goes to the left) update the player's score on the left side vice versa
    public void bounce(int xLow, int xHigh, int yLow, int yHigh) {
//        if ((x - radius <= xLow && dx < 0) || (x + radius >= xHigh && dx > 0)) {
//            dx = -dx;
//        }
        if ((y - radius <= yLow && dy < 0) || (y + radius >= yHigh && dy > 0)) {
            dy = -dy;
        }
    }

    public boolean isScoreLeft() {
        if (x - radius < 0 && dx < 0) {
            return true;
        }
        return false;
    }

    public boolean isScoreRight() {
        if (x + radius > 900 && dx > 0) {
            return true;
        }
        return false;
    }

    public int getScoreOne() {
        if (x - radius <= 0 && dx < 0) {
            scoreOne++;
        }
        return scoreOne;
    }

    public int getScoreTwo() {
        if (x + radius >= 900 && dx > 0) {
            scoreTwo++;
        }
        return scoreTwo;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
    }
}