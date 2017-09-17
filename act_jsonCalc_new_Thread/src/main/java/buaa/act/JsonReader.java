package buaa.act;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import net.sf.json.JSONObject;

public class JsonReader {
	 String readAll(Reader rd) throws IOException {
		    StringBuilder sb = new StringBuilder();
		    int cp;
		    while ((cp = rd.read()) != -1) {
		      sb.append((char) cp);
		    }
		    return sb.toString();
		  }
	 
	public JSONObject readJsonFromUrl(int choose, String query) throws MalformedURLException, IOException{
		String url = "";
		if(choose == 0){ // github
			url = "https://api.github.com/" + query + "&client_id=8c77acd5ff3d574eb164&client_secret=d6977df80b8becda3b335d9a77f28c3d961c95ae";
		}
		else if(choose == 1){// stackoverflow
			url = "https://api.stackexchange.com/2.2/" + query + "&site=stackoverflow";
//			url = "http://api.stackoverflow.com/0.8/questions/2886661";
//			System.out.println(url);
		}
		else if(choose == 2) {// topcoder
			
		}
		InputStream is;
		while(true) {
			try {
				is = new URL(url).openStream();
				break;
			} catch (IOException e){
				System.out.println(e.toString());
			}
			
		}
		try {
			BufferedReader rd = null; // = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), Charset.forName("UTF-8")));
			if(choose == 0){
				rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			}
			else if(choose == 1) {
				rd = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), Charset.forName("UTF-8")));
			}
		    String jsonText = readAll(rd);
		    JSONObject json = JSONObject.fromObject(jsonText);
//		    System.out.println(jsonText);
		    return json;
		  } finally {
		    is.close();
//			System.out.println("haha end");
		  }
	}
//	public static void main(String args[]) throws MalformedURLException, IOException {
//		JsonReader reader = new JsonReader();
//		for(int i = 0; i< 1; i++) {
//			
//			JSONObject json = reader.readJsonFromUrl(1, "users?page=1&pagesize=2&inname=tom");
//			System.out.println(json.toString());
//		}
//	}
}
