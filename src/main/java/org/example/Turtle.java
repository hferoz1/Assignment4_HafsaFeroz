package org.example;

public class Turtle {
    private double x;
    private double y;
    private double direction; // in degrees
    private Pen pen;

    public Turtle(Pen pen) {
        this.x = 0.0;
        this.y = 0.0;
        this.direction = 0.0; // facing east
        this.pen = pen;
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

    public Pen getPen() {
        return pen;
    }


    public void move(Matrix matrix, double distance) {
        double oldX = x;
        double oldY = y;

        double radians = Math.toRadians(direction);
        x += distance * Math.cos(radians);
        y += distance * Math.sin(radians);

        pen.drawLine(matrix, oldX, oldY, x, y);
    }

    public void turn(double angle) {
        direction += angle;
        direction = direction % 360; // keep direction between 0-359
        if (direction < 0) {
            direction += 360;
        }
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setDirection(double direction) {
        this.direction = direction % 360;
        if (this.direction < 0) {
            this.direction += 360;
        }
    }

    public TurtleState createSnapshot() {
        return new TurtleState(x, y, direction, pen.isDown());
    }

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