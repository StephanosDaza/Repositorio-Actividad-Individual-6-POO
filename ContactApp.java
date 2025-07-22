import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ContactApp extends JFrame {
    private ContactManager manager;
    private JTextField nameField;
    private JTextField phoneField;
    private JTextArea displayArea;

    public ContactApp() {
        manager = new ContactManager();
        setTitle("Gestor de Contactos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLayout(new FlowLayout());

        nameField = new JTextField(15);
        phoneField = new JTextField(15);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        JButton addButton = new JButton("Agregar");
        JButton showButton = new JButton("Mostrar");
        JButton updateButton = new JButton("Actualizar");
        JButton deleteButton = new JButton("Eliminar");

        add(new JLabel("Nombre:"));
        add(nameField);
        add(new JLabel("Teléfono:"));
        add(phoneField);
        add(addButton);
        add(showButton);
        add(updateButton);
        add(deleteButton);
        add(new JScrollPane(displayArea));

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                if (!name.isEmpty() && !phone.isEmpty()) {
                    manager.addContact(name, phone);
                    displayArea.setText("Contacto agregado.");
                } else {
                    displayArea.setText("Campos vacíos.");
                }
            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder();
                for (Contact c : manager.getContacts()) {
                    sb.append("Nombre: ").append(c.getName())
                      .append(" - Teléfono: ").append(c.getPhone()).append("\n");
                }
                displayArea.setText(sb.toString());
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String newPhone = phoneField.getText();
                if (manager.updateContact(name, newPhone)) {
                    displayArea.setText("Contacto actualizado.");
                } else {
                    displayArea.setText("Contacto no encontrado.");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                if (manager.deleteContact(name)) {
                    displayArea.setText("Contacto eliminado.");
                } else {
                    displayArea.setText("Contacto no encontrado.");
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new ContactApp();
    }
}
