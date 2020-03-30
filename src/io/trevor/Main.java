package io.trevor;

import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone phone = new MobilePhone();

    public static void main(String[] args) {
        boolean quit = false;
        startPhone();
        printInstructions();

        while(!quit) {
            System.out.print("Enter a menu option ('6' to display options): ");
            int choice = scanner.nextInt();
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

    /**
     * Following method designed to locate existing contact from user input,
     *  then allow user to decide whether they want to update the existing contact's
     *  phone number (if new name matches existing)...if new number matches existing number
     *  user will be notified of duplicate data...while loop designed to execute until user
     *  selects either 'y' or 'n'
     */
    //TODO: Solution for existing phone number check across all contact records...
    private static void modifyContact() {
        System.out.print("Enter the name of the current contact: ");
        String oldName = scanner.nextLine();
        if(phone.onFile(oldName)) {
            Contact oldContact = phone.queryContact(oldName);
            System.out.print("Enter the name of the new contact: ");
            String newName = scanner.nextLine();
            if(phone.onFile(newName)) {
                System.out.println(newName + ", is already a contact");
                boolean proceed = true;
                while (proceed) {
                    System.out.print("Update existing phone number(y/n)? ");
                    String choice = scanner.nextLine();
                    //Allow user to decide to update existing contact's number
                    if (choice.equalsIgnoreCase("y")) {
                        System.out.print("Enter the new phone number: ");
                        String newNumber = scanner.nextLine();
                        //Check whether new phone number matches existing
                        if (oldContact.getPhoneNumber().equalsIgnoreCase(newNumber)) {
                            System.out.println("Duplicate information");
                        } else {
                            Contact newContact = Contact.addNewContact(newName, newNumber);
                            phone.modifyContact(oldContact, newContact);
                            System.out.println(newName + "'s number has been updated to: "
                                    + newContact.getPhoneNumber());
                            proceed = false;
                        }
                    } else if (choice.equalsIgnoreCase("n")) {
                        proceed = false;
                    } else {
                        System.out.println("Please enter a valid response: ");
                    }
                }
            }
            // As long as new name does not match existing, following code will execute
            else {
                System.out.print("Enter the new contact number: ");
                String number = scanner.nextLine();
                Contact newContact = Contact.addNewContact(newName, number);
                phone.modifyContact(oldContact, newContact);
                System.out.println("\t" + oldName + ", has been replaced with: " + newName + "\n");
            }
        }
        // If existing name does not match existing contact, following will execute..
        else {
            System.out.println("\t"+oldName+", is not one of your contacts\n");
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
