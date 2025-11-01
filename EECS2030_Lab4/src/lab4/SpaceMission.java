package lab4;

/**
* SpaceMission - Main class containing all space mission related classes.
* This file demonstrates inheritance, composition, and aggregation relationships.
* 
* @author Dr. Sukhwant Sagar
* @author Aaditya Karamchandani
*/
public class SpaceMission {
   // Empty main class - all functionality is in the inner classes below

}

/**
 * PowerSystem class - represents the power management system of a spacecraft.
 * This class demonstrates COMPOSITION - it cannot exist without a Spacecraft.
 * The PowerSystem manages all energy-related operations including battery management,
 * solar panel efficiency tracking, and power consumption monitoring.
 * 
 * @author Dr. Sukhwant Sagar
 */
class PowerSystem {
    //Attributes
    private double batteryLevel;
    private double solarPanelEfficiency;
    private double powerConsumptionRate;
    private double maxCapacity;

    /**
     * Overloaded constructor that initializes all fields of the PowerSystem.
     * 
     * @param batteryLevel the current battery level as a percentage (0-100)
     * @param solarPanelEfficiency the efficiency of solar panels as a percentage (0-100)
     * @param powerConsumptionRate the rate of power consumption in kilowatts
     * @param maxCapacity the maximum battery capacity in kilowatt-hours
     */ 
    public PowerSystem(double batteryLevel, double solarPanelEfficiency, double powerConsumptionRate, double maxCapacity) {
      this.batteryLevel = batteryLevel;
      this.solarPanelEfficiency = solarPanelEfficiency;
      this.powerConsumptionRate = powerConsumptionRate;
      this.maxCapacity = maxCapacity;
    }
    
	  
	
    
    /**
     * Copy constructor that creates a deep copy of another PowerSystem object.
     * This ensures that modifications to the original do not affect the copy.
     * 
     * @param other the PowerSystem object to copy
     */
    
	  public PowerSystem(PowerSystem other) {
      this.batteryLevel = other.batteryLevel;
      this.solarPanelEfficiency = other.solarPanelEfficiency;
      this.powerConsumptionRate = other.powerConsumptionRate;
      this.maxCapacity = other.maxCapacity;
    }
	
	
	
    /**
     * Gets the current battery level.
     * 
     * @return the battery level as a percentage
     */
   
	  public double getBatteryLevel() {
      return this.batteryLevel;
    }
	
    
    /**
     * Sets the battery level to a new value.
     * 
     * @param batteryLevel the new battery level as a percentage
     */
    public void setBatteryLevel(double batteryLevel){
      this.batteryLevel = batteryLevel;

    }

	
    /**
     * Gets the solar panel efficiency.
     * 
     * @return the solar panel efficiency as a percentage
     */
    public double getSolarPanelEfficiency(){
      return this.solarPanelEfficiency;
    }

	
    /**
     * Sets the solar panel efficiency to a new value.
     * 
     * @param solarPanelEfficiency the new solar panel efficiency as a percentage
     */
    public void setSolarPanelEfficiency(double solarPanelEfficiency) {
      this.solarPanelEfficiency = solarPanelEfficiency;
    }

	
    /**
     * Gets the power consumption rate.
     * 
     * @return the power consumption rate in kilowatts
     */
    public double getPowerConsumptionRate(){
      return this.powerConsumptionRate;
    }

	
    
    /**
     * Sets the power consumption rate to a new value.
     * 
     * @param powerConsumptionRate the new power consumption rate in kilowatts
     */
    public void setPowerConsumptionRate(double powerConsumptionRate) {
      this.powerConsumptionRate = powerConsumptionRate;
    }
    /**
     * Gets the maximum battery capacity.
     * 
     * @return the maximum capacity in kilowatt-hours
     */
    public double getMaxCapacity(){
      return this.maxCapacity;
    }

	
    
