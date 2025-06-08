package org.example;

public class Pen {
    private DrawingStrategy strategy;
    private boolean isDown;

    public Pen(DrawingStrategy strategy) {
        this.strategy = strategy;
        this.isDown = false;
    }

    public void setStrategy(DrawingStrategy strategy) {
        this.strategy = strategy;
    }

    public DrawingStrategy getStrategy() {
        return strategy;
    }

    public void down() {
        this.isDown = true;
    }

    public void up() {
        this.isDown = false;
    }

    public boolean isDown() {
        return isDown;
    }

    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        if (isDown) {
            strategy.drawLine(m, x0, y0, x1, y1);
        }
    }
}