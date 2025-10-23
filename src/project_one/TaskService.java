/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package project_one;

import java.util.HashMap;

public class TaskService {
		
	//create new list to hold the contacts
	public static HashMap<String, Task> taskList = new HashMap<String, Task>();
	
	/**
	 * adds a new task and increments currentIDNumber
	 * @param taskName
	 * @param taskDescriptioin
	 */
	public void addTask(String taskID, String taskName, String taskDescription) {		
		Task newTask = new Task(taskID, taskName, taskDescription);
		Task existing = taskList.putIfAbsent(taskID, newTask);
		if (existing != null) {
			throw new IllegalArgumentException("taskId already exists: " + taskID);
		}
	}
	
	/**
	 * Delete task by taskID
	 * @param taskID
	 */	
	public boolean deleteTask(String taskID) {
		if(taskList.containsKey(taskID)) {
			return taskList.remove(taskID) != null;
		}
		
		return false;
	}

	/**
	 * edit task by taskID
	 * @param taskID
	 * @param newName
	 * @param newDescription
	 */
	public void editTask(String taskID, String newName, String newDescription) {
		if (taskList.containsKey(taskID)) {
			taskList.get(taskID).setName(newName);
			taskList.get(taskID).setDescription(newDescription);
		}
	}

	/**
	 * edit editName by taskID
	 * @param taskID
	 * @param newName
	 */
	public void editTaskName(String taskID, String newName) {
		if (taskList.containsKey(taskID)) {
			taskList.get(taskID).setName(newName);
		}
	}
	/**
	 * edit taskDescription by taskID
	 * @param taskID
	 * @param newDescription
	 */
	public void editDescription(String taskID, String newDescription) {
		if (taskList.containsKey(taskID)) {
			taskList.get(taskID).setDescription(newDescription);
		}
	}

}
