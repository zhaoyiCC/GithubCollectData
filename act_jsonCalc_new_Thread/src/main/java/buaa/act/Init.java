package buaa.act;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.neo4j.driver.v1.*;

public class Init extends Thread {
	
	final static int N = 7;

	private Connection con;
	private Session session[] = new Session[N];
	
	private Driver driver[] = new Driver[N];

	int start, end, id_thread;
	
	public Init(int id_thred_t, int start_t, int end_t) throws SQLException {
		start = start_t;
		end = end_t;
		id_thread = id_thred_t;
		for (int i = 0; i < N; ++i){
			driver[i] = null;
			session[i] = null;
		}
	}

	public void build_new(int id) throws SQLException{
		
		String address = "10.1.1.4";
		String login = "neo4j";
		String password = "buaaxzl";
		
		// choose github
		driver[id] = GraphDatabase.driver("bolt://10.1.1.4:7687",
	                AuthTokens.basic("neo4j", "buaaxzl"));
		session[id] = driver[id].session();
		
	}
	
	
	
	public void close() throws SQLException{
		this.con.close();
	}
	
	public void run(){

		try{
		
			String f_s;
			f_s = "out"  + String.valueOf(id_thread)+ ".txt";
			File f = new File(f_s);
			
	    	FileOutputStream tmp_f = new FileOutputStream(f);
	    	PrintStream outfile = new PrintStream(tmp_f);
	//    	outfile.println("hh");
	//    	
			for (int i = 0; i < N; ++i){
				build_new(i);
			}
			
	//		String cypher0 = "MATCH (n:Project) RETURN n.projectId as fx";
	//		StatementResult statementResult0 = session[0].run(cypher0);
	//		
	//        String cypher1 = "MATCH (n:Project) RETURN n.name as fx";
	//		StatementResult statementResult1 = session[1].run(cypher1);
	//		
	//		String cypher2 = "MATCH (n:Project) RETURN n.isForked as fx";
	//		StatementResult statementResult2 = session[2].run(cypher2);
			
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> isForkeds = new ArrayList<String>();
			
			ArrayList<String> languages = new ArrayList<String>();
			ArrayList<String> starNums = new ArrayList<String>();
			ArrayList<String> user_logins = new ArrayList<String>();
			
			String cypher0, cypher1, cypher2, cypher3, cypher4, cypher5;
			
			String user_login = null, name = null, language;
			
			int projectId, isForked, starNum = 0;
			
			Value ans;
			
			
	//		while(statementResult0.hasNext()){
			for (int i = start; i <= end; ++i){
			
				projectId = i;
				
	//			ans = statementResult0.next().get("fx");
	//			projectId = Integer.parseInt(ans.asString());
				
				cypher1 = "MATCH (n:Project) where n.projectId = '"
						+ String.valueOf(i)
						+ "' return n.name as fx";
				
	//			ans = statementResult1.next().get("fx");
	//			name = ans.asString();
				names = executeCypher_new(1, cypher1, 1, false);
				
				if (names.size() == 0)
					continue;
				
				name = names.get(0);
				
				
	//			ans = statementResult2.next().get("fx");
	//			isForked = ans.asInt();
				cypher2 = "MATCH (n:Project) where n.projectId = '"
						+ String.valueOf(i)
						+ "' return n.isForked as fx";
				isForkeds = executeCypher_new(2, cypher2, 1, true);
				
//				if (isForkeds.size() == 0)
//					continue;
				
				isForked = Integer.valueOf(isForkeds.get(0));
				
				
				
				cypher3 = "MATCH (m:Lang)-[r:WrittenWith]-(n:Project) where n.projectId='"
						+ String.valueOf(projectId)
						+ "' RETURN distinct(m.language) as fx";
				
				//MATCH (m:Lang)-[r:WrittenWith]-(n:Project) where n.projectId='1' RETURN m.language as fx
				languages = executeCypher_new(3, cypher3, 100000, false);
				
				
				cypher4 = "MATCH (m:User)-[r:Star]->(n:Project) where n.projectId='"
						+ String.valueOf(projectId)
						+ "'  RETURN count(r) as fx";
				
				starNums = executeCypher_new(4, cypher4, 1, true);
				
				starNum = Integer.parseInt(starNums.get(0));
	//			
				cypher5 = "MATCH (m:User)-[r:Own]->(n:Project) where n.projectId='"
						+ String.valueOf(projectId)
						+ "'  RETURN m.login as fx";
	//			
				user_logins = executeCypher_new(5, cypher5, 1, false);
				
				user_login = user_logins.get(0);
				
				String lan = "\"";
				
				for (int j = 0; j < languages.size(); ++j){
					if (j!=0)
						lan+=",";
					lan +=languages.get(j);
	//				System.out.println(languages.get(j));
		
	//				System.out.println("projectId: "+ projectId + " user_login: "+user_login+ " projectName: "+ name +" language: "+languages.get(j)
	//				+ " starNum: " + starNum   +" isForked: "+ isForked);
				
			
				}
				lan+="\"";
	//			System.out.println(lan);
				
//				System.out.println("projectId: "+ projectId + " user_login: "+user_login+ " projectName: "+ name +
//						" language: "+lan+
//						" starNum: " + starNum   +" isForked: "+ isForked);
				
				System.out.println(projectId + ","+name+","+user_login+ 
						
						"," + starNum   +","+ isForked+","+lan);
				
				outfile.println(projectId + ","+name+","+user_login+ 
						
						"," + starNum   +","+ isForked+","+lan);
				
				
			}
			return ;
		}catch (Exception e) { e.printStackTrace(); }
	}
	
	
	public ArrayList<String> executeCypher_new(int id, String cypher, int length, boolean flag) throws SQLException{

		String userId = "";
        String projectId = "";
//		build_new(choose);
        
		StatementResult statementResult = session[id].run(cypher);
					
//		ResultSet rs =  stmt.executeQuery(cypher);
		ArrayList<String> ret = new ArrayList<String>();
			
		int i = 0;
		while(statementResult.hasNext()){
			if(flag) {
					 
				Value ans =statementResult.next().get("fx");//System.out.println(ans);
				ret.add(Integer.toString(ans.asInt()));//ret.add(Integer.toString(rs.getInt("fx")));
			}else {
				Value ans =statementResult.next().get("fx");//System.out.println(ans);
//				ret.add(Integer.toString(ans.asInt()));
				ret.add(ans.asString());
			}
			i ++;
			if(i > length) break;
		}
		
		return ret;
	}
	
	
	public void Init_release(){
		for (int i = 0; i < N; ++i){
			session[i].close();
			driver[i].close();
		}
	}
	
}