    /**
     * Sets the maximum battery capacity to a new value.
     * 
     * @param maxCapacity the new maximum capacity in kilowatt-hours
     */
    public void setMaxCapacity(double maxCapacity) {
      this.maxCapacity = maxCapacity;
    }

	
    /**
     * Charges the battery by a specified amount using solar panels or external power sources.
     * The battery level cannot exceed the maximum capacity to prevent overcharging damage.
     * Formula: batteryLevel = minimum of (batteryLevel + amount, maxCapacity)
     * 
     * @param amount the amount of charge to add as a percentage
     */
    public void chargeBattery(double amount){
      this.batteryLevel = Math.min(this.batteryLevel + amount, maxCapacity);
    }

	
    
    /**
     * Consumes power from the battery to operate spacecraft systems and equipment.
     * The battery level cannot go below zero, representing complete power depletion.
     * Formula: batteryLevel = maximum of (batteryLevel - amount, 0)
     * 
     * @param amount the amount of power to consume as a percentage
     */
    public void consumePower(double amount){
      this.batteryLevel = Math.max(this.batteryLevel - amount, 0);
    }

	
    
    /**
     * Checks the current power status based on battery level thresholds for mission safety.
     * Returns status classification to indicate operational readiness of the spacecraft.
     * Status Rules: >75% = Optimal, >25% = Moderate, ≤25% = Critical
     * 
     * @return "Optimal" if battery > 75%, "Moderate" if battery > 25%, "Critical" otherwise
     */
    public String checkPowerStatus() {
      if(this.batteryLevel > 75) {
        return "Optimal";
      }
      else if (this.batteryLevel > 25) {
        return "Moderate";
      }
      else {
        return "Critical";
      }
    }

	
    
    /**PROVIDED: 
     * Displays comprehensive power system information in a formatted string for monitoring.
     * Provides complete overview of battery status, efficiency, consumption, and capacity metrics.
     * 
     * @return a formatted string containing all power system details
     */
    public String displayPowerInfo() {
        return String.format("PowerSystem [Battery: %.2f%%, Solar Efficiency: %.2f%%, " +
                           "Consumption: %.2fkW, Max Capacity: %.2fkWh]",
                           batteryLevel, solarPanelEfficiency, powerConsumptionRate, maxCapacity);
    }
}

/**
 * Crew class - represents the crew members assigned to a spacecraft.
 * This class demonstrates AGGREGATION - it can exist independently of Spacecraft.
 * Crew members can be reassigned to different missions and continue to exist
 * even after a particular spacecraft mission ends.
 * 
 * @author Dr. Sukhwant Sagar
 * 
 */
class Crew {
    //Attributes
    private String crewMembers;
    private int crewSize;
    private String missionRole;
    /**
     * Overloaded constructor that initializes all fields of the Crew.
     * 
     * @param crewMembers a comma-separated string of crew member names
     * @param crewSize the number of crew members
     * @param missionRole the primary role of the crew (e.g., "Pilot", "Engineer")
     */
    public Crew(String crewMembers, int crewSize, String missionRole) {
      this.crewMembers = crewMembers;
      this.crewSize = crewSize;
      this.missionRole = missionRole;
    }
	
    
    /**
     * Copy constructor that creates a copy of another Crew object.
     * 
     * @param other the Crew object to copy
     */
    public Crew(Crew other) {
      this.crewMembers = other.crewMembers;
      this.crewSize = other.crewSize;
      this.missionRole = other.missionRole;
    }

	
    
    /**
     * Gets the crew members as a comma-separated string.
     * 
     * @return the crew members string
     */
    public String getCrewMembers() {
      return this.crewMembers;
    }

	
    /**
     * Sets the crew members string.
     * 
     * @param crewMembers the new crew members string
     */
    public void setCrewMembers(String crewMembers) {
      this.crewMembers = crewMembers;
    }

	
    /**
     * Gets the crew size.
     * 
     * @return the number of crew members
     */
    public int getCrewSize() {
      return this.crewSize;
    }

	
    /**
     * Sets the crew size.
     * 
     * @param crewSize the new crew size
     */
    public void setCrewSize(int crewSize) {
      this.crewSize = crewSize;
    }

	
    
