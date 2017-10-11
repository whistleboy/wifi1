package com.ysu.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class localToHbase {

	public static void main(String[] args) {
		File file = new File(args[0]);
		File file2 = new File(args[1]);
		try {
			if (!file2.exists()) {
				file2.createNewFile();
			}
			FileWriter fw = new FileWriter(file2, true);
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				// 在这切分
				String[] tmpStr = s.split(" ");
				// 保存到hbase
				
				// 保存到本地文件，格式为2017-10-01.log
				// 如果是空行，则跳过
				if (s.length() <= 1) {
					continue;
				}
				String content = tmpStr[0] + " " + tmpStr[8] + " " + tmpStr[12].substring(6) + " " + tmpStr[23] + "\n";
				bw.write(content);
			}
			bw.close();
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
