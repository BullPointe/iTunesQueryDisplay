
public class Main 
{

	public static void main(String[] args) 
	{
		
		//Two web service API examples
		//https://itunes.apple.com/search?term=pop
		//"http://currencies.apps.grandtrunk.net/currencies"
		
		JavaUrlConnectionReader reader = 
				new JavaUrlConnectionReader("https://itunes.apple.com/search?term=pop");
		reader.parseSongData();

	}

}
