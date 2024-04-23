// James Yue

import java.awt.Color;
import java.awt.Graphics;

public class Paddle {
    private int x;
    private int y;
    private int dy;
    private int width;
    private int height;
    private Color color;

    public Paddle(int x, int y, int dy, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.dy = dy;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void move() {
        y = y + dy;
    }

    public void shiftY(int shift, int yLow, int yHigh) {
        if (y - height + shift <= yLow && shift < 0) {
            y = yLow + height;
        }
        else if (y + height + shift >= yHigh && shift > 0) {
            y = yHigh - height;
        }
        else {
            y += shift;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillOval(x - width, y - height, 2 * height, 2 * height);
    }
}
