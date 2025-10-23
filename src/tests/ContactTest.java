/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project_one.Contact;

class ContactTest {

    // helper to build strings of a certain length
    private static String s(int n) {
        return "x".repeat(n);
    }

    @Test
    @DisplayName("Valid constructor populates all fields; getters return values")
    void constructorValidAndGetters() {
        Contact c = new Contact("ID123", "John", "Doe", "1234567890", "123 Main St");
        assertEquals("ID123", c.getContactID());
        assertEquals("John", c.getFirstName());
        assertEquals("Doe", c.getLastName());
        assertEquals("1234567890", c.getPhoneNumber());
        assertEquals("123 Main St", c.getAddress());
    }

    @Test
    @DisplayName("Boundary: exactly 10 chars for ID/first/last/phone and 30 for address are valid")
    void constructorBoundaryValid() {
        String ten = s(10);
        String thirty = s(30);
        Contact c = new Contact(ten, ten, ten, "1234567890", thirty);
        assertEquals(ten, c.getContactID());
        assertEquals(ten, c.getFirstName());
        assertEquals(ten, c.getLastName());
        assertEquals("1234567890", c.getPhoneNumber());
        assertEquals(thirty, c.getAddress());
    }

    @Test
    @DisplayName("Current behavior: empty ID, first/last name, and address are accepted")
    void constructorAllowsEmptyStringsWhereNotExplicitlyForbidden() {
        // validateID("") returns true; validateFirst/Last("") true; validateAddress("") true
        Contact c = new Contact("", "", "", "0123456789", "");
        assertEquals("", c.getContactID());
        assertEquals("", c.getFirstName());
        assertEquals("", c.getLastName());
        assertEquals("0123456789", c.getPhoneNumber());
        assertEquals("", c.getAddress());
    }

    @Test
    @DisplayName("Constructor rejects: null or >10 ID")
    void constructorRejectsBadId() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(null, "John", "Doe", "1234567890", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact(s(11), "John", "Doe", "1234567890", "123 Main St"));
    }

    @Test
    @DisplayName("Constructor rejects: null or >10 first name")
    void constructorRejectsBadFirstName() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", null, "Doe", "1234567890", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", s(11), "Doe", "1234567890", "123 Main St"));
    }

    @Test
    @DisplayName("Constructor rejects: null or >10 last name")
    void constructorRejectsBadLastName() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", null, "1234567890", "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", s(11), "1234567890", "123 Main St"));
    }

    @Test
    @DisplayName("Constructor rejects: phone null, too short, too long (must be exactly 10)")
    void constructorRejectsBadPhone() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", null, "123 Main St"));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "12345", "123 Main St"));      // 5
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "12345678901", "123 Main St")); // 11
    }

    @Test
    @DisplayName("Constructor rejects: address null or >30 chars")
    void constructorRejectsBadAddress() {
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "1234567890", null));
        assertThrows(IllegalArgumentException.class, () ->
            new Contact("123", "John", "Doe", "1234567890", s(31)));
    }

    @Test
    @DisplayName("Setters: happy path including boundary values")
    void settersHappyPathWithBoundaries() {
        Contact c = new Contact("abc", "A", "B", "0123456789", "Addr");

        // boundaries: 10 for first/last, 30 for address, phone exactly 10
        c.setFirstName(s(10));
        c.setLastName(s(10));
        c.setPhoneNumber("9876543210");
        c.setAddress(s(30));

        assertEquals(s(10), c.getFirstName());
        assertEquals(s(10), c.getLastName());
        assertEquals("9876543210", c.getPhoneNumber());
        assertEquals(s(30), c.getAddress());
    }

    @Test
    @DisplayName("Setters: reject invalid values to hit all exception branches")
    void settersRejectInvalidValues() {
        Contact c = new Contact("id", "John", "Doe", "1234567890", "Addr");

        // first name
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setFirstName(s(11)));

        // last name
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> c.setLastName(s(11)));

        // phone
        assertThrows(IllegalArgumentException.class, () -> c.setPhoneNumber(null));
        assertThrows(IllegalArgumentException.class, () -> c.setPhoneNumber("12345"));
        assertThrows(IllegalArgumentException.class, () -> c.setPhoneNumber("12345678901"));

        // address
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> c.setAddress(s(31)));
    }
}
