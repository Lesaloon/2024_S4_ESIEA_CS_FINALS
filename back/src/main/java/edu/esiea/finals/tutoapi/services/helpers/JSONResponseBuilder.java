package edu.esiea.finals.tutoapi.services.helpers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResponseBuilder {
	Map<String, Object> response = new HashMap<String, Object>();

	public JSONResponseBuilder() {

		this.response.put("success", true);
		this.response.put("message", "no message provided");
		this.response.put("payload", Arrays.asList());
		this.response.put("errors", null);
	}

	public JSONResponseBuilder setSuccess(boolean status) {
		this.response.put("success", status);
		return this;
	}

	public JSONResponseBuilder setMessage(String message) {
		this.response.put("message", message);
		return this;
	}

	public JSONResponseBuilder setPayload(Object payload) {

		// if the payload is a single object, we need to put it in a list
		if (!(payload instanceof Iterable<?>)) {
			payload = Arrays.asList(payload);
		}

		this.response.put("payload", payload);
		return this;
	}

	public JSONResponseBuilder setErrors(Object errors) {
		this.response.put("errors", errors);
		return this;
	}

	public String build() {
		return this.getJSON();
	}


	public String getJSON() {
		final ObjectMapper objectMapper = new ObjectMapper();
		try {
			return objectMapper.writeValueAsString(this.response);
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Map<String, Object> getResponse() {
		return this.response;
	}

	public JSONResponseBuilder setResponse(Map<String, Object> response) {
		this.response = response;
		return this;
	}

	public JSONResponseBuilder addResponse(String key, Object value) {
		this.response.put(key, value);
		return this;
	}

	public static String createErrorResponse(String message) {
		return new JSONResponseBuilder().setSuccess(false).setErrors(true).setMessage(message).build();
	}

	public static String createErrorResponse(String message, Object payload) {
		return new JSONResponseBuilder().setSuccess(false).setErrors(true).setMessage(message).setPayload(payload)
				.build();
	}

	public static String createSuccessResponse(String message) {
		return new JSONResponseBuilder().setSuccess(true).setMessage(message).build();
	}

	public static String createSuccessResponse(String message, Object payload) {
		return new JSONResponseBuilder().setSuccess(true).setMessage(message).setPayload(payload).build();
	}


}


