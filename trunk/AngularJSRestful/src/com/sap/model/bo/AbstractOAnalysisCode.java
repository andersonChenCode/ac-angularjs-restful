package com.sap.model.bo;

import java.io.InputStream;
import java.io.Serializable;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.util.Hashtable;



/** @author Hibernate CodeGenerator */
public class AbstractOAnalysisCode implements Serializable {

	public interface FieldNames {
		String id = "id";
		String name = "name";
		String code = "code";
		String companycode = "companycode";
		String company = "company";
		String analysisgroupname = "analysisgroupname";
		String dproperties = "dproperties";
	}

    /** nullable persistent field */
    private Integer id;

    /** nullable persistent field */
    private String name;

    /** nullable persistent field */
    private String code;

    /** nullable persistent field */
    private String analysisgroupname;

    /** nullable persistent field */
    private String codeDesc;
    
    /** nullable persistent field */
    private String editId;

    /** nullable persistent field */
    private String editDate;

    /** nullable persistent field */
    private String editTime;
    
    /** nullable persistent field */
    private byte[] dproperties;


    /** default constructor */
    public AbstractOAnalysisCode() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getAnalysisgroupname() {
        return this.analysisgroupname;
    }

    public void setAnalysisgroupname(String analysisgroupname) {
        this.analysisgroupname = analysisgroupname;
    }

    public byte[] getDproperties() {
        return this.dproperties;
    }

    public void setDproperties(byte[] dproperties) {
			this.dproperties = dproperties;
    }
    public String getCodeDesc() {
        return this.codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
    public String getEditId() {
        return this.editId;
    }

    public void setEditId(String editId) {
        this.editId = editId;
    }

    public String getEditDate() {
        return this.editDate;
    }

    public void setEditDate(String editDate) {
        this.editDate = editDate;
    }

    public String getEditTime() {
        return this.editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("id", getId())
            .append("name", getName())
            .append("code", getCode())
            .append("analysisgroupname", getAnalysisgroupname())
           // .append("dproperties", getDproperties())
            .toString();
    }

}
