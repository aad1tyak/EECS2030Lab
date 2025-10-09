package lab2;


import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 * Comprehensive JUnit test suite for ParkingSpot and ParkingGarage classes.
 * Tests are organized to execute in alphabetical order.
 * 
 * @author Dr. Sukhwant Sagar
 * @version Fall 2025
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_ParkingSpot {

    private ParkingSpot regularSpot;
    private ParkingSpot compactSpot;
    private ParkingSpot electricSpot;
    private ParkingSpot handicapSpot;
    private ParkingGarage garage;
    private long currentTime;

    @Before
    public void setUp() {
        // Set up test data before each test
        regularSpot = new ParkingSpot(1, 0.0, 0.0, "Regular");
        compactSpot = new ParkingSpot(2, 5.0, 5.0, "Compact");
        electricSpot = new ParkingSpot(3, 10.0, 0.0, "Electric");
        handicapSpot = new ParkingSpot(4, 3.0, 4.0, "Handicap");
        garage = new ParkingGarage("Smart Parking Plaza", 5.0);
        currentTime = System.currentTimeMillis();
    }

    // ========== PARKINGSPOT CONSTRUCTOR TESTS ==========

    @Test
    public void test01_parkingSpotConstructor() {
        ParkingSpot spot = new ParkingSpot(99, 15.5, 20.3, "Regular");
        assertEquals("Spot number should match", 99, spot.getSpotNumber());
        assertEquals("Spot type should match", "Regular", spot.getSpotType());
        assertFalse("Spot should not be occupied initially", spot.isOccupied());
        assertNull("Vehicle ID should be null initially", spot.getVehicleId());
        assertEquals("Entry time should be zero initially", 0, spot.getEntryTime());
        
        double[] coords = spot.getCoordinates();
        assertEquals("X coordinate should match", 15.5, coords[0], 0.01);
        assertEquals("Y coordinate should match", 20.3, coords[1], 0.01);
    }

    @Test
    public void test02_parkingSpotConstructorWithDifferentTypes() {
        ParkingSpot electric = new ParkingSpot(5, 0.0, 0.0, "Electric");
        ParkingSpot compact = new ParkingSpot(6, 0.0, 0.0, "Compact");
        ParkingSpot handicap = new ParkingSpot(7, 0.0, 0.0, "Handicap");
        
        assertEquals("Electric type should be set", "Electric", electric.getSpotType());
        assertEquals("Compact type should be set", "Compact", compact.getSpotType());
        assertEquals("Handicap type should be set", "Handicap", handicap.getSpotType());
    }

    
    // ========== PARKINGSPOT GETTER TESTS ==========

    @Test
    public void test04_getSpotNumber() {
        assertEquals("Spot number should match", 1, regularSpot.getSpotNumber());
        assertEquals("Spot number should match", 2, compactSpot.getSpotNumber());
    }

    @Test
    public void test06_getSpotType() {
        assertEquals("Regular type", "Regular", regularSpot.getSpotType());
        assertEquals("Compact type", "Compact", compactSpot.getSpotType());
        assertEquals("Electric type", "Electric", electricSpot.getSpotType());
        assertEquals("Handicap type", "Handicap", handicapSpot.getSpotType());
    }

    // ========== PARKINGSPOT PARK VEHICLE TESTS ==========

    @Test
    public void test08_parkVehicleValid() {
        assertTrue("Parking should succeed", regularSpot.parkVehicle("ABC123", currentTime));
        assertTrue("Spot should be occupied", regularSpot.isOccupied());
        assertEquals("Vehicle ID should be set", "ABC123", regularSpot.getVehicleId());
        assertEquals("Entry time should be set", currentTime, regularSpot.getEntryTime());
    }

    @Test
    public void test09_parkVehicleAlreadyOccupied() {
        regularSpot.parkVehicle("FIRST", currentTime);
        assertFalse("Second parking should fail", regularSpot.parkVehicle("SECOND", currentTime + 1000));
        assertEquals("Original vehicle should remain", "FIRST", regularSpot.getVehicleId());
        assertEquals("Original time should remain", currentTime, regularSpot.getEntryTime());
    }

   @Test
    public void test12_parkVehicleInvalidTime() {
        assertFalse("Parking with zero time should fail", regularSpot.parkVehicle("ABC123", 0));
        assertFalse("Parking with negative time should fail", regularSpot.parkVehicle("ABC123", -1000));
        assertFalse("Spot should remain unoccupied", regularSpot.isOccupied());
    }

    // ========== PARKINGSPOT REMOVE VEHICLE TESTS ==========

    @Test
    public void test14_removeVehicleValid() {
        regularSpot.parkVehicle("TEST123", currentTime);
        assertTrue("Removal should succeed", regularSpot.removeVehicle());
        assertFalse("Spot should not be occupied", regularSpot.isOccupied());
        assertNull("Vehicle ID should be null", regularSpot.getVehicleId());
        assertEquals("Entry time should be zero", 0, regularSpot.getEntryTime());
    }

    @Test
    public void test16_removeVehicleMultipleTimes() {
        regularSpot.parkVehicle("TEST456", currentTime);
        assertTrue("First removal should succeed", regularSpot.removeVehicle());
        assertFalse("Second removal should fail", regularSpot.removeVehicle());
        assertFalse("Spot should remain unoccupied", regularSpot.isOccupied());
    }

    // ========== PARKINGSPOT RESERVATION TESTS ==========

    @Test
    public void test17_reserveSpotValid() {
        long futureTime = currentTime + 3600000; // 1 hour later
        assertTrue("Reservation should succeed", regularSpot.reserveSpot(futureTime));
        assertTrue("Spot should be reserved now", regularSpot.isReserved(currentTime));
        assertTrue("Spot should be reserved before expiry", regularSpot.isReserved(futureTime - 1000));
        assertFalse("Spot should not be reserved after expiry", regularSpot.isReserved(futureTime + 1000));
    }

    @Test
    public void test18_reserveOccupiedSpot() {
        regularSpot.parkVehicle("OCCUPIED", currentTime);
        long futureTime = currentTime + 3600000;
        assertFalse("Reserving occupied spot should fail", regularSpot.reserveSpot(futureTime));
        assertFalse("Spot should not be reserved", regularSpot.isReserved(currentTime));
    }

    @Test
    public void test21_isReservedEdgeCases() {
        long reserveUntil = currentTime + 3600000;
        regularSpot.reserveSpot(reserveUntil);
        
        assertTrue("Should be reserved before expiry", regularSpot.isReserved(reserveUntil - 1));
        assertFalse("Should not be reserved at expiry time", regularSpot.isReserved(reserveUntil));
        assertFalse("Should not be reserved after expiry", regularSpot.isReserved(reserveUntil + 1));
    }

    // ========== PARKINGSPOT DISTANCE CALCULATION TESTS ==========

    @Test
    public void test22_calculateDistanceBasic() {
        // regularSpot is at (0,0)
        double distance = regularSpot.calculateDistance(3.0, 4.0);
        assertEquals("Distance should be 5.0 (3-4-5 triangle)", 5.0, distance, 0.01);
    }

    @Test
    public void test25_calculateDistanceRounding() {
        // compactSpot is at (5,5)
        double distance = compactSpot.calculateDistance(5.0, 6.0);
        assertEquals("Distance should be 1.0", 1.0, distance, 0.01);
        
        // Test rounding to 2 decimal places
        distance = compactSpot.calculateDistance(5.123, 5.456);
        // Expected: sqrt((0.123)^2 + (0.456)^2) = sqrt(0.015129 + 0.207936) = sqrt(0.223065) = 0.472296...
        assertEquals("Distance should be rounded to 2 decimal places", 0.47, distance, 0.01);
    }

    @Test
    public void test27_calculateDistanceFloatingPoint() {
        // Test various floating point distances
        double distance1 = electricSpot.calculateDistance(13.0, 4.0); // (10,0) to (13,4) = 5.0
        double distance2 = handicapSpot.calculateDistance(0.0, 0.0); // (3,4) to (0,0) = 5.0
        
        assertEquals("Distance from electric spot", 5.0, distance1, 0.01);
        assertEquals("Distance from handicap spot", 5.0, distance2, 0.01);
    }

    // ========== PARKINGSPOT TOSTRING TESTS ==========

    @Test
    public void test28_toStringAvailable() {
        String str = regularSpot.toString();
        assertTrue("Should contain spot number", str.contains("#1"));
        assertTrue("Should contain coordinates", str.contains("(0.0, 0.0)"));
        assertTrue("Should contain type", str.contains("Regular"));
        assertTrue("Should show available status", str.contains("Available"));
    }

    @Test
    public void test31_toStringCoordinateFormatting() {
        ParkingSpot spot = new ParkingSpot(99, 12.345, 67.890, "Electric");
        String str = spot.toString();
        assertTrue("Should format coordinates to 1 decimal", str.contains("(12.3, 67.9)"));
    }

    // ========== PARKINGGARAGE CONSTRUCTOR TESTS ==========

    @Test
    public void test32_garageConstructor() {
        ParkingGarage testGarage = new ParkingGarage("Test Garage", 8.5);
        assertEquals("Garage name should match", "Test Garage", testGarage.getGarageName());
        assertEquals("Total spots should be zero", 0, testGarage.getTotalSpots());
        assertEquals("Occupied spots should be zero", 0, testGarage.getOccupiedSpots());
        assertEquals("Available spots should be zero", 0, testGarage.getAvailableSpots());
        assertEquals("Revenue should be zero", 0.0, testGarage.getTotalRevenue(), 0.01);
    }

    // ========== PARKINGGARAGE GETTER TESTS ==========

    @Test
    public void test34_initialStatistics() {
        assertEquals("Initial total spots", 0, garage.getTotalSpots());
        assertEquals("Initial occupied spots", 0, garage.getOccupiedSpots());
        assertEquals("Initial available spots", 0, garage.getAvailableSpots());
        assertEquals("Initial revenue", 0.0, garage.getTotalRevenue(), 0.01);
        assertEquals("Initial occupancy rate", 0.0, garage.getOccupancyRate(), 0.01);
    }

    // ========== PARKINGGARAGE ADD SPOT TESTS ==========

    @Test
    public void test35_addParkingSpotValid() {
        assertTrue("Adding valid spot should succeed", garage.addParkingSpot(regularSpot));
        assertEquals("Total spots should increase", 1, garage.getTotalSpots());
        assertEquals("Available spots should increase", 1, garage.getAvailableSpots());
    }

    @Test
    public void test36_addMultipleParkingSpots() {
        assertTrue("Adding first spot should succeed", garage.addParkingSpot(regularSpot));
        assertTrue("Adding second spot should succeed", garage.addParkingSpot(compactSpot));
        assertTrue("Adding third spot should succeed", garage.addParkingSpot(electricSpot));
        
        assertEquals("Total spots should be 3", 3, garage.getTotalSpots());
        assertEquals("Available spots should be 3", 3, garage.getAvailableSpots());
    }
  
    // ========== PARKINGGARAGE FIND NEAREST SPOT TESTS ==========

    @Test
    public void test40_findNearestSpotBasic() {
        garage.addParkingSpot(regularSpot); // at (0,0)
        garage.addParkingSpot(compactSpot);  // at (5,5)
        
        ParkingSpot nearest = garage.findNearestSpot(1.0, 1.0, "Regular");
        assertEquals("Should find regular spot", regularSpot, nearest);
    }

    @Test
    public void test45_findNearestSpotWrongType() {
        garage.addParkingSpot(regularSpot);
        garage.addParkingSpot(compactSpot);
        
        ParkingSpot nearest = garage.findNearestSpot(0.0, 0.0, "Electric");
        assertNull("Should return null for unavailable type", nearest);
    }
    

    // ========== PARKINGGARAGE PROCESS PARKING TESTS ==========

    @Test
    public void test47_processParkingValid() {
        garage.addParkingSpot(regularSpot);
        assertTrue("Parking processing should succeed", garage.processParking(regularSpot, "ABC123", currentTime));
        assertEquals("Occupied spots should increase", 1, garage.getOccupiedSpots());
        assertEquals("Available spots should decrease", 0, garage.getAvailableSpots());
        assertTrue("Spot should be occupied", regularSpot.isOccupied());
    }

    @Test
    public void test48_processParkingMultiple() {
        garage.addParkingSpot(regularSpot);
        garage.addParkingSpot(compactSpot);
        
        assertTrue("First parking should succeed", garage.processParking(regularSpot, "CAR1", currentTime));
        assertTrue("Second parking should succeed", garage.processParking(compactSpot, "CAR2", currentTime + 1000));
        
        assertEquals("Should have 2 occupied spots", 2, garage.getOccupiedSpots());
        assertEquals("Should have 0 available spots", 0, garage.getAvailableSpots());
    }

    // ========== PARKINGGARAGE PROCESS CHECKOUT TESTS ==========

    @Test
    public void test52_processCheckoutValid() {
        garage.addParkingSpot(regularSpot);
        garage.processParking(regularSpot, "TEST123", currentTime);
        
        long checkoutTime = currentTime + 3600000; // 1 hour later
        double fee = garage.processCheckout(regularSpot, checkoutTime);
        
        assertTrue("Fee should be positive", fee > 0);
        assertEquals("Occupied spots should decrease", 0, garage.getOccupiedSpots());
        assertTrue("Total revenue should be updated", garage.getTotalRevenue() > 0);
        assertFalse("Spot should not be occupied", regularSpot.isOccupied());
    }

    @Test
    public void test55_processCheckoutMultiple() {
        garage.addParkingSpot(regularSpot);
        garage.addParkingSpot(compactSpot);
        
        garage.processParking(regularSpot, "CAR1", currentTime);
        garage.processParking(compactSpot, "CAR2", currentTime);
        
        long checkoutTime = currentTime + 3600000;
        double fee1 = garage.processCheckout(regularSpot, checkoutTime);
        double fee2 = garage.processCheckout(compactSpot, checkoutTime);
        
        assertEquals("All spots should be available", 2, garage.getAvailableSpots());
        assertEquals("No spots should be occupied", 0, garage.getOccupiedSpots());
        assertEquals("Total revenue should be sum of fees", fee1 + fee2, garage.getTotalRevenue(), 0.01);
    }

    // ========== PARKINGGARAGE CALCULATE PARKING FEE TESTS ==========

     @Test
    public void test58_calculateParkingFeeTypesMultipliers() {
    	garage.addParkingSpot(regularSpot);
        garage.addParkingSpot(compactSpot);
        garage.addParkingSpot(electricSpot);
        garage.addParkingSpot(handicapSpot);
        
        long checkoutTime = currentTime + 3600000; // 1 hour
        
        regularSpot.parkVehicle("REG", currentTime);
        compactSpot.parkVehicle("COMP", currentTime);
        electricSpot.parkVehicle("ELEC", currentTime);
        handicapSpot.parkVehicle("HAND", currentTime);
                     
        double regularFee = garage.calculateParkingFee(regularSpot, checkoutTime);
        double compactFee = garage.calculateParkingFee(compactSpot, checkoutTime);
        double electricFee = garage.calculateParkingFee(electricSpot, checkoutTime);
        double handicapFee = garage.calculateParkingFee(handicapSpot, checkoutTime);
        
        // Base = 5.0, hours = 1, demand = 1.0 (no occupancy tracking)
        // Regular: 5.0 * 1 * 1.0 * 1.0 = 5.00
        // Compact: 5.0 * 1 * 0.8 * 1.0 = 4.00
        // Electric: 5.0 * 1 * 1.2 * 1.0 = 6.00
        // Handicap: 5.0 * 1 * 0.5 * 1.0 = 2.50
        
        assertEquals("Regular spot fee", 5.00, regularFee, 0.01);
        assertEquals("Compact spot fee", 4.00, compactFee, 0.01);
        assertEquals("Electric spot fee", 6.00, electricFee, 0.01);
        assertEquals("Handicap spot fee", 2.50, handicapFee, 0.01);
    }

   
    // ========== PARKINGGARAGE OCCUPANCY RATE TESTS ==========

    @Test
    public void test63_getOccupancyRateEmpty() {
        assertEquals("Empty garage occupancy should be 0", 0.0, garage.getOccupancyRate(), 0.01);
    }

    @Test
    public void test65_getOccupancyRatePartial() {
        garage.addParkingSpot(regularSpot);
        garage.addParkingSpot(compactSpot);
        garage.addParkingSpot(electricSpot);
        garage.addParkingSpot(handicapSpot);
        
        garage.processParking(regularSpot, "CAR1", currentTime);
        garage.processParking(compactSpot, "CAR2", currentTime);
        
        // 2 out of 4 spots occupied = 50%
        assertEquals("Partial occupancy", 50.0, garage.getOccupancyRate(), 0.01);
    }

    @Test
    public void test67_getOccupancyRateAfterCheckout() {
        garage.addParkingSpot(regularSpot);
        garage.addParkingSpot(compactSpot);
        
        garage.processParking(regularSpot, "CAR1", currentTime);
        garage.processParking(compactSpot, "CAR2", currentTime);
        
        assertEquals("Before checkout", 100.0, garage.getOccupancyRate(), 0.01);
        
        garage.processCheckout(regularSpot, currentTime + 3600000);
        
        assertEquals("After checkout", 50.0, garage.getOccupancyRate(), 0.01);
    }

    // ========== PARKINGGARAGE GENERATE REPORT TESTS ==========

    @Test
    public void test68_generateGarageReportEmpty() {
        String report = garage.generateGarageReport();
        assertNotNull("Report should not be null", report);
        assertTrue("Report should contain garage name", report.contains("Smart Parking Plaza"));
        assertTrue("Report should show 0 total spots", report.contains("Total Spots: 0"));
        assertTrue("Report should show 0 occupied", report.contains("Occupied Spots: 0"));
        assertTrue("Report should show 0 available", report.contains("Available Spots: 0"));
        assertTrue("Report should show 0.0% occupancy", report.contains("Occupancy Rate: 0.0%"));
        assertTrue("Report should show $0.00 revenue", report.contains("Revenue: $0.00"));
    }

}
        