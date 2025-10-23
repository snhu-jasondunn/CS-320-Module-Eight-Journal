/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package project_one;

import java.util.Date;

/**
 * Represents an appointment with an immutable unique ID, a date that cannot be in the past, and a description with a 50-character limit.
 */
public class Appointment {
    private static final int MAX_ID_LEN = 10;
    private static final int MAX_DESCRIPTION_LEN = 50;

    private final String appointmentId; // immutable, max length 10, non-null
    private Date appointmentDate;       // non-null, not in the past
    private String description;         // non-null, max length 50

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        validateId(appointmentId);
        this.appointmentId = appointmentId;

        setAppointmentDate(appointmentDate); // validates non-null and not in the past
        setDescription(description);         // validates non-null and length
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        // Defensive copy because Date is mutable
        return appointmentDate == null ? null : new Date(appointmentDate.getTime());
    }

    public void setAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null) {
            throw new IllegalArgumentException("Appointment date cannot be null.");
        }
        if (appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }
        this.appointmentDate = new Date(appointmentDate.getTime());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        if (description.length() > MAX_DESCRIPTION_LEN) {
            throw new IllegalArgumentException("Description cannot be longer than 50 characters.");
        }
        this.description = description;
    }

    private static void validateId(String appointmentId) {
        if (appointmentId == null) {
            throw new IllegalArgumentException("Appointment ID cannot be null.");
        }
        if (appointmentId.length() > MAX_ID_LEN) {
            throw new IllegalArgumentException("Appointment ID cannot be longer than 10 characters.");
        }
    }

}
