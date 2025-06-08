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
        addCommand(new TraceCommand(turtle, matrix, size));        // Top horizontal line
        addCommand(new TurnCommand(turtle, -90));                  // Face down
        addCommand(new TraceCommand(turtle, matrix, size / 2));    // Right vertical (top half)
        addCommand(new TurnCommand(turtle, 90));                   // Face left
        addCommand(new TraceCommand(turtle, matrix, size * 0.8));  // Middle horizontal line
        addCommand(new TurnCommand(turtle, -90));                  // Face down
        addCommand(new TraceCommand(turtle, matrix, size / 2));    // Right vertical (bottom half)
        addCommand(new TurnCommand(turtle, 90));                   // Face left
        addCommand(new TraceCommand(turtle, matrix, size));        // Bottom horizontal line
    }
}