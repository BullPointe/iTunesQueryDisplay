import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class SimpleScreen extends JFrame
{
	private JLabel label;
	private Container contentPane;
	private ArrayList<Song> listOfSongs = new ArrayList<Song>();
	private String artistName = "";
	public SimpleScreen()
	{
		
		JavaUrlConnectionReader reader = 
				new JavaUrlConnectionReader("https://itunes.apple.com/search?term=pop");
		listOfSongs = reader.parseSongData();
		
		artistName = listOfSongs.get(0).getArtistName();
		createScreen();
	
	}
	
	private void createScreen()
	{
		contentPane = this.getContentPane(); //inherited method
		contentPane.setLayout(null);
		
		System.out.println(listOfSongs.get(0).getArtistName());
		label = new JLabel(artistName);
		label.setBounds(100, 100, 200, 100);
		contentPane.add(label);
		
		setTitle("Music");
		setVisible(true);
		setSize(500, 500);
	}

	public static void main(String[] args) 
	{
		SimpleScreen screen = new SimpleScreen();
		screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
