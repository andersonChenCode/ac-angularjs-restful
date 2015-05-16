package com.sap.conn;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCo;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

public class SapUtilities {
	public static SapSystem getDefTestSystem(){
		return new SapSystem("DEV", "192.168.1.105", "710","00", "IT02", "d5379843","ZF","/H/saprouter2.swancor.com/H/");
	}
	public static SapSystem getLocalDefTestSystem(){
		return new SapSystem("DEV", "192.168.1.105", "710","00", "NW431", "d963852741","ZF");
	}	
	public static Object[] getJCoFunction(String BAPIName){
		SapSystem system = new SapSystem("DEV", "192.168.1.105", "300","00", "IT02", "5379843","ZF","/H/saprouter2.swancor.com/H/");
		Connection connect = new Connection(system);
		JCoFunction function = connect.getFunction(BAPIName);
		if (function == null) {
			System.out.println("The Function " + function.getName() + "is Not Found in SAP !");
		}
		return new Object[]{connect,function};
	}	
	public static Object[] getJCoFunction(SapSystem system,String BAPIName){
		Connection connect = new Connection(system);
		JCoFunction function = connect.getFunction(BAPIName);
		if (function == null) {
			System.out.println("The Function " + function.getName() + "is Not Found in SAP !");
		}
		return new Object[]{connect,function};
	}
	public static void showMsg(Connection connect,JCoTable returnStructure) throws JCoException{
        if(!(returnStructure.getString("TYPE").equals("") || returnStructure.getString("TYPE").equals("S"))){
    	    for (int i = 0; i < returnStructure.getNumRows(); i++){ 
    	    	 returnStructure.setRow(i); 
    	    	 String FIELD = returnStructure.getString("FIELD");
    	    	 String MESSAGE = returnStructure.getString("MESSAGE");
   	    	     System.out.println(FIELD +" >> " + MESSAGE);
    	    }
        }
	}	
}
