package org.example;

public class LetterSCommand extends CompositeCommand {
    public LetterSCommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TraceCommand(turtle, matrix, size));        // Top horizontal line (left to right)
        addCommand(new TurnCommand(turtle, -90));                  // Turn down
        addCommand(new TraceCommand(turtle, matrix, size / 2));    // Down to middle (right side)
        addCommand(new TurnCommand(turtle, -90));                  // Turn left
        addCommand(new TraceCommand(turtle, matrix, size));        // Middle horizontal line (right to left)
        addCommand(new TurnCommand(turtle, -90));                  // Turn down
        addCommand(new TraceCommand(turtle, matrix, size / 2));    // Down to bottom (left side)
        addCommand(new TurnCommand(turtle, -90));                  // Turn right
        addCommand(new TraceCommand(turtle, matrix, size));        // Bottom horizontal line (left to right)
    }
}