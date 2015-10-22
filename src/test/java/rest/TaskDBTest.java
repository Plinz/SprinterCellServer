package rest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.GenericType;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import BDD.App;
import BDD.TaskDao;

public class TaskDBTest extends JerseyTest {

	private static TaskDao dao;

	@Override
	protected Application configure() {
		dao = App.getDbi().open(TaskDao.class);
		return new App();
	}

	@Before
	public void init() {
		dao.dropTask();
		dao.createTaskTable();
	}

	@Test
	public void testCreateTask() {
		TaskDBResource tr = new TaskDBResource();
		Task t = new Task("tache", "description", 13);
		tr.createTask(t);

		Task tache = target("/taskdb/tache").request().get(Task.class);
		Assert.assertEquals("tache", tache.getTitle());
	}
	
	@Test
	public void testAllTask(){
		TaskDBResource tr = new TaskDBResource();
		Task t = new Task("tache", "description", 13);
		Task t2 = new Task("tache2", "description2", 8);
		tr.createTask(t);
		tr.createTask(t2);
		List<Task> tasks = target("/taskdb/").request().get(new GenericType<List<Task>>(){});
		assertEquals(2, tasks.size());
	}
	
	@Test
	public void testAllTaskMustBeOrdered() throws InterruptedException {
		TaskDBResource tr = new TaskDBResource();
		Task t = new Task("AAA", "descriptionA", 13);
		Task t2 = new Task("BBB", "descriptionB", 8);
		tr.createTask(t);
		tr.createTask(t2);
		List<Task> tasks = target("/taskdb/").request().get(new GenericType<List<Task>>(){});
		assertEquals("AAA", tasks.get(0).getTitle());
	}

}
