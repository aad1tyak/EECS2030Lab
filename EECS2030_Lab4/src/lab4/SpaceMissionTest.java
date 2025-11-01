package lab4;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/**
 * JUnit Test class for Space Mission System
 * Tests inheritance, composition, and aggregation relationships
 * @author Dr. Sukhwant Sagar
 */
public class SpaceMissionTest {
    
    private Crew crew1;
    private Crew crew2;
//    private PowerSystem power1;
//    private Spacecraft spacecraft1;
//    private RoverMission rover1;
//    private SatelliteMission satellite1;
    
    @Before
    public void setUp() {
        crew1 = new Crew("John Smith, Jane Doe", 2, "Pilot");
        crew2 = new Crew("Mike Johnson", 1, "Engineer");
    }
    
    // ==================== PowerSystem Tests ====================
    
    @Test
    public void test01_PowerSystemConstructorInitializesAllFields() {
        PowerSystem ps = new PowerSystem(85.5, 92.0, 4.5, 1200.0);
        assertEquals(85.5, ps.getBatteryLevel(), 0.01);
        assertEquals(92.0, ps.getSolarPanelEfficiency(), 0.01);
        assertEquals(4.5, ps.getPowerConsumptionRate(), 0.01);
        assertEquals(1200.0, ps.getMaxCapacity(), 0.01);
    }
    
    @Test
    public void test02_PowerSystemCopyConstructorCreatesDeepCopy() {
        PowerSystem original = new PowerSystem(75.0, 90.0, 5.0, 1000.0);
        PowerSystem copy = new PowerSystem(original);
        
        assertEquals(75.0, copy.getBatteryLevel(), 0.01);
        
        // Modify original
        original.setBatteryLevel(50.0);
        
        // Copy should remain unchanged (deep copy)
        assertEquals(75.0, copy.getBatteryLevel(), 0.01);
    }
          
    @Test
    public void test04_PowerSystemChargeBatteryIncreasesLevel() {
        PowerSystem ps = new PowerSystem(50.0, 95.0, 5.0, 1000.0);
        ps.chargeBattery(30.0);
        assertEquals(80.0, ps.getBatteryLevel(), 0.01);
    }
    
    @Test
    public void test05_PowerSystemConsumePowerDecreasesLevel() {
        PowerSystem ps = new PowerSystem(80.0, 95.0, 5.0, 1000.0);
        ps.consumePower(25.0);
        assertEquals(55.0, ps.getBatteryLevel(), 0.01);
    }
           
    // ==================== Crew Tests ====================
    
    @Test
    public void test08_CrewConstructorInitializesAllFields() {
        Crew crew = new Crew("Alice, Bob, Charlie", 3, "Science Team");
        assertEquals("Alice, Bob, Charlie", crew.getCrewMembers());
        assertEquals(3, crew.getCrewSize());
        assertEquals("Science Team", crew.getMissionRole());
    }
    
    @Test
    public void test09_CrewCopyConstructorCreatesIndependentCopy() {
        Crew original = new Crew("Tom, Jerry", 2, "Navigation");
        Crew copy = new Crew(original);
        
        assertEquals(original.getCrewMembers(), copy.getCrewMembers());
        
        // Modify original
        original.setCrewMembers("Tom, Jerry, Spike");
        
        // Copy should remain unchanged
        assertEquals("Tom, Jerry", copy.getCrewMembers());
    }
    
    @Test
    public void test10_CrewSettersModifyValues() {
        Crew crew = new Crew("John", 1, "Pilot");
        crew.setCrewMembers("John, Sarah");
        crew.setCrewSize(2);
        crew.setMissionRole("Command Team");
        
        assertEquals("John, Sarah", crew.getCrewMembers());
        assertEquals(2, crew.getCrewSize());
        assertEquals("Command Team", crew.getMissionRole());
    }
       
    @Test
    public void test14_CrewCanExistIndependentlyDemonstratesAggregation() {
        Crew independentCrew = new Crew("John Doe", 1, "Specialist");
        assertNotNull(independentCrew);
        assertEquals("John Doe", independentCrew.getCrewMembers());
        // Crew exists before being assigned to any spacecraft
    }
    
    // ==================== Spacecraft Tests ====================
    
