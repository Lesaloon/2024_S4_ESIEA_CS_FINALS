package edu.esiea.finals.tutoapi.services;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class rootService {
	
	@GET
	@Path("/test")
	public Response getRoot() {
		return Response.ok().entity("Hello World").build();
	}
}
