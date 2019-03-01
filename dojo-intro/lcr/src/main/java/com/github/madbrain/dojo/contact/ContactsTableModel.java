package com.github.madbrain.dojo.contact;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ContactsTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 8827295225422992167L;

    private List<Contact> contacts;

    public ContactsTableModel(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public int getRowCount() {
        return contacts.size();
    }

    public int getColumnCount() {
        return 3;
    }

    public String getColumnName(int columnIndex) {
        if (columnIndex == 0) {
            return "Nom";
        } else if (columnIndex == 1) {
            return "Prénom";
        } else {
            return "Téléphone";
        }
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Contact contact = contacts.get(rowIndex);
        if (columnIndex == 0) {
            return contact.getName();
        }
        if (columnIndex == 1) {
            return contact.getSurname();
        }
        return contact.getPhoneNumber();
    }

}
