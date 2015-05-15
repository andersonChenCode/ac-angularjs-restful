/*
*<br>程式目的：顯示Html view之函數
*<br>程式代號：View
*<br>撰寫日期：94/05/10
*<br>程式作者：swancor
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*<br>
*/

package com.util.lgt;

import java.util.*;
import org.springframework.orm.hibernate3.HibernateOperations;
import com.persistence.PersistenceServiceGetter;


public class View {

	private View() {
		//avoid instantiation...
	}

    static public String getPrintFormatOption(String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
    	String[] formatId = new String[]{"1","2","3"};
    	String[] formatName = {"HTML","PDF","Excel"};
        try {
    		for (int i=0; i<formatId.length; i++) {
                sb.append("<option value='").append(formatId[i]).append("' ");
                if (selectedOne!= null && selectedOne.equals(formatId[i])) {
                    sb.append(" selected ");
                }
                sb.append(">").append(formatName[i]).append("</option>\n");
    		}
        } catch (Exception ex) {
            sb.append("<option value=''>查詢錯誤</option>");
            ex.printStackTrace();
        }
        return sb.toString();
    }

	static final int[] roleIdArray = new int[] {1,2,3};
	static final String[] roleName = new String[] {"一般使用者","業務管理者","系統管理者"};

	public static String getRoleName(String roleId) {
		int rid = Common.getInt(roleId);
		for (int i=0; i<roleIdArray.length; i++) {
			if (rid==roleIdArray[i]) return roleName[i];
		}
		return "";
	}

	public static String getRoleOption(String selected) {
		int roleId = Common.getInt(selected);
        StringBuilder sb = new StringBuilder().append("<option value=''></option>");
		for (int i=0; i<roleIdArray.length; i++) {
            sb.append("<option value='").append(roleIdArray[i]).append("' ");
            if (roleId == roleIdArray[i]) sb.append(" selected ");
            sb.append(">").append(roleName[i]).append("</option>\n");
		}
		return sb.toString();
	}

	static final String[] stageId = new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
                                                                  //年度-預算 年度-預算案
	static final String[] stageName =  new String[]{"年度-概算","年度-預算案","年度-法定預算","年度追一-概算","年度追一-預算","年度追一-法定預算","年度追二一-概算","年度追二-預算","年度追二-法定預算","年度追三-概算","年度追三-預算","年度追三-法定預算"};
	static final String[] characterId = new String[]{"1","2","3","4"};
	//static final String[] characterName =  new String[]{"經常門","資本門","融資性"};
        static final String[] characterName =  new String[]{"經常門","資本門","融資性","經常資本門併計"};
	private static final String[] schoolType = new String[]{"01","02","03","04"};
	private static final String[] schoolTypeName = new String[]{"高級中學","國民中學","國民小學","幼稚園"};

	/**
	 * 學校類別名稱
	 * @param s = 類別代碼
	 * @return
	 */
	public static String getSchoolTypeName(String s) {
		if (s!=null) {
			for (int i=0; i<schoolType.length; i++) {
				if (s.equals(schoolType[i])) return schoolTypeName[i];
			}
		}
		return "";
	}

	/**
	 * 學校類別選單
	 * @param selectedOne
	 * @return
	 */
	public static String getSchoolTypeOption(String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
		for (int i=0; i<schoolType.length; i++) {
            sb.append("<option value='").append(schoolType[i]).append("' ");
            if (selectedOne!= null && selectedOne.equals(schoolType[i])) {
                sb.append(" selected ");
            }
            sb.append(">").append(schoolTypeName[i]).append("</option>\n");
		}
		return sb.toString();
	}

	/**
	 * 概預算編製階段名稱
	 *
	 * @return
	 */
	public static String getStageName(String s) {
		for (int i=0; i<stageId.length; i++) {
            if (s!=null && s.equals(stageId[i])) return stageName[i];
		}
		return "";
	}

	/**
	 * 取得概預算階段選單
	 * @param selectedOne
	 * @return
	 */
	public static String getStageOption(String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
		for (int i=0; i<stageId.length; i++) {
            sb.append("<option value='").append(stageId[i]).append("' ");
            if (selectedOne!= null && selectedOne.equals(stageId[i])) {
                sb.append(" selected ");
            }
            sb.append(">").append(stageName[i]).append("</option>\n");
		}
		return sb.toString();
	}

	/**
	 * 取得概預算階段選單
	 * <br>例如:不提供追加減階段, 則輸入getStageOption(3,"")
	 * @param endStage : 只顯示到某個階段別
	 * @param selectedOne
	 * @return
	 */
	public static String getStageOption(int endStage, String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
		for (int i=0; i<endStage; i++) {
            sb.append("<option value='").append(stageId[i]).append("' ");
            if (selectedOne!= null && selectedOne.equals(stageId[i])) {
                sb.append(" selected ");
            }
            sb.append(">").append(stageName[i]).append("</option>\n");
		}
		return sb.toString();
	}



	/**
	 * 取得前一個預算階段(主要是用在追加減時抓取原預算數)
	 * @param s
	 * @return
	 */
	public static String getStageBefore(String s) {
		int i = 0;
		if (s!=null && Validate.checkInt(s)) {
			i = Integer.parseInt(s);
			if (i>6 && i<10) return "06";
			else if (i>9) return "09";
		}
		return "03";
	}


