package org.example;

public class BresenhamStrategy implements DrawingStrategy {
    @Override
    public void drawLine(Matrix m, double x0, double y0, double x1, double y1) {
        int ix0 = (int)Math.round(x0);
        int iy0 = (int)Math.round(y0);
        int ix1 = (int)Math.round(x1);
        int iy1 = (int)Math.round(y1);

        int dx = Math.abs(ix1 - ix0);
        int sx = ix0 < ix1 ? 1 : -1;
        int dy = -Math.abs(iy1 - iy0);
        int sy = iy0 < iy1 ? 1 : -1;
        int error = dx + dy;

        int x = ix0;
        int y = iy0;

        while (true) {
            if (x >= 0 && x < m.getWidth() && y >= 0 && y < m.getHeight()) {
                m.setCell(x, y, '#');
            }

            if (x == ix1 && y == iy1) break;

            int e2 = 2 * error;
            if (e2 >= dy) {
                if (x == ix1) break;
                error += dy;
                x += sx;
            }
            if (e2 <= dx) {
                if (y == iy1) break;
                error += dx;
                y += sy;
            }
        }
    }
}