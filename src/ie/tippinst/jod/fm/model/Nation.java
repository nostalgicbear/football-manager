package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.List;

public class Nation implements Serializable {
	
	private int id;
	private String name;
	private int reputation;
	private List<Player> squad;
	private List<NonPlayer> staff;	
	
	public Nation() {
		super();
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

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}

	public List<Player> getSquad() {
		return squad;
	}

	public void setSquad(List<Player> squad) {
		this.squad = squad;
	}

	public List<NonPlayer> getStaff() {
		return staff;
	}

	public void setStaff(List<NonPlayer> staff) {
		this.staff = staff;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
