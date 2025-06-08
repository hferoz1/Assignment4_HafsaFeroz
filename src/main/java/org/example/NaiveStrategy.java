package org.example;

public class NaiveStrategy implements DrawingStrategy {

    @Override
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        if (x1 == x0) {
            int startY = (int)Math.min(y0, y1);
            int endY = (int)Math.max(y0, y1);
            for (int y = startY; y <= endY; y++) {
                m.setCell((int)x0, y, '#');
            }
            return;
        }
        double slope = (y1 - y0) / (x1 - x0);
        double b = y0 - slope * x0;
        int startX = (int)Math.min(x0, x1);
        int endX = (int)Math.max(x0, x1);
        for (int x = startX; x <= endX; x++) {
            int y = (int)(slope * x + b);
            m.setCell(x, y, '#');
        }
    }
}