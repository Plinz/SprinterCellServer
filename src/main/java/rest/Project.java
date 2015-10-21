package rest;

import java.util.ArrayList;
import java.util.Collection;

import fr.developeverywhere.gestionbdd.Base;

public class Project {
	private String name;
	private ArrayList<Taches> sources;
	private int id;
	private Membre owner;
	
	public Project(){
	}
	
	public Project (String name, Membre owner, int id){
		this.name = name;
		this.owner = owner;
		this.id = id;
		this.sources = new ArrayList<SourceFile>();
	}
	
	public Project(Integer idProject) {
		this.id = idProject;
		this.sources = new ArrayList<SourceFile>();
		Base b = new Base();
		this.name = b.getProjectName(idProject);
		this.owner = b.getProjectOwner(idProject);
		ArrayList<Integer> tmp = b.getSourceFilesFromProject(idProject);
		for(int i = 0; i<tmp.size(); i++){
			sources.add(new SourceFile(tmp.get(i)));
		}
	}
	
	public Project(Integer idProject, Membre owner){
		this.id = idProject;
		this.sources = new ArrayList<SourceFile>();
		Base b = new Base();
		this.name = b.getProjectName(idProject);
		this.owner = owner;
		ArrayList<Integer> tmp = b.getSourceFilesFromProject(idProject);
		for(int i = 0; i<tmp.size(); i++){
			sources.add(new SourceFile(tmp.get(i)));
		}
	}
	
	public void putBDD(){
		Base b = new Base();
		b.createProject(this.id, this.name, this.owner.getPseudo());
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
