package edu.esiea.finals.tutoapi.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.esiea.finals.tutoapi.models.Step;
import edu.esiea.finals.tutoapi.models.Tutorial;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tutorials")
public class TutorialService {

	// TODO : Configure logger in the log4j2.xml file
	private static final Logger logger = LogManager.getLogger(TutorialService.class);
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTutorials() {
		return Response.ok().entity("Hello World").build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTutorialById(@PathParam("id") int id) {
		return Response.ok().entity("Hello World").build();
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTutorial(Tutorial tutorial) {
		return Response.ok().entity("Hello World").build();
	}

	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTutorial(Tutorial tutorial) {
		return Response.ok().entity("Hello World").build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTutorial(@PathParam("id") int id) {
		return Response.ok().entity("Hello World").build();
	}

	@GET
	@Path("/{id}/steps")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStepsByTutorialId(@PathParam("id") int id) {
		return Response.ok().entity("Hello World").build();
	}

	@GET
	@Path("/{id}/materials")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaterialsByTutorialId(@PathParam("id") int id) {
		return Response.ok().entity("Hello World").build();
	}

	@GET
	@Path("/{id}/materials/{materialId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMaterialByTutorialIdAndMaterialId(@PathParam("id") int id,
			@PathParam("materialId") int materialId) {
		return Response.ok().entity("Hello World").build();
	}

	@GET
	@Path("/{id}/steps/{stepId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStepByTutorialIdAndStepId(@PathParam("id") int id, @PathParam("stepId") int stepId) {
		return Response.ok().entity("Hello World").build();
	}

	@POST
    @Path("/{id}/steps/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStepToTutorial(@PathParam("id") int id, Step step) {
        return Response.ok().entity("Hello World").build();
	}
}
