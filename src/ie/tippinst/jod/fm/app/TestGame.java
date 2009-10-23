package ie.tippinst.jod.fm.app;

import ie.tippinst.jod.fm.obj.Club;
import ie.tippinst.jod.fm.obj.Injury;
import ie.tippinst.jod.fm.obj.Nation;
import ie.tippinst.jod.fm.obj.NonPlayer;
import ie.tippinst.jod.fm.obj.Person;
import ie.tippinst.jod.fm.obj.Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;


public class TestGame {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		List<Person> personList = new ArrayList<Person>();
		List<Nation> nationList = new ArrayList<Nation>();
		Nation personNationality = null;
		
		// User clicks create game button
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			
			//Create a statement
			Statement statement;
			
			//Place the results from out statements somewhere
			ResultSet personResult;
			ResultSet playerResult;
			ResultSet result;
			
			//Connect to the Database
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/footballmanager?user=root&password=football");
			
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT * FROM nation;");
			
			while(result.next()){
				nationList.add(new Nation(result.getInt(1), result.getString(2), result.getInt(3)));
			}
			Iterator<Nation> i = nationList.iterator();
			while(i.hasNext()){
				System.out.println(i.next());
			}
			
			statement = connection.createStatement();
			personResult = statement.executeQuery("SELECT * FROM person;");
			
			statement = connection.createStatement();
			playerResult = statement.executeQuery("SELECT * FROM player;");
			
			while(personResult.next()){
				while(i.hasNext()){
					Nation currentNation = i.next();
					if(currentNation.getId() == personResult.getInt(9)){
						personNationality = currentNation;
					}
				}
				if(personResult.getBoolean(11)){
					personList.add(new Player(personResult.getInt(1), personResult.getString(2), personResult.getString(3),
							personNationality, personResult.getDouble(4), personResult.getInt(5), new Date(), personResult.getInt(7),
							personResult.getInt(8), new Date(), new Club(), 5000000.0, 10000000.0, 2, 3,
							4, 5, 90, 100, 100, 6, 7,
							8, 9, 10, 11, 12,
							13, new Injury()));
					
					/*if(personResult.getBoolean(11)){
					personList.add(new Player(personResult.getInt(1), personResult.getString(2), personResult.getString(3),
							personNationality, personResult.getDouble(4), personResult.getInt(5), new Date(), personResult.getInt(7),
							personResult.getInt(8), new Date(), new Club(), 5000000.0, 10000000.0, playerResult.getInt(2), playerResult.getInt(3),
							playerResult.getInt(4), playerResult.getInt(5), 90, 100, 100, playerResult.getInt(6), playerResult.getInt(7),
							playerResult.getInt(8), playerResult.getInt(9),	playerResult.getInt(10), playerResult.getInt(11), playerResult.getInt(12),
							playerResult.getInt(13), new Injury()));*/
				}
				//else{
				//	personList.add(new NonPlayer());
				//}
			}
			
			Iterator<Person> j = personList.iterator();
			while(j.hasNext()){
				System.out.println(j.next());
			}
			
			statement.clearBatch();
			statement.close();
			connection.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
