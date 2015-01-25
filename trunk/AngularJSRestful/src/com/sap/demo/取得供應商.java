package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得供應商 {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BBP_VENDOR_GETLIST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        function.getImportParameterList().setValue("COMP_CODE","TW01");
    	
    	JCoTable VENDOR = function.getTableParameterList().getTable("VENDOR"); 
    	
		connect.executeNTX(function);
		

	    for (int i = 0; i < VENDOR.getNumRows(); i++){ 
	    	VENDOR.setRow(i); 
	    	 String VENDOR_NO = VENDOR.getString("VENDOR_NO");
	    	 String NAME = VENDOR.getString("NAME");
	    	 System.out.println( VENDOR_NO + "  " + NAME );
	    }
	    
	    JCoStructure RETURN = function.getExportParameterList().getStructure("RETURN"); 
    	 String TYPE = RETURN.getString("TYPE");
    	 String MESSAGE = RETURN.getString("MESSAGE");
         System.out.println( TYPE + " >> " + MESSAGE);
	    
	}
}
