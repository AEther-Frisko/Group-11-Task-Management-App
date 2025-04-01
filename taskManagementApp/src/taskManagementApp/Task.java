package taskManagementApp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Task implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private ArrayList<String> tags;
	private Priority priority;
	private TaskStatus status;
	
	public Task(int id, String title, String description, LocalDate dueDate, Priority priority, TaskStatus status) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.priority = priority;
		this.status = status;
		this.tags = new ArrayList<String>();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	public ArrayList<String> getTags() {
		return tags;
	}
	public void setTags(ArrayList<String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	public boolean removeTag(String tag) {
		if (tags.contains(tag)) {
			tags.remove(tag);
			return true;
		}
		return false;
	}
	public void clearTags() {
		tags.clear();
	}
	
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
		this.status = status;
	}
	
	public void displayTask() {
		System.out.println("ID: " + this.id);
		System.out.println("Task: " + this.title);
		System.out.println(this.description);
		System.out.println("Due: " + this.dueDate);
		System.out.println("Tags:");
		if (this.tags != null) {
			this.tags.forEach(System.out::println);
		}
		System.out.println("Priority: " + this.priority);
		System.out.println("Status: " + this.status);
	}
}
