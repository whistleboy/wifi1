package com.ysu.db;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.ysu.util.Constants;

public class HBaseDB {
	private Configuration conf = null;
	private Connection conn = null;

	private static class SingletonHolder {
		private static final HBaseDB INSTANCE = new HBaseDB();
	}

	private HBaseDB() {
		conf = HBaseConfiguration.create();
		conf.set("hbase.zookeeper.quorum", Constants.HBASE_NAME);
		conf.set("hbase.rootdir", Constants.HBASE_PATH_PRE);
		try {
			conn = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConn() {
		return SingletonHolder.INSTANCE.conn;
	}

	/**
	 * 根据信息，从计数器中生成id
	 * @param hbaseTableGid
	 * @param hbaseFamilyGidGid
	 * @param hbaseRowKeyGidGid
	 * @param hbaseColumnGidGid
	 * @return
	 */
	public static long getId(String tableName,  String rowkey, String family,
			String column) {
		long id = 0l;
		try {
			Table table = getConn().getTable(TableName.valueOf(tableName));
			id = table.incrementColumnValue(Bytes.toBytes(rowkey), Bytes.toBytes(family), Bytes.toBytes(column), 1);
		} catch (IOException e) {
			e.printStackTrace();
		}	
		return id;
	}
	public static void deleteRow(String tableName,String rowKey,String colFamily,String col) {
        Table table = null;
		try {
			table = getConn().getTable(TableName.valueOf(tableName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Delete delete = new Delete(rowKey.getBytes());
        //删除指定列族的所有数据
        //delete.addFamily(colFamily.getBytes());
        //删除指定列的数据
       // delete.addColumn(colFamily.getBytes(), col.getBytes());
 
        try {
			table.delete(delete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void deleteRow(String tableName,long rowKey,String colFamily,String col){
        Table table = null;
		try {
			table = getConn().getTable(TableName.valueOf(tableName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        //删除指定列族的所有数据
        //delete.addFamily(colFamily.getBytes());
        //删除指定列的数据
       // delete.addColumn(colFamily.getBytes(), col.getBytes());
 
        try {
			table.delete(delete);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			table.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public static void deleteColumns(String tableName,Long rowKey,String family, Long qualifier) throws Exception {
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Delete delete = new Delete(Bytes.toBytes(rowKey));
//		delete.deleteColumns(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		delete.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier));
		table.delete(delete);
		table.close();
	}
	public static void deleteRow(String tableName,Long rowKey01,Long rowKey02) throws Exception {
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Delete delete = new Delete(Bytes.add(Bytes.toBytes(rowKey01), Bytes.toBytes(rowKey02)));
		table.delete(delete);
		table.close();
	}
	public static void add(String tableName, Long rowKey, String family, Long qualifier, String value) throws IOException {
		//连接到table
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
//		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		table.put(put);
		table.close();
	}
	public static void add(String tableName, Long rowKey, String family, String qualifier, String value) throws IOException {
		//连接到table
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.toBytes(rowKey));
//		put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		table.put(put);
		table.close();
	}
	public static  void add(String tableName, long rowKey01,long rowKey02, String family, String qualifier, Long value) throws IOException {
		//连接到table
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.add(Bytes.toBytes(rowKey01), Bytes.toBytes(rowKey02)));
		if (qualifier!=null) {
//			put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
			put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		}else{
//			put.add(Bytes.toBytes(family), null, Bytes.toBytes(value));
			put.addColumn(Bytes.toBytes(family), null, Bytes.toBytes(value));
		}
		table.put(put);
		table.close();
	}
	
	public static long getGid(String row) throws Exception {
		Table table_gid = getConn().getTable(TableName.valueOf("gid"));
//		HTable table_gid = new HTable(TableName.valueOf("gid"), connection);
		long id = table_gid.incrementColumnValue(Bytes.toBytes(row), Bytes.toBytes("gid"), Bytes.toBytes(row), 1);
		table_gid.close();
		return id;
	}
	public static void add(String tableName, Long rowKey01,Long rowKey02, String family, String qualifier, String value) throws IOException {
		//连接到table
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.add(Bytes.toBytes(rowKey01), Bytes.toBytes(rowKey02)));
		if (qualifier!=null) {
//			put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
			put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.toBytes(value));
		}else{
//			put.add(Bytes.toBytes(family), null, Bytes.toBytes(value));
			put.addColumn(Bytes.toBytes(family), null, Bytes.toBytes(value));
		}
		table.put(put);
		table.close();
	}
	public static void add(String tableName, Long rowKey01,Long rowKey02,Long rowKey03, String family, String qualifier, Long value01, Long value02) throws IOException {
		//连接到table
//		HTable table = new HTable(TableName.valueOf(tableName), connection);
		Table table = getConn().getTable(TableName.valueOf(tableName));
		Put put = new Put(Bytes.add(Bytes.toBytes(rowKey01), Bytes.toBytes(rowKey02), Bytes.toBytes(rowKey03)));
		if (qualifier!=null) {
//			put.add(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.add(Bytes.toBytes(value01), Bytes.toBytes(value02)));
			put.addColumn(Bytes.toBytes(family), Bytes.toBytes(qualifier), Bytes.add(Bytes.toBytes(value01), Bytes.toBytes(value02)));
		}else{
//			put.add(Bytes.toBytes(family), null, Bytes.add(Bytes.toBytes(value01), Bytes.toBytes(value02)));
			put.addColumn(Bytes.toBytes(family), null, Bytes.add(Bytes.toBytes(value01), Bytes.toBytes(value02)));
		}
		table.put(put);
		table.close();
	}

	
}
