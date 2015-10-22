package rest;

import java.awt.PageAttributes.MediaType;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import BDD.App;
import BDD.MemberDAO;
import BDD.ProjectDAO;


@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectDBRessource {
	private static ProjectDAO daoProject = App.getDbi().open(ProjectDAO.class);
	private static MemberDAO daoMember = App.getDbi().open(MemberDAO.class);

	public ProjectDBRessource() {
		try {
			daoProject.createProjectTable();
		} catch (Exception e) {
			System.out.println("Table déjà là !");
		}
	}
	
	
	@POST
	@Path("/{pseudo}")
	public Project createProject(@PathParam("pseudo") String pseudo, Project project) {
		project.addMember(this.daoMember.findByPseudo(pseudo));
		this.daoProject.insert(project);
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
		Project update = this.daoProject.findProjectByIdp(id);
		if (update == null) {
			throw new WebApplicationException(404);
		}
		update.update(project);
		return Response.status(200).entity(update).build();
	}
	
	@GET
	@Path("/{id}")
	public Project getProject(@PathParam("id") int id ) {
		Project p = this.daoProject.findProjectByIdp(id);
		if (p == null) {
			throw new WebApplicationException(404);
		}
		return p;
	}
	
	@GET
	public List<Project> getProjects(@PathParam("pseudo") String pseudo) {
		return this.daoMember.getProjects(pseudo);
	}

}