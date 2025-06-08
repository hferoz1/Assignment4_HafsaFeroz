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
        // Create a deep copy of the matrix
        Matrix matrixCopy = new Matrix(matrix.getWidth(), matrix.getHeight());
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                matrixCopy.setCell(j, i, matrix.getCell(j, i));
            }
        }
        Snapshot snapshot = new Snapshot(turtle.createSnapshot(), matrixCopy);
        undoStack.push(snapshot);
        redoStack.clear();
    }

    public boolean undo(Turtle turtle, Matrix matrix) {
        if (undoStack.isEmpty()) {
            return false;
        }
        Matrix matrixCopy = new Matrix(matrix.getWidth(), matrix.getHeight());
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                matrixCopy.setCell(j, i, matrix.getCell(j, i));
            }
        }
        Snapshot currentSnapshot = new Snapshot(turtle.createSnapshot(), matrixCopy);
        redoStack.push(currentSnapshot);
        Snapshot previousSnapshot = undoStack.pop();
        turtle.restoreState(previousSnapshot.getTurtleState());
        matrix.clear();
        Matrix previousMatrix = previousSnapshot.getMatrix();
        for (int i = 0; i < previousMatrix.getHeight(); i++) {
            for (int j = 0; j < previousMatrix.getWidth(); j++) {
                matrix.setCell(j, i, previousMatrix.getCell(j, i));
            }
        }

        return true;
    }

    public boolean redo(Turtle turtle, Matrix matrix) {
        if (redoStack.isEmpty()) {
            return false;
        }
        Matrix matrixCopy = new Matrix(matrix.getWidth(), matrix.getHeight());
        for (int i = 0; i < matrix.getHeight(); i++) {
            for (int j = 0; j < matrix.getWidth(); j++) {
                matrixCopy.setCell(j, i, matrix.getCell(j, i));
            }
        }
        Snapshot currentSnapshot = new Snapshot(turtle.createSnapshot(), matrixCopy);
        undoStack.push(currentSnapshot);
        Snapshot redoSnapshot = redoStack.pop();
        turtle.restoreState(redoSnapshot.getTurtleState());
        matrix.clear();
        Matrix redoMatrix = redoSnapshot.getMatrix();
        for (int i = 0; i < redoMatrix.getHeight(); i++) {
            for (int j = 0; j < redoMatrix.getWidth(); j++) {
                matrix.setCell(j, i, redoMatrix.getCell(j, i));
            }
        }
        return true;
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