package org.example;

import java.util.Stack;

public class CommandHistory {
    private Stack<Snapshot> undoStack;
    private Stack<Snapshot> redoStack;

    public CommandHistory() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void saveSnapshot(Turtle turtle, Matrix matrix) {
        Matrix matrixCopy = copyMatrix(matrix);
        Snapshot snapshot = new Snapshot(turtle.createSnapshot(), matrixCopy);
        undoStack.push(snapshot);
        redoStack.clear(); // Clear redo stack when new command is executed
    }

    public boolean undo(Turtle turtle, Matrix matrix) {
        if (undoStack.isEmpty()) {
            return false;
        }

        // Save current state to redo stack
        Matrix currentMatrixCopy = copyMatrix(matrix);
        Snapshot currentSnapshot = new Snapshot(turtle.createSnapshot(), currentMatrixCopy);
        redoStack.push(currentSnapshot);

        // Restore previous state
        Snapshot previousSnapshot = undoStack.pop();
        turtle.restoreState(previousSnapshot.getTurtleState());
        restoreMatrix(matrix, previousSnapshot.getMatrix());

        return true;
    }

    public boolean redo(Turtle turtle, Matrix matrix) {
        if (redoStack.isEmpty()) {
            return false;
        }

        // Save current state to undo stack
        Matrix currentMatrixCopy = copyMatrix(matrix);
        Snapshot currentSnapshot = new Snapshot(turtle.createSnapshot(), currentMatrixCopy);
        undoStack.push(currentSnapshot);

        // Restore redo state
        Snapshot redoSnapshot = redoStack.pop();
        turtle.restoreState(redoSnapshot.getTurtleState());
        restoreMatrix(matrix, redoSnapshot.getMatrix());

        return true;
    }

    private Matrix copyMatrix(Matrix original) {
        Matrix copy = new Matrix(original.getWidth(), original.getHeight());
        for (int i = 0; i < original.getHeight(); i++) {
            for (int j = 0; j < original.getWidth(); j++) {
                copy.setCell(j, i, original.getCell(j, i));
            }
        }
        return copy;
    }

    private void restoreMatrix(Matrix target, Matrix source) {
        target.clear();
        for (int i = 0; i < source.getHeight(); i++) {
            for (int j = 0; j < source.getWidth(); j++) {
                target.setCell(j, i, source.getCell(j, i));
            }
        }
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoStack.isEmpty();
    }

    private static class Snapshot {
        private final TurtleState turtleState;
        private final Matrix matrix;

        public Snapshot(TurtleState turtleState, Matrix matrix) {
            this.turtleState = turtleState;
            this.matrix = matrix;
        }

        public TurtleState getTurtleState() {
            return turtleState;
        }

        public Matrix getMatrix() {
            return matrix;
        }
    }
}