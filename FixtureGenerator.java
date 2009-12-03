/*
 * This code owes an enormous debt to
 * http://www.barrychessclub.org.uk/berger2001.htm
 */

import java.util.Arrays;
import java.util.*;

public class FixtureGenerator {

    public static void main(String[] args) {

        // Find out how many teams we want fixtures for.
        int teams = 20;
        /*try {
            teams = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Please state number of teams as first arg.");
            System.exit(0);
        }*/

        List<String> teamList = new ArrayList<String>();
        teamList.add("Arsenal");
        teamList.add("Aston Villa");
        teamList.add("Birmingham City");
        teamList.add("Blackburn Rovers");
        teamList.add("Bolton Wanderers");
        teamList.add("Burnley");
        teamList.add("Chelsea");
        teamList.add("Everton");
        teamList.add("Fulham");
        teamList.add("Hull City");
        teamList.add("Liverpool");
        teamList.add("Manchester City");
        teamList.add("Manchester United");
        teamList.add("Portsmouth");
        teamList.add("Stoke City");
        teamList.add("Sunderland");
        teamList.add("Tottenham Hotspur");
        teamList.add("West Ham United");
        teamList.add("Wigan Athletic");
        teamList.add("Wolverhampton Wanderers");

        Collections.shuffle(teamList);

        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }

        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];
        String[][] awayRound = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                switch(home){
					case 0: rounds[round][match] = teamList.get(0) + " v ";
							break;
					case 1: rounds[round][match] = teamList.get(1) + " v ";
							break;
					case 2: rounds[round][match] = teamList.get(2) + " v ";
							break;
					case 3: rounds[round][match] = teamList.get(3) + " v ";
							break;
					case 4: rounds[round][match] = teamList.get(4) + " v ";
							break;
					case 5: rounds[round][match] = teamList.get(5) + " v ";
							break;
					case 6: rounds[round][match] = teamList.get(6) + " v ";
							break;
					case 7: rounds[round][match] = teamList.get(7) + " v ";
							break;
					case 8: rounds[round][match] = teamList.get(8) + " v ";
							break;
					case 9: rounds[round][match] = teamList.get(9) + " v ";
							break;
					case 10: rounds[round][match] = teamList.get(10) + " v ";
							break;
					case 11: rounds[round][match] = teamList.get(11) + " v ";
							break;
					case 12: rounds[round][match] = teamList.get(12) + " v ";
							break;
					case 13: rounds[round][match] = teamList.get(13) + " v ";
							break;
					case 14: rounds[round][match] = teamList.get(14) + " v ";
							break;
					case 15: rounds[round][match] = teamList.get(15) + " v ";
							break;
					case 16: rounds[round][match] = teamList.get(16) + " v ";
							break;
					case 17: rounds[round][match] = teamList.get(17) + " v ";
							break;
					case 18: rounds[round][match] = teamList.get(18) + " v ";
							break;
					case 19: rounds[round][match] = teamList.get(19) + " v ";
							break;
					default: System.out.println("Error");
				}
				switch(away){
					case 0: rounds[round][match] = rounds[round][match] + teamList.get(0);
							break;
					case 1: rounds[round][match] = rounds[round][match] + teamList.get(1);
							break;
					case 2: rounds[round][match] = rounds[round][match] + teamList.get(2);
							break;
					case 3: rounds[round][match] = rounds[round][match] + teamList.get(3);
							break;
					case 4: rounds[round][match] = rounds[round][match] + teamList.get(4);
							break;
					case 5: rounds[round][match] = rounds[round][match] + teamList.get(5);
							break;
					case 6: rounds[round][match] = rounds[round][match] + teamList.get(6);
							break;
					case 7: rounds[round][match] = rounds[round][match] + teamList.get(7);
							break;
					case 8: rounds[round][match] = rounds[round][match] + teamList.get(8);
							break;
					case 9: rounds[round][match] = rounds[round][match] + teamList.get(9);
							break;
					case 10: rounds[round][match] = rounds[round][match] + teamList.get(10);
							break;
					case 11: rounds[round][match] = rounds[round][match] + teamList.get(11);
							break;
					case 12: rounds[round][match] = rounds[round][match] + teamList.get(12);
							break;
					case 13: rounds[round][match] = rounds[round][match] + teamList.get(13);
							break;
					case 14: rounds[round][match] = rounds[round][match] + teamList.get(14);
							break;
					case 15: rounds[round][match] = rounds[round][match] + teamList.get(15);
							break;
					case 16: rounds[round][match] = rounds[round][match] + teamList.get(16);
							break;
					case 17: rounds[round][match] = rounds[round][match] + teamList.get(17);
							break;
					case 18: rounds[round][match] = rounds[round][match] + teamList.get(18);
							break;
					case 19: rounds[round][match] = rounds[round][match] + teamList.get(19);
							break;
					default: System.out.println("Error");
				}
                //rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }

        for (int i = 0; i < 19; i++){
			for(int j = 0; j < 10; j++){
				awayRound[i][j] = flip(rounds[i][j]);
			}
		}

        //rounds[0][0] = "Arsenal";

        // Display the fixtures
        for (int i = 0; i < rounds.length; i++) {
			Arrays.sort(rounds[i]);
            System.out.println("Round " + (i + 1));
            for (int j = 0; j < matchesPerRound; j++){
				System.out.println(rounds[i][j]);
			}
            //System.out.println(Arrays.asList(rounds[i]));
            System.out.println();
        }

        for (int i = 0; i < awayRound.length; i++) {
			Arrays.sort(awayRound[i]);
			System.out.println("Round " + (i + 20));
			for (int j = 0; j < matchesPerRound; j++){
				System.out.println(awayRound[i][j]);
			}
			//System.out.println(Arrays.asList(rounds[i]));
			System.out.println();
        }

        System.out.println();

        if (ghost) {
            System.out.println("Matches against team " + teams + " are byes.");
        }

        //System.out.println("Use mirror image of these rounds for "
        //    + "return fixtures.");
    }

    private static String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }
}

