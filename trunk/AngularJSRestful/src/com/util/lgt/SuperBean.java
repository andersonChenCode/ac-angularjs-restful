package com.util.lgt;

import org.apache.commons.lang.StringEscapeUtils;

import org.json.simple.JSONValue;

/**
*<br>程式目的：LG,FA部門所有前端網頁的bean都需繼承此類別
*<br>程式代號：SuperBean
*<br>撰寫日期：93/12/01
*<br>程式作者：Kang Da Information : LG-T
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*<br>
*/

public abstract class SuperBean {
	int listLimit=500;
	public int getListLimit(){ return listLimit; }
	public void setListLimit(int i){ listLimit=i; }
	public String getListLimitError() { return "查詢資料超過"+getListLimit()+"筆, 請縮小查詢範圍！"; }

	String state="init";
	public String getState(){ return checkGet(state); }
	public void setState(String s){ state=checkSet(s); }
	
	String errorMsg;
	public String getErrorMsg(){ return checkGet(errorMsg); }
	public void setErrorMsg(String s){ errorMsg=checkSet(s); }	

	String queryAllFlag="false";
	public String getQueryAllFlag(){ return checkGet(queryAllFlag); }
	public void setQueryAllFlag(String s){ queryAllFlag=checkSet(s); }	
	
	String userID;
	public String getUserID(){ return checkGet(userID); }
	public void setUserID(String s){ userID=checkSet(s); }
	
	String editID;
	public String getEditID(){ return checkGet(editID); }
	public void setEditID(String s){ editID=checkSet(s); }
	
	String editDate;
	public String getEditDate(){ return checkGet(editDate); }
	public void setEditDate(String s){ editDate=checkSet(s); }
	
	String editTime;
	public String getEditTime(){ return checkGet(editTime); }
	public void setEditTime(String s){ editTime=checkSet(s); }	
	
	String nextInsert;
	public String getNextInsert(){ return checkGet(nextInsert); }
	public void setNextInsert(String s){ nextInsert=checkSet(s); }
	
	String listContainerActiveRowId;
	public String getListContainerActiveRowId(){ return checkGet(listContainerActiveRowId); }
	public void setListContainerActiveRowId(String s){ listContainerActiveRowId=checkSet(s); }	
	
	String pageNumberVar;
	String q_outputFormat;	
	String q_reportMethod;
	String deptCode;	
	public String getPageNumberVar() { return checkGet(pageNumberVar); }
	public void setPageNumberVar(String s) { pageNumberVar = checkSet(s); }
	public String getQ_outputFormat() { return checkGet(q_outputFormat); }
	public void setQ_outputFormat(String s) { q_outputFormat = checkSet(s); }
	public String getQ_reportMethod() { return checkGet(q_reportMethod); }
	public void setQ_reportMethod(String s) { q_reportMethod = checkSet(s); }	
	public String getDeptCode() { return checkGet(deptCode); }
	public void setDeptCode(String s) { deptCode = checkSet(s); }	
	
	//歲出科目
	String astLevel0;
	String astLevel1;
	String astLevel2;
	String astLevel3;
	String astLevel4;
	String q_astLevel0;
	String q_astLevel1;
	String q_astLevel2;
	String q_astLevel3;
	String q_astLevel4;
	
	public String getAstLevel0(){ return checkGet(astLevel0); }
	public void setAstLevel0(String s){ astLevel0=checkSet(s); }
	public String getAstLevel1(){ return checkGet(astLevel1); }
	public void setAstLevel1(String s){ astLevel1=checkSet(s); }
	public String getAstLevel2(){ return checkGet(astLevel2); }
	public void setAstLevel2(String s){ astLevel2=checkSet(s); }
	public String getAstLevel3(){ return checkGet(astLevel3); }
	public void setAstLevel3(String s){ astLevel3=checkSet(s); }
	public String getAstLevel4(){ return checkGet(astLevel4); }
	public void setAstLevel4(String s){ astLevel4=checkSet(s); }
	
