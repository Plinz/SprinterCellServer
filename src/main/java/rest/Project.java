package rest;

import java.util.ArrayList;
import java.util.Collection;

import BDD.Base;

public class Project {
	private String name;
	private String description;
	private ArrayList<Task> tasks;
	private int id;
	private ArrayList<Member> members;
	private Base bdd;
	
	public Project(){
	}
	
	
	public Project(String name, String description, ArrayList<Task> tasks,
			int id, ArrayList<Member> members, Base bdd) {
		super();
		this.name = name;
		this.description = description;
		this.tasks = tasks;
		this.id = id;
		this.members = members;
		this.bdd = bdd;
	}



	public Project(Integer idProject) {
		this.id = idProject;
		this.tasks = new ArrayList<Task>();
		this.bdd = new Base();
		this.name = bdd.getProjectName(idProject);
		this.members = bdd.getProjectMembers(idProject);
		ArrayList<Integer> tmp = bdd.getProjectTasks(idProject);
		for(int i = 0; i<tmp.size(); i++){
			this.tasks.add(new Task(tmp.get(i)));
		}
	}
	
	public void putBDD(){
		this.bdd = new Base();
		bdd.createProject(this.id, this.name, this.members.getPseudo());
	}
	
	public void addTask (Task task){
		this.tasks.add(task);
	}
	
	public void addTasks (Collection<? extends Task> tasks){
		this.tasks.addAll(tasks);
	}
	
	public void removeTask(Task task){
		this.tasks.remove(task);
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public ArrayList<Task> getTasks() {
		return tasks;
	}


	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ArrayList<Member> getMembers() {
		return members;
	}


	public void setMembers(ArrayList<Member> members) {
		this.members = members;
	}
	
}