    @Test
    public void test15_SpacecraftDefaultConstructorCreatesValidObject() {
        Spacecraft sc = new Spacecraft();
        assertEquals("UNKNOWN", sc.getMissionID());
        assertEquals("TBD", sc.getLaunchDate());
        assertEquals(0.0, sc.getFuelCapacity(), 0.01);
        assertEquals("Planning", sc.getCurrentStatus());
        assertNotNull(sc.getPowerSystem()); // PowerSystem created (composition)
        assertNull(sc.getCrew()); // Crew not assigned (aggregation)
    }
         
    @Test
    public void test17_SpacecraftCompositionPowerSystemCreatedInternally() {
        Spacecraft sc = new Spacecraft("SC-002", "2025-02-01", 6000.0, "Active", 180, crew1);
        PowerSystem ps = sc.getPowerSystem();
        
        assertNotNull(ps);
        // PowerSystem should have default values set internally
        assertEquals(100.0, ps.getBatteryLevel(), 0.01);
    }
    
     @Test
    public void test19_SpacecraftCopyConstructorShallowCopiesCrew() {
        Spacecraft original = new Spacecraft("SC-004", "2025-04-01", 5500.0, "Ready", 150, crew1);
        Spacecraft copy = new Spacecraft(original);
        
        // Both should reference the same crew object (shallow copy - aggregation)
        assertSame(original.getCrew(), copy.getCrew());
        
        // Modifying crew in original affects copy
        original.getCrew().setMissionRole("Updated Role");
        assertEquals("Updated Role", copy.getCrew().getMissionRole());
    }
    
    @Test
    public void test22_SpacecraftLaunchMethodChangesStatus() {
        Spacecraft sc = new Spacecraft("SC-007", "2025-07-01", 5200.0, "Ready", 180, crew1);
        sc.launch();
        assertEquals("Active", sc.getCurrentStatus());
    }
    
    @Test
    public void test23_SpacecraftRefuelIncreasesCapacity() {
        Spacecraft sc = new Spacecraft("SC-008", "2025-08-01", 3000.0, "Active", 90, crew1);
        sc.refuel(1500.0);
        assertEquals(4500.0, sc.getFuelCapacity(), 0.01);
    }
      
    @Test
    public void test26_SpacecraftCrewAggregationMultipleSpacecraftShareCrew() {
        Crew sharedCrew = new Crew("Shared Team", 3, "Multi-Mission");
        Spacecraft sc1 = new Spacecraft("SC-011", "2025-11-01", 4000.0, "Ready", 100, sharedCrew);
        Spacecraft sc2 = new Spacecraft("SC-012", "2025-12-01", 4200.0, "Ready", 120, sharedCrew);
        
        // Both spacecraft reference the same crew (aggregation)
        assertSame(sc1.getCrew(), sc2.getCrew());
    }
    
        
    // ==================== RoverMission Tests ====================
    
    @Test
    public void test28_RoverMissionConstructorInitializesAllFields() {
        RoverMission rover = new RoverMission("RV-001", "2025-01-20", 8000.0, "Ready", 
                                              720, crew1, "Mars", "Rocky", 0, 15.5);
        
        assertEquals("RV-001", rover.getMissionID());
        assertEquals("2025-01-20", rover.getLaunchDate());
        assertEquals(8000.0, rover.getFuelCapacity(), 0.01);
        assertEquals("Mars", rover.getPlanetName());
        assertEquals("Rocky", rover.getTerrainType());
        assertEquals(0, rover.getSamplesCollected());
        assertEquals(15.5, rover.getMaxSpeed(), 0.01);
    }
    
    @Test
    public void test30_RoverMissionInheritsPowerSystemComposition() {
        RoverMission rover = new RoverMission("RV-003", "2025-03-15", 9000.0, "Standby", 
                                              500, crew1, "Europa", "Icy", 0, 8.5);
        
        PowerSystem ps = rover.getPowerSystem();
        assertNotNull(ps);
        assertEquals(100.0, ps.getBatteryLevel(), 0.01); // Default value from composition
    }
    
    @Test
    public void test31_RoverMissionInheritsCrewAggregation() {
        Crew roverCrew = new Crew("Rover Team Alpha", 4, "Surface Exploration");
        RoverMission rover1 = new RoverMission("RV-004", "2025-04-01", 8500.0, "Ready", 
                                               450, roverCrew, "Titan", "Methane Lakes", 0, 10.0);
        RoverMission rover2 = new RoverMission("RV-005", "2025-05-01", 8700.0, "Ready", 
                                               480, roverCrew, "Mars", "Desert", 0, 14.0);
        
        // Both rovers can share the same crew (aggregation inherited from Spacecraft)
        assertSame(rover1.getCrew(), rover2.getCrew());
    }
      
