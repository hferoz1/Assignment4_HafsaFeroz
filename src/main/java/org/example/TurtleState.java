package org.example;

public class TurtleState {
    private final double x;
    private final double y;
    private final double direction;
    private final boolean penDown;

    /**
     * creates new turtle state with all info
     * @param x x-coord
     * @param y y-coord
     * @param direction direction turtle is facing
     * @param penDown if pen is down or not
     */
    public TurtleState(double x, double y, double direction, boolean penDown) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.penDown = penDown;
    }

    /**
     * @return saved x-coord
     */
    public double getX() {
        return x;
    }

    /**
     * @return saved y-coord
     */
    public double getY() {
        return y;
    }

    /**
     * @return direction of turtle
     */
    public double getDirection() {
        return direction;
    }

    /**
     * @return if pen is down or not
     */
    public boolean isPenDown() {
        return penDown;
    }
}