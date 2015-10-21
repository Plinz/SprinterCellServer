package rest;

import java.util.ArrayList;

import BDD.Base;

public class Member {
	private Integer id;
	private String mdp;
	private String email;
	private ArrayList<Project> projets = new ArrayList<Project>();
	private Base bdd = new Base();
	private String pseudo;

	public Member(String pseudo) {
		id = 0;
		this.pseudo = pseudo;
		mdp = bdd.getMdp(pseudo);
		email = bdd.getMail(pseudo);
		ArrayList<Integer> projets = bdd.getProject(pseudo);
		for (int i = 0; i < projets.size(); i++) {
			this.projets.add(new Project(projets.get(i)));
		}
	}

		public Member() {
		}

	public String toString() {
		String print;
		print = " user :" + pseudo + "\nmdp :" + mdp + "\nemail :" + email
				+ "\nprojets :" + projets;
		return print;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public ArrayList<Project> getProjets() {
		return projets;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Base getBdd() {
		return bdd;
	}

	public void setBdd(Base bdd) {
		this.bdd = bdd;
	}

	public void setProjets(ArrayList<Project> projets) {
		this.projets = projets;
	}
}