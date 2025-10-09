package lab2;

import java.util.ArrayList;
import java.util.List;

/**
 * The Lab2 class represents a parking spot in a smart parking system.
 * This class encapsulates spot information including coordinates, type, occupancy status,
 * vehicle information, and provides mathematical operations for distance calculations.
 * 
 * @author Dr. Sukhwant Sagar
 * @author Aaditya Karamchandani
 * @version Fall2025
 */
public class ParkingSpot {
    
	// TODO: Declare private instance variables
    // - spotNumber (int)
    // - coordinates (double[]) - array of size 2 for [x, y]
    // - spotType (String) - "Regular", "Compact", "Electric", "Handicap"
    // - isOccupied (boolean)
    // - vehicleId (String)
    // - entryTime (long) - timestamp in milliseconds
    // - reservedUntil (long) - timestamp until which spot is reserved
    private int spotNumber;
    private double[] coordinates = new double[2];
    private String spotType;
    private boolean isOccupied;
    private String vehicleId;
    private long entryTime;
    private long reservedUntil;
    
    /**
     * Constructor to create a new parking spot with coordinates and type.
     * The spot is initially available with no vehicle or reservation.
     * spotNumber should be positive
     * spotType should be one of the valid types
     * 
     * @param spotNumber the unique spot number
     * @param x the x-coordinate of the spot
     * @param y the y-coordinate of the spot  
     * @param spotType the type of parking spot ("Regular", "Compact", "Electric", "Handicap")
     * 
     */
    public ParkingSpot(int spotNumber, double x, double y, String spotType) {
    	// TODO: Implement this constructor
        // Initialize spotNumber, coordinates array [x, y], spotType
        // Set isOccupied to false, vehicleId to null, entryTime to 0, reservedUntil to 0
    	this.spotNumber = spotNumber;
    	this.coordinates[0] = x;
    	this.coordinates[1] = y;
    	this.spotType = spotType;
    	this.isOccupied = false;
    	this.vehicleId = null;
    	this.entryTime = 0;
    	this.reservedUntil = 0;
    }
    
    /**
     * Returns the spot number.
     * @return the spot number
     */
    public int getSpotNumber() {
    	// TODO: Implement this method
        return this.spotNumber;
    }
    
    /**
     * Returns a copy of the coordinates array.
     * @return a copy of the coordinates [x, y]
     */
    public double[] getCoordinates() {
    	// TODO: Implement this method
        // Return a copy of the coordinates array to maintain encapsulation
    	double[] temp = new double[this.coordinates.length];
    	temp[0] = this.coordinates[0];
    	temp[1] = this.coordinates[1];
        return temp;
    }
    
    /**
     * Returns the spot type.
     * @return the spot type
     */
    public String getSpotType() {
    	 // TODO: Implement this method
        return this.spotType;
    }
    
    /**
     * Checks if the spot is currently occupied.
     * @return true if occupied, false otherwise
     */
    public boolean isOccupied() {
    	// TODO: Implement this method
        return this.isOccupied;
    }
    
    /**
     * Returns the vehicle ID of the current occupant.
     * @return the vehicle ID, or null if spot is not occupied
     */
    public String getVehicleId() {
    	// TODO: Implement this method
        return this.vehicleId;
    }
    
    /**
     * Returns the entry time of the current vehicle.
     * @return the entry time in milliseconds, or 0 if spot is not occupied
     */
    public long getEntryTime() {
    	// TODO: Implement this method
        if (this.isOccupied) {
        	return this.entryTime;
        }
        else {
        	return 0;
        }
    }
    
    /**
     * Parks a vehicle in this spot.
     * The spot can only be parked if it is currently available and not reserved.
     * VehicleId should not be null or empty. entryTime should be positive
     * 
     * @param vehicleId the ID of the vehicle to park
     * @param entryTime the time when vehicle entered (milliseconds since epoch)
     * @return true if parking was successful, false otherwise
     * 
     */
    public boolean parkVehicle(String vehicleId, long entryTime) {
    	// TODO: Implement this method
        // Check if spot is available (not occupied and not reserved at entryTime)
        // If available, set vehicleId, entryTime, and mark as occupied
        // Return true if successful, false otherwise
       
        if (this.vehicleId == null && !(this.isOccupied) && entryTime > 0 && !this.isReserved(entryTime)) {
        	this.vehicleId = vehicleId;
        	this.entryTime = entryTime;
        	this.isOccupied = true;
        	return true;
        }
        else {
        	return false;
        }
    }
    
