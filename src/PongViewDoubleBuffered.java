// James Yue

import java.awt.*;
import java.awt.image.BufferStrategy;

public class PongViewDoubleBuffered extends PongView {
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private Ball b;

    public PongViewDoubleBuffered(Paddle leftPaddle, Paddle rightPaddle, Ball b) {
        super(leftPaddle, rightPaddle, b);
        createBufferStrategy(2);
    }

    @Override
    public void paint(Graphics g) {
        BufferStrategy bf = this.getBufferStrategy();
        if (bf == null)
            return;

        Graphics g2 = null;

        try {
            g2 = bf.getDrawGraphics();
            // myPaint does the actual drawing, as described in ManyBallsView
            super.paint(g2);
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
}
