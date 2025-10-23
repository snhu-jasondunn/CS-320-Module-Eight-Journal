/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project_one.Appointment;
import project_one.AppointmentService;

class AppointmentServiceTest {

    private static Date futureDate(long millisAhead) {
        return new Date(System.currentTimeMillis() + millisAhead);
    }

    // ---------- addAppointment(Appointment) ----------

    @Test
    @DisplayName("addAppointment: null throws IllegalArgumentException")
    void addAppointment_null_throws() {
        AppointmentService svc = new AppointmentService();
        assertThrows(IllegalArgumentException.class, () -> svc.addAppointment((Appointment) null));
    }

    @Test
    @DisplayName("addAppointment: valid unique appointment is added and retrievable")
    void addAppointment_valid_addsAndRetrieves() {
        AppointmentService svc = new AppointmentService();
        Appointment a = new Appointment("ID1", futureDate(60_000), "desc");

        svc.addAppointment(a);

        assertSame(a, svc.getAppointment("ID1"));
        assertNull(svc.getAppointment("NOPE"));
    }

    @Test
    @DisplayName("addAppointment: duplicate ID throws IllegalArgumentException")
    void addAppointment_duplicateId_throws() {
        AppointmentService svc = new AppointmentService();
        svc.addAppointment(new Appointment("DUP", futureDate(60_000), "one"));

        assertThrows(IllegalArgumentException.class,
            () -> svc.addAppointment(new Appointment("DUP", futureDate(120_000), "two")));
    }

    // ---------- addAppointment(String, Date, String) overload ----------

    @Test
    @DisplayName("addAppointment overload: creates and adds appointment")
    void addAppointment_overload_createsAndAdds() {
        AppointmentService svc = new AppointmentService();
        Date when = futureDate(60_000);

        svc.addAppointment("A2", when, "check");

        Appointment stored = svc.getAppointment("A2");
        assertNotNull(stored);
        assertEquals("A2", stored.getAppointmentId());
        assertEquals("check", stored.getDescription());
        assertEquals(when.getTime(), stored.getAppointmentDate().getTime());
    }

    // ---------- deleteAppointment ----------

    @Test
    @DisplayName("deleteAppointment: returns true when existing, false when absent or null")
    void deleteAppointment_behavesAsDocumented() {
        AppointmentService svc = new AppointmentService();
        svc.addAppointment(new Appointment("DEL", futureDate(60_000), "to delete"));

        assertTrue(svc.deleteAppointment("DEL"), "Should return true when removed");
        assertFalse(svc.deleteAppointment("DEL"), "Second delete should be false");
        assertFalse(svc.deleteAppointment("MISSING"), "Missing ID returns false");
        assertFalse(svc.deleteAppointment(null), "Null ID returns false");
    }

    @Test
    @DisplayName("deleteAppointment: actually removes the appointment")
    void deleteAppointment_actuallyRemoves() {
        AppointmentService svc = new AppointmentService();
        svc.addAppointment(new Appointment("X1", futureDate(60_000), "x"));
        assertNotNull(svc.getAppointment("X1"));

        svc.deleteAppointment("X1");
        assertNull(svc.getAppointment("X1"));
    }

    // ---------- getAllAppointments ----------

    @Test
    @DisplayName("getAllAppointments: returns unmodifiable map reflecting contents")
    void getAllAppointments_unmodifiableAndAccurate() {
        AppointmentService svc = new AppointmentService();
        Appointment a = new Appointment("M1", futureDate(60_000), "m1");
        Appointment b = new Appointment("M2", futureDate(120_000), "m2");

        svc.addAppointment(a);
        svc.addAppointment(b);

        Map<String, Appointment> view = svc.getAllAppointments();
        assertEquals(2, view.size());
        assertSame(a, view.get("M1"));
        assertSame(b, view.get("M2"));

        assertThrows(UnsupportedOperationException.class, () -> view.put("NEW", a));
        assertThrows(UnsupportedOperationException.class, () -> view.remove("M1"));
        assertThrows(UnsupportedOperationException.class, view::clear);
    }

    // ---------- sanity: different IDs allowed even if other fields equal ----------

    @Test
    @DisplayName("Different IDs can coexist even with same date/description")
    void differentIds_sameOtherFields_ok() {
        AppointmentService svc = new AppointmentService();
        Date when = futureDate(90_000);

        svc.addAppointment(new Appointment("A", when, "same"));
        svc.addAppointment(new Appointment("B", when, "same"));

        assertNotNull(svc.getAppointment("A"));
        assertNotNull(svc.getAppointment("B"));
    }
}
