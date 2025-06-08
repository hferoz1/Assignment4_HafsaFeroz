package org.example;

public class Digit3Command extends CompositeCommand {

    /**
     * command to draw the digit "3"
     * @param turtle turtle for drawing
     * @param matrix matrix to draw on
     * @param size size of digit
     */
    public Digit3Command(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TurnCommand(turtle, 0 - turtle.getDirection()));
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new TraceCommand(turtle, matrix, size * 0.8));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new MoveCommand(turtle, matrix, size * 0.8));
        addCommand(new TurnCommand(turtle, -90));
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new TurnCommand(turtle, 180));
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new TurnCommand(turtle, 180));
    }
}