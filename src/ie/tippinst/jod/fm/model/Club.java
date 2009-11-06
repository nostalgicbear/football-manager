package ie.tippinst.jod.fm.model;

import java.util.List;

public class Club {
	
	private int id;
	private String name;
	private List<Player> squad;
	private List<NonPlayer> staff;
	private int reputation;
	private List<Club> rivals;
	private double bankBalance;
	private Stadium homeGround;
	private Nation nationality;
	
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
	
	

}
