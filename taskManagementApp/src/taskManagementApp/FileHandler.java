package taskManagementApp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
	private String filename;
	private File file;
	
	FileHandler(String filename){
		try {
			this.filename = filename;
			this.file = new File(this.filename);
			if(this.file.createNewFile()) {
				System.out.println("File created: " + this.file.getName());
			}
			else {
				System.out.println("File " + this.file.getName() + " already exists.");
			}
		} catch (IOException e) {
			System.out.println("Error occurred while creating file.");
			e.printStackTrace();
		}
	}
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}
	
	/**
	 * Writes a given task to the file.
	 * @param tasks  Array of Tasks to be written to the file.
	 * @throws FileNotFoundException 
	 */
	public void writeTasksToFile(ArrayList<Task> tasks) throws FileNotFoundException {
	    FileOutputStream fileOutputStream = new FileOutputStream(this.file);

	    try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
	        objectOutputStream.writeInt(tasks.size());
	        for(Task task: tasks) {
	            objectOutputStream.writeObject(task);
	        }
	    } catch (IOException e) {
			System.out.println("Error occurred while writing to file");
			e.printStackTrace();
		}
	}
	
	/**
	 * Reads Tasks from a file and puts them in an array.
	 * @param tasks  Array for Tasks to be added to.
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 */
	public void readTasksFromFile(ArrayList<Task> tasks) throws FileNotFoundException, ClassNotFoundException {
	    FileInputStream fileInputStream = new FileInputStream(this.file);

	    try(ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)){
	        int trainCount = objectInputStream.readInt();
	         for (int i = 0; i < trainCount; i++) {
	            tasks.add((Task)objectInputStream.readObject());
	        }
	    } catch (IOException e) {
	    	// This is also called when the file is empty, unfortunately.
	    	System.out.println("Error occurred while reading from file");
			e.printStackTrace();
		}
	}
}
