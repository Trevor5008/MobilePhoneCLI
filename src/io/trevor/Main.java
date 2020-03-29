package io.trevor;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone phone = new MobilePhone();

    public static void main(String[] args) {
        boolean quit = false;
        int choice;
        startPhone();
        printInstructions();

        while(!quit) {
            System.out.print("Enter a menu option ('6' to display options): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    phone.printContacts();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    modifyContact();
                    break;
                case 4:
                    removeContact();
                    break;
                case 5:
                    findContact();
                    break;
                case 6:
                    printInstructions();
                    break;
            }
        }
    }

    private static void addContact() {
        System.out.print("Enter the contact name: ");
        String name = scanner.nextLine();
        if(!phone.onFile(name)) {
            System.out.print("Enter the contact's number: ");
            String number = scanner.nextLine();
            phone.addContact(name, number);
            System.out.println("\t"+name+", is now in your contacts\n");
        } else {
            System.out.println("\t"+name+", is already a contact\n");
        }
    }

    private static void modifyContact() {
        System.out.print("Enter the name of the current contact: ");
        String oldName = scanner.nextLine();
        if(phone.onFile(oldName)) {
            Contact oldContact = phone.queryContact(oldName);
            System.out.print("Enter the name of the new contact: ");
            String newName = scanner.nextLine();
            System.out.print("Enter the new contact number: ");
            String number = scanner.nextLine();
            Contact newContact = Contact.addNewContact(newName, number);
            phone.modifyContact(oldContact, newContact);
            System.out.println("\t"+oldName+", has been replaced with: "+newName+"\n");
        } else {
            System.out.println("\t"+oldName+", is not one of your contacts\n");
            return;
        }
    }
    private static void findContact() {
        System.out.print("Enter the name of the contact: ");
        String name = scanner.nextLine();
        if(phone.onFile(name)) {
            String number = phone.queryContact(name).getPhoneNumber();
            System.out.println("Found '"+name+"': #"+number);
        } else {
            System.out.println(name+ " is not one of your current contacts\n");
        }
    }
    private static void removeContact() {
        System.out.print("Enter the name of the contact to remove: ");
        String name = scanner.nextLine();
        if(phone.onFile(name)) {
            phone.removeContact(name);
            System.out.println(name+", has been removed from your contacts\n");
        } else {
            System.out.println(name + ", is not one of your contacts\n");
        }
    }

    private static void startPhone() {
        System.out.println("Turning on...");
    }

    private static void printInstructions() {
        System.out.println("\nAvailable Options:");
        System.out.println("\t 0 - Power off");
        System.out.println("\t 1 - Add a new contact");
        System.out.println("\t 2 - Add a new contact");
        System.out.println("\t 3 - Modify an existing contact");
        System.out.println("\t 4 - Remove an existing contact");
        System.out.println("\t 5 - Search for a contact");
        System.out.println("\t 6 - Display menu options\n");
    }
}
