package ie.tippinst.jod.fm.model;

import java.io.Serializable;

public class Injury implements Serializable {
	
	private static final long serialVersionUID = -6699885390439073024L;
	private int id;
	private String name;
	private String sentenceName;
	private int minDaysOut;
	private int extraDaysOut;
	
	public Injury() {
		super();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinDaysOut() {
		return minDaysOut;
	}

	public void setMinDaysOut(int minDaysOut) {
		this.minDaysOut = minDaysOut;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Injury(int id, String name, int daysOut) {
		super();
		this.id = id;
		this.name = name;
		this.minDaysOut = daysOut;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Injury [daysOut=" + minDaysOut + ", id=" + id + ", name=" + name
				+ "]";
	}

	public void setExtraDaysOut(int extraDaysOut) {
		this.extraDaysOut = extraDaysOut;
	}

	public int getExtraDaysOut() {
		return extraDaysOut;
	}

	public void setSentenceName(String sentenceName) {
		this.sentenceName = sentenceName;
	}

	public String getSentenceName() {
		return sentenceName;
	}
	
	

}
