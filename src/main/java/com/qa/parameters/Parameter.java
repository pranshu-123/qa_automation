package com.qa.parameters;

public interface Parameter {

	public static final String propertyFilePath = "./src/main/java/com/qa/config/config.properties";

	public static final String unravelURL_AWS_EMR_Cluster = "http://13.233.183.71:3000/";

	
	// public static final String platform = "remote";
	static final String hostname_ec2 = "3.7.252.12";
	static final String username_ec2 = "ec2-user";
	// static final String password="sshpwd";
	static final String privateKey_ec2 = "D:\\biru\\IrisLogic\\aws\\birenderIrisLogicHadoop.pem";
	static final String command_ec2 = "ls -ltr";

	static final String unravel_url = "http://tnode91.unraveldata.com:3000/#/app/applications/";
	static final String workflow_url = unravel_url+ "workflows";
	
	//cluster env details
	static final String hostname = "tnode91.unraveldata.com";
	static final String username = "root";
	static final String password = "unraveldata";
	static final String command_ls = "ls -ltr";
	
	static final String startDate = "08/05/2020";
	static final String endDate = "08/08/2020";
	
		
	static final String impalaResourceStartDate = "07/19/2020";
	static final String impalaResourceEndDate = "08/19/2020";
	
	static final String impalaResource2018StartDate = "01/01/2018";
	static final String impalaResource2018EndDate = "12/01/2018";

	static final String spark_submit_cmd1 = "sudo spark-submit --class org.apache.spark.examples.SparkPi --master yarn --deploy-mode cluster --num-executors 1 --driver-memory 2g --executor-memory 4g --executor-cores 1 /opt/cloudera/parcels/CDH-6.3.3-1.cdh6.3.3.p0.1796617/lib/spark/examples/jars/spark-examples_2.11-2.4.0-cdh6.3.3.jar 100";


}
