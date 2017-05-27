//import java.util.Locale;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class MainScreen extends JFrame{
	
	private Container contentPane;
	private JLabel welcomeJLabel,weatherJLabel;
	private JLabel tempFJLabel;
	private JLabel tempSkyJLabel;
	private JLabel tempAirJLabel;
	private JLabel tempHumJLabel;
	private JButton searchMusicJButton;
	private SearchAlgortithim searcher;
	private SearchMusic musicSearcher;
	private ArrayList<Song> currentSearchList,currentSearchList2,currentSearchList3;
	private ArrayList<JButton> songs1;
	private JPanel SongsJPanel;
	private JPanel Songs1JPanel;
	private JLabel SongsJLabel;
	private JButton nextJButton;
	private int pageNumber=1;
	private JButton backJButton;
	private int clickedPageIndex, clickedSongIndex;
	private JLabel songPictureJLabel;
	private JLabel SongNameJLabel;
	private JLabel ArtistNameJLabel;
	private	JButton songLinkJButton;
	private JButton songLinkPJButton;
	private URI uri;
	private Song displayedSong;
	private ArrayList<Song> songPlaylist = new ArrayList<Song>();
	private JButton AddPlaylistJButton, viewPlaylistJButton;
	private JLabel playlistTitleJLabel; 
	private JTextArea playlistTitles;
	private JButton playlistLinkJButton;
	private JScrollPane playlistScrollPane;
	
	
	public MainScreen() {
		//TODO Auto-generated constructor stub
		createUserInterface();
		musicSearcher = new SearchMusic();
		
	}

	private void createUserInterface() 
	{
		setUpWindow();
		setUpWelcomePage();
	}


	private void setUpWelcomePage() 
	{
		// TODO Auto-generated method stub
		welcomeJLabel = new JLabel("Welcome to the WeatherSmart Music Search");
		welcomeJLabel.setBounds(20, 10, 540, 100);
		welcomeJLabel.setForeground(Color.WHITE);
		welcomeJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(welcomeJLabel);
		
		weatherJLabel = new JLabel("Currently the Weather is:");
		weatherJLabel.setBounds(20, 70, 470, 100);
		weatherJLabel.setForeground(Color.WHITE);
		weatherJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(weatherJLabel);
		
		tempFJLabel = new JLabel("Temp in F :");
		tempFJLabel.setBounds(40, 110, 470, 100);
		tempFJLabel.setForeground(Color.WHITE);
		tempFJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(tempFJLabel);
		
		tempSkyJLabel = new JLabel("The Weather Outside :");
		tempSkyJLabel.setBounds(40, 150, 470, 100);
		tempSkyJLabel.setForeground(Color.WHITE);
		tempSkyJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(tempSkyJLabel);
		
		tempAirJLabel = new JLabel("The Air Pressure is :");
		tempAirJLabel.setBounds(40, 190, 470, 100);
		tempAirJLabel.setForeground(Color.WHITE);
		tempAirJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(tempAirJLabel);
		
		tempHumJLabel = new JLabel("The Humidity is :");
		tempHumJLabel.setBounds(40, 230, 470, 100);
		tempHumJLabel.setForeground(Color.WHITE);
		tempHumJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(tempHumJLabel);
		
		searchMusicJButton = new JButton("Enter the Music Warehouse !");
		searchMusicJButton.setBounds(10, 320, 540, 100);
		searchMusicJButton.setOpaque(false);
		searchMusicJButton.setForeground(Color.WHITE);
		searchMusicJButton.setContentAreaFilled(false);
		searchMusicJButton.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(searchMusicJButton);
		searchMusicJButton.addActionListener(
				
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						startMusicSearch(e);
					}	
				}
				);
	
		//SEARCHES AND ADDS THE WAETHER !!!!!
				this.addWeatherContents();
				
		viewPlaylistJButton = new JButton("View Playlist");
		viewPlaylistJButton.setBounds(670, 0, 200, 100);
		viewPlaylistJButton.setVisible(false);
		viewPlaylistJButton.setOpaque(false);
		viewPlaylistJButton.setForeground(Color.WHITE);
		viewPlaylistJButton.setContentAreaFilled(false);
		viewPlaylistJButton.setFont(new Font("Arial Black", Font.BOLD, 20));
		contentPane.add(viewPlaylistJButton);		
		viewPlaylistJButton.addActionListener(
				
				new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						cleardisplaySearchedSongs();
						displayPlaylistTitles();
					}
					
				}
				
				);
				

		SongsJLabel = new JLabel("Songs we Recommend: ");
		SongsJLabel.setBounds(195,-30, 470, 100);
		SongsJLabel.setForeground(Color.WHITE);
		SongsJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
		SongsJLabel.setVisible(false);
		contentPane.add(SongsJLabel);
				
				
