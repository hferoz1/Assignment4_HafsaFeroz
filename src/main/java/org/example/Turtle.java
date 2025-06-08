package org.example;

public class Turtle {
    private double x;
    private double y;
    private double direction;
    private Pen pen;

    /**
     * creates new turtle with the pen
     * @param pen pen for drawing
     */
    public Turtle(Pen pen) {
        this.x = 0.0;
        this.y = 0.0;
        this.direction = 0.0;
        this.pen = pen;
    }

    /**
     * gets x-coord of turtle
     * @return x-coord of turtle
     */
    public double getX() {
        return x;
    }

    /**
     * gets y-coord of turtle
     * @return y-coord of turtle
     */
    public double getY() {
        return y;
    }

    /**
     * get direction of turtle
     * @return direction of turtle
     */
    public double getDirection() {
        return direction;
    }

    /**
     * gets turtle's pen
     * @return turtle's pen
     */
    public Pen getPen() {
        return pen;
    }

    /**
     * moves turtle forward by specified distance
     * @param matrix matrix to draw on
     * @param distance distance to move forward
     */
    public void move(Matrix matrix, double distance) {
        double oldX = x;
        double oldY = y;

        double radians = Math.toRadians(direction);
        x += distance * Math.cos(radians);
        y += distance * Math.sin(radians);
        pen.drawLine(matrix, oldX, oldY, x, y);
    }

    /**
     * turns turtle by specified angle
     * @param angle angle to turn by in degrees
     */
    public void turn(double angle) {
        direction += angle;
        direction = ((direction % 360) + 360) % 360;
    }

    /**
     * sets position of turtle
     * @param x new x-coord of turtle
     * @param y new y-coord of turtle
     */
    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * sets direction of turtle
     * @param direction new direction of turtle in degrees
     */
    public void setDirection(double direction) {
        this.direction = ((direction % 360) + 360) % 360;
    }

    /**
     * creates snapshot of turtle's current state
     * @return snapshot of turtle's state
     */
    public TurtleState createSnapshot() {
        return new TurtleState(x, y, direction, pen.isDown());
    }

    /**
     * restores turtle to saved state
     * @param state state to restore turtle to
     */
    public void restoreState(TurtleState state) {
        this.x = state.getX();
        this.y = state.getY();
        this.direction = state.getDirection();
        if (state.isPenDown()) {
            pen.down();
        } else {
            pen.up();
        }
    }
}