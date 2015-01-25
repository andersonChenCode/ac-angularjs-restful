package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得成本中心 {
	/**
	 * @param args
	 * @throws JCoException 
	 */
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_COSTCENTER_GETLIST1");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        function.getImportParameterList().setValue("CONTROLLINGAREA","TW00");
        function.getImportParameterList().setValue("COMPANYCODE_FROM","TW01");
        function.getImportParameterList().setValue("DATE_FROM","20121012");
        
    	JCoTable COSTCENTERLIST = function.getTableParameterList().getTable("COSTCENTERLIST"); 
    	
		connect.executeNTX(function);
		
	    for (int i = 0; i < COSTCENTERLIST.getNumRows(); i++){ 
	    	COSTCENTERLIST.setRow(i); 
	    	 String COSTCENTER = COSTCENTERLIST.getString("COSTCENTER");
	    	 String NAME = COSTCENTERLIST.getString("NAME");
	    	 //String DESCRIPT = COSTCENTERLIST.getString("DESCRIPT");
	    	 System.out.println(COSTCENTER +"  " + NAME );
	    }
	    
		JCoTable RETURN = function.getTableParameterList().getTable("RETURN"); 
	    for (int i = 0; i < RETURN.getNumRows(); i++){ 
 	    	 RETURN.setRow(i); 
 	    	 String TYPE = RETURN.getString("TYPE");
 	    	 String MESSAGE = RETURN.getString("MESSAGE");
 	         System.out.println( TYPE + " >> " + MESSAGE);
 	    }
	    
	}
}
