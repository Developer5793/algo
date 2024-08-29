class Snake {
    //Snake Color
    AudColor color = AudColor.BLUE;
    int length;
    Point[] points;
    Point point;

    public enum Direction {
        RIGHT,
        LEFT,
        UP,
        DOWN
    }

    Direction nextDirection = Direction.RIGHT;

    //Konstruktor fuer Schlange initialisieren
    Snake(int length, int x, int y) {
        if (length < 1) {
            throw new IllegalArgumentException();
        }
        this.length = length;
        this.points = new Point[this.length];
        this.point = new Point(x, y);
        this.points[0] = this.point;
    }

    Snake(int x, int y) {
        this(5, x, y);
    }

    //Getter
    public Direction getNextDirection() {
        return this.nextDirection;
    }

    // Setter
    public void setNextDirection(Direction Direction) {
        this.nextDirection = Direction;
    }

    // Schlange zeichnen
    public void paint(AudGraphics g) {
        g.setColor(color);
        for (Point value : this.points) {
            if (value != null) {
                g.drawRect(value.getX() * SnakeGame.SQUARE_SIZE, value.getY() * SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);
                g.fillRect(value.getX() * SnakeGame.SQUARE_SIZE, value.getY() * SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE, SnakeGame.SQUARE_SIZE);

            }
        }
    }

    void step() {
        //Wenn ich in Step die lastDirection pruefen soll, dann muss ich das ja hier jeden Schritt machen, waere doch besser dies nur bei Userinput zu machen..
        //Alle Punkte eins nach rechts kopieren
        System.arraycopy(this.points, 0, this.points, 1, this.points.length - 1);


        //Schlange in die entsprechenden Richtungen bewegen
        if (this.nextDirection == Direction.LEFT) {
            Point newFirstPoint = new Point(this.points[0].getX() - 1, this.points[0].getY());
            this.points[0] = newFirstPoint;

        }
        if (this.nextDirection == Direction.RIGHT) {
            Point newFirstPoint = new Point(this.points[0].getX() + 1, this.points[0].getY());
            this.points[0] = newFirstPoint;
        }

        if (this.nextDirection == Direction.UP) {
            Point newFirstPoint = new Point(this.points[0].getX(), this.points[0].getY() + 1);
            this.points[0] = newFirstPoint;
        }

        if (this.nextDirection == Direction.DOWN) {
            Point newFirstPoint = new Point(this.points[0].getX(), this.points[0].getY() - 1);
            this.points[0] = newFirstPoint;
        }

    }

    //Pr?fen ob Schlange die Mauer ber?hrt
    public boolean collidesWith(GameItem item) {
        //Ich habe keinen Zugriff auf GameAreHeight und Width, daher weiss ich nicht wie ich hier die Limits ohne Konstanten festlegen soll

        //Kollision oben checken
        if (points[0].getY() <= item.getY() && item.getY() == 0) {
            return true;
        }
        //Kollision unten checken
        if (points[0].getY() >= item.getY() && item.getY() == 34) {
            return true;
        }
        //Kollision links checken
        if (points[0].getX() <= item.getX() && item.getX() == 0) {
            return true;
        }
        //Kollision rechts checken
        if (points[0].getX() >= item.getX() && item.getX() == 48) {
            return true;
        }
        return false;
    }

    //Pr?fen, ob Schlange sich selbst ber?hrt
    boolean collidesWithSelf() {
        for (int i = 1; i < this.points.length; i++) {
            if (this.points[i] != null) {
                if (points[0].getX() == points[i].getX() && points[0].getY() == points[i].getY()) {
                    return true;
                }
            }
        }
        ;
        return false;
    }

    //F?r Wachstum der Schlange sorgen
    void grow(int growFactor) {
        if (growFactor < 1) {
            throw new IllegalArgumentException();
        } else {
            Point[] tempPoints = new Point[this.points.length + growFactor];
            System.arraycopy(this.points, 0, tempPoints, 0, this.points.length);
            this.points = tempPoints;
        }
    }

};

