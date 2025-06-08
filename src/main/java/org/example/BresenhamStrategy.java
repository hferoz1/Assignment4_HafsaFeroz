package org.example;

public class BresenhamStrategy implements DrawingStrategy {
    @Override
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        double dx = Math.abs(x1-x0);
        int sx = x0 < x1 ? 1 : -1;
        double dy = -Math.abs(y1-y0);
        int sy = y0 < y1 ? 1 : -1;
        double error = dx + dy;

        double x = x0;
        double y = y0;
        while (x != x1 || y != y1) {
            m.setCell((int)x, (int)y, '#');
            double e2 = 2*error;
            if (e2 >= dy) {
                if (x == x1) break;
                error += dy;
                x += sx;
            }
            if (e2 <= dx) {
                if (y == y1) break;
                error += dx;
                y += sy;
            }
        }
    }
}