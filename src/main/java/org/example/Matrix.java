package org.example;

public class Matrix {
    private char[][] canvas;
    private int width, height;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.canvas = new char[height][width];
        clear();
    }

    public void setCell(int x, int y, char val) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            canvas[y][x] = val;
        }
    }

    public char getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return canvas[y][x];
        }
        return ' ';
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                canvas[i][j] = ' ';
            }
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

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