    @Test
    public void test33_RoverMissionCopyConstructorShallowCopiesCrew() {
        RoverMission original = new RoverMission("RV-007", "2025-07-01", 8200.0, "Ready", 
                                                 400, crew1, "Enceladus", "Icy Geysers", 8, 7.0);
        RoverMission copy = new RoverMission(original);
        
        // Both should reference the same crew (shallow copy - aggregation)
        assertSame(original.getCrew(), copy.getCrew());
    }
          
    @Test
    public void test35_RoverMissionCollectSampleIncrementsSamples() {
        RoverMission rover = new RoverMission("RV-009", "2025-09-01", 8100.0, "Active", 
                                              550, crew1, "Mars", "Canyon", 10, 13.0);
        
        rover.collectSample();
        assertEquals(11, rover.getSamplesCollected());
        
        rover.collectSample();
        rover.collectSample();
        assertEquals(13, rover.getSamplesCollected());
    }
    
    
    @Test
    public void test38_RoverMissionUsesSuper() {
        RoverMission rover = new RoverMission("RV-012", "2025-12-01", 7700.0, "Standby", 
                                              380, crew2, "Callisto", "Frozen", 0, 6.5);
        
        // Verify that inherited fields are accessible (super was used)
        assertEquals("RV-012", rover.getMissionID());
        assertEquals(7700.0, rover.getFuelCapacity(), 0.01);
        assertNotNull(rover.getPowerSystem());
    }
    
    @Test
    public void test39_RoverMissionPolymorphismAsSpacecraft() {
        Spacecraft spacecraft = new RoverMission("RV-013", "2025-11-15", 8400.0, "Ready", 
                                                 600, crew1, "Mars", "Highland", 20, 14.5);
        
        // Can be treated as Spacecraft (polymorphism)
        assertEquals("RV-013", spacecraft.getMissionID());
        spacecraft.launch();
        assertEquals("Active", spacecraft.getCurrentStatus());
    }
    
    @Test
    public void test40_RoverMissionDriveDistanceMethod() {
        RoverMission rover = new RoverMission("RV-014", "2025-10-20", 8000.0, "Active", 
                                              450, crew1, "Mars", "Valley", 5, 12.5);
        
        // Just verify the method can be called without errors
        rover.driveDistance(10.5);
        assertEquals(12.5, rover.getMaxSpeed(), 0.01);
    }
   
    // ==================== SatelliteMission Tests ====================
    
    @Test
    public void test43_SatelliteMissionConstructorInitializesAllFields() {
        SatelliteMission sat = new SatelliteMission("SAT-001", "2025-01-25", 3000.0, "Ready", 
                                                     1825, crew1, 400.0, "Geostationary", 5, 50000.0);
        
        assertEquals("SAT-001", sat.getMissionID());
        assertEquals("2025-01-25", sat.getLaunchDate());
        assertEquals(3000.0, sat.getFuelCapacity(), 0.01);
        assertEquals(400.0, sat.getOrbitAltitude(), 0.01);
        assertEquals("Geostationary", sat.getOrbitType());
        assertEquals(5, sat.getResolutionQuality());
        assertEquals(50000.0, sat.getCoverageArea(), 0.01);
    }
    
    @Test
    public void test44_SatelliteMissionInheritsSpacecraftProperties() {
        SatelliteMission sat = new SatelliteMission("SAT-002", "2025-02-15", 2800.0, "Active", 
                                                     1095, crew2, 600.0, "Polar", 3, 75000.0);
        
        // Test inherited properties
        assertEquals("SAT-002", sat.getMissionID());
        assertEquals(1095, sat.getMissionDuration());
        assertNotNull(sat.getPowerSystem()); // Inherited composition
        assertEquals(crew2, sat.getCrew()); // Inherited aggregation
    }
    
    @Test
    public void test47_SatelliteMissionCopyConstructorDeepCopiesPowerSystem() {
        SatelliteMission original = new SatelliteMission("SAT-006", "2025-06-15", 3300.0, "Active", 
                                                          2190, crew1, 700.0, "Medium Earth Orbit", 7, 90000.0);
        SatelliteMission copy = new SatelliteMission(original);
        
        // Modify original's power system
        original.getPowerSystem().setBatteryLevel(55.0);
        
        // Copy's power system should remain unchanged (deep copy - composition)
        assertEquals(100.0, copy.getPowerSystem().getBatteryLevel(), 0.01);
    }
    
