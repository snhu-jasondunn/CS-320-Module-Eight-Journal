/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package tests;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project_one.Appointment;

class AppointmentTest {

    private static Date futureDate(long millisAhead) {
        return new Date(System.currentTimeMillis() + millisAhead);
    }

    private static Date pastDate(long millisBehind) {
        return new Date(System.currentTimeMillis() - millisBehind);
    }

    // ---------- Constructor & Getters ----------

    @Test
    @DisplayName("Constructor: valid inputs initialize fields and getters return values")
    void constructor_validInputs_succeeds() {
        String id = "ABC1234567"; // 10 chars
        Date appt = futureDate(60_000);
        String desc = "Routine check";

        Appointment a = new Appointment(id, appt, desc);

        assertEquals(id, a.getAppointmentId());
        assertEquals(desc, a.getDescription());

        // getAppointmentDate returns equal instant but should not be same reference
        Date got = a.getAppointmentDate();
        assertNotNull(got);
        assertEquals(appt.getTime(), got.getTime());
        assertNotSame(appt, got);
    }

    // ---------- ID validation ----------

    @Test
    @DisplayName("Constructor: null ID throws IllegalArgumentException")
    void constructor_nullId_throws() {
        assertThrows(IllegalArgumentException.class,
            () -> new Appointment(null, futureDate(60_000), "desc"));
    }

    @Test
    @DisplayName("Constructor: ID longer than 10 throws IllegalArgumentException")
    void constructor_idTooLong_throws() {
        String tooLong = "ABCDEFGHIJK"; // 11
        assertThrows(IllegalArgumentException.class, () -> new Appointment(tooLong, futureDate(60_000), "desc"));
    }

    @Test
    @DisplayName("Constructor: ID length exactly 10 is allowed")
    void constructor_idLengthBoundary_allows10() {
        String ten = "1234567890";
        Appointment a = new Appointment(ten, futureDate(60_000), "ok");
        assertEquals(ten, a.getAppointmentId());
    }

    // ---------- Date validation (constructor) ----------

    @Test
    @DisplayName("Constructor: null appointment date throws IllegalArgumentException")
    void constructor_nullDate_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("ID1", null, "desc"));
    }

    @Test
    @DisplayName("Constructor: past appointment date throws IllegalArgumentException")
    void constructor_pastDate_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("ID1", pastDate(1_000), "desc"));
    }

    // ---------- Description validation (constructor) ----------

    @Test
    @DisplayName("Constructor: null description throws IllegalArgumentException")
    void constructor_nullDescription_throws() {
        assertThrows(IllegalArgumentException.class, () -> new Appointment("ID1", futureDate(60_000), null));
    }

    @Test
    @DisplayName("Constructor: description longer than 50 throws IllegalArgumentException")
    void constructor_descriptionTooLong_throws() {
        String longDesc = "x".repeat(51);
        assertThrows(IllegalArgumentException.class, () -> new Appointment("ID1", futureDate(60_000), longDesc));
    }

    @Test
    @DisplayName("Constructor: description length exactly 50 is allowed")
    void constructor_descriptionLengthBoundary_allows50() {
        String fifty = "x".repeat(50);
        Appointment a = new Appointment("ID1", futureDate(60_000), fifty);
        assertEquals(fifty, a.getDescription());
    }

    // ---------- setAppointmentDate ----------

    @Test
    @DisplayName("setAppointmentDate: setting to a future date succeeds and defensively copies")
    void setAppointmentDate_future_succeedsAndDefensiveCopy() {
        Appointment a = new Appointment("ID1", futureDate(60_000), "desc");
        Date external = futureDate(120_000);

        a.setAppointmentDate(external);

        Date stored = a.getAppointmentDate();
        assertEquals(external.getTime(), stored.getTime());
        assertNotSame(external, stored);

        // Mutating external should not change stored
        external.setTime(external.getTime() + 3_600_000);
        assertNotEquals(external.getTime(), a.getAppointmentDate().getTime());
    }

    @Test
    @DisplayName("setAppointmentDate: null throws IllegalArgumentException")
    void setAppointmentDate_null_throws() {
        Appointment a = new Appointment("ID1", futureDate(60_000), "desc");
        assertThrows(IllegalArgumentException.class, () -> a.setAppointmentDate(null));
    }

    @Test
    @DisplayName("setAppointmentDate: past date throws IllegalArgumentException")
    void setAppointmentDate_past_throws() {
        Appointment a = new Appointment("ID1", futureDate(60_000), "desc");
        assertThrows(IllegalArgumentException.class, () -> a.setAppointmentDate(pastDate(1_000)));
    }

    // ---------- getAppointmentDate defensive copy ----------

    @Test
    @DisplayName("getAppointmentDate: returns a defensive copy each time")
    void getAppointmentDate_returnsDefensiveCopies() {
        Appointment a = new Appointment("ID1", futureDate(60_000), "desc");
        Date d1 = a.getAppointmentDate();
        Date d2 = a.getAppointmentDate();

        assertNotSame(d1, d2);
        assertEquals(d1.getTime(), d2.getTime());

        // Mutate returned date; internal state should not change
        d1.setTime(d1.getTime() + 86_400_000);
        assertNotEquals(d1.getTime(), a.getAppointmentDate().getTime());
    }

    // ---------- setDescription ----------

    @Test
    @DisplayName("setDescription: null throws IllegalArgumentException")
    void setDescription_null_throws() {
        Appointment a = new Appointment("ID1", futureDate(60_000), "desc");
        assertThrows(IllegalArgumentException.class, () -> a.setDescription(null));
    }

    @Test
    @DisplayName("setDescription: >50 chars throws; exactly 50 allowed")
    void setDescription_lengthValidation() {
        Appointment a = new Appointment("ID1", futureDate(60_000), "ok");

        String fifty = "x".repeat(50);
        a.setDescription(fifty);
        assertEquals(fifty, a.getDescription());

        String fiftyOne = "x".repeat(51);
        assertThrows(IllegalArgumentException.class, () -> a.setDescription(fiftyOne));
    }

    // ---------- ID immutability (sanity check) ----------

    @Test
    @DisplayName("Appointment ID is immutable")
    void id_isImmutable() {
        Appointment a = new Appointment("IMMUTABLE", futureDate(60_000), "desc");
        assertEquals("IMMUTABLE", a.getAppointmentId());
        // There is no setter; just ensure it remains what was set
        a.setDescription("changed");
        a.setAppointmentDate(futureDate(120_000));
        assertEquals("IMMUTABLE", a.getAppointmentId());
    }
}
