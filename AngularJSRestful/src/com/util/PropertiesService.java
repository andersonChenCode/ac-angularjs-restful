package com.util;

public interface PropertiesService {
	public String getCerberusDriverClassName();
	public void setCerberusDriverClassName(String cerberusDriverClassName);
	public String getCerberusPassword();
	public void setCerberusPassword(String cerberusPassword);
	public String getCerberusUrl();
	public void setCerberusUrl(String cerberusUrl);
	public String getCerberusUsername();
	public void setCerberusUsername(String cerberusUsername);
	
	public String getTavfDriverClassName();
	public void setTavfDriverClassName(String tavfDriverClassName);
	public String getTavfPassword();
	public void setTavfPassword(String tavfPassword);
	public String getTavfUrl();
	public void setTavfUrl(String tavfUrl);
	public String getTavfUsername();
	public void setTavfUsername(String tavfUsername);
	
	public String getTccgDriverClassName();
	public void setTccgDriverClassName(String tccgDriverClassName);
	public String getTccgPassword();
	public void setTccgPassword(String tccgPassword);
	public String getTccgUrl();
	public void setTccgUrl(String tccgUrl);
	public String getTccgUsername();
	public void setTccgUsername(String tccgUsername);
}
