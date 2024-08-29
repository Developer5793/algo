//Clemens Osbahr // Matrikelnummer: 23453430
public abstract class AuDHashTable {

    protected int capacity;

    public AuDHashTable(int capacity) {
        this.capacity = capacity;
    }

    abstract public void insert(Contact c);

    abstract public void remove(Contact c);

    abstract public Contact getContact(String email);

    // Implementierung von Hashfunktion, die Chars aufaddiert und in den entsprechenden Bucket sortiert
    protected int hash(String s) {
        int hashValue = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            hashValue += s.charAt(i);
        }

        return hashValue % capacity;
    }
}
