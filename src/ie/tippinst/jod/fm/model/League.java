package ie.tippinst.jod.fm.model;

import java.io.Serializable;
import java.util.List;

public class League extends Competition implements Serializable {
	
	private int numberOfTeams;
	private List<Club> teams;
	private int table [][];
	
	public League() {
		super();
	}

	public League(int id, String name, int reputation, int numberOfTeams) {
		super(id, name, reputation);
		this.numberOfTeams = numberOfTeams;
		table = new int [this.getNumberOfTeams()][10];
		for(int i = 0; i < this.getNumberOfTeams(); i++){
			table[i][0] = i + 1;
			table[i][1] = this.getTeams().get(i).getId();
		}
	}

	public int getNumberOfTeams() {
		return numberOfTeams;
	}

	public void setNumberOfTeams(int numberOfTeams) {
		this.numberOfTeams = numberOfTeams;
	}

	public List<Club> getTeams() {
		return teams;
	}

	public void setTeams(List<Club> teams) {
		this.teams = teams;
	}

	public int[][] getTable() {
		return table;
	}

	public void setTable(int[][] table) {
		this.table = table;
	}
}
