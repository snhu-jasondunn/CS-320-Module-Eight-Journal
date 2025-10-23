/**
 * Jason Dunn
 * CS-320 Project One
 * 10/15/2025
 */

package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import project_one.TaskService;

class TaskServiceTest {

	private String id, name, description;
	private String badID;

	@BeforeEach
	void setUp(){
		this.id = "0";
		this.name = "The taskName";
		this.description = "This is the taskDescription.";

		this.badID = "12345678901234567890";
		//this.badName = "This is the taskName.";
		//this.badDescription = "This is the taskDescription that is wayyyyyyy toooo long!";
	}


	@AfterEach
	void tearDown() throws Exception {
		TaskService.taskList.clear();
	}

	@DisplayName("Add a Task")
	@Test
	void testAddTask() {
        TaskService newTask = new TaskService();
        assertEquals(0, TaskService.taskList.size());
        newTask.addTask(id, name, description);
		assertTrue(TaskService.taskList.containsKey(id));
		assertEquals(name, TaskService.taskList.get(id).getName());
		assertEquals(description, TaskService.taskList.get(id).getDescription());              
        
	}
	

	/**
	* Add 3 task via addTask, then delete task at id 1 & check the id has been deleted
	*/
	@DisplayName("Test deleteContact")			  
	@Test void testDeleteTask() {
		  
        TaskService newTask = new TaskService();
        assertEquals(0, TaskService.taskList.size());
        newTask.addTask(id, name, description); 
        newTask.addTask("1", name, description);
        newTask.addTask("2", name, description); 
		assertEquals(3,TaskService.taskList.size());
		newTask.deleteTask("1");
		assertEquals(2,TaskService.taskList.size());
		assertFalse(TaskService.taskList.containsKey("1"));
		assertTrue(TaskService.taskList.containsKey("0"));
			 
	}
	
	/*
	 * create a task and then update with valid data
	 */
	@DisplayName("Test editTask with a valid data")
	@Test
	void testEditTask() {
        TaskService newTask = new TaskService();
        assertEquals(0, TaskService.taskList.size());
        newTask.addTask(id, name, description);
        assertEquals(1, TaskService.taskList.size());
        assertTrue(TaskService.taskList.containsKey("0"));
        assertEquals(name, TaskService.taskList.get("0").getName());
        assertEquals(description, TaskService.taskList.get("0").getDescription());
        newTask.editTask("0", "New Name", "New description");
        assertEquals("New description", TaskService.taskList.get("0").getDescription());
        assertEquals("New Name", TaskService.taskList.get("0").getName());
	}
	
	/*
	 * create a task and then update with valid description
	 */
	@DisplayName("Test editTaskDescription with a valid description")
	@Test
	void testEditTaskDescriptionn() {
        TaskService newTask = new TaskService();
        assertEquals(0, TaskService.taskList.size());
        newTask.addTask(id, name, description);
        assertEquals(1, TaskService.taskList.size());
        assertTrue(TaskService.taskList.containsKey("0"));
        assertEquals(name, TaskService.taskList.get("0").getName());
        assertEquals(description, TaskService.taskList.get("0").getDescription());
        newTask.editDescription("0","New description");
        assertEquals("New description", TaskService.taskList.get("0").getDescription());
	}
	
	/*
	 * create a task and then update with valid Name
	 */
	@DisplayName("Test editTaskName with a valid Name")
	@Test
	void testEditTaskName() {
        TaskService newTask = new TaskService();
        assertEquals(0, TaskService.taskList.size());
        newTask.addTask(id, name, description);
        assertEquals(1, TaskService.taskList.size());
        assertTrue(TaskService.taskList.containsKey("0"));
        assertEquals(name, TaskService.taskList.get("0").getName());
        assertEquals(description, TaskService.taskList.get("0").getDescription());
        newTask.editTaskName("0", "New Name");
        assertEquals("New Name", TaskService.taskList.get("0").getName());
	}	
	
	
	/*
	 * create a task and then update with invalid id
	 */
	@DisplayName("Test editTask with a invalid ID")
	@Test
	void testBadUpdateTasks() {
		TaskService newTask = new TaskService();
        newTask.addTask(id, name, description);
        newTask.editTask(badID, name, "New description");
        assertNotEquals("New description", TaskService.taskList.get(id).getDescription());
        assertEquals(name, TaskService.taskList.get(id).getName());
        
	}

}

