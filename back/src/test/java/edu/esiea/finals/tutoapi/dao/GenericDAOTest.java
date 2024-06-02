package edu.esiea.finals.tutoapi.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.esiea.finals.tutoapi.interfaces.IDAO;
import edu.esiea.finals.tutoapi.models.Step;
import edu.esiea.finals.tutoapi.models.Tutorial;
import edu.esiea.finals.tutoapi.models.enums.DifficultyLevel;

class GenericDAOTest {

	private static IDAO<Tutorial> tutorialDAO;

	private static final String TITLE = "Test";
	private static final String DESCRIPTION = "Description";
	private static final Double COST = 100.0;
	private static final Integer TIME_TO_COMPLETE = 120;
	private static final DifficultyLevel DIFFICULTY_LEVEL = DifficultyLevel.EASY;
	private static final String TOOLS = "Tools";

	private static final Step STEP = new Step();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DAOBDDHelper.setInstance("TutoPU-test");
		tutorialDAO = new GenericDAO<Tutorial>(Tutorial.class);
		STEP.setTitle("Step 1");
		STEP.setDescription("Description");
		STEP.setPhoto("Photo in base64");
	}

	@BeforeEach
	void setUp() throws Exception {
		List<Tutorial> tutoList = tutorialDAO.readAll();
		for (Tutorial tuto : tutoList) {
			tutorialDAO.delete(tuto);
		}
	}

	@Test
	void testCreate() throws Exception {
		Tutorial tuto = new Tutorial();
		tuto.setTitle(TITLE);
		tuto.setDescription(DESCRIPTION);
		tuto.setCost(COST);
		tuto.setTimeToComplete(TIME_TO_COMPLETE);
		tuto.setDifficultyLevel(DIFFICULTY_LEVEL);
		tuto.setTools(TOOLS);
		tuto.setSteps(Arrays.asList(STEP));
		Tutorial tutoCreated = tutorialDAO.create(tuto);
		assertNotNull(tutoCreated);
		assertEquals(TITLE, tutoCreated.getTitle());
		assertEquals(DESCRIPTION, tutoCreated.getDescription());
		assertEquals(COST, tutoCreated.getCost());
		assertEquals(TIME_TO_COMPLETE, tutoCreated.getTimeToComplete());
		assertEquals(DIFFICULTY_LEVEL, tutoCreated.getDifficultyLevel());
		assertEquals(TOOLS, tutoCreated.getTools());
		assertEquals(1, tutoCreated.getSteps().size());
		assertEquals(STEP, tutoCreated.getSteps().get(0));
	}

	@Test
	void testRead() throws Exception {
		Tutorial tuto = new Tutorial();
		tuto.setTitle(TITLE);
		tuto.setDescription(DESCRIPTION);
		tuto.setCost(COST);
		tuto.setTimeToComplete(TIME_TO_COMPLETE);
		tuto.setDifficultyLevel(DIFFICULTY_LEVEL);
		tuto.setTools(TOOLS);
		tuto.setSteps(Arrays.asList(STEP));
		Tutorial tutoCreated = tutorialDAO.create(tuto);
		assertNotNull(tutoCreated);
		assertNotNull(tutoCreated.getId());
		Tutorial readTuto = tutorialDAO.read(tutoCreated.getId());

		assertNotNull(readTuto);
		assertEquals(tutoCreated.getId(), readTuto.getId());
		assertEquals(TITLE, readTuto.getTitle());
		assertEquals(DESCRIPTION, readTuto.getDescription());
		assertEquals(COST, readTuto.getCost());
		assertEquals(TIME_TO_COMPLETE, readTuto.getTimeToComplete());
		assertEquals(DIFFICULTY_LEVEL, readTuto.getDifficultyLevel());
		assertEquals(TOOLS, readTuto.getTools());
		assertEquals(1, readTuto.getSteps().size());
		assertEquals(STEP, readTuto.getSteps().get(0));
	}
	
	@Test
	public void testReadAll() throws Exception {
		List<Tutorial> tutoList = new ArrayList<>();
		for (int i = 0; i < 50; i++) {
			Tutorial tuto = new Tutorial();
			tuto.setTitle(TITLE + " " + i);
			tuto.setDescription(DESCRIPTION + " " + i);
			tuto.setCost(COST + i);
			tuto.setTimeToComplete(TIME_TO_COMPLETE + i);
			tuto.setDifficultyLevel(DIFFICULTY_LEVEL);
			tuto.setTools(TOOLS + " " + i);
			for (int j = 0; j < 5; j++) {
				Step step = new Step();
				step.setTitle("Step " + j);
				step.setDescription("Description " + j);
				step.setPhoto("Photo in base64 " + j);
				List<Step> steps = tuto.getSteps() != null ? tuto.getSteps() : new ArrayList<>();
				steps.add(step);
				tuto.setSteps(steps);
			}
			tutoList.add(tuto);
		}
		for (Tutorial tuto : tutoList) {
			tutorialDAO.create(tuto);
		}
		
		List<Tutorial> readTutoList = tutorialDAO.readAll();
		
		assertNotNull(readTutoList);
		assertEquals(50, readTutoList.size());
		for (int i = 0; i < 50; i++) {
			Tutorial tuto = readTutoList.get(i);
			assertEquals(TITLE + " " + i, tuto.getTitle());
			assertEquals(DESCRIPTION + " " + i, tuto.getDescription());
			assertEquals(COST + i, tuto.getCost());
			assertEquals(TIME_TO_COMPLETE + i, tuto.getTimeToComplete());
			assertEquals(DIFFICULTY_LEVEL, tuto.getDifficultyLevel());
			assertEquals(TOOLS + " " + i, tuto.getTools());
			assertEquals(5, tuto.getSteps().size());
		}		
	}
	
	@Test
	public void testSearch() throws Exception {
        Tutorial tutorial1 = new Tutorial();
        tutorial1.setTitle("JUnit Testing");
        Tutorial tutorial2 = new Tutorial();
        tutorial2.setTitle("Mocking");
        
        tutorialDAO.bulkCreate(Arrays.asList(tutorial1, tutorial2));
        List<Tutorial> foundTutorials = tutorialDAO.search(t -> t.getTitle().contains("JUnit"));
        assertEquals(1, foundTutorials.size());
    }
	
	@Test
	public void testUpdate() throws Exception {
        Tutorial tutorial = new Tutorial();
        tutorial.setTitle(TITLE);
        Tutorial createdTutorial = tutorialDAO.create(tutorial);
        
        createdTutorial.setTitle("Updated " + TITLE);
        tutorialDAO.update(createdTutorial);
        
        Tutorial updatedTutorial = tutorialDAO.read(createdTutorial.getId());
        assertEquals("Updated " + TITLE, updatedTutorial.getTitle());
    }
	
	@Test
	public void testBulkUpdate() throws Exception {
		Tutorial tutorial1 = new Tutorial();
		tutorial1.setTitle(TITLE);
		Tutorial tutorial2 = new Tutorial();
		tutorial2.setTitle(TITLE);

		tutorialDAO.bulkCreate(Arrays.asList(tutorial1, tutorial2));

		tutorial1.setTitle("Updated " + TITLE);
		tutorial2.setTitle("Updated " + TITLE);

		tutorialDAO.bulkUpdate(Arrays.asList(tutorial1, tutorial2));

		List<Tutorial> updatedTutorials = tutorialDAO.readAll();
		assertEquals("Updated " + TITLE, updatedTutorials.get(0).getTitle());
		assertEquals("Updated " + TITLE, updatedTutorials.get(1).getTitle());
	}
	
	@Test
	public void testDelete() throws Exception {
		Tutorial tutorial = new Tutorial();
		tutorial.setTitle(TITLE);
		Tutorial createdTutorial = tutorialDAO.create(tutorial);
		tutorialDAO.delete(createdTutorial);
		assertNull(tutorialDAO.read(createdTutorial.getId()));
	}
	
	@Test
	public void testDeleteById() throws Exception {
		Tutorial tutorial = new Tutorial();
		tutorial.setTitle(TITLE);
		Tutorial createdTutorial = tutorialDAO.create(tutorial);
		tutorialDAO.delete(createdTutorial.getId());
		assertNull(tutorialDAO.read(createdTutorial.getId()));
	}
	
	@Test
	public void testBulkDelete() throws Exception {
		Tutorial tutorial1 = new Tutorial();
		tutorial1.setTitle(TITLE);
		Tutorial tutorial2 = new Tutorial();
		tutorial2.setTitle(TITLE);

		tutorialDAO.bulkCreate(Arrays.asList(tutorial1, tutorial2));
		tutorialDAO.bulkDelete(Arrays.asList(tutorial1, tutorial2));

		List<Tutorial> tutorials = tutorialDAO.readAll();
		assertEquals(0, tutorials.size());
	}
	
	@Test
	public void testBulkDeleteByIds() throws Exception {
		Tutorial tutorial1 = new Tutorial();
		tutorial1.setTitle(TITLE);
		Tutorial tutorial2 = new Tutorial();
		tutorial2.setTitle(TITLE);

		tutorialDAO.bulkCreate(Arrays.asList(tutorial1, tutorial2));
		int[] ids = {tutorial1.getId(), tutorial2.getId()};
		tutorialDAO.bulkDelete(ids);

		List<Tutorial> tutorials = tutorialDAO.readAll();
		assertEquals(0, tutorials.size());
	}
}
