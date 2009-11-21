package ie.tippinst.jod.fm.model;

import java.io.Serializable;

public class Injury implements Serializable {
	
	private int id;
	private String name;
	private int daysOut;
	
	public Injury() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDaysOut() {
		return daysOut;
	}

	public void setDaysOut(int daysOut) {
		this.daysOut = daysOut;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Injury(int id, String name, int daysOut) {
		super();
		this.id = id;
		this.name = name;
		this.daysOut = daysOut;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Injury [daysOut=" + daysOut + ", id=" + id + ", name=" + name
				+ "]";
	}
	
	

}
