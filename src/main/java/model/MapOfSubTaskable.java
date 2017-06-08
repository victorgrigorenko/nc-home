package model;

import java.util.Date;
import java.util.Map;

public interface MapOfSubTaskable<T extends Taskable> {

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
