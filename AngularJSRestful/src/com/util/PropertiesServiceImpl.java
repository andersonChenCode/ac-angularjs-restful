package com.util;

public class PropertiesServiceImpl implements PropertiesService{
	
	private String cerberusUrl;
	private String cerberusUsername;
	private String cerberusPassword;
	private String cerberusDriverClassName;
	
	private String tavfUrl;
	private String tavfUsername;
	private String tavfPassword;
	private String tavfDriverClassName;
	
	private String tccgUrl;
	private String tccgUsername;
	private String tccgPassword;
	private String tccgDriverClassName;
	
	public String getCerberusDriverClassName() {
		return cerberusDriverClassName;
	}

	public void setCerberusDriverClassName(String cerberusDriverClassName) {
		this.cerberusDriverClassName = cerberusDriverClassName;
	}

	public String getCerberusPassword() {
		return cerberusPassword;
	}

	public void setCerberusPassword(String cerberusPassword) {
		this.cerberusPassword = cerberusPassword;
	}

	public String getCerberusUrl() {
		return cerberusUrl;
	}

	public void setCerberusUrl(String cerberusUrl) {
		this.cerberusUrl = cerberusUrl;
	}

	public String getCerberusUsername() {
		return cerberusUsername;
	}

	public void setCerberusUsername(String cerberusUsername) {
		this.cerberusUsername = cerberusUsername;
	}

	public String getTavfDriverClassName() {
		return tavfDriverClassName;
	}

	public void setTavfDriverClassName(String tavfDriverClassName) {
		this.tavfDriverClassName = tavfDriverClassName;
	}

	public String getTavfPassword() {
		return tavfPassword;
	}

	public void setTavfPassword(String tavfPassword) {
		this.tavfPassword = tavfPassword;
	}

	public String getTavfUrl() {
		return tavfUrl;
	}

	public void setTavfUrl(String tavfUrl) {
		this.tavfUrl = tavfUrl;
	}

	public String getTavfUsername() {
		return tavfUsername;
	}

	public void setTavfUsername(String tavfUsername) {
		this.tavfUsername = tavfUsername;
	}
	public String getTccgDriverClassName() {
		return tccgDriverClassName;
	}

	public void setTccgDriverClassName(String tccgDriverClassName) {
		this.tccgDriverClassName = tccgDriverClassName;
	}

	public String getTccgPassword() {
		return tccgPassword;
	}

	public void setTccgPassword(String tccgPassword) {
		this.tccgPassword = tccgPassword;
	}

	public String getTccgUrl() {
		return tccgUrl;
	}

	public void setTccgUrl(String tccgUrl) {
		this.tccgUrl = tccgUrl;
	}

	public String getTccgUsername() {
		return tccgUsername;
	}

	public void setTccgUsername(String tccgUsername) {
		this.tccgUsername = tccgUsername;
	}
}
