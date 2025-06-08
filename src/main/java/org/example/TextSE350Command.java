package org.example;

public class TextSE350Command extends CompositeCommand {

    /**
     * command to draw "SE350"
     * @param turtle turtle for drawing
     * @param matrix matrix to draw on
     * @param size size of each character
     */
    public TextSE350Command(Turtle turtle, Matrix matrix, double size) {
        super();
        double spacing = size + 2;
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new LetterECommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new Digit3Command(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));
        addCommand(new RectangleCommand(turtle, matrix, size * 0.8, size));
    }
}