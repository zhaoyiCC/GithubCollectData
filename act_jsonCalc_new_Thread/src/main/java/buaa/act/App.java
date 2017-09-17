package buaa.act;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import buaa.act.ConnectMySql;
import buaa.act.Init;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipEntry;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
//import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;  

class Init_CSDN {
	Connection con;
	String driver = "com.mysql.jdbc.Driver";
    String url1 = "jdbc:mysql://192.168.7.132:3306/db_bbspage?characterEncoding=utf8";
    String url2 = "jdbc:mysql://192.168.7.132:3306/db_yifei?characterEncoding=utf8";
    
    String url3 = "jdbc:mysql://192.168.7.132:3306/db_csdnbbs?characterEncoding=utf8";
    
    String user = "root";
    String password = "123456";
	
    public Init_CSDN(int choose) throws ClassNotFoundException, SQLException, Exception{
    	if (choose>=0)
    		build(choose);
    }
    
	public void build(int choose) throws ClassNotFoundException, SQLException, Exception{
		Class.forName(driver);
		if(choose == 1){
			this.con = DriverManager.getConnection(url1, user, password);
		}
		else if (choose == 2){
			this.con = DriverManager.getConnection(url2, user, password);
		}else if (choose == 3){
			this.con = DriverManager.getConnection(url3, user, password);
		}
	}
	
	public void close() throws SQLException{
		this.con.close();
	}
	
	public JSONArray executeCypher_new(int choose, String cypher, int length, boolean flag) throws ClassNotFoundException, Exception{
//		build(choose);
		try(Statement statement = this.con.createStatement() ){
			JSONArray array = new JSONArray();
			
			//System.out.println(cypher);
			ResultSet rs = statement.executeQuery(cypher);
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount();
			int count = 0;
			
			//System.out.println(columnCount);
			
			while (rs.next()) {        
		        JSONObject jsonObj = new JSONObject();
		        
		        for (int i = 1; i <= columnCount; i++) {  
		            String columnName =md.getColumnLabel(i);
		            //System.out.println("columnName "+columnName);
		            String value = rs.getString(columnName); 
		            //System.out.println("value "+value);
		            jsonObj.put(columnName, value);  
	            } 
		        count ++;
				if(count > length) break; 
		        array.add(jsonObj);
		    }  
			
//			close();
			//System.out.println(array);
			return array;
		}
	}
}


public class App 
{
	public static int local = 0;
	public static Logger logger = Logger.getLogger(App.class);  
//		public static PrintStream tc_number_of_country;
		public static Map<String, String> map_code_country = new HashMap<String, String>();
		
		public static String getCurrentTime() {
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.format(date);
		}
		
	    public static void main(String[] args) throws Exception {
	    	
	        
	    	work();//Slight Different

//	    	System.out.println("----------***************-----------");
//			System.out.println("----------------END-----------------");
//			System.out.println("----------***************-----------");
	    }

	    /** 
	     * 读取流 
	     *  
	     * @param inStream 
	     * @return 字节数组 
	     * @throws Exception 
	     */  
	    public static byte[] readStream(InputStream inStream) throws Exception {  
	        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();  
	        byte[] buffer = new byte[1024];  
	        int len = -1;  
	        while ((len = inStream.read(buffer)) != -1) {  
	            outSteam.write(buffer, 0, len);  
	        }  
	        outSteam.close();  
	        inStream.close();  
	        return outSteam.toByteArray();  
	    }  
	    
	    

	    /*
	    	更新topcoder上不同国家的人数的信息
	    */
	    public static void work() throws SQLException, IOException{
	    		
	    	Map<String, Object> numbers;
			numbers = new HashMap<String, Object>();
			
			int tot = 45109830;
			int core = 16;//实验室的电脑CPU核心数是16
			int threads = 2*core+2;//合适的线程的数量: CPU核心数量*2 +2 个线程 
			int each = tot/threads;
			int start, end;
			
			Init con[] = new Init[threads+1];
			
			for (int i = 1; i <= threads; ++i){
				start = (i-1)*each + 1;
				end = i*each;
				if (i == threads)
					end = tot;
				System.out.println(i+" "+start+" "+end);
				con[i] = new Init(i,start,end);
			}
			
			
			for (int i = 1; i <= threads; ++i){
				con[i].start();
			}
			
//			con.executeCypher_N();
			
			
//			con.Init_release();
//			outfile.println("}");
//			outfile.close();
	    }
}




