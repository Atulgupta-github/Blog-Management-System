package com.blog.exceptions;

public class ResourceNotFoundException extends RuntimeException {

	
	String resourceName;
	String fieldName;
	long fieldValue;
	String field;
	
	public ResourceNotFoundException(String resourceName,String fieldName ,	long fieldValue) {
		super(String.format("%s not found with %s :%s", resourceName, fieldName, fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}

	public ResourceNotFoundException(String resourceName2, String fieldName2, String email) {
		super(String.format("%s not found with %s :%s", resourceName2, fieldName2, email));
		this.resourceName=resourceName2;
		this.fieldName=fieldName2;
		this.field=email;
	}
}
