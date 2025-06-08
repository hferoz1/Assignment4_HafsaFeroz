package org.example;

public class LetterSCommand extends CompositeCommand {

    /**
     * command to draw "S"
     * @param turtle turtle for drawing
     * @param matrix matrix to draw on
     * @param size size of letter
     */
    public LetterSCommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TurnCommand(turtle, 0 - turtle.getDirection()));
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180)); // Face left
        addCommand(new MoveCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, -90)); // Face down
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 90)); // Face right
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, -90)); // Face down
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 180)); // Face left
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180)); // Face right
    }
}