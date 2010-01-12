package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Club implements Serializable {

	private static final long serialVersionUID = 8870087600952858149L;
	private int id;
	private String name;
	private List<Player> squad = new ArrayList<Player>();
	private List<NonPlayer> staff;
	private int reputation;
	private List<Club> rivals;
	private double bankBalance;
	private Stadium homeGround;
	private Nation nationality;
	private League league;
	private List<Match> fixtures;
	private List<Player> selectedTeam = new ArrayList<Player>();
	private double transferBudget;
	private int numberOfSeasonTicketHolders;
	private double seasonTicketPrice;
	private int averageAttendance;
	private double ticketPrice;

	public Club() {
		super();
	}

	public Club(int id, String name, int reputation, double bankBalance,
			Stadium homeGround, Nation nationality, League league) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = reputation;
		this.bankBalance = bankBalance;
		this.homeGround = homeGround;
		this.nationality = nationality;
		this.setLeague(league);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Club [bankBalance=" + bankBalance + ", homeGround="
				+ homeGround + ", id=" + id + ", name=" + name
				+ ", nationality=" + nationality + ", reputation=" + reputation
				+ "]";
	}

	public void setStaff(List<NonPlayer> staff) {
		this.staff = staff;
	}

	public List<NonPlayer> getStaff() {
		return staff;
	}

	public List<Player> getSquad() {
		return squad;
	}

	public void setSquad(List<Player> squad) {
		this.squad = squad;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public List<Club> getRivals() {
		return rivals;
	}

	public void setRivals(List<Club> rivals) {
		this.rivals = rivals;
	}

	public double getBankBalance() {
		return bankBalance;
	}

	public void setBankBalance(double bankBalance) {
		this.bankBalance = bankBalance;
	}

	public Stadium getHomeGround() {
		return homeGround;
	}

	public void setHomeGround(Stadium homeGround) {
		this.homeGround = homeGround;
	}

	public Nation getNationality() {
		return nationality;
	}

	public void setNationality(Nation nationality) {
		this.nationality = nationality;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getClubInformation() {
		List<String> list = new ArrayList<String>();
		list.add(this.getNationality().getName());
		int reputation = this.getReputation();
		if (reputation >= 9500) {
			list.add("Worldwide");
		} else if (reputation >= 7500) {
			list.add("Continental");
		} else if (reputation >= 4500) {
			list.add("National");
		} else if (reputation >= 1500) {
			list.add("Regional");
		} else {
			list.add("Local");
		}
		double finances = this.getBankBalance();
		if (finances >= 80000000) {
			list.add("Very Rich");
		} else if (finances >= 40000000) {
			list.add("Rich");
		} else if (finances >= 20000000) {
			list.add("Secure");
		} else if (finances >= -5000000) {
			list.add("Okay");
		} else if (finances >= -20000000) {
			list.add("Insecure");
		} else {
			list.add("In Debt");
		}
		list.add(this.getLeague().getName());
		list.add(this.getHomeGround().getName());
		list.add(this.getHomeGround().getCapacity() + "");
		return list;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public League getLeague() {
		return league;
	}

	public void setFixtures(List<Match> fixtures) {
		this.fixtures = fixtures;
	}

	public List<Match> getFixtures() {
		return fixtures;
	}

	public boolean makeOffer(Player p, double d) {
		if (p.getSaleValue() > d) {
			return false;
		}
		return true;
	}

	public boolean offerContract(Player p, int wages, Calendar c, int status) {
		// TODO: Check if player is happy with terms and if yes return true else
		// return false
		// check club reputation, wages offered, if club is a rival, contract
		// length, squad status, if player has recently moved
		// if (p.getReputation() * 50 > this.getReputation())
		if ((p.getStatusAsString().equalsIgnoreCase("indispensable") || p
				.getStatusAsString().equalsIgnoreCase("important"))
				&& ((p.getCurrentClub().getReputation() - this.getReputation()) > 200) || ((p.getCurrentClub().getReputation() - this.getReputation()) > 2000))
			return false;
		return true;
	}

	public void setStatusOfPlayers() {
		List<Player> list = new ArrayList<Player>(this.getSquad());
		// Collections.copy(list, this.getSquad());
		if (list.size() >= 16) {
			Iterator<Player> i = list.iterator();
			while (i.hasNext()) {
				i.next().setStatus(5);
			}
			List<Integer> firstTeamAbilities = new ArrayList<Integer>();
			List<Integer> squadAbilities = new ArrayList<Integer>();
			this.setSelectedTeam(this.getFirstTeamPlayers());
			i = this.getSelectedTeam().iterator();
			// System.out.println(this.getSelectedTeam().size());
			while (i.hasNext()) {
				Player p = i.next();
				p.setStatus(1);
				firstTeamAbilities.add(p.getCurrentAbility());
				list.remove(p);
			}
			// System.out.println(abilities.size());
			// System.out.println(Collections.max(abilities).intValue());
			int maxAbility = Collections.max(firstTeamAbilities).intValue();
			firstTeamAbilities.remove(new Integer(maxAbility));
			maxAbility = Collections.max(firstTeamAbilities).intValue();
			// System.out.println(maxAbility);
			firstTeamAbilities.remove(new Integer(maxAbility));
			maxAbility = Collections.max(firstTeamAbilities).intValue();
			i = this.getSelectedTeam().iterator();
			while (i.hasNext()) {
				Player p = i.next();
				if (p.getCurrentAbility() >= maxAbility)
					p.setStatus(0);
			}
			List<Player> squadPlayers = this.getBestTeam(list);
			i = squadPlayers.iterator();
			while (i.hasNext()) {
				Player p = i.next();
				p.setStatus(2);
				squadAbilities.add(p.getCurrentAbility());
				list.remove(p);
			}

			i = list.iterator();
			int minPotentialFirstTeamAbility = Collections.min(
					firstTeamAbilities).intValue();
			int minPotentialSquadAbility = Collections.min(squadAbilities)
					.intValue();
			List<Integer> goalkeeperAbilities = new ArrayList<Integer>();
			while (i.hasNext()) {
				Player p = i.next();
				if (p.getGoalkeepingAbility() >= 15) {
					goalkeeperAbilities.add(p.getCurrentAbility());
				} else if (p.getPotentialAbility() >= minPotentialFirstTeamAbility
						&& p.getAge() < 24) {
					p.setStatus(3);
				} else if (p.getPotentialAbility() >= minPotentialSquadAbility
						&& p.getAge() < 24) {
					p.setStatus(4);
				}
			}
			if (goalkeeperAbilities.size() > 0) {
				int maxGoalkeeperSquadAbility = Collections.max(
						goalkeeperAbilities).intValue();
				i = list.iterator();
				while (i.hasNext()) {
					Player p = i.next();
					if (p.getCurrentAbility() >= maxGoalkeeperSquadAbility) {
						p.setStatus(2);
					}
				}
			}
		}

		else if (list.size() > 0) {
			Iterator<Player> i = list.iterator();
			while (i.hasNext()) {
				i.next().setStatus(1);
			}
		}

		this.getSelectedTeam().removeAll(this.getSelectedTeam());

		/*
		 * double[] abilityThresholds = new double[3]; abilityThresholds[0] =
		 * ((195 / 20) * this.getLeague().getReputation()) - ((10000 -
		 * this.getReputation()) / 100.0); abilityThresholds[1] = ((178 / 20) *
		 * this.getLeague().getReputation()) - ((10000 - this.getReputation()) /
		 * 100.0); abilityThresholds[2] = ((155 / 20) *
		 * this.getLeague().getReputation()) - ((10000 - this.getReputation()) /
		 * 100.0); while(i.hasNext()){ Player p = i.next();
		 * if(p.getCurrentAbility() >= abilityThresholds[0]){ p.setStatus(0); }
		 * else if(p.getCurrentAbility() >= abilityThresholds[1]){
		 * p.setStatus(1); } else if(p.getCurrentAbility() >=
		 * abilityThresholds[2]){ p.setStatus(2); } else
		 * if(p.getPotentialAbility() >= abilityThresholds[1] && p.getAge() <=
		 * 23){ p.setStatus(3); } else if(p.getPotentialAbility() >=
		 * abilityThresholds[2] && p.getAge() <= 23){ p.setStatus(4); } else{
		 * p.setStatus(5); } }
		 */
	}

	public void setSelectedTeam(List<Player> selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public List<Player> getSelectedTeam() {
		return selectedTeam;
	}

	public List<Player> getFirstTeamPlayers() {
		return getBestTeam(this.getSquad());
	}

	public List<Player> getAvailablePlayers() {
		List<Player> availablePlayers = new ArrayList<Player>();
		Iterator<Player> i = this.getSquad().iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getDaysUnavailable() == 0 && p.getMatchCondition() >= 90) {
				availablePlayers.add(p);
			}
		}
		if (availablePlayers.size() < 11) {
			i = this.getSquad().iterator();
			while (i.hasNext()) {
				Player p = i.next();
				if (p.getDaysUnavailable() == 0 && p.getMatchCondition() >= 70
						&& p.getMatchCondition() < 90) {
					availablePlayers.add(p);
				}
			}
		}
		return availablePlayers;
	}

	public List<Player> getBestTeam(List<Player> players) {
		List<Player> team = new ArrayList<Player>();
		Iterator<Player> i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getGoalkeepingAbility() >= 15) {
				if (team.size() <= 0) {
					if (!(team.contains(p)))
						team.add(p);
				} else if (team.get(0).getCurrentAbility()
						+ (team.get(0).getGoalkeepingAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getGoalkeepingAbility() * 5)) {
					team.remove(0);
					team.add(0, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getRightFullbackAbility() >= 15) {
				if (team.size() <= 1) {
					if (!(team.contains(p)))
						team.add(p);
					// System.out.println("1: " + p.getFirstName() + " " +
					// p.getLastName());
				} else if ((team.get(1).getCurrentAbility()
						+ (team.get(1).getRightFullbackAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getRightFullbackAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(1);
					team.add(1, p);
					// System.out.println("2: " + p.getFirstName() + " " +
					// p.getLastName());
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getLeftFullbackAbility() >= 15) {
				if (team.size() <= 2) {
					if (!(team.contains(p)))
						team.add(p);
					// System.out.println("1: " + p.getFirstName() + " " +
					// p.getLastName());
				} else if ((team.get(2).getCurrentAbility()
						+ (team.get(2).getLeftFullbackAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getLeftFullbackAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(2);
					team.add(2, p);
					// System.out.println("2: " + p.getFirstName() + " " +
					// p.getLastName());
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getCentrebackAbility() >= 15) {
				if (team.size() <= 3) {
					if (!(team.contains(p)))
						team.add(p);
					// System.out.println("1: " + p.getFirstName() + " " +
					// p.getLastName());
				} else if ((team.get(3).getCurrentAbility()
						+ (team.get(3).getCentrebackAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getCentrebackAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(3);
					team.add(3, p);
					// System.out.println("2: " + p.getFirstName() + " " +
					// p.getLastName());
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getCentrebackAbility() >= 15) {
				if (team.size() <= 4) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(4).getCurrentAbility()
						+ (team.get(4).getCentrebackAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getCentrebackAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(4);
					team.add(4, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getRightMidfieldAbility() >= 15) {
				if (team.size() <= 5) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(5).getCurrentAbility()
						+ (team.get(5).getRightMidfieldAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getRightMidfieldAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(5);
					team.add(5, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getLeftMidfieldAbility() >= 15) {
				if (team.size() <= 6) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(6).getCurrentAbility()
						+ (team.get(6).getLeftMidfieldAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getLeftMidfieldAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(6);
					team.add(6, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getCentreMidfieldAbility() >= 15) {
				if (team.size() <= 7) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(7).getCurrentAbility()
						+ (team.get(7).getCentreMidfieldAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getCentreMidfieldAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(7);
					team.add(7, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getCentreMidfieldAbility() >= 15) {
				if (team.size() <= 8) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(8).getCurrentAbility()
						+ (team.get(8).getCentreMidfieldAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getCentreMidfieldAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(8);
					team.add(8, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getStrikerAbility() >= 15) {
				if (team.size() <= 9) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(9).getCurrentAbility()
						+ (team.get(9).getStrikerAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getStrikerAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(9);
					team.add(9, p);
				}
			}
		}
		i = players.iterator();
		while (i.hasNext()) {
			Player p = i.next();
			if (p.getStrikerAbility() == 20) {
				if (team.size() <= 10) {
					if (!(team.contains(p)))
						team.add(p);
				} else if ((team.get(10).getCurrentAbility()
						+ (team.get(10).getStrikerAbility() * 5) < p
						.getCurrentAbility()
						+ (p.getStrikerAbility() * 5))
						&& (!(team.contains(p)))) {
					team.remove(10);
					team.add(10, p);
				}
			}
		}
		return team;
	}

	public void setTransferBudget(double transferBudget) {
		this.transferBudget = transferBudget;
	}

	public double getTransferBudget() {
		return transferBudget;
	}

	public void updateFinances(Calendar date) {
		double financialUpdate = 0;
		financialUpdate = financialUpdate
				+ ((3000 * this.getReputation()) / 365.25);
		if (date.get(Calendar.MONTH) >= 5 && date.get(Calendar.MONTH) <= 7) {
			financialUpdate = financialUpdate
					+ (this.getNumberOfSeasonTicketHolders()
							* this.getSeasonTicketPrice() / 92);
		}
		if (date.get(Calendar.DAY_OF_MONTH) == 1) {
			double wages = 0;
			Iterator<Player> iPlayer = this.getSquad().iterator();
			while (iPlayer.hasNext()) {
				wages += iPlayer.next().getWages();
			}
			Iterator<NonPlayer> iNonPlayer = this.getStaff().iterator();
			while (iNonPlayer.hasNext()) {
				wages += iNonPlayer.next().getWages();
			}
			financialUpdate = financialUpdate - (wages * 4.333);
		}
		financialUpdate = financialUpdate
				- ((this.getHomeGround().getCapacity() * 100) / 365.25);
		this.setBankBalance(this.getBankBalance() + financialUpdate);
	}

	public void setNumberOfSeasonTicketHolders(int numberOfSeasonTicketHolders) {
		this.numberOfSeasonTicketHolders = numberOfSeasonTicketHolders;
	}

	public int getNumberOfSeasonTicketHolders() {
		return numberOfSeasonTicketHolders;
	}

	public void setSeasonTicketPrice(double seasonTicketPrice) {
		this.seasonTicketPrice = seasonTicketPrice;
	}

	public double getSeasonTicketPrice() {
		return seasonTicketPrice;
	}

	public void setAverageAttendance(int averageAttendance) {
		this.averageAttendance = averageAttendance;
	}

	public int getAverageAttendance() {
		return averageAttendance;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}
	
	public boolean checkForFixture(Calendar c){
		boolean fixture = false;
		for(int i = 0; i < this.getFixtures().size(); i++){
			Match m = this.getFixtures().get(i);
			//System.out.println(m.getDate().get(Calendar.DAY_OF_YEAR));
			//System.out.println(c.get(Calendar.DAY_OF_YEAR));
			if((Math.abs(m.getDate().get(Calendar.DAY_OF_YEAR) - c.get(Calendar.DAY_OF_YEAR))) <= 2){
				fixture = true;
				break;
			}
		}
		return fixture;
	}
}
