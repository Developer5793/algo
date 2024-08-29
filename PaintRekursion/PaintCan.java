//Clemens Osbahr - Matrikelnummer:23453430
public class PaintCan extends Paint {

    public static void main(String[] args) {
        PaintCan p = new PaintCan();
    }

    public void fillBob(int x, int y) {
        while (!isFilled(x, y - 1)) {
            y = y - 1;
        }

        while (!isFilled(x, y)) {
            int x2 = x;
            while (!isFilled(x2, y)) {
                fillPixel(x2, y);
                x2++;
            }
            x2 = x - 1;

            while (!isFilled(x2, y)) {
                fillPixel(x2, y);
                x2--;
            }
            y++;
        }

        /*Warum funktioniert dieser Code nicht? (2 Punkte)
        2. Zum einen hoert der Algorithmus sobald er links oder rechts bei einem schwarzen Pixel angekommen ist auf,
        ohne zu pruefen ob die Flaeche dahinter in irgendeiner Weise mit der Flaeche davor verbunden ist. d.h.
        es wird zeilenweise ohne Pruefung der Verbindungen gefuellt.
        Der Algorithmus  bewegt sich nur auf der entsprechende Klickhoehe des Nutzers und geht dort bis zum naechsten
     oberen und unteren schwarzen Pixel, was bedeutet, dass die entsprechenden wei?en Fl?chen, die sich ueber der Klickhoehe des
     Nutzers befinden nicht beruecksichtigt werden .


     3. Man kann ein Pixel fuellen und dann jeweils die Methode fillRec rekursiv mit den Koordinaten um das gefuellte Pixel herum so lange aufrufen,
     bis alle Pixel in seinem Umkreis gefuellt sind.
        */
    }


    public void fillRec(int x, int y) {
        if (isFilled(x, y)) {
            return;
        }

        //Pixel f?llen
        fillPixel(x, y);

        //Pixel in der Pixelumgebung rekursiv fuellen
        fillRec(x + 1, y);
        fillRec(x - 1, y);
        fillRec(x, y + 1);
        fillRec(x, y - 1);
    }
}