	public String getQ_astLevel0(){ return checkGet(q_astLevel0); }
	public void setQ_astLevel0(String s){ q_astLevel0=checkSet(s); }
	public String getQ_astLevel1(){ return checkGet(q_astLevel1); }
	public void setQ_astLevel1(String s){ q_astLevel1=checkSet(s); }
	public String getQ_astLevel2(){ return checkGet(q_astLevel2); }
	public void setQ_astLevel2(String s){ q_astLevel2=checkSet(s); }
	public String getQ_astLevel3(){ return checkGet(q_astLevel3); }
	public void setQ_astLevel3(String s){ q_astLevel3=checkSet(s); }
	public String getQ_astLevel4(){ return checkGet(astLevel4); }
	public void setQ_astLevel4(String s){ q_astLevel4=checkSet(s); }	
	
	public String getRealAstId() {
		String astId = "";
		if (!"".equals(getAstLevel0())) astId = getAstLevel0();
		if (!"".equals(getAstLevel1())) astId = getAstLevel1();
		if (!"".equals(getAstLevel2())) astId = getAstLevel2();
		if (!"".equals(getAstLevel3())) astId = getAstLevel3();
		if (!"".equals(getAstLevel4())) astId = getAstLevel4();
		return astId;
	}
	
	public String getQ_realAstId() {
		String astId = "";
		if (!"".equals(getQ_astLevel0())) astId = getQ_astLevel0();
		if (!"".equals(getQ_astLevel1())) astId = getQ_astLevel1();
		if (!"".equals(getQ_astLevel2())) astId = getQ_astLevel2();
		if (!"".equals(getQ_astLevel3())) astId = getQ_astLevel3();
		if (!"".equals(getQ_astLevel4())) astId = getQ_astLevel4();
		return astId;
	}
	
	
	

	
	
	
	//檢查資料隱碼之旗標
	String sqlInjectionFlag="false";		
	boolean isPOJO = false;
	public boolean isPOJO() {return isPOJO;}
	
	/*回傳各種狀態值*/
	public void setStateInsertSuccess()  {		
		setState("insertSuccess");
		if (!"".equals(getNextInsert())) {
			setState("insert");	
		}		
	}
	public void setStateInsertError()    { setState("insertError"); }
	public void setStateUpdateSuccess()  { setState("updateSuccess"); }
	public void setStateUpdateError()    { setState("updateError"); }
	public void setStateDeleteSuccess()  { setState("deleteSuccess"); }
	public void setStateDeleteError()    { setState("deleteError"); }
	public void setStateQueryOneSuccess(){ 
		setState("queryOneSuccess");
		//setState("edit");
	}
	public void setStateQueryAllSuccess(){ 
		if ("queryAll".equals(getState())){
			setState("queryAllSuccess");			
		}
	}
	
  	/**
  	 * <br>
   	 * <br>目的：撰寫JavaBean get方法時所需套用的函數
   	 * <br>參數：資料字串
     * <br>傳回：檢查資料為null,是則傳回空字串
  	*/
	static public String get(String s){
		if(s==null){
			return "";
		}else{
			return s.trim();
		}
	}
  	/**
  	 * <br>
  	 * <br>目的：撰寫JavaBean set方法時所需套用的函數
  	 * <br>參數：資料字串
  	 * <br>傳回：將資料前後空白去除
  	*/
	static public String set(String s){
		if(s==null){
			return "";
		}else{
			return s.trim();
		}
	}	
	
