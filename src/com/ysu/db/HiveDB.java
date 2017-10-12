package com.ysu.db;
import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet; 
import java.sql.SQLException; 
import java.sql.Statement; 
 
import org.apache.log4j.Logger;

import com.ysu.util.Constants; 
public class HiveDB {
	static Logger log =Logger.getLogger("HiveJdbcClient");
	  
	   private static String driverName= "org.apache.hive.jdbc.HiveDriver"; 
	   private static String url =Constants.HIVE_URL; 
	   private static String user =Constants.HIVE_USER;  //主机的用户名
	   private static String password =Constants.HIVE_PASSWORD;  //远程主机密码
	   private static String sql =""; 
	   private static ResultSet res; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Connection conn = null; 
	       Statement stmt = null; 
	       try { 
	           conn = getConn(); 
	           stmt =conn.createStatement(); 
	 
	           String tableName ="t_hive";
	           // 第一步:存在就先删除 
	           dropTable(stmt); 
	 
	           // 第二步:不存在就创建 
	           createTable(stmt,tableName); 
	 /*
	           // 第三步:查看创建的表 
	           showTables(stmt,tableName); 
	 
	           // 执行describe table操作 
	           describeTables(stmt,tableName); 
	 
	           // 执行load data into table操作 
	           loadData(stmt,tableName); 
	 
	           // 执行 select * query 操作 
	           //selectData(stmt,tableName); 
	 
	           // 执行 regular hive query 统计操作 
	           countData(stmt,tableName);
	 */
	       } catch(ClassNotFoundException e) { 
	           e.printStackTrace(); 
	           log.error(driverName +" not found!", e); 
	           System.exit(1); 
	       } catch (SQLException e){ 
	           e.printStackTrace(); 
	           log.error("Connectionerror!", e); 
	           System.exit(1); 
	       } finally { 
	           try {
	               if(res != null){
	                   res.close();
	               }
	              
	               if (stmt != null){ 
	                   stmt.close(); 
	               }
	              
	               if (conn != null){ 
	                  conn.close();  
	               }
	           } catch (SQLException e){ 
	               e.printStackTrace();
	           } 
	       } 
	}
	 private static void countData(Statement stmt, String tableName) 
	           throws SQLException{ 
	       sql = "select count(1)from " + tableName; 
	       log.info("Running:"+ sql); 
	       res =stmt.executeQuery(sql); 
	       log.info("执行“regular hive query”运行结果:"); 
	       while (res.next()) { 
	           log.info("count------>" + res.getString(1)); 
	       } 
	   } 
	 
	   private static void selectData(Statement stmt, String tableName) 
	           throws SQLException{ 
	       sql = "select * from" + tableName; 
	       log.info("Running:"+ sql); 
	       res =stmt.executeQuery(sql); 
	       log.info("执行 select * query 运行结果:"); 
	       while (res.next()) { 
	           log.info(res.getString(1)+ "\t" + res.getString(2)); 
	       } 
	   } 
	 
	    private static void loadData(Statement stmt,String tableName) 
	            throws SQLException{ 
	        String filepath ="/home/hadoop/software/test/t_hive.txt"; 
	        sql = "load data localinpath '" + filepath + "' into table " 
	                + tableName; 
	       log.info("Running:" + sql); 
	        stmt.execute(sql); 
	    } 
	 
	    private static void describeTables(Statement stmt, String tableName) 
	            throws SQLException{ 
	        sql = "describe "+ tableName; 
	       log.info("Running:" + sql); 
	        res =stmt.executeQuery(sql); 
	        log.info("执行 describe table 运行结果:"); 
	        while (res.next()) { 
	           log.info(res.getString(1) + "\t" + res.getString(2)); 
	        } 
	    } 
	 
	    private static void showTables(Statement stmt, String tableName) 
	            throws SQLException{ 
	        sql = "show tables'" + tableName + "'"; 
	       log.info("Running:" + sql); 
	        res =stmt.executeQuery(sql); 
	        log.info("执行 show tables 运行结果:"); 
	        if (res.next()) { 
	           log.info(res.getString(1)); 
	        } 
	    } 
	 
	    private static void createTable(Statement stmt, String tableName) 
	            throws SQLException{ 
	        log.info("执行 create tables:"+tableName); 
	        sql = "create table" 
	                + tableName 
	                + " (a string,b string,c string)  row format delimitedfields terminated by '+'"; 
	        stmt.execute(sql); 
	    } 
	 
	    private static String dropTable(Statement stmt) throws SQLException { 
	        // 创建的表名 
	        String tableName ="t_hive"; 
	        sql = "drop table" + tableName; 
	        stmt.execute(sql); 
	        return tableName; 
	    } 
	 
	    private static Connection getConn() throws ClassNotFoundException, 
	            SQLException { 
	       Class.forName(driverName); 
	        Connection conn =DriverManager.getConnection(url, user, password); 
	        return conn; 
	    } 
}
