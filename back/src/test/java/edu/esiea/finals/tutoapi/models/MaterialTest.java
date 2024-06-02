package edu.esiea.finals.tutoapi.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MaterialTest {

	private Material material;

    @BeforeEach
    public void setUp() {
        material = new Material();
    }

    @Test
    public void testGetSetId() {
        int id = 1;
        material.setId(id);
        assertEquals(id, material.getId());
    }

    @Test
    public void testGetSetName() {
        String name = "Material Name";
        material.setName(name);
        assertEquals(name, material.getName());
    }

    @Test
    public void testGetSetDescription() {
        String description = "Material Description";
        material.setDescription(description);
        assertEquals(description, material.getDescription());
    }

    @Test
    public void testGetSetPhoto() {
        String photo = "material_photo_base64";
        material.setPhoto(photo);
        assertEquals(photo, material.getPhoto());
    }

    @Test
    public void testGetSetQuantity() {
        Integer quantity = 100;
        material.setQuantity(quantity);
        assertEquals(quantity, material.getQuantity());
    }

    @Test
    public void testGetSetUnit() {
        String unit = "kg";
        material.setUnit(unit);
        assertEquals(unit, material.getUnit());
    }

}
