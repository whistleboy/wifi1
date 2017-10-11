package com.ysu.util;

import java.io.IOException;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.junit.Test;

import com.netpan.db.HBaseDB;

/**
 * 初始化hbase中的表
 * @author Administrator
 *
 */
public class InitTable {

	@Test
	public void addFile() {
		try {
			//创建gid
			Admin admin = HBaseDB.getConn().getAdmin();
			if(admin.tableExists(TableName.valueOf(Constants.HBASE_TABLE_FILE))){
				admin.disableTable(TableName.valueOf(Constants.HBASE_TABLE_FILE));
				admin.deleteTable(TableName.valueOf(Constants.HBASE_TABLE_FILE));
			}
			HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(Constants.HBASE_TABLE_FILE));
			HColumnDescriptor family = new HColumnDescriptor(Constants.HBASE_FAMILY_FILE_FILE);
			desc.addFamily(family);
			admin.createTable(desc);
			
			System.out.println("create file end");
			//创建id_user
			if(admin.tableExists(TableName.valueOf(Constants.HBASE_TABLE_USER_FILE))){
				admin.disableTable(TableName.valueOf(Constants.HBASE_TABLE_USER_FILE));
				admin.deleteTable(TableName.valueOf(Constants.HBASE_TABLE_USER_FILE));
			}
			desc = new HTableDescriptor(TableName.valueOf(Constants.HBASE_TABLE_USER_FILE));
			family = new HColumnDescriptor(Constants.HBASE_FAMILY_USER_FILE_FILE);
			desc.addFamily(family);
			admin.createTable(desc);
			System.out.println("create user_file end");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		try {
			//创建gid
			Admin admin = HBaseDB.getConn().getAdmin();
			if(admin.tableExists(TableName.valueOf(Constants.HBASE_TABLE_GID))){
				admin.disableTable(TableName.valueOf(Constants.HBASE_TABLE_GID));
				admin.deleteTable(TableName.valueOf(Constants.HBASE_TABLE_GID));
			}
			HTableDescriptor desc = new HTableDescriptor(TableName.valueOf(Constants.HBASE_TABLE_GID));
			HColumnDescriptor family = new HColumnDescriptor(Constants.HBASE_FAMILY_GID_GID);
			desc.addFamily(family);
			admin.createTable(desc);
			
			System.out.println("create gid end");
			//创建id_user
			if(admin.tableExists(TableName.valueOf(Constants.HBASE_TABLE_ID_USER))){
				admin.disableTable(TableName.valueOf(Constants.HBASE_TABLE_ID_USER));
				admin.deleteTable(TableName.valueOf(Constants.HBASE_TABLE_ID_USER));
			}
			desc = new HTableDescriptor(TableName.valueOf(Constants.HBASE_TABLE_ID_USER));
			family = new HColumnDescriptor(Constants.HBASE_FAMILY_ID_USER_USER);
			desc.addFamily(family);
			admin.createTable(desc);
			System.out.println("create id_user end");
			//创建user_id
			if(admin.tableExists(TableName.valueOf(Constants.HBASE_TABLE_USER_ID))){
				admin.disableTable(TableName.valueOf(Constants.HBASE_TABLE_USER_ID));
				admin.deleteTable(TableName.valueOf(Constants.HBASE_TABLE_USER_ID));
			}
			desc = new HTableDescriptor(TableName.valueOf(Constants.HBASE_TABLE_USER_ID));
			family = new HColumnDescriptor(Constants.HBASE_FAMILY_USER_ID_ID);
			desc.addFamily(family);
			admin.createTable(desc);
			System.out.println("create user_id end");
			//创建email_user
			if(admin.tableExists(TableName.valueOf(Constants.HBASE_TABLE_EMAIL_USER))){
				admin.disableTable(TableName.valueOf(Constants.HBASE_TABLE_EMAIL_USER));
				admin.deleteTable(TableName.valueOf(Constants.HBASE_TABLE_EMAIL_USER));
			}
			desc = new HTableDescriptor(TableName.valueOf(Constants.HBASE_TABLE_EMAIL_USER));
			family = new HColumnDescriptor(Constants.HBASE_FAMILY_EMAIL_USER_USER);
			desc.addFamily(family);
			admin.createTable(desc);
			System.out.println("create email_user end");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
