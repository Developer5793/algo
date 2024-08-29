//Clemens Osbahr - Matrikelnummer:23453430

import java.util.concurrent.ThreadLocalRandom;

public class SnakeGame extends AudGameWindow {
    public static final int SQUARE_SIZE = 16;
    public static final int STEP_TIME = 100;
    public static final int GROW_AMOUNT = 1;
    int score = 0;
    Snake snake;
    Brick[] wall;
    Apple apple;
    long lastSnakeUpdate;
    int width;
    int height;

    public SnakeGame() {
        super();
        setTitle("AuD-Snake Score: " + score);
        //Bildschirmgroe?e herausfinden
        this.width = getGameAreaWidth() / SQUARE_SIZE;
        this.height = getGameAreaHeight() / SQUARE_SIZE;


        //Neues Schlangenobjekt in der Mitte des Bildschirms erstellen
        this.snake = new Snake(5, width / 2, height / 2);
        //Apfelobjekt erstellen
        createNewApple();

        //Wall mit geeigneten Umfang erstellen
        this.wall = new Brick[2 * width + height * 2];
        int n = 0;
        int m = 0;
        int o = 0;
        int p = 0;

        //Obere Wall
        for (int i = 0; i < wall.length; i++) {
            if (i < width) {
                this.wall[i] = new Brick(n, 0);
                n++;
            }
            //Linke Wall
            if (width <= i && i < width + height) {
                this.wall[i] = new Brick(0, m);
                m++;
            }
            //Rechte Wall
            if (width + height <= i && i < width + 2 * height) {
                this.wall[i] = new Brick(width - 1, o);
                o++;
            }
            //untere Wall
            if (width + 2 * height <= i && i < 2 * width + 2 * height) {
                this.wall[i] = new Brick(p, height - 1);
                p++;
            }

        }

        //Timestamp initialisieren
        this.lastSnakeUpdate = System.currentTimeMillis();

    }

    public SnakeGame(int timerInterval) {
        super(timerInterval);
    }

    @Override
    public void start() {
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public int getGameAreaWidth() {
        return super.getGameAreaWidth();
    }

    @Override
    public int getGameAreaHeight() {
        return super.getGameAreaHeight();
    }

    @Override
    public void setGameAreaWidth(int value) {
        super.setGameAreaWidth(value);
    }

    @Override
    public void setGameAreaHeight(int value) {
        super.setGameAreaHeight(value);
    }

    @Override
    public void setRepeatableKeys(int... keyCodes) {
        super.setRepeatableKeys(keyCodes);
    }

    @Override
    public void setShowFps(boolean showFps) {
        super.setShowFps(showFps);
    }

    @Override
    public boolean getShowFps() {
        return super.getShowFps();
    }

    @Override
    public long getStartTime() {
        return super.getStartTime();
    }

    @Override
    protected void showDialog(String text) {
        super.showDialog(text);
    }

    // Spiel aktualisieren
    @Override
    public void updateGame(long time) {
        while (time - this.lastSnakeUpdate > STEP_TIME) {
            snake.step();
            checkCollisions();
            this.lastSnakeUpdate = this.lastSnakeUpdate + STEP_TIME;
        }
    }

    @Override
    public void paintGame(AudGraphics g) {
        //Spielbildschirm zeichnen
        AudColor color = AudColor.WHITE;
        g.setColor(color);
        g.fillRect(0, 0, getGameAreaWidth(), getGameAreaHeight());


        //Mauer zeichnen
        for (int i = 0; i < wall.length; i++) {
            {
                wall[i].paint(g);
            }
        }
        //Schlange zeichnen und bewegen
        this.snake.paint(g);
        apple.paint(g);


    }

    //Jedes Gameitem einzeln ?bergeben und pr?fen ob die Koordinaten mit der Schlange ?bereinstimmen
    public void checkCollisions() {
        for (int i = 0; i < wall.length; i++) {
            boolean wallCollision = snake.collidesWith(wall[i]);
            boolean selfCollision = snake.collidesWithSelf();
            if (wallCollision || selfCollision) {
                stop();
                showDialog("You died! Score: " + score);
                return;
            }
        }
// leider wird der VALUE nicht richtig geupdatet, liegt das vllt an updatezeit? Verstehe es nicht ganz weil augescheinlich wird ja auch nur ein Apfel erzeugt.
        if (snake.points[0].getX() == apple.getX() && snake.points[0].getY() == apple.getY()) {
            snake.grow(GROW_AMOUNT);
            createNewApple();
            score += this.apple.getValue();
            setTitle("AuD-Snake Score: " + score);
        }
    }

    @Override
    public void handleInput(int keyCode) {
        //getter Methode um zu pr?fen wohin sich die Schlange bewegt, damit gegebenenfalls Bewegung in Gegenrichtung ignoriert werden kann
        Snake.Direction lastDirection = snake.getNextDirection();

        if (keyCode == KeyEvent.VK_RIGHT && lastDirection != Snake.Direction.LEFT) {
            snake.setNextDirection(Snake.Direction.RIGHT);

        }
        if (keyCode == KeyEvent.VK_LEFT && lastDirection != Snake.Direction.RIGHT) {
            snake.setNextDirection(Snake.Direction.LEFT);
        }
        //Bei mir geht es nur so rum also hoch dr?cken muss die Schlange runter und umgekehrt
        if (keyCode == KeyEvent.VK_UP && lastDirection != Snake.Direction.UP) {
            snake.setNextDirection(Snake.Direction.DOWN);
        }

        if (keyCode == KeyEvent.VK_DOWN && lastDirection != Snake.Direction.DOWN) {
            snake.setNextDirection(Snake.Direction.UP);
        }
    }

    //Apfel erzeugen
    public void createNewApple() {
        boolean coordinateChecked = false;
        int randomXCoordinate = 0;
        int randomYCoordinate = 0;

        while (!coordinateChecked) {

            randomXCoordinate = ThreadLocalRandom.current().nextInt(2, width - 1);
            randomYCoordinate = ThreadLocalRandom.current().nextInt(2, height - 1);


            for (int i = 0; i < snake.points.length; i++)
                if (snake.points[i] != null) {
                    {
                        if (randomXCoordinate == snake.points[i].getX() && randomYCoordinate == snake.points[i].getY()) {
                            break;
                        }
                    }
                    this.apple = new Apple(randomXCoordinate, randomYCoordinate);
                    coordinateChecked = true;
                }
        }

    }

    public static void main(String[] args) {
        SnakeGame SnakeGame = new SnakeGame();
        SnakeGame.start();

    }
}