/*
 * This code owes an enormous debt to
 * http://www.barrychessclub.org.uk/berger2001.htm
 */

import java.util.Arrays;

public class FixtureGenerator {

    public static void main(String[] args) {

        // Find out how many teams we want fixtures for.
        int teams = 20;
        /*try {
            teams = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("Please state number of teams as first arg.");
            System.exit(0);
        }*/

        // If odd number of teams add a "ghost".
        boolean ghost = false;
        if (teams % 2 == 1) {
            teams++;
            ghost = true;
        }

        // Generate the fixtures using the cyclic algorithm.
        int totalRounds = teams - 1;
        int matchesPerRound = teams / 2;
        String[][] rounds = new String[totalRounds][matchesPerRound];

        for (int round = 0; round < totalRounds; round++) {
            for (int match = 0; match < matchesPerRound; match++) {
                int home = (round + match) % (teams - 1);
                int away = (teams - 1 - match + round) % (teams - 1);
                // Last team stays in the same place while the others
                // rotate around it.
                if (match == 0) {
                    away = teams - 1;
                }
                // Add one so teams are number 1 to teams not 0 to teams - 1
                // upon display.
                rounds[round][match] = (home + 1) + " v " + (away + 1);
            }
        }

        // Interleave so that home and away games are fairly evenly dispersed.
        String[][] interleaved = new String[totalRounds][matchesPerRound];

        int evn = 0;
        int odd = (teams / 2);
        for (int i = 0; i < rounds.length; i++) {
            if (i % 2 == 0) {
                interleaved[i] = rounds[evn++];
            } else {
                interleaved[i] = rounds[odd++];
            }
        }

        rounds = interleaved;

        // Last team can't be away for every game so flip them
        // to home on odd rounds.
        for (int round = 0; round < rounds.length; round++) {
            if (round % 2 == 1) {
                rounds[round][0] = flip(rounds[round][0]);
            }
        }

        // Display the fixtures
        for (int i = 0; i < rounds.length; i++) {
            System.out.println("Round " + (i + 1));
            System.out.println(Arrays.asList(rounds[i]));
            System.out.println();
        }

        System.out.println();

        if (ghost) {
            System.out.println("Matches against team " + teams + " are byes.");
        }

        System.out.println("Use mirror image of these rounds for "
            + "return fixtures.");
    }

    private static String flip(String match) {
        String[] components = match.split(" v ");
        return components[1] + " v " + components[0];
    }
}
