package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得成本中心明細 {
	
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_COSTCENTER_GETDETAIL1");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        function.getImportParameterList().setValue("CONTROLLINGAREA","TW00");
        function.getImportParameterList().setValue("COSTCENTER","NFC00000");

    	JCoStructure COSTCENTERDETAIL = function.getExportParameterList().getStructure("COSTCENTERDETAIL");
    	
		connect.executeNTX(function);
		
		String COSTCENTER = COSTCENTERDETAIL.getString("COSTCENTER");
		String DEPARTMENT = COSTCENTERDETAIL.getString("DEPARTMENT");
		String FUNC_AREA = COSTCENTERDETAIL.getString("FUNC_AREA");
		String VALID_TO = COSTCENTERDETAIL.getString("VALID_TO");		
		
		System.out.println("COSTCENTER >> " + COSTCENTER);
		System.out.println("DEPARTMENT >> " + DEPARTMENT);
		System.out.println("FUNC_AREA >> " + FUNC_AREA);
		System.out.println("VALID_TO >> " + VALID_TO);		
		
		JCoTable RETURN = function.getTableParameterList().getTable("RETURN"); 
	    for (int i = 0; i < RETURN.getNumRows(); i++){ 
 	    	 RETURN.setRow(i); 
 	    	 String TYPE = RETURN.getString("TYPE");
 	    	 String MESSAGE = RETURN.getString("MESSAGE");
 	         System.out.println( TYPE + " >> " + MESSAGE);
 	    }
	    
	}
}
