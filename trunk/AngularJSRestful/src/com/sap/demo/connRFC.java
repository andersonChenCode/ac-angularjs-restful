package com.sap.demo;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.SapUtilities;

public class connRFC {
	public static void main(String[] args) throws JCoException {
		
		SapSystem sapSystem = SapUtilities.getLocalDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"YFIRFC_GETVOUCHER");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	function.getImportParameterList().setValue("BUKRS", "CN02");
    	function.getImportParameterList().setValue("BELNR", "140000008");
    	function.getImportParameterList().setValue("GJAHR", "2012");
		connect.executeNTX(function);
		
		String XREF1_HD = (String) function.getExportParameterList().getValue("XREF1_HD");
		
		System.out.println(" XREF1_HD >>  " + XREF1_HD);
		
		JCoStructure RETURN = function.getExportParameterList().getStructure("RETURN");
		System.out.println("========================================================");
        System.out.println("消息類型[TYPE]:"+RETURN.getString("TYPE")); 
        System.out.println("消息, 消息類[ID]:"+RETURN.getString("ID")); 
        System.out.println("消息, 消息編號[NUMBER]:"+RETURN.getString("NUMBER")); 
        System.out.println("消息文本[MESSAGE]:"+RETURN.getString("MESSAGE")); 
        System.out.println("應用程序日誌: 日誌號[LOG_NO] :"+RETURN.getString("LOG_NO")); 
        System.out.println("應用日誌：內部郵件序列號[LOG_MSG_NO]:"+RETURN.getString("LOG_MSG_NO")); 
        System.out.println("消息,消息變量[MESSAGE_V1]:"+RETURN.getString("MESSAGE_V1")); 
        System.out.println("消息,消息變量[MESSAGE_V2]:"+RETURN.getString("MESSAGE_V2")); 
        System.out.println("消息,消息變量[MESSAGE_V3]:"+RETURN.getString("MESSAGE_V3")); 
        System.out.println("消息,消息變量[MESSAGE_V4]:"+RETURN.getString("MESSAGE_V4")); 
        System.out.println("參數名稱[PARAMETER]:"+RETURN.getString("PARAMETER")); 
        System.out.println("參數中的行[ROW]:"+RETURN.getString("ROW")); 
        System.out.println("參數中的字段[FIELD]:"+RETURN.getString("FIELD")); 
        System.out.println("引發消息的邏輯系統[SYSTEM]:"+RETURN.getString("SYSTEM")); 
        System.out.println("MESSAGE:"+RETURN.getString("MESSAGE")); 
	}
}