//		SongsJPanel = new JPanel();
//		SongsJPanel.setBounds(30, 50, 840, 200);
//		SongsJPanel.setLayout(null);
//		SongsJPanel.setBorder(new TitledBorder(""));
//		SongsJPanel.setVisible(false);
//		contentPane.add(SongsJPanel);
//		
//		Songs1JPanel = new JPanel();
//		Songs1JPanel.setBounds(30, 300, 650, 200);
//		Songs1JPanel.setLayout(null);
//		Songs1JPanel.setBorder(new TitledBorder(""));
//		Songs1JPanel.setVisible(false);
//		contentPane.add(Songs1JPanel);
				
		
		
		songs1 = new ArrayList<JButton>();
		
		
		int xpos1 = 75;
		int ypos1 = 50;
		
		for(int i= 0; i< 10 ; i++)
		{
				switch(i)
				{
				case 1: xpos1 = 249;ypos1= 50;break;
				case 2: xpos1 = 420;ypos1= 50; break;
				case 3: xpos1 = 5;ypos1= 220; break;
				case 4: xpos1 = 175;ypos1= 220;break;
				case 5: xpos1 = 345;ypos1= 220; break;
				case 6: xpos1 = 515;ypos1= 220; break;
				case 7: xpos1 = 249;ypos1= 390; break;
				case 8: xpos1 = 420;ypos1= 390; break;
				case 9: xpos1 = 75;ypos1= 390; break;

				}
				JButton tempJButton= new JButton("");
				tempJButton.setBounds(xpos1, ypos1, 160, 150);
				tempJButton.setFont(new Font("Arial Black", Font.BOLD, 12));
				tempJButton.setHorizontalTextPosition(JButton.CENTER);
				tempJButton.setVerticalTextPosition(JButton.BOTTOM);
				tempJButton.setVisible(false);
				tempJButton.setForeground(Color.WHITE);
				tempJButton.setOpaque(false);
				tempJButton.setContentAreaFilled(false);
				tempJButton.setBorderPainted(true);
				contentPane.add(tempJButton);
				songs1.add(tempJButton);
				
				tempJButton.addActionListener(
						new ActionListener()
						{

						
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								cleardisplaySearchedSongs();
								setClickedSongIndex(e);
								showClickedSong();
							}
							
							
						});
			}
		
		
		
			nextJButton = new JButton("");
			nextJButton.setBounds(700, 230, 150, 130);
			nextJButton.setIcon(new ImageIcon("nextButton.jpg"));
			nextJButton.setVisible(false);
			nextJButton.setOpaque(false);
			nextJButton.setContentAreaFilled(false);
			nextJButton.setBorderPainted(true);
			contentPane.add(nextJButton);
			nextJButton.addActionListener(
					
					new ActionListener()
					{

						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(pageNumber==1)
							{
								pageNumber++;
								displaySearchedSongs(true);
							}
							else if(pageNumber==2)
							{
								pageNumber++;
								displaySearchedSongs(true);
							}
							
							
						}
						
					}
					);
			
			backJButton = new JButton("");
			backJButton.setBounds(5, 230, 150, 130);
			backJButton.setIcon(new ImageIcon("backButton.jpg"));
			backJButton.setVisible(false);
			backJButton.setOpaque(false);
			backJButton.setContentAreaFilled(false);
			backJButton.setBorderPainted(true);
			contentPane.add(backJButton);
			backJButton.addActionListener(
					
					new ActionListener()
					{

						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							if(songs1.get(0).isVisible()==false)
							{
								if(pageNumber==1)
								{
									displaySearchedSongs(true);
								}
								else
								{
									displaySearchedSongs(false);
								}
								clearClickedSong();
							}
							else if(pageNumber==2)
							{
								pageNumber--;
								displaySearchedSongs(false);
							}
							else if(pageNumber==3)
							{
								pageNumber--;
								displaySearchedSongs(false);
							}
							
							
						}
						
					}
					);
			
			songPictureJLabel = new JLabel("");
			songPictureJLabel.setBounds(15,190, 470, 100);
			songPictureJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
			songPictureJLabel.setVisible(false);
			contentPane.add(songPictureJLabel);
			
			SongNameJLabel = new JLabel("Song Name ");
			SongNameJLabel.setBounds(195,160, 600, 100);
			SongNameJLabel.setForeground(Color.WHITE);
			SongNameJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
			SongNameJLabel.setVisible(false);
			contentPane.add(SongNameJLabel);
			
			ArtistNameJLabel = new JLabel("Artist Name");
			ArtistNameJLabel.setBounds(195,200, 850, 100);
			ArtistNameJLabel.setForeground(Color.WHITE);
			ArtistNameJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
			ArtistNameJLabel.setVisible(false);
			contentPane.add(ArtistNameJLabel);
			
			playlistTitleJLabel = new JLabel("Current Playlist");
			playlistTitleJLabel.setBounds(414,10, 850, 100);
			playlistTitleJLabel.setForeground(Color.WHITE);
			playlistTitleJLabel.setFont(new Font("Arial Black", Font.BOLD, 20));
			playlistTitleJLabel.setVisible(false);
			contentPane.add(playlistTitleJLabel);
			
			playlistTitles = new JTextArea();
			playlistTitles.setBounds(190,100, 800, 400);
			playlistTitles.setBackground(Color.BLACK);
			playlistTitles.setForeground(Color.WHITE);
			playlistTitles.setFont(new Font("Arial Black", Font.BOLD, 20));
			playlistTitles.setEditable(false);
