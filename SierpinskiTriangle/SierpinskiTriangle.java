//Clemens Osbahr - Matrikelnummer:23453430

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SierpinskiTriangle extends SierpinskiTriangleAbstract {

    private static final int MIN_DEPTH = 0;
    private static final int MAX_DEPTH = 7;

    public SierpinskiTriangle() {
        super();
        //Standardfarbe festlegen
        this.color = Color.BLACK;
        //Zufallsfarbe aus togglen
        this.useRandomColor = false;
    }

    @Override
    protected void drawTriangleRec(int ax, int ay, int bx, int by, int cx, int cy, int depth, Color color) {
        //f,d,e berechnen
        int fx = (ax + bx) / 2;
        int fy = (ay + by) / 2;
        int ex = (bx + cx) / 2;
        int ey = (by + cy) / 2;
        int dx = (ax + cx) / 2;
        int dy = (ay + cy) / 2;

        // Abbruchbedingung festlegen
        if (depth == 0 || bx - ax < 2) {
            if (depth == 0) {
                // Dreieck zeichnen
                int[] xPoints = {ax, bx, cx};
                int[] yPoints = {ay, by, cy};
                g.setColor(color);
                g.fillPolygon(xPoints, yPoints, 3);
            }
            return;
        }

        // Zeichenfarbe festlegen
        g.setColor(useRandomColor ? getRandomColor() : color);

        //Dreieck zeichnen
        int[] xPoints = {ax, bx, cx};
        int[] yPoints = {ay, by, cy};
        g.fillPolygon(xPoints, yPoints, 3);


        // Wei?es Dreieck zeichnen
        int[] midXPoints = {fx, ex, dx};
        int[] midYPoints = {fy, ey, dy};
        g.setColor(Color.WHITE);
        g.fillPolygon(midXPoints, midYPoints, 3);

        //Kleine Dreiecke rekursiv zeichnen
        drawTriangleRec(ax, ay, fx, fy, dx, dy, depth - 1, color);
        drawTriangleRec(fx, fy, bx, by, ex, ey, depth - 1, color);
        drawTriangleRec(dx, dy, ex, ey, cx, cy, depth - 1, color);
    }

    //Nutzerinput verarbeiten
    @Override
    protected void handleInput(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
                if (depth < MAX_DEPTH) {
                    depth++;
                }
                break;
            case KeyEvent.VK_DOWN:
                if (depth > MIN_DEPTH) {
                    depth--;
                }
                break;
            case KeyEvent.VK_SPACE:
                toggleRandomColor();
                break;
            default:
                break;
        }
        paint(getGraphics());
    }

    //Zufallsfarbe togglen
    @Override
    protected void toggleRandomColor() {
        useRandomColor = !useRandomColor;
    }

    //Zufallsfarbe festlegen
    private Color getRandomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }

    //Dreiecke zeichnen
    @Override
    protected void drawTriangle() {
        if (useRandomColor) {
            color = getRandomColor();
        } else {
            color = Color.BLACK;
        }
        super.drawTriangle();
    }

    public static void main(String[] args) {
        new SierpinskiTriangle();
    }
}
