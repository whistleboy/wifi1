package com.ysu.model;

public class macInfoBean {
	private String mac;
	private String productor;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	@Override
	public String toString() {
		return "macInfoBean [mac=" + mac + ", productor=" + productor + "]";
	}
	
	

}
