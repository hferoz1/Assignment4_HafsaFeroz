package org.example;

public class RectangleCommand extends CompositeCommand {

    /**
     * uses command to draw rectangle
     * @param turtle turtle for drawing
     * @param matrix matrix to draw on
     * @param width width of rectangle
     * @param height height of rectangle
     */
    public RectangleCommand(Turtle turtle, Matrix matrix, double width, double height) {
        super();
        addCommand(new TraceCommand(turtle, matrix, width));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, height));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, width));
        addCommand(new TurnCommand(turtle, 90));
        addCommand(new TraceCommand(turtle, matrix, height));
        addCommand(new TurnCommand(turtle, 90));
    }
}