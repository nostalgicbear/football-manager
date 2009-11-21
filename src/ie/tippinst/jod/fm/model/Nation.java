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

	public Nation(int id, String name, int reputation,
			List<Player> squad, List<NonPlayer> staff) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = reputation;
		this.squad = squad;
		this.staff = staff;
	}

	public Nation(int id, String name, int reputation) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = reputation;
	}

	@Override
	public String toString() {
		return "Nation [name=" + name + ", reputation="
				+ reputation + "]";
	}

	public int getId() {
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	
}
