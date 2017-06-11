package model;

import java.util.Date;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "taskable")
public interface Taskable<T extends Taskable> {
	
	Taskable createTask();

	Taskable createTask(String title, String desc, Date date);

	public int getOwner(); // Вернуть ID владельца

	public boolean getDestructibleSatus(); // Вернуть статус (уничтожаемая/неуничтожаемая задача)

	public void setDestructibleSatus(boolean status); 

	public int getID();
	
	public void setID(int id);
	
	String getTitle();
	
	void setTitle(String title);
	
	String getDescription();
	
	void setDescription(String desc);
	
	Date getDate();
	
	String getStringDate();
	
	void setDate(Date date);
	
	String show();

	T createSubTask(String title, String desc, Date date); // создать подзадачу

	void addSubTask(T subTask); 

	boolean replaceSubTask(String title, T subTask);

	boolean editSubTask(int id, String editTitle, String editDescription, Date editDate); 

	Map<Integer,T> getSubTasks(); // LinkedHashMap - т.к. в порядке вставки
	
	boolean deleteSubTask(int id); 
	
	void clearSubTasks(); 
	
	T searchSubTask(String title);

	T getSubTask(int id);
}
