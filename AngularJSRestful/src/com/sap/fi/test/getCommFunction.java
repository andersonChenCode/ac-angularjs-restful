package com.sap.fi.test;

import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;

import com.sap.conn.Connection;
import com.sap.conn.SapSystem;
import com.sap.conn.SapUtilities;


public class getCommFunction {
	public static void main(String[] args) throws JCoException {
		getCommFunction obj = new getCommFunction();
		obj.runTest();
	}
	public static void runTest() throws JCoException {
		SapSystem sapSystem = SapUtilities.getLocalDefTestSystem();
    	Object[] obj = SapUtilities.getJCoFunction(sapSystem,"CURRENCY_CONVERTING_FACTOR");
    	Connection connect = (Connection)obj[0];
    	JCoFunction function = (JCoFunction)obj[1];
    	
    	function.getImportParameterList().setValue("CURRENCY", "TWD");
    	
    	Double FACTOR = function.getExportParameterList().getDouble("FACTOR");
    	
		connect.executeNTX(function);

		System.out.println("FACTOR >> " + FACTOR);
	}
}
