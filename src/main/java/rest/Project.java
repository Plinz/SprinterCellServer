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
		bdd.createProject(this.id, this.name, this.member.getPseudo());
	}
	
	public void addSourceFile (SourceFile src){
		this.sources.add(src);
	}
	
	public void addSourceFile (Collection<? extends SourceFile> src){
		this.sources.addAll(src);
	}
	
	public ArrayList<SourceFile> getSourceFile (){
		return this.sources;
	}
	
	public void removeSourceFile(SourceFile src){
		this.sources.remove(src);
	}
	
	public void setName(String name){
		this.name=name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setOwner(Membre owner){
		this.owner=owner;
	}
	
	public Membre getOwner(){
		return this.owner;
	}
	
	public int getId(){
		return this.id;
	}
	
	public void setId(int id){
		this.id=id;
	}
}
