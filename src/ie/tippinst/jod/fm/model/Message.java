package ie.tippinst.jod.fm.model;

public class Message {
	
	private String heading;
	private String body;
	
	public Message() {
		super();
	}
	public Message(String heading, String body) {
		super();
		this.setHeading(heading);
		this.setBody(body);
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
	
	

}
