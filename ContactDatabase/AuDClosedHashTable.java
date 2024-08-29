//Clemens Osbahr // Matrikelnummer: 23453430

import java.util.NoSuchElementException;

public class AuDClosedHashTable extends AuDHashTable {
    private Contact[] table;
    private boolean[] deleted;
    private int counter;

    public AuDClosedHashTable(int capacity) {
        super(capacity);
        //Eindimensionale Hash-Tabelle und boolean Array
        table = new Contact[capacity];
        deleted = new boolean[capacity];
        counter = 0;
    }
    public boolean isFull() {
        return counter == capacity;
    }
    //Hashfunktion mit linearer Sondierung ?berladen
    private int hash(String s, int i) {
        int baseHash = hash(s);
        if (i % 2 == 0) {
            return Math.floorMod(baseHash - i / 2 - 1, capacity);
        } else {
            return Math.floorMod(baseHash + i / 2, capacity);
        }
    }

    @Override
    public void insert(Contact c) {
        if (isFull()) {
            throw new UnsupportedOperationException("HashTable is full");
        }
        int i = 0;
        int index;

        //Einfuegen
        while (true) {
            index = hash(c.getEmail(), i);
            if (table[index] == null || deleted[index]) {
                table[index] = c;
                deleted[index] = false;
                counter++;
                break;
            }
            //Sondierungsparameter hochzaehlen
            i++;
        }
    }


    private int getIndexOf(String email) {
        int i = 0;
        int index;
        while (i < capacity) {
            index = hash(email, i);
            // Kein Kontakt mit der gesuchten Email-Adresse gefunden..// "Out of Bounds"
            if (table[index] == null && !deleted[index]) {
                break;
            }
            //Element gefunden
            if (table[index] != null && table[index].getEmail().equals(email)) {
                return index;
            }
            i++;
        }
        throw new NoSuchElementException("Contact not found");
    }
    @Override
    public void remove(Contact c) {
        int index = getIndexOf(c.getEmail());
        table[index] = null;
        deleted[index] = true;
        counter--;
    }

    @Override
    public Contact getContact(String email) {
        int index = getIndexOf(email);
        return table[index];    }
}
