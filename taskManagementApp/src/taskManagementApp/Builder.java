package taskManagementApp;

import java.time.LocalDate;

public interface Builder {
	void setId(int id);
	void setTitle(String title);
	void setDescription(String description);
	void setDueDate(LocalDate dueDate);
	void setPriority(Priority priority);
	void setStatus(TaskStatus status);
}
