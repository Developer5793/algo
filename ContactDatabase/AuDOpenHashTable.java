//Clemens Osbahr // Matrikelnummer: 23453430

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class AuDOpenHashTable extends AuDHashTable {
    private LinkedList<Contact>[] table;

    public AuDOpenHashTable(int capacity) {
        super(capacity);
        //Liste f?r Buckets
        this.table = new LinkedList[capacity];
        //F?r jeden Bucket Liste erstellen
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }

    }
    //Hashen und einfuegen
    @Override
    public void insert(Contact c) {
        int index = hash(c.getEmail());
        table[index].add(c);
    }
    //Hashen und entfernen
    @Override
    public void remove(Contact c) {
        int index = hash(c.getEmail());
        if (!table[index].remove(c)) {
            throw new NoSuchElementException("Contact not found");

        }
    }


    @Override
    public Contact getContact(String email) {
        int index = hash(email);
        //Durch Liste an Index iterieren fuer jeden Eintrag Kontakt erstellen und pruefen ob es mit der Eingabe uebereinstimmt
        for (Contact c : table[index]) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        throw new NoSuchElementException("Contact not found");
    }
}