    /**
     * Gets the mission role of the crew.
     * 
     * @return the mission role
     */
    public String getMissionRole() {
      return this.missionRole;
    }

	
    /**
     * Sets the mission role of the crew.
     * 
     * @param missionRole the new mission role
     */
    public void setMissionRole(String missionRole) {
      this.missionRole = missionRole;
    }
	

    
    /**
     * Assigns a new member to the crew by appending their name to the crew list and incrementing the total count.
     * Handles comma-separated formatting automatically, ensuring proper string structure whether the crew is empty
     * or already populated.
     * Formula: crewSize = crewSize + 1
     * 
     * @param memberName the name of the member to assign
     */
    public void assignMember(String name){
      this.crewMembers = this.crewMembers + ", " + name;
      this.crewSize++;
    }
	

    
    /**
     * Removes a member from the crew by deleting their name from the crew list and decrementing the total count.
     * Cleans up string formatting by removing extra commas and whitespace to maintain proper comma-separated structure.
     * Formula: crewSize = max(crewSize - 1, 0)
     * 
     * @param memberName the name of the member to remove
     */
    public void removeMember(String name){
      if (this.crewMembers.contains(name)) {
        String[] members = this.crewMembers.split(", ");
        String newCrewMembers = "";
        for (int i = 0; i < members.length; i++) {
            if (!members[i].equals(name)) {
                if (newCrewMembers.length() > 0) {
                    newCrewMembers += ", ";
                }
                newCrewMembers += members[i];
            }
        }
        this.crewMembers = newCrewMembers;
        this.crewSize = Math.max(this.crewSize - 1, 0);
      }
    }

	
    /**
     * Gets the current count of crew members.
     * 
     * @return the crew size
     */
    public int getCrewCount(){
      return this.crewSize;
    }


    
    /**PROVIDED: 
     * Displays comprehensive crew information in a formatted string.
     * 
     * @return a formatted string containing all crew details
     */
    public String displayCrewInfo() {
        return String.format("Crew [Members: %s, Size: %d, Role: %s]",
                           crewMembers, crewSize, missionRole);
    }
}

/**
 * Spacecraft class - the superclass for all space missions.
 * Demonstrates COMPOSITION with PowerSystem and AGGREGATION with Crew.
 * This class serves as the parent class for specialized mission types
 * like RoverMission and SatelliteMission.
 * 
 * <p>Composition: PowerSystem is created internally and cannot exist without Spacecraft.
 * Aggregation: Crew is passed as a parameter and can exist independently.</p>
 * 
 * @author Dr. Sukhwant Sagar
 */
class Spacecraft {
   //Attributes
    private String missionID;
    private String launchDate;
    private double fuelCapacity;
    private String currentStatus;
    private int missionDuration;
    private PowerSystem powerSystem;
    private Crew crew;
    /**
     * Default constructor that initializes all fields to default values.
     * Creates a new PowerSystem internally (composition).
     * Crew is set to null initially (aggregation).
     */
    public Spacecraft(){
      this.missionID = "UNKNOWN";
      this.launchDate = "TBD";
      this.fuelCapacity = 0.0;
      this.currentStatus = "Planning";
      this.powerSystem = new PowerSystem(0.0, 0.0, 0.0, 0.0);
      this.crew = null;
    }
	
    
    /**
     * Overloaded constructor that initializes all fields with specified values.
     * Creates a new PowerSystem internally (composition).
     * Accepts a Crew reference as a parameter (aggregation).
     * 
     * @param missionID the unique mission identifier
     * @param launchDate the scheduled launch date
     * @param fuelCapacity the fuel capacity in metric tons
     * @param currentStatus the current operational status
     * @param missionDuration the mission duration in days
     * @param crew the crew assigned to this mission (can be null)
     */
    public Spacecraft(String missionID, String launchDate, double fuelCapacity, String currentStatus, int missionDuration, Crew crew) {
      this.missionID = missionID;
      this.launchDate = launchDate;
      this.fuelCapacity = fuelCapacity;
      this.missionDuration = missionDuration;
      this.currentStatus = currentStatus;
      this.powerSystem = new PowerSystem(100.0, 0.0, 0.0, 100.0);
      this.crew = crew;
    }

	
    
