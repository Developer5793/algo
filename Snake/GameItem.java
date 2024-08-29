abstract class GameItem {
    protected int x;
    protected int y;

    GameItem(int x, int y) {
        this.x = x * SnakeGame.SQUARE_SIZE;
        this.y = y * SnakeGame.SQUARE_SIZE;
    }

    ;
    public int getX() {
        return this.x / SnakeGame.SQUARE_SIZE;
    }

    public int getY() {
        return this.y / SnakeGame.SQUARE_SIZE;
    }

    public abstract void paint(AudGraphics g);

}
