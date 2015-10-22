package rest;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import BDD.App;
import BDD.MemberDAO;

import java.util.List;

@Path("/memberdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MemberResource {
	private static MemberDAO dao = App.getDbi().open(MemberDAO.class);

	public MemberResource() {
		try {
			dao.createMemberTable();
		} catch (Exception e) {
			System.out.println("Table already exists !");
		}
	}
	
	@POST
	public Member createMember(Member member) {
		int id = dao.insert(member.getPseudo());
		return member;
	}

	@GET
	@Path("/{pseudo}")
	public Member getMember(@PathParam("pseudo") String pseudo) {
		Member user = dao.findByPseudo(pseudo);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}
	
	@GET
	@Path("/login")
	public Boolean login(Member member){
		Member user = dao.findByPseudo(member.getPseudo());
		if (user.getMdp()==member.getMdp()){
			return true;
		}
		else{
			return false;
		}
	}
	

	@GET
	public List<Member> getAllMember() {
		return dao.all();
	}
}