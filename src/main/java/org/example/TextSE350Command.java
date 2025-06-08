package org.example;

public class TextSE350Command extends CompositeCommand {

    /**
     * command to draw "SE350"
     * @param turtle turtle for drawing
     * @param matrix matrix
     * @param size size of each char
     */
    public TextSE350Command(Turtle turtle, Matrix matrix, double size) {
        super();
        double spacing = size + 1;

        // draw S
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));

        // draw E
        addCommand(new LetterECommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing * 1.5));

        // draw 3
        addCommand(new Digit3Command(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));

        // draw 5
        addCommand(new LetterSCommand(turtle, matrix, size));
        addCommand(new MoveCommand(turtle, matrix, spacing));

        // draw 0
        addCommand(new RectangleCommand(turtle, matrix, size, size));
    }
}