public class Brick extends GameItem {
    AudColor color = AudColor.GRAY;

    Brick(int x, int y) {
        super(x, y);
    }

    @Override
    public void paint(AudGraphics g) {
        g.setColor(color);
        g.drawRect(x, y, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
        g.fillRect(x, y, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);

    }
}

