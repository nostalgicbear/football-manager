package ie.tippinst.jod.fm.tests;

import ie.tippinst.jod.fm.model.Cup;
import ie.tippinst.jod.fm.model.Person;
import ie.tippinst.jod.fm.storage.PersonXMLFileHandler;

import java.beans.Encoder;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class TestGame {

	public static void main(String[] args) {
		//PersonXMLFileHandler p = new PersonXMLFileHandler();
		//Calendar c = new GregorianCalendar();
		Cup cup = new Cup();
		cup.setId(10);
		cup.setName("FA Cup");
		cup.setReputation(16);
		cup.setNumberOfRounds(8);
		List<Calendar> list = new ArrayList<Calendar>();
		list.add(new GregorianCalendar(2009, 10, 7));
		cup.createRound(80, 1, list, false, 18000, 0);
		List<Calendar> list2 = new ArrayList<Calendar>();
		list2.add(new GregorianCalendar(2009, 10, 28));
		cup.createRound(40, 2, list2, false, 27000, 0);
		List<Calendar> list3 = new ArrayList<Calendar>();
		list3.add(new GregorianCalendar(2010, 0, 2));
		cup.createRound(64, 3, list3, false, 67500, 0);
		List<Calendar> list4 = new ArrayList<Calendar>();
		list4.add(new GregorianCalendar(2010, 0, 23));
		cup.createRound(32, 4, list4, false, 90000, 0);
		List<Calendar> list5 = new ArrayList<Calendar>();
		list5.add(new GregorianCalendar(2010, 1, 13));
		cup.createRound(16, 5, list5, false, 180000, 0);
		List<Calendar> list6 = new ArrayList<Calendar>();
		list6.add(new GregorianCalendar(2010, 2, 6));
		cup.createRound(8, 6, list6, false, 360000, 0);
		List<Calendar> list7 = new ArrayList<Calendar>();
		list7.add(new GregorianCalendar(2010, 3, 10));
		cup.createRound(4, 7, list7, false, 900000, 450000);
		List<Calendar> list8 = new ArrayList<Calendar>();
		list8.add(new GregorianCalendar(2010, 4, 15));
		cup.createRound(2, 8, list8, false, 1800000, 900000);
		
		double[] test = {1, 2, 3};
		/*for(int i = 0; i < matchDates.length; i++){
			Calendar c = new GregorianCalendar();
    		switch(i){
            case 0:
            	c.set(2010, 4, 6);
            	break;
            case 1:
            	c.set(2010, 4, 10);
            	break;
            case 2:
            	c.set(2010, 4, 16);
            	break;
            case 3:
            	c.set(2009, 7, 18);
            	break;
            case 4:
            	c.set(2009, 7, 22);
            	break;
            case 5:
            	c.set(2009, 7, 29);
            	break;
            case 6:
            	c.set(2009, 7, 31);
            	break;
            case 7:
            	c.set(2009, 8, 5);
            	break;
            case 8:
            	c.set(2009, 8, 8);
            	break;
            case 9:
            	c.set(2009, 8, 12);
            	break;
            case 10:
            	c.set(2009, 8, 19);
            	break;
            case 11:
            	c.set(2009, 8, 22);
            	break;
            case 12:
            	c.set(2009, 8, 26);
            	break;
            case 13:
            	c.set(2009, 8, 29);
            	break;
            case 14:
            	c.set(2009, 9, 3);
            	break;
            case 15:
            	c.set(2009, 9, 10);
            	break;
            case 16:
            	c.set(2009, 9, 17);
            	break;
            case 17:
            	c.set(2009, 9, 31);
            	break;
            case 18:
            	c.set(2009, 10, 14);
            	break;
            case 19:
             	c.set(2009, 10, 21);
             	break;
            case 20:
             	c.set(2009, 10, 24);
             	break;
            case 21:
             	c.set(2009, 10, 28);
             	break;
            case 22:
             	c.set(2009, 11, 1);
             	break;
            case 23:
             	c.set(2009, 11, 5);
             	break;
            case 24:
             	c.set(2009, 11, 26);
             	break;
            case 25:
             	c.set(2009, 11, 28);
             	break;
            case 26:
             	c.set(2010, 0, 1);
             	break;
            case 27:
             	c.set(2010, 0, 16);
             	break;
            case 28:
             	c.set(2010, 0, 23);
             	break;
            case 29:
             	c.set(2010, 0, 26);
             	break;
            case 30:
             	c.set(2010, 0, 30);
             	break;
            case 31:
             	c.set(2010, 1, 6);
             	break;
            case 32:
             	c.set(2010, 1, 9);
             	break;
            case 33:
             	c.set(2010, 1, 13);
             	break;
            case 34:
             	c.set(2010, 1, 20);
             	break;
            case 35:
             	c.set(2010, 1, 27);
             	break;
            case 36:
             	c.set(2010, 2, 6);
             	break;
            case 37:
             	c.set(2010, 2, 9);
             	break;
            case 38:
             	c.set(2010, 2, 13);
             	break;
            case 39:
             	c.set(2010, 2, 20);
             	break;
            case 40:
             	c.set(2010, 2, 27);
             	break;
            case 41:
             	c.set(2010, 3, 3);
             	break;
            case 42:
             	c.set(2010, 3, 5);
             	break;
            case 43:
             	c.set(2010, 3, 10);
             	break;
            case 44:
             	c.set(2010, 3, 17);
             	break;
            case 45:
             	c.set(2010, 3, 24);
             	break;
            default: System.out.println("ERROR");
            }
    		matchDates[i] = c;
		}*/
		try {
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(new File("test2.xml"))));
			encoder.writeObject(test);
			encoder.flush();
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//c.set(1980, 0, 22);
		//p.create(new Person(c));
		//System.out.println(p.read());
		//System.out.println(c.getTimeInMillis());
	}
}
