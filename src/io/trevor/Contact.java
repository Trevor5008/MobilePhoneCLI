package io.trevor;

public class Contact {
    private String name;
    private String phoneNumber;

    // Constructor
    public Contact(String name, String number) {
        this.name = name;
        this.phoneNumber = number;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    // Setter
    public static Contact addNewContact(String name, String number) {
        Contact contact = new Contact(name, number);
        return contact;
    }
}
