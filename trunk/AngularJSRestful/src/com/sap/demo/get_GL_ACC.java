package com.sap.demo;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;

public class get_GL_ACC {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getLocalDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_GL_ACC_GETDETAIL");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
       	function.getImportParameterList().setValue("COMPANYCODE", "CN01");
       	function.getImportParameterList().setValue("GLACCT", "0011010005");
		
       	connect.executeNTX(function);
		
    	JCoStructure returnStructure = function.getExportParameterList().getStructure("ACCOUNT_DETAIL");
    
		
	    Object GL_ACCOUNT = returnStructure.getValue("GL_ACCOUNT");
	    Object SHORT_TEXT  = returnStructure.getValue("SHORT_TEXT");
	    Object LONG_TEXT = returnStructure.getValue("LONG_TEXT");
	    Object CHRT_ACCTS = returnStructure.getValue("CHRT_ACCTS");
	    System.out.println(GL_ACCOUNT +"   " + SHORT_TEXT +"  " + LONG_TEXT +"  " + CHRT_ACCTS);
	}
}
