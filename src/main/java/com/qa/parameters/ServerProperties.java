package com.qa.parameters;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.print.attribute.standard.Severity;

public class ServerProperties implements Parameter  {

	private Properties serverProp = null;
	
	
	/*
	 *  below are for remote connection
	 */
	private String platform = null;
	
	private String userName = null;
	
	private String remotePrivateKeyPath = null;
	
	private String remotePrivateKeyPassword = null;
	
	private String remoteKnowHostsPath = null;
	
	private String remoteInstanceIP = null;
	
	private String remoteEMRMasterURL = null;
	
	private String remoteSshPort = null;
	
	private String remoteUploadFilePath = null;
	
	private String instancePrivateKeyPath = null;
	
	private String instancePrivateKeyPassword = null;

	private String automationToolPath = null;
	
	
	public ServerProperties() throws Exception{
		super();
		this.serverProp = new Properties();
		
	}
	
	public void initProperties() throws Exception{
		InputStream input = new FileInputStream(propertyFilePath);
		serverProp.load(input);
		
		this.platform = serverProp.getProperty("platform");
		this.userName = serverProp.getProperty("userName");
		this.remotePrivateKeyPath = serverProp.getProperty("remotePrivateKeyPath");
		this.remotePrivateKeyPassword = serverProp.getProperty("remotePrivateKeyPassword");
		this.remoteKnowHostsPath = serverProp.getProperty("remoteKnowHostsPath");
		this.remoteInstanceIP = serverProp.getProperty("remoteInstanceIP");
		this.remoteSshPort = serverProp.getProperty("remoteSshPort");
		this.remoteEMRMasterURL = serverProp.getProperty("remoteEMRMasterURL");
		this.remoteUploadFilePath = serverProp.getProperty("remoteUploadFilePath");
		this.instancePrivateKeyPath = serverProp.getProperty("instancePrivateKeyPath");
		this.instancePrivateKeyPassword = serverProp.getProperty("instancePrivateKeyPassword");
		this.automationToolPath = serverProp.getProperty("automationToolPath");			
		
	}
	
	public Properties getServerProp() {
		return serverProp;
	}
	
	public String getPlatform() {
		return platform;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getRemotePrivateKeyPath() {
		return remotePrivateKeyPath;
	}
	
	public String getRemotePrivateKeyPassword() {
		return remotePrivateKeyPassword;
	}
	
	public String getRemoteKnowHostsPath() {
		return remoteKnowHostsPath;
	}

	public String getRemoteInstanceIP() {
		return remoteInstanceIP;
	}
	
	public String getRemoteSshPort() {
		return remoteSshPort;
	}
	
	public String getRemoteEMRMasterURL(){
		return remoteEMRMasterURL;
	}
	
	public String getRemoteUploadFilePath() {
		return remoteUploadFilePath;
	}
	
	public String getInstancePrivateKeyPath() {
		return instancePrivateKeyPath;
	}
	
	public String getInstancePrivateKeyPassword() {
		return instancePrivateKeyPassword;
	}

	public String getAutomationToolPath() { return automationToolPath; }
	
}
