package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.List;

public class Club implements Serializable {
	
	private int id;
	private String name;
	private List<Player> squad;
	private List<NonPlayer> staff;
	private int reputation;
	private List<Club> rivals;
	private double bankBalance;
	private Stadium homeGround;
	private Nation nationality;
	
	public Club(){
		super();
	}
	
	public Club(int id, String name, int reputation, double bankBalance,
			Stadium homeGround, Nation nationality) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = reputation;
		this.bankBalance = bankBalance;
		this.homeGround = homeGround;
		this.nationality = nationality;
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
	
	
	

}
