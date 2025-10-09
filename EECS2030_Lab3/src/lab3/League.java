package lab3;

/**
 * Represents a scoring format for a sports league.
 * This class is composed within a League (composition relationship).
 * Constructors are private - objects must be created using static factory methods.
 * 
 * @author Dr. Sukhwant Sagar
 * @author Aaditya Karamchandani
 * @since Fall 2025
 */
class ScoringFormat {
    //attributes

	private String formatName;
	private int touchdownPoints;
	private double receptionPoints;
	private double yardsPerPoint;
	
    /**
     * Private constructor to create a ScoringFormat.
     * Users must use static factory methods to create instances.
     * 
     * @param formatName the name of the scoring format
     * @param touchdownPoints points awarded for touchdowns
     * @param receptionPoints points awarded per reception
     * @param yardsPerPoint yards needed per point
     */
	private ScoringFormat(String formatString, String touchdownPoints, int receptionPoints, double yardsPerPoint) {
		
	}
    
	

    /**
     * Private copy constructor to create a deep copy of a ScoringFormat.
     * Used internally for composition relationships.
     * 
     * @param other the ScoringFormat to copy
     */
    
	private ScoringFormat(ScoringFormat other) {
		
	}
    
    /**
     * Package-private method to create a copy.
     * This is used by League class for composition relationship.
     * 
     * @param other the ScoringFormat to copy
     * @return a new deep copy of the ScoringFormat
     */
    ScoringFormat createCopy(ScoringFormat other) {
    	
    }
	
	
	

    /**
     * Static factory method to create a Standard scoring format.
     * Standard: 6 points per TD, 0 points per reception, 0.1 yards per point
     * 
     * @return a new ScoringFormat with Standard settings
     */
    public ScoringFormat createStandingScoring() {
		
	}
	
    

    /**
     * Static factory method to create a PPR (Points Per Reception) scoring format.
     * PPR: 6 points per TD, 1 point per reception, 0.1 yards per point
     * 
     * @return a new ScoringFormat with PPR settings
     */
    public ScoringFormat createPPRScoring() {
	
    }
	
    

    /**
     * Static factory method to create a Half-PPR scoring format.
     * Half-PPR: 6 points per TD, 0.5 points per reception, 0.1 yards per point
     * 
     * @return a new ScoringFormat with Half-PPR settings
     */
    public ScoringFormat createHalfPPRScoring() {
	
    }
    

    // Getters and Setters
   public String getFormatName() {
	   return this.formatName;
   }
   public void setFormatName(String formatName) {
	   this.formatName = formatName;
   }
   public int getTouchdownPoints() {
	   return this.touchdownPoints;
   }
   public void setTouchdownPoints(int touchdownPoints) {
	   this.touchdownPoints = touchdownPoints;
   }
   public double getReceptionPoints() {
	   return this.receptionPoints;
   }
   public void setReceptionPoints(int receptionPoints) {
	   this.receptionPoints = receptionPoints;
   }
   public double getYardPerPoint() {
	   return this.yardPerPoint;
   }
   public void setYardPerPoint(double yardPerPoints) {
	   this.yardPerPoints = yardPerPoints;
   }
   public double calculatePoints(int touchdowns, int receptions, int yards) {

   }
   public String toString() {
   }
    
}

/**
 * Represents an athlete in a sports league.
 * Athletes can be part of team rosters (aggregation relationship).
 *  
 */
class Athlete {
    
	//Attributes

    private String athleteId;
    private String firstname;
    private String lastName;
    private String position;
    private String proTeam;
    private double weeklyPoints;
    private double seasonPoints;
    /**
     * Overloaded constructor to create an Athlete.
     * 
     * @param athleteId unique identifier for the athlete
     * @param firstName athlete's first name
     * @param lastName athlete's last name
     * @param position athlete's position (QB, RB, WR, etc.)
     * @param proTeam athlete's professional team
     */
   public Athlete(String athleteId, String firstName, String lastName, String position, String proTeam) {
   }


    /**
     * Copy constructor to create a deep copy of an Athlete.
     * 
     * @param other the Athlete to copy
     */
   public Athlete(Athlete other) {
   }

    /**
     * Static factory method to create an Athlete.
     * 
     * @param athleteId unique identifier for the athlete
     * @param firstName athlete's first name
     * @param lastName athlete's last name
     * @param position athlete's position
     * @param proTeam athlete's professional team
     * @return a new Athlete instance
     */
   public static Athlete createAthlete(String athleteId, String firstName, String lastName, String position, String proTeam) {
   }
   

    // Getters and Setters
  
  public String getAthleteId() {
	  return this.athleteId;
  }

  public void setAthleteId(String athleteId) {
	  this.athleteId = athleteId;
  }

  public String getFirstName() {
	  return this.firstName;
  }

  public void setFirstName(String firstName) {
	  this.firstName = firstName;
  }

  public String getLastName() {
	  return this.lastName;
  }

  public void setLastName(String lastName) {
	  this.lastName = lastName;
  }

  public String getPosition() {
	  return this.position;
  }

  public void setPosition(String position) {
	  this.position = position;
  }

  public String getProTeam() {
	  return this.proTeam;
  }

  public void setProTeam(String proTeam) {
	  this.proTeam = proTeam;
  }

  public double getWeeklyPoints() {
	  return this.weeklyPoints;
  }

  public void setWeeklyPoints(double weeklyPoints) {
	  this.weeklyPoints = weeklyPoints;
  }

  public double getSeasonPoints() {
	  return this.seasonPoints;
  }

  public void setSeasonPoints(double seasonPoints) {
	  this.seasonPoints = seasonPoints;
  }



  public void updateWeeklyStats(double points) {

  }

  public String getFullName() {
	  return this.firstName + this.lastName;
  }

