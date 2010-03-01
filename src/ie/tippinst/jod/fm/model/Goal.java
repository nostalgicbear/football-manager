package ie.tippinst.jod.fm.model;

import java.io.Serializable;

public class Goal implements Serializable{
	
	private static final long serialVersionUID = -1828292652851225649L;
	private Player scorer;
	private int minute;
	
	public Goal(){
		
	}
	
	public Goal(Player scorer, int minute){
		this.scorer = scorer;
		this.minute = minute;
	}
	
	public void setScorer(Player scorer) {
		this.scorer = scorer;
	}
	public Player getScorer() {
		return scorer;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	public int getMinute() {
		return minute;
	}
}
