/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project_one.Task;

class TaskTest {

    // --- helpers ---
    private static String repeat(char ch, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) sb.append(ch);
        return sb.toString();
    }

    @Test
    @DisplayName("Valid task is created and fields are accessible")
    void testValidConstruction() {
        Task t = new Task("12345", "Short Name", "Short description");
        assertEquals(12345, t.getID()); // per current implementation
        assertEquals("Short Name", t.getName());
        assertEquals("Short description", t.getDescription());
    }

    // --- taskID validations ---
    @Test
    @DisplayName("Null taskID throws IllegalArgumentException")
    void testNullIdThrows() {
        assertThrows(IllegalArgumentException.class,
            () -> new Task(null, "Name", "Desc"));
    }

    @Test
    @DisplayName("taskID longer than 10 chars throws IllegalArgumentException")
    void testIdTooLongThrows() {
        String tooLongId = repeat('1', 11);
        assertThrows(IllegalArgumentException.class,
            () -> new Task(tooLongId, "Name", "Desc"));
    }

    @Test
    @DisplayName("Non-numeric taskID causes getID() to throw NumberFormatException")
    void testNonNumericIdGetIdThrows() {
        Task t = new Task("ABCDEF", "Name", "Desc");
        assertThrows(NumberFormatException.class, t::getID);
    }

    // --- name validations (constructor & setter) ---
    @Test
    @DisplayName("Null name throws IllegalArgumentException")
    void testNullNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Task("1", null, "Desc"));
    }

    @Test
    @DisplayName("Empty name throws IllegalArgumentException")
    void testEmptyNameThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Task("1", "", "Desc"));
    }

    @Test
    @DisplayName("Name longer than 20 chars throws IllegalArgumentException")
    void testNameTooLongThrows() {
        String tooLongName = repeat('N', 21);
        assertThrows(IllegalArgumentException.class, () -> new Task("1", tooLongName, "Desc"));
    }

    @Test
    @DisplayName("setName updates when valid; throws when invalid")
    void testSetName() {
        Task t = new Task("123", "Old", "Desc");
        t.setName("New Name");
        assertEquals("New Name", t.getName());

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> t.setName(null)),
            () -> assertThrows(IllegalArgumentException.class, () -> t.setName("")),
            () -> assertThrows(IllegalArgumentException.class, () -> t.setName(repeat('N', 21)))
        );
    }

    // --- description validations (constructor & setter) ---
    @Test
    @DisplayName("Null description throws IllegalArgumentException")
    void testNullDescriptionThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Task("1", "Name", null));
    }

    @Test
    @DisplayName("Empty description throws IllegalArgumentException")
    void testEmptyDescriptionThrows() {
        assertThrows(IllegalArgumentException.class, () -> new Task("1", "Name", ""));
    }

    @Test
    @DisplayName("Description longer than 50 chars throws IllegalArgumentException")
    void testDescriptionTooLongThrows() {
        String tooLongDesc = repeat('D', 51);
        assertThrows(IllegalArgumentException.class, () -> new Task("1", "Name", tooLongDesc));
    }

    @Test
    @DisplayName("setDescription updates when valid; throws when invalid")
    void testSetDescription() {
        Task t = new Task("123", "Name", "Old");
        t.setDescription("New Description");
        assertEquals("New Description", t.getDescription());

        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> t.setDescription(null)),
            () -> assertThrows(IllegalArgumentException.class, () -> t.setDescription("")),
            () -> assertThrows(IllegalArgumentException.class, () -> t.setDescription(repeat('D', 51)))
        );
    }
}