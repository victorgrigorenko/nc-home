package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "task")
public class Task implements Taskable<Task>{

	private FreeID freeID = new FreeID();
	
	@XmlElement(required = true, name = "subTask") 
	@XmlElementWrapper(name = "subTasks")
	private Map<Integer, Task> mapOfSubTask = new LinkedHashMap<>(); 
	
	@XmlElement
	private int owner = -1; 
	
	@XmlElement
	private boolean destructible = true; 

	@XmlElement
	private int id;
	
	@XmlElement
	private String title;
	
	@XmlElement
	private String description;
	
	@XmlElement
	private Date date;
	
	private SimpleDateFormat formatDate = new SimpleDateFormat("dd.MM.YYYY HH:mm");

	public Task(){
		title = "No Name";
		description = "...";
		date = new Date(); 
	}
	
	public Task(String title){
		setTitle(title);
		description = "...";
		date = new Date();
	}

	public Task(String title, String desc){
		setTitle(title);
		setDescription(desc);
		date = new Date();
	}

	public Task(String title, Date date){
		setTitle(title);
		description = "...";
		setDate(date); 
	}

	public Task(String title, String desc, Date date){
		setTitle(title);
		setDescription(desc);
		setDate(date);
	}

	private Task(String title, String desc, Date date, int owner){
		setTitle(title);
		setDescription(desc);
		setDate(date);
		this.owner = owner;
	}

	@Override
	public Taskable createTask() {
		return new Task();
	}

	@Override
	public Taskable createTask(String title, String desc, Date date) {
		return new Task(title,desc,date);
	}
	@Override
	public int getID() {
		return id;
	}

	@Override
	public void setID(int id) {
		this.id = id;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String desc) {
		this.description = desc;
	}

	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) { 
		this.date = date;		
	}
	
	@Override
	public String toString(){
		return title;
	}

	@Override
	public String show(){
		return "название: " + this.getTitle()
		+"\nописание: "+ this.getDescription()
		+"\nдата: "+ getStringDate();
	}

	@Override
	public String getStringDate() {
		return formatDate.format(getDate().getTime());
	}

	@Override
	public int getOwner() {
		return owner;
	}

	@Override
	public boolean getDestructibleSatus() {
		return destructible;
	}

	@Override
	public void setDestructibleSatus(boolean status) {
		destructible = status;
	}

	

	private int getFreeID(){ 
		return freeID.getID(mapOfSubTask);
	}

	private Task returnReferenceOnTask(String title){ 
		if (title == null || title.isEmpty()) 
			return null; 

		Iterator<Entry<Integer, Task>> iterator = mapOfSubTask.entrySet().iterator();
		Task currentTask;
		while(iterator.hasNext()){
			Entry<Integer, Task> entry = iterator.next();
			currentTask = entry.getValue();
			if(currentTask.getTitle().equals(title))
				return currentTask;
		}
		return null;			
	}
	
	@Override
	public Task createSubTask(String title, String desc, Date date) {
		if(title!=null && !title.isEmpty() && desc!=null && date!=null){
			Task task = new Task(title, desc, date, id); 
			task.setID(getFreeID());
			return task;
		}
		return null;
	}

	@Override
	public void addSubTask(Task subTask) {
		if(subTask != null){
			mapOfSubTask.put(subTask.getID(), subTask);
		}
	}

	@Override
	public boolean replaceSubTask(String title, Task subTask) {
		if(title != null && !title.isEmpty() && subTask != null){
			Task currentTask = returnReferenceOnTask(title);
			if(currentTask != null){
				mapOfSubTask.replace(currentTask.getID(), currentTask, subTask);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean editSubTask(int id, String editTitle, String editDescription, Date editDate) {
		Boolean edited = false; 
		Task task = getSubTask(id); 
		if (task == null)
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
	public Map<Integer, Task> getSubTasks() {
		return mapOfSubTask;
	}

	@Override
	public boolean deleteSubTask(int id) {		
		Task task = getSubTask(id);
		if (task != null && task.getDestructibleSatus()){
			freeID.addID(id);
			return mapOfSubTask.remove(id, task);
		}
		return false;			
	}

	@Override
	public void clearSubTasks() {
		Iterator<Entry<Integer,Task>> iterator = mapOfSubTask.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Integer, Task> entry = iterator.next();
			if(entry.getValue().getDestructibleSatus()){
				freeID.addID(entry.getKey());
				iterator.remove();
			}
		}
	}

	@Override
	public Task searchSubTask(String title) {
		if (title == null || title.isEmpty()) 
			return null; 

		for(Entry<Integer, Task> entry: mapOfSubTask.entrySet()){
			if(entry.getValue().getTitle().equals(title)){
				return entry.getValue();
			}
		}
		return null;
	}
	
	@Override
	public Task getSubTask(int id) {
		for(Entry<Integer, Task> entry: mapOfSubTask.entrySet()){
			if (entry.getKey() == id)
				return entry.getValue();
		}
		return null; 
	}	
}