    /**
     * Copy constructor that creates a copy of another Spacecraft.
     * Performs deep copy for PowerSystem (composition).
     * Performs shallow copy for Crew (aggregation).
     * 
     * @param other the Spacecraft object to copy
     */
    public Spacecraft(Spacecraft other) {
      this.missionID = other.missionID;
      this.launchDate = other.launchDate;
      this.fuelCapacity = other.fuelCapacity;
      this.missionDuration = other.missionDuration;
      this.currentStatus = other.currentStatus;
      this.powerSystem = new PowerSystem(other.powerSystem);
      this.crew = other.crew;

     
    }

	
    
    /**
     * Gets the mission ID.
     * 
     * @return the mission identifier
     */
    public String getMissionID() {
      return this.missionID;
    }

	
    /**
     * Sets the mission ID.
     * 
     * @param missionID the new mission identifier
     */ 
    public void setMissionID(String missionID) {
      this.missionID = missionID;
    }


	
    
    /**
     * Gets the launch date.
     * 
     * @return the launch date
     */ 
    public String getLaunchDate() {
      return this.launchDate;
    }


	
    
    /**
     * Sets the launch date.
     * 
     * @param launchDate the new launch date
     */ 
    public void setLaunchDate(String launchDate) {
      this.launchDate = launchDate;
    }


	
    
    /**
     * Gets the fuel capacity.
     * 
     * @return the fuel capacity in metric tons
     */ 
    public double getFuelCapacity() {
      return this.fuelCapacity;
    }


	
    
    /**
     * Sets the fuel capacity.
     * 
     * @param fuelCapacity the new fuel capacity in metric tons
     */ 
    public void setFuelCapacity(double fuelCapacity) {
      this.fuelCapacity = fuelCapacity;
    }


	
    
    /**
     * Gets the current operational status.
     * 
     * @return the current status
     */ 
    public String getCurrentStatus() {
      return this.currentStatus;
    }


	
    
    /**
     * Sets the current operational status.
     * 
     * @param currentStatus the new status
     */ 
    public void setCurrentStatus(String currentStatus) {
      this.currentStatus = currentStatus;
    }


	
    
    /**
     * Gets the mission duration.
     * 
     * @return the mission duration in days
     */ 
    public int getMissionDuration() {
      return this.missionDuration;
    }


	
    
    /**
     * Sets the mission duration.
     * 
     * @param missionDuration the new mission duration in days
     */ 
    public void setMissionDuration(int missionDuration) {
      this.missionDuration = missionDuration;
    }


	
    
    /**
     * Gets the power system of this spacecraft.
     * Note: No setter is provided as PowerSystem is composed (cannot be replaced).
     * 
     * @return the PowerSystem object
     */ 
    public PowerSystem getPowerSystem() {
      return this.powerSystem;
    }


	
    
    /**
     * Gets the crew assigned to this spacecraft.
     * 
     * @return the Crew object, or null if no crew is assigned
     */ 
    public Crew getCrew() {
      return this.crew;
    }


	
    
    /**
     * Sets or reassigns the crew for this spacecraft.
     * This demonstrates aggregation - crew can be reassigned.
     * 
     * @param crew the new Crew to assign
     */ 
    public void setCrew(Crew crew) {
      this.crew = crew;
    }


	
    
