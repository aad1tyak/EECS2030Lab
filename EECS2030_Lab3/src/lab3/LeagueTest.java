package lab3;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import java.util.ArrayList;

/**
 * JUnit test class for the Fantasy Sports League Management System.
 * Tests are ordered by method name for consistent execution.
 * ScoringFormat constructors are private - only static factory methods are tested.
 * 
 * @author Dr. Sukhwant Sagar
 * @since Fall 2025
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LeagueTest {

    // ========================================
    // ScoringFormat Tests 
    // Note: ScoringFormat has PRIVATE constructors
    // Objects can ONLY be created via static factory methods
    // ========================================

    @Test
    public void test01_ScoringFormat_CreateStandardScoring_FormatName() {
        ScoringFormat standard = ScoringFormat.createStandardScoring();
        assertEquals("Standard", standard.getFormatName());
    }

    @Test
    public void test04_ScoringFormat_CreateStandardScoring_YardsPerPoint() {
        ScoringFormat standard = ScoringFormat.createStandardScoring();
        assertEquals(0.1, standard.getYardsPerPoint(), 0.001);
    }

    @Test
    public void test06_ScoringFormat_CreatePPRScoring_TouchdownPoints() {
        ScoringFormat ppr = ScoringFormat.createPPRScoring();
        assertEquals(6, ppr.getTouchdownPoints());
    }
   
    @Test
    public void test08_ScoringFormat_CreateHalfPPRScoring_FormatName() {
        ScoringFormat halfPPR = ScoringFormat.createHalfPPRScoring();
        assertEquals("Half-PPR", halfPPR.getFormatName());
    }
  
    @Test
    public void test10_ScoringFormat_Setters_FormatName() {
        ScoringFormat format = ScoringFormat.createStandardScoring();
        format.setFormatName("Custom");
        assertEquals("Custom", format.getFormatName());
    }


    @Test
    public void test12_ScoringFormat_Setters_ReceptionAndYardsPoints() {
        ScoringFormat format = ScoringFormat.createPPRScoring();
        format.setReceptionPoints(1.5);
        format.setYardsPerPoint(0.05);
        
        assertEquals(1.5, format.getReceptionPoints(), 0.001);
        assertEquals(0.05, format.getYardsPerPoint(), 0.001);
    }

    // ========================================
    // Athlete Tests 
    // ========================================

     @Test
    public void test15_Athlete_OverloadedConstructor_LastName() {
        Athlete athlete = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        assertEquals("Mahomes", athlete.getLastName());
    }

    @Test
    public void test16_Athlete_OverloadedConstructor_Position() {
        Athlete athlete = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        assertEquals("QB", athlete.getPosition());
    }

    @Test
    public void test19_Athlete_OverloadedConstructor_SeasonPointsInitialized() {
        Athlete athlete = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        assertEquals(0.0, athlete.getSeasonPoints(), 0.001);
    }

   @Test
    public void test22_Athlete_CopyConstructor_Position() {
        Athlete athlete1 = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        Athlete athlete2 = new Athlete(athlete1);
        assertEquals(athlete1.getPosition(), athlete2.getPosition());
    }

    @Test
    public void test23_Athlete_CopyConstructor_DeepCopy() {
        Athlete athlete1 = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        Athlete athlete2 = new Athlete(athlete1);
        athlete2.setFirstName("Travis");
        assertNotEquals(athlete1.getFirstName(), athlete2.getFirstName());
    }

    @Test
    public void test28_Athlete_SetFirstName() {
        Athlete athlete = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        athlete.setFirstName("Josh");
        assertEquals("Josh", athlete.getFirstName());
    }

     @Test
    public void test32_Athlete_SetWeeklyAndSeasonPoints() {
        Athlete athlete = new Athlete("A001", "Patrick", "Mahomes", "QB", "Kansas City Chiefs");
        athlete.setWeeklyPoints(25.5);
        athlete.setSeasonPoints(350.0);
        
        assertEquals(25.5, athlete.getWeeklyPoints(), 0.001);
        assertEquals(350.0, athlete.getSeasonPoints(), 0.001);
    }

    // ========================================
    // Team Tests
    // ========================================

    @Test
    public void test33_Team_OverloadedConstructor_TeamId() {
        Team team = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        assertEquals("T001", team.getTeamId());
    }

    @Test
    public void test34_Team_OverloadedConstructor_TeamName() {
        Team team = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        assertEquals("Thunder Strikers", team.getTeamName());
    }

    @Test
    public void test37_Team_OverloadedConstructor_WinsInitialized() {
        Team team = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        assertEquals(0, team.getWins());
    }

    @Test
    public void test44_Team_CopyConstructor_DeepCopy() {
        Team team1 = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        Team team2 = new Team(team1);
        team2.setTeamName("Lightning Bolts");
        assertNotEquals(team1.getTeamName(), team2.getTeamName());
    }

    @Test
    public void test45_Team_CopyConstructor_RosterDeepCopy() {
        Team team1 = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        ArrayList<Athlete> roster = new ArrayList<Athlete>();
        roster.add(new Athlete("A001", "Test", "Player", "QB", "Team A"));
        team1.setRoster(roster);
        
        Team team2 = new Team(team1);
        assertEquals(team1.getRoster().size(), team2.getRoster().size());
    }

    @Test
    public void test49_Team_SetTeamId() {
        Team team = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        team.setTeamId("T999");
        assertEquals("T999", team.getTeamId());
    }

    @Test
    public void test53_Team_SetWins() {
        Team team = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        team.setWins(8);
        assertEquals(8, team.getWins());
    }

    @Test
    public void test56_Team_SetRoster_Size() {
        Team team = new Team("T001", "Thunder Strikers", "John Doe", "Blue");
        ArrayList<Athlete> roster = new ArrayList<Athlete>();
        roster.add(new Athlete("A001", "Test", "Player1", "QB", "Team A"));
        roster.add(new Athlete("A002", "Test", "Player2", "RB", "Team B"));
        team.setRoster(roster);
        assertEquals(2, team.getRoster().size());
    }

   // ========================================
    // League Tests
    // ========================================

    @Test
    public void test58_League_DefaultConstructor_LeagueId() {
        League league = new League();
        assertEquals("L000", league.getLeagueId());
    }

   @Test
    public void test63_League_DefaultConstructor_ScoringFormatNotNull() {
        League league = new League();
        assertNotNull(league.getScoringFormat());
    }

    @Test
    public void test64_League_DefaultConstructor_TeamsListNotNull() {
        League league = new League();
        assertNotNull(league.getTeams());
    }

      @Test
    public void test68_League_OverloadedConstructor_Season() {
        ScoringFormat ppr = ScoringFormat.createPPRScoring();
        League league = new League("L001", "Premier League", "Commissioner Alice", 2025, ppr, 12);
        assertEquals(2025, league.getSeason());
    }
    @Test
    public void test71_League_CopyConstructor_LeagueId() {
        ScoringFormat ppr = ScoringFormat.createPPRScoring();
        League league1 = new League("L001", "Premier League", "Commissioner Alice", 2025, ppr, 12);
        League league2 = new League(league1);
        assertEquals(league1.getLeagueId(), league2.getLeagueId());
    }

    @Test
    public void test74_League_CopyConstructor_DeepCopy() {
        ScoringFormat ppr = ScoringFormat.createPPRScoring();
        League league1 = new League("L001", "Premier League", "Commissioner Alice", 2025, ppr, 12);
        League league2 = new League(league1);
        league2.setLeagueName("Modified League");
        assertNotEquals(league1.getLeagueName(), league2.getLeagueName());
    }

    @Test
    public void test80_League_SetCommissionerName() {
        League league = new League();
        league.setCommissionerName("Commissioner Charlie");
        assertEquals("Commissioner Charlie", league.getCommissionerName());
    }

    @Test
    public void test83_League_SetScoringFormat() {
        League league = new League();
        ScoringFormat halfPPR = ScoringFormat.createHalfPPRScoring();
        league.setScoringFormat(halfPPR);
        assertEquals("Half-PPR", league.getScoringFormat().getFormatName());
    }

    @Test
    public void test85_League_GetTeams_ReturnsDeepCopy() {
        League league = new League();
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(new Team("T001", "Team 1", "Owner 1", "Red"));
        league.setTeams(teams);
        
        ArrayList<Team> retrieved = league.getTeams();
        assertNotSame(teams, retrieved);
    }

      // ========================================
    // Aggregation Relationship Tests 
    // ========================================

    @Test
    public void test88_Aggregation_LeagueTeam_TeamsAreCopied() {
        Team team1 = new Team("T001", "Independent Team", "Owner A", "Blue");
        League league = new League();
        
        ArrayList<Team> teams = new ArrayList<Team>();
        teams.add(team1);
        league.setTeams(teams);
        
        team1.setTeamName("Modified Independent Team");
        assertNotEquals(team1.getTeamName(), league.getTeams().get(0).getTeamName());
    }

    @Test
    public void test91_Aggregation_TeamRoster_IndependentAfterSet() {
        Team team = new Team("T001", "Test Team", "Owner", "Blue");
        ArrayList<Athlete> originalRoster = new ArrayList<Athlete>();
        originalRoster.add(new Athlete("A001", "Test", "Player", "QB", "Team A"));
        
        team.setRoster(originalRoster);
        originalRoster.add(new Athlete("A002", "Another", "Player", "RB", "Team B"));
        
        assertEquals(1, team.getRoster().size());
    }

     // ========================================
    // Composition Relationship Tests 
    // ========================================

    @Test
    public void test94_Composition_LeagueScoringFormat_ExternalFormatUnaffected() {
        ScoringFormat externalFormat = ScoringFormat.createPPRScoring();
        League league = new League("L001", "Test League", "Commissioner", 2025, externalFormat, 10);
        
        // Get the league's format and modify it
        ScoringFormat leagueFormat = league.getScoringFormat();
        leagueFormat.setTouchdownPoints(10);
        
        // External format should remain unchanged (still 6 points)
        assertEquals(6, externalFormat.getTouchdownPoints());
        
        // League's internal format should also still be 6 (because getScoringFormat returns a copy)
        assertEquals(6, league.getScoringFormat().getTouchdownPoints());
    }

    @Test
    public void test95_Composition_SetScoringFormat_CreatesDeepCopy() {
        League league = new League();
        ScoringFormat newFormat = ScoringFormat.createStandardScoring();
        
        league.setScoringFormat(newFormat);
        newFormat.setFormatName("External Modified");
        
        assertNotEquals(newFormat.getFormatName(), league.getScoringFormat().getFormatName());
    }

     @Test
    public void test98_Composition_TeamStatistics_IndependentAfterCopy() {
        Team team1 = new Team("T001", "Test Team", "Owner", "Blue");
        team1.setWins(5);
        team1.setLosses(3);
        
        Team team2 = new Team(team1);
        team1.setWins(10);
        
        assertEquals(5, team2.getWins());
        assertNotEquals(team1.getWins(), team2.getWins());
    }

    @Test
    public void test99_Composition_LeagueWithMultipleScoringFormatChanges() {
        League league = new League();
        
        ScoringFormat ppr = ScoringFormat.createPPRScoring();
        league.setScoringFormat(ppr);
        assertEquals("PPR", league.getScoringFormat().getFormatName());
        
        ScoringFormat halfPPR = ScoringFormat.createHalfPPRScoring();
        league.setScoringFormat(halfPPR);
        assertEquals("Half-PPR", league.getScoringFormat().getFormatName());
        
        // Original formats should be unaffected
        assertEquals("PPR", ppr.getFormatName());
    }

    @Test
    public void test102_ScoringFormat_GetterModification_DoesNotAffectLeague() {
        League league = new League();
        
        // Get the scoring format and try to modify it
        ScoringFormat retrieved = league.getScoringFormat();
        retrieved.setFormatName("Hacked Format");
        retrieved.setTouchdownPoints(100);
        
        // League should still have original format (Standard with 6 TD points)
        assertEquals("Standard", league.getScoringFormat().getFormatName());
        assertEquals(6, league.getScoringFormat().getTouchdownPoints());
    }
    }
