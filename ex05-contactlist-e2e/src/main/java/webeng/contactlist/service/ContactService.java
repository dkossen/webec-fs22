package webeng.contactlist.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import webeng.contactlist.model.Contact;
import webeng.contactlist.model.ContactListEntry;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class ContactService {

    private static final String JSON_FILE = "contacts.json";

    private final Map<Integer, Contact> contacts;

    public ContactService(ObjectMapper mapper) throws IOException {
        var contactsList = mapper.readValue(ContactService.class.getResource(JSON_FILE),
                new TypeReference<List<Contact>>() {});
        contacts = contactsList.stream()
                .collect(toMap(Contact::getId, identity()));
    }

    public List<ContactListEntry> getContactList() {
        return contacts.values().stream()
                .sorted(comparing(Contact::getId))
                .map(c -> new ContactListEntry(c.getId(), c.getFirstName() + " " + c.getLastName()))
                .collect(toList());
    }

    public Optional<Contact> findContact(int id) {
        return Optional.ofNullable(contacts.get(id));
    }

    public int phoneNumberCount() {
        return contacts.values().stream()
                .mapToInt(c -> c.getPhone().size())
                .sum();
    }

    public int emailCount() {
        return contacts.values().stream()
                .mapToInt(c -> c.getEmail().size())
                .sum();
    }

    public List<Contact> searchContacts(String searchText) {
        searchText = searchText.toLowerCase();
        var result = new ArrayList<Contact>();
        for (var contact : contacts.values()) {
            if (matches(searchText, contact)) {
                result.add(contact);
            }
        }
        return result;
    }

    public boolean matches(String searchText, Contact contact) {
        searchText = searchText.toLowerCase();
        return (contact.getFirstName().contains(searchText) ||
            contact.getLastName().contains(searchText) ||
            contact.getCompany().contains(searchText) ||
            contact.getJobTitle().contains(searchText) ||
            listContains(contact.getEmail(), searchText) ||
            listContains(contact.getPhone(), searchText));
    }

    private  boolean listContains(List<String> list, String searchText) {
        for (var element : list) {
            if (element.toLowerCase().contains(searchText)) {
                return true;
            }
        }
        return false;
    }

}
