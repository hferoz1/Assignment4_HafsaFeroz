package org.example;

public class TraceCommand implements Command {
    private Turtle turtle;
    private Matrix matrix;
    private double distance;

    /**
     * constructs trace command
     * @param turtle turtle to move and draw with
     * @param matrix matrix to draw on
     * @param distance distance to trace
     */
    public TraceCommand(Turtle turtle, Matrix matrix, double distance) {
        this.turtle = turtle;
        this.matrix = matrix;
        this.distance = distance;
    }

    /**
     * executes trace command
     */
    @Override
    public void execute() {
        turtle.getPen().down();
        turtle.move(matrix, distance);
    }
}