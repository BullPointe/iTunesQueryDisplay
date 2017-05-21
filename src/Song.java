import javax.swing.ImageIcon;

public class Song 
{
	
	//create an instance variable for every data field that you need
	//Then make a constructor that initializes all of the data to make a song
	
	private String artistName;
	private String songName;
	private ImageIcon songPicture;
	
	Song(String aName, String sName, ImageIcon sPicture)
	{
		artistName = aName;
		songName = sName;
		songPicture = sPicture;
	}
	
	public String getArtistName()
	{
		return artistName;
	}
	
	public String getSongName()
	{
		return songName;
	}
	
	public ImageIcon getIcon()
	{
		return songPicture;
	}

}
