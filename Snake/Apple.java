public class Apple extends GameItem {
    AudColor color = AudColor.RED;
    private final int VALUE;
    static int appleCount = 0;

    Apple(int x, int y) {
        super(x, y);
        appleCount++;
        VALUE = appleCount;
    }

    @Override
    public void paint(AudGraphics g) {
        g.setColor(color);
        g.drawOval(x, y, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
        g.fillOval(x, y, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
    }

    public int getValue() {
        return VALUE;
    }
}
