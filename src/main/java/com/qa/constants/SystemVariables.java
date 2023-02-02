package com.qa.constants;

public enum SystemVariables {

	BUILD_NUMBER("BUILD_NUMBER"),
	FEATURE("FEATURE"),
	ENVIRONMENT_URL("ENVIRONMENT_URL"),
	USERNAME("USERNAME"),
	PASSWORD("PASSWORD"),
	EMAIL_TO("EMAIL_TO");

	String systemProperty;

	SystemVariables(String systemProperty) {
		this.systemProperty = System.getProperty(systemProperty);
	}


	@Override
	public String toString() {
		return systemProperty;
	}
}
