package com.fabiogontijo.bank_management_api.core.exception.model;

public enum MessageType {

	Conflict_Error("The identifier that already exists."),

	Business_Logic_Error("Business logic error."),

	Internal_Architecture_Error("Ooops! some big problem found."),

	Resource_Not_Found("Resource not found.");

	private final String description;

	MessageType(final String des) {
		description = des;
	}

	public String getDescription() {
		return description;
	}

}
