package org.example;

public class Matrix {
    private char[][] canvas;
    private int width, height;

    /**
     * constructs new matrix with specified dimensions
     * @param width width of matrix
     * @param height height of matrix
     */
    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.canvas = new char[height][width];
        clear();
    }

    /**
     * sets char at coords
     * @param x x-coord
     * @param y y-coord
     * @param val char to set
     */
    public void setCell(int x, int y, char val) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            canvas[y][x] = val;
        }
    }

    /**
     * gets char at coords
     * @param x x-coord
     * @param y y-coord
     * @return char at position
     */
    public char getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return canvas[y][x];
        }
        return ' ';
    }

    /**
     * clears matrix
     */
    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = ' ';
            }
        }
    }

    /**
     * gets width of matrix
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * gets height of matrix
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * converts matrix to string rep
     * @return string rep of matrix
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(canvas[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}