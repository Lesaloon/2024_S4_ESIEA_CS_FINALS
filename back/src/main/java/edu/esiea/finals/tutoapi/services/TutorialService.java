package edu.esiea.finals.tutoapi.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import edu.esiea.finals.tutoapi.dao.DAOFactory;
import edu.esiea.finals.tutoapi.models.*;
import edu.esiea.finals.tutoapi.services.helpers.JSONResponseBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/tutorials")
public class TutorialService {
	
	// TODO : Configure logger in the log4j2.xml file
	private static final Logger logger = LogManager.getLogger(TutorialService.class);

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTutorials() {
		logger.info("Fetching all tutorials");
		try {
			final List<Tutorial> tutorials = DAOFactory.getTutorialDAO().readAll();
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorials fetched successfully", tutorials);
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			logger.error("An error occurred while fetching all tutorials", e);
			final String entity = JSONResponseBuilder.createErrorResponse("An error occurred while fetching all tutorials", e);
			return Response.serverError().entity(entity).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTutorialById(@PathParam("id") int id) {
		logger.info("Fetching tutorial id {}", id);
		try {
			final Tutorial tutorial = DAOFactory.getTutorialDAO().read(id);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial fetched successfully", tutorial);
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			logger.error("An error occurred while fetching tutorial id {}", id, e);
			final String entity = JSONResponseBuilder
					.createErrorResponse("An error occurred while fetching tutorial id " + id, e);
			return Response.serverError().entity(entity).build();
		}
	}
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTutorial(Tutorial tutorial) {
		try {
			DAOFactory.getTutorialDAO().create(tutorial);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial added successfully", tutorial);
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			logger.error("An error occurred while adding tutorial", e);
			final String entity = JSONResponseBuilder.createErrorResponse("An error occurred while adding tutorial", e);
			return Response.serverError().entity(entity).build();
		}
	}
		
	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTutorial(Tutorial tutorial, @PathParam("id") int id) {
		logger.info("Updating tutorial id {}", id);
		try {
			tutorial.setId(id);
			DAOFactory.getTutorialDAO().update(tutorial);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial updated successfully", tutorial);
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			logger.error("An error occurred while updating tutorial id {}", id, e);
			final String entity = JSONResponseBuilder
					.createErrorResponse("An error occurred while updating tutorial id " + id, e);
			return Response.serverError().entity(entity).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTutorial(@PathParam("id") int id) {
		logger.info("Deleting tutorial id {}", id);
		try {
			DAOFactory.getTutorialDAO().delete(id);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial deleted successfully", null);
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			logger.error("An error occurred while deleting tutorial id {}", id, e);
			final String entity = JSONResponseBuilder
					.createErrorResponse("An error occurred while deleting tutorial id " + id, e);
			return Response.serverError().entity(entity).build();
		}
	}
}
