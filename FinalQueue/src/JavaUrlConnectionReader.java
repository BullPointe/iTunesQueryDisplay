import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;


public class JavaUrlConnectionReader
{
	private String URL;
	
	public JavaUrlConnectionReader(String url)
	{
		URL = url;
	}
	
	
	//Complete the parser to create all the song objects
    public ArrayList<Song> parseSongData()
    {
		ArrayList <Song> list = new ArrayList<Song>();
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		try 
		{
			jsonObject = (JSONObject)parser.parse(this.getAllSongs());
			System.out.println(jsonObject.keySet());
			JSONArray array =  (JSONArray) jsonObject.get("results");
			JSONObject o = (JSONObject) array.get(1);
			System.out.println(o.get("trackCensoredName"));
			System.out.println(o.get("artistName"));
			list.add(new Song((String)o.get("artistName")));
		}
		catch(ParseException e)
		{
			System.out.print("ERROR: " ); 
			e.printStackTrace();
		}
		return list;
    }
	
	
	//Returns all songs based on the URL fetch
	//The string returned is a JSON file
	public String getAllSongs()
	{
		
		 StringBuilder content = new StringBuilder();
		 
		    try
		    {
		      // create a URL object
		      //This URL returns all the possible currencies 
		      URL url = new URL(URL);
		 
		      // create a URL connection object
		      URLConnection urlConnection = url.openConnection();
		 
		    
		      // wrap the URL connection in a buffered reader
		      BufferedReader bufferedReader = new BufferedReader(
		    		  new InputStreamReader(urlConnection.getInputStream()));
		 
		      String line;
		 
		      // read from the URL connection via the buffered reader
		      while ((line = bufferedReader.readLine()) != null)
		      {
		        content.append(line + "\n");
		      }
		      
		      bufferedReader.close();
		    }//end try
		    catch(Exception e)
		    {
		         e.printStackTrace();
		    }
		  
		return content.toString();
	}
	
	
	
 
}