    @Test
    public void test49_SatelliteMissionSettersModifySatelliteSpecificFields() {
        SatelliteMission sat = new SatelliteMission("SAT-008", "2025-08-05", 3000.0, "Active", 
                                                     1460, crew2, 400.0, "LEO", 3, 50000.0);
        
        sat.setOrbitAltitude(550.0);
        sat.setOrbitType("Sun-Synchronous");
        sat.setResolutionQuality(2);
        sat.setCoverageArea(70000.0);
        
        assertEquals(550.0, sat.getOrbitAltitude(), 0.01);
        assertEquals("Sun-Synchronous", sat.getOrbitType());
        assertEquals(2, sat.getResolutionQuality());
        assertEquals(70000.0, sat.getCoverageArea(), 0.01);
    }
           
    @Test
    public void test52_SatelliteMissionUsesSuper() {
        SatelliteMission sat = new SatelliteMission("SAT-011", "2025-11-20", 2950.0, "Standby", 
                                                     1095, crew2, 420.0, "LEO", 3, 45000.0);
        
        // Verify that inherited fields are accessible (super was used)
        assertEquals("SAT-011", sat.getMissionID());
        assertEquals(2950.0, sat.getFuelCapacity(), 0.01);
        assertNotNull(sat.getPowerSystem());
    }
       
    
    @Test
    public void test55_SatelliteMissionAdjustOrbitMethod() {
        SatelliteMission sat = new SatelliteMission("SAT-014", "2025-09-15", 2900.0, "Active", 
                                                     730, crew2, 450.0, "LEO", 3, 55000.0);
        
        // Verify method can be called
        sat.adjustOrbit();
        assertEquals(450.0, sat.getOrbitAltitude(), 0.01);
    }
   
    // ==================== Integration Tests ====================
    
    @Test
    public void test58_InheritancePolymorphismWithArray() {
        Spacecraft[] missions = new Spacecraft[3];
        missions[0] = new Spacecraft("SC-100", "2025-01-01", 5000.0, "Ready", 365, crew1);
        missions[1] = new RoverMission("RV-100", "2025-02-01", 8000.0, "Ready", 500, crew1, 
                                       "Mars", "Rocky", 0, 15.0);
        missions[2] = new SatelliteMission("SAT-100", "2025-03-01", 3000.0, "Ready", 1095, crew1, 
                                           400.0, "LEO", 5, 50000.0);
        
        // All can be stored in Spacecraft array (polymorphism)
        for (Spacecraft mission : missions) {
            assertNotNull(mission);
            assertNotNull(mission.getPowerSystem());
        }
    }
    
    @Test
    public void test59_CompositionLifecyclePowerSystemBoundToSpacecraft() {
        Spacecraft sc = new Spacecraft("SC-200", "2025-05-01", 4500.0, "Ready", 200, crew1);
        PowerSystem ps1 = sc.getPowerSystem();
        PowerSystem ps2 = sc.getPowerSystem();
        
        // Same PowerSystem instance (cannot be replaced)
        assertSame(ps1, ps2);
    }
    
   
    // ==================== Display Method Tests ====================
    
    @Test
    public void test61_SpacecraftDisplayInfoContainsAllRequiredFields() {
        Crew testCrew = new Crew("Test Pilot", 1, "Commander");
        Spacecraft sc = new Spacecraft("SC-400", "2025-07-15", 6000.0, "Operational", 250, testCrew);
        
        String displayOutput = sc.displayInfo();
        
        // Verify all spacecraft fields are present
        assertTrue("Display should contain mission ID", displayOutput.contains("SC-400"));
        assertTrue("Display should contain launch date", displayOutput.contains("2025-07-15"));
        assertTrue("Display should contain fuel capacity", displayOutput.contains("6000"));
        assertTrue("Display should contain status", displayOutput.contains("Operational"));
        assertTrue("Display should contain duration", displayOutput.contains("250"));
        
        // Verify PowerSystem info is included (composition)
        assertTrue("Display should contain PowerSystem info", displayOutput.contains("PowerSystem"));
        assertTrue("Display should contain battery info", displayOutput.contains("Battery"));
        
        // Verify Crew info is included (aggregation)
        assertTrue("Display should contain Crew info", displayOutput.contains("Crew"));
        assertTrue("Display should contain crew member name", displayOutput.contains("Test Pilot"));
        assertTrue("Display should contain crew role", displayOutput.contains("Commander"));
    }
    
