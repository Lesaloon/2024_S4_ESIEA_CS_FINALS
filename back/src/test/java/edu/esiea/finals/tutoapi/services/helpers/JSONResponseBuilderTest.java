package edu.esiea.finals.tutoapi.services.helpers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

class JSONResponseBuilderTest {

	 @Test
	    public void testDefaultResponse() {
	        JSONResponseBuilder builder = new JSONResponseBuilder();
	        Map<String, Object> response = builder.getResponse();

	        assertTrue((Boolean) response.get("success"));
	        assertEquals("no message provided", response.get("message"));
	        assertTrue(((List<?>) response.get("payload")).isEmpty());
	        assertNull(response.get("errors"));
	    }

	    @Test
	    public void testSetSuccess() {
	        JSONResponseBuilder builder = new JSONResponseBuilder().setSuccess(false);
	        Map<String, Object> response = builder.getResponse();

	        assertFalse((Boolean) response.get("success"));
	    }

	    @Test
	    public void testSetMessage() {
	        String message = "Test message";
	        JSONResponseBuilder builder = new JSONResponseBuilder().setMessage(message);
	        Map<String, Object> response = builder.getResponse();

	        assertEquals(message, response.get("message"));
	    }

	    @Test
	    public void testSetPayloadSingleObject() {
	        String payload = "Single payload";
	        JSONResponseBuilder builder = new JSONResponseBuilder().setPayload(payload);
	        Map<String, Object> response = builder.getResponse();

	        assertEquals(1, ((List<?>) response.get("payload")).size());
	        assertEquals(payload, ((List<?>) response.get("payload")).get(0));
	    }

	    @Test
	    public void testSetPayloadIterable() {
	        List<String> payload = Arrays.asList("payload1", "payload2");
	        JSONResponseBuilder builder = new JSONResponseBuilder().setPayload(payload);
	        Map<String, Object> response = builder.getResponse();

	        assertEquals(payload, response.get("payload"));
	    }

	    @Test
	    public void testSetErrors() {
	        String error = "Test error";
	        JSONResponseBuilder builder = new JSONResponseBuilder().setErrors(error);
	        Map<String, Object> response = builder.getResponse();

	        assertEquals(error, response.get("errors"));
	    }

	    @Test
	    public void testBuild() throws Exception {
	        String message = "Build message";
	        String payload = "Build payload";
	        JSONResponseBuilder builder = new JSONResponseBuilder().setMessage(message).setPayload(payload);

	        String jsonString = builder.build();

	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> jsonMap = objectMapper.readValue(jsonString, Map.class);

	        assertEquals(true, jsonMap.get("success"));
	        assertEquals(message, jsonMap.get("message"));
	        assertEquals(1, ((List<?>) jsonMap.get("payload")).size());
	        assertEquals(payload, ((List<?>) jsonMap.get("payload")).get(0));
	        assertNull(jsonMap.get("errors"));
	    }

	    @Test
	    public void testCreateErrorResponse() {
	        String message = "Error message";
	        String json = JSONResponseBuilder.createErrorResponse(message);

	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> jsonMap;
	        try {
	            jsonMap = objectMapper.readValue(json, Map.class);

	            assertEquals(false, jsonMap.get("success"));
	            assertEquals(message, jsonMap.get("message"));
	            assertTrue((Boolean) jsonMap.get("errors"));
	        } catch (Exception e) {
	            fail("Failed to parse JSON");
	        }
	    }

	    @Test
	    public void testCreateSuccessResponse() {
	        String message = "Success message";
	        String json = JSONResponseBuilder.createSuccessResponse(message);

	        ObjectMapper objectMapper = new ObjectMapper();
	        Map<String, Object> jsonMap;
	        try {
	            jsonMap = objectMapper.readValue(json, Map.class);

	            assertEquals(true, jsonMap.get("success"));
	            assertEquals(message, jsonMap.get("message"));
	            assertNull(jsonMap.get("errors"));
	        } catch (Exception e) {
	            fail("Failed to parse JSON");
	        }
	    }

}
