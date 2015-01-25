package com.sap.conn;

import java.util.Properties;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.ext.DestinationDataEventListener;
import com.sap.conn.jco.ext.DestinationDataProvider;


public class CustomDestinationDataProvider
{
    static class MyDestinationDataProvider implements DestinationDataProvider
    {
        private DestinationDataEventListener eL;

        private Properties ABAP_AS_properties; 
        
        public Properties getDestinationProperties(String destinationName)
        {
            if(destinationName.equals("ABAP_AS") && ABAP_AS_properties!=null)
                return ABAP_AS_properties;
            
            return null;
            //alternatively throw runtime exception
            //throw new RuntimeException("Destination " + destinationName + " is not available");
        }

        public void setDestinationDataEventListener(DestinationDataEventListener eventListener)
        {
            this.eL = eventListener;
        }

        public boolean supportsEvents()
        {
            return true;
        }
        
        void changePropertiesForABAP_AS(Properties properties)
        {
            if(properties==null)
            {
                ABAP_AS_properties = null;
                eL.deleted("ABAP_AS");
            }
            else 
            {
                if(ABAP_AS_properties==null || !ABAP_AS_properties.equals(properties))
                {
	                ABAP_AS_properties = properties;
                    eL.updated("ABAP_AS");
                }
            }
        }
    }
    
    public static void main(String[] args) throws Exception
    {
        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "169.254.218.109");
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR,  "00");
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "000");
        connectProperties.setProperty(DestinationDataProvider.JCO_USER,   "cd109");
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "5379843");
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG,   "en");

        MyDestinationDataProvider myProvider = new MyDestinationDataProvider();
        
        com.sap.conn.jco.ext.Environment.registerDestinationDataProvider(myProvider);
        myProvider.changePropertiesForABAP_AS(connectProperties);
        
        JCoDestination ABAP_AS = JCoDestinationManager.getDestination("ABAP_AS");
        ABAP_AS.ping();

        System.out.println("ABAP_AS destination is ok");
        
    }
    
}
