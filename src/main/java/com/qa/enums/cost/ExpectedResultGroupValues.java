package com.qa.enums.cost;

public enum ExpectedResultGroupValues {
	
	ROOT("root"),
	USER("smananghat@unraveldata.com"),
	AI("AI Team"),
	ML("ML team"),
	PG_III("4730_PG_III"),
	PROD("Production"),
	AI_WORKSPACE("AI_Workspace"),
	ML_WORKSPACE("ML_Workspace"),
	PG("4730_PG");
	
	 public String value;
	 ExpectedResultGroupValues(String value) {
	        this.value = value;
	    }

}
