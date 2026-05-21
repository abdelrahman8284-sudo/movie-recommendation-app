package com.movie.backend.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)// لو ظهر null تمنعه
public class ApiResponse {

	private boolean success;
	private String message;
	private Object data;
	public ApiResponse(boolean success, String message, Object data) {
		super();
		this.success = success;
		this.message = message;
		this.data = data;
	}
	public ApiResponse(String message, Object data) {
		super();
		this.message = message;
		this.data = data;
	}
	public ApiResponse(boolean success, String message) {
		super();
		this.success = success;
		this.message = message;
	}
	
	
}
