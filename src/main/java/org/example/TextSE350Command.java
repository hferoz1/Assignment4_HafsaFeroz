package org.example;

public class TextSE350Command extends CompositeCommand {
    public TextSE350Command(Turtle turtle, Matrix matrix, double size) {
        super();
        double spacing = size + 1;

        // Draw S
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));

        // Draw E
        addCommand(new LetterECommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing * 1.5));

        // Draw 3
        addCommand(new Digit3Command(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));

        // Draw 5 (same as S)
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));

        // Draw 0 (rectangle)
        addCommand(new RectangleCommand(turtle, matrix, size, size));
    }
}