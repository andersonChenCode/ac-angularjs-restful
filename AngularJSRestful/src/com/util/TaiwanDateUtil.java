package com.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.util.lang.NumberUtil;

public class TaiwanDateUtil {
	
	public interface Format {
		/****
		 * "YYYMMDD HHMMSS"
		 */
		int YYYMMDD_HHMMSS = 1;
		/**
		 * "YYY/MM/DD HH:MM:SS"
		 */
		int YYYMMDD_HHMMSS_WITH_SEPARATOR = 2;		
		
		int YYYMMDD_SEPARATOR = 3;	

	}
	
	public interface Order {
		int ASC = 1;
		int DESC = 2;
	}

	private static Logger logger = Logger.getLogger(TaiwanDateUtil.class);

	public static void main(String args[]) {
		Date date = new Date(81, 9, 25);
		String yyymmdd = TaiwanDateUtil.parseToString(date);
		logger.info(date + " = " + yyymmdd);
		logger.info(date + ".year = "
				+ TaiwanDateUtil.getPartOf(yyymmdd, Calendar.YEAR));
		logger.info(date + ".month = "
				+ TaiwanDateUtil.getPartOf(yyymmdd, Calendar.MONTH));
		logger.info(date + ".date = "
				+ TaiwanDateUtil.getPartOf(yyymmdd, Calendar.DATE));

		TaiwanDateUtil.validate("1980/05/25");
		TaiwanDateUtil.validate("0700228");
		TaiwanDateUtil.validate("0700229");
		TaiwanDateUtil.validate("0700532");
	}

	public static boolean validate(String twDateText) {
		boolean flg = true;
		String dateStr = "";
		if (twDateText == null) {
			flg = false;
		} else {
			try {
				String tt = twDateText.trim();
				if (tt.length() < 7)
					tt = StringUtils.leftPad(tt, 7, "0");
				Date date = parseToDate(tt);
				dateStr = date.toLocaleString();
				int newM = date.getMonth() + 1;
				int newD = date.getDate();

				int oldM = Integer.parseInt(TaiwanDateUtil.getPartOf(tt,
						Calendar.MONTH));
				int oldD = Integer.parseInt(TaiwanDateUtil.getPartOf(tt,
						Calendar.DATE));

				if (newM != oldM)
					flg = false;
				else if (newD != oldD)
					flg = false;
			} catch (Exception e) {
				flg = false;
			}
		}
		logger
				.info("[validate] " + twDateText + " -> " + dateStr + " = "
						+ flg);
		return flg;
	}

	/**
	 * parse To Tw String
	 * 
	 * @param date
	 * @return yyymmdd
	 */
	public static String parseToString(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		StringBuilder sb = new StringBuilder();
		sb.append(StringUtils.leftPad(String.valueOf(calendar
				.get(Calendar.YEAR) - 1911), 3, '0'));
		sb.append(StringUtils.leftPad(String.valueOf(calendar
				.get(Calendar.MONTH)+1), 2, '0'));
		sb.append(StringUtils.leftPad(String.valueOf(calendar
				.get(Calendar.DAY_OF_MONTH)), 2, '0'));
		return sb.toString();
	}
	
	
	//yyy/mm/dd
	public static String parseToString1(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(calendar.get(Calendar.YEAR) - 1911));
		sb.append("/");
		sb.append(String.valueOf(calendar.get(Calendar.MONTH)+1));
		sb.append("/");
		sb.append(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
		return sb.toString();
	}
    //yyy年mm月dd日
	public static String parseToString2(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(calendar.get(Calendar.YEAR) - 1911));
		sb.append("年");
		sb.append(String.valueOf(calendar.get(Calendar.MONTH)+1));
		sb.append("月");
		sb.append(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
		sb.append("日");
		return sb.toString();
	}
	
	
	
	public static String parseToStringNullSafe(Date date) {
		if(date==null) { return ""; }
		return parseToString(date);
	}
	
	public static String parseToString(Date date, int format) {
		if(date==null) {
			return "";
		}
		
		String YYYMMDD = parseToString(date);
		String HHMMSS = parseToHHMMSS(date);
		
		if(format==Format.YYYMMDD_HHMMSS) {
			return YYYMMDD + " " + HHMMSS;
		}
		else if(format==Format.YYYMMDD_HHMMSS_WITH_SEPARATOR) {
			return YYYMMDD.substring(0,3) + "/" + YYYMMDD.substring(3,5) + "/" + YYYMMDD.substring(5) +
			" " + HHMMSS.substring(0,2) + ":" + HHMMSS.substring(2,4) + ":" + HHMMSS.substring(4);
		}else if (format==Format.YYYMMDD_SEPARATOR){
			return YYYMMDD.substring(0,3) + "/" + YYYMMDD.substring(3,5) + "/" + YYYMMDD.substring(5);
		}
		return YYYMMDD + HHMMSS;
	}
	
