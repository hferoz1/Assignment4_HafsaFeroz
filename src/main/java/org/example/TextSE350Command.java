package org.example;

public class TextSE350Command extends CompositeCommand {

    public TextSE350Command(Turtle turtle, Matrix matrix, double size) {
        super();
        double spacing = size + 1;
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new LetterECommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing * 1.5));
        addCommand(new Digit3Command(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new RectangleCommand(turtle, matrix, size, size));
    }
}