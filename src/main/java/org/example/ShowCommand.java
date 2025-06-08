package org.example;

public class ShowCommand implements Command {
    private Matrix matrix;

    public ShowCommand(Matrix matrix) {
        this.matrix = matrix;
    }

    @Override
    public void execute() {
        System.out.println("Current Drawing:");
        System.out.println(matrix.toString());
    }
}