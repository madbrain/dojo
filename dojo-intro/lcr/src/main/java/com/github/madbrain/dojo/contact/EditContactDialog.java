package com.github.madbrain.dojo.contact;

import javax.swing.*;
import java.awt.*;

public class EditContactDialog extends JDialog {

    private static final long serialVersionUID = -1274791311484131697L;

    private boolean isOk = false;

    private JLabel errorLabel;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField phoneNumberField;


    private JButton okButton;
    private JButton cancelButton;
    private Contact contact;

    public EditContactDialog() {
        super((JFrame) null, "Contact", true);

        errorLabel = new JLabel();
        errorLabel.setAlignmentX(0.0f);
        errorLabel.setForeground(Color.RED);
        getContentPane().add(errorLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        JPanel formPanel = new JPanel(new GridLayout(3, 2));
        formPanel.add(new JLabel("Prénom:"), gc(0, 0));
        formPanel.add(nameField = new JTextField(), gc(0, 1));
        formPanel.add(new JLabel("Nom:"), gc(1, 0));
        formPanel.add(surnameField = new JTextField(), gc(1, 1));
        formPanel.add(new JLabel("Téléphone:"), gc(2, 0));
        formPanel.add(phoneNumberField = new JTextField(), gc(2, 1));
        mainPanel.add(formPanel);

        getContentPane().add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton = new JButton("OK"));
        buttonPanel.add(cancelButton = new JButton("Annuler"));
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);

        okButton.addActionListener(e -> {
            if (validateForm()) {
                isOk = true;
                dispose();
            }
        });

        cancelButton.addActionListener(e -> dispose());

        pack();
    }

    public static GridBagConstraints gc(int row, int col) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = col;
        gbc.gridy = row;
        return gbc;
    }

    public void setError(String message) {
        errorLabel.setText(message);
        pack();
    }

    public boolean openDialog(Contact contact) {
        this.contact = contact;
        nameField.setText(contact.getName());
        surnameField.setText(contact.getSurname());
        phoneNumberField.setText(contact.getPhoneNumber());
        setVisible(true);
        return isOk;
    }

    public boolean validateForm() {
        if (nameField.getText().isEmpty()) {
            setError("Le nom est obligatoire");
            return false;
        }
        if (!isAllNumber(phoneNumberField.getText())) {
            setError("Le numéro de téléphone doit être composé de chiffres");
            return false;
        }
        setError("");
        contact.setName(nameField.getText());
        contact.setSurname(surnameField.getText());
        contact.setPhoneNumber(phoneNumberField.getText());
        return true;
    }

    public static boolean isAllNumber(String text) {
        for (int i = 0; i < text.length(); ++i) {
            if (text.charAt(i) != ' ' && !Character.isDigit(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
