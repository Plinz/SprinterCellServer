package rest;

import java.util.List;

import BDD.App;
import BDD.TaskDao;
import BDD.TaskToMemberDAO;


public class Task {
	private String title;
	private int id, value;
	private String description, state;
	private static TaskDao daoTasks = App.getDbi().open(TaskDao.class); ;


	public Task() {
		
	}
	 
	public Task(String title, String description, int value) {
		this.title = title;
		this.description = description;
		state = "TO DO";
		this.value = value;
	}
	
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void addUser(Member member){
		
	}

	public void removeUser(Member member){
		
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

	public TaskDao getDao() {
		return daoTasks;
	}
	
	public List<Member> getMembers(){
		return this.daoTasks.findMembersByIdt(this.id);
	}

	
	
}