    /**
     * Launches the spacecraft by changing its status to "Active".
     */ 
    public void launch() {
      this.currentStatus = "Active";
    }


	
    
    /**PROVIDED: 
     * Transmits data from the spacecraft.
     * Prints a message indicating data transmission.
     */
    public void transmitData() {
        System.out.println("Transmitting data from mission " + missionID);
    }
    
    /**
     * Calculates the total distance based on mission duration.
     * Simple calculation: duration * 1000 km.
     * 
     * @return the calculated distance in kilometers
     */ 
    public int totalDistance() {
      return this.missionDuration * 1000;
    }


    
    /**
     * Refuels the spacecraft by adding to the fuel capacity.
     * 
     * @param amount the amount of fuel to add in metric tons
     */
    public void refuel(double amount) {
        fuelCapacity += amount;
    }
    
    /**PROVIDED: 
     * Displays comprehensive information about the spacecraft.
     * Includes spacecraft details, power system info, and crew info.
     * 
     * @return a formatted string containing all spacecraft information
     */
    public String displayInfo() {
        String crewInfo = (crew != null) ? crew.displayCrewInfo() : "No crew assigned";
        return String.format("Spacecraft [ID: %s, Launch: %s, Fuel: %.2f, Status: %s, " +
                           "Duration: %d days]\n%s\n%s",
                           missionID, launchDate, fuelCapacity, currentStatus, 
                           missionDuration, powerSystem.displayPowerInfo(), crewInfo);
    }
}

/**
 * RoverMission class - subclass of Spacecraft for planetary surface exploration.
 * Extends Spacecraft and adds rover-specific attributes and behaviors.
 * Inherits composition relationship with PowerSystem and aggregation with Crew.
 * 
 * @author Dr. Sukhwant Sagar
 */
class RoverMission extends Spacecraft {
   //Attributes
    private String planetName;
    private String terrainType;
    private int sampleCollected;
    private double maxSpeed;
    /**
     * Overloaded constructor that initializes all fields including inherited ones.
     * Uses super() to call the parent constructor for Spacecraft fields.
     * 
     * @param missionID the unique mission identifier
     * @param launchDate the scheduled launch date
     * @param fuelCapacity the fuel capacity in metric tons
     * @param currentStatus the current operational status
     * @param missionDuration the mission duration in days
     * @param crew the crew assigned to this mission
     * @param planetName the name of the planet to explore
     * @param terrainType the type of terrain (e.g., "Rocky", "Sandy")
     * @param samplesCollected the number of samples collected
     * @param maxSpeed the maximum speed in km/h
     */
    public RoverMission(String missionID, String launchDate, double fuelCapacity, String currentStatus, int missionDuration, Crew crew, String planetName, String terrainType, int sampleCollected, double maxSpeed) {
      super(missionID, launchDate, fuelCapacity, currentStatus, missionDuration, crew);
      this.planetName = planetName;
      this.terrainType = terrainType;
      this.sampleCollected = sampleCollected;
      this.maxSpeed = maxSpeed;
    }
	
	
    
    /**
     * Copy constructor that creates a copy of another RoverMission.
     * Uses super() to copy Spacecraft fields (including deep copy of PowerSystem).
     * 
     * @param other the RoverMission object to copy
     */ 
    public RoverMission(RoverMission other) {
      super(other);
      this.planetName = other.planetName;
      this.terrainType = other.terrainType;
      this.sampleCollected = other.sampleCollected;
      this.maxSpeed = other.maxSpeed;
    }


	
    /**
     * Gets the planet name.
     * 
     * @return the name of the planet
     */
    public String getPlanetName() {
        return this.planetName;
    }


	
    
