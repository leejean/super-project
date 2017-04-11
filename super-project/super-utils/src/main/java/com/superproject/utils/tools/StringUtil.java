package com.superproject.utils.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 字符串处理工具类
 * <li>@author Leejean
 * <li>@create 2014-6-24 下午04:13:57
 */
public class StringUtil {
	private static String GLOBAL_TIME_STAMP="yyyyMMddHHmmss";
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return true 不为空 false 空
	 */
	public static boolean notNull(String str) {
		if ((str != null) && (!str.trim().equals(""))) {
			return true;
		}
		return false;
	}
	public static boolean isNull(String str) {
		return !StringUtil.notNull(str);
	}
	/**
	 * 分割字符串 
	 * @param params 被分割的字符串
	 * @param splitChar 分割字符
	 * @return List<Long>
	 */
	public static List<Long> splitToLongList(String params, String splitChar) {
		List<Long> longlist = new ArrayList<Long>();
		if(params==null||params.trim().equals("")){return longlist;}
		String param[] = params.split(splitChar);
		for (String string : param) {
			if(string.equals("")){
				longlist.add(null);
			}else{	
				longlist.add(Long.parseLong(string));
			}
		}
		return longlist;
	}
	/**
	 * 将字符串转换为String数组
	 * @param params
	 * @param splitChar
	 * @return
	 */
	public static List<String> splitToStringList(String params, String splitChar) {
		List<String> stringlist = new ArrayList<String>();
		if(params==null||params.trim().equals("")){return stringlist;}
		String param[] = params.split(splitChar);
		for (String string : param) {
			if(string.equals("")){
				stringlist.add(null);
			}else{	
				stringlist.add(string);
			}
		}
		return stringlist;
	}
	/**
	 * 将字符串转换为Integer数组
	 * @param params
	 * @param splitChar
	 * @return
	 */
	public static List<Integer> splitToIntegerList(String params, String splitChar) {
		List<Integer> longlist = new ArrayList<Integer>();
		if(params==null||params.trim().equals("")){return longlist;}
		if(!params.trim().equals("")){
			String param[] = params.split(splitChar);
			for (String string : param) {
				if(string.equals("")){
					longlist.add(null);
				}else{				
					longlist.add(Integer.parseInt(string));
				}
			}
		}
		return longlist;
	}
	/**
	 * 将字符串转换为Double数组
	 * @param params
	 * @param splitChar
	 * @return
	 */
	public static List<Double> splitToDouble(String params, String splitChar) {
		List<Double> longlist = new ArrayList<Double>();
		if(params==null||params.trim().equals("")){return longlist;}
		if(!params.trim().equals("")){
			String param[] = params.split(splitChar);
			for (String string : param) {
				if(string.equals("")){
					longlist.add(null);
				}else{	
					longlist.add(Double.parseDouble(string));
				}
			}
		}
		return longlist;
	}
	/**
	 * 格式化
	 * @author Leejean
	 * @create 2014年9月16日下午2:13:17
	 * @param from
	 * @param to
	 * @return
	 */
	public static String fmtDateBetweenParams(String o_date,String from,String to){
		if(StringUtil.notNull(from)||StringUtil.notNull(to)){
			if(!StringUtil.notNull(from)){				
				return " and ( "+o_date+" < '"+to+"' ) ";
			}
			if(!StringUtil.notNull(to)){				
				return " and ( "+o_date+" > '"+from+"' ) ";
			}
			return " and ("+o_date+" between '"+from+"' and '"+to+"') ";
		}
		return "";
	}
	
	/**
	* @Title: arrayToString 
	* @Description: String数组转 sql语句中的in '','',''
	* @param  params
	* @return String   返回类型 
	* @throws
	 */
	public static String arrayToStringBySqlin(String[] params){
		String returnString="";
		for(int i=0;i<params.length;i++){
			if(StringUtil.notNull(params[i])){
				if(i==params.length-1){
					returnString+="'"+params[i]+"' ";
				}else{
					returnString+="'"+params[i]+"',";
				}
			}
		}
		return returnString;
	}
	/**
	* @Title: arrayToString 
	* @Description: String数组转 sql语句中的in '','',''
	* @param  params
	* @return String   返回类型 
	* @throws
	 */
	public static String arrayToStringBySqlin(List<String> inIds){
		String returnString = "";
		
		if(inIds!=null&&inIds.size()>0){
			for (int i = 0; i < inIds.size(); i++) {
				if(i==inIds.size()-1){
					returnString+="'"+inIds.get(i)+"' ";
				}else{
					returnString+="'"+inIds.get(i)+"',";
				}
			}
		}
		return returnString;
	}
	/**
	 * 处理in条件id串条件<br>
	 * 例如  ids = 8af41576518f65d601519049aa830011,8af41576518f65d601519049aa830011,8af41576518f65d601519049aa830011 <br>
	 * 要处理为 '8af41576518f65d601519049aa830011','8af41576518f65d601519049aa830011','8af41576518f65d601519049aa830011'
	 * @param inStr
	 * @return
	 */
	public static String fmtSqlInIdsString(String inStr){
		return StringUtil.arrayToStringBySqlin(StringUtil.splitToStringList(inStr, ","));
	}
	public static String getFileNameExt(String fileName) throws IOException{
		if(StringUtil.notNull(fileName)){
			return fileName.substring(fileName.lastIndexOf("."));
		}else{
			throw new IOException("文件名称为空.");
		}		
	}
	/**
	 * 获得时间戳字符串
	 * 用于文件名称
	 * @return
	 */
	public static String getTimestampString(){
		return DateUtil.formatDateToString(new Date(),GLOBAL_TIME_STAMP)+String.valueOf(MathUtil.getRandomInt(1000, 9999));
	}
	public static void main(String[] args) {
		String fmtSqlInIdsString = StringUtil.fmtSqlInIdsString(null);
		System.out.println(":"+fmtSqlInIdsString);
	}
}
