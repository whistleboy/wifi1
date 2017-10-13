package com.ysu.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.ysu.db.HBaseDB;
import com.ysu.db.HiveDB;
import com.ysu.model.WifiInfoBean;
import com.ysu.model.macInfoBean;
import com.ysu.util.Constants;

public class WifiDao {
	public boolean insert(WifiInfoBean wifiInfoBean) {
		long id = 0;
		try {
			id = HBaseDB.getId(Constants.HBASE_TABLE_WIFIGID, Constants.HBASE_ROW_KEY_WIFIGID_INFOID,
					Constants.HBASE_FAMILY_WIFIGID_WIFIGID, Constants.HBASE_COLUMN_WIFIGID_INFOID);
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO,
					Constants.HBASE_COLUMN_WIFIINFO_LOGTIME, wifiInfoBean.getLogtime());
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO,
					Constants.HBASE_COLUMN_WIFIINFO_SIGNAL, wifiInfoBean.getSignal());
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO,
					Constants.HBASE_COLUMN_WIFIINFO_MAC, wifiInfoBean.getMac());
			HBaseDB.add(Constants.HBASE_TABLE_WIFIINFO, id, Constants.HBASE_FAMILY_WIFIINFO_WIFIINFO,
					Constants.HBASE_COLUMN_WIFIINFO_WIFINAME, wifiInfoBean.getWifiname());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// 获取到七个计算的结果
	// 计算客流量
	public int getKeLiuLiang(){
		String sql = "select count(distinct mac) from wifiinfo;";
		int ret = 0;
		try {
			ResultSet res = null;
			Statement statement =HiveDB.getStmt();
			res = statement.executeQuery(sql);
			while(res.next()) {
				ret = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	//入店量
	public int getRuDianLiang() {
		String sql = "select count(distinct mac) from wifiinfo where signal > 30;";
		int ret = 0;
		try {
			ResultSet res = null;
			Statement statement =HiveDB.getStmt();
			res = statement.executeQuery(sql);
			while(res.next()) {
				ret = res.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	//获取入店率，入店量/客流量
	public double getRuDianLv() {
		return (double)getRuDianLiang()/getKeLiuLiang();
	}

	//来访周期，以四个小时为一个周期
	public double getLaiFangZhouQi(String mac) {

		return 0;
	}

	//新老顾客，true是老顾客，false是新顾客
	public boolean isLaoGuKe(String mac) {
		String sql = "select mac from wifiinfo;";
		try {
			ResultSet res = null;
			Statement statement =HiveDB.getStmt();
			res = statement.executeQuery(sql);
			while(res.next()) {
				if(mac.equals(res.getString(1))) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	//顾客活跃度，贝叶斯算法？？
	public double getHuoYueDu(String mac) {

		return 0;
	}

	//驻店时长，
	public double getShiChang(String mac) {
		
		return 0;
	}

	//手机品牌，解析mac地址前三位
	public String getPinPai(String mac) {
		macInfoBean macInfoBean = new macInfoBean();
		macInfoBean = macDao.getMacInfo(mac);
		return macInfoBean.getProductor();
	}
	
	//跳出率
	public double getTiaoChuLv() {

		return 0;
	}
	
	//深访率
	public double getShenFangLv() {

		return 0;
	}

}
