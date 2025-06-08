package org.example;

public class LetterECommand extends CompositeCommand {

    public LetterECommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size));
    }
}