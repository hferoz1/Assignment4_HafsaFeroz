package org.example;

public class TurnCommand implements Command {
    private Turtle turtle;
    private double angle;

    public TurnCommand(Turtle turtle, double angle) {
        this.turtle = turtle;
        this.angle = angle;
    }

    @Override
    public void execute() {
        turtle.turn(angle);
    }
}