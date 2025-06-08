package org.example;

public class MoveCommand implements Command {
    private Turtle turtle;
    private Matrix matrix;
    private double distance;

    /**
     * creates move command
     * @param turtle turtle to move
     * @param matrix matrix
     * @param distance distance to move
     */
    public MoveCommand(Turtle turtle, Matrix matrix, double distance) {
        this.turtle = turtle;
        this.matrix = matrix;
        this.distance = distance;
    }

    @Override
    /**
     * executes move command by lifting pen
     */
    public void execute() {
        boolean wasPenDown = turtle.getPen().isDown();
        turtle.getPen().up();
        turtle.move(matrix, distance);
        if (wasPenDown) {
            turtle.getPen().down();
        }
    }
}