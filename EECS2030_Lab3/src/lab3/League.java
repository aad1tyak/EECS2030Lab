package lab3;

import java.util.ArrayList;

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
	private ScoringFormat(String formatName, int touchdownPoints, double receptionPoints, double yardsPerPoint) {
	  this.formatName = formatName;
    this.touchdownPoints = touchdownPoints;
    this.receptionPoints = receptionPoints;
    this.yardsPerPoint = yardsPerPoint;
	}
    
	

    /**
     * Private copy constructor to create a deep copy of a ScoringFormat.
     * Used internally for composition relationships.
     * 
     * @param other the ScoringFormat to copy
     */
    
	private ScoringFormat(ScoringFormat other) {
    this.formatName = other.formatName;
    this.touchdownPoints = other.touchdownPoints;
    this.receptionPoints = other.receptionPoints;
    this.yardsPerPoint = other.yardsPerPoint;
	}
    
    /**
     * Package-private method to create a copy.
     * This is used by League class for composition relationship.
     * 
     * @param other the ScoringFormat to copy
     * @return a new deep copy of the ScoringFormat
     */
    ScoringFormat createCopy(ScoringFormat other) {
      ScoringFormat newScoringFormat = new ScoringFormat(other);
      return newScoringFormat;
    }
	
	
	

    /**
     * Static factory method to create a Standard scoring format.
     * Standard: 6 points per TD, 0 points per reception, 0.1 yards per point
     * 
     * @return a new ScoringFormat with Standard settings
     */
    public static ScoringFormat createStandardScoring() {
      return new ScoringFormat("Standard", 6, 0, 0.1);
}
	
    

    /**
     * Static factory method to create a PPR (Points Per Reception) scoring format.
     * PPR: 6 points per TD, 1 point per reception, 0.1 yards per point
     * 
     * @return a new ScoringFormat with PPR settings
     */
    public static ScoringFormat createPPRScoring() {
      return new ScoringFormat("PPR", 6, 1, 0.1);
    }
	
    

    /**
     * Static factory method to create a Half-PPR scoring format.
     * Half-PPR: 6 points per TD, 0.5 points per reception, 0.1 yards per point
     * 
     * @return a new ScoringFormat with Half-PPR settings
     */
    public static ScoringFormat createHalfPPRScoring() {
      return new ScoringFormat("Half-PPR", 6, 0.5, 0.1);

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
   public void setReceptionPoints(double receptionPoints) {
	   this.receptionPoints = receptionPoints;
   }
   public double getYardsPerPoint() {
	   return this.yardsPerPoint;
   }
   public void setYardsPerPoint(double yardsPerPoints) {
	   this.yardsPerPoint = yardsPerPoints;
   }
   public double calculatePoints(int touchdowns, int receptions, int yards) {
     return (touchdowns * this.touchdownPoints) + (receptions * receptionPoints) + (yards * yardsPerPoint);
   }
   public String toString() {
     return String.format("%s: %d points per TD, %f point per reception, %f yards per point", this.formatName, this.touchdownPoints, this.receptionPoints, this.yardsPerPoint);

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
    private String firstName;
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
     this.athleteId = athleteId;
     this.firstName= firstName;
     this.lastName = lastName;
     this.position = position;
     this.proTeam = proTeam;
   }


    /**
     * Copy constructor to create a deep copy of an Athlete.
     * 
     * @param other the Athlete to copy
     */
   public Athlete(Athlete other) {
     this.athleteId = other.athleteId;
     this.firstName = other.firstName;
     this.lastName = other.lastName;
     this.position = other.position;
     this.proTeam = other.proTeam;
     this.weeklyPoints = other.weeklyPoints;
     this.seasonPoints = other.seasonPoints;
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
     Athlete newAthlete = new Athlete(athleteId, firstName, lastName, position, proTeam);
     return newAthlete;
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
    this.weeklyPoints = points;
    this.seasonPoints += points;
  }

  public String getFullName() {
	  return this.firstName + this.lastName;
  }

  public String toString() {
	  return String.format("Athlete Id: %s, First Name: %s, Last Name: %s, Position: %s, ProTeam: %s, Weekly Points: %f, Season Points: %f", this.athleteId, this.firstName, this.lastName, this.position, this.proTeam, this.weeklyPoints, this.seasonPoints);
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
   private ArrayList<Athlete> roster;
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
    this.teamId = teamId;
    this.teamName = teamName;
    this.ownerName = ownerName;
    this.logoColor = logoColor;
    this.roster = new ArrayList<Athlete>();
  }


    /**
     * Copy constructor to create a deep copy of a Team.
     * 
     * @param other the Team to copy
     */
  public Team(Team other) {
    this.teamId = other.teamId;
    this.teamName = other.teamName;
    this.ownerName = other.ownerName;
    this.logoColor = other.logoColor;
    this.roster = new ArrayList<Athlete>(other.roster);
    this.wins = other.wins;
    this.losses = other.losses;
    this.totalPoints = other.totalPoints;

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
    return new Team(teamId, teamName, ownerName, logoColor);
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
    return this.roster;

  }
  public void setRoster(ArrayList<Athlete> roster) {
	    this.roster = new ArrayList<Athlete>();
	    for(Athlete athlete : roster) {
	        this.roster.add(new Athlete(athlete)); // Deep copy each athlete
	    }
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
    if(this.roster.size() <= 16) {
      this.roster.add(athlete);
      return true;
    }
    else {
      return false;
    }
  }

  public boolean removeAthlete(String athleteId) {
    for(Athlete rostee : this.roster) {
      if (rostee.getAthleteId().equals(athleteId)) {
        this.roster.remove(rostee);
        return true;
      }
    }
    return false;
  }

  public double calculateWeeklyScore() {
    double totalWeeklyScore = 0.0;
    for(Athlete rostee : this.roster) {
      totalWeeklyScore += rostee.getWeeklyPoints();
    }
    return totalWeeklyScore;
  }

  public void recordWin() {
    this.wins++;

  }

  public void recordLoss() {
    this.losses++;
  }

  public double getWinPercentage() {
    return ( this.wins * 100 ) / (this.wins + this.losses);

  }

  public String toString() {
    return String.format("Team Id: %s, Team Name: %s, Owner Name: %s, Logo Color: %s, Wins: %d, losses: %d, Total Points: %f", this.teamId, this.teamName, this.ownerName, this.logoColor, this.wins, this.losses, this.totalPoints);

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
	   this.scoringFormat = ScoringFormat.createStandardScoring();
	   this.teams = new ArrayList<Team>();
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
   public League(String leagueId, String leagueName, String commissionerName, int season, ScoringFormat scoringFormat, int maxTeams) {
     this.leagueId = leagueId;
     this.commissionerName = commissionerName;
     this.season = season;
     this.maxTeams = maxTeams;
     this.scoringFormat = scoringFormat.createCopy(scoringFormat);
   }

    /**
     * Copy constructor to create a deep copy of a League.
     * 
     * @param other the League to copy
     */
    public League(League other) {
     this.leagueId = other.leagueId;
     this.commissionerName = other.commissionerName;
     this.season = other.season;
     this.maxTeams = other.maxTeams;
     this.scoringFormat = other.scoringFormat.createCopy(other.scoringFormat);
     this.teams = other.teams;

    }

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
    public static League createLeague(String leagueId, String leagueName, String commissionerName, int season, ScoringFormat scoringFormat, int maxTeams) {
      return new League(leagueId, leagueName, commissionerName, season, scoringFormat, maxTeams);
    } 

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
   public ScoringFormat getScoringFormat(){
     return this.scoringFormat.createCopy(this.scoringFormat);
   }

   public ArrayList<Team> getTeams() {
     return new ArrayList<Team>(this.teams);
   }
   

    /**
     * Sets the scoring format with a deep copy (composition).
     * 
     * @param scoringFormat the new scoring format
     */
    public void setScoringFormat(ScoringFormat scoringFormat) {
      this.scoringFormat = scoringFormat.createCopy(scoringFormat);
    }

    public void setTeams(ArrayList<Team> teams) {
        this.teams = new ArrayList<Team>();
        for(Team team : teams) {
            this.teams.add(new Team(team)); // Deep copy
        }
    }

    public boolean addTeam(Team team) {
      if(teams.size() < maxTeams){
        teams.add(team);
        return true;
      }
      else {
        return false;
      }
    }

    public boolean removeTeam(String teamId) {
      for(Team team : this.teams) {
        if(team.getTeamId().equals(teamId)){
          this.teams.remove(team);
          return true;
        }
      }
      return false;
    }


    public String toString() {
      return String.format("League Id: %s, League Name: %s, Commissioner Name: %s, Season: %s, Max Teams: %d", this.leagueId, this.leagueName, this.commissionerName, this.season, this.maxTeams);
    }

}
