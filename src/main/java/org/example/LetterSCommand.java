package org.example;
public class LetterSCommand extends CompositeCommand {
    public LetterSCommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size));
    }
}