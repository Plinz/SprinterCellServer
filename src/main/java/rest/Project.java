package rest;

import java.util.ArrayList;
import java.util.List;

import BDD.App;
import BDD.ProjectDAO;
import BDD.ProjectToMemberDao;
import BDD.ProjectToTaskDao;

public class Project {
	private String name;
	private String description;
	private int id;
	private static ProjectToMemberDao daoProjectToMember = App.getDbi().open(ProjectToMemberDao.class);
	private static ProjectToTaskDao daoProjectToTask = App.getDbi().open(ProjectToTaskDao.class);	
	private static ProjectDAO daoProject = App.getDbi().open(ProjectDAO.class);
	
	public Project(){
	}
	
	public Project(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public void addMember(Member m){
		this.daoProjectToMember.insert(this.id, m.getId());
	}
	
	public void removeMember (Member m){
		this.daoProjectToMember.deleteProjectMember(this.id, m.getId());
	}
	
	public void addTask (Task task){
		this.daoProjectToTask.insert(this.id, task.getId());
	}
	
	public void removeTask(Task task){
		this.daoProjectToTask.deleteProjectTask(this.id, task.getId());
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


	public List<Member> getTasks() {
		return this.daoProject.findTasksByIdp(this.id);
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public List<Member> getMembers() {
		return this.daoProject.findMembersByIdp(this.id);
	}

	
}
