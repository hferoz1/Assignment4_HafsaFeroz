package org.example;

import java.util.Stack;

public class CommandHistory {
    private Stack<Snapshot> undoStack;
    private Stack<Snapshot> redoStack;

    /**
     * creates new CommandHistory with empty undo/redo stacks
     */
    public CommandHistory() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    /**
     * saves current turtle and matrix state
     * @param turtle turtle whose state should be saved
     * @param matrix matrix whose state should be saved
     */
    public void save(Turtle turtle, Matrix matrix) {
        Matrix matrixCopy = copyMatrix(matrix);
        Snapshot snapshot = new Snapshot(turtle.createSnapshot(), matrixCopy);
        undoStack.push(snapshot);
        redoStack.clear();
    }

    /**
     * undos the command by restoring the last state
     * @param turtle turtle to restore
     * @param matrix matrix to restore
     * @return true if undo worked
     */
    public boolean undo(Turtle turtle, Matrix matrix) {
        if (undoStack.isEmpty()) {
            return false;
        }
        Matrix currentMatrixCopy = copyMatrix(matrix);
        Snapshot currentSnapshot = new Snapshot(turtle.createSnapshot(), currentMatrixCopy);
        redoStack.push(currentSnapshot);
        Snapshot previousSnapshot = undoStack.pop();
        turtle.restoreState(previousSnapshot.getTurtleState());
        restoreMatrix(matrix, previousSnapshot.getMatrix());
        return true;
    }

    /**
     * redoes the last thing undone
     * @param turtle the turtle to restore
     * @param matrix the matrix to restore
     * @return true if redo was successful, false if no commands to redo
     */
    public boolean redo(Turtle turtle, Matrix matrix) {
        if (redoStack.isEmpty()) {
            return false;
        }
        Matrix currentMatrixCopy = copyMatrix(matrix);
        Snapshot currentSnapshot = new Snapshot(turtle.createSnapshot(), currentMatrixCopy);
        undoStack.push(currentSnapshot);
        Snapshot redoSnapshot = redoStack.pop();
        turtle.restoreState(redoSnapshot.getTurtleState());
        restoreMatrix(matrix, redoSnapshot.getMatrix());
        return true;
    }

    /**
     * @param original matrix to copy
     * @return a duplicate matrix as the original
     */
    private Matrix copyMatrix(Matrix original) {
        Matrix copy = new Matrix(original.getWidth(), original.getHeight());
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                copy.setCell(j, i, original.getCell(j, i));
            }
        }
        return copy;
    }

    /**
     * restores target matrix to match the og matrix
     * @param target matrix to be restored
     * @param source matrix to copy from
     */
    private void restoreMatrix(Matrix target, Matrix source) {
        target.clear();
        for (int i = 0; i < source.getHeight(); i++) {
            for (int j = 0; j < source.getWidth(); j++) {
                target.setCell(j, i, source.getCell(j, i));
            }
        }
    }

    private static class Snapshot {
        private final TurtleState turtleState;
        private final Matrix matrix;

        /**
         * creates a new snapshot with the given turtle state + matrix
         * @param turtleState state of turtle
         * @param matrix state of matrix
         */
        public Snapshot(TurtleState turtleState, Matrix matrix) {
            this.turtleState = turtleState;
            this.matrix = matrix;
        }

        /**
         * gets turtle state from the snapshot
         * @return turtle state
         */
        public TurtleState getTurtleState() {
            return turtleState;
        }

        /**
         * gets the matrix
         * @return matrix
         */
        public Matrix getMatrix() {
            return matrix;
        }
    }
}