    /**
     * Sets the planet name.
     * 
     * @param planetName the new planet name
     */
    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }


	
    
    /**
     * Gets the terrain type.
     * 
     * @return the terrain type
     */
    public String getTerrainType() {
        return this.terrainType;
    }


	
    
    /**
     * Sets the terrain type.
     * 
     * @param terrainType the new terrain type
     */
    public void setTerrainType(String terrainType) {
        this.terrainType = terrainType;
    }


	
    
    /**
     * Gets the number of samples collected.
     * 
     * @return the samples collected count
     */
    public int getSamplesCollected() {
        return this.sampleCollected;
    }


	
    
    /**
     * Sets the number of samples collected.
     * 
     * @param samplesCollected the new samples count
     */
    public void setSampleCollected(int sampleCollected) {
        this.sampleCollected = sampleCollected;
    }


	
    
    /**
     * Gets the maximum speed of the rover.
     * 
     * @return the maximum speed in km/h
     */
    public double getMaxSpeed() {
        return this.maxSpeed;
    }


	
    
    /**
     * Sets the maximum speed of the rover.
     * 
     * @param maxSpeed the new maximum speed in km/h
     */
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }


	
    
    /**
     * Collects a sample and increments the samples collected count.
     */
    public void collectSample() {
        this.sampleCollected++;
    }


	
    
    /**
     * PROVIDED: Analyzes the terrain of the current location.
     * Prints analysis information about the terrain type and planet.
     */
    public void analyzeTerrain() {
        System.out.println("Analyzing " + terrainType + " terrain on " + planetName);
    }
    
    /**
     * PROVIDED: Deploys scientific instruments on the planetary surface.
     * Prints deployment information.
     */
    public void deployInstruments() {
        System.out.println("Deploying scientific instruments on " + planetName);
    }
    
    /**
     * PROVIDED: Drives the rover a specified distance.
     * Prints information about the distance traveled and maximum speed.
     * 
     * @param distance the distance to drive in kilometers
     */
    public void driveDistance(double distance) {
        System.out.println("Rover driving " + distance + " km at max speed " + maxSpeed + " km/h");
    }
    
    /**
     * Displays comprehensive information about the rover mission.
     * Overrides the parent method to include rover-specific details.
     * 
     * @return a formatted string containing all rover mission information
     */
    @Override
    public String displayInfo() {
        String roverInfo = String.format("RoverMission [Planet: %s, Terrain: %s, Samples Collected: %d, Max Speed: %.2f]",
                                       this.planetName, this.terrainType, this.sampleCollected, this.maxSpeed);
        return super.displayInfo() + "\n" + roverInfo;
    }
    
    
    
}

/**
 * SatelliteMission class - subclass of Spacecraft for orbital observation.
 * Extends Spacecraft and adds satellite-specific attributes and behaviors.
 * Inherits composition relationship with PowerSystem and aggregation with Crew.
 * 
 * @author Dr. Sukhwant Sagar
 */
class SatelliteMission extends Spacecraft {
    //Attributes
    private double orbitAltitude;
    private String orbitType;
    private int resolutionQuality;
    private double coverageArea;
    
    /**
     * Overloaded constructor that initializes all fields including inherited ones.
     * Uses super() to call the parent constructor for Spacecraft fields.
     * 
     * @param missionID the unique mission identifier
     * @param launchDate the scheduled launch date
     * @param fuelCapacity the fuel capacity in metric tons
     * @param currentStatus the current operational status
     * @param missionDuration the mission duration in days
     * @param crew the crew assigned to this mission
     * @param orbitAltitude the orbit altitude in kilometers
     * @param orbitType the type of orbit (e.g., "Geostationary", "Polar")
     * @param resolutionQuality the image resolution quality in meters per pixel
     * @param coverageArea the coverage area in square kilometers
     */
    public SatelliteMission(String missionID, String launchDate, double fuelCapacity, String currentStatus, int missionDuration, Crew crew, double orbitAltitude, String orbitType, int resolutionQuality, double coverageArea) {
        super(missionID, launchDate, fuelCapacity, currentStatus, missionDuration, crew);
        this.orbitAltitude = orbitAltitude;
        this.orbitType = orbitType;
        this.resolutionQuality = resolutionQuality;
        this.coverageArea = coverageArea;
    }

