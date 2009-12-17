package ie.tippinst.jod.fm.model;

import java.util.Calendar;

public class Message implements Comparable<Message>{
	
	private int id;
	private Calendar date;
	private String heading;
	private String body;
	private static int count = 0;
	
	public Message() {
		super();
		this.id = count;
		count++;
	}
	public Message(Calendar date, String heading, String body) {
		super();
		this.setDate(date);
		this.setHeading(heading);
		this.setBody(body);
		this.id = count;
		count++;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getHeading() {
		return heading;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBody() {
		return body;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public Calendar getDate() {
		return date;
	}

	@Override
	public int compareTo(Message o) {
	    return this.getDate().getTime().compareTo(o.getDate().getTime());
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
