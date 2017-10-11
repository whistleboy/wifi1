package com.ysu.model;

public class WifiInfoBean {
	private long wifiInfoId;
	private String logtime;
	private int signal;
	private String mac;
	private String wifiname;
	public WifiInfoBean(long wifiInfoId, String logtime, int signal, String mac, String wifiname) {
		super();
		this.wifiInfoId = wifiInfoId;
		this.logtime = logtime;
		this.signal = signal;
		this.mac = mac;
		this.wifiname = wifiname;
	}
	public WifiInfoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public long getWifiInfoId() {
		return wifiInfoId;
	}
	public void setWifiInfoId(long wifiInfoId) {
		this.wifiInfoId = wifiInfoId;
	}
	public String getLogtime() {
		return logtime;
	}
	public void setLogtime(String logtime) {
		this.logtime = logtime;
	}
	public int getSignal() {
		return signal;
	}
	public void setSignal(int signal) {
		this.signal = signal;
	}
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getWifiname() {
		return wifiname;
	}
	public void setWifiname(String wifiname) {
		this.wifiname = wifiname;
	}
	@Override
	public String toString() {
		return "WifiInfoBean [wifiInfoId=" + wifiInfoId + ", logtime=" + logtime + ", signal=" + signal + ", mac=" + mac
				+ ", wifiname=" + wifiname + "]";
	}
	
}
