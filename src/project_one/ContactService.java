/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package project_one;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ContactService {
	//create new list to hold the contacts
	private final Map<String, Contact> contacts = new HashMap<>();
	
	public boolean addContact(Contact contact) {
		if (contacts.containsKey(contact.getContactID())){ return false; }
		contacts.put(contact.getContactID(), contact);
		return true;
	}

	/**
	 * adds a new contact and increments currentIDNumber
	 * @param firstName
	 * @param lastName
	 * @param phoneNumber
	 * @param address
	 */
	public void addContact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		addContact(new Contact(contactID, firstName,lastName, phoneNumber, address));
	}
	
	/**
	 * Delete contact by contactID
	 * @param contactID
	 */	
	public boolean deleteContact(String contactID) {
		if (contactID == null){	return false; };
		return contacts.remove(contactID) != null;
	}
	
	/**
	 * Edit firstName by contactID
	 * @param contactID
	 * @param newFirstName
	 */
	public boolean editFirstName(String contactID, String newFirstName) {
		Contact contact = contacts.get(contactID);
		if (contact== null){ return false; };
		contact.setFirstName(newFirstName);
		return true;
	}
	
	/**
	 * edit lastName by contactID
	 * @param contactID
	 * @param newLastName
	 */
	public boolean editLastName(String contactID, String newLastName) {
		Contact contact = contacts.get(contactID);
		if (contact== null){ return false; };
		contact.setLastName(newLastName);
		return true;
	}

	/**
	 * edit phoneNumber by contactID
	 * @param contactID
	 * @param newPhoneNumber
	 */
	public boolean editPhoneNumber(String contactID, String newPhoneNumber) {
		Contact contact = contacts.get(contactID);
		if (contact== null){ return false; };
		contact.setPhoneNumber(newPhoneNumber);
		return true;
	}

	/**
	 * edit address by contactID
	 * @param contactID
	 * @param newAddress
	 */
	public boolean editAddress(String contactID, String newAddress) {
		Contact contact = contacts.get(contactID);
		if (contact== null){ return false;};
		contact.setAddress(newAddress);
		return true;
	}

	/**
     * Retrieves an contact by ID, or null if not found.
     */
	public Contact getContact(String contactID){
		return contacts.get(contactID);
	}


	/**
     * Returns an unmodifiable view of all contacts keyed by ID.
     */
	public Map<String, Contact> getAllContacts(){
		return Collections.unmodifiableMap(contacts);
	}
}
