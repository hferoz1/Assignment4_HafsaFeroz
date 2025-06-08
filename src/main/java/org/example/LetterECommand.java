package org.example;

public class LetterECommand extends CompositeCommand {
    public LetterECommand(Turtle turtle, Matrix matrix, double size) {
        super();
        addCommand(new TurnCommand(turtle, -90));                  // Face down
        addCommand(new TraceCommand(turtle, matrix, size));        // Left vertical line (top to bottom)
        addCommand(new TurnCommand(turtle, 90));                   // Face right
        addCommand(new TraceCommand(turtle, matrix, size));        // Bottom horizontal line
        addCommand(new TurnCommand(turtle, 180));                  // Face left
        addCommand(new MoveCommand(turtle, matrix, size));         // Move back to left edge
        addCommand(new TurnCommand(turtle, 90));                   // Face up
        addCommand(new MoveCommand(turtle, matrix, size / 2));     // Move to middle
        addCommand(new TurnCommand(turtle, -90));                  // Face right
        addCommand(new TraceCommand(turtle, matrix, size * 0.8));  // Middle horizontal line
        addCommand(new TurnCommand(turtle, 180));                  // Face left
        addCommand(new MoveCommand(turtle, matrix, size * 0.8));   // Move back to left edge
        addCommand(new TurnCommand(turtle, 90));                   // Face up
        addCommand(new MoveCommand(turtle, matrix, size / 2));     // Move to top
        addCommand(new TurnCommand(turtle, -90));                  // Face right
        addCommand(new TraceCommand(turtle, matrix, size));        // Top horizontal line
    }
}