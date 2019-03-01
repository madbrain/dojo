package com.github.madbrain.dojo.contact;

import javax.swing.*;
import java.awt.*;

public class ContactManager extends JFrame {

    private static final long serialVersionUID = -8934756829362707852L;

    public ContactManager() {
        super("Contact Manager");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new ContactsPanel(), BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        new ContactManager().setVisible(true);
    }
}
