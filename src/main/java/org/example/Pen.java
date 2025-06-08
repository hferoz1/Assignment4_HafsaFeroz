package org.example;

public class Pen {
    private DrawingStrategy strategy;
    private boolean isDown;

    /**
     * creates new pen with drawing strat
     * @param strategy drawing strategy to use
     */
    public Pen(DrawingStrategy strategy) {
        this.strategy = strategy;
        this.isDown = false;
    }

    /**
     * sets drawing strat
     * @param strategy new drawing strat
     */
    public void setStrategy(DrawingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * gets current drawing strat
     * @return current drawing strat
     */
    public DrawingStrategy getStrategy() {
        return strategy;
    }

    /**
     * puts pen down (drawing)
     */
    public void down() {
        this.isDown = true;
    }

    /**
     * puts pen up (not drawing)
     */
    public void up() {
        this.isDown = false;
    }

    /**
     * checks if pen is down
     * @return true if pen is down
     */
    public boolean isDown() {
        return isDown;
    }

    /**
     * draws line between two points if pen is down
     * @param m matrix
     * @param x0 starting x-coord
     * @param y0 starting y-coord
     * @param x1 ending x-coord
     * @param y1 ending y-coord
     */
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        if (isDown) {
            strategy.drawLine(m, x0, y0, x1, y1);
        }
    }
}