  	/**
  	 * <br>
   	 * <br>目的：撰寫JavaBean chect get方法時所需套用的函數
   	 * <br>參數：資料字串
     * <br>傳回：檢查資料為null,是則傳回空字串
  	*/
	public String checkGet(String s){
		if(s==null){
			return "";
		}else{
			s = s.trim();	
			s = s.replaceAll("<", "&lt;");
			s = s.replaceAll(">","&gt;");
			//s = org.apache.commons.lang.StringEscapeUtils.escapeHtml(s);
			return s;
		}
	}
	public String checkGet(Double s){
		if(s == null){
			return "0";
		}
		if(s.toString().equals("")){
			return "0";
		}
		return s.toString();
	}	
  	/**
  	 * <br>
  	 * <br>目的：撰寫JavaBean check set方法時所需套用的函數
  	 * <br>參數：資料字串
  	 * <br>傳回：將資料前後空白去除
  	*/
	public String checkSet(String s){
		if(s==null){
			return "";
		}else{
			//檢查資料隱碼
			if ("false".equals(sqlInjectionFlag)){
				if ((s.indexOf("'")>=0)||(s.indexOf("\"")>=0))	{
					sqlInjectionFlag="true";
				}
			}	
			return s.trim().replaceAll("'", "''");
		}
	}		
		
	//--------- Pagging Control Start -----------------------
    int pageSize = 10;
    int totalPage = 0;
    int currentPage = 1;
    int totalRecord = 0;
    int recordStart = 0;
    int recordEnd = 0;
            
    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
    /*public void setPageSize(String s) {
        try{
            this.pageSize = Integer.parseInt(s);
        }catch(Exception x){}        
        
    }*/
    public int getCurrentPage() {
        return currentPage;
    }
    public void setCurrentPage(int currentPageNumber) {
        this.currentPage = currentPageNumber;
    }
    
    /*protected void setCurrentPage(String s) {
        try{
            this.currentPage = Integer.parseInt(s);
        }catch(Exception x){}
    }*/
    
