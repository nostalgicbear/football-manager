package ie.tippinst.jod.fm.app;

import ie.tippinst.jod.fm.db.Database;
import ie.tippinst.jod.fm.model.Club;
import ie.tippinst.jod.fm.model.Competition;
import ie.tippinst.jod.fm.model.League;
import ie.tippinst.jod.fm.model.Match;
import ie.tippinst.jod.fm.model.Message;
import ie.tippinst.jod.fm.model.Nation;
import ie.tippinst.jod.fm.model.NonPlayer;
import ie.tippinst.jod.fm.model.Person;
import ie.tippinst.jod.fm.model.Player;

import java.text.DateFormat;
import java.text.DecimalFormat;
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
	
	private static Game game = null;
	private Database db;
	//private List<Message> messages = new ArrayList<Message>();
	private Club userClub;

	/* This constructs a new game object with the initial date set at 2 July 2009*/
	private Game(){
		super();
		db = new Database();
	}
	
	/* As this is class is a singleton a new game object is returned only if no previous one has been instantiated*/
	public static Game getInstance(){
		if(game == null)
			game = new Game();
		return game;
	}
	
	/*This returns all clubs from a particular competition*/
	public List<Club> getTeams(String competition){
		return ((League) db.findCompetition(competition)).getTeams();
	}
	
	/*This returns the squad of a particular club*/
	public List<Player> getSquad(String club){
		return db.findClub(club).getSquad();
	}
	
	/*This returns the fixtures of a particular club*/
	public List<Match> getFixtures(String club){
		return db.findClub(club).getFixtures();
	}
	
	/*This returns all players in the game*/
	public List<Player> getPlayers(){
		List<Player> players = new ArrayList<Player>();
		Iterator<Person> i = db.getPersonList().iterator();
		while(i.hasNext()){
			Person p = i.next();
			if(p instanceof Player){
				players.add((Player) p);
			}
		}
		return players;
	}
	
	//This returns all staff in the game that the user can employ*/
	public List<NonPlayer> getAllStaff(){
		List<NonPlayer> staff = new ArrayList<NonPlayer>();
		Iterator<Person> i = db.getPersonList().iterator();
		while(i.hasNext()){
			Person p = i.next();
			if(p instanceof NonPlayer && p.getId() != 0 && ((NonPlayer) p).getChairmanRole() != 20){
				staff.add((NonPlayer) p);
			}
		}
		return staff;
	}
	
	/*Gets the league table as a String array*/
	public String[][] getLeagueTable(String competition){
		int[][] table;
		League league = (League) db.findCompetition(competition);
		table = league.getTable();
		String[][] tableToReturn = new String[table.length][table[0].length];
		List<Club> teams = league.getTeams();
		List<String> teamNames = new ArrayList<String>();
		Iterator<Club> iterator = teams.iterator();
		while(iterator.hasNext()){
			teamNames.add(iterator.next().getName());
		}
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
		league.sortTable(tableToReturn);
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
		leagueFixtures = ((League) db.findCompetition(name)).getFixtures();
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
		return((Player) db.findPerson(name)).getPlayerProfileInfo(db.getDate());
	}
	
	/*Gets information for a nonplayer's profile*/
	public List<String> getStaffProfileInfo(String name){
		return ((NonPlayer) db.findPerson(name)).getStaffProfileInfo();
	}
	
	/*Gets a particular club's information*/
	public List<String> getClubInformation(String club){
		return db.findClub(club).getClubInformation();
	}
	
	/*Get the staff of a particular club*/
	public List<NonPlayer> getStaff(String club){
		return db.findClub(club).getStaff();
	}
	
	/*Get the transfer budget of a particular club*/
	public double getTransferBudget(String club){
		return db.findClub(club).getTransferBudget();
	}
	
	/*Returns a sorted list of names of nations in the game*/
	public List<String> getNationNames(){
		List<String> listOfNames = new ArrayList<String>();
		Iterator<Nation> i = db.getNationList().iterator();
		while(i.hasNext()){
			listOfNames.add(i.next().getName());
		}
		Collections.sort(listOfNames);
		return listOfNames;
	}
	
	/*Creates the user in the game*/
	public void createNewUser(String firstName, String surname, Calendar dob, String nationality, String club) {
		
		// Create the user as a new nonplayer object and add them to the list of person objects in the game
		this.userClub = db.findClub(club);
		db.setUserClub(userClub);
		NonPlayer user = new NonPlayer(firstName, surname, db.findNation(nationality), 5000, dob, 100, 200, 1, 20, 1, 1, 1, 1, db.findClub(club));
		user.setRole();
		List<Person> list = db.getPersonList();
		list.add(user);
		db.setPersonList(list);
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
		db.getMessages().add(new Message((Calendar) db.getDate().clone(), "Welcome to " + this.userClub.getName(), "The chairman of " + this.userClub.getName() + ", " + chairman.getFirstName() + " " + chairman.getLastName() + ", would like to welcome you to the club!"));
		
		Iterator<Person> j = db.getPersonList().iterator();
		// If the user's club already has a manager remove them and assign them to no club
		while(j.hasNext()){
			Person p = j.next();
			if((p instanceof NonPlayer)&&(p.getCurrentClub().getId() == this.userClub.getId())&&(((NonPlayer)p).getManagerRole() == 20)&&(j.hasNext())){
				p.setCurrentClub(null);
				staff.remove(p);
				user.getCurrentClub().setStaff(staff);
				break;
			}
		}
	}
	
	public int continueGame(boolean processFixtures, List<String> players){
		int fixtures = 0;
		if(!(processFixtures)){
			db.getDate().add(Calendar.DATE, 1);
		}
		Match[][] leagueFixtures = null;
		Iterator<Competition> iterator = db.getCompetitionList().iterator();
		while(iterator.hasNext()){
			Competition c = iterator.next();
			leagueFixtures = ((League) c).getFixtures();
			for(int i = 0; i < leagueFixtures.length; i++){
				for(int j = 0; j < leagueFixtures[i].length; j++){
					if((leagueFixtures[i][j].getDate().get(Calendar.DAY_OF_MONTH) == db.getDate().get(Calendar.DAY_OF_MONTH)) && (leagueFixtures[i][j].getDate().get(Calendar.MONTH) == db.getDate().get(Calendar.MONTH)) && (leagueFixtures[i][j].getDate().get(Calendar.YEAR) == db.getDate().get(Calendar.YEAR))){
						if(!(processFixtures)){
							if(leagueFixtures[i][j].getHomeTeam().getId() == this.userClub.getId() || leagueFixtures[i][j].getAwayTeam().getId() == this.userClub.getId()){
								List<Player> selectedTeam = new ArrayList<Player>();
								Iterator<String> iteratorPlayers = players.iterator();
								while(iteratorPlayers.hasNext()){
									Player p = (Player) db.findPerson(iteratorPlayers.next());
									if(!(p.isInjured()))
										selectedTeam.add(p);
								}
								if(selectedTeam.size() < 11){
									db.getDate().add(Calendar.DATE, -1);
									return 2;
								}
								this.userClub.setSelectedTeam(selectedTeam);
								/*Iterator<Player> temp = this.userClub.getSelectedTeam().iterator();
								while(temp.hasNext()){
									System.out.println(temp.next().getLastName());
								}*/
							}
							fixtures = 1;
						}
						if(fixtures != 1){
							if(leagueFixtures[i][j].getHomeTeam().getId() != this.userClub.getId())
								leagueFixtures[i][j].getHomeTeam().setSelectedTeam(leagueFixtures[i][j].getHomeTeam().getBestTeam(leagueFixtures[i][j].getHomeTeam().getAvailablePlayers()));
							/*Iterator<Player> it = leagueFixtures[i][j].getHomeTeam().getSelectedTeam().iterator();
							while(it.hasNext()){
								Player p = it.next();
								System.out.println(p.getFirstName() + " " + p.getLastName());
							}
							System.out.println();*/
							if(leagueFixtures[i][j].getAwayTeam().getId() != this.userClub.getId())
								leagueFixtures[i][j].getAwayTeam().setSelectedTeam(leagueFixtures[i][j].getAwayTeam().getBestTeam(leagueFixtures[i][j].getAwayTeam().getAvailablePlayers()));
							/*it = leagueFixtures[i][j].getAwayTeam().getSelectedTeam().iterator();
							while(it.hasNext()){
								Player p = it.next();
								System.out.println(p.getFirstName() + " " + p.getLastName());
							}*/
							leagueFixtures[i][j].generateResult();
						}
					}
				}
			}
		}
		db.updateAllPersonAttributes();
		db.updateAllClubAttributes();
		return fixtures;
	}
	
	public String getMessageBody(int index){
		/*String message = null;
		Iterator<Message> i = db.getMessages().iterator();
		while(i.hasNext()){
			Message m = i.next();
			if(m.getId() == index){
				message = m.getBody();
				break;
			}
		}
		return message;*/
		return getMessages().get(index).getBody();
	}
	
	public Calendar getMessageDate(int index){
		return getMessages().get(index).getDate();
	}
	
	public void offerContractToPlayer(int value, String player, int wages, int lengthOfContract, int status){
		Calendar c = new GregorianCalendar(db.getDate().get(Calendar.YEAR), 5, 30);
		c.add(Calendar.YEAR, lengthOfContract);
		Calendar cal = new GregorianCalendar(db.getDate().get(Calendar.YEAR), db.getDate().get(Calendar.MONTH), db.getDate().get(Calendar.DATE));
		cal.add(Calendar.DATE, 3);
		if(userClub.offerContract((Player) db.findPerson(player), wages, c, status)){
			((Player) db.findPerson(player)).transferPlayer(value, userClub, (double) wages, c, status);			
			db.getMessages().add(new Message(cal, player + " Accepts Contract", player + " has accepted your contract offer!"));
		}
		else{
			db.getMessages().add(new Message(cal, player + " Rejects Contract", player + " has rejected your contract offer!"));
		}
	}
	
	public boolean makeOfferForPlayer(String manager, String player, int value){
		if(!(checkShortlistForPlayer(player, manager))){
			addPlayerToShortlist(player, manager);
		}
		Calendar c = new GregorianCalendar(db.getDate().get(Calendar.YEAR), db.getDate().get(Calendar.MONTH), db.getDate().get(Calendar.DATE));
		c.add(Calendar.DATE, 3);
		if(userClub.getTransferBudget() - value < 0){
			return false;
		}
		if(userClub.makeOffer((Player) db.findPerson(player), value)){
			db.getMessages().add(new Message(c, "Offer for " + player + " Accepted", db.findPerson(player).getCurrentClub().getName() + " have accepted your €" + value + " offer for " + player + "!  You now have permission to offer him a contract!"));
		}
		else{
			db.getMessages().add(new Message(c, "Offer for " + player + " Rejected", db.findPerson(player).getCurrentClub().getName() + " have rejected your €" + value + " offer for " + player + "!"));
		}
		return true;
	}
	
	public List<Message> getMessages() {
		Iterator<Message> iMessage = db.getMessages().iterator();
		List<Message> list = new ArrayList<Message>();
		while(iMessage.hasNext()){
			Message m = iMessage.next();
			if(db.getDate().getTimeInMillis() >= m.getDate().getTimeInMillis()){
				list.add(m);
			}
		}
		return list;
	}
	
	public List<Player> getShortlist(String manager){		
		return ((NonPlayer) db.findPerson(manager)).getShortlist();
	}
	
	public boolean checkShortlistForPlayer(String player, String manager){		
		if(((NonPlayer) db.findPerson(manager)).getShortlist().contains(db.findPerson(player)))
			return true;
		return false;
	}
	
	public void addPlayerToShortlist(String player, String manager){		
		List<Player> shortlist = ((NonPlayer) db.findPerson(manager)).getShortlist();
		shortlist.add((Player) db.findPerson(player));
		((Player) db.findPerson(player)).getInterested().add(db.findPerson(manager).getCurrentClub());
		((NonPlayer) db.findPerson(manager)).setShortlist(shortlist);
	}
	
	public void removePlayerFromShortlist(String player, String manager){		
		List<Player> shortlist = ((NonPlayer) db.findPerson(manager)).getShortlist();
		shortlist.remove((Player) db.findPerson(player));
		((Player) db.findPerson(player)).getInterested().remove(db.findPerson(manager).getCurrentClub());
		((NonPlayer) db.findPerson(manager)).setShortlist(shortlist);
	}
	
	public Calendar getDate(){
		return db.getDate();
	}
	
	public List<String> getClubFinances(String clubName){
		List<String> list = new ArrayList<String>();
		Club club = db.findClub(clubName);
		DecimalFormat format = new DecimalFormat("000,000");
		list.add("€" + format.format(club.getBankBalance()));
		list.add("€" + format.format(club.getTransferBudget()));
		return list;
	}
}
