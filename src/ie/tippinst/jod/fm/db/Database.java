package ie.tippinst.jod.fm.db;

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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

public class Database {
	
	private List<Person> personList;
	private List<Nation> nationList;
	private List<Club> clubList;
	private List<Stadium> stadiumList;
	private List<Injury> injuryList;
	private List<Competition> competitionList;
	private Iterator<Person> iPerson;
	private Iterator<Nation> iNation;
	private Iterator<Club> iClub;
	private Iterator<Stadium> iStadium;
	private Iterator<Injury> iInjury;
	private Iterator<Competition> iCompetition;
	private Calendar date;
	private List<Message> messages;
	private Club userClub;
	private XMLDecoder decoder = null;

	/* This constructs a new game object with the initial date set at 2 July 2009*/
	public Database(){
		super();
		date = new GregorianCalendar();
		date.set(2009, 6, 2);
		
		// Lists used to store all objects
		personList = new ArrayList<Person>();
		nationList = new ArrayList<Nation>();
		clubList = new ArrayList<Club>();
		stadiumList = new ArrayList<Stadium>();
		injuryList = new ArrayList<Injury>();
		competitionList = new ArrayList<Competition>();
		messages = new ArrayList<Message>();
		
		try {
			loadStadia();
			loadInjuries();
			loadNations();
			loadLeagues();
			loadClubs();
			loadPlayers();
			loadNonPlayers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		initialisePlayers();
		initialiseNonPlayers();
		initialiseClubs();
		initialiseLeagues();
	}
	
	public Competition findCompetition(String name){
		Competition c = null;
		iCompetition = competitionList.iterator();
		while(iCompetition.hasNext()){
			c = iCompetition.next();
			if(c.getName().equals(name)){
				break;
			}
		}
		return c;
	}
	
	public Club findClub(String name){
		Club c = null;
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			c = iClub.next();
			if(c.getName().equals(name)){
				break;
			}
		}
		return c;
	}
	
	public Person findPerson(String name){
		Person p = null;
		iPerson = personList.iterator();
		while(iPerson.hasNext()){
			p = iPerson.next();
			if((p.getFirstName() + " " + p.getLastName()).equals(name)){
				break;
			}
		}
		return p;
	}
	
	public Nation findNation(String name){
		Nation n = null;
		iNation = nationList.iterator();
		while(iNation.hasNext()){
			n = iNation.next();
			if(n.getName().equals(name)){
				break;
			}
		}
		return n;
	}
	
	private void setSquadAndStaff(){
		// Set the squads and staff for all clubs in the game
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			iPerson = personList.iterator();
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
			Iterator<Player> i = playerList.iterator();
			while(i.hasNext()){
				Player p = i.next();
				p.setMarketValue(this.getDate());
				p.setSaleValue();
			}
		}
	}
	
	private void initialiseNonPlayers() {
		iPerson = personList.iterator();
		while (iPerson.hasNext()) {
			Person p = iPerson.next();
			if (p instanceof NonPlayer) {
				iNation = nationList.iterator();
				iClub = clubList.iterator();
				while (iClub.hasNext()) {
					Club c = iClub.next();
					if (p.getCurrentClub().getId() == c.getId()) {
						p.setCurrentClub(c);
						break;
					}
				}
				while (iNation.hasNext()) {
					Nation n = iNation.next();
					if (p.getNationality().getId() == n.getId()) {
						p.setNationality(n);
						break;
					}
				}
				((NonPlayer) p).setRole();
			}
		}
	}

	private void initialisePlayers() {
		iPerson = personList.iterator();
		while (iPerson.hasNext()) {
			Person p = iPerson.next();
			if (p instanceof Player) {
				iNation = nationList.iterator();
				iClub = clubList.iterator();
				while (iClub.hasNext()) {
					Club c = iClub.next();
					if (p.getCurrentClub().getId() == c.getId()) {
						p.setCurrentClub(c);
						break;
					}
				}
				while (iNation.hasNext()) {
					Nation n = iNation.next();
					if (p.getNationality().getId() == n.getId()) {
						p.setNationality(n);
						break;
					}
				}
				((Player) p).setPosition();
				((Player) p).setCurrentAbility();
				((Player) p).setMatchCondition(70);
				((Player) p).setMorale(8000);
				((Player) p).setFitness(2000);
				((Player) p).setHappinessAtClub(7500);
				((Player) p).setFatigue(0);
				p.setAge(this.date);
			}
		}
	}

	private void setLeagueFixtures() {
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

	private void initialiseClubs() {
		iClub = clubList.iterator();
		while(iClub.hasNext()){
			Club c = iClub.next();
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
		}
		setSquadAndStaff();		
	}

	private void initialiseLeagues() {
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
		setLeagueFixtures();
	}

	private void loadStadia() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("stadium.xml"))));
		while(true){
			try{
				stadiumList.add((Stadium) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	private void loadInjuries() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("injury.xml"))));
		while(true){
			try{
				injuryList.add((Injury) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	private void loadNations() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("nation.xml"))));
		while(true){
			try{
				nationList.add((Nation) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	private void loadLeagues() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("league.xml"))));
		while(true){
			try{
				competitionList.add((Competition) decoder.readObject());					
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	private void loadClubs() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("club.xml"))));
		while(true){
			try{
				clubList.add((Club) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}		
	}
	
	private void loadPlayers() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("player.xml"))));
		while(true){
			try{
				personList.add((Person) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	private void loadNonPlayers() throws FileNotFoundException{
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(new File("nonplayer.xml"))));
		while(true){
			try{
				personList.add((Person) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e){
				break;
			}
		}
	}
	
	public void updateAllPersonAttributes(){
		iPerson = this.getPersonList().iterator();
		while(iPerson.hasNext()){
			Person p = iPerson.next();
			p.setAge(this.getDate());
			if(p instanceof Player){
				Player player = (Player) p;
				/*int peakAge = 33;
				if(player.getRightFullbackAbility() == 20 || player.getLeftFullbackAbility() == 20 || player.getCentrebackAbility() == 20)
					peakAge-=2;
				else if(player.getRightMidfieldAbility() == 20 || player.getLeftMidfieldAbility() == 20 || player.getCentreMidfieldAbility() == 20)
					peakAge-=4;
				else if(player.getStrikerAbility() == 20)
					peakAge-=6;
				if(player.getAge() > peakAge){
					//reduce current ability
				}
				else if(player.getCurrentAbility() < player.getPotentialAbility()){
					//increase current ability
				}*/
				player.setMarketValue(this.getDate());
				player.setSaleValue();
				if(player.isInjured()){
					player.setFitness(player.getFitness() - 200);
					player.setFatigue(player.getFatigue() - 200);
				}
				else{
					player.setFitness(player.getFitness() - 100);
					player.setFatigue(player.getFatigue() - 100);
				}
				if(player.getMatchCondition() < 100){
					player.setMatchCondition(player.getMatchCondition() + 2);
				}
			}
			//TODO: update happiness
		}
	}
	
	/* This method returns the current ingame date */
	public Calendar getDate() {
		return date;
	}
	
	public List<Person> getPersonList() {
		return personList;
	}

	public void setPersonList(List<Person> personList) {
		this.personList = personList;
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
	
	public List<Competition> getCompetitionList() {
		return competitionList;
	}
}
