package ie.tippinst.jod.fm.tests;

import ie.tippinst.jod.fm.model.Person;
import ie.tippinst.jod.fm.storage.PersonXMLFileHandler;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class TestGame {

	public static void main(String[] args) {
		PersonXMLFileHandler p = new PersonXMLFileHandler();
		Calendar c = new GregorianCalendar();
		c.set(1987, 0, 18);
		p.create(new Person(c));
		//System.out.println(p.read());
	}

}
