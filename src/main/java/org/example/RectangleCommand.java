package org.example;

public class RectangleCommand extends CompositeCommand {

    public RectangleCommand(Turtle turtle, Matrix matrix, double width, double height) {
        super();
        addCommand(new TraceCommand(turtle, matrix, width));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, height));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, width));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, height));
        addCommand(new TurnCommand(turtle, 90));
    }
}