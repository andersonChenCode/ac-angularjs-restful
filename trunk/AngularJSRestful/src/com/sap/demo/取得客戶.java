package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得客戶 {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_CUSTOMER_GETCONTACTLIST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        function.getImportParameterList().setValue("MAXROWS","0");
    	
        
    	JCoTable CUSTOMERRANGE = function.getTableParameterList().getTable("CUSTOMERRANGE"); 
    	CUSTOMERRANGE.appendRow(); 
    	CUSTOMERRANGE.setValue("SIGN", "I");
    	CUSTOMERRANGE.setValue("OPTION", "BT");
    	CUSTOMERRANGE.setValue("LOW", "0");
    	CUSTOMERRANGE.setValue("HIGH", "ZZZZZZZZZZZZZZ");

		
    	JCoTable CONTACTADDRESSDATA = function.getTableParameterList().getTable("CONTACTADDRESSDATA"); 
    	
		connect.executeNTX(function);
		

	    for (int i = 0; i < CONTACTADDRESSDATA.getNumRows(); i++){ 
	    	CONTACTADDRESSDATA.setRow(i); 
	    	 String CUSTOMER = CONTACTADDRESSDATA.getString("CUSTOMER");
	    	 String LASTNAME = CONTACTADDRESSDATA.getString("LASTNAME");
	    	 System.out.println( CUSTOMER + "  " + LASTNAME );
	    }
	    
	    JCoStructure RETURN = function.getExportParameterList().getStructure("RETURN"); 
    	 String TYPE = RETURN.getString("TYPE");
    	 String MESSAGE = RETURN.getString("MESSAGE");
         System.out.println( TYPE + " >> " + MESSAGE);
	    
	}
}