	/***************************************************************************
	 * @param HHMMSS
	 *            (24Hour)
	 * @return Date without Year, Month, Day components
	 */
	public static Date parseToTime(String HHMMSS) {
		String HH_ = HHMMSS.substring(0, 2);
		String MM_ = HHMMSS.substring(2, 4);
		String SS_ = HHMMSS.substring(4, 6);
		int HH = Integer.parseInt(HH_);
		int MM = Integer.parseInt(MM_);
		int SS = Integer.parseInt(SS_);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.HOUR_OF_DAY, HH);
		calendar.set(Calendar.MINUTE, MM);
		calendar.set(Calendar.SECOND, SS);
		return calendar.getTime();
	}
	public static String parseToHHMMSS(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minutes = calendar.get(Calendar.MINUTE);
		int sec = calendar.get(Calendar.SECOND);
		String HH = hour<10?"0"+hour:""+hour;
		String MM = minutes<10?"0"+minutes:""+minutes;
		String SS = sec<10?"0"+sec:""+sec;	
		return HH+MM+SS;
	}
	public static String parseToYYYMMDDHHMMSS(Date date) {
		String YYYMMDD = parseToString(date);
		String HHMMSS = parseToHHMMSS(date);
		return YYYMMDD + HHMMSS;
	}

	/***************************************************************************
	 * @param YYYMMDD
	 *            (e.g. 0950101=民國95年一月一日)
	 * @return Date without Hour, Minute, Second, Millis components
	 */
	public static Date parseToDate(String YYYMMDD) {
		String ymd = YYYMMDD.trim();
		if (ymd.length() < 7)
			ymd = StringUtils.leftPad(ymd, 7, "0");
		String YYY_ = ymd.substring(0, 3);
		String MM_ = ymd.substring(3, 5);
		String DD_ = ymd.substring(5, 7);
		int YYYY = twYearToWesternYear(Integer.parseInt(YYY_));
		int MM = Integer.parseInt(MM_);
		int DD = Integer.parseInt(DD_);
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(Calendar.YEAR, YYYY);
		calendar.set(Calendar.MONTH, MM - 1);
		calendar.set(Calendar.DAY_OF_MONTH, DD);
		return calendar.getTime();
	}

	public static int twYearToWesternYear(int twYear) {
		return twYear + 1900 + 11;
	}

	public static int westernYearToTwYear(int westernYear) {
		return westernYear - 1900 - 11;
	}

	/***************************************************************************
	 * @param date
	 * @return Taiwan Year in 3 digits
	 */
	public static String dateToTwYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.setTime(date);
		int year = westernYearToTwYear(calendar.get(Calendar.YEAR));
		String YYY = year < 100 ? "0" + year : String.valueOf(year);
		return YYY;
	}


	public static String getPartOf(String yyymmdd, int calendarType) {
		String find = null;
		int pos[] = new int[] { 0, 3, 5 };
		if (yyymmdd.length() < 7) {
			yyymmdd = StringUtils.leftPad(yyymmdd, 7, "0");
		}
		switch (calendarType) {
		case Calendar.YEAR:
			find = yyymmdd.substring(pos[0], pos[1]);
			break;
		case Calendar.MONTH:
			find = yyymmdd.substring(pos[1], pos[2]);
			break;
		case Calendar.DAY_OF_MONTH:
			find = yyymmdd.substring(pos[2]);
			break;
		default:
			break;
		}
		return find;
	}
	

	public static String getYYYYMMDD(String yyymmdd) {				
		return getPartOf(yyymmdd,Calendar.YEAR)+ getPartOf(yyymmdd,Calendar.MONTH) + getPartOf(yyymmdd,Calendar.DATE);
	}
	
	public static String getYear(String yyymmdd) {
		return getPartOf(yyymmdd,Calendar.YEAR);
	}
	public static String getMonth(String yyymmdd) {
		return getPartOf(yyymmdd,Calendar.MONTH);
	}
	public static String getDayOfMonth(String yyymmdd) {
		return getPartOf(yyymmdd,Calendar.DAY_OF_MONTH);
	}
	
	
	/**
	 * e.g. orgTwYear = 096, radix = 5, order = 1
	 * 	return x = {'091', '092', '093', '094', '095'}
	 * 
	 * e.g. orgTwYear = 096, radix = 5, order = 2
	 *  return x = {'095', '094', '093', '092', '091'}
	 * 
	 * @param orgTwYear
	 * @param radix
	 * @return
	 */
	public static String[] getPreviousTwYearByRadix(String orgTwYear, int radix, int order) {
		if(radix <= 0 || orgTwYear ==  null || orgTwYear.equals("") || NumberUtil.isInteger(orgTwYear) == false) {
			return null;
		}
		String[] twYear = new String[radix];
		int baseTwYear = Integer.parseInt(orgTwYear);
		for(int i=0; i<radix; i++) {
			String x = new String();
			if(order == Order.ASC) {
				x = String.valueOf(baseTwYear - radix+i);
			} else {
				x = String.valueOf(baseTwYear - (i + 1));
			}
			
			if(x.length() < 3) {
				x = "0" + x;				
			}
			twYear[i] = x;
		}
		return twYear;
	}
	
	/**
	 * 供query的date range  
	 * @param endDate
	 * @return 當年度之1/1~迄日
	 */
	public static Date[] getQueryDateRange(String endDate){
		Date temp = TaiwanDateUtil.parseToDate(endDate);
		Date d1 = DateUtil.getStartOfYear(temp);
		Date d2 = DateUtil.getEndOfMonth(temp);
		return DateUtil.createDateRange(d1, temp);
	}
	
	/**
	 * 供query的date range  
	 * @param endDate
	 * @return 當年度之1/1~迄日
	 */
	public static Date[] getQueryDateRange(Date endDate){
		Date d1 = DateUtil.getStartOfYear(endDate);
		Date d2 = DateUtil.getEndOfMonth(endDate);
		return DateUtil.createDateRange(d1, endDate);
	}
	
	/**
	 * 供query的date range  
	 * @param endDate
	 * @return 當月之1/1~迄日
	 */
	public static Date[] getQueryDateRange1(Date endDate){
		Date d1 = DateUtil.getStartOfMonth(endDate);
		Date d2 = DateUtil.getEndOfMonth(endDate);
		return DateUtil.createDateRange(d1, endDate);
	}
	

	/**
	 * 取得某個日期該月份的最後一天及日期
	 * @param twDate
	 * @return
	 */
	public static Date getLastDateOfMonth(String twDate){
		Date date = TaiwanDateUtil.parseToDate(twDate);
		return getLastDateOfMonth(date);
	}
	
	/**
	 * 取得某個日期該月份的最後一天及日期
	 * @param date
	 * @return
	 */
	public static Date getLastDateOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		final int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, lastDay);
		Date lastDate = calendar.getTime();
		
		return lastDate;
	}
	
	/**
	 * 取得列印日期
	 * @param date
	 * @return
	 */
	public static String getPrintDate(Date date){
		String twDate = TaiwanDateUtil.parseToString(date);
		String year = TaiwanDateUtil.getYear(twDate);
		String month = TaiwanDateUtil.getMonth(twDate);
		String day = StringUtils.substring(twDate,5);
		return "(" + year + "/" + month + "/" + day + ")";		
	}
	
	/**
	 * 取得列印日期(起訖，例中華民國XXX年XX月XX日至XXX年XX月XX日)
	 * @param date
	 * @return
	 */
	public static String getChinesePrintDateRange(Date startDate,Date endDate){
		StringBuffer sb = new StringBuffer();
		sb.append("中華民國");
		String twDate = TaiwanDateUtil.parseToString(startDate);
		sb.append(convertTwDate2ChineseDate(twDate));
		sb.append("起至");
		twDate = TaiwanDateUtil.parseToString(endDate);
		sb.append(convertTwDate2ChineseDate(twDate));
		return sb.toString();		
	}	
	/**
	 * 取得中華民國日期(依傳入之日期)
	 * @param twDate
	 * @return
	 */
	public static String convertTwDate2ChineseDate(String twDate){
		String year = TaiwanDateUtil.getYear(twDate);
		String month = TaiwanDateUtil.getMonth(twDate);
		String day = StringUtils.substring(twDate,5);
		return  Integer.parseInt(year) + "年 " + Integer.parseInt(month) + "月 " + Integer.parseInt(day) + "日" ;		
	}
	
	
}
