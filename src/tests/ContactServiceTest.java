/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import project_one.Contact;
import project_one.ContactService;

class ContactServiceTest {

private ContactService service;
    // Baseline valid data
    private final String ID = "0";
    private final String FIRST = "Annabella";
    private final String LAST = "Zuniga";
    private final String PHONE = "7716463226";
    private final String ADDRESS = "25494 Main Circle";

    @BeforeEach
    @DisplayName("Initialize fresh ContactService and seed with one contact")
    void setUp() {
        service = new ContactService();
        service.addContact(ID, FIRST, LAST, PHONE, ADDRESS);
    }

    @Nested
    @DisplayName("Add Contact")
    class AddTests {

        @Test
        @DisplayName("addContact(Contact) returns true on new ID and stores contact")
        void addContactObject() {
            Contact c = new Contact("ID002", "Jane", "Smith", "0987654321", "456 Oak Ave");
            assertTrue(service.addContact(c));
            assertEquals(c, service.getContact("ID002"));
        }

        @Test
        @DisplayName("addContact(Contact) returns false for duplicate ID")
        void addDuplicateReturnsFalse() {
            Contact dup = new Contact(ID, "Jake", "Other", "1112223333", "789 Pine Rd");
            assertFalse(service.addContact(dup));
            // Original contact remains
            Contact existing = service.getContact(ID);
            assertEquals(FIRST, existing.getFirstName());
            assertEquals(LAST, existing.getLastName());
        }

        @Test
        @DisplayName("addContact(String,...) adds contact (void) and constructor validation is enforced")
        void addViaFactoryMethod() {
            service.addContact("ID003", "Amy", "Adams", "2223334444", "777 River Dr");
            Contact c = service.getContact("ID003");
            assertNotNull(c);
            assertEquals("Amy", c.getFirstName());

            // Invalid input should bubble up from Contact constructor
            assertThrows(IllegalArgumentException.class, () ->
                service.addContact("TOO_LONG_ID_11", "Bob", "Lee", "5556667777", "12 Short St")
            );
        }
    }

    @Nested
    @DisplayName("Delete Contact")
    class DeleteTests {

        @Test
        @DisplayName("deleteContact returns true when existing ID removed")
        void deleteExisting() {
            assertTrue(service.deleteContact(ID));
            assertNull(service.getContact(ID));
        }

        @Test
        @DisplayName("deleteContact returns false when ID not found or null")
        void deleteMissingOrNull() {
            assertFalse(service.deleteContact("NOPE"));
            assertFalse(service.deleteContact(null));
        }
    }

    @Nested
    @DisplayName("Edit Fields")
    class EditTests {

        @SuppressWarnings("unused")
		@Test
        @DisplayName("editFirstName updates when valid; returns false if not found; throws on invalid")
        void editFirstName() {
            assertTrue(service.editFirstName(ID, "Jane"));
            assertEquals("Jane", service.getContact(ID).getFirstName());

            assertFalse(service.editFirstName("NOPE", "Zed"));

            // Invalid (too long) should throw and leave old value intact
            IllegalArgumentException ex = assertThrows(IllegalArgumentException.class,
                () -> service.editFirstName(ID, "ThisNameIsTooLong"));
            assertEquals("Jane", service.getContact(ID).getFirstName());
        }

        @Test
        @DisplayName("editLastName updates when valid; returns false if not found; throws on invalid")
        void editLastName() {
            assertTrue(service.editLastName(ID, "Smith"));
            assertEquals("Smith", service.getContact(ID).getLastName());

            assertFalse(service.editLastName("NOPE", "Else"));

            assertThrows(IllegalArgumentException.class,
                () -> service.editLastName(ID, "LastNameTooLong"));
            assertEquals("Smith", service.getContact(ID).getLastName());
        }

        @Test
        @DisplayName("editPhoneNumber updates when valid; returns false if not found; throws on invalid")
        void editPhone() {
            assertTrue(service.editPhoneNumber(ID, "0987654321"));
            assertEquals("0987654321", service.getContact(ID).getPhoneNumber());

            assertFalse(service.editPhoneNumber("NOPE", "0123456789"));

            assertThrows(IllegalArgumentException.class,
                () -> service.editPhoneNumber(ID, "123")); // not 10 digits
            assertEquals("0987654321", service.getContact(ID).getPhoneNumber());
        }

        @Test
        @DisplayName("editAddress updates when valid; returns false if not found; throws on invalid")
        void editAddress() {
            assertTrue(service.editAddress(ID, "456 New St"));
            assertEquals("456 New St", service.getContact(ID).getAddress());

            assertFalse(service.editAddress("NOPE", "789 Anywhere"));

            assertThrows(IllegalArgumentException.class,
                () -> service.editAddress(ID, null));
            assertEquals("456 New St", service.getContact(ID).getAddress());
        }
    }

    @Nested
    @DisplayName("Getters & Views")
    class GetterTests {

        @Test
        @DisplayName("getContact returns the stored instance or null")
        void getContactById() {
            Contact found = service.getContact(ID);
            assertNotNull(found);
            assertEquals(FIRST, found.getFirstName());
            assertNull(service.getContact("MISSING"));
        }

        @Test
        @DisplayName("getAllContacts returns unmodifiable map view")
        void unmodifiableAllContacts() {
            Map<String, Contact> view = service.getAllContacts();
            assertTrue(view.containsKey(ID));
            assertThrows(UnsupportedOperationException.class, () -> view.put("X", null));
        }
    }
}
