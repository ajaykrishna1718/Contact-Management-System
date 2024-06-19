import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ContactManager {
    private ArrayList<Contact> contacts;

    public ContactManager() {
        contacts = new ArrayList<>();
    }

    // Add a new contact
    public void addContact(Contact contact) {
        contacts.add(contact);
        // Sort the contacts every time a new contact is added
        mergeSort(contacts, 0, contacts.size() - 1);
    }

    // Remove a contact by name
    public boolean removeContact(String name) {
        int index = binarySearch(contacts, name);
        if (index >= 0) {
            contacts.remove(index);
            return true;
        }
        return false;
    }

    // Search for a contact by name
    public Contact searchContact(String name) {
        int index = binarySearch(contacts, name);
        if (index >= 0) {
            return contacts.get(index);
        }
        return null;
    }

    // Display all contacts
    public void displayContacts() {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    // Merge sort algorithm to sort contacts
    private void mergeSort(ArrayList<Contact> list, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(list, left, mid);
            mergeSort(list, mid + 1, right);
            merge(list, left, mid, right);
        }
    }

    private void merge(ArrayList<Contact> list, int left, int mid, int right) {
        ArrayList<Contact> leftList = new ArrayList<>(list.subList(left, mid + 1));
        ArrayList<Contact> rightList = new ArrayList<>(list.subList(mid + 1, right + 1));

        int i = 0, j = 0, k = left;

        while (i < leftList.size() && j < rightList.size()) {
            if (leftList.get(i).getName().compareTo(rightList.get(j).getName()) <= 0) {
                list.set(k++, leftList.get(i++));
            } else {
                list.set(k++, rightList.get(j++));
            }
        }

        while (i < leftList.size()) {
            list.set(k++, leftList.get(i++));
        }

        while (j < rightList.size()) {
            list.set(k++, rightList.get(j++));
        }
    }

    // Binary search algorithm to search contacts by name
    private int binarySearch(ArrayList<Contact> list, String name) {
        int left = 0;
        int right = list.size() - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int compareResult = list.get(mid).getName().compareTo(name);

            if (compareResult == 0) {
                return mid;
            } else if (compareResult < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1; // Not found
    }
}