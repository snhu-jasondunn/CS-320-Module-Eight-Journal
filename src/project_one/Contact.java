/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package project_one;

public class Contact {
    private static final int MAX_ID_LEN = 10;
    private static final int MAX_NAME_LEN = 10;
    private static final int MAX_ADDRESS_LEN = 30;
	
	private final String contactID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String address;	
	
	
	public Contact(String contactID, String firstName, String lastName, String phoneNumber, String address) {
		
		if(!this.validateID(contactID)) {
			throw new IllegalArgumentException("Invalid contact ID");
		}
		
		if(!this.validateFirstName(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		
		if(!this.validateLastName(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		
		if(!this.validatePhone(phoneNumber)) {
			throw new IllegalArgumentException("Invalid phone number");
		}
		
		if(!this.validateAddress(address)) {
			throw new IllegalArgumentException("Invalid address");
		}		
		
		this.contactID = contactID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	
	/**
	 * validates the contactID
	 * @param contactID
	 * @return boolean
	 */
	private final boolean validateID(String contactID) {
		if(contactID == null || contactID.length() > MAX_ID_LEN) {
			return false;			
		}		
		return true;
	}
	
	/**
	 * validates the Last Name
	 * @param lastName
	 * @return boolean
	 */
	private final boolean validateLastName(String lastName) {
		if(lastName == null || lastName.length() > MAX_NAME_LEN) {
			return false;			
		}
		return true;
	}
	/**
	 * validates the first Name
	 * @param firstName
	 * @return boolean
	 */
	private final boolean validateFirstName(String firstName) {
		if(firstName == null || firstName.length() > MAX_NAME_LEN) {	
			return false;			
		}
		return true;
	}
	/**
	 * validates the phone number
	 * @param phoneNumber
	 * @return boolean
	 */
	private final boolean validatePhone(String phoneNumber) {
		if(phoneNumber == null || !phoneNumber.matches("\\d{10}")) {
			return false;			
		}
		return true;
	}
	/**
	 * validates the address
	 * @param address
	 * @return boolean
	 */
	private final boolean validateAddress(String address) {
		if(address == null || address.length() > MAX_ADDRESS_LEN) {
			return false;			
		}
		return true;
	}
	

	public String getContactID() {return contactID;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}
	public String getPhoneNumber() {return phoneNumber;}
	public String getAddress() {return address;}

	public void setFirstName(String firstName) {
		if(!this.validateFirstName(firstName)) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		if(!this.validateLastName(lastName)) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		if(!this.validatePhone(phoneNumber)) {
			throw new IllegalArgumentException("Invalid phone number");
		}
		this.phoneNumber = phoneNumber;
	}

	public void setAddress(String address) {
		if(!this.validateAddress(address)) {
			throw new IllegalArgumentException("Invalid address");
		}	
		this.address = address;
	}
}
