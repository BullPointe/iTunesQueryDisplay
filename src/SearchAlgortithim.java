
import java.util.ArrayList;

import java.util.Random;

public class SearchAlgorithim {
	private JavaUrlConnectionReader reader;
	private String url = "http://ip-api.com/json";
	private String weatherUrl = "http://api.wunderground.com/api/52633b82f70e1334/conditions/q/";
	private JSONObject userLocation;
	private	ArrayList<String> weatherResults = new ArrayList<String>();

	public SearchAlgorithim() 
	{
		getUserWeather();
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
	private void setUserLocation()
	{
		reader = new JavaUrlConnectionReader(url);
		String locationData = reader.getAllSongs();
		
		JSONParser parser = new JSONParser();
		JSONObject jsonObject;
		
		try
		{
			jsonObject = (JSONObject)parser.parse(locationData);
			userLocation = jsonObject;
			
			
		}
		catch(Exception e)
		{
			System.out.println("Falied Location");
		
		}
	}
	
	public ArrayList<String> getUserWeather()
	{
		//Note: index 6 is temperature, index 7 is condition, index 9 is humidity
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
			JSONObject cityWeather =  (JSONObject) jsonObject.get("current_observation");
			

			
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
}
