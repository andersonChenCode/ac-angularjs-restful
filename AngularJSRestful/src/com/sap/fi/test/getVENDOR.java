package com.sap.fi.test;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;

public class getVENDOR {

	public static void main(String[] args) throws JCoException {
		getVENDOR obj = new getVENDOR();
		obj.runTest();
	}
	public static void runTest() throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getLocalDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"YGETVENDOR");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	function.getImportParameterList().setValue("BUKRS", "TW01");
    	function.getImportParameterList().setValue("LIFNR", "1*");
    	function.getImportParameterList().setValue("NAME1", "台灣*");
    	
    	JCoTable returnStructure = function.getTableParameterList().getTable("T_LFA1"); 
    	
		connect.executeNTX(function);
		
		
	    for (int i = 0; i < returnStructure.getNumRows(); i++){ 
	    	 returnStructure.setRow(i); 
	    	 String LIFNR = returnStructure.getString("LIFNR");
	    	 String NAME1 = returnStructure.getString("NAME1");
	    	 String SORTL = returnStructure.getString("SORTL");
	    	 System.out.println(LIFNR +"   " + NAME1 + "  " + SORTL);
	    }
	}

}
