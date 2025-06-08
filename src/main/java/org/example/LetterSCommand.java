package org.example;

public class LetterSCommand extends CompositeCommand {

    /**
     * command to draw the letter "S"
     * @param turtle turtle for drawing
     * @param matrix matrix to draw on
     * @param size size of letter
     */
    public LetterSCommand(Turtle turtle, Matrix matrix, double size) {
        super();
        final double startX = turtle.getX();
        final double startY = turtle.getY();
        addCommand(new Command() {
            @Override
            public void execute() {
                turtle.setPosition(startX, startY);
                turtle.setDirection(0);
            }
        });
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new Command() {
            @Override
            public void execute() {
                turtle.setPosition(startX, startY);
                turtle.setDirection(270);
            }
        });
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new Command() {
            @Override
            public void execute() {
                turtle.setPosition(startX, startY + size / 2);
                turtle.setDirection(0);
            }
        });
        addCommand(new TraceCommand(turtle, matrix, size));
        addCommand(new Command() {
            @Override
            public void execute() {
                turtle.setPosition(startX + size, startY + size / 2);
                turtle.setDirection(270);
            }
        });
        addCommand(new TraceCommand(turtle, matrix, size / 2));
        addCommand(new Command() {
            @Override
            public void execute() {
                turtle.setPosition(startX, startY + size);
                turtle.setDirection(0);
            }
        });
        addCommand(new TraceCommand(turtle, matrix, size));
    }
}
