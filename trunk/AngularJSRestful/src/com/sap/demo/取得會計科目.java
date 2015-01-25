package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得會計科目 {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_GL_ACC_GETLIST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        function.getImportParameterList().setValue("COMPANYCODE","TW01");
        //function.getImportParameterList().setValue("LANGUAGE","ZF");
        
    	JCoTable ACCOUNT_LIST = function.getTableParameterList().getTable("ACCOUNT_LIST"); 
    	
		connect.executeNTX(function);
		
	    for (int i = 0; i < ACCOUNT_LIST.getNumRows(); i++){ 
	    	ACCOUNT_LIST.setRow(i); 
	    	 String GL_ACCOUNT = ACCOUNT_LIST.getString("GL_ACCOUNT");
	    	 String SHORT_TEXT = ACCOUNT_LIST.getString("SHORT_TEXT");
	    	 System.out.println(GL_ACCOUNT +"  " + SHORT_TEXT );
	    }
	   
	    
	}
}
