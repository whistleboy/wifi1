package com.ysu.util;

/**
 * 定义项目中的一些常量
 * @author Administrator
 *
 */
public class Constants {

	public static final String SESSION_USER_BEAN = "userBean";
	
	//定义hbase中用到的表
	public static final String HBASE_TABLE_WIFIGID = "wifigid";
	public static final String HBASE_TABLE_WIFIUSER = "wifiuser";
	public static final String HBASE_TABLE_WIFIINFO = "wifiinfo";
	public static final String HBASE_TABLE_MACINFO = "macinfo";
	
	//定义列族
	public static final String HBASE_FAMILY_WIFIGID_WIFIGID = "wifigid";
	public static final String HBASE_FAMILY_WIFIUSER_WIFIUSER = "wifiuser";
	public static final String HBASE_FAMILY_WIFIINFO_WIFIINFO = "wifiinfo";
	public static final String HBASE_FAMILY_MACINFO_MACINFO = "macinfo";
	//定义行健
	public static final String HBASE_ROW_KEY_WIFIGID_GID = "gid";
	public static final String HBASE_ROW_KEY_WIFIGID_INFOID = "infoid";
	
	//定义列
	public static final String HBASE_COLUMN_WIFIGID_GID = "gid";
	public static final String HBASE_COLUMN_WIFIGID_INFOID = "infoid";

	public static final String HBASE_COLUMN_WIFIUSER_PWD = "pwd";
	public static final String HBASE_COLUMN_WIFIUSER_NAME = "name";

	public static final String HBASE_COLUMN_WIFIINFO_LOGTIME = "logtime";
	public static final String HBASE_COLUMN_WIFIINFO_SIGNAL = "signal";
	public static final String HBASE_COLUMN_WIFIINFO_MAC = "mac";
	public static final String HBASE_COLUMN_WIFIINFO_WIFINAME = "wifiname";
	
	public static final String HBASE_COLUMN_MACINFO_MAC = "mac";
	
	//hdfs路径
	public static final String PATH_PRE = "hdfs://li:9000";
	
	//HBASE
	public static final String HBASE_PATH_PRE = "hdfs://li:9000/hbase";
	public static final String HBASE_NAME = "li";
	
}
