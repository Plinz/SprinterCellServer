package rest;

import java.util.ArrayList;

import BDD.App;
import BDD.MemberDAO;

public class Member {
	private Integer id;
	private String mdp;
	private String email;
	private String pseudo;
	private static MemberDAO dao ;
	static {
		dao = App.getDbi().open(MemberDAO.class);
	}
	public Member(String pseudo) {
		this.pseudo = pseudo;
	}

	public Member() {
	}

	public String toString() {
		String print;
		print = " user :" + pseudo + "\nmdp :" + mdp + "\nemail :" + email
				+ "\nprojets :" +getProjets();
		return print;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return id;
	}
	public ArrayList<Project> getProjets() {
		return dao.getProjects(id);
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


}