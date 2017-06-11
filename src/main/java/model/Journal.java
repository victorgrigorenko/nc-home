package model;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)  
@XmlType(name = "journalOfTask")
public class Journal implements Journalable<Task>{
	private FreeID freeID = new FreeID();
	
	@XmlElement(required = true, name = "task") 
	@XmlElementWrapper(name = "tasks")
	private List<Task> taskList = new ArrayList<>();

	@Override
	public int getFreeID(){ 
		return freeID.getID(taskList);
	}
	
	@Override 
	public Task createTask(String title, String desc, Date date) {
		if(title!=null && !title.isEmpty() && desc!=null && date!=null){
			Task task = new Task(title, desc, date);
			task.setID(getFreeID());
			return task;
		}
		
		return null;
	}
	
	@Override
	public boolean addTask(Task task){
		boolean result;
		Task t = returnReferenceOnTask(task.getTitle());
		if (t != null && t.getDestructibleSatus()) 
			freeID.addID(t.getID());
			taskList.remove(t); 
		result = (taskList.add(task))? true: false;
		return result;
	}

	@Override
	public void addTasks(List<? extends Task> list) {
		if (list !=null && !list.isEmpty()){ 
			taskList.addAll(list);
		}
	}

	@Override
	public boolean deleteTask(String title){
		Task task = (title != null && !title.isEmpty())? returnReferenceOnTask(title): null;
		if (task != null && task.getDestructibleSatus()){
			freeID.addID(task.getID());
			taskList.remove(task);
			return true; 
		}

		return false;			
	}
	
	@Override
	public void clearTasks(){ 
		Iterator<Task> iterator = taskList.iterator();
		Task currentTask;
		while(iterator.hasNext()){
			currentTask = iterator.next();
			if(currentTask.getDestructibleSatus()){
				freeID.addID(currentTask.getID());
				iterator.remove();
			}
		}
	}
	
	@Override 
	public Task searchTask(String title){
		if (title == null || title.isEmpty()) 
			return null; 

		for (Task task : taskList) { 
			if (task.getTitle().equals(title))
				return task;
		}
		return null; 
	}
	
	private Task returnReferenceOnTask(String title){ 
		if (title == null || title.isEmpty()) 
			return null; 

		Iterator<Task> iterator = taskList.iterator();
		Task currentTask;
		while(iterator.hasNext()){
			currentTask = iterator.next();
			if(currentTask.getTitle().equals(title))
				return currentTask;
		}
		return null;			
	}

	@Override 
	public boolean replaceTask(String title, Task task) {
		if(title != null && !title.isEmpty() && task != null){
			Task currentTask = returnReferenceOnTask(title);
			int index = taskList.indexOf(currentTask);
			if (index != -1){
				task.setID(currentTask.getID()); 
				taskList.set(index, task);
				return true;
			}
		}
		return false;
	}
	
	@Override  
	public void replaceTasks(List<? extends Task> list) { 
		taskList.clear();
		freeID.clearAllID();
		taskList.addAll(list);
	}

	@Override
	public boolean editTask(String title, String editTitle, String editDescription, Date editDate) {
		Boolean edited = false; 
		Task task = returnReferenceOnTask(title); 
		if (task==null)
			return false;
		
		if(editTitle != null && !editTitle.isEmpty()){ 
			task.setTitle(editTitle);
			edited = true;
		}

		if(editDescription != null && !editDescription.isEmpty()){ 
			task.setDescription(editDescription);
			edited = true;
		}

		if(editDate != null){ 
			task.setDate(editDate);
			edited = true;
		}
		
		return edited;
	}
	
	@Override
	public List<? extends Task> getTasks() {
		return taskList;
	}

	@Override
	public Task getTask(int id) {
		for (Task task : taskList) { 
			if (task.getID() == id)
				return task;
		}
		return null; 
	}	
}
