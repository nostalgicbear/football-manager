package ie.tippinst.jod.fm.model;

import java.util.List;

public class Nation {
	
	private int id;
	private String name;
	private int reputation;
	private List<Player> squad;
	private List<NonPlayer> staff;
	
	
	
	public Nation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Nation(int id, String name, int worldRankingPoints,
			List<Player> squad, List<NonPlayer> staff) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = worldRankingPoints;
		this.squad = squad;
		this.staff = staff;
	}

	public Nation(int id, String name, int worldRankingPoints) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = worldRankingPoints;
	}

	@Override
	public String toString() {
		return "Nation [name=" + name + ", worldRankingPoints="
				+ reputation + "]";
	}

	public int getId() {
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	
}