    /**
     * Copy constructor that creates a copy of another SatelliteMission.
     * Uses super() to copy Spacecraft fields (including deep copy of PowerSystem).
     * 
     * @param other the SatelliteMission object to copy
     */
    public SatelliteMission(SatelliteMission other) {
        super(other);
        this.orbitAltitude = other.orbitAltitude;
        this.orbitType = other.orbitType;
        this.resolutionQuality = other.resolutionQuality;
        this.coverageArea = other.coverageArea;
    }


	
	
    /**
     * Gets the orbit altitude.
     * 
     * @return the orbit altitude in kilometers
     */
    public double getOrbitAltitude() {
        return this.orbitAltitude;
    }


	
    
    /**
     * Sets the orbit altitude.
     * 
     * @param orbitAltitude the new orbit altitude in kilometers
     */
    public void setOrbitAltitude(double orbitAltitude) {
        this.orbitAltitude = orbitAltitude;
    }


	
    
    /**
     * Gets the orbit type.
     * 
     * @return the orbit type
     */
    public String getOrbitType() {
        return this.orbitType;
    }


	
    
    /**
     * Sets the orbit type.
     * 
     * @param orbitType the new orbit type
     */
    public void setOrbitType(String orbitType) {
        this.orbitType = orbitType;
    }


	
    
    /**
     * Gets the image resolution quality.
     * 
     * @return the resolution quality in meters per pixel
     */
    public int getResolutionQuality() {
        return this.resolutionQuality;
    }


	
    
    /**
     * Sets the image resolution quality.
     * 
     * @param resolutionQuality the new resolution quality in meters per pixel
     */
    public void setResolutionQuality(int resolutionQuality) {
        this.resolutionQuality = resolutionQuality;
    }


	
    
    /**
     * Gets the coverage area.
     * 
     * @return the coverage area in square kilometers
     */
    public double getCoverageArea() {
        return this.coverageArea;
    }


	
    
    /**
     * Sets the coverage area.
     * 
     * @param coverageArea the new coverage area in square kilometers
     */
    public void setCoverageArea(double coverageArea) {
        this.coverageArea = coverageArea;
    }


	
    
    /**PROVIDED: 
     * Captures an image with the satellite's imaging system.
     * Prints information about the image resolution.
     */
    public void captureImage() {
        System.out.println("Capturing image at " + resolutionQuality + "m resolution");
    }
    
    /**
     * PROVIDED: Adjusts the satellite's orbit.
     * Prints information about the orbit type and altitude.
     */
    public void adjustOrbit() {
        System.out.println("Adjusting " + orbitType + " orbit at " + orbitAltitude + " km");
    }
    
    /**
     * PROVIDED: Scans a specified region using the satellite's sensors.
     * Prints information about the coverage area.
     */
    public void scanRegion() {
        System.out.println("Scanning coverage area of " + coverageArea + " sq km");
    }
    
    /**PROVIDED: 
     * Transmits collected images back to ground stations.
     * Prints transmission information with the mission ID.
     */
    public void transmitImages() {
        System.out.println("Transmitting images from satellite " + getMissionID());
    }
    
    /**
     * Displays comprehensive information about the satellite mission.
     * Overrides the parent method to include satellite-specific details.
     * 
     * @return a formatted string containing all satellite mission information
     */
    @Override
    public String displayInfo() {
        String satelliteInfo = String.format("SatelliteMission [Orbit Altitude: %.2f, Orbit Type: %s, Resolution Quality: %d, Coverage Area: %.2f]",
                                           this.orbitAltitude, this.orbitType, this.resolutionQuality, this.coverageArea);
        return super.displayInfo() + "\n" + satelliteInfo;
    }
   
}
