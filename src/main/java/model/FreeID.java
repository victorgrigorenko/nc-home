package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class FreeID {
	private ArrayList<Integer> freeIDs = new ArrayList<>();
		
	public boolean addID(int id){ 
		return freeIDs.add(id);
	}
	
	public void clearAllID(){
		freeIDs.clear();
	}
	
	public int getID(List<Task> listOfTasks){ 
		Iterator<Integer> id = freeIDs.iterator();
		int tmpID = -1; 
		while(id.hasNext()){
			tmpID = id.next();
			id.remove();
			return tmpID;
		}
		Iterator<Task> iterator = listOfTasks.iterator();
		Task currentTask;
		while(iterator.hasNext()){
			currentTask = iterator.next();
			if(currentTask.getID() > tmpID){
				tmpID = currentTask.getID();
			}
		}
		return ++tmpID;
	}
	

	public int getID(Map<Integer, Task> mapOfSubTasks){ 
		Iterator<Integer> id = freeIDs.iterator();
		int tmpID = -1; 
		while(id.hasNext()){
			tmpID = id.next();
			id.remove();
			return tmpID;
		}
		Iterator<Entry<Integer, Task>> iterator = mapOfSubTasks.entrySet().iterator();
		Task currentTask;
		while(iterator.hasNext()){
			Entry<Integer, Task> entry = iterator.next();
			currentTask = entry.getValue();
			if(currentTask.getID() > tmpID){
				tmpID = currentTask.getID();
			}
		}
		return ++tmpID;
	}
		
}
