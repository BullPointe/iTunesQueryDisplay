import java.util.ArrayList;

public class SearchAlgortithim
{
	private JavaUrlConnectionReader reader;
	private String url = "http://ip-api.com/json";
	private String weatherUrl = "http://api.wunderground.com/api/52633b82f70e1334/conditions/q/";
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
	

	

}
