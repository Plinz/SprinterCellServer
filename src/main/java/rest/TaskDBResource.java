package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import BDD.App;
import BDD.TaskDao;

import java.util.List;

@Path("/taskdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskDBResource {
	private static TaskDao dao = App.getDbi().open(TaskDao.class);

	public TaskDBResource() {
		try {
			dao.createTaskTable();
			
		} catch (Exception e) {
			System.out.println("Table already exists");
		}
	}
	
	@POST
	public Task createTask(Task task) {
		int id = dao.insert(task.getTitle(), task.getDescription(), task.getValue());
		task.setId(id);
		return task;
	}

	@GET
	@Path("/{title}")
	public Task getTask(@PathParam("title") String title) {
		Task task = dao.findByTitle(title);
		if (task == null) {
			throw new WebApplicationException(404);
		}
		return task;
	}

	@GET
	public List<Task> getAllTasks() {
		return dao.all();
	}

}