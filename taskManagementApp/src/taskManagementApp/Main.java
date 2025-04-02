package taskManagementApp;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);
		ArrayList<Task> taskList = new ArrayList<Task>();
		
		FileHandler fileHandler = new FileHandler("tasks.bin");
		fileHandler.readTasksFromFile(taskList);
		
		boolean run = true;
		while(run) {
			System.out.println("What would you like to do?");
			System.out.println("0) Save and Quit");
			System.out.println("1) Add task");
			System.out.println("2) View all tasks");
			System.out.println("3) Remove a task");
			System.out.println("4) Edit a task");
			System.out.println("5) Filter tasks by tag");
			int input = in.nextInt();
			in.nextLine();
			
			switch(input) {
			case 0:
				System.out.println("Saving tasks...");
				fileHandler.writeTasksToFile(taskList);
				
				System.out.println("Quitting...");
				run = false;
				break;
			case 1:
				System.out.println("Starting task creation...");
				taskList.add(createTask(in));
				for(Task task : taskList) {
					task.setId(taskList.indexOf(task));
				}
				break;
			case 2:
				System.out.println("All recorded tasks:");
				for(Task task : taskList) {
					task.displayTask();
					System.out.println("-----");
				}
				break;
			case 3:
				System.out.println("Enter the task's name:");
				String title = in.nextLine();
				int index = searchForTask(taskList, title);
				if (index != -1) {
					taskList.remove(index);
					System.out.println("Removed " + title + "(ID " + index + ") from tasks.");
					for(Task task : taskList) {
						task.setId(taskList.indexOf(task));
					}
				}
				break;
			case 4:
				System.out.println("Enter the task's name:");
				String title1 = in.nextLine();
				int index1 = searchForTask(taskList, title1);
				if (index1 != -1) {
					System.out.println("Editing task " + title1 + " (ID " + index1 + ")...");
					editTask(taskList.get(index1), in);
				}
				break;
			case 5:
				System.out.println("Enter the tag to filter by:");
				String tag = in.nextLine();
				System.out.println("Tasks under " + tag + ":");
				for(Task task : taskList) {
					if(task.getTags().contains(tag)) {
						task.displayTask();
						System.out.println("-----");
					}
				}
			default:
				System.out.println("Invalid input, please try again.");
				break;
			}
		}
		in.close();
	}
	
	/**
	 * Creates a Task based off of user input.
	 * Some values are optional, and will either be left as null,
	 * or given default values if skipped.
	 * @param in  Input Scanner.
	 * @return The newly created Task.
	 */
	public static Task createTask(Scanner in) {
		Task newTask = null;
		TaskBuilder builder = new TaskBuilder();
		
		System.out.println("Enter task name:");
		String title = in.nextLine();
		builder.setTitle(title);
		
		System.out.println("Add a description? (y/n)");
		if(in.nextLine().equals("y")) {
			System.out.println("Enter description:");
			String desc = in.nextLine();
			builder.setDescription(desc);
		}
		
		System.out.println("Add a due date? (y/n)");
		if(in.nextLine().equals("y")) {
			System.out.println("Enter due date (YYYY-MM-DD):");
			String due = in.nextLine();
			LocalDate dueDate = LocalDate.parse(due);
			builder.setDueDate(dueDate);
		}
		
		System.out.println("Set the priority? (y/n)");
		if(in.nextLine().equals("y")) {
			System.out.println("Enter the priority (Low, Medium, High):");
			String pr = in.nextLine();
			Priority priority = Priority.valueOf(pr);
			builder.setPriority(priority);
		}
		else {
			builder.setPriority(Priority.Low);
		}
		
		System.out.println("Set the task status? (y/n)");
		if(in.nextLine().equals("y")) {
			System.out.println("Enter the status (ToDo, InProgress, Completed):");
			String st = in.nextLine();
			TaskStatus status = TaskStatus.valueOf(st);
			builder.setStatus(status);
		}
		else {
			builder.setStatus(TaskStatus.ToDo);
		}
		
		newTask = builder.getResult();
		return newTask;
	}
	
	/**
	 * Searches for a specified Task via its title.
	 * @param taskList  List of Tasks to search.
	 * @param title     Task title to search for.
	 * @return  An integer indicating the index of the found Task (-1 if not found).
	 */
	public static int searchForTask(ArrayList<Task> taskList, String title) {
		for(Task task : taskList) {
			if(task.getTitle().equals(title)) {
				return task.getId();
			}
		}
		System.out.println("Could not find a task titled: " + title);
		return -1;
	}
	
	/**
	 * Edits attributes of a task based on user input.
	 * Loops until the user chooses to exit, allowing multiple edits.
	 * @param task  Task to be edited.
	 * @param in    Input scanner.
	 */
	public static void editTask(Task task, Scanner in) {
		boolean run = true;
		while(run) {
			System.out.println("Which part of the task (ID " + task.getId() + ") would you like to edit?");
			System.out.println("0) Go Back");
			System.out.println("1) Title");
			System.out.println("2) Description");
			System.out.println("3) Due Date");
			System.out.println("4) Priority");
			System.out.println("5) Status");
			System.out.println("6) Add Tag");
			System.out.println("7) Remove Tag");
			System.out.println("8) Clear all Tags");
			int input = in.nextInt();
			in.nextLine();
			
			switch(input) {
			case 0:
				System.out.println("Returning to main menu...");
				run = false;
				break;
			case 1:
				System.out.println("Enter new title:");
				String title = in.nextLine();
				task.setTitle(title);
				System.out.println("Title changed to: " + task.getTitle());
				break;
			case 2:
				System.out.println("Enter new description:");
				String desc = in.nextLine();
				task.setDescription(desc);
				System.out.println("Description changed to: " + task.getDescription());
				break;
			case 3:
				System.out.println("Enter new date (YYYY-MM-DD):");
				String date = in.nextLine();
				LocalDate dueDate = LocalDate.parse(date);
				task.setDueDate(dueDate);
				System.out.println("Due date changed to: " + task.getDueDate());
				break;
			case 4:
				System.out.println("Enter new priority (Low, Medium, High):");
				String pr = in.nextLine();
				Priority priority = Priority.valueOf(pr);
				task.setPriority(priority);
				System.out.println("Priority changed to: " + task.getPriority());
				break;
			case 5:
				System.out.println("Enter new status (ToDo, InProgress, Completed):");
				String st = in.nextLine();
				TaskStatus status = TaskStatus.valueOf(st);
				task.setStatus(status);
				System.out.println("Status changed to: " + task.getStatus());
				break;
			case 6:
				System.out.println("Enter tag to add:");
				String newTag = in.nextLine();
				task.addTag(newTag);
				System.out.println("Tag added: " + task.getTags().getLast());
				break;
			case 7:
				System.out.println("Enter tag to remove:");
				String killTag = in.nextLine();
				boolean wasFound = task.removeTag(killTag);
				if(wasFound) {
					System.out.println("Tag removed: " + killTag);
				}
				else {
					System.out.println("Could not find tag: " + killTag);
				}
				break;
			case 8:
				task.clearTags();
				System.out.println("All tags cleared.");
			default:
				System.out.println("Invalid input, please try again.");
				break;
			}
		}
	}
}
