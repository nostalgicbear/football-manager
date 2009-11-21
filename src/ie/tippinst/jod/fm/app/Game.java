package ie.tippinst.jod.fm.app;

import ie.tippinst.jod.fm.model.Club;
import ie.tippinst.jod.fm.model.Injury;
import ie.tippinst.jod.fm.model.Nation;
import ie.tippinst.jod.fm.model.NonPlayer;
import ie.tippinst.jod.fm.model.Person;
import ie.tippinst.jod.fm.model.Player;
import ie.tippinst.jod.fm.model.Stadium;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Game {
	
	private List<Person> personList;
	private List<Nation> nationList;
	private List<Club> clubList;
	private List<Stadium> stadiumList;
	private List<Injury> injuryList;
	private static Game game = null;
	
	private Game(){
	}
	
	public static Game getInstance(){
		if(game == null)
			game = new Game();
		return game;
	}
	
	public void loadDatabase(){
		// Lists used to store all objects
		personList = new ArrayList<Person>();
		nationList = new ArrayList<Nation>();
		clubList = new ArrayList<Club>();
		stadiumList = new ArrayList<Stadium>();
		injuryList = new ArrayList<Injury>();
		
		// Temporary variables to store references
		Nation personNationality = null;
		Nation clubNationality = null;
		Stadium clubStadium = null;
		Club personClub = null;
		Injury playerInjury = null;
		
		// Iterator references used when processing each list of objects
		XMLDecoder decoder = null;
		
		File file = new File("stadium.xml");
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			decoder = new XMLDecoder(bos);
			while(true)
				try{
					stadiumList.add((Stadium) decoder.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		file = new File("injury.xml");
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			decoder = new XMLDecoder(bos);
			while(true)
				try{
					injuryList.add((Injury) decoder.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		Iterator<Stadium> iStadium = stadiumList.iterator();
		while(iStadium.hasNext()){
			System.out.println(iStadium.next());
		}
		
		Iterator<Injury> iInjury = injuryList.iterator();
		while(iInjury.hasNext()){
			System.out.println(iInjury.next());
		}
		
		file = new File("club.xml");
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			decoder = new XMLDecoder(bos);
			while(true){
				try{
					clubList.add((Club) decoder.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		Iterator<Club> iClub = clubList.iterator();
		while(iClub.hasNext()){
			System.out.println(iClub.next());
		}
		
		file = new File("nation.xml");
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			decoder = new XMLDecoder(bos);
			while(true)
				try{
					nationList.add((Nation) decoder.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		Iterator<Nation> iNation = nationList.iterator();
		while(iNation.hasNext()){
			System.out.println(iNation.next());
		}
		
		file = new File("player.xml");
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			decoder = new XMLDecoder(bos);
			while(true)
				try{
					personList.add((Person) decoder.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		file = new File("nonplayer.xml");
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			decoder = new XMLDecoder(bos);
			while(true)
				try{
					personList.add((Person) decoder.readObject());
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		//System.out.println(stadiumList.size());
		/*Iterator<Stadium> iStadium = stadiumList.iterator();
		while(iStadium.hasNext()){
			System.out.println(iStadium.next());
		}*/
		
		/*try {
			Class.forName("org.gjt.mm.mysql.Driver");
			
			//Create a statement
			Statement statement;
			
			//Place the results from out statements somewhere
			ResultSet personResult;
			ResultSet playerResult;
			ResultSet nonPlayerResult;
			ResultSet nationResult;
			ResultSet clubResult;
			ResultSet stadiumResult;
			ResultSet injuryResult;
			
			//Connect to the Database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/footballmanager?user=root&password=football");
			
			// Get all nations from database and store in list
			statement = connection.createStatement();
			nationResult = statement.executeQuery("SELECT * FROM nation;");
			
			while(nationResult.next()){
				nationList.add(new Nation(nationResult.getInt(1), nationResult.getString(2), nationResult.getInt(3)));
			}
			
			// Get all stadiums from database and store in list
			statement = connection.createStatement();
			stadiumResult = statement.executeQuery("SELECT * FROM stadium;");
			
			while(stadiumResult.next()){
				stadiumList.add(new Stadium(stadiumResult.getInt(1), stadiumResult.getString(2), stadiumResult.getInt(3)));
			}
			
			// Get all clubs from database and store in list
			statement = connection.createStatement();
			clubResult = statement.executeQuery("SELECT * FROM club;");
			
			iNation = nationList.iterator();
			while(clubResult.next()){
				
				// Get nationality of club
				while(iNation.hasNext()){
					Nation currentNation = iNation.next();
					if(currentNation.getId() == clubResult.getInt(5)){
						clubNationality = currentNation;
					}
				}
				iStadium = stadiumList.iterator();
				
				// Get home stadium of club
				while(iStadium.hasNext()){
					Stadium stadium = iStadium.next();
					if(stadium.getId() == clubResult.getInt(6)){
						clubStadium = stadium;
					}
				}
				clubList.add(new Club(clubResult.getInt(1), clubResult.getString(2), clubResult.getInt(3), clubResult.getDouble(4),
						clubStadium, clubNationality));
			}
			
			// Get all injuries from database and store in list
			statement = connection.createStatement();
			injuryResult = statement.executeQuery("SELECT * FROM injury;");
			
			while(injuryResult.next()){
				injuryList.add(new Injury(injuryResult.getInt(1), injuryResult.getString(2), injuryResult.getInt(3)));
			}
			
			// Get all persons from database
			statement = connection.createStatement();
			personResult = statement.executeQuery("SELECT * FROM person;");
			
			// Get all players from database
			statement = connection.createStatement();
			playerResult = statement.executeQuery("SELECT * FROM player;");
			
			// Get all non-players from database
			statement = connection.createStatement();
			nonPlayerResult = statement.executeQuery("SELECT * FROM nonplayer;");
			
			// Store all players and non-players in a list of persons
			while(personResult.next()){
				
				// Get nationality of person
				while(iNation.hasNext()){
					Nation currentNation = iNation.next();
					if(currentNation.getId() == personResult.getInt(9)){
						personNationality = currentNation;
					}
				}
				
				iClub = clubList.iterator();
				
				// Get person's club
				while(iClub.hasNext()){
					Club club = iClub.next();
					if(club.getId() == personResult.getInt(10)){
						personClub = club;
					}
				}
					if(personResult.getBoolean(11)){
					playerResult.next();
					
					iInjury = injuryList.iterator();
					
					// Get player's injury
					while(iInjury.hasNext()){
						Injury injury = iInjury.next();
						if(injury.getId() == playerResult.getInt(14)){
							playerInjury = injury;
						}
					}
					personList.add(new Player(personResult.getInt(1), personResult.getString(2), personResult.getString(3),
							personNationality, personResult.getDouble(4), personResult.getInt(5), new Date(), personResult.getInt(7),
							personResult.getInt(8), new Date(), personClub, 5000000.0, 10000000.0, playerResult.getInt(2), playerResult.getInt(3),
							playerResult.getInt(4), playerResult.getInt(5), 90, 100, 100, playerResult.getInt(6), playerResult.getInt(7),
							playerResult.getInt(8), playerResult.getInt(9),	playerResult.getInt(10), playerResult.getInt(11), playerResult.getInt(12),
							playerResult.getInt(13), playerInjury));
				}
				else{
					nonPlayerResult.next();
					personList.add(new NonPlayer(personResult.getInt(1), personResult.getString(2), personResult.getString(3),
							personNationality, personResult.getDouble(4), personResult.getInt(5), new Date(), personResult.getInt(7),
							personResult.getInt(8), new Date(), personClub, nonPlayerResult.getInt(2), nonPlayerResult.getInt(3),
							nonPlayerResult.getInt(4), nonPlayerResult.getInt(5), nonPlayerResult.getInt(6), nonPlayerResult.getInt(7)));
				}
			}
			
			statement.clearBatch();
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
	}

	public List<Person> getPersonList() {
		return personList;
	}

	public List<Nation> getNationList() {
		return this.nationList;
	}

	public List<Club> getClubList() {
		return clubList;
	}

	public List<Stadium> getStadiumList() {
		return stadiumList;
	}

	public List<Injury> getInjuryList() {
		return injuryList;
	}
	
	public List<String> getNationNames(){
		List<String> listOfNames = new ArrayList<String>();
		List<Nation> list = getNationList();
		Iterator<Nation> i = list.iterator();
		while(i.hasNext()){
			listOfNames.add(i.next().getName());
		}
		return listOfNames;
	}

	public void createNewUser(String firstName, String surname, Date dob, String nationality, String club) {
		List<Nation> nationList = getNationList();
		Iterator<Nation> iNation = nationList.iterator();
		Nation nation = null;
		while(iNation.hasNext()){
			nation = iNation.next();
			if(nation.getName().equals(nationality)){
				break;
			}
		}
		List<Club> clubList = getClubList();
		Iterator<Club> iClub = clubList.iterator();
		Club userClub = null;
		while(iClub.hasNext()){
			userClub = iClub.next();
			if(userClub.getName().equals(club)){
				break;
			}
		}
		NonPlayer user = new NonPlayer(firstName, surname, nation, 5000, new Date(), 100, 200, 1, 20, 1, 1, 1, 1, userClub);
		getPersonList().add(user);
		System.out.println(user);
		Iterator<Person> iPerson = personList.iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if((p instanceof NonPlayer)&&(p.getCurrentClub().getId() == userClub.getId())&&(((NonPlayer)p).getManagerRole() == 20)&&(iPerson.hasNext())){
				System.out.println(p);
				iClub = clubList.iterator();
				Club c = null;
				while(iClub.hasNext()){
					c = iClub.next();
					if(c.getName().equals("No Club"))
					break;
				}
				p.setCurrentClub(c);
				System.out.println(p);
				break;
			}
		}
	}	
}
