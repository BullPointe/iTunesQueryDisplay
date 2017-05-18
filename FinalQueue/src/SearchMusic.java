import java.util.ArrayList;

public class SearchMusic {
	private String Url;
	
	private ArrayList SongList;
	
	public ArrayList<Song> MusicSearcher(String term, Integer limit)
	{
		
			
			Url="https://itunes.apple.com/search?term"+term.replaceAll(" ", "+")+"&limit="+limit;
			JavaUrlConnectionReader test= new JavaUrlConnectionReader(Url);
			return test.parseSongData();
			
			
		
	}
	public ArrayList getSongList() {
		return SongList;
	}
	public void setSongList(ArrayList songList) {
		SongList = songList;
	}
	
}
