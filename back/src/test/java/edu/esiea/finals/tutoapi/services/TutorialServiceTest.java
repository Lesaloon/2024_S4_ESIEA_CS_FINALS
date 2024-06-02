package edu.esiea.finals.tutoapi.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.esiea.finals.tutoapi.dao.DAOBDDHelper;
import edu.esiea.finals.tutoapi.dao.DAOFactory;
import edu.esiea.finals.tutoapi.interfaces.IDAO;
import edu.esiea.finals.tutoapi.models.Tutorial;


@TestInstance(Lifecycle.PER_CLASS)
class TutorialServiceTest extends JerseyTest {
	
	@Override
    protected Application configure() {
		try {
			DAOBDDHelper.setInstance("TutoPU-test");
		} catch (Exception e) {
		}
        return new ResourceConfig(TutorialService.class);
    }
	
    @Test
    public void testGetAllTutorials() throws Exception {
    	Tutorial tutorial = new Tutorial();
    	Tutorial tutorial2 = new Tutorial();
    	tutorial.setTitle("Test Tutorial");
    	tutorial2.setTitle("Test Tutorial 2");
    	IDAO<Tutorial> tutorialDAO = DAOFactory.getTutorialDAO();
    	tutorial = tutorialDAO.create(tutorial);
    	tutorial2 = tutorialDAO.create(tutorial2);
    	
        WebTarget target = target("/tutorials/all");
        Response response = target.request().get();

        assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        assertNotNull(json);
        // Parse the Json into a list of tutorials and check if the list is not empty
        apiResponseShema<Tutorial> ObjectResponse = new ObjectMapper()
        		.readValue(json, new TypeReference<apiResponseShema<Tutorial>>() {});
        System.out.println(json);
        List<Tutorial> tutorials = ObjectResponse.payload;
        assertFalse(tutorials.isEmpty());
        
        assertEquals(2, tutorials.size());
        assertEquals("Test Tutorial", tutorials.get(0).getTitle());
        assertEquals("Test Tutorial 2", tutorials.get(1).getTitle());
        
        // remove the tutorials
        tutorialDAO.delete(tutorialDAO.readAll().get(0).getId());
        tutorialDAO.delete(tutorialDAO.readAll().get(0).getId());        
    }

    @Test
    public void testGetTutorialById() throws Exception {
    	
    	Tutorial tutorial = new Tutorial();
    	tutorial.setTitle("Test Tutorial");
    	IDAO<Tutorial> tutorialDAO = DAOFactory.getTutorialDAO();
    	tutorial = tutorialDAO.create(tutorial);
    	// get the tutorial id
    	int id = tutorial.getId();
    	
        WebTarget target = target("/tutorials/" + id);
        Response response = target.request().get();

        assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        assertNotNull(json);     
    }

    @Test
    public void testAddTutorial() throws Exception {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Test Tutorial");

        WebTarget target = target("/tutorials/add");
        Response response = target.request()
                                  .post(Entity.entity(tutorial, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        assertNotNull(json);
        
        // remove the tutorial
        IDAO<Tutorial> tutorialDAO = DAOFactory.getTutorialDAO();
        tutorialDAO.delete(tutorialDAO.readAll().get(0).getId());
        
    }

    @Test
    public void testUpdateTutorial() throws Exception {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle("Updated Tutorial");

        WebTarget target = target("/tutorials/update/1");
        Response response = target.request()
                                  .put(Entity.entity(tutorial, MediaType.APPLICATION_JSON));

        assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        assertNotNull(json);

        // Further assertions can be added based on the expected structure of the JSON response
        // remove the tutorial
        IDAO<Tutorial> tutorialDAO = DAOFactory.getTutorialDAO();
        tutorialDAO.delete(tutorialDAO.readAll().get(0).getId());
    }

    @Test
    public void testDeleteTutorial() {
        WebTarget target = target("/tutorials/1");
        Response response = target.request().delete();

        assertEquals(200, response.getStatus());
        String json = response.readEntity(String.class);
        assertNotNull(json);

        // Further assertions can be added based on the expected structure of the JSON response
    }

}