	/**
	 * 經資門Id
	 * @param s
	 * @return
	 */
	public static String getCharacterId(String s) {
		for (int i=0; i<characterName.length; i++) {
            if (s.equals(characterName[i])) {
                return characterId[i];
            }
		}
		return "";
	}

	/**
	 * 經資門名稱
	 *
	 * @return
	 */
	public static String getCharacterName(String s) {
		for (int i=0; i<characterId.length; i++) {
            if (s.equals(characterId[i])) {
                return characterName[i];
            }
		}
		return "";
	}

	/**
	 * 取得經資門別選單
	 * @param selectedOne
	 * @return
	 */
	public static String getCharacterOption(String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
		for (int i=0; i<characterId.length; i++) {
            sb.append("<option value='").append(characterId[i]).append("' ");
            if (selectedOne!= null && selectedOne.equals(characterId[i])) {
                sb.append(" selected ");
            }
            sb.append(">").append(characterName[i]).append("</option>\n");
		}
		return sb.toString();
	}


    /**
     * <br>
     * <br>目的：組合html option語法函數
     * <br>參數：(1)sql字串 (2)被選的value
     * <br>傳回：加上html option element
     */
    static public String getOption(String sql, String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
        try {
        
        	List list = null;//PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
        	if (list!=null && list.size()>0) {
        		for (int i=0; i<list.size(); i++) {
        			Object[] obj = (Object[])list.get(i);
                    String id = obj[0].toString();
                    String name = obj[1].toString();

                    sb.append("<option value='").append(id).append("' ");
                    if (selectedOne!= null && selectedOne.equals(id)) {
                        sb.append(" selected ");
                    }
                    sb.append(">").append(name).append("</option>\n");
        		}
        	}
        } catch (Exception ex) {
            sb.append("<option value=''>查詢錯誤</option>");
            ex.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * 傳回：加上html multiple select option element
     * @param sql
     * @param selected = String[]
     * @return
     */
    static public String getOption(String sql, String[] selected) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
        try {
        	List list = null;//PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
        	if (list!=null && list.size()>0) {
        		StringBuilder sb1 = new StringBuilder();
        		StringBuilder sb2 = new StringBuilder();
        		for (int i=0; i<list.size(); i++) {
        			Object[] obj = (Object[])list.get(i);
                    String id = obj[0].toString();
                    String name = obj[1].toString();
                    boolean sFlag = true;
                    if (selected!=null && selected.length>0) {
                        for (int j=0; j<selected.length; j++) {
                        	if (selected[j]!= null && selected[j].equals(id)) {
                                sb1.append("<option value='").append(id).append("' selected");
                                sb1.append(">").append(name).append("</option>\n");
                                sFlag = false;
                        	}
                        }
                    }
                    if (sFlag) {
                        sb2.append("<option value='").append(id).append("'");
                        sb2.append(">").append(name).append("</option>\n");
                    }
        		}
        		sb.append(sb1).append(sb2);
        	}
        } catch (Exception ex) {
            sb.append("<option value=''>查詢錯誤</option>");
            ex.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合html option語法函數
     * <br>參數：(1)sql字串 (2)被選的value
     * <br>傳回：加上html option element
     */
    static public String getSingleOption(String sql, String selectedOne) {
        StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
        HibernateOperations ht = PersistenceServiceGetter.getInstance().getHibernateTemplate();
        try {
        	List list = null;//ht.find(sql);
        	if (list!=null && list.size()>0) {
        		for (int i=0; i<list.size(); i++) {
                    String id = list.get(i).toString();

                    sb.append("<option value='").append(id).append("' ");
                    if (selectedOne!= null && selectedOne.equals(id)) {
                    	sb.append(" selected ");
                    }
                    sb.append(">").append(id).append("</option>\n");
        		}
        	}
        } catch (Exception ex) {
        	sb.append("<option value=''>查詢錯誤</option>");
            ex.printStackTrace();
        }
        return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合Yes or No option語法函數
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getYNOption(String selectedOne) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<option value=''>請選擇</option>");
    	if ("Y".equals(selectedOne)){
    		sb.append("<option value='Y' selected>是</option>");
    		sb.append("<option value='N'>否</option>");
    	}else if ("N".equals(selectedOne)){
    		sb.append("<option value='Y'>是</option>");
    		sb.append("<option value='N' selected>否</option>");
    	}else{
    		sb.append("<option value='Y'>是</option>");
    		sb.append("<option value='N'>否</option>");
    	}
        return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合月份01~12 option語法函數
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getMonthOption(String selectedOne) {
        String sb = "<option value=''>請選擇</option>";
    	for (int i=1; i<13; i++) {
            sb = sb + "<option value='" + Common.formatFrontZero(""+i,2) + "' ";
            if (selectedOne!= null && selectedOne.equals(Common.formatFrontZero(Integer.toString(i),2))) {
                sb = sb + " selected ";
            }
            sb = sb + ">" + Common.formatFrontZero(""+i,2) + "</option>\n";
    	}
        return sb;
    }

    /**
     * <br>
     * <br>目的：組合1~100百分比option語法函數
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getPercentageOption(String selectedOne) {
        String sb = "<option value=''>請選擇</option>";
    	for (int i=1; i<101; i++) {
            sb = sb + "<option value='" + i + "' ";
            if (selectedOne!= null && selectedOne.equals(""+i)) {
                sb = sb + " selected ";
            }
            sb = sb + ">" + i + "%</option>\n";
    	}
        return sb;
    }

    /**
     * <br>
     * <br>目的：組合0~100的option語法函數
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getHundredOption(String selectedOne) {
        String sb = "<option value=''>請選擇</option>";
    	for (int i=0; i<101; i++) {
            sb = sb + "<option value='" + i + "' ";
            if (selectedOne!= null && selectedOne.equals(""+i)) {
                sb = sb + " selected ";
            }
            sb = sb + ">" + i + "</option>\n";
    	}
        return sb;
    }

    /**
     * <br>
     * <br>目的：組合小時0~23 option語法函數
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getHourOption(String selectedOne) {
        String sb = "<option value=''>請選擇</option>";
    	for (int i=0; i<24; i++) {
            sb = sb + "<option value='" + i + "' ";
            if (selectedOne!= null && selectedOne.equals(Integer.toString(i))) {
                sb = sb + " selected ";
            }
            sb = sb + ">" + i + "</option>\n";
    	}
        return sb;
    }

    /**
     * <br>
     * <br>目的：組合分鐘0~59 option語法函數
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getMinuteOption(String selectedOne) {
        String sb = "<option value=''>請選擇</option>";
    	for (int i=0; i<60; i++) {
            sb = sb + "<option value='" + i + "' ";
            if (selectedOne!= null && selectedOne.equals(Integer.toString(i))) {
                sb = sb + " selected ";
            }
            sb = sb + ">" + i + "</option>\n";
    	}
        return sb;
    }


    /**
     * <br>
     * <br>目的：組合textOption option語法函數，分隔符號';'，第一個表value，第2個表option
     * <br>參數：被選的value
     * <br>傳回：加上html option element
     */
    static public String getTextOption(String textOption , String selectedOne) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<option value=''>請選擇</option>");
    	String[] stestOption = textOption.split(";");
    	for(int i=0 ; i<stestOption.length;i++){
    	    if(stestOption[i].equals(selectedOne))
    	        sb.append("<option value='"+stestOption[i]+"' selected>"+stestOption[i+1]+"</option>");
    	    else
    	        sb.append("<option value='"+stestOption[i]+"' >"+stestOption[i+1]+"</option>");
    	    i++;
    	}
        return sb.toString();
    }

    public static String getPopCode(String className, String inputName, String inputValue, String chineseDesc, String accYear, String preWord, String fullCode, String fullCodeValue) {
    	return getPopCode(className, inputName, inputValue, chineseDesc, accYear, preWord, fullCode, fullCodeValue, 20);
    }
    public static String getPopCode(String className, String inputName, String inputValue, String chineseDesc, String accYear, String preWord, String fullCode, String fullCodeValue, int nameFieldSize) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	//popId,accYear,popKind,popCode,popCodeName,js
    	sb.append("<input class=\"" + className + "\" type=\"hidden\" id=\"" + inputName + "\" name=\"" + inputName + "\" value=\"" + inputValue + "\">");
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+fullCode+"\" size=\"5\"");
    	sb.append(" maxlength=\"10\" value=\""+fullCodeValue+"\" onchange=\"getCodeName('" + inputName + "','" + accYear + "','" + preWord + "',this,'" + inputName + "Name');\">\n");
    	sb.append("<input class=\"" + className + ROStr + "\" type=\"text\" id=\"" + inputName + "Name\" name=\"" + inputName + "Name\" size=\"" + nameFieldSize + "\" maxlength=\"50\" value=\"" + chineseDesc + "\">\n");
    	if (!("".equals(ROStr))) {
    		sb.append("<input class=\"" + className + "\" type=\"button\" id=\"btn_" + inputName + "\" name=\"btn_" + inputName + "\" onclick=\"popCode('" + inputName + "','" + accYear + "','" + preWord + "','" + fullCode + "','" + inputName + "Name')\" value=\"...\" title=\"代碼輸入輔助視窗\">");
    	}
    	return sb.toString();
    }


    /**
     * <br>
     * <br>目的：組合popOrgan語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回機關代碼的欄位名稱 (3)inputValue 機關代碼的值 (4)chineseDesc機關代碼的中文說明 (5)isLimit是否顯示全部機關名稱
     * <br>傳回：加上html option element
     */
    static public String getPopOrgan(String className, String inputName, String inputValue, String chineseDesc) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"10\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popOrgan('"+inputName+"','"+inputName+"Name','N')\" value=\"...\" title=\"機關輔助視窗\">\n");
    	return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合popOrgan語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回機關代碼的欄位名稱 (3)inputValue 機關代碼的值 (4)chineseDesc機關代碼的中文說明 (5)isLimit是否顯示全部機關名稱
     * <br>傳回：加上html option element
     */
    static public String getPopOrgan(String className, String inputName, String inputValue, String chineseDesc,String isLimit) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"10\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popOrgan('"+inputName+"','"+inputName+"Name','"+isLimit+"')\" value=\"...\" title=\"機關輔助視窗\">\n");
    	return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合popOrgan語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回機關代碼的欄位名稱 (3)inputValue 機關代碼的值 (4)chineseDesc機關代碼的中文說明 (5)isLimit是否顯示全部機關名稱
     * <br>傳回：加上html option element
     */ 
    static public String getPopOrgan(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String manageOrgan, String code) { 
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	
    	sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Fullcode\" size=\"13\" maxlength=\"13\" value=\""+code+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"2\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"70\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"50\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popOrgan('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + manageOrgan + "')\" value=\"...\" title=\"機關輔助視窗\">\n");
    	return sb.toString();
    }
    /**
     * <br>
     * <br>目的：組合popOrgan語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回機關代碼的欄位名稱 (3)inputValue 機關代碼的值 (4)chineseDesc機關代碼的中文說明 (5)isLimit是否顯示全部機關名稱
     * <br>傳回：加上html option element
     */ 
    static public String getPopOrgan(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String manageOrgan) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	
    	//sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Fullcode\" size=\"13\" maxlength=\"13\" value=\""+code+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"2\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"70\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"70\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popOrgan('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + manageOrgan + "')\" value=\"...\" title=\"機關輔助視窗\">\n");
    	return sb.toString();
    }
    /**
     * 目的：組合popIncomeSubject語法函數 = 歲入科目輔助視窗
     *
     * @param className : css的class名稱
     * @param inputName : 傳回科目代碼的欄位名稱
     * @param inputValue : 科目代碼的值
     * @param chineseDesc : 科目名稱
     * @param isLimit
     * @param accYear
     * @param fullCode
     * @param characterId
     * @return
     */
    static public String getPopIncomeSubject(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue, String characterId) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+fullCode+"\" size=\"8\"");
    	sb.append(" maxlength=\"8\" value=\""+fullCodeValue+"\" onchange=\"getIncomeSubject('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "',this);\">\n");

    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"2\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	if("".equals(ROStr))
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	else
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    		//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popIncomeSubject('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "')\" value=\"...\" title=\"歲入科目代碼輔助視窗\">\n");
    	return sb.toString();
    }
    static public String getPopIncomeSubject2(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue, String characterId,String js) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+fullCode+"\" size=\"8\"");
    	sb.append(" maxlength=\"8\" value=\""+fullCodeValue+"\" onchange=\"getIncomeSubject2('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "',this);"+js+"\">\n");

    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"2\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	if("".equals(ROStr))
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	else
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    		//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popIncomeSubject2('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "','"+js+"')\" value=\"...\" title=\"歲入科目代碼輔助視窗\">\n");
    	return sb.toString();
    }
    /**
     * 目的：組合popASType語法函數 = 歲出科目輔助視窗
     *
     * @param className
     * @param inputName
     * @param inputValue
     * @param chineseDesc
     * @param isLimit
     * @param accYear
     * @param fullCode
     * @param fullCodeValue
     * @param characterId
     * @return
     */
    static public String getPopASType(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue, String characterId) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+fullCode+"\" size=\"8\"");
    	sb.append(" maxlength=\"10\" value=\""+fullCodeValue+"\" onchange=\"getASType('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "',this);\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"10\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	if("".equals(ROStr))
    		sb.append("<input class=\""+ className + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	else
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    		//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popASType('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "')\" value=\"...\" title=\"歲出科目代碼輔助視窗\">\n");
    	return sb.toString();
    }
    /**
     * 目的：組合PopASOR語法函數 = 歲出科目輔助視窗
     *
     * @param className
     * @param inputName
     * @param inputValue
     * @param chineseDesc
     * @param isLimit
     * @param accYear
     * @param fullCode
     * @param fullCodeValue
     * @param characterId
     * @return
     */
    static public String getPopASOR(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue, String characterId) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+fullCode+"\" size=\"8\"");
    	sb.append(" maxlength=\"10\" value=\""+fullCodeValue+"\" onchange=\"getASType('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + characterId + "',this);\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"10\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	if("".equals(ROStr))
    		sb.append("<input class=\""+ className + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	else
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    		//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popSelectForm()\" value=\"...\" title=\"歲出科目代碼輔助視窗\">\n");
    	return sb.toString();
    }

    /**
     * 目的：組合popASType語法函數 = 歲出科目輔助視窗
     *
     * @param className
     * @param inputName
     * @param inputValue
     * @param chineseDesc
     * @param isLimit
     * @param accYear
     * @param fullCode
     * @param fullCodeValue
     * @param characterId
     * @return
     */
    static public String getPopCodePersonnel(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue, String persn, String moneyField) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+fullCode+"\" size=\"8\"");
    	sb.append(" maxlength=\"10\" value=\""+fullCodeValue+"\" onchange=\"getCodePersonnel('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + persn + "','" + moneyField + "',this);\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"10\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	if("".equals(ROStr))
    		sb.append("<input class=\""+ className + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	else
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    		//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popCodePersonnel('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','" + persn + "','" + moneyField + "')\" value=\"...\" title=\"人事費標準代碼輔助視窗\">\n");
    	return sb.toString();
    }

    /**
     * 目的：組合popSubjectUsage語法函數 = 歲出用途別輔助視窗
     *
     * @param className : css的class名稱
     * @param inputName : 傳回科目代碼的欄位名稱
     * @param inputValue : 科目代碼的值
     * @param chineseDesc : 科目名稱
     * @param isLimit
     * @param accYear
     * @param fullCode
     * @return
     */
    static public String getPopSubjectUsage(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue) {
    	return getPopSubjectUsage(className, inputName, inputValue, chineseDesc,isLimit,accYear,fullCode,fullCodeValue,"","");
    }
    static public String getPopSubjectUsage(String className, String inputName, String inputValue, String chineseDesc,String isLimit, String accYear, String fullCode, String fullCodeValue, String jsGet, String jsPop) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")!=-1) ROStr = "";
    	jsGet = Common.get(jsGet);
    	jsPop = Common.get(jsPop);
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+fullCode+"\" size=\"6\"");
    	sb.append(" maxlength=\"6\" value=\""+fullCodeValue+"\" onchange=\"getSubjectUsage('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "',this);").append(jsGet).append("\">\n");

    	sb.append("<input class=\""+ className +"\" type=\"hidden\" name=\""+inputName+"\" size=\"2\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	if("".equals(ROStr))
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    	else
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">\n");
    		//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"45\" maxlength=\"256\" value=\""+chineseDesc+"\">]\n");
    	if(!"".equals(ROStr))
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popSubjectUsage('"+inputName+"','"+inputName+"Name','"+isLimit+"','" + accYear + "','" + fullCode + "','").append(jsPop).append("')\" value=\"...\" title=\"歲出用途別輔助視窗\">\n");
    	return sb.toString();
    }


    /**
     * <br>
     * <br>目的：組合PopProperty語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回財產編號的欄位名稱 (3)inputValue 財產編號的值 (4)chineseDesc財產編號的中文說明 (5)preWord財產編號的前置詞
     * <br>傳回：加上html option element
     */
    static public String getPopProperty(String className, String inputName, String inputValue, String chineseDesc, String preWord) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if (className.indexOf("RO")>0) {
    		ROStr="";
    		sb.append("[<input class=\""+ className +"\" type=\"text\" name=\""+inputName+"\" size=\"10\" maxlength=\"11\" value=\""+inputValue+"\">");
    		sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
    	} else {
    		sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+inputName+"\" size=\"10\" maxlength=\"11\" value=\""+inputValue+"\" onBlur=\"getProperty('"+inputName+"','"+inputName+"Name','"+preWord+"');\">\n");
        	if(!"".equals(ROStr)) sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popProperty('"+inputName+"','"+inputName+"Name','"+ preWord +"')\" value=\"...\" title=\"財產編號輔助視窗\">\n");
        	sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
    	}
        return sb.toString();
    }


    /**
     * <br>
     * <br>目的：組合popStore語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回廠商編號的欄位名稱 (3)inputValue 廠商編號的值 (4)chineseDesc廠商編號的中文說明
     * <br>傳回：加上html option element
     */
    static public String getPopStoreNo(String className, String inputName, String inputValue, String chineseDesc) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+inputName+"\" size=\"10\" maxlength=\"10\" value=\""+inputValue+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popStore('"+inputName+"','"+inputName+"Name')\" value=\"...\" title=\"廠商輔助視窗\">\n");
    	sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
        return sb.toString();
    }


    /**
     * <br>
     * <br>目的：組合popDocNo語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回文號編號的欄位名稱 (3)inputValue 文號編號的值 (4)chineseDesc文號編號的中文說明
     * <br>傳回：加上html option element
     */
    static public String getPopDocNo(String className, String inputName, String inputValue, String chineseDesc) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+inputName+"\" size=\"5\" maxlength=\"5\" value=\""+inputValue+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popDocNo('"+inputName+"','"+inputName+"Name')\" value=\"...\" title=\"文號輔助視窗\">\n");
    	sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
        return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合popKeepUnit語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回保管單位編號的欄位名稱 (3)inputValue 保管單位編號的值 (4)chineseDesc保管單位編號的中文說明
     * <br>傳回：加上html option element
     */
    static public String getPopKeepUnit(String className, String inputName, String inputValue, String chineseDesc) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+inputName+"\" size=\"5\" maxlength=\"5\" value=\""+inputValue+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popKeepUnit('"+inputName+"','"+inputName+"Name')\" value=\"...\" title=\"保管單位輔助視窗\">\n");
    	sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"Name\" size=\"20\" maxlength=\"50\" value=\""+chineseDesc+"\">]\n");
        return sb.toString();
    }
    /**
     * <br>
     * <br>目的：組合popCalndar語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:傳回日期的欄位名稱 (3)inputValue 日期的值
     * <br>傳回：加上html option element
     */
    static public String getPopCalndar(String className, String inputName, String inputValue) {
    	StringBuilder sb = new StringBuilder();
    	sb.append("<input class=\""+ className +"\" type=\"text\" name=\""+inputName+"\" size=\"7\" maxlength=\"7\" value=\""+inputValue+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"popCalndar('"+inputName+"')\" value=\"...\" title=\"萬年曆輔助視窗\">\n");
        return sb.toString();
    }

    /**
     * <br>
     * <br>目的：組合popUpload語法函數
     * <br>參數：(1)className:css的class名稱 (2)inputName:檔案上傳欄位名稱 (3)inputValue 檔案名稱
     * <br>傳回：一個檔案上傳欄位, 上傳及下載檔案按鈕各一個
     * <br>2005/10/22
     */
    static public String getPopUpload(String className, String inputName, String inputValue) {
    	StringBuilder sb = new StringBuilder();
    	String ROStr="RO";
    	if ("field".equals(className)) ROStr="_RO";
    	else if ("field_RO".equals(className)) ROStr="";
    	//sb.append("[<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"\" size=\"20\" maxlength=\"300\" value=\""+inputValue+"\">]\n");
    	sb.append("<input class=\""+ className + ROStr + "\" type=\"text\" name=\""+inputName+"\" size=\"20\" maxlength=\"300\" value=\""+inputValue+"\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"\" onclick=\"openUploadWindow('"+inputName+"','"+inputValue+"');\" value=\"上傳檔案\">\n");
    	sb.append("<input class=\""+ className +"\" type=\"button\" name=\"btn_"+inputName+"Download\" onclick=\"openDownloadWindow(form1."+inputName+".value);\" value=\"下載檔案\">\n");
    	return sb.toString();
    }


  	/**
  	 * <br>
  	 * <br>目的：組合查詢列表之html
  	 * <br>參數：(1)主鍵之index (2)顯示之index (3)列表集合 (4)是否有查詢旗標
  	 * <br>傳回：傳回列表之html字串
  	*/
    public static String getQuerylist(boolean primaryArray[], boolean displayArray[],
    		ArrayList objList, String queryAllFlag) {
    	StringBuilder sb = new StringBuilder();
    	if (objList!=null && objList.size()>0) {
	    	int i;
	    	int counter=0;
	    	boolean trFlag = false;
    		String rowArray[]=new String[primaryArray.length];
			java.util.Iterator it=objList.iterator();
			boolean even = true;
			String pk="";
			while(it.hasNext()){
				rowArray= (String[])it.next();
				counter++;
				String classTR="listTROdd", classTD = "listTDOdd";

				if (even) {
					classTR = "listTREven";
					classTD = "listTDEven";
				}
				pk = "";
				for(i=0;i<primaryArray.length;i++){
					if (primaryArray[i]) pk+=rowArray[i];
				}
				//顯示TR
				sb.append(" <tr id=\"").append("listContainerRow").append(pk).append("\"");
				sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"listContainerRowClick('").append(pk).append("');queryOne(");

				for(i=0;i<primaryArray.length;i++){
					if (primaryArray[i]){
						if(trFlag){
							sb.append(",'").append(rowArray[i]).append("'");
						}else{
							sb.append("'").append(rowArray[i]).append("'");
							trFlag = true;
						}
					}
				}
				sb.append(");\" >\n");

				//顯示TD
				sb.append(" <td class='").append(classTD).append("' >").append(counter).append(".</td>\n");
				for(i=0;i<displayArray.length;i++){
					if (displayArray[i]){
						sb.append(" <td class='").append(classTD).append("' >").append(Common.get(rowArray[i])).append("</td>\n");
					}
				}
				sb.append("</tr>\n");
				trFlag = false;
				even = !even;
			}
    	} else {
    		sb.append(" <tr class='listTREven' ><td class='listTDEven' colspan='100'>&nbsp;</td></tr>");
    	}
		return sb.toString();
    }

  	/**
  	 * <br>
  	 * <br>目的：組合查詢列表之html
  	 * <br>參數：(1)主鍵之index (2)顯示之index (3)列表集合對齊方式 (4)列表集合 (5)是否有查詢旗標
  	 * <br>傳回：傳回列表之html字串
  	*/
    public static String getQuerylist(boolean primaryArray[], boolean displayArray[], String[] arrAlign,
    		ArrayList objList, String queryAllFlag) {
    	int i, counter=0;
    	boolean trFlag = false;
    	boolean even = true;
    	StringBuilder sb = new StringBuilder();
    	if (objList!=null && objList.size()>0) {
			String rowArray[]=new String[primaryArray.length];
			java.util.Iterator it=objList.iterator();
			String pk = "";
			while(it.hasNext()) {
				rowArray= (String[])it.next();
				counter++;
				String classTR="listTROdd", classTD = "listTDOdd";

				if (even) {
					classTR = "listTREven";
					classTD = "listTDEven";
				}
				pk = "";
				for(i=0;i<primaryArray.length;i++){
					if (primaryArray[i]) pk+=rowArray[i];
				}
				//顯示TR
				sb.append(" <tr id=\"").append("listContainerRow").append(pk).append("\"");
				sb.append(" class='").append(classTR).append("' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='").append(classTR).append("'\" onClick=\"listContainerRowClick('").append(pk).append("');queryOne(");
				for(i=0;i<primaryArray.length;i++){
					if (primaryArray[i]) {
						if (trFlag) {
							sb.append(",'").append(rowArray[i]).append("'");
						} else {
							sb.append("'").append(rowArray[i]).append("'");
							trFlag = true;
						}
					}
				}
				sb.append(");\" >\n");

				//顯示TD
				sb.append(" <td class='").append(classTD).append("' >").append(counter).append(".</td>\n");
				for(i=0;i<displayArray.length;i++){
					if (displayArray[i]) sb.append(" <td class='").append(classTD).append("' style=\"text-align:").append(arrAlign[i]).append("\">").append(Common.get(rowArray[i])).append("</td>\n");
				}
				sb.append("</tr>\n");
				trFlag = false;
				even = !even;
			}
    	} else {
    		sb.append(" <tr class='listTREven' ><td class='listTDEven' colspan='100'>&nbsp;</td></tr>");
    	}
		return sb.toString();
    }


    /**
     * 取得page用的 query list,移除了一般query list NO 欄位
     * @param primaryArray
     * @param displayArray
     * @param objList
     * @param queryAllFlag
     * @return
     */
    public static String getPageQuerylist(boolean primaryArray[], boolean displayArray[],
            ArrayList objList, String queryAllFlag) {
        int i;
        int counter=0;
        boolean trFlag = false;
        StringBuilder sb = new StringBuilder();
    	StringBuilder sbQueryOne = new StringBuilder("");

        if ("true".equals(queryAllFlag) && objList.size()==0){
            //sb.append(" <tr class='listTR' ><td class='listTD' colspan='100' style='color:red'>查無資料，請您重新輸入查詢條件！</td></tr>");
            sb.append(" <tr class='listTR' ><td class='listTD' colspan='100'>&nbsp;</td></tr>");
        }else{
            String rowArray[]=new String[primaryArray.length];
            java.util.Iterator it=objList.iterator();
            while(it.hasNext()){
                rowArray= (String[])it.next();
                counter++;
                //顯示TR
                sb.append(" <tr class='listTR' onmouseover=\"this.className='listTRMouseover'\" onmouseout=\"this.className='listTR'\" onClick=\"queryOne(");
				if (counter==1) {
					sbQueryOne.append("<script type='text/javascript'>if (form1.state.value=='queryAllSuccess') { try { queryOne(");
				}
                for(i=0;i<primaryArray.length;i++){
                    if (primaryArray[i]){
                        if(trFlag){
                            sb.append(",'"+rowArray[i]+"'");
							if (counter==1) {
								sbQueryOne.append(",'"+rowArray[i]+"'");
							}
                        }else{
                            sb.append("'"+rowArray[i]+"'");
							if (counter==1) {
								sbQueryOne.append("'"+rowArray[i]+"'");
							}
                            trFlag = true;
                        }

                    }
                }
                sb.append(")\" >\n");
				if (counter==1) sbQueryOne.append("); } catch(e) {  }\n\n}</script>");

                //顯示TD
                for(i=0;i<displayArray.length;i++){
                    if (displayArray[i]){
                        sb.append(" <td class='listTD' >"+Common.get(rowArray[i])+"</td>\n");
                    }
                }
                sb.append("</tr>\n");
                trFlag = false;
            }
        }
		return sb.append(sbQueryOne).toString();
    }

    /**
     * 組出有checkbox的query List
     *
     * @param primaryArray
     * @param displayArray
     * @param arrAlign
     * @param objList
     * @param queryAllFlag
     * @param checkboxName
     * @return
     */
    public static String getCheckboxQuerylist(boolean primaryArray[], boolean displayArray[], String[] arrAlign,
            ArrayList objList, String queryAllFlag, String checkboxName) {
    	return getCheckboxQuerylist(primaryArray, displayArray, arrAlign, objList, queryAllFlag, checkboxName, null, null);
    }

    public static String getCheckboxQuerylist(boolean primaryArray[], boolean displayArray[], String[] arrAlign,
    		ArrayList objList, String queryAllFlag, String checkboxName, boolean linkArray[], String target) {
    	int i, counter=0;
    	boolean trFlag = false, targetFlag = false;
    	//boolean even = true;
    	StringBuilder sb = new StringBuilder();
    	if (objList!=null && objList.size()>0) {
			String rowArray[]=new String[primaryArray.length];
			java.util.Iterator it=objList.iterator();
			while(it.hasNext()) {
				rowArray= (String[])it.next();
				counter++;
//				String classTR="listTROdd", classTD = "listTDOdd";
//				if (even) {
//					classTR = "listTREven";
//					classTD = "listTDEven";
//				}
				//顯示TR
				//sb.append(" <tr class='highLight' onClick=\"queryOne(");
				sb.append(" <tr class='highLight' >");
				StringBuilder v = new StringBuilder().append("");
				for(i=0;i<primaryArray.length;i++){
					if (primaryArray[i]) {
						if (trFlag) {
							v.append(",'").append(rowArray[i]).append("'");
						} else {
							v.append("'").append(rowArray[i]).append("'");
							trFlag = true;
						}
					}
				}
				//if (target!=null && !"".equals(Common.get(target))) v.append(",'").append(target).append("'");
				//sb.append(v).append(")\" >\n");


				//顯示TD
				sb.append(" <td class='listTD' >").append("<input type='checkbox' id=\"").append(checkboxName).append("\" name=\"").append(checkboxName).append("\" value=\"").append(v.toString().replaceAll("'", "")).append("\"></td>\n");
				targetFlag = false;
				for(i=0;i<displayArray.length;i++){
					if (displayArray[i]) {
						if (targetFlag==false && target!=null && !"".equals(Common.get(target))) {
							v.append(",'").append(target).append("'");
							targetFlag = true;
						}

						if (arrAlign!=null && arrAlign.length>0) {
							sb.append(" <td class='listTD' style=\"text-align:").append(arrAlign[i]).append("\">"); //.append(Common.get(rowArray[i])).append("</td>\n");
						} else {
							sb.append(" <td class='listTD' >");
						}
						if (linkArray!=null && linkArray[i]) {
							sb.append("<a href='#' class='sLink2' onClick=\"queryOne(").append(v).append(")\"");
							//if (target!=null && !"".equals(Common.get(target))) sb.append(" target=\"").append(target).append("\"");
							sb.append(">").append(Common.get(rowArray[i])).append("</a>");
						} else sb.append(Common.get(rowArray[i]));

						sb.append("</td>\n");
					}
				}
				sb.append("</tr>\n");
				trFlag = false;
			}
    	} else {
    		if ("true".equals(queryAllFlag)) sb.append(" <tr class='highLight' ><td class='listTD' colspan='100'>查無資料，請您重新輸入查詢條件！</td></tr>");
    	}
		return sb.toString();
    }

    public static Object getObject(String sql) {
        try {
        	List list = null;//PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
        	if (list!=null && list.size()>0) {
        		return list.get(0);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return null;
    }

    public static Object[] getObjects(String sql) {
        try {
        	List list = null;//PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
        	if (list!=null && list.size()>0) {
        		return (Object[])list.get(0);
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return null;
    }

    public static String getLookupField(String sql) {
        try {
        	List list = null;//PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql);
        	if (list!=null && list.size()>0) {
        		return Common.get(list.get(0));
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return "";
    }

    public static String getLookupField(String sql, Object id) {
        try {
        	List list = null;//PersistenceServiceGetter.getInstance().getHibernateTemplate().find(sql,id);
        	if (list!=null && list.size()>0) {
        		return Common.get(list.get(0));
        	}
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    	return "";
    }
    public static String getVerify(String selectedOne) {
        return View.getTextOption("N;未審核;Y;已審核",selectedOne);
    }
    
    public static String getCloseCase(String selectedOne) {
        return View.getTextOption("Y;結案;N;未結案",selectedOne);
    }   
    
    /**
     * 優先順序選單
     * @param selectedOne
     * @return
     */
    private static final String[] SeqType = new String[]{
                                                         "1","2","3","4","5",
                                                         "6","7","8","9","10",
                                                         "11","12","13","14","15",
                                                         "16","17","18","19","20"
                                                         };
    public static String getSeqTypeOption(String selectedOne) {
    StringBuilder sb = new StringBuilder().append("<option value=''>請選擇</option>");
            for (int i=0; i<SeqType.length; i++) {
        sb.append("<option value='").append(SeqType[i]).append("' ");
        if (selectedOne!= null && selectedOne.equals(SeqType[i])) {
            sb.append(" selected ");
        }
        sb.append(">").append(SeqType[i]).append("</option>\n");
            }
            return sb.toString();
    	}
    public static String getAutoCode(String classname,
    		                         String codename,String fullcode,
    		                         String fullname,String vfullname,
    		                         String undertakeDeptId,
    		                         String insid,String undertakeDeptIdcode,String undertakeDeptIdname,
    		                         String undertakeDeptIdValue,String insidValue,String js) {
    	StringBuilder sb = new StringBuilder(); 
    	sb.append("<input class=\""+classname+"\"    type=\"text\"   name=\""+codename+"\" size=\"15\" maxlength=\"15\" value=\""+fullcode+"\" onChange=\"getAutofullcode('"+codename+"','"+fullname+"_fullname','"+undertakeDeptId+"','"+insid+"','"+undertakeDeptIdcode+"','"+undertakeDeptIdname+"')\">\n");
    	sb.append("<select class=\""+classname+"\" type=\"select\"   name=\""+fullname+"_fullname\" onChange=\"getSelectAutofullcode('"+fullname+"_fullname','"+undertakeDeptId+"','"+undertakeDeptIdcode+"','"+undertakeDeptIdname+"')\">\n");
    	sb.append(" <script>"+js+"</script> \n");
    	sb.append("</select> \n");
    	//sb.append("<input class=\"field\"    type=\"hidden\" name=\""+undertakeDeptId+"\" size=\"70\" maxlength=\"256\" value=\""+undertakeDeptIdValue+"\">\n");
    	sb.append("<input class=\"field\"    type=\"hidden\" name=\""+insid+"\" size=\"70\" maxlength=\"256\" value=\""+insidValue+"\">\n");
    	return sb.toString();
       } 
    public static String getPlanCode(String classname,String planCode,String planCodeValue,String planName,String planNameValue,String planId,String planIdValue,String dId,String uId,String iId) {
    	StringBuilder sb = new StringBuilder(); 
    	sb.append("<input class=\""+classname+"\" type=\"text\" name=\""+planCode+"\" size=\"15\" maxlength=\"15\" value=\""+planCodeValue+"\"  onChange=\"getAutoplanCode('"+planName+"','"+planCode+"','"+dId+"','"+uId+"','"+iId+"')\">\n");
    	sb.append("<input class=\"field_RO\" type=\"text\" name=\""+planName+"\" size=\"70\" maxlength=\"256\" value=\""+planNameValue+"\">\n");
    	sb.append("<input class=\""+classname+"\" type=\"button\" name=\"btn_"+planId+"\" value=\"...\" onClick=\"getPlan('"+planCode+"','"+planName+"','"+planId+"','"+dId+"','"+uId+"','"+iId+"')\">\n");
    	sb.append("<input class=\"field_RO\" type=\"hidden\" name=\""+planId+"\" size=\"10\" maxlength=\"10\" value=\""+planIdValue+"\">\n");
    	return sb.toString();
       }
    public static String getPlanCode2(String classname,String planCode,String planCodeValue,String planName,String planNameValue,String planId,String planIdValue,String dId,String uId,String iId,String js) {
    	StringBuilder sb = new StringBuilder(); 
    	sb.append("<input class=\""+classname+"\" type=\"text\" name=\""+planCode+"\" size=\"15\" maxlength=\"15\" value=\""+planCodeValue+"\"  onChange=\"getAutoplanCode('"+planName+"','"+planCode+"','"+dId+"','"+uId+"','"+iId+"')\">\n");
    	sb.append("<input class=\"field_RO\" type=\"text\" name=\""+planName+"\" size=\"70\" maxlength=\"256\" value=\""+planNameValue+"\">\n");
    	sb.append("<input class=\""+classname+"\" type=\"button\" name=\"btn_"+planId+"\" value=\"...\" onClick=\""+js+"getPlan('"+planCode+"','"+planName+"','"+planId+"','"+dId+"','"+uId+"','"+iId+"')\">\n");
    	sb.append("<input class=\"field_RO\" type=\"hidden\" name=\""+planId+"\" size=\"10\" maxlength=\"10\" value=\""+planIdValue+"\">\n");
    	return sb.toString();
       }
}
