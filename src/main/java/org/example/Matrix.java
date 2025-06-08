package org.example;

public class Matrix {
    private char[][] graph;
    private int width, height;

    public Matrix(int width, int height) {
        this.width = width;
        this.height = height;
        this.graph = new char[height][width];
        clear();
    }

    public void setCell(int x, int y, char val) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            graph[y][x] = val;
        }
    }

    public char getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return graph[y][x];
        }
        return ' ';
    }

    public void clear() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                graph[i][j] = ' ';
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
                sb.append(graph[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}