    /**
     * Removes the vehicle from this spot.
     * The spot can only be cleared if it is currently occupied.
     * 
     * @return true if vehicle was removed successfully, false otherwise
     */
    public boolean removeVehicle() {
    	// TODO: Implement this method
        // Check if spot is currently occupied
        // If occupied, clear vehicleId, set entryTime to 0, mark as not occupied
        // Return true if successful, false otherwise
    	if (this.isOccupied) {
    		this.vehicleId = null;
    		this.entryTime = 0;
    		this.isOccupied = false;
    		return true;
    	}
    	else {
        return false;
    	}
    }
    
    /**
     * Reserves this spot until the specified time.
     * The spot can only be reserved if it is currently available.
     * reserveUntil should be in the future
     * 
     * @param reserveUntil the timestamp until which to reserve the spot
     * @return true if reservation was successful, false otherwise
     * 
     */
    public boolean reserveSpot(long reserveUntil) {
    	 // TODO: Implement this method
        // Check if spot is available (not occupied and not currently reserved)
        // If available, set reservedUntil timestamp
        // Return true if successful, false otherwise
        if (!(this.isOccupied)) {
        	this.reservedUntil = reserveUntil;
        	return true;
        }
        else {
        	return false;
        }
        
    }
    
    /**
     * Checks if the spot is reserved at the given time.
     * 
     * @param currentTime the time to check reservation status
     * @return true if spot is reserved at the given time, false otherwise
     */
    public boolean isReserved(long currentTime) {
    	// TODO: Implement this method
        // Check if currentTime is before reservedUntil
        // Return true if reserved, false otherwise
    	if (currentTime < this.reservedUntil) {
    		return true;
    	}
        return false;
    }
    
    /**
     * Calculates the Euclidean distance from this spot to the given coordinates.
     * Uses the formula: distance = √[(x₂-x₁)² + (y₂-y₁)²]
     * 
     * @param targetX the x-coordinate of the target location
     * @param targetY the y-coordinate of the target location
     * @return the distance to the target location, rounded to 2 decimal places
     */
    public double calculateDistance(double targetX, double targetY) {
    	 // TODO: Implement this method
        // Use Euclidean distance formula: sqrt((x2-x1)^2 + (y2-y1)^2)
        // Round result to 2 decimal places using Math.round(distance * 100.0) / 100.0
    	double sqDistanceX = Math.pow((targetX - this.coordinates[0]), 2);
    	double sqDistanceY = Math.pow((targetY - this.coordinates[1]), 2);
        double distance = Math.sqrt(sqDistanceX + sqDistanceY);
        return Math.round(distance * 100.0) / 100.0;
    }
    
    /**
     * Returns a string representation of the parking spot.
     * Format: "Spot #[number] at ([x], [y]) - Type: [type], Status: [Available/Occupied by vehicleId/Reserved until timestamp]"
     * 
     * @return a formatted string representation of the parking spot
     */
    @Override
    public String toString() {
    	// TODO: Implement this method
        // Create formatted string with spot details and current status
        // Include coordinates with 1 decimal place, and appropriate status message
    	String status = "";
    	if (this.isOccupied) {
    		status = String.format("Occupied by %s", this.vehicleId);
    	}
    	else if (this.reservedUntil != 0) {
    		status = String.format("Reserved until %d", this.reservedUntil);
    	}
    	else {
    		status = "Available";
    	}
        return String.format("Spot #%d at (%.1f, %.1f) - Type: %s, Status: %s", this.spotNumber, this.coordinates[0], this.coordinates[1], this.spotType, status);
    }
}

/**
 * The ParkingGarage class represents a smart parking garage management system.
 * This class manages multiple parking spots, implements complex algorithms for 
 * finding nearest spots, calculates dynamic pricing, and tracks occupancy statistics.
 *  
 */
class ParkingGarage {
    
	 // TODO: Declare private instance variables
    // - garageName (String)
    // - totalSpots (int)
    // - occupiedSpots (int)
    // - totalRevenue (double)
    // - baseParkingRate (double) - base rate per hour
    
