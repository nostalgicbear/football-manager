package ie.tippinst.jod.fm.db;

import java.io.Serializable;
import java.util.Calendar;

public class Message implements Comparable<Message>, Serializable{
	
	private static final long serialVersionUID = -7625330569109576375L;
	private int id;
	private Calendar date;
	private String heading;
	private String body;
	private static int count = 0;
	private boolean read;
	
	public Message() {
		super();
		this.setId(count);
		count++;
	}
	public Message(Calendar date, String heading, String body) {
		super();
		this.setDate(date);
		this.setHeading(heading);
		this.setBody(body);
		this.setId(count);
		this.read = false;
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
    public int compareTo(Message m) {
        int compare = this.getDate().compareTo(m.getDate());
        return (compare == 0 ? this.getId() - m.getId() : compare);
    }
	public void setId(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	public boolean isRead() {
		return read;
	}
	public static void setCount(int count) {
		Message.count = count;
	}
}
