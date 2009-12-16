package ie.tippinst.jod.fm.app;

import ie.tippinst.jod.fm.model.Club;
import ie.tippinst.jod.fm.model.Competition;
import ie.tippinst.jod.fm.model.Injury;
import ie.tippinst.jod.fm.model.League;
import ie.tippinst.jod.fm.model.Match;
import ie.tippinst.jod.fm.model.Message;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Game {
	
	private List<Person> personList;
	private List<Nation> nationList;
	private List<Club> clubList;
	private List<Stadium> stadiumList;
	private List<Injury> injuryList;
	private List<Competition> competitionList;
	private static Game game = null;
	private Iterator<Person> iPerson;
	private Iterator<Nation> iNation;
	private Iterator<Club> iClub;
	private Iterator<Stadium> iStadium;
	@SuppressWarnings("unused")
	private Iterator<Injury> iInjury;
	private Iterator<Competition> iCompetition;
	private Calendar date;
	private List<Message> messages;
	private Club userClub;

	/* This constructs a new game object with the initial date set at 2 July 2009*/
	private Game(){
		super();
		date = new GregorianCalendar();
		date.set(2009, 6, 2);
	}
	
	/* As this is class is a singleton a new game object is returned only if no previous one has been instantiated*/
	public static Game getInstance(){
		if(game == null)
			game = new Game();
		return game;
	}
	
	/* This method loads all xml data into the game*/
	public void loadDatabase(){
		
		// Lists used to store all objects
		personList = new ArrayList<Person>();
		nationList = new ArrayList<Nation>();
		clubList = new ArrayList<Club>();
		stadiumList = new ArrayList<Stadium>();
		injuryList = new ArrayList<Injury>();
		competitionList = new ArrayList<Competition>();
		messages = new ArrayList<Message>();
				
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
		
		// Load all injuries from xml file into injury objects
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
		
		// Load all leagues from xml file into competition objects
		try {
			decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("league.xml"))));
			while(true)
				try{
					Competition c = (Competition) decoder.readObject();
					competitionList.add(c);					
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
					iCompetition = competitionList.iterator();
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
					while(iCompetition.hasNext()){
						Competition competition = iCompetition.next();
						if(c.getLeague().getId() == competition.getId()){
							c.setLeague((League) competition);
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
					((Player) p).setPosition();
					((Player) p).setCurrentAbility();
					((Player) p).setMarketValue(this.getDate());
					((Player) p).setStatus(0);
					//System.out.println(((Player) p).getStatus());
					((Player) p).setSaleValue();
					((Player) p).setMatchCondition(70);
					((Player) p).setMorale(8000);
					((Player) p).setFitness(2000);
					((Player) p).setHappinessAtClub(7500);
					((Player) p).setFatigue(0);
					p.setAge(this.date);
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
					((NonPlayer) p).setRole();
					personList.add(p);
				} catch (ArrayIndexOutOfBoundsException e){
					break;
				}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			decoder.close();
		}
		
		// Set the squads and staff for all clubs in the game
		iClub = clubList.iterator();
		iPerson = personList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			List<Player> playerList = new ArrayList<Player>();
			List<NonPlayer> staffList = new ArrayList<NonPlayer>();
			while(iPerson.hasNext()){
				Person p = iPerson.next();
				if(p.getCurrentClub().getId()==c.getId() && p instanceof Player){
					playerList.add((Player) p);
				}
				else if(p.getCurrentClub().getId()==c.getId() && p instanceof NonPlayer){
					staffList.add((NonPlayer) p);
				}
			}
			c.setSquad(playerList);
			c.setStaff(staffList);
			c.setStatusOfPlayers();
		}
		
		// Assign all leagues with a list of their clubs
		iCompetition = competitionList.iterator();
		iClub = clubList.iterator();
		while(iCompetition.hasNext()){
			Competition c = iCompetition.next();
			List<Club> clubList = new ArrayList<Club>();
			while(iClub.hasNext()){
				Club club = iClub.next();
				if(club.getLeague().getId() == c.getId() && c instanceof League){
					clubList.add((Club) club);
				}
			}
			((League) c).setTeams(clubList);
		}
		
		// Assign the initial table for all leagues in the game
		iCompetition = competitionList.iterator();
		while(iCompetition.hasNext()){
			Competition c = iCompetition.next();
			((League) c).setTable();
			((League) c).setFixtures(((League) c).generateFixtures());
		}
		
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			Match[][] fixtures = null;
			List<Match> clubFixtures = new ArrayList<Match>();
			iCompetition = competitionList.iterator();
			while(iCompetition.hasNext()){
				Competition comp = iCompetition.next();
				if(((League)comp).getTeams().contains(c)){
					fixtures = ((League) comp).getFixtures();
					for(int i = 0; i < fixtures.length; i++){
						for(int j = 0; j < fixtures[i].length; j++){
							if((fixtures[i][j].getHomeTeam().getId() == c.getId()) || (fixtures[i][j].getAwayTeam().getId() == c.getId())){
								clubFixtures.add(fixtures[i][j]);
							}
						}
					}
				}
			}
			c.setFixtures(clubFixtures);
		}
	}
	
	/*This returns all clubs from a particular competition*/
	public List<Club> getTeams(String competition){
		List<Club> teams = null;
		iCompetition = competitionList.iterator();
		while(iCompetition.hasNext()){
			Competition c = iCompetition.next();
			if(c.getName().equals(competition)){
				teams = ((League) c).getTeams();
			}
		}
		return teams;
	}
	
	/*This returns the squad of a particular club*/
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
	
	/*This returns the fixtures of a particular club*/
	public List<Match> getFixtures(String club){
		List<Match> fixtures = null;
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			if(c.getName().equals(club)){
				fixtures = c.getFixtures();
			}
		}
		//System.out.println(club);
		return fixtures;
	}
	
	/*This returns all players in the game*/
	public List<Player> getPlayers(){
		List<Player> players = new ArrayList<Player>();
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if(p instanceof Player){
				players.add((Player) p);
			}
		}
		return players;
	}
	
	//This returns all staff in the game that the user can employ*/
	public List<NonPlayer> getAllStaff(){
		List<NonPlayer> staff = new ArrayList<NonPlayer>();
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if(p instanceof NonPlayer && p.getId() != 0 && ((NonPlayer) p).getChairmanRole() != 20){
				staff.add((NonPlayer) p);
			}
		}
		return staff;
	}
	
	/*Gets the league table as a String array*/
	public String[][] getLeagueTable(String league){
		int[][] table;
		Competition c = null;
		iCompetition = competitionList.iterator();
		while(iCompetition.hasNext()){
			c = iCompetition.next();
			if(c.getName().equals(league)){
				break;
			}
		}
		table = ((League) c).getTable();
		String[][] tableToReturn = new String[table.length][table[0].length];
		List<Club> teams = ((League) c).getTeams();
		List<String> teamNames = new ArrayList<String>();
		iClub = teams.iterator();
		while(iClub.hasNext()){
			teamNames.add(iClub.next().getName());
		}
		//Collections.sort(teamNames);
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[0].length; j++){
				if(j == 1){
					tableToReturn[i][j] = teamNames.get(i);
				}
				else{
					tableToReturn[i][j] = table[i][j] + "";
				}
			}
		}
		((League) c).sortTable(tableToReturn);
		for(int i = 0; i < table.length; i++){
			for(int j = 0; j < table[0].length; j++){
				if((j == 8) && (Integer.parseInt(tableToReturn[i][j]) > 0)){
					tableToReturn[i][j] = "+" + tableToReturn[i][j];
				}
			}
		}
		return tableToReturn;
	}
	
	/*Gets league fixtures*/
	public List<String> getLeagueFixtures(String name, String date){
		DateFormat format = new SimpleDateFormat("dd-MMM-yy");
		Calendar cal = new GregorianCalendar();
		try {
			cal.setTime((Date) format.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Match[][] leagueFixtures = null;
		List<String> matches = new ArrayList<String>();
		iCompetition = competitionList.iterator();
		while(iCompetition.hasNext()){
			Competition c = iCompetition.next();
			if(c.getName().equals(name)){
				leagueFixtures = ((League) c).getFixtures();
			}
		}
		for(int i = 0; i < leagueFixtures.length; i++){
			for(int j = 0; j < leagueFixtures[i].length; j++){
				if((leagueFixtures[i][j].getDate().get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) && (leagueFixtures[i][j].getDate().get(Calendar.MONTH) == cal.get(Calendar.MONTH)) && (leagueFixtures[i][j].getDate().get(Calendar.YEAR) == cal.get(Calendar.YEAR))){
					matches.add(leagueFixtures[i][j].toString());
				}
			}
		}
		return matches;
	}
	
	/*Gets information for a player profile*/
	public List<String> getPlayerProfileInfo(String name){
		List<String> playerProfileInfo = null;
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if((p.getFirstName() + " " + p.getLastName()).equals(name)){
				playerProfileInfo = ((Player) p).getPlayerProfileInfo(date);
			}
		}
		return playerProfileInfo;
	}
	
	/*Gets information for a nonplayer's profile*/
	public List<String> getStaffProfileInfo(String name){
		List<String> staffProfileInfo = null;
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if((p.getFirstName() + " " + p.getLastName()).equals(name)){
				staffProfileInfo = ((NonPlayer) p).getStaffProfileInfo();
			}
		}
		return staffProfileInfo;
	}
	
	/*Gets a particular club's information*/
	public List<String> getClubInformation(String club){
		List<String> clubInformation = null;
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			if(c.getName().equals(club)){
				clubInformation = c.getClubInformation();
			}
		}
		return clubInformation;
	}
	
	/*Get the staff of a particular club*/
	public List<NonPlayer> getStaff(String club){
		List<NonPlayer> staff = null;
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
			if(c.getName().equals(club)){
				staff = c.getStaff();
			}
		}
		return staff;
	}
	
	/*Returns a sorted list of names of nations in the game*/
	public List<String> getNationNames(){
		List<String> listOfNames = new ArrayList<String>();
		iNation = nationList.iterator();
		while(iNation.hasNext()){
			listOfNames.add(iNation.next().getName());
		}
		Collections.sort(listOfNames);
		return listOfNames;
	}
	
	/*Creates the user in the game*/
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
		user.setRole();
		getPersonList().add(user);
		iPerson = personList.iterator();
		List<NonPlayer> staff = user.getCurrentClub().getStaff();
		staff.add(user);
		user.getCurrentClub().setStaff(staff);
		Iterator<NonPlayer> i = staff.iterator();
		NonPlayer chairman = null;
		while(i.hasNext()){
			chairman = i.next();
			if(chairman.getChairmanRole() == 20){
				break;
			}
		}
		messages.add(new Message(this.getDate(), "Welcome to " + userClub.getName(), "The chairman of " + userClub.getName() + ", " + chairman.getFirstName() + " " + chairman.getLastName() + ", would like to welcome you to the club!"));
		
		iPerson = personList.iterator();
		// If the user's club already has a manager remove them and assign them to no club
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			if((p instanceof NonPlayer)&&(p.getCurrentClub().getId() == userClub.getId())&&(((NonPlayer)p).getManagerRole() == 20)&&(iPerson.hasNext())){
				iClub = clubList.iterator();
				Club c = null;
				p.setCurrentClub(c);
				staff.remove(p);
				user.getCurrentClub().setStaff(staff);
				break;
			}
		}
		this.userClub = userClub;
	}
	
	public boolean continueGame(boolean processFixtures){
		if(!(processFixtures)){
			this.date.add(Calendar.DATE, 1);
		}
		Match[][] leagueFixtures = null;
		iCompetition = competitionList.iterator();
		while(iCompetition.hasNext()){
			Competition c = iCompetition.next();
			leagueFixtures = ((League) c).getFixtures();
			for(int i = 0; i < leagueFixtures.length; i++){
				for(int j = 0; j < leagueFixtures[i].length; j++){
					if((leagueFixtures[i][j].getDate().get(Calendar.DAY_OF_MONTH) == this.getDate().get(Calendar.DAY_OF_MONTH)) && (leagueFixtures[i][j].getDate().get(Calendar.MONTH) == this.getDate().get(Calendar.MONTH)) && (leagueFixtures[i][j].getDate().get(Calendar.YEAR) == this.getDate().get(Calendar.YEAR))){
						if(!(processFixtures)){
							return true;
						}
						leagueFixtures[i][j].generateResult();
					}
				}
			}
		}
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			p.setAge(this.date);
		}
		return false;
	}
	
	public String getMessageBody(String header){
		String message = null;
		Iterator<Message> i = messages.iterator();
		while(i.hasNext()){
			Message m = i.next();
			if(m.getHeading().equals(header)){
				message = m.getBody();
				break;
			}
		}
		return message;
	}
	
	public void offerContractToPlayer(int value, String player, int wages, int lengthOfContract, int status){
		iPerson = personList.iterator();
		Person p = null;
		while(iPerson.hasNext()){
			p = iPerson.next();
			if((p instanceof Player) && ((p.getFirstName() + " " + p.getLastName()).equals(player))){
				break;
			}
		}
		Calendar c = new GregorianCalendar(this.getDate().get(Calendar.YEAR), 5, 30);
		c.add(Calendar.YEAR, lengthOfContract);
		Calendar cal = new GregorianCalendar(this.getDate().get(Calendar.YEAR), this.getDate().get(Calendar.MONTH), this.getDate().get(Calendar.DATE));
		cal.add(Calendar.DATE, 3);
		if(userClub.offerContract((Player) p, wages, c, status)){
			((Player) p).transferPlayer(value, userClub, (double) wages, c, status);			
			messages.add(new Message(cal, player + " Accepts Contract", player + " has accepted your contract offer!"));
		}
		else{
			messages.add(new Message(cal, player + " Rejects Contract", player + " has rejected your contract offer!"));
		}
	}
	
	public void makeOfferForPlayer(String player, int value){
		iPerson = personList.iterator();
		Person p = null;
		while(iPerson.hasNext()){
			p = iPerson.next();
			if((p instanceof Player) && ((p.getFirstName() + " " + p.getLastName()).equals(player))){
				break;
			}
		}
		Calendar c = new GregorianCalendar(this.getDate().get(Calendar.YEAR), this.getDate().get(Calendar.MONTH), this.getDate().get(Calendar.DATE));
		c.add(Calendar.DATE, 3);
		if(userClub.makeOffer((Player) p, value)){
			messages.add(new Message(c, "Offer for " + player + " Accepted", p.getCurrentClub().getName() + " have accepted your €" + value + " offer for " + player + "!  You now have permission to offer him a contract!"));
		}
		else{
			messages.add(new Message(c, "Offer for " + player + " Rejected", p.getCurrentClub().getName() + " have rejected your €" + value + " offer for " + player + "!"));
		}
	}
	
	/* This method returns the current ingame date */
	public Calendar getDate() {
		return date;
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
	
	public List<Message> getMessages() {
		Iterator<Message> iMessage = messages.iterator();
		List<Message> list = new ArrayList<Message>();
		while(iMessage.hasNext()){
			Message m = iMessage.next();
			if(this.getDate().getTimeInMillis() >= m.getDate().getTimeInMillis()){
				list.add(m);
			}
		}
		Collections.sort(list);
		return list;
	}
	
	public List<Player> getShortlist(String manager){
		iPerson = personList.iterator();
		Person p = null;
		while(iPerson.hasNext()){
			p = iPerson.next();
			if((p instanceof NonPlayer) && ((p.getFirstName() + " " + p.getLastName()).equals(manager))){
				break;
			}
		}		
		return ((NonPlayer) p).getShortlist();
	}
	
	public boolean checkShortlistForPlayer(String playerName, String managerName){
		iPerson = personList.iterator();
		Person manager = null;
		Person player = null;
		while(iPerson.hasNext()){
			manager = iPerson.next();
			if((manager instanceof NonPlayer) && ((manager.getFirstName() + " " + manager.getLastName()).equals(managerName))){
				break;
			}
		}
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			player = iPerson.next();
			if((player instanceof Player) && ((player.getFirstName() + " " + player.getLastName()).equals(playerName))){
				break;
			}
		}
		
		if(((NonPlayer) manager).getShortlist().contains(player)){
			return true;
		}
		return false;
	}
	
	public void addPlayerToShortlist(String playerName, String managerName){
		iPerson = personList.iterator();
		Person manager = null;
		Person player = null;
		while(iPerson.hasNext()){
			manager = iPerson.next();
			if((manager instanceof NonPlayer) && ((manager.getFirstName() + " " + manager.getLastName()).equals(managerName))){
				break;
			}
		}
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			player = iPerson.next();
			if((player instanceof Player) && ((player.getFirstName() + " " + player.getLastName()).equals(playerName))){
				break;
			}
		}		
		List<Player> shortlist = ((NonPlayer) manager).getShortlist();
		shortlist.add((Player) player);
		((NonPlayer) manager).setShortlist(shortlist);
	}
	
	public void removePlayerFromShortlist(String playerName, String managerName){
		iPerson = personList.iterator();
		Person manager = null;
		Person player = null;
		while(iPerson.hasNext()){
			manager = iPerson.next();
			if((manager instanceof NonPlayer) && ((manager.getFirstName() + " " + manager.getLastName()).equals(managerName))){
				break;
			}
		}
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			player = iPerson.next();
			if((player instanceof Player) && ((player.getFirstName() + " " + player.getLastName()).equals(playerName))){
				break;
			}
		}		
		List<Player> shortlist = ((NonPlayer) manager).getShortlist();
		shortlist.remove((Player) player);
		((NonPlayer) manager).setShortlist(shortlist);
	}
}