    public int getRecordEnd() {
        return recordEnd;
    }
    protected void setRecordEnd(int recordEnd) {
        this.recordEnd = recordEnd;
    }
    public int getRecordStart() {
        return recordStart;
    }
    protected void setRecordStart(int recordStart) {
        this.recordStart = recordStart;
    }
    public int getTotalPage() {
        return totalPage;
    }
    protected void setTotalPage(int totalPageNumber) {
        this.totalPage = totalPageNumber;
    }
    public int getTotalRecord() {
        return totalRecord;
    }
    protected void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }    
    String historyPage;
    String historyPageSize;
    public String getHistoryPage(){ return checkGet(historyPage); }
    public void setHistoryPage(String s){ historyPage=checkSet(s); }    
    public String getHistoryPageSize(){ return checkGet(historyPageSize); }
    public void setHistoryPageSize(String s){ historyPageSize=checkSet(s); }
    
    
	public int initRecourdCount(String hql) throws Exception {
		isPOJO = true;
		Object x = null;//
		if (x!=null && !"".equals(get(x.toString()))) {
			int rc = Integer.parseInt(x.toString());			
			processCurrentPageAttribute(rc);
			return rc;
		} else {
			processCurrentPageAttribute(0);
		}
		return 0;
	}
	
    protected void processCurrentPageAttribute(java.sql.ResultSet rs) throws Exception{
        rs.last();
        totalRecord = rs.getRow();
        rs.first();

        if (totalRecord <= 0) {
            recordStart = 0;
            recordEnd = 0;            
            return ;
        }

        if (pageSize > 0) {
            totalPage = (totalRecord - 1) / pageSize + 1;
            if (currentPage < 1)
                currentPage = 1;
            if (currentPage > totalPage)
                currentPage = totalPage;

            recordStart = pageSize * (currentPage - 1) + 1;
            recordEnd = Math.min(recordStart + pageSize - 1, totalRecord);
        } else {
            recordStart = 0;
            recordEnd = totalRecord;
        }
        rs.absolute(recordStart);     
    }    
    
    protected void processCurrentPageAttribute(int t) throws Exception{
		isPOJO = true;    	
        totalRecord = t;
        if (totalRecord <= 0) {
            recordStart = 0;
            recordEnd = 0;            
            return ;
        }
        if (pageSize > 0) {
            totalPage = (totalRecord - 1) / pageSize + 1;
            if (currentPage < 1)
                currentPage = 1;
            if (currentPage > totalPage)
                currentPage = totalPage;

            //recordStart = pageSize * (currentPage - 1) + 1;
            recordStart = pageSize * (currentPage - 1);
            //recordEnd = Math.min(recordStart + pageSize - 1, totalRecord);
            recordEnd = Math.min(recordStart + pageSize, totalRecord);
        } else {
            recordStart = 0;
            recordEnd = totalRecord;
        }            
    }
    
    //------- End Of Pagging Control -------------
	
	//查詢欄位json string
	String qryString;
	public String getQryString() {	return get(qryString);	}
	public void setQryString(String qryString) { this.qryString = set(qryString); }
	//所有欄位的json string
	String fldString;
	public String getFldString() {	return get(fldString);	}
	public void setFldString(String s) { this.fldString = set(s); }
	
	
	//將前端畫面的所有欄位轉成HashMap型式  --> 取出時用 getForm("前端網頁欄位名稱")就可以得該欄位值
    java.util.HashMap webForm = new java.util.HashMap();
	protected java.util.HashMap getWebForm() {
		if (webForm!=null && webForm.size()>0) return webForm;
		else if (getFldString().length()>0){
			try {
				return (java.util.HashMap)JSONValue.parse(getFldString());				
			} catch (Exception e) {}
		}
		return webForm;
	}
		
	protected void setWebForm(java.util.HashMap form) {
		this.webForm = form;
	}
	
	/**
  	 * <br>
  	 * <br>目的：檢查是否有SQL Injection的威脅
  	 * <br>
  	*/
	public boolean isSQLInjection(){
		if ("true".equals(sqlInjectionFlag)) return true;
		else return false;
	}
	
	
	 //------- Start CRUD Control -------------
	
	//傳回Insert前之檢查SQL及message　
	protected String[][] getInsertCheckSQL(){String [][] rtnArray={{"","","",""}}; return rtnArray;}	
	//傳回Update前之檢查SQL及message　
	protected String[][] getUpdateCheckSQL(){String [][] rtnArray={{"","","",""}}; return rtnArray;}	
	//傳回Delete前之檢查SQL及message　
	protected String[][] getDeleteCheckSQL(){String [][] rtnArray={{"","","",""}}; return rtnArray;}
	
	/**
  	 * <br>
  	 * <br>目的：執行新增, 修改, 刪除前之邏輯檢查
  	 * <br>參數：傳入字串二維陣列, 允許多筆邏輯檢查
  	 * <br>　　　[0][0]:檢查之SQL語法, 查詢欄位只允許一個, 需加上as checkResult
  	 * <br>　　　[0][1]:七種條件式("EOF","=","!=",">",">=","<","<=")
  	 * <br>　　　[0][2]:條件值(需為數字字串,如條件式為EOF則不須輸入條件值)
  	 * <br>　　　[0][3]:錯誤訊息
  	 * <br>傳回：成功傳回true, 失敗傳回false
  	*/
	public boolean beforeExecCheck(String [][] checkSQLArray){
		boolean rtnBoolean=true;
		int checkResult=0;
		int condition=0;	
		if ("true".equals(sqlInjectionFlag)){
			setErrorMsg("所有欄位均不允許輸入[單引號]及[雙引號]，請重新檢查！");
			if ("insert".equals(getState()) || "insertError".equals(getState())) {
				setStateInsertError();
			} else { 
				setStateUpdateError(); 
			}
			return false;
		}
		try {		
			for(int i=0; i<checkSQLArray.length; i++){
				if (!"".equals(checkSQLArray[i][0].toString())){
					Object o = View.getObject(checkSQLArray[i][0]);
					if (o!=null){
						try{
							checkResult=Common.getInt(o);
							condition=Integer.parseInt(checkSQLArray[i][2]);
						} catch (Exception e) {
							continue;
						}		
						if ("=".equals(checkSQLArray[i][1])){
							if (checkResult==condition)
								rtnBoolean=false;
						}else if ("!=".equals(checkSQLArray[i][1])){
							if (checkResult!=condition)
								rtnBoolean=false;						
						}else if (">".equals(checkSQLArray[i][1])){
							if (checkResult>condition)
								rtnBoolean=false;						
						}else if (">=".equals(checkSQLArray[i][1])){
							if (checkResult>=condition)
								rtnBoolean=false;						
						}else if ("<".equals(checkSQLArray[i][1])){
							if (checkResult<condition)
								rtnBoolean=false;						
						}else if ("<=".equals(checkSQLArray[i][1])){
							if (checkResult<=condition)
								rtnBoolean=false;						
						}						
					}else{
						if ("EOF".equals(checkSQLArray[i][1]))
							rtnBoolean=false;
					}
					if (!rtnBoolean){
						setErrorMsg(checkSQLArray[i][3]);
						break;
					}
				}
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtnBoolean;
	}	
	
  	/**
  	 * <br>
  	 * <br>目的：新增儲存
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void insert(){
		try {
			if (!beforeExecCheck(getInsertCheckSQL())){
				setStateInsertError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());
				doCreate(getWebForm());
				setStateInsertSuccess();
				setErrorMsg("新增完成");				
			}
		} catch (Exception e) {
			e.printStackTrace();
			setStateInsertError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(Common.escapeJavaScript(e.getMessage()));
			else setErrorMsg("新增失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");
		}
	}	
	
  	/**
  	 * <br>
  	 * <br>目的：更新
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void update(){
		try {
			if (!beforeExecCheck(getUpdateCheckSQL())){
				setStateUpdateError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());			
				doUpdate(getWebForm());
				setStateUpdateSuccess();
				setErrorMsg("修改完成");					
			}			
		} catch (Exception e) {
			e.printStackTrace();
			setStateUpdateError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(StringEscapeUtils.escapeJavaScript(e.getMessage()));
			else setErrorMsg("發生未預期的錯誤,修改失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");			
		}
	}
	
  	/**
  	 * <br>
  	 * <br>目的：刪除
  	 * <br>參數：無
  	 * <br>傳回：無
  	*/		
	public void delete(){
		try {
			if (!beforeExecCheck(getDeleteCheckSQL())){
				setStateDeleteError();
			}else{
				setEditDate(Datetime.getYYYMMDD());
				setEditTime(Datetime.getHHMMSS());			
				doDelete(getWebForm());
				setStateDeleteSuccess();
				setErrorMsg("刪除完成");					
			}				
		} catch (Exception e) {
			e.printStackTrace();
			setStateDeleteError();
			if (e.getMessage()!=null && e.getMessage().length()<200) setErrorMsg(StringEscapeUtils.escapeJavaScript(e.getMessage()));
			else setErrorMsg("發生未預期的錯誤,刪除失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");			
		}
	}	
	
	public Object queryOne(){
		setStateQueryOneSuccess();
		try {
			return doQueryOne(getWebForm());
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMsg("發生未預期的錯誤,查詢失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");
			return null;
		}
	}	
	
	public Object queryAll(){
		setStateQueryAllSuccess();
		try {
			return doQueryAll(getWebForm());
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMsg("發生未預期的錯誤,查詢失敗!若問題持續,請洽詢系統管理者或相關承辦人員！");
			return null;
		}
	}	

	public abstract Object doQueryOne(java.util.Map form) throws Exception;	
	public abstract Object doQueryAll(java.util.Map form) throws Exception;	
	public abstract void doCreate(java.util.Map form) throws Exception;	
	public abstract void doUpdate(java.util.Map form) throws Exception;	
	public abstract void doDelete(java.util.Map form) throws Exception;	
	//public abstract void doPrint(java.util.Map form) throws Exception;
	
	//------- End of CRUD Control -------------
    
}