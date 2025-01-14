package webeng.contactlist.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import webeng.contactlist.SampleContactsAdder;
import webeng.contactlist.model.ContactListEntry;

import java.io.IOException;
import java.util.Set;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.IntStream.rangeClosed;
import static org.junit.jupiter.api.Assertions.*;
//
//class ContactServiceTest {
//
//    ContactService service;
//
//    ContactServiceTest() throws IOException {
//        var mapper = new ObjectMapper().configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
//        service = new ContactService();
//        new SampleContactsAdder(mapper, service).addSampleContacts();
//    }
//
//    @Test
//    void contactListIds() {
//        var contactList = service.getContactList(null);
//        assertNotNull(contactList);
//        var ids = contactList.stream()
//                .mapToInt(ContactListEntry::getId)
//                .toArray();
//        assertArrayEquals(rangeClosed(1, 30).toArray(), ids);
//    }
//
//    @Test
//    void contactListName() {
//        var contactList = service.getContactList(null);
//        assertNotNull(contactList);
//        assertFalse(contactList.isEmpty());
//        assertEquals("Mabel Guppy", contactList.get(0).getName());
//    }
//
//    @Test
//    void search() {
//        var results = service.getContactList("Engineer");
//        assertEquals(Set.of("Graeme Impett", "Chilton Treversh"),
//                results.stream().map(ContactListEntry::getName).collect(toSet()));
//    }
//
//    @Test
//    void searchIgnoresCase() {
//        var results = service.getContactList("mO");
//        assertEquals(Set.of("Moll Mullarkey", "Seana Burberye"), // one in name, one in email
//                results.stream().map(ContactListEntry::getName).collect(toSet()));
//    }
//}
//
