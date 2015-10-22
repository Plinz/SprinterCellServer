package rest;

import java.util.ArrayList;

import BDD.App;
import BDD.TaskDao;


public class Task {
	private String title;
	private int id, value;
	private String description;
	private ArrayList<Member> members;
	private static TaskDao dao ;
	static {
		setDao(App.getDbi().open(TaskDao.class));
	}
	
	public Task(int id, String title, ArrayList<Member> members,String description, int value) {
		this.id = id;
		this.title = title;
		this.members = members;
		this.description = description;
		this.value = value;
	}
	
	
	public ArrayList<Member> getMembers() {
		return members;
	}
	

	public void setMembers(ArrayList<Member> members) {
		this.members = members;
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
		return dao;
	}

	public static void setDao(TaskDao dao) {
		Task.dao = dao;
	}
	
	
}