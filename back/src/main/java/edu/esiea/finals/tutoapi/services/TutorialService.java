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

	private static final Logger logger = LogManager.getLogger(TutorialService.class);

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllTutorials() {
		logger.info("getAllTutorials called");
		try {
			final List<Tutorial> tutorials = DAOFactory.getTutorialDAO().readAll();
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorials fetched successfully", tutorials);
			logger.info("200 OK - Tutorials fetched successfully");
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			final String entity = JSONResponseBuilder.createErrorResponse("An error occurred while fetching all tutorials", e);
			logger.error("500 Internal Server Error - An error occurred while fetching all tutorials", e);
			return Response.serverError().entity(entity).build();
		}
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTutorialById(@PathParam("id") int id) {
		logger.info("getTutorialById id {} called", id);
		try {
			final Tutorial tutorial = DAOFactory.getTutorialDAO().read(id);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial fetched successfully", tutorial);
			logger.info("200 OK - Tutorial fetched successfully");
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			final String entity = JSONResponseBuilder
					.createErrorResponse("An error occurred while fetching tutorial id " + id, e);
			logger.error("500 Internal Server Error - An error occurred while fetching tutorial id {}", id, e);
			return Response.serverError().entity(entity).build();
		}
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addTutorial(Tutorial tutorial) {
		logger.info("addTutorial called");
		try {
			DAOFactory.getTutorialDAO().create(tutorial);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial added successfully", tutorial);
			logger.info("200 OK - Tutorial added successfully");
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			final String entity = JSONResponseBuilder.createErrorResponse("An error occurred while adding tutorial", e);
			logger.error("500 Internal Server Error - An error occurred while adding tutorial", e);
			return Response.serverError().entity(entity).build();
		}
	}

	@PUT
	@Path("/update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateTutorial(Tutorial tutorial, @PathParam("id") int id) {
		logger.info("updateTutorial id {} called", id);
		try {
			tutorial.setId(id);
			DAOFactory.getTutorialDAO().update(tutorial);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial updated successfully", tutorial);
			logger.info("200 OK - Tutorial updated successfully");
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			final String entity = JSONResponseBuilder
					.createErrorResponse("An error occurred while updating tutorial id " + id, e);
			logger.error("500 Internal Server Error - An error occurred while updating tutorial id {}", id, e);
			return Response.serverError().entity(entity).build();
		}
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteTutorial(@PathParam("id") int id) {
		logger.info("deleteTutorial id {} called", id);
		try {
			DAOFactory.getTutorialDAO().delete(id);
			final String entity = JSONResponseBuilder.createSuccessResponse("Tutorial deleted successfully", null);
			logger.info("200 OK - Tutorial deleted successfully");
			return Response.ok().entity(entity).build();
		} catch (Exception e) {
			final String entity = JSONResponseBuilder
					.createErrorResponse("An error occurred while deleting tutorial id " + id, e);
			logger.error("500 Internal Server Error - An error occurred while deleting tutorial id {}", id, e);
			return Response.serverError().entity(entity).build();
		}
	}
}
