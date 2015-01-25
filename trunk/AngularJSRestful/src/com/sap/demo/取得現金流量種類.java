package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 取得現金流量種類 {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"YFIRFC_ZFIT001");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	
        //function.getImportParameterList().setValue("ZCFCAT","C00");
        
    	JCoTable ACCOUNT_LIST = function.getTableParameterList().getTable("T_ZFIT001"); 
    	
		connect.executeNTX(function);
		
	    for (int i = 0; i < ACCOUNT_LIST.getNumRows(); i++){ 
	    	ACCOUNT_LIST.setRow(i); 
	    	 String ZCFCAT = ACCOUNT_LIST.getString("ZCFCAT");
	    	 String ZCFCAT_NAME = ACCOUNT_LIST.getString("ZCFCAT_NAME");
	    	 System.out.println(ZCFCAT + "  " + ZCFCAT_NAME );
	    }
	   
	    
	}
}
