import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class DaoUtente {
	public Utente[] cercaUtente(String username) throws Exception{
		
		URL url = new URL("http://3.137.116.242/cercautente_aws.php");
		Map<String,Object> params = new LinkedHashMap<>();
		params.put("username",username);
		
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
		System.out.println(response);
		JSONArray jsonArray = new JSONArray(response);
		Utente[] utenti = new Utente[jsonArray.length()];
		
		
		for (int i = 0; i < jsonArray.length() ; i++){
			JSONObject jsonObjectTemp = jsonArray.getJSONObject(i);
			Utente user = new Utente();
			user.setNome(jsonObjectTemp.getString("nome_utente"));
			user.setCognome(jsonObjectTemp.getString("cognome_utente"));
			user.setUsername(jsonObjectTemp.getString("username"));
			if(jsonObjectTemp.get("foto_profilo")!=null){user.setFoto(jsonObjectTemp.get("foto_profilo"));}
			user.setEmail(jsonObjectTemp.getString("email_utente"));
			utenti[i] = user; 
		System.out.println("Il nome di: "+jsonObjectTemp.getString("username")+" e': "+jsonObjectTemp.get("nome_utente"));
		
		}
		return utenti;
		
		
	}
}
