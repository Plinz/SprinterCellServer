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
import BDD.ProjectDAO;
import java.util.List;

public class ProjectDBTest extends JerseyTest{
	
private static ProjectDAO dao;
private static MemberDAO dao2;
	
	@Override
    protected Application configure() {
		dao = App.getDbi().open(ProjectDAO.class);
		dao2 = App.getDbi().open(MemberDAO.class);
        return new App();
    }
	
	@Before
	public void init () {
		dao.dropProject();
		dao.createProjectTable();
		dao2.createMemberTable();
	}
	
	@Test
	public void testCreateProject() {
		ProjectDBRessource pr = new ProjectDBRessource();
		MemberResource mr = new MemberResource();
		Member m = new Member("Naruto");
		mr.createMember(m);
		Project p = new Project("projet","description");
		pr.createProject("Naruto",p);
		Project projet = target("/projectdb/projet").request().get(Project.class);
		assertEquals("projet", projet.getName());
	}

}
