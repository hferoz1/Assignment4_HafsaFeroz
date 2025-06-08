package org.example;

public class LetterECommand extends CompositeCommand {

    public LetterECommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TurnCommand(turtle, 90)); // Face up
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, -90)); // Face right
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180)); // Face left
        addCommand(new MoveCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 90)); // Face down
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, -90)); // Face right
        addCommand(new TraceCommand(turtle, matrix, size * 0.7));
        addCommand(new TurnCommand(turtle, 180)); // Face left
        addCommand(new MoveCommand(turtle, matrix, size * 0.7));
        addCommand(new TurnCommand(turtle, 90)); // Face down
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, -90)); // Face right
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180)); // Face left
    }
}