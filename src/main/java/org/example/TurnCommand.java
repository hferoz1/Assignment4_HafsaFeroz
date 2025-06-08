package org.example;

public class TurnCommand implements Command {
    private Turtle turtle;
    private double angle;

    /**
     * constructs turn command
     * @param turtle turtle to turn
     * @param angle angle for turtle to turn
     */
    public TurnCommand(Turtle turtle, double angle) {
        this.turtle = turtle;
        this.angle = angle;
    }

    /**
     * execute turn command
     */
    @Override
    public void execute() {
        turtle.turn(angle);
    }
}