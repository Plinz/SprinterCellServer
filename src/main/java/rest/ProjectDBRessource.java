package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import BDD.App;
import BDD.MemberDAO;
import BDD.ProjectDAO;


@Path("/projectdb")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectDBRessource {
	private static ProjectDAO daoProject = App.getDbi().open(ProjectDAO.class);
	private static MemberDAO daoMember = App.getDbi().open(MemberDAO.class);

	public ProjectDBRessource() {
		try {
			daoProject.createProjectTable();
		} catch (Exception e) {
			System.out.println("Table already exists !");
		}
	}
	
	
	@POST
	@Path("/{pseudo}")
	public rest.Project createProject(@PathParam("pseudo") String pseudo, Project project) {
		System.out.println("S1");
		project.addMember(this.daoMember.findByPseudo(pseudo));
		this.daoProject.insert(project.getName(), project.getDescription(), project.getTasks().toString(), project.getMembers().toString() );
		System.out.println("S3");
		return project;
	}
	
	@DELETE
	@Path("{id}")
	public Response deleteProject(@PathParam("id") Integer id) {
		this.daoProject.deleteProject(id);
	    return Response.accepted().status(Status.NOT_FOUND).build();
	}
	
	@PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("{id}")
	public Response updateProject(@PathParam("id") int id, 
			Project project) {
		Project update = daoProject.findProjectByIdp(id);
		if (update == null) {
			throw new WebApplicationException(404);
		}
		update.update(project);
		return Response.status(200).entity(update).build();
	}
	
	@GET
	@Path("/{id}")
	public Project getProject(@PathParam("id") int id ) {
		Project p =daoProject.findProjectByIdp(id);
		
		if (p == null) {
			throw new WebApplicationException(404);
		}
		System.out.println(daoProject.all().get(0).getName());
		return p;
	}
	
	@GET
	public List<Project> getProjects(@PathParam("pseudo") String pseudo) {
		return daoMember.getProjects(pseudo);
	}

}