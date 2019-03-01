package com.github.madbrain.dojo.contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private static final File dataRoot = new File("database");

    private static int contactId = 1;

    static {
        File cocktailsDir = new File(dataRoot, "contacts");
        if (cocktailsDir.exists()) {
            for (File file : cocktailsDir.listFiles()) {
                contactId = Math.max(contactId, Integer.parseInt(file.getName()));
            }
        }
        ++contactId;
    }


    public static List<Contact> getAllContacts() {
        File cocktailsDir = new File(dataRoot, "contacts");
        List<Contact> result = new ArrayList<Contact>();
        try {
            if (cocktailsDir.exists()) {
                for (File file : cocktailsDir.listFiles()) {
                    result.add(readContact(file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
        return result;
    }

    public static void saveOrUpdateContact(Contact contact) {
        if (contact.getId() == 0) {
            contact.setId(contactId++);
        }
        File ingredientFile = new File(dataRoot, "contacts/" + String.valueOf(contact.getId()));
        ingredientFile.getParentFile().mkdirs();
        try {
            writeContact(contact, ingredientFile);
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    public static Contact readContact(File file) throws IOException {
        DataInputStream stream = new DataInputStream(new FileInputStream(file));
        Contact contact = new Contact();
        contact.setId(stream.readInt());
        contact.setName(stream.readUTF());
        contact.setSurname(stream.readUTF());
        contact.setPhoneNumber(stream.readUTF());
        stream.close();
        return contact;
    }

    public static void writeContact(Contact contact, File file) throws IOException {
        DataOutputStream stream = new DataOutputStream(new FileOutputStream(file));
        stream.writeInt(contact.getId());
        stream.writeUTF(contact.getName());
        stream.writeUTF(contact.getSurname());
        stream.writeUTF(contact.getPhoneNumber());
        stream.close();
    }

    public static void removeContact(Contact contact) {
        if (contact.getId() > 0) {
            File ingredientFile = new File(dataRoot, "contacts/" + String.valueOf(contact.getId()));
            if (ingredientFile.exists()) {
                ingredientFile.delete();
            }
        }
    }

}
