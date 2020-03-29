package io.trevor;

import java.util.ArrayList;

public class MobilePhone {
    private ArrayList<Contact> contacts;

    public MobilePhone() {
        this.contacts = new ArrayList<>();
    }
    // Add contacts
    public void addContact(String name, String number) {
        Contact contact = new Contact(name, number);
        contacts.add(contact);
    }
    // Modify/Update
    public void modifyContact(Contact oldContact, Contact newContact) {
        int position = findContact(oldContact);
        if(findContact(oldContact) >= 0) {
            contacts.set(position, newContact);
        } else {
            System.out.println("Could not update");
        }
    }

    public void removeContact(String name) {
        int position = findContact(name);
        if(position >= 0) {
            contacts.remove(position);
        } else {
            System.out.println("Could not remove");
        }
    }

    private int findContact(Contact contact) {
        int position = contacts.indexOf(contact);
        if(position >= 0) {
            return position;
        }
        return -1;
    }

    private int findContact(String name) {
        for(int i = 0; i < contacts.size(); i++) {
            if(contacts.get(i).getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    public Contact queryContact(String name) {
        int position = findContact(name);
        if(position >= 0) {
            Contact contact = contacts.get(position);
            return contact;
        }
        return null;
    }

    public void printContacts() {
        if(contacts.size() == 0) {
            System.out.println("\tYou have zero contacts");
        }
        for(int i = 0; i < contacts.size(); i++) {
            System.out.println("\t"+contacts.get(i).getName() +
                    ": #"+contacts.get(i).getPhoneNumber());
        }
    }

    public boolean onFile(String name) {
        if(findContact(name) >= 0) {
            return true;
        }
        return false;
    }
}