    @Test
    public void test62_RoverMissionDisplayInfoIncludesInheritedAndRoverSpecificData() {
        Crew roverCrew = new Crew("Mars Explorer Team", 3, "Surface Operations");
        RoverMission rover = new RoverMission("RV-200", "2025-08-20", 9000.0, "Active", 
                                              600, roverCrew, "Mars", "Rocky Plains", 15, 18.5);
        
        String displayOutput = rover.displayInfo();
        
        // Verify inherited Spacecraft fields
        assertTrue("Display should contain mission ID", displayOutput.contains("RV-200"));
        assertTrue("Display should contain launch date", displayOutput.contains("2025-08-20"));
        assertTrue("Display should contain fuel capacity", displayOutput.contains("9000"));
        assertTrue("Display should contain status", displayOutput.contains("Active"));
        assertTrue("Display should contain duration", displayOutput.contains("600"));
        
        // Verify inherited PowerSystem info (composition)
        assertTrue("Display should contain PowerSystem", displayOutput.contains("PowerSystem"));
        
        // Verify inherited Crew info (aggregation)
        assertTrue("Display should contain Crew info", displayOutput.contains("Mars Explorer Team"));
        
        // Verify RoverMission-specific fields
        assertTrue("Display should contain RoverMission identifier", displayOutput.contains("RoverMission"));
        assertTrue("Display should contain planet name", displayOutput.contains("Mars"));
        assertTrue("Display should contain terrain type", displayOutput.contains("Rocky Plains"));
        assertTrue("Display should contain samples collected", displayOutput.contains("15"));
        assertTrue("Display should contain max speed", displayOutput.contains("18.5") || displayOutput.contains("18.50"));
        
        // Verify proper method overriding - should have both Spacecraft and RoverMission sections
        int spacecraftCount = displayOutput.split("Spacecraft").length - 1;
        int roverCount = displayOutput.split("RoverMission").length - 1;
        assertTrue("Display should contain Spacecraft section", spacecraftCount >= 1);
        assertTrue("Display should contain RoverMission section", roverCount >= 1);
    }
    
    @Test
    public void test63_SatelliteMissionDisplayInfoIncludesInheritedAndSatelliteSpecificData() {
        Crew satelliteCrew = new Crew("Satellite Control Team", 2, "Remote Operators");
        SatelliteMission satellite = new SatelliteMission("SAT-200", "2025-09-25", 3500.0, "Operational", 
                                                           2000, satelliteCrew, 650.0, "Sun-Synchronous", 3, 85000.0);
        
        String displayOutput = satellite.displayInfo();
        
        // Verify inherited Spacecraft fields
        assertTrue("Display should contain mission ID", displayOutput.contains("SAT-200"));
        assertTrue("Display should contain launch date", displayOutput.contains("2025-09-25"));
        assertTrue("Display should contain fuel capacity", displayOutput.contains("3500"));
        assertTrue("Display should contain status", displayOutput.contains("Operational"));
        assertTrue("Display should contain duration", displayOutput.contains("2000"));
        
        // Verify inherited PowerSystem info (composition)
        assertTrue("Display should contain PowerSystem", displayOutput.contains("PowerSystem"));
        assertTrue("Display should contain battery information", displayOutput.contains("Battery"));
        
        // Verify inherited Crew info (aggregation)
        assertTrue("Display should contain Crew info", displayOutput.contains("Satellite Control Team"));
        assertTrue("Display should contain crew role", displayOutput.contains("Remote Operators"));
        
        // Verify SatelliteMission-specific fields
        assertTrue("Display should contain SatelliteMission identifier", displayOutput.contains("SatelliteMission"));
        assertTrue("Display should contain orbit altitude", displayOutput.contains("650"));
        assertTrue("Display should contain orbit type", displayOutput.contains("Sun-Synchronous"));
        assertTrue("Display should contain resolution quality", displayOutput.contains("3"));
        assertTrue("Display should contain coverage area", displayOutput.contains("85000"));
        
        // Verify proper method overriding - should have both Spacecraft and SatelliteMission sections
        int spacecraftCount = displayOutput.split("Spacecraft").length - 1;
        int satelliteCount = displayOutput.split("SatelliteMission").length - 1;
        assertTrue("Display should contain Spacecraft section", spacecraftCount >= 1);
        assertTrue("Display should contain SatelliteMission section", satelliteCount >= 1);
    }
  
    
}