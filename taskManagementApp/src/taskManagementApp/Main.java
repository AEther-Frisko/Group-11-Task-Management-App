package taskManagementApp;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ArrayList<Task> taskList = new ArrayList<Task>();
		
		boolean run = true;
		while(run) {
			System.out.println("What would you like to do?");
			System.out.println("0) Quit");
			System.out.println("1) Add task");
			System.out.println("2) View all tasks");
			System.out.println("3) Remove a task");
			int input = in.nextInt();
			in.nextLine();
			
			switch(input) {
			case 0:
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
				for(Task task : taskList) {
					if(task.getTitle().equals(title)) {
						taskList.remove(task);
						System.out.println("Removed " + title + "(ID " + task.getId() + ") from tasks.");
					}
					else {
						System.out.println("Could not find a task titled: " + title);
					}
					break;
				}
			default:
				System.out.println("Invalid input, please try again.");
			}
		}
		in.close();
	}
	
	/**
	 * Creates a Task based off of user input.
	 * Some values are optional, and will either be left as null,
	 * or given default values if skipped.
	 * @param in  the input Scanner.
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
	
}