  public String toString() {
	  
  }




}

/**
 * Represents a team in a league.
 * Teams have athletes (aggregation) and game statistics (composition).
 * 
 */
class Team {
	
   //Attributes
   private String teamId;
   private String teamName;
   private String ownerName;
   private String logoColor;
   private ArrayList<Athlete>;
   private int wins;
   private int losses;
   private double totalPoints;
    /**
     * Overloaded constructor to create a Team.
     * 
     * @param teamId unique identifier for the team
     * @param teamName name of the team
     * @param ownerName name of the team owner
     * @param logoColor team's logo color
     */
  public Team(String teamId, String teamName, String ownerName, String logoColor) {
  }


    /**
     * Copy constructor to create a deep copy of a Team.
     * 
     * @param other the Team to copy
     */
  public Team(Team other) {
  }


    /**
     * Static factory method to create a Team.
     * 
     * @param teamId unique identifier for the team
     * @param teamName name of the team
     * @param ownerName name of the team owner
     * @param logoColor team's logo color
     * @return a new Team instance
     */
  public static Team createTeam(String teamId, String teamName, String ownerName, String logoColor) {
  }

    // Getters and Setters
  public String getTeamId() {
	  return this.teamId;
  }

  public void setTeamId(String teamId) {
	  this.teamId = teamId;
  }

  public String getTeamName() {
	  return this.teamName;
  }

  public void setTeamName(String teamName) {
  	this.teamName = teamName;
  }


  public String getOwnerName() {
  	return this.ownerName;
  }

  public void setOwnerName(String ownerName) {
  	this.ownerName = ownerName;
  }

  public String getLogoColor() {
  	return this.logoColor;
  }

  public void setLogoColor(String logoColor) {
  	this.logoColor = logoColor;
  }

  public ArrayList<Athlete> getRoster() {

  }

  public void setRoster(ArrayList<Athlete> roster) {

  }

  public int getWins() {
  	return this.wins;
  }

  public void setWins(int wins) {
  	this.wins = wins;
  } 

  public int getLosses() {
  	return this.losses;
  }

  public void setLosses(int losses) {
  	this.losses = losses;
  }

  public double getTotalPoints() {
  	return this.totalPoints;
  }

  public void setTotalPoints(double totalPoints) {
  	this.totalPoints = totalPoints;
  }



  public boolean addAthlete(Athlete athlete) {

  }

  public boolean removeAthlete(String athleteId) {

  }

  public double calculateWeeklyScore() {

  }

  public void recordWin() {

  }

  public void recordLoss() {

  }

  public double getWinPercentage() {

  }

  public String toString() {

  }


}

/**
 * Represents a sports league.
 * A league has a scoring format (composition) and teams (aggregation).
 * */
public class League {
	
    //Attributes
   private String leagueId;
   private String leagueName;
   private String commissionerName;
   private int season;
   private ScoringFormat scoringFormat;
   private ArrayList<Team> teams;
   private int maxTeams;

    /**
     * Default constructor to create a League with default values.
     * leagueId = "L000",leagueName = "Default League",commissionerName = "Commissioner",season = 2025,new scoringFormat, new ArrayList Team,maxTeams = 10
     */
   public League() {
	   this.leagueId = "L000";
	   this.leagueName = "Default League";
	   this.commissionerName = "Commissioner";
	   this.season = 2025;
	   this.scoringFormat = new ScoringFormat;
	   this.teams = new ArrayList<Teams>;
	   this.maxTeams = 10;
   } 

    /**
     * Overloaded constructor to create a League.
     * 
     * @param leagueId unique identifier for the league
     * @param leagueName name of the league
     * @param commissionerName name of the league commissioner
     * @param season the season year
     * @param scoringFormat the scoring format for the league (composition)
     * @param maxTeams maximum number of teams allowed
     */
   public League(String leagueId, String leagueName, String commissionerName, ints season, ScoringFormat scoringFormat, int maxTeams) {}

    /**
     * Copy constructor to create a deep copy of a League.
     * 
     * @param other the League to copy
     */
    public League(League other) {}

    /**
     * Static factory method to create a League.
     * 
     * @param leagueId unique identifier for the league
     * @param leagueName name of the league
     * @param commissionerName name of the league commissioner
     * @param season the season year
     * @param scoringFormat the scoring format for the league
     * @param maxTeams maximum number of teams allowed
     * @return a new League instance
     */
    public static League createLeague(String leagueId, String leagueName, String commissionerName, int season, ScoringFormat scoringFormat, int maxTeams) {} 

    // Getters and Setters
   public String getLeagueId() {
	   return this.leagueId;
   }

   public void setLeagueId(String leagueId) {
	   this.leagueId = leagueId;
   }

   public String getLeagueName() {
	   return this.leagueName; 
   }

   public void setLeagueName(String leagueName) {
	   this.leagueName = leagueName;
   }

   public String getCommissionerName() {
	   return this.commissionerName;
   }

   public void setCommissionerName(String commissionerName) {
	   this.commissionerName = commissionerName;
   }

   public int getSeason() {
	   return this.season;
   }

   public void setSeason(int season) {
	   this.season = season;
   }

   public int getMaxTeams() {
	   return this.maxTeams;
   }
   public void setMaxTeams(int maxTeams) {
	   this.maxTeams = maxTeams;
   }


    /**
     * Gets a copy of the scoring format (composition - return deep copy).
     * 
     * @return a deep copy of the scoring format
     */
   

    /**
     * Sets the scoring format with a deep copy (composition).
     * 
     * @param scoringFormat the new scoring format
     */
    
    public boolean addTeam(Team team) {
    }

    public boolean removeTeam(String teamId) {
    }

    public ArrayList<Team> getStandings() {}

    public String toString() {}

}
