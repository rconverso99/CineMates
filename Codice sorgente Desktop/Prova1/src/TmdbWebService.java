
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class TmdbWebService {
	
	public static final String SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=2e3034b8c110830b946972c7d30f5cb5&language=en-US&query=TITLE&page=1&include_adult=false";
	public static final String SEARCH_BY_IMDB_URL = "https://api.themoviedb.org/3/movie/ID?api_key=2e3034b8c110830b946972c7d30f5cb5";
	
    public static String sendGetRequest(String requestUrl){
    	StringBuffer response = new StringBuffer();
    	
    	try {
			URL url = new URL(requestUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Accept", "*/*");
			connection.setRequestProperty("Content-Type","application/json; charset=UTF-8");
			InputStream stream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			BufferedReader buffer = new BufferedReader(reader);
			String line;
			while((line = buffer.readLine()) != null){
				response.append(line);
			}
			buffer.close();
			connection.disconnect();
			
			
			
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
			//e.printStackTrace();
		}
    	
    	return response.toString();
    }
    
    public static String searchMovieByTitle(String title,String key){
    	/*try {
			title = URLEncoder.encode(title,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
    	String requestUrl = SEARCH_URL.replaceAll("TITLE", title).replaceAll("APIKEY", key);
    	if(sendGetRequest(requestUrl) != null){
    	return sendGetRequest(requestUrl);}else return null;
    }
    
    public static String searchMovieByImdb(String imdb , String key){
    	
    	String requestUrl = SEARCH_BY_IMDB_URL.replaceAll("ID", imdb).replaceAll("APIKEY",key);
    	return sendGetRequest(requestUrl);
    }
    
    public Film[] retrieveElencoFilm(String jsonString){
    	try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray("results");
			Film[] films = new Film[jsonArray.length()];
			for (int i = 0; i < jsonArray.length() ; i++){
				Film film = new Film();
				JSONObject jsonObjectTemp = jsonArray.getJSONObject(i);
				
				film.setTitolo(jsonObjectTemp.getString("title"));
				if(!jsonObjectTemp.isNull("release_date")){
				film.setAnno(jsonObjectTemp.getString("release_date"));}
				film.setCodice(jsonObjectTemp.getInt("id"));
				if(jsonObjectTemp.get("poster_path")!=null){
				film.setPoster("https://image.tmdb.org/t/p/w500"+jsonObjectTemp.get("poster_path"));}
	            film.setDescrizione(jsonObjectTemp.getString("overview"));
				System.out.println(film.getTitolo());
				films[i] = film; 
			}
			return films;
		} catch (JSONException e) {
			System.out.println("ERROR  ");
			e.printStackTrace();
			
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
			
		}
    }
    
    public Film retrieveFilm(String jsonString){
    	try{
    		Film film = new Film();
    		JSONObject jsonObject = new JSONObject(jsonString);
    		film.setTitolo(jsonObject.getString("title"));
			film.setAnno(jsonObject.getString("release_date"));
			film.setCodice(jsonObject.getInt("id"));
			if(jsonObject.get("poster_path")!=null){
			film.setPoster("https://image.tmdb.org/t/p/w500"+jsonObject.get("poster_path"));}
			film.setDescrizione(jsonObject.getString("overview"));
    		return film;
    		
    	}catch(JSONException e){
    		e.printStackTrace();
    		return null;
    	}
    }
    
    
    
    
}
