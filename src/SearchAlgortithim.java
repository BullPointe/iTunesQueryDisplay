import java.util.ArrayList;
import java.util.Random;

public class SearchAlgortithim
{
	private JavaUrlConnectionReader reader;
	private String url = "http://ip-api.com/json";
	private String weatherUrl = "http://api.wunderground.com/api/52633b82f70e1334/conditions/q/";
	private String youTubeUrl = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q=";
	private JSONObject userLocation;
	private	ArrayList<String> weatherResults = new ArrayList<String>();

	public SearchAlgortithim() 
	{
		// TODO Auto-generated constructor stub

	}
	
	private void setUserLocation()
	{
		reader = new JavaUrlConnectionReader(url);
		String locationData = reader.getAllSongs();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		
		try
		{
			jsonObject = (JSONObject)parser.parse(locationData);
			System.out.println(jsonObject.keySet());
//			System.out.println("checking");
			System.out.println(jsonObject.get("city"));
			System.out.println(jsonObject.get("timezone"));
			userLocation = jsonObject;
			
			
		}
		catch(Exception e)
		{
			System.out.println("Falied Location");
		
		}
	}
	
	public ArrayList<String> getUserWeather()
	{
		this.setUserLocation(); 
		String userZip = (String) userLocation.get("zip"); 
		weatherUrl += userZip+".json";
		
		reader = new JavaUrlConnectionReader(weatherUrl);
		String weatherData = reader.getAllSongs();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		
		try
		{
			jsonObject = (JSONObject)parser.parse(weatherData);
			System.out.println(jsonObject.keySet());
			JSONObject cityWeather =  (JSONObject) jsonObject.get("current_observation");
//			System.out.println("The temp in F is "+cityWeather.get("temp_f"));
//			System.out.println("The sky is " + cityWeather.get("weather"));
//			System.out.println("The pressure is "  + cityWeather.get("pressure_in"));
//			System.out.println("The Humidity is : " + cityWeather.get("relative_humidity"));
			
			//Place the Weather into the Array
			

			
			this.weatherResults.add(String.valueOf(cityWeather.get("temp_f")));
			this.weatherResults.add((String) cityWeather.get("weather"));
			this.weatherResults.add((String) cityWeather.get("pressure_in"));
			this.weatherResults.add((String) cityWeather.get("relative_humidity"));
			
			
			return this.weatherResults;
		}
		catch(ParseException  e)
		{
			System.out.println("Failed Weather");
		}
		
		
		return null;
	}
	
	public String getYouTubeUrl(String query)
	{
		return "https://www.youtube.com/watch?v=" + getYouTubeVideoId(query);
	}
	
	
	public String getYouTubeVideoId(String query)
	{
		System.out.println(query);
		String videoId = "";
		String temp = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=25&q="+query+"&type=video&key=AIzaSyDjGzz1cAc5clmjBe6uQSw0cCZraahsVAY";
		reader = new JavaUrlConnectionReader(temp);
		
		String youTubeData = reader.getAllSongs();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		
		try
		{
			jsonObject = (JSONObject)parser.parse(youTubeData);
			System.out.println(jsonObject.keySet());
			JSONArray youTubeVideos = (JSONArray) jsonObject.get("items");
			jsonObject = (JSONObject) youTubeVideos.get(0);
			jsonObject = (JSONObject) jsonObject.get("id");
			System.out.println(jsonObject.keySet());
			System.out.println(jsonObject.get("videoId"));
			videoId= (String) jsonObject.get("videoId");
			
		}
		catch(ParseException e)
		{
			System.out.println("Failed");
		}
		
		return videoId ;
	}
	
	
	public String[] generateSearch(){
		double temp = Double.parseDouble((weatherResults.get(0)));
		String weather = weatherResults.get(1).toLowerCase();
		ReadFile rf = new ReadFile();
		System.out.println(weather);
		if(weather.equals("overcast")){
			rf.setFileName("overcast.txt");
		}
		else if(weather.equals("partly cloudy")){
			rf.setFileName("Partly-Cloudy.txt");
		}
		else if(weather.equals("clear") || weather.equals("sunny")){
			if(temp >= 65){
				rf.setFileName("Warm-Clear.txt");
			}
			else{
				rf.setFileName("Cool-Clear.txt");
			}
		}
		else{
			rf.setFileName("Partly-Cloudy.txt");
		}
		String content = rf.getContents();
		String[] spl = content.split(";");
		String[] op = new String[3];
		int[] rands = three_random(spl.length);
		for(int i = 0; i < 3; i++){
			System.out.println(rands[i]);
			op[i] = spl[rands[i]];
		}
		return op;
		
	}
	private int[] three_random(int upper){
		int[] op = new int[3];
		int filled = 0;
		Random rand = new Random();
		while(filled < 3){
			boolean f = true;
			int current = rand.nextInt(upper);
			for(int i = 0; i < 3; i++){
				if(current == op[i]){
					f = false;
					break;
				}
			}
			if(f){
				op[filled] = current;
				filled += 1;
			}
		}
		return op;
		
	}
	

	

}
