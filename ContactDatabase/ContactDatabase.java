//Clemens Osbahr // Matrikelnummer: 23453430

import java.util.NoSuchElementException;

public class ContactDatabase {
    public static void main(String[] args) {
        // Test fuer offenes Hashing
        AuDOpenHashTable openHashTable = new AuDOpenHashTable(10);
        Contact contact1 = new Contact("max@mustermann.com");
        contact1.setName("Max Mustermann");
        openHashTable.insert(contact1);

        Contact contact2 = new Contact("maxine@musterfrau.com");
        contact2.setName("Maxine Musterfrau");
        contact2.setTelephone("+1111111111");
        openHashTable.insert(contact2);



        System.out.println(openHashTable.getContact("max@mustermann.com"));
        System.out.println(openHashTable.getContact("maxine@musterfrau.com"));

        openHashTable.remove(contact2);

        try {
            System.out.println(openHashTable.getContact("maxine@musterfrau.com"));
        } catch (NoSuchElementException e) {
            System.out.println("Contact not found");
        }

        // Test fuer geschlossenes Hashing
        AuDClosedHashTable closedHashTable = new AuDClosedHashTable(10);
        closedHashTable.insert(contact1);
        closedHashTable.insert(contact2);

        System.out.println("\nClosed Hash Table:");
        System.out.println(closedHashTable.getContact("maxine@musterfrau.com"));
        System.out.println(closedHashTable.getContact("max@mustermann.com"));

        closedHashTable.remove(contact1);

        try {
            System.out.println(closedHashTable.getContact("max@mustermann.com"));
        } catch (NoSuchElementException e) {
            System.out.println("Contact not found");
        }
    }
}