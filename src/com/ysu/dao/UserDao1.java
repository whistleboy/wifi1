package com.ysu.dao;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Repository;

import com.ysu.model.User;
import com.ysu.db.HBaseDB;
import com.ysu.util.Constants;


@Repository
public class UserDao1{
	//连接HBase
	public static Configuration configuration;
	public static Connection connection;
	public static Admin admin;
	public static void init(){
        configuration  = HBaseConfiguration.create();
        configuration.set("hbase.zookeeper.quorum", "li");
        configuration.set("hbase.rootdir","hdfs://li:9000/hbase");
    try{
    	connection = ConnectionFactory.createConnection(configuration);
    	admin = connection.getAdmin();
    	//System.out.println("suc1");
    	}
    catch (IOException e){
    		e.printStackTrace();
    		}
}
public static void close(){
try{
	if(admin != null){
		admin.close();
		//System.out.println("suc2");
		}
	if(null != connection){
		connection.close();
		}
	}catch (IOException e){
		e.printStackTrace();
		}
}
//接口函数的实现
//insertId向user_id中插入数据
//insertId向id_user中插入数据
//@Override
	public boolean insert(User user) {
		init();
	    Table table = null;
		try {
			table = connection.getTable(TableName.valueOf("id_user"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    Put putname = new Put(Bytes.toBytes(user.getUserId()));
	    putname.addColumn(Bytes.toBytes("user"), Bytes.toBytes("name"), Bytes.toBytes(user.getUserName()));
	    Put putpwd = new Put(Bytes.toBytes(user.getUserId()));
	    putpwd.addColumn(Bytes.toBytes("user"), Bytes.toBytes("pwd"), Bytes.toBytes(user.getUserPassWord()));
	    Put putemail = new Put(Bytes.toBytes(user.getUserId()));
	    try {
			table.put(putname);
			table.put(putpwd);
			table.put(putemail);
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
	    
	    
	    Table table1 = null;
		try {
			table1 = connection.getTable(TableName.valueOf("user_id"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Put put1 = new Put(Bytes.toBytes(user.getUserName()));
		put1.addColumn(Bytes.toBytes("id"), Bytes.toBytes("id"), Bytes.toBytes(user.getUserId()));
	    try {
			table1.put(put1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			table1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    Table table2 = null;
		try {
			table2 = connection.getTable(TableName.valueOf("email_user"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Put put2 = new Put(Bytes.toBytes(user.getEmail()));
	    put2.addColumn(Bytes.toBytes("user"), Bytes.toBytes("userid"), Bytes.toBytes(user.getUserId()));
	   
	    try {
			table2.put(put2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    try {
			table2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    close();
		return true;
	}
	public long insertGid() {
		init();
		Table table = null;
		try {
			table = connection.getTable(TableName.valueOf("gid"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long id = 0;
		try {
			id = table.incrementColumnValue(Bytes.toBytes("gid"), 
			  Bytes.toBytes("gid"), Bytes.toBytes("gid"), 1);
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
	close();
	System.out.println(String.valueOf(id));
		return id;
	}
	public boolean login(String name, String password){
		//User user=new User();
		long id = 0;
		String pwd=null;
		boolean b=false;
		  init();
	        Table table1 = null;
			try {
				table1 = connection.getTable(TableName.valueOf("user_id"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Get get = new Get(name.getBytes());
	        get.addColumn("id".getBytes(),"id".getBytes());
	        Result result = null;
			try {
				result = table1.get(get);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Cell[] cells = result.rawCells();
	        if(cells.length!=0){
	        for(Cell cell:cells){
	        id=Bytes.toLong(CellUtil.cloneValue(cell));
	        }
	        try {
				table1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        
	        
	        Table table2 = null;
			try {
				table2 = connection.getTable(TableName.valueOf("id_user"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Get get2 = new Get(Bytes.toBytes(id));
	        get.addColumn("user".getBytes(),"pwd".getBytes());
	        Result result2 = null;
			try {
				result2 = table2.get(get2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Cell[] cells2 = result2.rawCells();
	        for(Cell cell2:cells2){
	        pwd=new String(CellUtil.cloneValue(cell2));
	        if(pwd.equals(password))
	        {
	        	b=true;
	        }
	        }
	        try {
				table2.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        }
	        close();
		return b;
	}
	
	
	public String forget(String email) {
	long id=0;
	String pwd=null;
		init();
        Table table1 = null;
		try {
			table1 = connection.getTable(TableName.valueOf("email_user"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Get get = new Get(email.getBytes());
        get.addColumn("user".getBytes(),"userid".getBytes());
        Result result = null;
		try {
			result = table1.get(get);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Cell[] cells = result.rawCells();
        if(cells.length!=0){
        for(Cell cell:cells){
        id=Bytes.toLong(CellUtil.cloneValue(cell));
        }
        try {
			table1.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        
        Table table2 = null;
		try {
			table2 = connection.getTable(TableName.valueOf("id_user"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Get get2 = new Get(Bytes.toBytes(id));
        get.addColumn("user".getBytes(),"pwd".getBytes());
        Result result2 = null;
		try {
			result2 = table2.get(get2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Cell[] cells2 = result2.rawCells();
        for(Cell cell2:cells2){
        pwd=new String(CellUtil.cloneValue(cell2));
        }
        try {
			table2.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
        close();
		return pwd;
	}
	public boolean checkName(String name) {
		boolean b=false;
		  init();
	        Table table1 = null;
			try {
				table1 = connection.getTable(TableName.valueOf("user_id"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Get get = new Get(name.getBytes());
	        get.addColumn("id".getBytes(),"id".getBytes());
	        Result result = null;
			try {
				result = table1.get(get);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Cell[] cells = result.rawCells();
	        if(cells.length!=0){
	        	b=true;
	        }
	        try {
				table1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return b;
	}
	public boolean checkEmail(String useremail) {
		boolean b=false;
		  init();
	        Table table1 = null;
			try {
				table1 = connection.getTable(TableName.valueOf("email_user"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Get get = new Get(useremail.getBytes());
	        get.addColumn("user".getBytes(),"userid".getBytes());
	        Result result = null;
			try {
				result = table1.get(get);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Cell[] cells = result.rawCells();
	        if(cells.length!=0){
	        	b=true;
	        }
	        try {
				table1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return b;
	}
	public long getId(String name) {
		  init();
	        Table table1 = null;
	        long id = 0;
			try {
				table1 = connection.getTable(TableName.valueOf("user_id"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Get get = new Get(name.getBytes());
	        get.addColumn("id".getBytes(),"id".getBytes());
	        Result result = null;
			try {
				result = table1.get(get);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        Cell[] cells = result.rawCells();
	        if(cells.length!=0){
	        	 for(Cell cell:cells){
	     	        id=Bytes.toLong(CellUtil.cloneValue(cell));
	     	        }
	        }
	        try {
				table1.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return id;
	}
	public Set<String> getFollow(long id) throws Exception {
		Set<String> set = new HashSet<String>();
//		HTable table = new HTable(TableName.valueOf("follow"), connection);
		Table table = connection.getTable(TableName.valueOf(Constants.HBASE_TABLE_FOLLOW));
		Get get = new Get(Bytes.toBytes(id));
		Result rs = table.get(get);
		for (Cell cell : rs.rawCells()) {
			set.add(Bytes.toString(CellUtil.cloneValue(cell)));
		}
		return set;
	}
	public boolean follow(String oname,String dname) throws Exception {
		long oid = getId(oname);
		long did = getId(dname);
		if (oid==0 || oid==0 || oid==did){
			return false;
		}
		HBaseDB.add("follow", oid, "name", did, dname);
		
		HBaseDB.add("followed", did, oid, "userid", null, oid);
		return true;
	}
	public boolean unfollow(String oname,String dname) throws Exception {
		long oid = getId(oname);
		long did = getId(dname);
		if (oid == 0 || did == 0 || oid == did){
			return false;
		}
		HBaseDB.deleteColumns("follow", oid, "name", did);
		
		HBaseDB.deleteRow("followed", did, oid);
		return true;
	}
	
}
