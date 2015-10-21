package rest;

import java.util.ArrayList;


public class Task {
	private String title;
	private int id, value;
	private String description;

	
	public Task(int id, String title, String description, int value) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.value = value;
	}


	


	
	public void addUser(Membre member){
		
	}

	public void removeUser(Membre member){
		
	}

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getValue() {
		return value;
	}


	public void setValue(int value) {
		this.value = value;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Project getProject() {
		return project;
	}


	public void setProject(Project project) {
		this.project = project;
	}
	
	
	
	
	
	
}