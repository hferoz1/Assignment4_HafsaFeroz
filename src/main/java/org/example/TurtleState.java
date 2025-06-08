package org.example;

public class TurtleState {
    private final double x;
    private final double y;
    private final double direction;
    private final boolean penDown;

    public TurtleState(double x, double y, double direction, boolean penDown) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.penDown = penDown;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDirection() {
        return direction;
    }

    public boolean isPenDown() {
        return penDown;
    }
}