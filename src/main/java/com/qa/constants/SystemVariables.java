package com.qa.constants;

public enum SystemVariables {

	BUILD_NUMBER("BUILD_NUMBER"),
	FEATURE("FEATURE");

	String systemProperty;

	SystemVariables(String systemProperty) {
		this.systemProperty = System.getProperty(systemProperty);
	}


	@Override
	public String toString() {
		return systemProperty;
	}
}
