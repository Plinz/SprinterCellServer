package rest;

import static org.junit.Assert.assertEquals;
import javax.ws.rs.core.Application;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Before;
import org.junit.Test;
import BDD.App;
import BDD.MemberDAO;
import BDD.ProjectDAO;
import BDD.ProjectToMemberDao;
import BDD.ProjectToTaskDao;
import BDD.TaskDao;


public class ProjectDBTest extends JerseyTest{
	
private static ProjectDAO dao;
private static MemberDAO dao2;
private static ProjectToMemberDao dao3;
private static TaskDao dao4;
private static ProjectToTaskDao dao5;
	
	@Override
    protected Application configure() {
		dao = App.getDbi().open(ProjectDAO.class);
		dao2 = App.getDbi().open(MemberDAO.class);
		dao3 = App.getDbi().open(ProjectToMemberDao.class);
		dao4 = App.getDbi().open(TaskDao.class);
		dao5 = App.getDbi().open(ProjectToTaskDao.class);
        return new App();
    }
	
	@Before
	public void init () {
		//DROPS
		dao.dropProject();
		dao2.dropMember();
		dao3.dropProjectMember();
		dao4.dropTask();
		dao5.dropProjectTask();
		
		System.out.println("ICI DHGSIDJBUSDJIHBSDB");
		//CREATE
		dao.createProjectTable();
		dao2.createMemberTable();
		dao3.createProjectMember();
		dao4.createTaskTable();
		dao5.createProjectTask();
	}
	
	/*@Test
	public void testCreateProject() {
		ProjectDBRessource pr = new ProjectDBRessource();
		Project p = new Project("projet","description");
		pr.createProject("Naruto",p);
		Project projet = target("/projectdb/projet").request().get(Project.class);
		assertEquals("projet", projet.getName());
	}*/
	
	/*@Test
	public void testCreateProject() {
		System.out.println("T7");
		ProjectDBRessource pr = new ProjectDBRessource();
		System.out.println("T8");
		MemberResource mr = new MemberResource();
		System.out.println("T9");
		Member m = new Member("Naruto");
		System.out.println("T10");
		mr.createMember(m);
		System.out.println("T11");
		Project p = new Project("projet","description");
		System.out.println("T12");
		pr.createProject("Naruto",p);
		System.out.println("T13");
		Project projet = target("/projectdb/1").request().get(rest.Project.class);
		System.out.println("T14");
		assertEquals("projet", projet.getName());
		System.out.println("T15");
	}*/

}
