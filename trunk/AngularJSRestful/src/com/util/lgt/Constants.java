package com.util.lgt;

public class Constants {
    
    public static final String SESSION_CURRENT_USER = "current_user";
    public static final String SESSION_CURRENT_USER_DEPARTMENT = "current_department";
    public static final String SESSION_CURRENT_SYSTEM_ID = "current_sysid";    
    public static final String SESSION_IS_ADMIN = "is_admin";
    
    public static final int getSubSystemID(String s) {
    	String[] subSystemCode = new String[] {
    			"tast.generalbudget",
    			"tast.unitaccounting",
    			"tast.series",
    			"tast.payment",
    			"tast.publicfund",
    			"tast.generalaccounting",
    			"tast.financialmgt",
    			"tast.fine",
    			"tast.incomereceipt",
    			"tast.cashier",
    			"tast.salary",
    			"blackbear.systemmgt"};
    	
    	int[] subSystemID = new int[]{-11,-12,-13,-14,-15,-16,-17,-18,-19,-20,-21,-22};    	
    	for (int i=0; i<subSystemCode.length; i++) {
    		if (s.equals(subSystemCode[i])) return subSystemID[i];
    	}
    	return -1;    	
    }
    
    public static final String getSubSystemName(int s) {
    	String[] subSystemName = new String[] {"預算編製系統","財務管理系統","鄉鎮市彙編系統","庫款支付系統","公務基金系統","總會計系統","財務管理系統","罰鍰系統","自行收納統一收據系統","出納專戶系統","薪資系統","系統管理員"};
    	int[] subSystemID = new int[]{-11,-12,-13,-14,-15,-16,-17,-18,-19,-20,-21,-22};    	
    	for (int i=0; i<subSystemID.length; i++) {
    		if (s==subSystemID[i]) return subSystemName[i];
    	}
    	return "";    	
    } 
    
	public static final String SESSION_ACCOUNTING_YEAR = "GBGTParameter";	
	public static final String MSG_DTREE_LEVEL_LIMIT_FOR_ADD = "您好！　該項目之下不能再新增下一層級之項目！\\n\\n若確實有該需求，請洽業務承辦人員及系統開發廠商！\\n";
	public static final String MSG_DTREE_LEVEL_LIMIT_FOR_EDIT = "<div align=center><br>對不起,系統無法對該項目進行維護動作！<br><br>若有問題持續，請洽系統管理者或相關承辦人員！</div>";	
	public static final String MSG_NoPermission = "<div align=center><br><br><font color=red>對不起! 你沒有足夠的權限執行此功作!!<br><br>若有問題請洽詢系統管理者或相關承辦人員!!</font></div><br><br>";	
	public static final String MSG_ID_NOT_FOUND = "查無代碼資料，請先執行年度代碼承轉作業！";    

}
