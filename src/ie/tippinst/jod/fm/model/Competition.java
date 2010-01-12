package ie.tippinst.jod.fm.model;

import java.io.Serializable;

public abstract class Competition implements Serializable {
	
	private static final long serialVersionUID = -4266130857896762280L;
	private int id;
	private String name;
	private int reputation;

	public Competition() {
		super();
	}

	public Competition(int id, String name, int reputation) {
		super();
		this.id = id;
		this.name = name;
		this.reputation = reputation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getReputation() {
		return reputation;
	}

	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	
	public abstract void setMatchSchedule();
}
