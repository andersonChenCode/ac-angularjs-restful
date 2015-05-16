package com.sap.model.bo;

import javax.xml.bind.annotation.XmlRootElement;  

@XmlRootElement  
public class User {  

    private Integer userId;  
    private String userName;  
    private String age;  

    public User() {};  

    public User(Integer userId, String userName, String age) {  
        this.userId = userId;  
        this.userName = userName;  
        this.age = age;  
    }  
    public Integer getUserId() {  
        return userId;  
    }  
    public void setUserId(Integer userId) {  
        this.userId = userId;  
    }  
    public String getUserName() {  
        return userName;  
    }  
    public void setUserName(String userName) {  
        this.userName = userName;  
    }  
    public String getAge() {  
        return age;  
    }  
    public void setAge(String age) {  
        this.age = age;  
    }  
}  