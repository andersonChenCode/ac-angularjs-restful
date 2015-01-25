package com.sap.demo;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;



public class 取得公司代碼 {

	/**
	 * @param args
	 * @throws JCoException 
	 */
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_COMPANYCODE_GETLIST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	JCoTable returnStructure = function.getTableParameterList().getTable("COMPANYCODE_LIST"); 
    	
		connect.executeNTX(function);
		
		
	    for (int i = 0; i < returnStructure.getNumRows(); i++){ 
	    	 returnStructure.setRow(i); 
	    	 String COMP_CODE = returnStructure.getString("COMP_CODE");
	    	 String COMP_NAME = returnStructure.getString("COMP_NAME");
	    	 System.out.println(COMP_CODE +"   " + COMP_NAME);
	    }
	}

}
