package edu.esiea.finals.tutoapi.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.esiea.finals.tutoapi.models.enums.DifficultyLevel;
import edu.esiea.finals.tutoapi.models.enums.Type;

class TutorialTest {

    private Tutorial tutorial;
    
    
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		tutorial = new Tutorial();
	}

	@Test
    public void testGetSetId() {
        int id = 1;
        tutorial.setId(id);
        assertEquals(id, tutorial.getId());
    }

    @Test
    public void testGetSetTitle() {
        String title = "Test Title";
        tutorial.setTitle(title);
        assertEquals(title, tutorial.getTitle());
    }

    @Test
    public void testGetSetType() {
        Type type = Type.DIY;
        tutorial.setType(type);
        assertEquals(type, tutorial.getType());
    }

    @Test
    public void testGetSetDescription() {
        String description = "Test Description";
        tutorial.setDescription(description);
        assertEquals(description, tutorial.getDescription());
    }

    @Test
    public void testGetSetTools() {
        String tools = "Hammer, Nails";
        tutorial.setTools(tools);
        assertEquals(tools, tutorial.getTools());
    }

    @Test
    public void testGetSetToolsPhoto() {
        String toolsPhoto = "tools_photo.png";
        tutorial.setToolsPhoto(toolsPhoto);
        assertEquals(toolsPhoto, tutorial.getToolsPhoto());
    }

    @Test
    public void testGetSetMaterials() {
        Material material1 = new Material();
        Material material2 = new Material();
        List<Material> materials = Arrays.asList(material1, material2);
        tutorial.setMaterials(materials);
        assertEquals(materials, tutorial.getMaterials());
    }

    @Test
    public void testGetSetPhoto() {
        String photo = "tutorial_photo.png";
        tutorial.setPhoto(photo);
        assertEquals(photo, tutorial.getPhoto());
    }

    @Test
    public void testGetSetTimeToComplete() {
        Integer timeToComplete = 120;
        tutorial.setTimeToComplete(timeToComplete);
        assertEquals(timeToComplete, tutorial.getTimeToComplete());
    }

    @Test
    public void testGetSetCost() {
        Double cost = 19.99;
        tutorial.setCost(cost);
        assertEquals(cost, tutorial.getCost());
    }

    @Test
    public void testGetSetDifficultyLevel() {
        DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
        tutorial.setDifficultyLevel(difficultyLevel);
        assertEquals(difficultyLevel, tutorial.getDifficultyLevel());
    }

    @Test
    public void testGetSetSteps() {
        Step step1 = new Step();
        Step step2 = new Step();
        List<Step> steps = Arrays.asList(step1, step2);
        tutorial.setSteps(steps);
        assertEquals(steps, tutorial.getSteps());
    }

}
