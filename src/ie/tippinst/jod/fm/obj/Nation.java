package ie.tippinst.jod.fm.obj;

import java.util.List;

public class Nation {
	
	private int id;
	private String name;
	private int worldRankingPoints;
	private List<Player> squad;
	private List<NonPlayer> staff;
	
	public Nation(int id, String name, int worldRankingPoints,
			List<Player> squad, List<NonPlayer> staff) {
		super();
		this.id = id;
		this.name = name;
		this.worldRankingPoints = worldRankingPoints;
		this.squad = squad;
		this.staff = staff;
	}

	public Nation(int id, String name, int worldRankingPoints) {
		super();
		this.id = id;
		this.name = name;
		this.worldRankingPoints = worldRankingPoints;
	}

	@Override
	public String toString() {
		return "Nation [name=" + name + ", worldRankingPoints="
				+ worldRankingPoints + "]";
	}

	public int getId() {
		return id;
	}
	
	
	
	
}
