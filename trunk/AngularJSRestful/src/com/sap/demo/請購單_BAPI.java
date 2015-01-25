package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class 請購單_BAPI {
	public static void main(String[] args) throws JCoException {
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_PR_CREATE");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	connect.begin();
 
          JCoStructure PRHEADER = function.getImportParameterList().getStructure("PRHEADER");
		  JCoStructure PRHEADERX = function.getImportParameterList().getStructure("PRHEADERX");
		  
		  JCoTable PRITEM = function.getTableParameterList().getTable("PRITEM"); 
	      JCoTable PRITEMX = function.getTableParameterList().getTable("PRITEMX"); 
		  
	      JCoTable PRACCOUNT = function.getTableParameterList().getTable("PRACCOUNT"); 
	      JCoTable PRACCOUNTX = function.getTableParameterList().getTable("PRACCOUNTX"); 
	      
	      //JCoTable PRADDRDELIVERY = function.getTableParameterList().getTable("PRADDRDELIVERY");
	      
          PRHEADER.setValue("PR_TYPE", "PE");
          PRHEADER.setValue("AUTO_SOURCE", "X");
          
          PRHEADERX.setValue("PR_TYPE", "X");
          PRHEADERX.setValue("AUTO_SOURCE", "X");
          
          PRITEM.appendRow(); 
          PRITEM.setValue("PREQ_ITEM", "10");
          PRITEM.setValue("ACCTASSCAT", "K");
          PRITEM.setValue("SHORT_TEXT", "XXX 申請名片");
          PRITEM.setValue("QUANTITY", "3");
          PRITEM.setValue("UNIT", "CAS");
          PRITEM.setValue("PREQ_PRICE", "130");
          PRITEM.setValue("PRICE_UNIT", "1");
          PRITEM.setValue("DEL_DATCAT_EXT", "D");
          PRITEM.setValue("DELIV_DATE", "20121010");
          PRITEM.setValue("PLANT", "TNT1");
          PRITEM.setValue("PUR_GROUP", "N11");
          PRITEM.setValue("PREQ_NAME", "陳俊杰");
          PRITEM.setValue("MATL_GROUP", "01-884");

          PRITEMX.appendRow(); 
          PRITEMX.setValue("PREQ_ITEM", "10");
          PRITEMX.setValue("ACCTASSCAT", "X");
          PRITEMX.setValue("SHORT_TEXT", "X");
          PRITEMX.setValue("QUANTITY", "X");
          PRITEMX.setValue("UNIT", "X");
          PRITEMX.setValue("PREQ_PRICE", "X");
          PRITEMX.setValue("PRICE_UNIT", "X");
          PRITEMX.setValue("DEL_DATCAT_EXT", "X");
          PRITEMX.setValue("DELIV_DATE", "X");
          PRITEMX.setValue("PLANT", "X");
          PRITEMX.setValue("PUR_GROUP", "X");
          PRITEMX.setValue("PREQ_NAME", "X");
          PRITEMX.setValue("MATL_GROUP", "X");

          PRACCOUNT.appendRow(); 
          PRACCOUNT.setValue("PREQ_ITEM", "10");
          PRACCOUNT.setValue("SERIAL_NO", "1");
          PRACCOUNT.setValue("QUANTITY", "3");
          PRACCOUNT.setValue("GL_ACCOUNT", "0061880004");
          PRACCOUNT.setValue("CO_AREA", "TW00");
          PRACCOUNT.setValue("COSTCENTER", "NEC10900");

          PRACCOUNTX.appendRow(); 
          PRACCOUNTX.setValue("PREQ_ITEM", "10");
          PRACCOUNTX.setValue("SERIAL_NO", "1");
          PRACCOUNTX.setValue("PREQ_ITEMX", "X");
          PRACCOUNTX.setValue("QUANTITY", "X");
          PRACCOUNTX.setValue("GL_ACCOUNT", "X");
          PRACCOUNTX.setValue("CO_AREA", "X");
          PRACCOUNTX.setValue("COSTCENTER", "X");
		
	      //PRADDRDELIVERY.appendRow(); 
	      //PRADDRDELIVERY.setValue("PREQ_ITEM", "10");
	      //PRADDRDELIVERY.setValue("NAME_2", "測試地址");
          
		connect.execute(function);
		

		
        String NUMBER = function.getExportParameterList().getString("NUMBER");
		
		System.out.println(" NUMBER >> " + NUMBER);
        
        JCoTable RETURN = function.getTableParameterList().getTable("RETURN"); 
        

        boolean isFlag = false; 
	    for (int i = 0; i < RETURN.getNumRows(); i++){ 
  	    	 RETURN.setRow(i); 
  	    	 String TYPE = RETURN.getString("TYPE");
  	    	 String MESSAGE = RETURN.getString("MESSAGE");
  	       	 if (TYPE.equals("S")){
  	       		isFlag = true;
  	       	 }
  	         System.out.println( TYPE + " >> " + MESSAGE);
  	    }
	    if (isFlag == true){
	        function = connect.getFunction("BAPI_TRANSACTION_COMMIT"); 
	    	connect.execute(function);
	    }else{
	        function = connect.getFunction("BAPI_TRANSACTION_ROLLBACK"); 
	    	connect.execute(function);
	    }
	    
	    connect.end();

	}
}
