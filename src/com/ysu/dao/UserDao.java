package com.ysu.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.ValueFilter;
import org.apache.hadoop.hbase.generated.master.table_jsp;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import com.ysu.model.UserBean;
import com.ysu.db.HBaseDB;
import com.ysu.util.Constants;


@Repository
public class UserDao{
	public boolean insert(String name,String password) {
		long id=0;
		try {
			id=HBaseDB.getId(Constants.HBASE_TABLE_WIFIGID, Constants.HBASE_ROW_KEY_WIFIGID_GID, Constants.HBASE_FAMILY_WIFIGID_WIFIGID, Constants.HBASE_COLUMN_WIFIGID_GID);
			HBaseDB.add(Constants.HBASE_TABLE_WIFIUSER, id, Constants.HBASE_FAMILY_WIFIUSER_WIFIUSER, Constants.HBASE_COLUMN_WIFIUSER_NAME, name);
			HBaseDB.add(Constants.HBASE_TABLE_WIFIUSER, id, Constants.HBASE_FAMILY_WIFIUSER_WIFIUSER, Constants.HBASE_COLUMN_WIFIUSER_PWD, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	public long getIdByName(String name) {
        Table table = null;
        long id = 0;
		try {
			table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_WIFIUSER));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scan scan = new Scan();
		scan.addColumn(Bytes.toBytes(Constants.HBASE_FAMILY_WIFIUSER_WIFIUSER), Bytes.toBytes(Constants.HBASE_COLUMN_WIFIUSER_NAME));
		Filter filter = new ValueFilter(CompareFilter.CompareOp.EQUAL, new BinaryComparator(Bytes.toBytes(name)));
		scan.setFilter(filter);
		ResultScanner resultScanner = null;
		try {
			resultScanner = table.getScanner(scan);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (Result result : resultScanner) {
			id=Bytes.toLong(result.getRow());
		}
		resultScanner.close();
		try {
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return id;
}
	public boolean login(String name, String password){
		//User user=new User();
		long id = 0;
		String pwd=null;
		boolean b=false;
		id=getIdByName(name);
		if(id!=0){
			Table table = null;
			try {
				table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_WIFIUSER));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Get get = new Get(Bytes.toBytes(id));
	        get.addColumn(Constants.HBASE_FAMILY_WIFIUSER_WIFIUSER.getBytes(),Constants.HBASE_COLUMN_WIFIUSER_PWD.getBytes());
	        Result result1 = null;
			try {
				result1 = table.get(get);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Cell[] cells2 = result1.rawCells();
	        for(Cell cell2:cells2){
	        pwd=new String(CellUtil.cloneValue(cell2));
	        if(pwd.equals(password))
	        {
	        	b=true;
	        }
	        }
	        try {
				table.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
		return b;
	}
	
	public boolean checkName(String name) {
		boolean b=false;
	      long id=getIdByName(name);
	      if(id!=0){
	    	  b=true;
	      }
		return b;
	}
	
	
}
