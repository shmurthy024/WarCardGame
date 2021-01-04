/** Simulation.java
*
* This class is the driver class for War.
* @author Shree Murthy
* @author Student ID: 2374658
* @author shmurthy@chapman.edu
* CPSC 231-01 - Dr. Stevens
* Assignment - MP3 Warfare
* @version 1.0
*/



import java.util.*;

/** This method will conduct the simulation of the game(s).
* This will also spit out all the aggregate stats as well as release the WarLogger information.
*/
public class Simulation{

  /** Aggregate stats for number of battles*/
  private double avgNumOfBattles;
  /**Aggregate stats for number of wars */
  private double avgNumOfWars;
  /**Aggregate stats for number of double wars*/
  private double avgDoubleWars;
  /**Sets the number of max battles*/
  private int maxBattles;
  /**Sets the minimum number of battles*/
  private int minBattles;
  /**Sets max number of wars*/
  private int maxWars;
  /**Sets min number of wars*/
  private int minWars;

  /** Method that will instantiate all variables
  * Also calls methods to run the games
  */
  public Simulation(int games){
    avgNumOfWars = 0.0;
    avgNumOfBattles = 0.0;
    avgDoubleWars = 0.0;
    maxBattles = 0;
    minBattles = 0;
    maxWars = 0;
    minWars = 0;
    simulate(games);
    calculate(games);
  }

  /** Method that will simulate the number of games to be played
  * @param g represents int of games to be played
  */
  public void simulate(int g){
    Game game;
    for(int i = 1; i <= g; i++){
      System.out.println("Game: " + i);
      game = new Game();
      game.play();
      avgNumOfBattles += game.numBattle();
      avgNumOfWars += game.numWar();
      avgDoubleWars += game.numDoubleWars();
      if(game.numBattle() >= maxBattles && minBattles <= maxBattles){
        minBattles = maxBattles;
        maxBattles = game.numBattle();
      }
      if(game.numWar() > maxWars && minWars <= maxWars){
        minWars = maxWars;
        maxWars = game.numWar();
      }
      if(game.getWinner() == 1)
        WarLogger.getInstance().logGameOutcome(i, WarLogger.P1);
      if(game.getWinner() == 2)
        WarLogger.getInstance().logGameOutcome(i, WarLogger.P2);
    }
  }

  /** Calcualtes the averages of battles, wars, double wars
  * @param g int represents total games that were played to find avg value
  */
  public void calculate(int g){
    avgDoubleWars /= g;
    avgNumOfWars /= g;
    avgNumOfBattles /= g;
  }

  /** Method that reports all the stats
  * @return String of all the aggregate stats
  */
  public String report(){
    return "Average number of Battles: " + avgNumOfBattles + "\nAverage number of Wars: " + avgNumOfWars
    + "\nAverage number of Double Wars: " + avgDoubleWars + "\nMax Battles: " + maxBattles + "\nMin Battles: " + minBattles
    + "\nMax Wars: " + maxWars + "\nMin Wars: " + minWars + "\nYAYYYY YOU FINISHED WAR!!!!";
  }


  public static void main(String[] args) {
    int games = Integer.parseInt(args[0]);
    Simulation simulateMe = new Simulation(games);
    System.out.println(simulateMe.report());
    WarLogger.getInstance().release();
  }

}
