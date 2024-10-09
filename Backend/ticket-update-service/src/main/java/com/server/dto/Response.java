package com.server.dto;

import java.util.List;

public class Response {
	private List<String> message;

	public Response(List<String> message) {
		super();
		this.message = message;
	}

	public List<String> getMessage() {
		return message;
	}

	public void setMessage(List<String> message) {
		this.message = message;
	}
	
	

}
