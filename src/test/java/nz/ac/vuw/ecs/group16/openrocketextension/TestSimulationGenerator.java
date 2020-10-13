package nz.ac.vuw.ecs.group16.openrocketextension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import net.sf.openrocket.simulation.FlightData;
import nz.ac.vuw.ecs.group16.openrocketextension.CSVReader.CSVInData;
import nz.ac.vuw.ecs.group16.openrocketextension.SimulationGenerator.FlightObjects;

/**
 * @author ryanyee
 *
 */
public class TestSimulationGenerator {
  
  //src/main/resources/RocketDesign.ork src/main/resources/dummyData.csv output.csv 10 -v1 2
  private String dataFilePath = "src/main/resources/dummyData.csv";
  private String orkFilePath = "src/main/resources/RocketDesign.ork";
  
  /**
   * Create the test cases
   */
  public TestSimulationGenerator() {
    super();
  }
  
  /**
   * Test if a file our error handling for an invalid file path name is caught.
   */
  @Test
  public void TestInvalidDataFilepath01(){
    SimulationGenerator simGen = new SimulationGenerator();
    simGen.count = 10;
    CSVReader csvReader = new CSVReader();
    String invalidDataPath = "Invalid Data Filepath";
    try {
      csvReader.readCSV(invalidDataPath);
      simGen.loadOrk(orkFilePath);
      ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      assertTrue(e.getMessage().equals("Invalid Data Filepath (No such file or directory)")
				|| e.getMessage().equals("Invalid Data Filepath (The system cannot find the file specified)"));
    }
  }
  
  /**
   * Test if a file our error handling for an invalid file path name is caught.
   */
  @Test
  public void TestInvalidORKFilepath01(){
    SimulationGenerator simGen = new SimulationGenerator();
    simGen.count = 10;
    CSVReader csvReader = new CSVReader();
    String invalidORKPath = "Invalid ORK Filepath";
    try {
      csvReader.readCSV(dataFilePath);
      simGen.loadOrk(invalidORKPath);
      ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      assertTrue(e.getMessage().equals("Invalid ORK Filepath (No such file or directory)")
				|| e.getMessage().equals("Invalid ORK Filepath (The system cannot find the file specified)"));
    }
  }
  
  /**
   * Test output path end with csv
   */
  @Test
  public void TestValidOutputPath() {
      SimulationGenerator simGen = new SimulationGenerator();
        simGen.count = 1;
        CSVReader csvReader = new CSVReader();
        String inputPath = "src/main/resources/dummyData.csv";
        String outputPath = "output.csv";
        try {
          csvReader.readCSV(inputPath);
          
          simGen.loadOrk(orkFilePath);
          ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
          
          CSVWriter csvWriter = new CSVWriter(outputPath, outputData);
          csvWriter.writeCsvData();
          assertTrue(Files.exists(Paths.get(outputPath)));
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
  }
  
  /**
   * Test output path that does not end with csv
   */
  @Test
  public void TestValidOutputPath2() {
      SimulationGenerator simGen = new SimulationGenerator();
        simGen.count = 1;
        CSVReader csvReader = new CSVReader();
        String inputPath = "src/main/resources/dummyData.csv";
        String outputPath = "output";
        try {
          csvReader.readCSV(inputPath);
          
          simGen.loadOrk(orkFilePath);
          ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
          

          
          CSVWriter csvWriter = new CSVWriter(outputPath, outputData);
          csvWriter.writeCsvData();
          assertTrue(Files.exists(Paths.get(outputPath)));
        } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
  }

  /**
   * Test for legitimate co-ordinate values
   */
  @Test
  public void TestCoordinate01() {
    SimulationGenerator simGen = new SimulationGenerator();
    simGen.count = 10;
    CSVReader csvReader = new CSVReader();
    String path = "src/main/resources/dummyData.csv";
    try {
      csvReader.readCSV(dataFilePath);
      
      simGen.loadOrk(orkFilePath);
      ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
      
      for(FlightObjects d : outputData) {
        assertTrue(d.landingSimStatus.getRocketWorldPosition().getLatitudeDeg() >= -90);
        assertTrue(d.landingSimStatus.getRocketWorldPosition().getLatitudeDeg() <= 90);
        assertTrue(d.landingSimStatus.getRocketWorldPosition().getLongitudeDeg() >= -180);
        assertTrue(d.landingSimStatus.getRocketWorldPosition().getLongitudeDeg() <= 180);
      }
      
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Test for legitimate flight times (should be greater than 0 seconds)
   */
  @Test
  public void TestFlightTime01() {
    SimulationGenerator simGen = new SimulationGenerator();
    simGen.count = 10;
    CSVReader csvReader = new CSVReader();
    String path = "src/main/resources/dummyData.csv";
    try {
      csvReader.readCSV(dataFilePath);
      
      simGen.loadOrk(orkFilePath);
      ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
      
      for(FlightObjects d : outputData) {
        assertTrue(d.flightData.getFlightTime() > 0);
      }
      
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Test for legitimate ground hit velocities (should be greater than 0 seconds)
   */
  @Test
  public void TestGroundHitVelocity01() {
    SimulationGenerator simGen = new SimulationGenerator();
    simGen.count = 10;
    CSVReader csvReader = new CSVReader();
    String path = "src/main/resources/dummyData.csv";
    try {
      csvReader.readCSV(dataFilePath);
      
      simGen.loadOrk(orkFilePath);
      ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
      
      for(FlightObjects d : outputData) {
        assertTrue(d.flightData.getGroundHitVelocity() > 0);
      }
      
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
  /**
   * Test for that the max altitude is greater than 0
   */
  @Test
  public void TestMaxVelocity01() {
    SimulationGenerator simGen = new SimulationGenerator();
    simGen.count = 10;
    CSVReader csvReader = new CSVReader();
    String path = "src/main/resources/dummyData.csv";
    try {
      csvReader.readCSV(dataFilePath);
      
      simGen.loadOrk(orkFilePath);
      ArrayList<FlightObjects> outputData = simGen.generateSimulation(csvReader.CSVData);
      
      for(FlightObjects d : outputData) {
        assertTrue(d.flightData.getGroundHitVelocity() > 0);
      }
      
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}

