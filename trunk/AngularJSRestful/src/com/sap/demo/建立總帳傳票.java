package com.sap.demo;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;

public class 建立總帳傳票 {


	public static void main(String[] args) throws JCoException {
		SapSystem sapSystem = SapUtilities.getDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"BAPI_ACC_DOCUMENT_POST");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	connect.begin();
		JCoStructure DOCUMENTHEADER = function.getImportParameterList().getStructure("DOCUMENTHEADER");
		DOCUMENTHEADER.setValue("BUS_ACT", "RFBU");
		DOCUMENTHEADER.setValue("USERNAME", "NW431");
		DOCUMENTHEADER.setValue("COMP_CODE", "CN02");
		DOCUMENTHEADER.setValue("DOC_DATE", "20111031");
		DOCUMENTHEADER.setValue("PSTNG_DATE", "20111031");		
		DOCUMENTHEADER.setValue("TRANS_DATE", "20111031");		
		DOCUMENTHEADER.setValue("FISC_YEAR", "2011");
		DOCUMENTHEADER.setValue("FIS_PERIOD", "10");
		DOCUMENTHEADER.setValue("DOC_TYPE", "SA");
		DOCUMENTHEADER.setValue("HEADER_TXT","測試java建立傳票_20111031");
		
		JCoTable account_gl = function.getTableParameterList().getTable("ACCOUNTGL"); 
		account_gl.appendRow(); 
		account_gl.setValue("ITEMNO_ACC", "1");
		account_gl.setValue("GL_ACCOUNT", "0061880006");		
		account_gl.setValue("COSTCENTER", "TMC10130");
		//account_gl.setValue("PROFIT_CTR", "TTJ000");			
		account_gl.setValue("FUNC_AREA_LONG", "S000");	
		account_gl.setValue("COMP_CODE", "CN02");
		account_gl.setValue("TAX_CODE", "J1");						
		account_gl.setValue("ITEM_TEXT","");
		
		account_gl.appendRow(); 
		account_gl.setValue("ITEMNO_ACC", "2");
		account_gl.setValue("DOC_TYPE", "SA");		
		account_gl.setValue("GL_ACCOUNT", "0011010005");
		account_gl.setValue("COMP_CODE", "CN02");
		//account_gl.setValue("PSTNG_DATE", "20111031");
		account_gl.setValue("ITEM_TEXT","測試建立 1元整");
		
		JCoTable currency_amount = function.getTableParameterList().getTable("CURRENCYAMOUNT"); 
		currency_amount.appendRow(); 
		currency_amount.setValue("ITEMNO_ACC", "1");
		currency_amount.setValue("CURRENCY", "TWD");		
		currency_amount.setValue("AMT_DOCCUR", "-1");
		
		currency_amount.appendRow(); 
		currency_amount.setValue("ITEMNO_ACC", "2");
		currency_amount.setValue("CURRENCY", "TWD");		
		currency_amount.setValue("AMT_DOCCUR", "1");

		//JCoStructure COBL = JCo.createStructure(connect.getRepos().getStructureDefinition("COBL"));		
		//COBL.setValue("ZZCFCAT", "C10");	
		//System.out.println(COBL.getStructure(JCoMetaData.TYPE_DECF16));
		
		JCoTable EXTENSION2 = function.getTableParameterList().getTable("EXTENSION2"); 
		EXTENSION2.appendRow(); 
		EXTENSION2.setValue("STRUCTURE", "COBL"); 
		EXTENSION2.setValue("VALUEPART1", "2");		
		EXTENSION2.setValue("VALUEPART2", "ZZCFCAT");	
		EXTENSION2.setValue("VALUEPART3", "C10");	
		EXTENSION2.appendRow(); 
		EXTENSION2.setValue("STRUCTURE", "COBL"); 
		EXTENSION2.setValue("VALUEPART1", "2");		
		EXTENSION2.setValue("VALUEPART2", "PRCTR");	
		EXTENSION2.setValue("VALUEPART3", "N99000");	
		
		// 
		
		
		connect.execute(function);
		JCoTable returnStructure = function.getTableParameterList().getTable("RETURN"); 
        if(!(returnStructure.getString("TYPE").equals("") || returnStructure.getString("TYPE").equals("S"))){
    	    for (int i = 0; i < returnStructure.getNumRows(); i++){ 
    	    	 returnStructure.setRow(i); 
    	    	 String FIELD = returnStructure.getString("FIELD");
    	    	 String MESSAGE = returnStructure.getString("MESSAGE");
   	    	     System.out.println(FIELD +" >> " + MESSAGE);
    	    }
        }else{
            System.out.println("消息類型[TYPE]:"+returnStructure.getString("TYPE")); 
            System.out.println("消息, 消息類[ID]:"+returnStructure.getString("ID")); 
            System.out.println("消息, 消息編號[NUMBER]:"+returnStructure.getString("NUMBER")); 
            System.out.println("消息文本[MESSAGE]:"+returnStructure.getString("MESSAGE")); 
            System.out.println("應用程序日誌: 日誌號[LOG_NO] :"+returnStructure.getString("LOG_NO")); 
            System.out.println("應用日誌：內部郵件序列號[LOG_MSG_NO]:"+returnStructure.getString("LOG_MSG_NO")); 
            System.out.println("消息,消息變量[MESSAGE_V1]:"+returnStructure.getString("MESSAGE_V1")); 
            System.out.println("消息,消息變量[MESSAGE_V2]:"+returnStructure.getString("MESSAGE_V2")); 
            System.out.println("消息,消息變量[MESSAGE_V3]:"+returnStructure.getString("MESSAGE_V3")); 
            System.out.println("消息,消息變量[MESSAGE_V4]:"+returnStructure.getString("MESSAGE_V4")); 
            System.out.println("參數名稱[PARAMETER]:"+returnStructure.getString("PARAMETER")); 
            System.out.println("參數中的行[ROW]:"+returnStructure.getString("ROW")); 
            System.out.println("參數中的字段[FIELD]:"+returnStructure.getString("FIELD")); 
            System.out.println("引發消息的邏輯系統[SYSTEM]:"+returnStructure.getString("SYSTEM")); 
            System.out.println(returnStructure.getString("MESSAGE")); 
            function = connect.getFunction("BAPI_TRANSACTION_COMMIT"); 
        	connect.execute(function);
        }
        connect.end();

	}

}
