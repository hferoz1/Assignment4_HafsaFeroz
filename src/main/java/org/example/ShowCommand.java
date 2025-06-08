package org.example;

public class ShowCommand implements Command {
    private Matrix matrix;

    /**
     * show command for matrix
     * @param matrix matrix to display
     */
    public ShowCommand(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    /**
     * executes show command
     */
    public void execute() {
        System.out.println("Current Drawing:");
        System.out.println(matrix.toString());
    }
}