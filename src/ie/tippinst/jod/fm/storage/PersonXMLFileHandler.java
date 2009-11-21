package ie.tippinst.jod.fm.storage;

import ie.tippinst.jod.fm.model.Club;
import ie.tippinst.jod.fm.model.Stadium;

import java.beans.DefaultPersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonXMLFileHandler {

	public void create(Club c) {
		// Workaround to appending to XML file
		// Read in the contents of the file to a List

		File file = new File("club.xml");// File reference

		List<Club> list = new ArrayList<Club>();// List for file contents

		// Reading in from the file via XMLDecoder
		//XMLDecoder decoder = null;
		
		/*if(file.exists()) {
			try {
				FileInputStream fos = new FileInputStream(file);
				BufferedInputStream bos = new BufferedInputStream(fos);
				decoder = new XMLDecoder(bos);
	
				try {
					while (true) {
						Stadium record = (Stadium) decoder.readObject();
						list.add(record);
					}
				} catch (java.lang.ArrayIndexOutOfBoundsException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					decoder.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}*/

		// Add the new Person to the end of that list

		//System.out.println(s);
		list.add(c);
		//System.out.println(s);

		// Write out the List to the file

		Iterator<Club> i = list.iterator();

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		XMLEncoder encoder = null;
		try {
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			encoder = new XMLEncoder(bos);

			while (i.hasNext()) {
				//System.out.println(s);
			encoder.writeObject(i.next());// writing to XMLEncoder
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			encoder.flush();
			encoder.close();
		}

	}

	public void delete(String name) {
		// TODO Auto-generated method stub

	}

	public Stadium read() {
		
		File file = new File("stadium.xml");
		Stadium s = null;
		try {
			FileInputStream fos = new FileInputStream(file);
			BufferedInputStream bos = new BufferedInputStream(fos);
			XMLDecoder decoder = new XMLDecoder(bos);
			//do{
				s = (Stadium) decoder.readObject();
			//}while();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}

	public void update(String name, Stadium p) {
		// TODO Auto-generated method stub

	}

}

