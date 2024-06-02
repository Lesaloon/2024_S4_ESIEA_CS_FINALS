package edu.esiea.finals.tutoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;

import java.net.URI;

class rootServiceTest extends JerseyTest {

	@Override
    protected Application configure() {
        return new ResourceConfig(rootService.class);
    }

    @Override
    protected URI getBaseUri() {
    	// 0 is a random port
        return URI.create("http://localhost:0/");
    }

    @Test
    public void testGetRoot() {
        WebTarget target = target("/test");
        Response response = target.request().get();

        assertEquals(200, response.getStatus(), "HTTP status should be 200");
        assertEquals("Hello World", response.readEntity(String.class), "Response message should be 'Hello World'");
    }

}
