package ie.tippinst.jod.fm.db;

import ie.tippinst.jod.fm.model.Club;
import ie.tippinst.jod.fm.model.Competition;
import ie.tippinst.jod.fm.model.Cup;
import ie.tippinst.jod.fm.model.Injury;
import ie.tippinst.jod.fm.model.League;
import ie.tippinst.jod.fm.model.Match;
import ie.tippinst.jod.fm.model.Message;
import ie.tippinst.jod.fm.model.Nation;
import ie.tippinst.jod.fm.model.NonPlayer;
import ie.tippinst.jod.fm.model.Person;
import ie.tippinst.jod.fm.model.Player;
import ie.tippinst.jod.fm.model.Round;
import ie.tippinst.jod.fm.model.Stadium;

import java.beans.XMLDecoder;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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
	private List<Message> messages = new ArrayList<Message>();;
	private Club userClub;
	private XMLDecoder decoder = null;

	/*
	 * This constructs a new game object with the initial date set at 2 July
	 * 2009
	 */
	public Database() {
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
		setMessages(new ArrayList<Message>());

		try {
			loadStadia();
			loadInjuries();
			loadNations();
			loadCups();
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
		initialiseCups();
		setManagerShortlists();
	}

	public Injury findInjury(int id) {
		Injury i = null;
		iInjury = injuryList.iterator();
		while (iInjury.hasNext()) {
			i = iInjury.next();
			if (i.getId() == id) {
				break;
			}
		}
		return i;
	}

	public Competition findCompetition(String name) {
		Competition c = null;
		iCompetition = competitionList.iterator();
		while (iCompetition.hasNext()) {
			c = iCompetition.next();
			if (c.getName().equals(name)) {
				break;
			}
		}
		return c;
	}
	
	public Competition findCompetition(int id) {
		Competition c = null;
		iCompetition = competitionList.iterator();
		while (iCompetition.hasNext()) {
			c = iCompetition.next();
			if (c.getId() == id) {
				break;
			}
		}
		return c;
	}
	
	public Club findClub(int id) {
		Club c = null;
		iClub = clubList.iterator();
		while (iClub.hasNext()) {
			c = iClub.next();
			if (c.getId() == id) {
				break;
			}
		}
		return c;
	}

	public Club findClub(String name) {
		Club c = null;
		iClub = clubList.iterator();
		while (iClub.hasNext()) {
			c = iClub.next();
			if (c.getName().equals(name)) {
				break;
			}
		}
		return c;
	}

	public Person findPerson(String name) {
		Person p = null;
		iPerson = personList.iterator();
		while (iPerson.hasNext()) {
			p = iPerson.next();
			if ((p.getFirstName() + " " + p.getLastName()).equals(name)) {
				break;
			}
		}
		return p;
	}

	public Nation findNation(String name) {
		Nation n = null;
		iNation = nationList.iterator();
		while (iNation.hasNext()) {
			n = iNation.next();
			if (n.getName().equals(name)) {
				break;
			}
		}
		return n;
	}

	private void setSquadAndStaff() {
		// Set the squads and staff for all clubs in the game
		iClub = clubList.iterator();
		while (iClub.hasNext()) {
			iPerson = personList.iterator();
			Club c = iClub.next();
			List<Player> playerList = new ArrayList<Player>();
			List<NonPlayer> staffList = new ArrayList<NonPlayer>();
			while (iPerson.hasNext()) {
				Person p = iPerson.next();
				if (p.getCurrentClub().getId() == c.getId()
						&& p instanceof Player) {
					playerList.add((Player) p);
				} else if (p.getCurrentClub().getId() == c.getId()
						&& p instanceof NonPlayer) {
					staffList.add((NonPlayer) p);
				}
			}
			List<Player> goalkeepers = new ArrayList<Player>();
			Iterator<Player> i = playerList.iterator();
			while (i.hasNext()) {
				Player p = i.next();
				if (p.getGoalkeepingAbility() == 20)
					goalkeepers.add(p);
			}
			while (goalkeepers.size() < 2) {
				// generate goalkeeper
				Player player = generateGoalkeeper(c);
				goalkeepers.add(player);
				playerList.add(player);
				personList.add(player);
			}
			while (playerList.size() < 16) {
				// generate new outfield player
				Player player = generateOutfieldPlayer(c);
				playerList.add(player);
				personList.add(player);
			}
			c.setSquad(playerList);
			c.setStaff(staffList);
			c.setStatusOfPlayers();
			i = playerList.iterator();
			while (i.hasNext()) {
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
				((Player) p).setFitness(1500);
				((Player) p).setHappinessAtClub(7500);
				((Player) p).setFatigue(1500);
				p.setAge(this.date);
			}
		}
	}

	private void setClubFixtures() {
		iClub = clubList.iterator();
		while (iClub.hasNext()) {
			Club c = iClub.next();
			Match[][] fixtures = null;
			List<Match> clubFixtures = new ArrayList<Match>();
			iCompetition = competitionList.iterator();
			while (iCompetition.hasNext()) {
				Competition comp = iCompetition.next();
				if (comp instanceof League && ((League) comp).getTeams().contains(c) && comp.getId() != 0) {
					//Get league fixtures
					fixtures = ((League) comp).getFixtures();
					for (int i = 0; i < fixtures.length; i++) {
						for (int j = 0; j < fixtures[i].length; j++) {
							if ((fixtures[i][j].getHomeTeam().getId() == c
									.getId())
									|| (fixtures[i][j].getAwayTeam().getId() == c
											.getId())) {
								clubFixtures.add(fixtures[i][j]);
							}
						}
					}
					//Get league playoff fixtures
					if (((League) comp).getPlayoffs() != null) {
						Iterator<Round> i = ((League) comp).getPlayoffs().getRounds().iterator();
						while (i.hasNext()) {
							Round r = i.next();
							Iterator<Match> iMatch = r.getMatches().iterator();
							while (iMatch.hasNext()) {
								Match m = iMatch.next();
								if (m.getHomeTeam().getId() == c.getId()
										|| m.getAwayTeam().getId() == c.getId()) {
									clubFixtures.add(m);
								}
							}
						}
					}
					//Get league fixtures which have been rescheduled
					Iterator<Match> iMatch = ((League) comp).getRescheduledMatches().iterator();
					while(iMatch.hasNext()){
						Match m = iMatch.next();
						if (m.getHomeTeam().getId() == c.getId()
								|| m.getAwayTeam().getId() == c.getId()) {
							clubFixtures.add(m);
						}
					}
				}
				//Get cup fixtures(FA Cup)
				else if(comp instanceof Cup && comp.getId() == 10){
					Iterator<Round> i = ((Cup) comp).getRounds().iterator();
					while (i.hasNext()) {
						Round r = i.next();
						Iterator<Match> iMatch = r.getMatches().iterator();
						while (iMatch.hasNext()) {
							Match m = iMatch.next();
							if (m.getHomeTeam().getId() == c.getId() || m.getAwayTeam().getId() == c.getId()) {
								clubFixtures.add(m);
							}
						}
					}
				}
			}
			Collections.sort(clubFixtures);
			c.setFixtures(clubFixtures);
		}
	}

	private void initialiseClubs() {
		iClub = clubList.iterator();
		while (iClub.hasNext()) {
			Club c = iClub.next();
			iNation = nationList.iterator();
			iStadium = stadiumList.iterator();
			iCompetition = competitionList.iterator();
			while (iStadium.hasNext()) {
				Stadium s = iStadium.next();
				if (c.getHomeGround().getId() == s.getId()) {
					c.setHomeGround(s);
					break;
				}
			}
			while (iNation.hasNext()) {
				Nation n = iNation.next();
				if (c.getNationality().getId() == n.getId()) {
					c.setNationality(n);
					break;
				}
			}
			while (iCompetition.hasNext()) {
				Competition competition = iCompetition.next();
				if (c.getLeague().getId() == competition.getId()) {
					c.setLeague((League) competition);
					break;
				}
			}
			c.setTransferBudget(c.getBankBalance() * 0.9);
			c.setAverageAttendance(c.getReputation() * 8);
			c.setNumberOfSeasonTicketHolders((c.getAverageAttendance() / 5) * 3);
			c.setSeasonTicketPrice(c.getReputation() / 12.0);
			c.setTicketPrice(c.getReputation() / 250.0);
		}
		setSquadAndStaff();
	}
	
	public void initialiseCups() {
		iCompetition = competitionList.iterator();
		while (iCompetition.hasNext()) {
			Competition c = iCompetition.next();
			if(c instanceof Cup){
				Cup cup = (Cup) c;
				if(cup.getId() == 10){
					Calendar cal = null;
					Iterator<Round> iterator = cup.getRounds().iterator();
					while(iterator.hasNext()){
						Round r = iterator.next();
						r.getMatches().clear();
						r.getTeams().clear();
						r.getWinners().clear();
						int index = cup.getRounds().indexOf(r);
						cal = (Calendar) cup.getRounds().get(index).getRoundDate().get(cup.getRounds().get(index).getRoundDate().size() - 1).clone();
						cal.add(Calendar.DATE, -3);
						r.setDrawDate(cal);
					}
					cup.getRounds().get(0).getTeams().addAll(((League) this.findCompetition(3)).getTeams());
					cup.getRounds().get(0).getTeams().addAll(((League) this.findCompetition(4)).getTeams());
					cup.getRounds().get(0).getTeams().addAll(((League) this.findCompetition(5)).getTeams());
					cup.getRounds().get(0).getTeams().addAll(((League) this.findCompetition(0)).getTeams());
					cup.getRounds().get(2).getTeams().addAll(((League) this.findCompetition(1)).getTeams());
					cup.getRounds().get(2).getTeams().addAll(((League) this.findCompetition(2)).getTeams());
				}
			}
		}
	}

	public void initialiseLeagues() {
		iCompetition = competitionList.iterator();
		while (iCompetition.hasNext()) {
			Competition c = iCompetition.next();
			Iterator<Competition> i = competitionList.iterator();
			while (i.hasNext()) {
				Competition comp = i.next();
				if (c instanceof League && ((League) c).getPromotedTo() != null && ((League) c).getPromotedTo().getId() == comp.getId()) {
					((League) c).setPromotedTo((League) comp);
					break;
				}
			}
			i = competitionList.iterator();
			while (i.hasNext()) {
				Competition comp = i.next();
				if (c instanceof League && ((League) c).getRelegatedTo() != null && ((League) c).getRelegatedTo().getId() == comp.getId()) {
					((League) c).setRelegatedTo((League) comp);
					break;
				}
			}
			i = competitionList.iterator();
			while (i.hasNext()) {
				Competition comp = i.next();
				//set up league playoffs
				if (c instanceof League && ((League) c).getPlayoffs() != null && ((League) c).getPlayoffs().getId() == comp.getId()) {
					((League) c).setPlayoffs((Cup) comp);
					Calendar cal = (Calendar) ((League) c).getMatchDates().get(((League) c).getMatchDates().size() - 1).clone();
					cal.add(Calendar.DATE, 1);
					((League) c).getPlayoffs().getRounds().get(0).setDrawDate(cal);
					Iterator<Round> iterator = ((League) c).getPlayoffs().getRounds().iterator();
					while(iterator.hasNext()){
						Round r = iterator.next();
						r.getMatches().clear();
						r.getTeams().clear();
						r.getWinners().clear();
						if (r.getRoundNumber() > 1) {
							int index = ((League) c).getPlayoffs().getRounds().indexOf(r);
							cal = (Calendar) ((League) c).getPlayoffs().getRounds().get(index - 1).getRoundDate().get(((League) c).getPlayoffs().getRounds().get(index - 1).getRoundDate().size() - 1).clone();
							cal.add(Calendar.DATE, 1);
							r.setDrawDate(cal);
						}
					}
				}
			}
			iClub = this.clubList.iterator();
			List<Club> clubList = new ArrayList<Club>();
			while (iClub.hasNext()) {
				Club club = iClub.next();
				if (club.getLeague().getId() == c.getId() && c instanceof League) {
					clubList.add(club);
				}
			}
			if(c instanceof League)
				((League) c).setTeams(clubList);
		}
		
		// Assign the initial table for all leagues in the game
		iCompetition = competitionList.iterator();
		while (iCompetition.hasNext()) {
			Competition c = iCompetition.next();
			if (c.getId() != 0 && c instanceof League) {
				((League) c).setTable();
				((League) c).setFixtures(((League) c).generateFixtures());
			}
		}
		setClubFixtures();
	}

	private void loadStadia() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("stadium.xml"))));
		while (true) {
			try {
				stadiumList.add((Stadium) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	private void loadInjuries() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("injury.xml"))));
		while (true) {
			try {
				injuryList.add((Injury) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	private void loadNations() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("nation.xml"))));
		while (true) {
			try {
				nationList.add((Nation) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	private void loadLeagues() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("league.xml"))));
		while (true) {
			try {
				competitionList.add((Competition) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}
	
	private void loadCups() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("cups.xml"))));
		while (true) {
			try {
				competitionList.add((Competition) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	private void loadClubs() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("club.xml"))));
		while (true) {
			try {
				clubList.add((Club) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	private void loadPlayers() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("player.xml"))));
		while (true) {
			try {
				personList.add((Person) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	private void loadNonPlayers() throws FileNotFoundException {
		decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(
				new File("nonplayer.xml"))));
		while (true) {
			try {
				personList.add((Person) decoder.readObject());
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	public void updateAllPersonAttributes() {
		iPerson = this.getPersonList().iterator();
		while (iPerson.hasNext()) {
			Person p = iPerson.next();
			//update age
			p.setAge(this.getDate());
			if (p instanceof Player) {
				Player player = (Player) p;
				 //update current ability
				/*int peakAge = 33; if(player.getRightFullbackAbility() == 20
				 || player.getLeftFullbackAbility() == 20 ||
				 player.getCentrebackAbility() == 20) peakAge-=2; else
				 if(player.getRightMidfieldAbility() == 20 ||
				 player.getLeftMidfieldAbility() == 20 ||
				 player.getCentreMidfieldAbility() == 20) peakAge-=4; else
				 if(player.getStrikerAbility() == 20) peakAge-=6;
				 if(player.getAge() > peakAge){ //reduce current ability }
				 else if(player.getCurrentAbility() <
				 player.getPotentialAbility()){ //increase current ability }*/
				
				//update market value
				player.setMarketValue(this.getDate());

				//update sale value
				player.setSaleValue();
				
				//update fitness and fatigue
				double increase = (((10000.0 - player.getFatigue()) + player.getFitness()) / 4000.0);
				player.setMatchCondition(player.getMatchCondition() + increase);
				if (player.isInjured()) {
					player.setFitness(player.getFitness() - 300);
					player.setFatigue(player.getFatigue() - 100);
				} else {
					player.setFitness(player.getFitness() - 150);
					player.setFatigue(player.getFatigue() - 50);
				}
				
				// if player is injured reduce their time out by 1 day
				if (player.isInjured()) {
					player.setDaysUnavailable(player.getDaysUnavailable() - 1);
					if (player.getDaysUnavailable() == 0) {
						if (player.getCurrentClub().getId() == this.getUserClub().getId()) {
							this.getMessages().add(new Message((Calendar) this.getDate().clone(),
													player.getFirstName() + (player.getLastName().equals("") ? "" : " ") + player.getLastName()
															+ " Returns From Injury",
													player.getFirstName() + (player.getLastName().equals("") ? "" : " ") + player.getLastName()
															+ " has returned to training, after fully recovering from "	+ player.getInjury().getSentenceName()
															+ "!"));
						}
						player.setInjury(null);
					}
				}
				// if player is not injured then generate a random number to see whether to injure them
				else {
					// random number between 1 and 500
					int injuryChance = (int) (Math.random() * 500);
					if (injuryChance == 1) {
						// injure player;
						Injury injury = this.findInjury((int) (Math.random() * (this.getInjuryList().size())));
						player.setInjury(injury);
						player.setDaysUnavailable(injury.getMinDaysOut() + ((int) (Math.random() * injury.getExtraDaysOut())) + 1);
						if (player.getCurrentClub().getId() == this.getUserClub().getId()) {
							String timeOut = "";
							if (player.getDaysUnavailable() < 14) {
								timeOut = player.getDaysUnavailable() + (player.getDaysUnavailable() == 1 ? " day" : " days");
							}
							else if (player.getDaysUnavailable() < 60) {
								timeOut = player.getDaysUnavailable() / 7 + " weeks";
							}
							else {
								timeOut = player.getDaysUnavailable() / 30 + " months";
							}
							this.getMessages().add(new Message((Calendar) this.getDate().clone(),
													player.getFirstName() + (player.getLastName().equals("") ? "" : " ") + player.getLastName()	+ " Injured",
													player.getFirstName() + (player.getLastName().equals("") ? "" : " ") + player.getLastName()
															+ " has been ruled out for " + timeOut + ", after suffering from " + player.getInjury().getSentenceName()
															+ "!"));
						}
					}
				}
			}
			// TODO: update happiness
		}
		//update manager shortlists
		this.setManagerShortlists();
	}

	public void updateAllClubAttributes() {
		iClub = clubList.iterator();
		while (iClub.hasNext()) {
			Club c = iClub.next();
			
			//update club finances
			c.updateFinances(this.getDate());
			
			//set status of players if club is not user's club
			if (c.getId() != userClub.getId()) {
				c.setStatusOfPlayers();
			}
		}
		
		//update club fixtures
		this.setClubFixtures();
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

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

	public List<Message> getMessages() {
		Collections.sort(this.messages);
		Collections.reverse(this.messages);
		return messages;
	}

	public Club getUserClub() {
		return userClub;
	}

	public void setUserClub(Club userClub) {
		this.userClub = userClub;
	}

	public int getLastPersonIdUsed() {
		int id = 0;
		iPerson = personList.iterator();
		while (iPerson.hasNext()) {
			Person p = iPerson.next();
			if (p.getId() > id)
				id = p.getId();
		}
		return id;
	}
	
	public Player generatePlayer(Club c){
		Player p = new Player();
		
		//set id as biggest current id plus one
		p.setId(getLastPersonIdUsed() + 1);
		
		// generate random dob between 1/1/1978 and 31/12/1983
		int year = ((int) (Math.random() * 6)) + 1978;
		int month = (int) (Math.random() * 12);
		int numberOfDays = 31;
		if (month == 1) {
			numberOfDays = 28;
		} else if (month == 3 || month == 5 || month == 8 || month == 10) {
			numberOfDays = 30;
		}
		int date = ((int) (Math.random() * numberOfDays)) + 1;
		
		p.setDob(new GregorianCalendar(year, month, date));
		
		//set the club as the one passed in
		p.setCurrentClub(c);
		
		//generate a random contract expiry date between 1 and 3 years
		p.setContractExpiry(new GregorianCalendar((((int) (Math.random() * 3)) + 2010), 5, 30));
		
		//set nationality as their clubs nationality
		p.setNationality(c.getNationality());
		
		//pick random first name from list
		List<String> firstNames = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("first_names.dat")));
			while(br.ready()){
				firstNames.add(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setFirstName(firstNames.get((int) (Math.random() * firstNames.size())));
		
		//pick random surname from list
		List<String> surnames = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File("surnames.dat")));
			while(br.ready()){
				surnames.add(br.readLine());
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		p.setLastName(surnames.get((int) (Math.random() * surnames.size())));
		
		//set reputation based on clubs reputation
		p.setReputation(c.getReputation() / 50);
		
		//set wages based on reputation
		p.setWages(p.getReputation() * 50.0);
		
		return p;
	}

	public Player generateOutfieldPlayer(Club c) {
		Player p = this.generatePlayer(c);
		
		//set potential ability based on reputation
		p.setPotentialAbility(p.getReputation() + 30);
		
		//randomly set one foot ability to 20 with right foot more likely and set the other a random value between 1 and 15
		p.setLeftFootAbility(((int) (Math.random() * 15)) + 1);
		p.setRightFootAbility(((int) (Math.random() * 15)) + 1);
		if (((int) (Math.random() * 3)) == 0) {
			p.setLeftFootAbility(20);
		} else {
			p.setRightFootAbility(20);
		}
		
		//set all positions to 1 and then randomly set one of them to 20
		int positionDeterminant = (int) (Math.random() * 100);
		p.setGoalkeepingAbility(1);
		p.setRightFullbackAbility(1);
		p.setLeftFullbackAbility(1);
		p.setCentrebackAbility(1);
		p.setRightMidfieldAbility(1);
		p.setLeftMidfieldAbility(1);
		p.setCentreMidfieldAbility(1);
		p.setStrikerAbility(1);
		if (positionDeterminant < 34) {
			if (positionDeterminant < 11) {
				p.setRightFullbackAbility(20);
			} else if (positionDeterminant < 22) {
				p.setLeftFullbackAbility(20);
			} else {
				p.setCentrebackAbility(20);
			}
		} else if (positionDeterminant < 78) {
			if (positionDeterminant < 49) {
				p.setRightMidfieldAbility(20);
			} else if (positionDeterminant < 63) {
				p.setLeftMidfieldAbility(20);
			} else {
				p.setCentreMidfieldAbility(20);
			}
		} else {
			p.setStrikerAbility(20);
		}
		
		//set max current ability based one league and club reputation
		int range = ((c.getLeague().getReputation() * 10) - 60) - ((10000 - c.getReputation()) / 1000);
		
		//set current ability a random value between this range and 60 less than the range
		int ca = ((int) (Math.random() * 60)) + range + 1;
		
		//randomly set attributes, with passing, pace, strength and stamina based on current ability value
		p.setHandling(((int) (Math.random() * 5)) + 1);
		p.setReflexes(((int) (Math.random() * 5)) + 1);
		p.setCommandOfArea(((int) (Math.random() * 5)) + 1);
		p.setDecisions(((int) (Math.random() * 7)) + 13);
		p.setFinishing(((int) (Math.random() * 10)) + 3);
		p.setHeading(((int) (Math.random() * 3)) + 8);
		p.setMarking(((int) (Math.random() * 6)) + 1);
		p.setPassing(ca / 10);
		p.setCrossing(((int) (Math.random() * 8)) + 2);
		p.setDribbling(((int) (Math.random() * 8)) + 2);
		p.setTackling(((int) (Math.random() * 8)) + 2);
		p.setLongShots(((int) (Math.random() * 8)) + 2);
		p.setPenaltyTaking(((int) (Math.random() * 15)) + 2);
		p.setInfluence(((int) (Math.random() * 15)) + 2);
		int variance = ((int) (Math.random() * 11)) - 5;
		p.setPace((ca / 10) - variance);
		p.setStrength((ca / 10) + variance);
		p.setStamina(ca / 10);
		p.setLoyalty(((int) (Math.random() * 17)) + 4);
		p.setAmbition(((int) (Math.random() * 17)) + 4);
		
		//based on position set the attributes specifically associated with those positions to more realistic values		
		if (p.getStrikerAbility() == 20) {
			variance = ((int) (Math.random() * 11)) - 5;
			p.setHeading((ca / 10) + variance);
			p.setDribbling((ca / 10) - variance);
			variance = ((int) (Math.random() * 7)) - 3;
			p.setFinishing((ca / 10) + variance);
			p.setLongShots((ca / 10) - variance);
		} else if (p.getLeftMidfieldAbility() == 20
				|| p.getRightMidfieldAbility() == 20) {
			variance = ((int) (Math.random() * 5)) - 2;
			p.setCrossing((ca / 10) + variance);
			p.setDribbling((ca / 10) - variance);
		} else if (p.getCentreMidfieldAbility() == 20) {
			variance = ((int) (Math.random() * 11)) - 5;
			p.setTackling(ca / 10);
			p.setLongShots(ca / 10);
		} else if (p.getLeftFullbackAbility() == 20
				|| p.getRightFullbackAbility() == 20) {
			variance = ((int) (Math.random() * 9)) - 4;
			p.setDribbling((ca / 10) + variance);
			p.setHeading((ca / 10) - variance);
			variance = ((int) (Math.random() * 7)) - 3;
			p.setCrossing((ca / 10) + variance);
			p.setDribbling(p.getDribbling() - variance);
			variance = ((int) (Math.random() * 5)) - 2;
			p.setMarking((ca / 10) + variance);
			p.setTackling((ca / 10) - variance);
		} else {
			variance = ((int) (Math.random() * 5)) - 2;
			p.setHeading((ca / 10) - variance);
			p.setMarking((ca / 10) + variance);
			variance = ((int) (Math.random() * 5)) - 2;
			p.setMarking(p.getMarking() + variance);
			p.setTackling((ca / 10) - variance);
		}
		
		//set the position of the player
		p.setPosition();
		
		//set the current ability
		p.setCurrentAbility();
		
		//make sure potential ability is not less than the current ability
		if (p.getCurrentAbility() > p.getPotentialAbility()) {
			p.setPotentialAbility(p.getCurrentAbility());
		}
		
		//set various other values associated with the player with initial values
		p.setMatchCondition(70);
		p.setMorale(8000);
		p.setFitness(1500);
		p.setHappinessAtClub(7500);
		p.setFatigue(1500);
		p.setAge(this.date);
		return p;
	}

	public Player generateGoalkeeper(Club c) {
		Player p = this.generatePlayer(c);
		
		//set potential ability based on reputation
		p.setPotentialAbility(p.getReputation() + 30);
		
		//randomly set one foot ability to 20 with right foot more likely and set the other a random value between 1 and 15
		p.setLeftFootAbility(((int) (Math.random() * 15)) + 1);
		p.setRightFootAbility(((int) (Math.random() * 15)) + 1);
		if (((int) (Math.random() * 3)) == 0) {
			p.setLeftFootAbility(20);
		} else {
			p.setRightFootAbility(20);
		}
		
		//set goalkeeper to 20 all other positions to 1
		p.setGoalkeepingAbility(20);
		p.setRightFullbackAbility(1);
		p.setLeftFullbackAbility(1);
		p.setCentrebackAbility(1);
		p.setRightMidfieldAbility(1);
		p.setLeftMidfieldAbility(1);
		p.setCentreMidfieldAbility(1);
		p.setStrikerAbility(1);
		
		//set max current ability based on league and club reputation
		int range = ((c.getLeague().getReputation() * 10) - 60) - ((10000 - c.getReputation()) / 1000);
		
		//set current ability a random value between ca and 60 less
		int ca = ((int) (Math.random() * 60)) + range + 1;
		
		//generate realistic random values for each attribute
		int variance = ((int) (Math.random() * 7)) - 3;
		p.setHandling((ca / 10) + variance);
		p.setReflexes((ca / 10) - variance);
		variance = ((int) (Math.random() * 7)) - 3;
		p.setCommandOfArea((ca / 10) + variance);
		p.setDecisions((ca / 10) - variance);
		p.setFinishing(((int) (Math.random() * 6)) + 1);
		p.setHeading(((int) (Math.random() * 6)) + 1);
		p.setMarking(((int) (Math.random() * 6)) + 1);
		p.setPassing(((int) (Math.random() * 6)) + 1);
		p.setCrossing(((int) (Math.random() * 6)) + 1);
		p.setDribbling(((int) (Math.random() * 6)) + 1);
		p.setTackling(((int) (Math.random() * 6)) + 1);
		p.setLongShots(((int) (Math.random() * 6)) + 1);
		p.setPenaltyTaking(((int) (Math.random() * 6)) + 1);
		p.setInfluence(((int) (Math.random() * 16)) + 5);
		p.setPace(((int) (Math.random() * 10)) + 6);
		p.setStrength(((int) (Math.random() * 8)) + 9);
		p.setStamina(((int) (Math.random() * 8)) + 8);
		p.setLoyalty(((int) (Math.random() * 17)) + 4);
		p.setAmbition(((int) (Math.random() * 17)) + 4);
		
		//set various other values for player
		p.setPosition();
		p.setCurrentAbility();
		p.setMatchCondition(70);
		p.setMorale(8000);
		p.setFitness(1500);
		p.setHappinessAtClub(7500);
		p.setFatigue(1500);
		p.setAge(this.date);
		return p;
	}

	private void setManagerShortlists() {
		iPerson = personList.iterator();
		while (iPerson.hasNext()) {
			Person p = iPerson.next();
			if (p instanceof NonPlayer
					&& ((NonPlayer) p).getManagerRole() == 20
					&& p.getCurrentClub() != null && p.getId() != 0) {
				
				//Get the amount of players in each position in the squad
				List<Player> potentialSignings = new ArrayList<Player>();
				int goalkeepers = getNumberOfPlayers(0, p.getCurrentClub());
				int rightBacks = getNumberOfPlayers(1, p.getCurrentClub());
				int leftBacks = getNumberOfPlayers(2, p.getCurrentClub());
				int centreBacks = getNumberOfPlayers(3, p.getCurrentClub());
				int rightMidfielders = getNumberOfPlayers(4, p.getCurrentClub());
				int leftMidfielders = getNumberOfPlayers(5, p.getCurrentClub());
				int centreMidfielders = getNumberOfPlayers(6, p.getCurrentClub());
				int strikers = getNumberOfPlayers(7, p.getCurrentClub());
				
				//if club is lacking in any position search for possible players to buy
				if (goalkeepers < 1) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(0, minAbility, p.getCurrentClub()));
				}
				else if (goalkeepers < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(0, minAbility, p.getCurrentClub()));
				}
				else if (goalkeepers < 3) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (2 * 15);
					potentialSignings.addAll(getPotentialSignings(0, minAbility, p.getCurrentClub()));
				}
				if (rightBacks < 1) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(1, minAbility, p.getCurrentClub()));
				}
				else if (rightBacks < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(1, minAbility, p.getCurrentClub()));
				}
				if (leftBacks < 1) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(2, minAbility, p.getCurrentClub()));
				} 
				else if (leftBacks < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(2, minAbility, p.getCurrentClub()));
				}
				if (centreBacks < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(3, minAbility, p.getCurrentClub()));
				}
				else if (centreBacks < 4) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(3, minAbility, p.getCurrentClub()));
				}
				if (rightMidfielders < 1) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(4, minAbility, p.getCurrentClub()));
				}
				else if (rightMidfielders < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(4, minAbility, p.getCurrentClub()));
				}
				if (leftMidfielders < 1) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(5, minAbility, p.getCurrentClub()));
				}
				else if (leftMidfielders < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(5, minAbility, p.getCurrentClub()));
				}
				if (centreMidfielders < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(6, minAbility, p.getCurrentClub()));
				}
				else if (centreMidfielders < 4) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(6, minAbility, p.getCurrentClub()));
				}
				if (strikers < 2) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (0 * 15);
					potentialSignings.addAll(getPotentialSignings(7, minAbility, p.getCurrentClub()));
				}
				else if (strikers < 4) {
					int minAbility = (((p.getCurrentClub().getReputation() / 100)
							+ (p.getCurrentClub().getLeague().getReputation() * 10)) * (18 / 30))
							- (1 * 15);
					potentialSignings.addAll(getPotentialSignings(7, minAbility, p.getCurrentClub()));
				}
				//if club is not lacking in any position, then search for better players to improve the squad
				if (potentialSignings.size() == 0) {
					int requiredAbility = 0;
					for (int i = 0; i < 8; i++) {
						requiredAbility = this.getMaxAbility(i, p.getCurrentClub());
						potentialSignings.addAll(this.getPotentialSignings(i, requiredAbility, p.getCurrentClub()));
					}
				}
				removeDuplicates(potentialSignings);
				Iterator<Player> i = ((NonPlayer) p).getShortlist().iterator();
				while(i.hasNext()){
					i.next().getInterested().remove(p.getCurrentClub());
				}
				((NonPlayer) p).getShortlist().clear();
				((NonPlayer) p).getShortlist().addAll(potentialSignings);
				i = ((NonPlayer) p).getShortlist().iterator();
				while(i.hasNext()){
					i.next().getInterested().add(p.getCurrentClub());
				}
			}
		}
	}

	private int getMaxAbility(int position, Club club) {
		int currentAbility = 0;
		int positionalAbility = 1;
		Iterator<Player> iSquad = club.getSquad().iterator();
		while (iSquad.hasNext()) {
			Player p = iSquad.next();
			switch (position) {
			case 0:
				positionalAbility = p.getGoalkeepingAbility();
				break;
			case 1:
				positionalAbility = p.getRightFullbackAbility();
				break;
			case 2:
				positionalAbility = p.getLeftFullbackAbility();
				break;
			case 3:
				positionalAbility = p.getCentrebackAbility();
				break;
			case 4:
				positionalAbility = p.getRightMidfieldAbility();
				break;
			case 5:
				positionalAbility = p.getLeftMidfieldAbility();
				break;
			case 6:
				positionalAbility = p.getCentreMidfieldAbility();
				break;
			case 7:
				positionalAbility = p.getStrikerAbility();
				break;
			}
			if (positionalAbility >= 15) {
				if (p.getCurrentAbility() > currentAbility) {
					currentAbility = p.getCurrentAbility();
				}
			}
		}
		return currentAbility;
	}

	private void removeDuplicates(List<Player> players) {
		List<Player> players2 = new ArrayList<Player>();
		Iterator<Player> i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (!(players2.contains(p))) {
				players2.add(p);
			}
		}
		players.clear();
		players.addAll(players2);
	}

	private int getNumberOfPlayers(int position, Club club) {
		int players = 0;
		int ability = 1;
		Iterator<Player> iSquad = club.getSquad().iterator();
		while (iSquad.hasNext()) {
			Player p = iSquad.next();
			switch (position) {
			case 0:
				ability = p.getGoalkeepingAbility();
				break;
			case 1:
				ability = p.getRightFullbackAbility();
				break;
			case 2:
				ability = p.getLeftFullbackAbility();
				break;
			case 3:
				ability = p.getCentrebackAbility();
				break;
			case 4:
				ability = p.getRightMidfieldAbility();
				break;
			case 5:
				ability = p.getLeftMidfieldAbility();
				break;
			case 6:
				ability = p.getCentreMidfieldAbility();
				break;
			case 7:
				ability = p.getStrikerAbility();
				break;
			}
			if (ability >= 15) {
				players++;
			}
		}
		return players;
	}

	public List<Player> getPotentialSignings(int position, int minAbility,
			Club club) {
		int positionalAbility = 1;
		List<Player> potentialSignings = new ArrayList<Player>();
		Iterator<Person> iPerson = personList.iterator();
		while (iPerson.hasNext()) {
			Person person = iPerson.next();
			if (person instanceof Player
					&& person.getCurrentClub().getId() != club.getId()) {
				Player player = (Player) person;
				switch (position) {
				case 0:
					positionalAbility = player.getGoalkeepingAbility();
					break;
				case 1:
					positionalAbility = player.getRightFullbackAbility();
					break;
				case 2:
					positionalAbility = player.getLeftFullbackAbility();
					break;
				case 3:
					positionalAbility = player.getCentrebackAbility();
					break;
				case 4:
					positionalAbility = player.getRightMidfieldAbility();
					break;
				case 5:
					positionalAbility = player.getLeftMidfieldAbility();
					break;
				case 6:
					positionalAbility = player.getCentreMidfieldAbility();
					break;
				case 7:
					positionalAbility = player.getStrikerAbility();
					break;
				default:
					System.out.println("Error");
				}
				if ((positionalAbility >= 15)
						&& (player.getCurrentAbility() >= minAbility)
						&& (player.getSaleValue() <= club.getTransferBudget())
						&& club.offerContract(player, 50000, new GregorianCalendar((this.getDate().get(Calendar.YEAR) + 3), 5, 30), 0)) {
					potentialSignings.add(player);
				}
			}
		}
		return potentialSignings;
	}
}
