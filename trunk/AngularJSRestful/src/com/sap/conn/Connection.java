package com.sap.conn;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.ext.Environment;


/**
 * Connection allows to get and execute SAP functions. The constructor expect a
 * SapSystem and will save the connection data to a file. The connection will
 * also be automatically be established.
 */

public class Connection {
	static String ABAP_AS_POOLED = "ABAP_AS_WITH_POOL";  
	static String SAP_SERVER = "SAP_SERVER";
	private JCoRepository repos;
	private JCoDestination dest;
    public JCoRepository getRepos(){
    	return repos;
    }
	public Connection(SapSystem system) {
		MyDestinationDataProvider myProvider = new MyDestinationDataProvider(system);
		
		boolean isRegistered = Environment.isDestinationDataProviderRegistered();
		if (!isRegistered) Environment.registerDestinationDataProvider(myProvider);
		try {
			dest = JCoDestinationManager.getDestination(ABAP_AS_POOLED);
			//System.out.println("Attributes:");
			//System.out.println(dest.getAttributes());
			repos = dest.getRepository();
		} catch (JCoException e) {
			throw new RuntimeException(e);
		}

	}

	/**
	 * Method getFunction read a SAP Function and return it to the caller. The
	 * caller can then set parameters (import, export, tables) on this function
	 * and call later the method execute.
	 * 
	 * getFunction translates the JCo checked exceptions into a non-checked
	 * exceptions
	 */
	public JCoFunction getFunction(String functionStr) {
		JCoFunction function = null;
		try {
			function = repos.getFunction(functionStr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(
					"Problem retrieving JCO.Function object.");
		}
		if (function == null) {
			throw new RuntimeException("Not possible to receive function. ");
		}

		return function;
	}
	/**
	 * Method execute will call a function. The Caller of this function has
	 * already set all required parameters of the function
	 * @throws JCoException 
	 * 
	 */
	public void execute(JCoFunction function) throws JCoException {
		function.execute(dest);
//		try {
//			JCoContext.begin(dest);
//			function.execute(dest);
//
//		} catch (JCoException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				JCoContext.end(dest);
//			} catch (JCoException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void executeNTX(JCoFunction function) throws JCoException {
		try {
			JCoContext.begin(dest);
			function.execute(dest);
			
		} catch (JCoException e) {
			e.printStackTrace();
		} finally {
			try {
				//System.out.println("conn.......");
				JCoContext.end(dest);
			} catch (JCoException e) {
				e.printStackTrace();
			}
		}
	}	
	public void begin() {		
		JCoContext.begin(dest);
	}	
	public void end() throws JCoException {
		JCoContext.end(dest);
	}
}
