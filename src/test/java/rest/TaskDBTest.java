package rest;

import javax.ws.rs.core.Application;

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

}
