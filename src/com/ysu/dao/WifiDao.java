package com.ysu.dao;

import java.io.IOException;

import com.ysu.db.HBaseDB;
import com.ysu.model.WifiInfoBean;
import com.ysu.util.Constants;

public class WifiDao {
	public boolean insert(WifiInfoBean wifiInfoBean) {
		long id=0;
		try {
			id=HBaseDB.getId(Constants.HBASE_TABLE_WIFIGID, Constants.HBASE_ROW_KEY_WIFIGID_INFOID, Constants.HBASE_FAMILY_WIFIGID_WIFIGID, Constants.HBASE_COLUMN_WIFIGID_INFOID);
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO, Constants.HBASE_COLUMN_WIFIINFO_LOGTIME, wifiInfoBean.getLogtime());
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO, Constants.HBASE_COLUMN_WIFIINFO_SIGNAL, wifiInfoBean.getSignal());
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO, Constants.HBASE_COLUMN_WIFIINFO_MAC, wifiInfoBean.getMac());
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO, Constants.HBASE_COLUMN_WIFIINFO_WIFINAME, wifiInfoBean.getWifiname());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
