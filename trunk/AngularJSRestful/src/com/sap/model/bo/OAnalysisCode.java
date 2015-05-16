package com.sap.model.bo;


import java.io.Serializable;
import java.util.Hashtable;
import org.apache.commons.lang.builder.ToStringBuilder;



/** @author Hibernate CodeGenerator */
public class OAnalysisCode extends AbstractOAnalysisCode implements Serializable {

    protected Hashtable Dproperties_Hashtable = new Hashtable();

    public Hashtable getDproperties_Hashtable() {
    	if (getDproperties() == null ) return  Dproperties_Hashtable;
    	try {
			return (Hashtable)unit.createObjectByByteArray(getDproperties());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    public void setDproperties_Hashtable(Hashtable dproperties) throws Exception {
    	Dproperties_Hashtable = dproperties;
    }
    public void addDproperties(String key , String value) throws Exception {
    	getDproperties_Hashtable().put(key, value);
    	setDproperties(unit.createObjectByteArray(getDproperties_Hashtable()));
    }  
    public String getDpropertiesBykey(String key ) throws Exception {
    	return (String)getDproperties_Hashtable().get(key);
    }     
    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("analysisgroupname", getAnalysisgroupname())
            .append("dproperties", getDproperties_Hashtable())
            .toString();
    }
}
