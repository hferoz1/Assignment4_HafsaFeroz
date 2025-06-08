package org.example;

public class TraceCommand implements Command {
    private Turtle turtle;
    private Matrix matrix;
    private double distance;

    public TraceCommand(Turtle turtle, Matrix matrix, double distance) {
        this.turtle = turtle;
        this.matrix = matrix;
        this.distance = distance;
    }

    @Override
    public void execute() {
        boolean wasPenDown = turtle.getPen().isDown();
        turtle.getPen().down();
        turtle.move(matrix, distance);
        if (!wasPenDown) {
            turtle.getPen().up();
        }
    }
}