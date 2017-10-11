package com.ysu.db;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;
import org.springframework.stereotype.Repository;

import com.netpan.model.FileBean;
import com.netpan.model.User;
import com.ysu.util.Constants;
@Repository
public class HadoopDB {

	public void upload(InputStream in, String dir) throws Exception {
		Configuration conf = new Configuration();
		URI uri = null;
		try {
			uri = new URI("hdfs://li:9000");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileSystem fs = FileSystem.get(uri, conf);
		//Path dstPath = new Path(dir); // 目标路径
		// 打开一个输出流
		OutputStream out = fs.create(new Path(dir), new Progressable() {
			public void progress() {
				//System.out.println("ok");
			}
		});
		IOUtils.copyBytes(in, out, 4096, true);
		//System.out.println("文件创建成功！");
	}
	public void downLoad(String path,String local) throws Exception {
		Configuration conf = new Configuration();
		URI uri = null;
		try {
			uri = new URI("hdfs://li:9000");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileSystem fs = FileSystem.get(uri, conf);
		FSDataInputStream in = fs.open(new Path(path));
		OutputStream out = new FileOutputStream(local);
		IOUtils.copyBytes(in, out, 4096, true);
	}
	public void mkdir(String dir) throws Exception {
		Configuration conf = new Configuration();
		URI uri = null;
		try {
			uri = new URI("hdfs://li:9000");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileSystem fs = FileSystem.get(uri, conf);
		//Path srcPath = new Path(dir);
		//boolean isok = false;
		if (!fs.exists(new Path(dir))) {
			fs.mkdirs(new Path(dir));
		}
		//boolean isok = fs.mkdirs(srcPath);
		/*if (isok) {
			System.out.println("create dir ok!");
		} else {
			System.out.println("create dir failure");
		}*/
		fs.close();
	}
	public void copy(String srcPath,String dstPath){
		Path srcPathBean=new Path(srcPath);
		Path dstPathBean=new Path(dstPath);
		Configuration conf = new Configuration();
		URI uri = null;
		try {
			uri = new URI("hdfs://li:9000");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileSystem fs = null;
		try {
			fs = FileSystem.get(uri, conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtil.copy(fs, srcPathBean, fs, dstPathBean, false,conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(String filePath){
		Configuration conf = new Configuration();
		URI uri = null;
		try {
			uri = new URI("hdfs://li:9000");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		FileSystem fs = null;
		try {
			fs = FileSystem.get(uri, conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Path path = new Path(filePath);
		boolean isok = false;
		try {
			isok = fs.deleteOnExit(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isok) {
			System.out.println("delete ok!");
		} else {
			System.out.println("delete failure");
		}
		try {
			fs.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
