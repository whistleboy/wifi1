package com.ysu.util;

public class bashshell {

	public static void flume2hadoop() {
		try {
			String shpath = "sh /home/qjx/wifi/flume2hadoop.sh";
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	

	public static void hadoop2hive() {
		try {
			String shpath = "sh /home/qjx/wifi/hadoop2hive.sh";
			Process ps = Runtime.getRuntime().exec(shpath);
			ps.waitFor();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}
