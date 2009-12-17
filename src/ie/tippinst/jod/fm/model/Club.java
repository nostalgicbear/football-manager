package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public class Club implements Serializable {
	
	private static final long serialVersionUID = 8870087600952858149L;
	private int id;
	private String name;
	private List<Player> squad;
	private List<NonPlayer> staff;
	private int reputation;
	private List<Club> rivals;
	private double bankBalance;
	private Stadium homeGround;
	private Nation nationality;
	private League league;
	private List<Match> fixtures;
	private List<Player> selectedTeam;
	
	public Club(){
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
	
	public List<String> getClubInformation(){
		List<String> list = new ArrayList<String>();
		list.add(this.getNationality().getName());
		int reputation = this.getReputation();
		if(reputation >= 9500){
			list.add("Worldwide");
		}
		else if(reputation >= 7500){
			list.add("Continental");
		}
		else if(reputation >= 4500){
			list.add("National");
		}
		else if(reputation >= 1500){
			list.add("Regional");
		}
		else {
			list.add("Local");
		}
		double finances = this.getBankBalance();
		if(finances >= 80000000){
			list.add("Very Rich");
		}
		else if(finances >= 40000000){
			list.add("Rich");
		}
		else if(finances >= 20000000){
			list.add("Secure");
		}
		else if(finances >= -5000000){
			list.add("Okay");
		}
		else if(finances >= -20000000){
			list.add("Insecure");
		}
		else {
			list.add("In Debt");
		}
		list.add(this.getLeague().getName());
		list.add(this.getHomeGround().getName());
		list.add(this.getHomeGround().getCapacity()+"");
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
	
	public boolean makeOffer(Player p, int value){
		if(p.getSaleValue() > value){
			return false;
		}
		return true;
	}
	
	public boolean offerContract(Player p, int wages, Calendar c, int status){
		//TODO: Check if player is happy with terms and if yes return true else return false
		return true;
	}
	
	public void setStatusOfPlayers(){
		Iterator<Player> i = this.getSquad().iterator();
		double[] abilityThresholds = new double[3];
		abilityThresholds[0] = ((195 / 20) * this.getLeague().getReputation()) - ((10000 - this.getReputation()) / 100.0);
		abilityThresholds[1] = ((180 / 20) * this.getLeague().getReputation()) - ((10000 - this.getReputation()) / 100.0);
		abilityThresholds[2] = ((155 / 20) * this.getLeague().getReputation()) - ((10000 - this.getReputation()) / 100.0);		
		while(i.hasNext()){
			Player p = i.next();
			if(p.getCurrentAbility() >= abilityThresholds[0]){
				p.setStatus(0);
			}
			else if(p.getCurrentAbility() >= abilityThresholds[1]){
				p.setStatus(1);
			}
			else if(p.getCurrentAbility() >= abilityThresholds[2]){
				p.setStatus(2);
			}
			else if(p.getPotentialAbility() >= abilityThresholds[1] && p.getAge() <= 23){
				p.setStatus(3);
			}
			else if(p.getPotentialAbility() >= abilityThresholds[2] && p.getAge() <= 23){
				p.setStatus(4);
			}
			else{
				p.setStatus(5);
			}
		}
	}

	public void setSelectedTeam(List<Player> selectedTeam) {
		this.selectedTeam = selectedTeam;
	}

	public List<Player> getSelectedTeam() {
		return selectedTeam;
	}
}
