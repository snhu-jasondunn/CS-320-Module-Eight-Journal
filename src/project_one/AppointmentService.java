/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package project_one;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Service for managing appointments: add with unique ID and delete by ID.
 */
public class AppointmentService {

    private final Map<String, Appointment> appointments = new HashMap<>();

    /**
     * Adds an appointment to the service. The appointment ID must be unique.
     * @param appointment the appointment to add
     * @throws IllegalArgumentException if the appointment is null or the ID already exists
     */
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }
        String id = appointment.getAppointmentId();
        if (appointments.containsKey(id)) {
            throw new IllegalArgumentException("Appointment ID already exists: " + id);
        }
        appointments.put(id, appointment);
    }


    public void addAppointment(String id, java.util.Date date, String description) {
        addAppointment(new Appointment(id, date, description));
    }

    /**
     * Deletes an appointment by its ID.
     * @param appointmentId the ID to delete
     * @return true if an appointment was removed; false if it did not exist
     */
    public boolean deleteAppointment(String appointmentId) {
        if (appointmentId == null) {
            return false;
        }
        return appointments.remove(appointmentId) != null;
    }

    /**
     * Retrieves an appointment by ID, or null if not found.
     */
    public Appointment getAppointment(String appointmentId) {
        return appointments.get(appointmentId);
    }

    /**
     * Returns an unmodifiable view of all appointments keyed by ID.
     */
    public Map<String, Appointment> getAllAppointments() {
        return Collections.unmodifiableMap(appointments);
    }
}