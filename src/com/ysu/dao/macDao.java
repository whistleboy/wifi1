package com.ysu.dao;

import java.io.IOException;

import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import com.ysu.db.HBaseDB;
import com.ysu.model.macInfoBean;
import com.ysu.util.Constants;

public class macDao {

	public static boolean insert(macInfoBean macInfoBean) {
		long id = 0;
		try {
			HBaseDB.add(Constants.HBASE_TABLE_MACINFO, macInfoBean.getMac(), Constants.HBASE_FAMILY_MACINFO_MACINFO,
					Constants.HBASE_COLUMN_MACINFO_PRODUCTOR, macInfoBean.getProductor());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	public static macInfoBean getMacInfo(String mac) {
		macInfoBean macInfoBean = new macInfoBean();
		Table table = null;
		try {
			table = HBaseDB.getConn().getTable(TableName.valueOf(Constants.HBASE_TABLE_MACINFO));
			Get get = new Get(Bytes.toBytes(mac));
			Result result = table.get(get);
			String productor = Bytes.toString(result.getValue(Bytes.toBytes(Constants.HBASE_FAMILY_MACINFO_MACINFO), Bytes.toBytes(Constants.HBASE_COLUMN_MACINFO_PRODUCTOR)));
			macInfoBean.setProductor(productor);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return macInfoBean;
	}

}
