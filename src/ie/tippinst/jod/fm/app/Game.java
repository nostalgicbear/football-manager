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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Game {
	
	private List<Person> personList;
	private List<Nation> nationList;
	private List<Club> clubList;
	private List<Stadium> stadiumList;
	private List<Injury> injuryList;
	private static Game game = null;
	private Iterator<Person> iPerson;
	private Iterator<Nation> iNation;
	private Iterator<Club> iClub;
	private Iterator<Stadium> iStadium;
	private Iterator<Injury> iInjury;
	
	private Game(){
		super();
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
				
		XMLDecoder decoder = null;
		
		// Load all stadia from xml file into stadium objects
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("stadium.xml"))));
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
		
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("injury.xml"))));
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
		
		// Load all nations from xml file into nation objects
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("nation.xml"))));
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
		
		// Load all clubs from xml file into club objects
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("club.xml"))));
			while(true){
				try{
					Club c = (Club) decoder.readObject();
					iNation = nationList.iterator();
					iStadium = stadiumList.iterator();
					while(iStadium.hasNext()){
						Stadium s = iStadium.next();
						if(c.getHomeGround().getId() == s.getId()){
							c.setHomeGround(s);
							break;
						}
					}
					while(iNation.hasNext()){
						Nation n = iNation.next();
						if(c.getNationality().getId() == n.getId()){
							c.setNationality(n);
							break;
						}
					}
					clubList.add(c);
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}

		iClub = clubList.iterator();
		iPerson = personList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			List<Player> list = new ArrayList<Player>();
			while(iPerson.hasNext()){
				Person p = iPerson.next();
				if(p.getCurrentClub().getId()==c.getId() && p instanceof Player){
					list.add((Player) p);
				}
			}
			c.setSquad(list);
		}	
		
		// Load all players from xml file into person objects
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("player.xml"))));
			while(true)
				try{
					Person p = (Person) decoder.readObject();
					iNation = nationList.iterator();
					iClub = clubList.iterator();
					while(iClub.hasNext()){
						Club c = iClub.next();
						if(p.getCurrentClub().getId() == c.getId()){
							p.setCurrentClub(c);
							break;
						}
					}
					while(iNation.hasNext()){
						Nation n = iNation.next();
						if(p.getNationality().getId() == n.getId()){
							p.setNationality(n);
							break;
						}
					}
					personList.add(p);
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		// Load all nonplayers from xml file into person objects
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("nonplayer.xml"))));
			while(true)
				try{
					Person p = (Person) decoder.readObject();
					iNation = nationList.iterator();
					iClub = clubList.iterator();
					while(iClub.hasNext()){
						Club c = iClub.next();
						if(p.getCurrentClub().getId() == c.getId()){
							p.setCurrentClub(c);
							break;
						}
					}
					while(iNation.hasNext()){
						Nation n = iNation.next();
						if(p.getNationality().getId() == n.getId()){
							p.setNationality(n);
							break;
						}
					}
					personList.add(p);
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		iClub = clubList.iterator();
		iPerson = personList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			List<Player> list = new ArrayList<Player>();
			while(iPerson.hasNext()){
				Person p = iPerson.next();
				if(p.getCurrentClub().getId()==c.getId() && p instanceof Player){
					list.add((Player) p);
				}
			}
			c.setSquad(list);
		}
		
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			if(c.getName().equals("Manchester United")){
				List<Player> list = c.getSquad();
				Iterator<Player> i = list.iterator();
				while(i.hasNext()){
					System.out.println(i.next());
				}
			}
		}
	}
	
	public List<Player> getSquad(String club){
		List<Player> squad = null;
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			if(c.getName().equals(club)){
				squad = c.getSquad();
			}
		}
		return squad;
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
	
	// Returns a sorted list of names of nations in the game
	public List<String> getNationNames(){
		List<String> listOfNames = new ArrayList<String>();
		iNation = nationList.iterator();
		while(iNation.hasNext()){
			listOfNames.add(iNation.next().getName());
		}
		Collections.sort(listOfNames);
		return listOfNames;
	}

	public void createNewUser(String firstName, String surname, Calendar dob, String nationality, String club) {
		iNation = nationList.iterator();
		Nation nation = null;
		
		// Get nationality of user
		while(iNation.hasNext()){
			nation = iNation.next();
			if(nation.getName().equals(nationality)){
				break;
			}
		}
		
		// Get club user wants to manage
		iClub = clubList.iterator();
		Club userClub = null;
		while(iClub.hasNext()){
			userClub = iClub.next();
			if(userClub.getName().equals(club)){
				break;
			}
		}
		
		// Create the user as a new nonplayer object and add them to the list of person objects in the game
		NonPlayer user = new NonPlayer(firstName, surname, nation, 5000, dob, 100, 200, 1, 20, 1, 1, 1, 1, userClub);
		getPersonList().add(user);
		System.out.println(user);
		iPerson = personList.iterator();
		
		// If the user's club already has a manager remove them and assign them to no club
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if((p instanceof NonPlayer)&&(p.getCurrentClub().getId() == userClub.getId())&&(((NonPlayer)p).getManagerRole() == 20)&&(iPerson.hasNext())){
				iClub = clubList.iterator();
				Club c = null;
				/*while(iClub.hasNext()){
					c = iClub.next();
					if(c.getName().equals("No Club"))
					break;
				}*/
				p.setCurrentClub(c);
				break;
			}
		}
	}	
}
