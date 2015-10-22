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
			System.out.println("Table déjà là !");
		}
	}
	
	@POST
	public Member createUser(Member member) {
		int id = dao.insert(member.getPseudo());
		return member;
	}

	@GET
	@Path("/{name}")
	public Member getUser(@PathParam("pseudo") String pseudo) {
		Member user = dao.findByPseudo(pseudo);
		if (user == null) {
			throw new WebApplicationException(404);
		}
		return user;
	}

	@GET
	public List<Member> getAllUsers() {
		return dao.all();
	}
}