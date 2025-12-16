import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DaoModeratore {

	public boolean cercaModeratore(String username ,String  password) throws Exception{
		
		URL url = new URL("http://3.137.116.242/login_moderatore_aws.php");
		Map<String,Object> params = new LinkedHashMap<>();
		params.put("codice_moderatore",username);
		params.put("password_moderatore",password);
		
		StringBuilder postData = new StringBuilder();
		for(Map.Entry<String, Object> param : params.entrySet()){
			if(postData.length()!=0) postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
			}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		StringBuilder sb = new StringBuilder();
		String response= new String();
		for (int c;(c = in.read()) >= 0;){
			sb.append((char)c);
			response = sb.toString();
			
			
			/*
			JSONObject myResponse = new JSONObject(response.toString());
			System.out.println("Result after reading JSON Response");
			JSONArray jsonArray = myResponse.getJSONArray("");
			JSONObject jsonObjectTemp = jsonArray.getJSONObject(0);
			System.out.println((jsonObjectTemp.getString("nome_utente")));*/
			
		}
		//System.out.println(response);
		try{
			JSONArray jsonArray = new JSONArray(response);
	      
			return true;
		
		
		}catch(JSONException e){
			
			return false;
			
		}
		
	
		
		
	}
	
	public void creaModeratore(String username ,String  password) throws Exception{
		
		URL url = new URL("http://3.137.116.242/registrazione_moderatore_aws.php");
		Map<String,Object> params = new LinkedHashMap<>();
		params.put("codice_moderatore",username);
		params.put("password_moderatore",password);

		
		StringBuilder postData = new StringBuilder();
		for(Map.Entry<String, Object> param : params.entrySet()){
			if(postData.length()!=0) postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
			}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		StringBuilder sb = new StringBuilder();
		String response= new String();
		for (int c;(c = in.read()) >= 0;){
			sb.append((char)c);
			response = sb.toString();
			
			
			/*
			JSONObject myResponse = new JSONObject(response.toString());
			System.out.println("Result after reading JSON Response");
			JSONArray jsonArray = myResponse.getJSONArray("");
			JSONObject jsonObjectTemp = jsonArray.getJSONObject(0);
			System.out.println((jsonObjectTemp.getString("nome_utente")));*/
			
		}
		//System.out.println(response);
		
		
	
		
		
	}
	
public boolean verificaModeratore(String username ) throws Exception{
		
		URL url = new URL("http://3.137.116.242/controllamoderatore_aws.php");
		Map<String,Object> params = new LinkedHashMap<>();
		params.put("codice_moderatore",username);
		
		
		StringBuilder postData = new StringBuilder();
		for(Map.Entry<String, Object> param : params.entrySet()){
			if(postData.length()!=0) postData.append('&');
			postData.append(URLEncoder.encode(param.getKey(),"UTF-8"));
			postData.append('=');
			postData.append(URLEncoder.encode(String.valueOf(param.getValue()),"UTF-8"));
			}
		byte[] postDataBytes = postData.toString().getBytes("UTF-8");
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		conn.setRequestProperty("Content-Length",String.valueOf(postDataBytes.length));
		conn.setDoOutput(true);
		conn.getOutputStream().write(postDataBytes);
		
		Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
		
		StringBuilder sb = new StringBuilder();
		String response= new String();
		for (int c;(c = in.read()) >= 0;){
			sb.append((char)c);
			response = sb.toString();
			
			
			/*
			JSONObject myResponse = new JSONObject(response.toString());
			System.out.println("Result after reading JSON Response");
			JSONArray jsonArray = myResponse.getJSONArray("");
			JSONObject jsonObjectTemp = jsonArray.getJSONObject(0);
			System.out.println((jsonObjectTemp.getString("nome_utente")));*/
			
		}
		//System.out.println(response);
		try{
			JSONArray jsonArray = new JSONArray(response);
	       
			return false;
		
		
		}catch(JSONException e){
			
			return true;
			
		}
		
	
		
		
	}
	
	
	
	
	
}
