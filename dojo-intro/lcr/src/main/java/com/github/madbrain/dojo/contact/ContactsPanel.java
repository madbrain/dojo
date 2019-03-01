package com.github.madbrain.dojo.contact;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ContactsPanel extends JPanel {

    private static final long serialVersionUID = 4100374461874917272L;

    private final List<Contact> contacts;
    private final EditContactDialog editContactDialog = new EditContactDialog();
    private final JTable table;
    private final JButton addButton;
    private final JButton editButton;
    private final JButton removeButton;
    private final ContactsTableModel tableModel;

    public ContactsPanel() {
        this.contacts = Database.getAllContacts();

        setLayout(new BorderLayout());

        JToolBar toolBar = new JToolBar();
        toolBar.add(addButton = new JButton("Ajouter"));
        toolBar.add(editButton = new JButton("Modifier"));
        toolBar.add(removeButton = new JButton("Supprimer"));
        add(toolBar, BorderLayout.PAGE_START);

        addButton.addActionListener(e -> addContact());
        editButton.addActionListener(e -> editContact());
        removeButton.addActionListener(e -> removeContacts());

        tableModel = new ContactsTableModel(contacts);
        table = new JTable(tableModel);
        table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getSelectionModel().addListSelectionListener(e -> updateSelection());

        add(new JScrollPane(table), BorderLayout.CENTER);

        updateSelection();

    }

    public void addContact() {
        Contact contact = new Contact();
        if (editContactDialog.openDialog(contact)) {
            contacts.add(contact);
            Database.saveOrUpdateContact(contact);
            tableModel.fireTableDataChanged();
        }
    }

    public void editContact() {
        Contact contact = contacts.get(table.getSelectedRow());
        if (editContactDialog.openDialog(contact)) {
            Database.saveOrUpdateContact(contact);
            tableModel.fireTableDataChanged();
        }
    }

    public void removeContacts() {
        Contact contact = contacts.remove(table.getSelectedRow());
        Database.removeContact(contact);
        tableModel.fireTableDataChanged();
    }

    public void updateSelection() {
        editButton.setEnabled(table.getSelectedRow() >= 0);
        removeButton.setEnabled(table.getSelectedRow() >= 0);
    }

}
