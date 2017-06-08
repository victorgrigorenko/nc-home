package model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "taskable")
public interface Taskable {
	
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
}
