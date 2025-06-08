package org.example;

public class NaiveStrategy implements DrawingStrategy {
    @Override
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        int ix0 = (int)Math.round(x0);
        int iy0 = (int)Math.round(y0);
        int ix1 = (int)Math.round(x1);
        int iy1 = (int)Math.round(y1);

        // Bounds checking
        ix0 = Math.max(0, Math.min(m.getWidth() - 1, ix0));
        iy0 = Math.max(0, Math.min(m.getHeight() - 1, iy0));
        ix1 = Math.max(0, Math.min(m.getWidth() - 1, ix1));
        iy1 = Math.max(0, Math.min(m.getHeight() - 1, iy1));

        // Handle vertical line case (avoid division by zero)
        if (ix1 == ix0) {
            int startY = Math.min(iy0, iy1);
            int endY = Math.max(iy0, iy1);
            for (int y = startY; y <= endY; y++) {
                if (y >= 0 && y < m.getHeight()) {
                    m.setCell(ix0, y, '#');
                }
            }
            return;
        }

        // Calculate slope and y-intercept
        double slope = (double)(iy1 - iy0) / (ix1 - ix0);
        double b = iy0 - slope * ix0;

        // Draw line by iterating over x coordinates
        int startX = Math.min(ix0, ix1);
        int endX = Math.max(ix0, ix1);
        for (int x = startX; x <= endX; x++) {
            int y = (int)Math.round(slope * x + b);
            if (x >= 0 && x < m.getWidth() && y >= 0 && y < m.getHeight()) {
                m.setCell(x, y, '#');
            }
        }

        // For steep lines, also iterate over y coordinates
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