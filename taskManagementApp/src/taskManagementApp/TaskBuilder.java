package taskManagementApp;

import java.time.LocalDate;

public class TaskBuilder implements Builder {
	private int id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private Priority priority;
	private TaskStatus status;
	
	@Override
	public void setId(int id) {
		this.id = id;
		
	}
	@Override
	public void setTitle(String title) {
		this.title = title;
		
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
		
	}
	@Override
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
		
	}
	@Override
	public void setPriority(Priority priority) {
		this.priority = priority;
		
	}
	@Override
	public void setStatus(TaskStatus status) {
		this.status = status;
		
	}
	
	public Task getResult() {
		return new Task(id, title, description, dueDate, priority, status);
	}
}