	private String garageName;
	private int totalSpots;
	private int occupiedSpot;
	private double totalRevenue;
	private double baseParkingRate;
	private List<ParkingSpot> spots; //Why was this not mentioned in the TODO? I wasted so much time confused
    /**
     * Constructor to create a new parking garage.
     * Initializes all counters to zero.garageName should not be null or empty
     * baseParkingRate should be positive
     * 
     * @param garageName the name of the garage
     * @param baseParkingRate the base parking rate per hour
     * 
     */
    public ParkingGarage(String garageName, double baseParkingRate) {
    	// TODO: Implement this constructor
    	if(garageName != null && !garageName.isEmpty() && baseParkingRate >0) {
    	this.garageName = garageName;
    	this.baseParkingRate = baseParkingRate;
    	this.totalRevenue = 0;
    	this.totalSpots = 0;
    	this.occupiedSpot = 0;
    	this.spots = new ArrayList<>();
    	}
    	}
    
    /**
     * Returns the garage name.
     * @return the garage name
     */
    public String getGarageName() {
    	// TODO: Implement this method
        return this.garageName;
    }
    
    /**
     * Returns the total number of spots in the garage.
     * @return the total number of spots
     */
    public int getTotalSpots() {
    	// TODO: Implement this method
        return this.totalSpots;
    }
    
    /**
     * Returns the number of currently occupied spots.
     * @return the number of occupied spots
     */
    public int getOccupiedSpots() {
    	// TODO: Implement this method
        return this.occupiedSpot;
    }
    
    /**
     * Returns the number of available spots.
     * @return the number of available spots (total - occupied)
     */
    public int getAvailableSpots() {
    	// TODO: Implement this method
        return this.totalSpots - this.occupiedSpot;
    }
    
    /**
     * Returns the total revenue collected by the garage.
     * @return the total revenue
     */
    public double getTotalRevenue() {
    	 // TODO: Implement this method
        return this.totalRevenue;
    }
    
