package com.sap.model;



public class ServiceGetter {
	
	
	private static ServiceGetter instance;

    public ServiceGetter() {
        if(ServiceGetter.instance != null) {
            throw new RuntimeException(
                    this.getClass().getName()
                            + "is designed to be a Singleton, the instance already exist:"
                            + ServiceGetter.instance);
        }
        ServiceGetter.instance = this;
    }
    
	public static ServiceGetter getInstance() {
		return instance;
	}
	private com.sap.model.service.UserService UserService;
	public com.sap.model.service.UserService getUserService() {return UserService;}
	public void setUserService(com.sap.model.service.UserService UserService) {this.UserService = UserService;}
}
