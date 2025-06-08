package org.example;

public class MoveCommand implements Command {
    private Turtle turtle;
    private Matrix matrix;
    private double distance;

    public MoveCommand(Turtle turtle, Matrix matrix, double distance) {
        this.turtle = turtle;
        this.matrix = matrix;
        this.distance = distance;
    }

    @Override
    public void execute() {
        boolean wasPenDown = turtle.getPen().isDown();
        turtle.getPen().up();
        turtle.move(matrix, distance);
        if (wasPenDown) {
            turtle.getPen().down();
        }
    }
}