package rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;

import BDD.App;
import BDD.MemberDAO;

import java.util.List;

public class MemberDBTest extends JerseyTest {
	
	private static MemberDAO dao;
	
	@Override
    protected Application configure() {
		dao = App.getDbi().open(MemberDAO.class);
        return new App();
    }
	
	@Before
	public void init () {
		dao.dropMember();
		dao.createMemberTable();			
	}
	
	@Test
	public void testCreateUser() {
		MemberResource mr = new MemberResource();
		Member m = new Member("Naruto");
		mr.createMember(m);
		Member membre = target("/memberdb/Naruto").request().get(Member.class);
		assertEquals("Naruto", membre.getPseudo());
	}
	
	@Test
	public void testAllUser(){
		MemberResource mr = new MemberResource();
		Member m = new Member("Naruto");
		Member m2 = new Member("Kakashi");
		mr.createMember(m);
		mr.createMember(m2);
		List<Member> users = target("/memberdb/").request().get(new GenericType<List<Member>>(){});
		assertEquals(2, users.size());
	}

	@Test
	public void testAllUserMustBeOrdered() throws InterruptedException {
		MemberResource mr = new MemberResource();
		Member m = new Member("A");
		Member m2 = new Member("B");
		mr.createMember(m);
		mr.createMember(m2);
		List<Member> users = target("/memberdb/").request().get(new GenericType<List<Member>>(){});
		
		assertEquals("A", users.get(0).getPseudo());
	}

	private void debugServer() throws InterruptedException {
		System.out.println(this.getPort());
		Thread.sleep(120000);
	}
}
