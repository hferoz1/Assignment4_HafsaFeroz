package org.example;

public class NaiveStrategy implements DrawingStrategy {

    @Override
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        int ix0 = Math.max(0, Math.min(m.getWidth() - 1, (int)Math.round(x0)));
        int iy0 = Math.max(0, Math.min(m.getHeight() - 1, (int)Math.round(y0)));
        int ix1 = Math.max(0, Math.min(m.getWidth() - 1, (int)Math.round(x1)));
        int iy1 = Math.max(0, Math.min(m.getHeight() - 1, (int)Math.round(y1)));
        if (ix1 == ix0) {
            int startY = Math.min(iy0, iy1);
            int endY = Math.max(iy0, iy1);
            for (int y = startY; y <= endY; y++) {
                m.setCell(ix0, y, '#');
            }
            return;
        }
        double slope = (double)(iy1 - iy0) / (ix1 - ix0);
        double b = iy0 - slope * ix0;
        int startX = Math.min(ix0, ix1);
        int endX = Math.max(ix0, ix1);
        for (int x = startX; x <= endX; x++) {
            int y = (int)Math.round(slope * x + b);
            if (y >= 0 && y < m.getHeight()) {
                m.setCell(x, y, '#');
            }
        }
    }
}