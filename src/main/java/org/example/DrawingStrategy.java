package org.example;

public interface DrawingStrategy {
    void drawLine(Matrix m, double x0, double y0, double x1, double y1);
}