    /**
     * Adds a parking spot to the garage.
     * Only available spots can be added to the garage.spot should not be null
     * 
     * @param spot the parking spot to add
     * @return true if spot was successfully added, false if spot is null or occupied
     * 
     * */
    public boolean addParkingSpot(ParkingSpot spot) {
    	// TODO: Implement this method
        // Check if spot is not null and is available
        // If valid, increment totalSpots counter
        // Return true if successful, false otherwise
    	if(spot != null) {
    		this.totalSpots++;
    		this.spots.add(spot);
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Finds the nearest available parking spot of the specified type.
     * Uses Euclidean distance calculation to determine proximity.
     * In case of tie, returns spot with smaller spot number.
     * spotType should be valid ("Regular", "Compact", "Electric", "Handicap")
     * 
     * @param targetX the x-coordinate of the target location
     * @param targetY the y-coordinate of the target location
     * @param spotType the required spot type
     * @return the nearest available spot, or null if none found
     *
     */
    public ParkingSpot findNearestSpot(double targetX, double targetY, String spotType) {
    	// TODO: Implement this complex algorithm
        // 1. Initialize minDistance = Double.MAX_VALUE, nearestSpot = null
        // 2. For each spot in the garage (you'll need to maintain a collection of spots):
        //    a. Skip if spot is occupied, reserved, or wrong type
        //    b. Calculate distance using spot.calculateDistance()
        //    c. If distance < minDistance OR (distance == minDistance AND spot.number < nearestSpot.number):
        //       Update nearestSpot and minDistance
        // 3. Return nearestSpot
    	double minDistance = Double.MAX_VALUE;
    	ParkingSpot nearestSpot = null;
    	for(ParkingSpot spot: spots) {
    		if(spot.isOccupied() || spot.isReserved(0) || !spot.getSpotType().equals(spotType)) {
    			continue;
    		}
    		else {
    			double distance = spot.calculateDistance(targetX, targetY);
    			if(nearestSpot == null || distance < minDistance ||
    					   (distance == minDistance && spot.getSpotNumber() < nearestSpot.getSpotNumber())) {
    				nearestSpot = spot;
    				minDistance = distance;
    			}
    		}
    	}
        return nearestSpot;
    }
    
    /**
     * Processes parking a vehicle in the specified spot.
     * Updates occupancy statistics if successful.spot should not be null
     * vehicleId should not be null or empty
     * 
     * @param spot the parking spot to use
     * @param vehicleId the ID of the vehicle
     * @param entryTime the entry timestamp
     * @return true if parking was successful, false otherwise
     * 
     */
    public boolean processParking(ParkingSpot spot, String vehicleId, long entryTime) {
    	// TODO: Implement this method
        // Use spot.parkVehicle() to park the vehicle
        // If successful, increment occupiedSpots counter
        // Return the result of the parking operation
    	if(spot.parkVehicle(vehicleId, entryTime)) {
    		this.occupiedSpot++;
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    /**
     * Processes vehicle checkout and calculates parking fee.
     * Updates occupancy statistics and revenue if successful.
     * spot should not be null
     * checkoutTime should be after entry time
     * 
     * @param spot the parking spot to check out from
     * @param checkoutTime the checkout timestamp
     * @return the calculated parking fee, or 0.0 if checkout failed
     *  
     */
    public double processCheckout(ParkingSpot spot, long checkoutTime) {
    	// TODO: Implement this method
        // Calculate fee using calculateParkingFee()
        // Use spot.removeVehicle() to free the spot
        // If successful, decrement occupiedSpots and add fee to totalRevenue
        // Return the calculated fee
    	double fee = calculateParkingFee(spot, checkoutTime);
    	if(spot.removeVehicle()) {
    		this.totalRevenue += fee;
    		this.occupiedSpot--;
    	}
    	return fee;
    }
    
    /**
     * Calculates the parking fee using dynamic pricing algorithm.
     * Formula: fee = baseRate × hours × typeMultiplier × demandFactor
     * demandFactor = 1.0 + (occupancyRate × 0.5)
     * spot should not be null and should be occupied
     * 
     * @param spot the parking spot to calculate fee for
     * @param checkoutTime the checkout timestamp
     * @return the calculated fee rounded to nearest cent
     * 
     */
    public double calculateParkingFee(ParkingSpot spot, long checkoutTime) {
    	// TODO: Implement this complex calculation
        // 1. Calculate duration in hours: (checkoutTime - spot.getEntryTime()) / 3600000.0
        // 2. Calculate occupancy rate: occupiedSpots / totalSpots
        // 3. Calculate demand factor: 1.0 + (occupancyRate * 0.5)
        // 4. Get type multiplier based on spot type:
        //    Regular: 1.0, Compact: 0.8, Electric: 1.2, Handicap: 0.5
        // 5. Calculate fee: baseRate * hours * typeMultiplier * demandFactor
        // 6. Round to nearest cent: Math.round(fee * 100.0) / 100.0
    	double durationInHour = (checkoutTime - spot.getEntryTime()) / 3600000.0;
    	double occupancyRate = 0.0;
    	if(this.totalSpots != 0.0) {
    		occupancyRate = (double) this.occupiedSpot / this.totalSpots;
    	}
    	double demandFactor = 1.0 + (occupancyRate  * 0.5);
    	double fee = this.baseParkingRate * durationInHour * this.getTypeMultiplier(spot.getSpotType()) * demandFactor;
        return Math.round(fee * 100.0) / 100.0;
    }
    
    /**
     * Calculates the current occupancy rate as a percentage.
     * @return the occupancy rate (0.0 to 100.0), or 0.0 if no spots
     */
    public double getOccupancyRate() {
    	// TODO: Implement this method
        // Calculate: (occupiedSpots / totalSpots) * 100.0
        // Handle division by zero case (return 0.0 if totalSpots is 0)
        if (this.totalSpots == 0) return 0.0;
        return ((double) this.occupiedSpot / this.totalSpots) * 100.0;
    }
    
    /**
     * Generates a comprehensive garage status report.
     * @return a formatted report string with garage statistics
     */
    public String generateGarageReport() {
    	// TODO: Implement this method
        // Create a detailed report including:
        // - Garage name
        // - Total spots, occupied spots, available spots
        // - Occupancy rate (formatted to 1 decimal place)
        // - Total revenue (formatted to 2 decimal places)
        // - Base parking rate
    	
        return String.format("Garage Name: %s \n Total Spots: %d \n Occupied Spots: %d \n Available Spots: %d \n Occupancy Rate: %.1f%% \n Revenue: $%.2f \n Base Parking rate: %f", this.garageName, this.totalSpots, this.occupiedSpot, this.getAvailableSpots(), this.getOccupancyRate(), this.totalRevenue, this.baseParkingRate);
    }
    
    /**
     * Helper method to get the type multiplier for fee calculation.
     * 
     * @param spotType the spot type
     * @return the multiplier for the given type
     */
    private double getTypeMultiplier(String spotType) {
    	// TODO: Implement this helper method
        // Return appropriate multiplier based on spot type:
        // Regular: 1.0, Compact: 0.8, Electric: 1.2, Handicap: 0.5
        // Default to 1.0 for unknown types
        if(spotType.equals("Regular")) return 1.0;
        if(spotType.equals("Compact")) return 0.8;
        if(spotType.equals("Electric")) return 1.2;
        if(spotType.equals("Handicap")) return 0.5;
        return 1.0;
    }
}