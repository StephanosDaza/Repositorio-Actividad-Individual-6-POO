import java.util.ArrayList;

public class ContactManager {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    public void addContact(String name, String phone) {
        contacts.add(new Contact(name, phone));
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public boolean updateContact(String name, String newPhone) {
        for (Contact c : contacts) {
            if (c.getName().equalsIgnoreCase(name)) {
                c.setPhone(newPhone);
                return true;
            }
        }
        return false;
    }

    public boolean deleteContact(String name) {
        return contacts.removeIf(c -> c.getName().equalsIgnoreCase(name));
    }
}
