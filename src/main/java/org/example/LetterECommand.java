package org.example;

public class LetterECommand extends CompositeCommand {

    /**
     * creates command sequence to draw "E"
     * @param turtle turtle used to trace letter
     * @param matrix matrix representing canvas
     * @param size size for drawing each part of the letter
     */
    public LetterECommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TurnCommand(turtle, 0 - turtle.getDirection()));
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new MoveCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, size * 0.7));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new MoveCommand(turtle, matrix, size * 0.7));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new MoveCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, size));
    }
}
