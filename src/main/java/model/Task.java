package model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "task")
public class Task implements Taskable{

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
		// TODO Auto-generated method stub
		return formatDate.format(getDate().getTime());
	}
}
