package edu.esiea.finals.tutoapi.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class rootService {
	
	private static final Logger logger = LogManager.getLogger(rootService.class);
	
	@GET
	@Path("/test")
	public Response getRoot() {
		logger.info("getRoot called");
		return Response.ok().entity("Hello World").build();
	}
}
