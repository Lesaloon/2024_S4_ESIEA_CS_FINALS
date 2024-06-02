package edu.esiea.finals.tutoapi.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StepTest {

	private Step step;

    @BeforeEach
    public void setUp() {
        step = new Step();
    }

    @Test
    public void testGetSetId() {
        int id = 1;
        step.setId(id);
        assertEquals(id, step.getId());
    }

    @Test
    public void testGetSetTitle() {
        String title = "Step Title";
        step.setTitle(title);
        assertEquals(title, step.getTitle());
    }

    @Test
    public void testGetSetSequenceNumber() {
        Integer sequenceNumber = 1;
        step.setSequenceNumber(sequenceNumber);
        assertEquals(sequenceNumber, step.getSequenceNumber());
    }

    @Test
    public void testGetSetDescription() {
        String description = "Step Description";
        step.setDescription(description);
        assertEquals(description, step.getDescription());
    }

    @Test
    public void testGetSetPhoto() {
        String photo = "step_photo_base64";
        step.setPhoto(photo);
        assertEquals(photo, step.getPhoto());
    }
}
