/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package project_one;

public class Task {
	
    private static final int MAX_ID_LEN = 10;
    private static final int MAX_NAME_LEN = 20;
    private static final int MAX_DESC_LEN = 50;
	
	private final String taskID;
	private String taskName;
	private String taskDescription;
	
	public Task(String taskID, String taskName, String taskDescription) {
		this.taskID = validateID(taskID);
		setName(taskName);
		setDescription(taskDescription);

	}
	
	/**
	 * validates the taskID
	 * @param taskID
	 * @return boolean
	 */
	private static String validateID(String taskID) {
		if(taskID == null || taskID.length() > MAX_ID_LEN) {
			throw new IllegalArgumentException("invalid taskID");		
		}		
		return taskID;
	}
	
	/**
	 * validates the Name
	 * @param taskName
	 * @return boolean
	 */
	private final boolean validateName(String taskName) {
		if(taskName == null || taskName.length() > MAX_NAME_LEN || taskName.equals("")) {
			return false;			
		}
		return true;
	}

	/**
	 * validates the taskDescription
	 * @param taskDescription
	 * @return boolean
	 */
	private final boolean validateDescription(String taskDescription) {
		if(taskDescription == null || taskDescription.length() > MAX_DESC_LEN || taskDescription.equals("")) {	
			return false;			
		}
		return true;
	}
	
	public int getID() {
		return Integer.valueOf(taskID);
	}

	public String getName() {
		return taskName;
	}

	/**
	 * set's the task's name
	 * @param taskName
	 */
	public void setName(String taskName) {
		if(!this.validateName(taskName)) {
			throw new IllegalArgumentException("Invalid Name");
		}
		this.taskName = taskName;
	}
	
	public String getDescription() {
		return taskDescription;
	}

	/**
	 * set's the task's description
	 * @param taskDescription
	 */
	public void setDescription(String taskDescription) {
		if(!this.validateDescription(taskDescription)) {
			throw new IllegalArgumentException("Invalid Description");
		}
		this.taskDescription = taskDescription;
	}

}
