package nz.ac.vuw.ecs.group16.openrocketextension;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class deals with reading in the simulation options data from a CSV file.
 * 
 * @author Dominic
 *
 */
public class CSVReader {
  /**
   * Data structure to store the option values from the CSV file read in.
   * 
   * @author Finn
   *
   */
  public static class CSVInData {
    Double launchRodAngle = null;
    Double launchRodLength = null;
    Double launchRodDirection = null;
    Double launchAltitude = null;
    Double launchLatitude = null;
    Double launchLongitude = null;
    Double maximumAngle = null;
    Double windSpeed = null;
    Double windDirection = null;
    Double windTurbulence = null;
    Double launchTemperature = null;
    Double launchAirPressure = null;

    public CSVInData() {

    }
  }

  public CSVInData CSVData = new CSVInData();
  private String[] simulationParameters;

  /**
   * Uses buffered reader to read in CSV data from given file path. Stores data in CSVData class
   * variable.
   * 
   * @param csvFileName file path
   * @throws FileNotFoundException
   * @throws IOException
   * @throws NumberFormatException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  public void readCSV(String csvFileName) throws FileNotFoundException, IOException,
      NumberFormatException, IllegalArgumentException, IllegalAccessException {
    // Initialising a buffered reader to read through the CSV file
    BufferedReader csvReader = new BufferedReader(new FileReader(new File(csvFileName)));

    // Initialising variables detecting current row contents and line number
    String row = csvReader.readLine();

    String[] rowData = row.split(",");
    String[] parameters = new String[rowData.length];

    // Skip current line if the line does not start with a double or is not the
    // fourth line
    // System.out.println("Row: " + lineCount + " \t" + rowData[0]);
    // Check whether the line starts with a double or not

    parameters = rowData;
    simulationParameters = parameters;

    row = csvReader.readLine();
    rowData = row.split(",");
    // Retrieve data from the CSV file
    for (int i = 0; i < rowData.length; i++) {
      // Get the parameters
      if (simulationParameters[i].equals("﻿Launch Rod Angle")
          || simulationParameters[i].equals("# Launch Rod Angle")) {
        CSVData.launchRodAngle = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Rod Length")
          || simulationParameters[i].equals("# Launch Rod Length")) {
        CSVData.launchRodLength = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Rod Direction")
          || simulationParameters[i].equals("# Launch Rod Direction")) {
        CSVData.launchRodDirection = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Altitude")
          || simulationParameters[i].equals("# Launch Altitude")) {
        CSVData.launchAltitude = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Latitude")
          || simulationParameters[i].equals("# Launch Latitude")) {
        CSVData.launchLatitude = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Longitude")
          || simulationParameters[i].equals("# Launch Longitude")) {
        CSVData.launchLongitude = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Maximum Angle")
          || simulationParameters[i].equals("# Maximum Angle")) {
        CSVData.maximumAngle = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Wind Speed")
          || simulationParameters[i].equals("# Wind Speed")) {
        CSVData.windSpeed = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Wind Direction")
          || simulationParameters[i].equals("# Wind Direction")) {
        CSVData.windDirection = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Wind Turbulence")
          || simulationParameters[i].equals("# Wind Turbulence")) {
        CSVData.windTurbulence = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Temperature")
          || simulationParameters[i].equals("# Launch Temperature")) {
        CSVData.launchTemperature = Double.parseDouble(rowData[i]);
      } else if (simulationParameters[i].equals("Launch Air Pressure")
          || simulationParameters[i].equals("# Launch Air Pressure")) {
        CSVData.launchAirPressure = Double.parseDouble(rowData[i]);
      }

    }
    csvReader.close(); // closing the CSV reader as we are done.
  }
}
