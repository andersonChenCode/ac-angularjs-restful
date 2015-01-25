package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得利潤中心 {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_PROFITCENTER_GETLIST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        function.getImportParameterList().setValue("CONTROLLINGAREA","TW00");
        function.getImportParameterList().setValue("DATE","20121012");
        
    	JCoTable PROFITCENTER_LIST = function.getTableParameterList().getTable("PROFITCENTER_LIST"); 
    	
		connect.executeNTX(function);
		
	    for (int i = 0; i < PROFITCENTER_LIST.getNumRows(); i++){ 
	    	PROFITCENTER_LIST.setRow(i); 
	    	 String PROFIT_CTR = PROFITCENTER_LIST.getString("PROFIT_CTR");
	    	 String PCTR_NAME  = PROFITCENTER_LIST.getString("PCTR_NAME");
	    	 System.out.println(PROFIT_CTR +"  " + PCTR_NAME );
	    }
	    System.out.println("=========================================");
    	JCoStructure RETURN = function.getExportParameterList().getStructure("RETURN");
    	String TYPE = RETURN.getString("TYPE");
    	String MESSAGE = RETURN.getString("MESSAGE");
        System.out.println( TYPE + " >> " + MESSAGE);
	}
}
