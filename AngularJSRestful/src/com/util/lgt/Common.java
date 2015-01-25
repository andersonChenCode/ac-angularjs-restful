/*
*<br>程式目的：常用函數
*<br>程式代號：Common
*<br>撰寫日期：93/12/01
*<br>程式作者：KangDa Info
*<br>--------------------------------------------------------
*<br>修改作者　　修改日期　　　修改目的
*<br>--------------------------------------------------------
*<br>
*/

package com.util.lgt;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;



public class Common {
	
	private Common() {
		 // avoid instantiation...
	}
	
  	/**
  	 * <br>
   	 * <br>目的：撰寫JavaBean get方法時所需套用的函數
   	 * <br>參數：資料字串
     * <br>傳回：檢查資料為null,是則傳回空字串
  	*/
	static public String get(String s){
		if(s==null)return "";
		else return s.trim();		
	}
	static public String get(Object s){
		if(s==null) return "";
		else return s.toString().trim();		
	}	
	static public String get(int s){
		return get(""+s);
	}
	static public Double get(Double d){
		if(d!=null) return d;
		else return 0d;		
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
	
	static public String checkSet(String s){
		if(s==null){
			return "";
		} else {
			return s.trim();
		}
	}	
	
	static public String checkGet(String s){
		if(s==null){
			return "";
		}else{
			s = s.trim();	
			//s = s.replaceAll("<", "&lt;");
			//s = s.replaceAll(">","&gt;");
			//s = org.apache.commons.lang.StringEscapeUtils.escapeHtml(s);
			//return s;
			return s;
		}
	}	
	
    /**
     * <br>
     * <br>目的：組合sql語法時字串需加單引號的函數 
     * <br>參數：資料字串 
     * <br>傳回：加上單引號傳回資料字串 
     */
    static public String sqlChar(String s) {
        if (s == null || s .equals("")) { return "''"; }
        return "'" + s.trim() + "'";
    }
    
    /**
     * 這個函式主要用在報表
     * <br>例如：某個欄位x可以顯示的寬度是20個字，但需要縮排5個字，其使用範例如下：
     * <br>alignString(x,20-5,"五個縮排使用的字元(不管半型或全型都算一個字)")
     * @param s
     * @param size
     * @param padStr
     * @return
     */
    static public String alignString(String s, int size, String padStr) {
    	s = escapeReturnChar(s);
    	int len = s.length();
    	if (len>size) {    		
    		int loopLen = len/size;
    		if ((len % size) > 0 ) loopLen = loopLen + 1;
    		StringBuilder sb = new StringBuilder();    		
    		for (int i=0; i<loopLen; i++) {
    			if (i==0) sb.append(padStr).append(StringUtils.substring(s, i*size, (i+1)*size>len?len:(i+1)*size));
    			else sb.append("\n").append(padStr).append(StringUtils.substring(s, i*size, (i+1)*size>len?len:(i+1)*size));
    			//sb.append(padStr).append(StringUtils.substring(s, i*size, (i+1)*size>len?len:(i+1)*size)).append("\n");
    		}
    		return sb.toString();
    	} else return padStr + s;
    }        
    
    /**
     * 很多時候我們需要自動產生資料的key值,而用vmid是個不錯的選擇..
     * @return modifyVMID
     * @see java.rmi.dgc.VMID()
     */
    public static String getVMID() {
		return new java.rmi.dgc.VMID().toString().replaceAll(":","_");
    }

    /**
     * <br>
     * <br>目的：組合sql語法時字串需加單引號的函數 
     * <br>參數：資料字串 
     * <br>傳回：加上單引號傳回資料字串 
     */
    static public String sqlInt(String s) {
        if (s == null) { return "'0'"; }
        return "'" + s.trim() + "'";
    }
    
  	/**
  	 * <br> 
  	 * <br>目的：將字串BIG5轉為ISO1
  	 * <br>參數：資料字串
  	 * <br>傳回：傳回ISO1字串
  	*/
    public static String big5ToIso(String s){
     	if (s==null){ s=""; }
    	try {
    		s=new String(s.getBytes("big5"),"ISO-8859-1");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s; 
    }    
    public static String big5ToUTF8(String s){
     	if (s==null){ s=""; }
    	try {
    		s=new String(s.getBytes("big5"),"utf-8");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s; 
    }            
    public static String utf8ToIso(String s){
     	if (s==null){ s=""; }
    	try {
    		s=new String(s.getBytes("utf-8"),"ISO-8859-1");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s; 
    }    
    
  	/**
  	 * <br> 
  	 * <br>目的：將字串ISO1轉為BIG5
  	 * <br>參數：資料字串
  	 * <br>傳回：傳回BIG5字串
  	*/
    public static String isoToBig5(String s) {
     	if (s==null){ s=""; }
    	try {
			s=new String(s.getBytes("ISO-8859-1"),"big5");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s;        
    }
    
    public static String isoToUTF8(String s) {
     	if (s==null){ s=""; }
    	try {
			s=new String(s.getBytes("ISO-8859-1"),"utf-8");
    	} catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }   
	    return s;        
    }    
    
    /**
     * Turn a bytearray into a printable form, representing
     * each byte in hex.
     *
     * @param data the bytearray to stringize
     * @return a hex-encoded printable representation of <code>data</code>
     */
    public static String toHexString(byte[] data) {
        StringBuffer sb = new StringBuffer(data.length * 2);
        for (int i = 0; i < data.length; ++i)
        {
            sb.append(Integer.toHexString((data[i] >> 4) & 15));
            sb.append(Integer.toHexString(data[i] & 15));
        }
        return sb.toString();
    }
    
    /**
     * Encode a string as UTF-8.
     *
     * @param str the string to encode
     * @return the UTF-8 representation of <code>str</code>
     */
    public static byte[] encodeUTF8(String str) {
        // It turns out that under 1.4.2, at least, calling getBytes() is
        // faster than rolling our own converter (it uses direct buffers and, I suspect,
        // a native helper -- and the cost of the encoding lookup is mitigated by a
        // ThreadLocal that caches the last lookup). So we just do things the simple way here.
        try
        {
            return str.getBytes("UTF-8");
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            // Javadoc says that UTF-8 *must* be supported by all JVMs, so we don't try to be clever here.
            throw new RuntimeException("Unexpected exception: UTF-8 charset not supported: " + e);
        }
    }    
    
    
	/**
	*<br>目的：將字串前面補零傳回字串
	*<br>參數：(1)資料字串 (2)所需長度
	*<br>傳回：傳回字串
	*<br>範例：formatFrontZero("123",5)，傳回"00123"
	*/
	static public String formatFrontZero(String s,int len){
		return formatFrontString(s,len,'0');
	}
	
	/**
	*<br>目的：將字串後面補零傳回字串
	*<br>參數：(1)資料字串 (2)所需長度
	*<br>傳回：傳回字串
	*<br>範例：formatRearZero("123",5)，傳回"12300"
	*/
	static public String formatRearZero(String s,int len){
		return formatRearString(s,len,'0');
	}
	
	/**
	*<br>目的：將字串前面補自己想要加入的字元傳回字串
	*<br>參數：(1)資料字串 (2)所需長度 (3)欲加入的字元
	*<br>傳回：傳回字串
	*<br>範例：formatFrontString("123",5, "A")，傳回"AA123"
	*/
	static public String formatFrontString(String s,int len, char ch){
		String format="";
		int sLen=get(s).length();
		for(int i=0; i<(len-sLen) ; i++){
			format += ch;
		}
		format += s;
		return format;
	}	
	static public String LeftPad(String s,int len, char ch){
		return formatFrontString(s,len, ch);
	}	


	/**
	*<br>目的：將字串後面補自己想要加入的字元傳回字串
	*<br>參數：(1)資料字串 (2)所需長度 (3)欲加入的字元
	*<br>傳回：傳回字串
	*<br>範例：formatRearZero("123",5, "A")，傳回"123AA"
	*/	
	static public String formatRearString(String s,int len, char ch){
		String format="";
		int sLen=get(s).length();
		for(int i=0; i<(len-sLen) ; i++){
			format += ch;
		}
		format = s + format;
		return format;
	}
	static public String RightPad(String s,int len, char ch){
		return formatRearString(s, len, ch);
	}
	
	/**
	 * 將HHMMSS時間轉成HH:MM:SS
	 * @param HHMMSS
	 * @return HH:MM:SS
	 */
	static public String formatHHMMSS(String s) {
		if (s!=null && s.length()==6) {
			return s.substring(0,2)+":"+s.substring(2,4)+":"+s.substring(4);
		}
		return get(s);
	}
	
	/**
	 * 將YYYMMDD日期轉化成比較易讀的格式
	 * 
	 * @param YYYMMDD
	 * @param format = 1, 2, 3, 4
	 * @return YYY.MM.DD, YYY年MM月DD日, 民國YYY年MM月DD日, YYY/MM/DD, YYY-MM-DD
	 */
	static public String formatYYYMMDD(String s, int format) {
		if (s!=null && s.length()==7) {
			try {
				switch (format) {
				case 1: return s.substring(0,3)+"."+s.substring(3,5)+"."+s.substring(5);
				case 2: return s.substring(0,3)+"年"+s.substring(3,5)+"月"+s.substring(5)+"日";
				case 3: return "民國"+s.substring(0,3)+"年"+s.substring(3,5)+"月"+s.substring(5)+"日";
				case 4: return s.substring(0,3)+"/"+s.substring(3,5)+"/"+s.substring(5);
				case 5: return s.substring(0,3)+"-"+s.substring(3,5)+"-"+s.substring(5);
				}
				return s.substring(0,3)+"."+s.substring(3,5)+"."+s.substring(5);				
			}catch(Exception e) {
				return s;
			}
		} else if (s!=null && s.length()==5) {
			try {
				switch (format) {
				case 1: return s.substring(0,3)+"."+s.substring(3,5);
				case 2: return s.substring(0,3)+"年"+s.substring(3,5)+"月";
				case 3: return "民國"+s.substring(0,3)+"年"+s.substring(3,5);
				case 4: return s.substring(0,3)+"/"+s.substring(3,5);
				case 5: return s.substring(0,3)+"-"+s.substring(3,5);
				}
				return s.substring(0,3)+"."+s.substring(3,5);				
			}catch(Exception e) {
				return s;
			}			
		}
		return get(s);
	}
	
	/**
	 * 將YYYMMDD日期轉化成YYY.MM.DD
	 * 
	 * @param YYYMMDD
	 * @return YYY.MM.DD
	 */	
	static public String formatYYYMMDD(String s) {
		return formatYYYMMDD(s,99);
	}

  
  	/**
  	 * <br> 
  	 * <br>目的：將路徑檔名改為jasper可以解讀的格式, 例:"d:\test\test.jasper", 傳回"d:\\test\\test.jasper"
  	 * <br>參數：資料字串
  	 * <br>傳回：傳回轉換後字串
  	*/
    public static String getJasperPath(String s) {
    	StringBuffer sReturn = new StringBuffer();
    	int start=0;
    	for(int i=0; i<s.length(); i++){
    		if ("\\".equals(s.substring(i,i+1))){
    			sReturn.append(s.substring(start,i) +"\\");
    			start=i;
    		}
    	}
    	sReturn.append(s.substring(start,s.length()));
    	return sReturn.toString();     
    }    
    
    /**
     * 主要用於前端的網頁畫面顯示<br>
     * LG-T的架構，所有前端網頁的bean其getter和setter已經把<,>的符號轉成& l t ;和& g t ;<br>
     * 若要在前端顯示HTML語法內容則需要再將它還原，其用法如下：<br>
     * FormatStr(targetField,"Y")
     * 
     * @param sString : 要顯示的文字內容
     * @param sHTML　：　是否為HTML格式
     * @return
     */
    static public String FormatStr(String sString, String sHTML) {
      	String sStr = "";
      	if (sHTML.equalsIgnoreCase("Y") || sHTML.equalsIgnoreCase("true") || sHTML.equalsIgnoreCase("1")) {
      		//sStr = StringEscapeUtils.escapeJavaScript(sString).replaceAll("<%", "&lt;%");
      		return StringEscapeUtils.unescapeHtml(sString).replaceAll("<%", "&lt;%");
    	} else {
    		sStr = StringEscapeUtils.escapeHtml(sString);    		
    		//sStr = sString.replaceAll("<", "&lt;");
    		//sStr = sStr.replaceAll(">", "&gt;");
    		//sStr = sStr.replaceAll("\n", "<br>");
    		sStr = sStr.replaceAll("\r", "<br>");
    	}
    	return sStr;
    }
    
    static public String escapeJavaScript(String s) {
    	if (s!=null && !"".equals(s)) {
        	String[] arr = new String[]{"'","\"","\b","\n","\t","\f","\r","<>","<",">"};
        	for (int i=0; i<arr.length; i++) {
        		s = s.replaceAll(arr[i], "");
        	}
        	return StringEscapeUtils.escapeJavaScript(s);    		
    	}
    	return "";
    }      
    
    static public String escapeReturnChar(String s) {
    	if (s!=null && !"".equals(s)) {
        	//String[] arr = new String[]{"'","\"","\b","\n","\t","\f","\r","<>","<",">"," "};
        	String[] arr = new String[]{"'","\"","\b","\n","\t","\f","\r"};
        	for (int i=0; i<arr.length; i++) {
        		s = s.replaceAll(arr[i], "");
        	}
        	return s;    		
    	}
    	return "";
    }       
        
    
    
    /**
  	 * <br> 
  	 * <br>目的：將user所鍵入之password經MD5加密
  	 * <br>參數：已加密字串
  	 * <br>傳回：傳回轉換後字串
  	*/
    public static String getMD5PassWord(String inPassWord){
    	try{
	      MessageDigest md = MessageDigest.getInstance("MD5");
	      md.update(inPassWord.getBytes());
	      MessageDigest theClone = (MessageDigest)md.clone();
	      byte[] digest = theClone.digest();
	    	    
	      final StringBuffer buffer = new StringBuffer();
	      for (int i = 0; i < digest.length; ++i){
	    	final byte b = digest[i]; 
	    	final int value = (b & 0x7F) + (b < 0 ? 128 : 0);
	    	buffer.append(value < 16 ? "0" : "");
	    	buffer.append(Integer.toHexString(value));
	   	  }
	      return buffer.toString();
    	}catch(Exception ex){
    		ex.printStackTrace();
    		return "";
    	}
    }
    
    /**
     * 將文字陣列轉成普通文字
     * 例如:String[]{"1","2"} 變成 1,2
     *  
     * @param strArray
     * @return
     */
    public static String arrayToString(String[] strArray) {
    	return arrayToString(strArray, ",");
    }
    
    /**
     * 將文字陣列轉成普通文字
     * 例如:String[] x = new String[]{"1","2"}
     * arrayToString(x,",") 會得到 1,2
     * 
     * @param a
     * @param separator
     * @return
     */
    public static String arrayToString(String[] a, String separator) {
        StringBuffer result = new StringBuffer().append("");
        if (a.length > 0) {
            result.append(a[0]);
            for (int i=1; i<a.length; i++) {
                result.append(separator);
                result.append(a[i]);
            }
        }
        return result.toString();
    }    
    
    public static void saveFile(String text, File outputFile) throws IOException  {
    	java.io.FileWriter writer = new java.io.FileWriter(outputFile);
    	java.io.BufferedWriter os = new java.io.BufferedWriter(writer);
		os.write(text);
		os.flush();
		os.close();
		writer.close();
    }    

    public static boolean RemoveDirectory(File dir) {    	
        if (dir.isDirectory()) {
        	// if directory is root directory then throw exception
        	if(dir.getParentFile()==null) throw new IllegalArgumentException("Cann't remove root directory");
        	
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = RemoveDirectory(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }    
        // The directory is now empty so delete it
        return dir.delete();
    }
    
	public static boolean CreateDir(String strDirName) {
		// Create a directory; all ancestor directories must exist
		//boolean success = (new File(strDirName)).mkdir();
		return (new File(strDirName)).mkdirs();
		
		// Create a directory; all non-existent ancestor directories are
		// automatically created
		//success = (new File("directoryName")).mkdirs();
	}
	
	public static String ReportMoneyFormat(Object d) {
		if (d==null || getNumeric(d)==0) return "";
		else return MoneyFormat(getNumeric(d),0);
	}	
	public static String ReportMoneyFormat(Double d, boolean withDecimal) {
		if (d==null || d==0) return "";
		else return MoneyFormat(d,true);
	}	
	public static String ReportMoneyFormat(Double d, int p) {
		if (d==null || d==0) return "";
		else return MoneyFormat(d,p);
	}		
	   
    public static String MoneyFormat(Object d){
		if (d==null || getNumeric(d)==0) return "0";
		else return MoneyFormat(getNumeric(d),0); 
    }    
    public static String MoneyFormat(Double money, boolean withDecimal){
    	if (money!=null) {
        	String s = MoneyFormat(money,5);
        	if (s.lastIndexOf('.')!=-1) {
            	if (s.substring(s.lastIndexOf('.')).equals(".00000")) {
            		return s.substring(0,s.lastIndexOf('.'));
            	} else {
            		return s.substring(0,s.lastIndexOf('.')) + ("." + Double.parseDouble(s.substring(s.lastIndexOf('.')))).replaceAll("0.", "");
            	}
        	} else return s;
    	} else return "0";
    }    
    public static String MoneyFormat(Object money, int d){
        if(money!=null){
            DecimalFormat df = new DecimalFormat("###,###,###,###,###,###");
            if (d>0) df = new DecimalFormat("###,###,###,###,###,##0."+formatRearZero("",d));                
            return df.format(getNumeric(money));
        } else return "0."+formatFrontZero("",d);
    }  
    
    /**
     * 將半形數子轉成全形數字
     * <br> type = 1 (１,２..)
     * <br> type = 2 (一,二..)
     * <br> type = 3 (壹,貳..)
     * @param type
     * @param s
     * @return
     */
    static public String changeNumberType(int type, String s){
    	char[] num = {'1','2','3','4','5','6','7','8','9','0'};
    	char[] zh1 = {'１','２','３','４','５','６','７','８','９','０'};
    	char[] zh2 = {'一','二','三','四','五','六','七','八','九','０'};
    	char[] zh3 = {'壹','貳','參','肆','伍','陸','柒','捌','玖','零'};
    	s = get(s);
    	StringBuilder sb = new StringBuilder();
		for(int i=0;i<s.length();i++){
			char ch=s.charAt(i);
			for (int j=0; j<num.length; j++) {
				if (num[j]==ch) {
			    	switch (type) {    	
			    	case 1:ch=zh1[j]; break;
			    	case 2:ch=zh2[j]; break;
			    	case 3:ch=zh3[j]; break;
			    	}
				}
			}
			sb.append(ch);
		}
		return sb.toString();
    }
    
    /**
     * 數字金額轉國字金額:可以的話請直接使用numToZh(double val)
     * @param BigDecimal
     */
	public static String numToZh(BigDecimal bigdMoneyNumber)
	{
		return numToZh(bigdMoneyNumber.doubleValue());
	}
	
    /**
     * 數字金額轉國字金額:可以的話請直接使用numToZh(double val)
     * @param String
     */
	public static String numToZh(String value){
		return numToZh(Double.parseDouble(value));
	}

  /**
   * 數字金額轉國字金額
   * @param val double 金額
   * @return String
   */
	public static String numToZh(double val) {
		String HanDigiStr[] = new String[] {"零", "壹", "貳", "參", "肆", "伍", "陸", "柒", "捌", "玖"};
	    String HanDiviStr[] = new String[] {
	        "", "拾", "佰", "仟", "萬", "拾", "佰", "仟", "億",
	        "拾", "佰", "仟", "萬", "拾", "佰", "仟", "億",
	        "拾", "佰", "仟", "萬", "拾", "佰", "仟"};
	    String SignStr = "";
	    String TailStr = "";
	    long fraction, integer;
	    int jiao, fen;
	    String RMBStr = "", NumStr = "";
	    boolean lastzero = false;
	    boolean hasvalue = false; // 億、萬進位元前有數值標記
	    int len, n;
	    //數字金額判斷
	    if (val < 0) {
	      val = -val;
	      SignStr = "負";
	    }
	    if (val > 99999999999999.999 || val < -99999999999999.999) {
	      return "數值位數過大!";
	    }
	    // 四捨五入到分
	    long temp = Math.round(val * 100);
	    integer = temp / 100;
	    fraction = temp % 100;
	    jiao = (int) fraction / 10;
	    fen = (int) fraction % 10;
	    
	    if (jiao == 0 && fen == 0) {
	      TailStr = "整";
	    } else {
	    	TailStr = HanDigiStr[jiao];
	    	if (jiao != 0) {
	    		TailStr += "角";
	    	}
	    	if (integer == 0 && jiao == 0) { // 零元後不寫零幾分
	    		TailStr = "";
	    	}
	    	if (fen != 0) {
	    		TailStr += HanDigiStr[fen] + "分";
	    	}
	    }
	    
	    //判斷小數點前字串
	    NumStr = String.valueOf(integer);
	    len = NumStr.length();
	    if (len > 15) {
	      return "數值過大!";
	    }
	    for (int i = len - 1; i >= 0; i--) {
	      if (NumStr.charAt(len - i - 1) == ' ') {
	        continue;
	      }
	      n = NumStr.charAt(len - i - 1) - '0';
	      if (n < 0 || n > 9) {
	        return "輸入含非數位字元!";
	      }
	      if (n != 0) {
	        if (lastzero) {
	          RMBStr += HanDigiStr[0]; // 若干零後若跟非零值，只顯示一個零
	          // 除了億萬前的零不帶到後面
	          //if( !( n==1 && (i%4)==1 && (lastzero || i==len-1) ) )    // 如十進位前有零也不發壹音用此行
	        }
	        if (! (n == 1 && (i % 4) == 1 && i == len - 1)) { // 十進位處於第一位不發壹音
	          RMBStr += HanDigiStr[n];
	        }
	        RMBStr += HanDiviStr[i]; // 非零值後加進位，個位為空
	        hasvalue = true; // 置萬進位元前有值標記

	      }
	      else {
	        if ( (i % 8) == 0 || ( (i % 8) == 4 && hasvalue)) { // 億萬之間必須有非零值方顯示萬
	          RMBStr += HanDiviStr[i]; // “億”或“萬”
	        }
	      }
	      if (i % 8 == 0) {
	        hasvalue = false; // 萬進位元前有值標記逢億重定
	      }
	      lastzero = (n == 0) && (i % 4 != 0);
	    }
	    if (RMBStr.length() == 0) {
	      RMBStr = HanDigiStr[0]; // 輸入空字元或"0"，返回"零"
	    }
	    return SignStr + RMBStr + "元" + TailStr;
	}	
	
	
	//將日期加/
    public static String MaskDate(String sDate) {
    	String sMaskDate = "";
    	if ((sDate==null) || ("".equals(sDate))) {
    		return "";
    	}
    	else {
    		int iSLen = sDate.length();
    		if (iSLen <= 3) {
    			sMaskDate = sDate;
    		}
    		if ((iSLen > 3) && (iSLen <= 5)) {
    			sMaskDate = sDate.substring(0,3) + "/" +sDate.substring(3,iSLen);
    		}
    		if (iSLen > 5) {
    			sMaskDate = sDate.substring(0,3) + "/" +sDate.substring(3,5) + "/" + sDate.substring(5,iSLen);
    		}
    		return sMaskDate;
    	}
    }	
    
	//將日期加年月日
    public static String MaskCDate(String sDate) {
    	String sMaskDate = "";
    	if ((sDate==null) || ("".equals(sDate))) {
    		return "";
    	}
    	else {
    		int iSLen = sDate.length();
    		if (iSLen <= 3) {
    			sMaskDate = sDate;
    		}
    		if ((iSLen > 3) && (iSLen <= 5)) {
    			//sMaskDate = sDate.substring(0,3) + "年" +sDate.substring(3,iSLen);
    			if(!"0".equals(sDate.substring(0,1)))sMaskDate=sDate.substring(0,3)+"年";
        		else sMaskDate=sDate.substring(1,3)+"年";
        		if(!"0".equals(sDate.substring(3,4)))sMaskDate+=sDate.substring(3,iSLen)+"月";
        		else sMaskDate+=sDate.substring(4,iSLen)+"月";
    		}
    		if (iSLen > 5) {
    			//sMaskDate = sDate.substring(0,3) + "年" +sDate.substring(3,5) + "月" + sDate.substring(5,iSLen) + "日";
    			if(!"0".equals(sDate.substring(0,1)))sMaskDate=sDate.substring(0,3)+"年";
        		else sMaskDate=sDate.substring(1,3)+"年";
        		if(!"0".equals(sDate.substring(3,4)))sMaskDate+=sDate.substring(3,5)+"月";
        		else sMaskDate+=sDate.substring(4,5)+"月";
        		if(!"0".equals(sDate.substring(5,6)))sMaskDate+=sDate.substring(5,iSLen)+"日";
        		else sMaskDate+=sDate.substring(6,iSLen)+"日";
    		}
    		return sMaskDate;
    	}
    }  
    
    public static double getNumeric(Object s) {
    	if (s!=null) return getNumeric(s.toString());
    	else return 0;
    }    
	public static double getNumeric(String s){
		if (get(s).equals("")) return 0;
		try {
			s = s.replaceAll(",", "");
			if (Double.isNaN(Double.parseDouble(s))) return 0;
			else return Double.parseDouble(s.replaceAll(",", ""));
		} catch (NumberFormatException e) {
			return 0;
		}		
	}
	
	public static String getNumericString(String s){
		if (get(s).equals("")) return "0";
		try {
			s = s.replaceAll(",", "");
			if (Double.isNaN(Double.parseDouble(s))) return "0";
			else return s;
		} catch (NumberFormatException e) {
			return "0";
		}		
	}	
	
	public static int getInt(Object s){
		return getInteger(get(s)).intValue();
	}		
	public static int getInt(String s){
		return getInteger(s).intValue();
	}	
	public static Integer getInteger(String s){
		if (s==null || get(s).equals("")) return new Integer(0);
		try {
			s = s.replaceAll(",", "");
			if (Double.isNaN(Double.parseDouble(s))) return new Integer(0);
			else return Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return new Integer(0);
		}
	}	
    
    public static String getRemoteData(String urlString) { 
		StringBuffer document = new StringBuffer(); 
		try { 
		  java.net.URL url = new java.net.URL(urlString); 
		  java.net.URLConnection conn = url.openConnection(); 
		  BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
		  String line = null; 
		  while ((line = reader.readLine()) != null) 
		    document.append(line + "\n"); 
		  reader.close(); 
		} catch (java.net.MalformedURLException e) { 
		  System.out.println("Unable to connect to URL: " + urlString); 
		} catch (IOException e) { 
		  System.out.println("IOException when connecting to URL: " + urlString); 
		}
		return document.toString(); 
   }

}