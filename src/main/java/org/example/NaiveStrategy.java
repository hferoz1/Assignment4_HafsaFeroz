package org.example;

public class NaiveStrategy implements DrawingStrategy {
    @Override
    /**
     * draws line between two points using naive algo
     * @param m matrix to draw on
     * @param x0 starting x-coord
     * @param y0 starting y-coord
     * @param x1 ending x-coord
     * @param y1 ending y-coord
     */
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        int ix0 = (int)Math.round(x0);
        int iy0 = (int)Math.round(y0);
        int ix1 = (int)Math.round(x1);
        int iy1 = (int)Math.round(y1);

        // Handle division by zero when x1==x0 (vertical line)
        if (ix1 == ix0) {
            int startY = Math.min(iy0, iy1);
            int endY = Math.max(iy0, iy1);
            for (int y = startY; y <= endY; y++) {
                if (ix0 >= 0 && ix0 < m.getWidth() && y >= 0 && y < m.getHeight()) {
                    m.setCell(ix0, y, '#');
                }
            }
            return;
        }
        double slope = (double)(iy1 - iy0) / (ix1 - ix0);
        double b = iy0 - slope * ix0;
        int startX = Math.min(ix0, ix1);
        int endX = Math.max(ix0, ix1);
        for (int x = startX; x <= endX; x++) {
            int y = (int)Math.round(slope * x + b);
            if (x >= 0 && x < m.getWidth() && y >= 0 && y < m.getHeight()) {
                m.setCell(x, y, '#');
            }
        }
        if (Math.abs(slope) > 1) {
            int startY = Math.min(iy0, iy1);
            int endY = Math.max(iy0, iy1);
            for (int y = startY; y <= endY; y++) {
                int x = (int)Math.round((y - b) / slope);
                if (x >= 0 && x < m.getWidth() && y >= 0 && y < m.getHeight()) {
                    m.setCell(x, y, '#');
                }
            }
        }
    }
}