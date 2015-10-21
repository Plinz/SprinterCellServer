package rest;

import java.util.ArrayList;


public class Task {
	private String name;
	private int id = 0;
	private String codesource;
	private ArrayList<String> keyWords;
	private String formatedSource;
	private Project projet;

	public Project getProjet() {
		return projet;
	}
	public void setProjet(Project projet) {
		this.projet = projet;
	}
	public void setKeyWords(ArrayList<String> keyWords) {
		this.keyWords = keyWords;
	}
	public SourceFile(int id, String name, Project p) {
		this.id = id;
		this.name = name;
		this.codesource = "";
		this.formatedSource = "";
		this.keyWords = new ArrayList<String>();
		this.projet = p;
	}
	public SourceFile(Integer idSourceFile){
		Base b = new Base();
		name = b.getSourceFileName(idSourceFile);
		codesource = b.getSourceFileName(idSourceFile);
		this.keyWords = new ArrayList<String>();
		
	}
	public SourceFile(){
		
	}
	
	public String getCodesource() {
		return codesource;
	}

	public void setCodesource(String source) {
		this.codesource = source;
	}
	
	public String getFormatedSource() {
		return formatedSource;
	}

	public void setFormatedSource(String formatedSource) {
		this.formatedSource = formatedSource;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return id + ": " + name + ": " + codesource;
	}

	public String[] getKeyWords(){
		updateKeyWords();
		return (String[])keyWords.toArray();
	}
	
	
	
}