//			playlistTitles.setVisible(false);
//			contentPane.add(playlistTitles);
			
			playlistScrollPane = new JScrollPane(playlistTitles);
			playlistScrollPane.setBounds(190,100, 650, 400);
			playlistScrollPane.setForeground(Color.BLACK);
			playlistScrollPane.setBackground(Color.WHITE);
			playlistScrollPane.setFont(new Font("Arial Black", Font.BOLD, 20));
			playlistScrollPane.setVisible(false);
			contentPane.add(playlistScrollPane);
			
			
			
			
			playlistLinkJButton = new JButton("Listen to the Playlist");
			playlistLinkJButton.setBounds(190,520, 650, 100);
			playlistLinkJButton.setFont(new Font("Arial Black", Font.BOLD, 27));
			playlistLinkJButton.setVisible(false);
			playlistLinkJButton.setOpaque(false);
			playlistLinkJButton.setForeground(Color.WHITE);
			playlistLinkJButton.setContentAreaFilled(false);
			playlistLinkJButton.setBorderPainted(true);
			contentPane.add(playlistLinkJButton);
			playlistLinkJButton.addActionListener(
					
					new ActionListener()
					{

						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							String videoIds = "";
							if(songPlaylist.isEmpty()== false)
							{
								for(int i=0;i<songPlaylist.size();i++)
								{
									if(i== songPlaylist.size()-1)
									{
										videoIds+=songPlaylist.get(i).getYoutubeVideoID();
									}
									else
									{
										videoIds+=songPlaylist.get(i).getYoutubeVideoID()+",";
									}
								}
							}
							String link = "http://www.youtube.com/watch_videos?video_ids="+ videoIds;
							System.out.println(link);
							
							try{
								 uri = new URI(link);
							}
							catch(URISyntaxException f)
							{
								f.printStackTrace();
							}
							openUri(uri);
						}
						
					}
					
					);
			
			
			
			
			songLinkJButton = new JButton("Listen to the Full Song");
			songLinkJButton.setBounds(480,300, 450, 150);
			songLinkJButton.setFont(new Font("Arial Black", Font.BOLD, 27));
			songLinkJButton.setVisible(false);
			songLinkJButton.setOpaque(false);
			songLinkJButton.setForeground(Color.WHITE);
			songLinkJButton.setContentAreaFilled(false);
			songLinkJButton.setBorderPainted(true);
			contentPane.add(songLinkJButton);
			songLinkJButton.addActionListener(
					
					new ActionListener(){

						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							String temp = displayedSong.getYoutubeUrl();
							try {
								uri = new URI(temp);
							} catch (URISyntaxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							openUri(uri);
						}
						
					}
					
					);
			

			songLinkPJButton = new JButton("Listen to the Preview");
			songLinkPJButton.setBounds(10,300, 450, 150);
			songLinkPJButton.setFont(new Font("Arial Black", Font.BOLD, 27));
			songLinkPJButton.setVisible(false);
			songLinkPJButton.setOpaque(false);
			songLinkPJButton.setForeground(Color.WHITE);
			songLinkPJButton.setContentAreaFilled(false);
			songLinkPJButton.setBorderPainted(true);
			contentPane.add(songLinkPJButton);
			songLinkPJButton.addActionListener(
					
					new ActionListener(){

						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							String temp = displayedSong.getSongUrl();
							try {
								uri = new URI(temp);
							} catch (URISyntaxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							openUri(uri);
						}
						
					}
					
					);
			
			
			AddPlaylistJButton = new JButton("Add to Playlist");
			AddPlaylistJButton.setBounds(240,460, 450, 150);
			AddPlaylistJButton.setFont(new Font("Arial Black", Font.BOLD, 27));
			AddPlaylistJButton.setVisible(false);
			AddPlaylistJButton.setOpaque(false);
			AddPlaylistJButton.setForeground(Color.WHITE);
			AddPlaylistJButton.setContentAreaFilled(false);
			AddPlaylistJButton.setBorderPainted(true);
			contentPane.add(AddPlaylistJButton);
			AddPlaylistJButton.addActionListener(
					
					new ActionListener()
					{
						public void actionPerformed(ActionEvent arg0) {
							// TODO Auto-generated method stub
							boolean ifAdd = true;
							for(int i=0; i<songPlaylist.size();i++)
							{
								if(songPlaylist.get(i).getSongName().equals(displayedSong.getSongName()))
								{
									ifAdd = false;
								}
							}
							if(ifAdd== true)
							{
								displayedSong.setYoutubeVideoID(searcher.getYouTubeVideoId(displayedSong.getArtistName().replaceAll(" ", "+")+ "+" + displayedSong.getSongName().replaceAll(" ", "+")));
								songPlaylist.add(displayedSong);
								System.out.println("ADDED SONG: " + displayedSong.getSongName());
							}
						}
						
					}
					);
			
			
			
			
	}
		
		
		
		
				
				
				
				
	

	private void displayPlaylistTitles() 
	{
		this.playlistLinkJButton.setEnabled(true);
		this.setSize(1000,900);
		playlistTitleJLabel.setVisible(true);
		playlistTitles.setVisible(true);
//		playlistTitles.setEditable(true);
		playlistScrollPane.setVisible(true);
		this.playlistLinkJButton.setVisible(true);
		
		String temp = "";
		if(this.songPlaylist.isEmpty()==false)
		{
			for(int i=0;i<this.songPlaylist.size();i++)
			{
				temp+= (i+1)+". "+  this.songPlaylist.get(i).getArtistName()+ "- " + this.songPlaylist.get(i).getSongName() +"\n";
			}
		}
		else
		{
			this.playlistLinkJButton.setEnabled(false);
		}
		System.out.println(temp);
		this.playlistTitles.setText(temp);
		this.playlistTitles.setCaretPosition(0);
//		playlistTitles.setEditable(false);
		
		
	}

	private void openUri(URI uri)
	{
		if (Desktop.isDesktopSupported()) 
		{
		      try 
		      {
		        Desktop.getDesktop().browse(uri);
		      } 
		      catch (IOException e) 
		      { 
		    	  /* TODO: error handling */   
		      }
		}
		else 
		{ 
			/* TODO: error handling */ 
		}
		
	}

	private void setClickedSongIndex(ActionEvent e) 
	{
		for(int i=0; i< 10; i++)
		{
			if(e.getSource()==songs1.get(i))
			{
				System.out.println("Working");
				if(pageNumber==1)
				{
					if(i<3)
					{
						this.clickedPageIndex=1;
						this.clickedSongIndex = i;
					}
					else if(i<7)
					{
						this.clickedPageIndex=2;
						this.clickedSongIndex = i;
					}
					else if(i<10)
					{
						this.clickedPageIndex=3;
						this.clickedSongIndex = i;
					}
				}
				else if(pageNumber==2)
				{
					if(i<3)
					{
						this.clickedPageIndex=1;
						this.clickedSongIndex = i+3;
					}
					else if(i<7)
					{
						this.clickedPageIndex=2;
						this.clickedSongIndex = i+4;
					}
					else if(i<10)
					{
						this.clickedPageIndex=3;
						this.clickedSongIndex = i+3;
					}
				}
				else if(pageNumber==3)
				{
					if(i<3)
					{
						this.clickedPageIndex=1;
						this.clickedSongIndex = i+6;
					}
					else if(i<7)
					{
						this.clickedPageIndex=2;
						this.clickedSongIndex = i+8;
					}
					else if(i<10)
					{
						this.clickedPageIndex=3;
						this.clickedSongIndex = i+6;
					}
				}
			}
		}
		
	}

	private void showClickedSong() 
	{
		this.setSize(1000,710);
		Song clickedSong = null;
		if(this.clickedPageIndex==1)
		{
			clickedSong = this.currentSearchList.get(this.clickedSongIndex);
		}
		else if(this.clickedPageIndex==2)
		{
			clickedSong = this.currentSearchList2.get(this.clickedSongIndex);
		}
		else if(this.clickedPageIndex==3)
		{
			clickedSong = this.currentSearchList3.get(this.clickedSongIndex);
		}
		
		System.out.println(clickedSong.getSongName());
		
		this.SongNameJLabel.setVisible(true);
		this.songPictureJLabel.setVisible(true);
		this.ArtistNameJLabel.setVisible(true);
		this.songLinkJButton.setVisible(true);
		this.songLinkPJButton.setVisible(true);
		this.AddPlaylistJButton.setVisible(true);
		
		this.displayedSong = clickedSong;
		
		this.SongNameJLabel.setText("Song Title:"+ " "+ clickedSong.getSongName());
		this.ArtistNameJLabel.setText("Artist:"+" "+clickedSong.getArtistName());
		this.songPictureJLabel.setIcon(clickedSong.getIcon());
//		String songUrl = searcher.getYouTubeUrl(clickedSong.getArtistName().replaceAll(" ", "+")+ "+" + clickedSong.getSongName().replaceAll(" ", "+"));
		this.displayedSong.setYoutubeUrl(searcher.getYouTubeUrl(clickedSong.getArtistName().replaceAll(" ", "+")+ "+" + clickedSong.getSongName().replaceAll(" ", "+")));
		this.songLinkJButton.setText("Listen to the Full Song");
		
		
		
	}
	
	private void clearClickedSong()
	{
		this.SongNameJLabel.setVisible(false);
		this.songPictureJLabel.setVisible(false);
		this.ArtistNameJLabel.setVisible(false);
		this.songLinkJButton.setVisible(false);
		this.songLinkPJButton.setVisible(false);
		this.AddPlaylistJButton.setVisible(false);
	}
	
	
	

	
	
	private void displaySearchedSongs(boolean isUp)
	{
		this.backJButton.setBounds(5, 230, 150, 130);
		this.SongsJLabel.setVisible(true);
		this.viewPlaylistJButton.setVisible(true);
		this.playlistTitleJLabel.setVisible(false);
		this.playlistTitles.setVisible(false);
		this.playlistLinkJButton.setVisible(false);
		playlistScrollPane.setVisible(false);
		if(pageNumber==1)
		{
			this.setSize(890,610);
			
//			SongsJPanel.setVisible(true);
//			Songs1JPanel.setVisible(true);
			
			for(int i=0;i<10;i++)
			{
				
				if(i<3)
				{
					songs1.get(i).setIcon(this.currentSearchList.get(i).getIcon());
					songs1.get(i).setText(this.currentSearchList.get(i).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				else if(i<7)
				{
					songs1.get(i).setIcon(this.currentSearchList2.get(i).getIcon());
					songs1.get(i).setText(this.currentSearchList2.get(i).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				else if(i<10)
				{
					songs1.get(i).setIcon(this.currentSearchList3.get(i).getIcon());
					songs1.get(i).setText(this.currentSearchList3.get(i).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				
				
				if(isUp == false)
				{
					songs1.get(i).setBounds(songs1.get(i).getX()-160, songs1.get(i).getY(),  songs1.get(i).getWidth(),songs1.get(i).getHeight());
					SongsJLabel.setBounds(SongsJLabel.getX()-17, SongsJLabel.getY(), SongsJLabel.getWidth(), SongsJLabel.getHeight());
					nextJButton.setBounds(nextJButton.getX()-17, nextJButton.getY(), nextJButton.getWidth(), nextJButton.getHeight());
					viewPlaylistJButton.setBounds(viewPlaylistJButton.getX()-21, viewPlaylistJButton.getY(), viewPlaylistJButton.getWidth(), viewPlaylistJButton.getHeight());
				}
			}
			nextJButton.setVisible(true);
			backJButton.setVisible(false);
		}
		else if(pageNumber==2)
		{
			this.setSize(1100,610);
			
			for(int i=0;i<10;i++)
			{
				if(i<3)
				{
					songs1.get(i).setIcon(this.currentSearchList.get(i+3).getIcon());
					songs1.get(i).setText(this.currentSearchList.get(i+3).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				else if(i<7)
				{
					songs1.get(i).setIcon(this.currentSearchList2.get(i+4).getIcon());
					songs1.get(i).setText(this.currentSearchList2.get(i+4).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				else if(i<10)
				{
					songs1.get(i).setIcon(this.currentSearchList3.get(i+3).getIcon());
					songs1.get(i).setText(this.currentSearchList3.get(i+3).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				if(isUp)
				{
					SongsJLabel.setBounds(SongsJLabel.getX()+17, SongsJLabel.getY(), SongsJLabel.getWidth(), SongsJLabel.getHeight());
					songs1.get(i).setBounds(songs1.get(i).getX()+160, songs1.get(i).getY(),  songs1.get(i).getWidth(),songs1.get(i).getHeight());
					nextJButton.setBounds(nextJButton.getX()+17, nextJButton.getY(), nextJButton.getWidth(), nextJButton.getHeight());
					viewPlaylistJButton.setBounds(viewPlaylistJButton.getX()+21, viewPlaylistJButton.getY(), viewPlaylistJButton.getWidth(), viewPlaylistJButton.getHeight());
				}
			}
			nextJButton.setVisible(true);
			backJButton.setVisible(true);
		}
		else if(pageNumber==3)
		{
			this.setSize(1100,610);
			
			for(int i=0;i<10;i++)
			{
				if(i<3)
				{
					songs1.get(i).setIcon(this.currentSearchList.get(i+6).getIcon());
					songs1.get(i).setText(this.currentSearchList.get(i+6).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				else if(i<7)
				{
					songs1.get(i).setIcon(this.currentSearchList2.get(i+8).getIcon());
					songs1.get(i).setText(this.currentSearchList2.get(i+8).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				else if(i<10)
				{
					songs1.get(i).setIcon(this.currentSearchList3.get(i+6).getIcon());
					songs1.get(i).setText(this.currentSearchList3.get(i+6).getSongName());	
					songs1.get(i).setIconTextGap(0);
					songs1.get(i).setVisible(true);
				}
				
			}
			nextJButton.setVisible(false);
		}
		
	}


	
	
	
	public void cleardisplaySearchedSongs()
	{
		SongsJLabel.setVisible(false);
		
		for(int i=0;i<10;i++)
		{
			songs1.get(i).setIcon(this.currentSearchList.get(i).getIcon());
			songs1.get(i).setText(this.currentSearchList.get(i).getSongName());	
			songs1.get(i).setIconTextGap(0);
			songs1.get(i).setVisible(false);
		}
		nextJButton.setVisible(false);
		backJButton.setBounds(0,0, 150, 130);
		backJButton.setVisible(true);
		this.viewPlaylistJButton.setVisible(false);

	}

	private void addWeatherContents()
	{
		searcher = new SearchAlgortithim();
		ArrayList<String> tempWeather = searcher.getUserWeather();
		this.tempFJLabel.setText("The temp is : " + tempWeather.get(0) + " " +"F");
		this.tempSkyJLabel.setText("The Weather outside is : " + tempWeather.get(1));
		this.tempAirJLabel.setText("The Air Pressure is : " + tempWeather.get(2));
		this.tempHumJLabel.setText("The Humidity is : " + tempWeather.get(3));
	}
	
	
	private void clearWelcomePage() 
	{
		// TODO Auto-generated method stub
		this.welcomeJLabel.setVisible(false);
		this.weatherJLabel.setVisible(false);
		this.tempAirJLabel.setVisible(false);
		this.tempFJLabel.setVisible(false);
		this.tempHumJLabel.setVisible(false);
		this.tempSkyJLabel.setVisible(false);
		this.searchMusicJButton.setVisible(false);

		
	}
	
	private void showWelcomePage()
	{
		this.welcomeJLabel.setVisible(true);
		this.weatherJLabel.setVisible(true);
		this.tempAirJLabel.setVisible(true);
		this.tempFJLabel.setVisible(true);
		this.tempHumJLabel.setVisible(true);
		this.tempSkyJLabel.setVisible(true);
		this.searchMusicJButton.setVisible(true);
	}
	
	//START THE SEARCH 
		private void startMusicSearch(ActionEvent e) {
			// TODO Auto-generated method stub
//			String tempTerm = searcher.getTerm();
			
			String temp[] = searcher.generateSearch(); 
			String tempTerm1 = temp[0] ;
			String tempTerm2 = temp[1];
			String tempTerm3 = temp[2];
			this.currentSearchList = musicSearcher.MusicSearcher(tempTerm1, 20);
			this.currentSearchList2 = musicSearcher.MusicSearcher(tempTerm2,20);
			this.currentSearchList3 = musicSearcher.MusicSearcher(tempTerm3,20);

			System.out.println("The size of the recieved list " + this.currentSearchList.size());
			this.displaySearchedSongs(true);
			
		
			this.clearWelcomePage();
			
		}

	private void setUpWindow() {
		// TODO Auto-generated method stub
		
		contentPane = this.getContentPane();
		this.setTitle("WeatherSmart Music Search");
		this.setSize(600, 610);
		this.setVisible(true);
		this.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		contentPane.setForeground(Color.WHITE);
	}

}
