package nz.ac.vuw.ecs.group16.openrocketextension;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import nz.ac.vuw.ecs.group16.openrocketextension.SimulationGenerator.FlightObjects;

/**
 * Deals with writing simulation monte-carlo data to a CSV file.
 * 
 * @author Ethan, David
 *
 */
public class CSVWriter {

  public String csvFileName;
  public File csvFile;

  // All data from simulations
  public ArrayList<FlightObjects> flightObjects;

  // Radians
  private double meanR = 0;
  private double stdevR = 0;

  // Meters
  private double meanM = 0;
  private double stdevM = 0;

  public CSVWriter(String name, ArrayList<FlightObjects> flightObjects) {
    csvFileName = name;
    csvFile = new File(csvFileName);
    this.flightObjects = flightObjects;
  }

  /**
   * Calculate the mean and standard deviation for the latitude and longitude.
   * 
   * @param fligtObjects2
   */
  private void calculateMeanAndStdev(ArrayList<FlightObjects> fligtObjects2) {

    final int R = 6371; // Radius of the earth
    // Store radians values
    double sumR = 0;
    ArrayList<Double> allDistancesR = new ArrayList<>();

    // Store meters values
    double sumM = 0;
    ArrayList<Double> allDistancesM = new ArrayList<>();

    // Calculate the means
    for (FlightObjects d : fligtObjects2) {
      // Calculate in radians
      double a = d.landingSimStatus.getRocketWorldPosition().getLatitudeDeg()
          - d.startData.getLaunchLatitude();
      double b = d.landingSimStatus.getRocketWorldPosition().getLongitudeDeg()
          - d.startData.getLaunchLongitude();
      double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

      allDistancesR.add(c);
      sumR += c;

      // Calculate in meters
      double a2 = Math.sin(a / 2) * Math.sin(a / 2) + Math.cos(Math.toRadians(a))
          * Math.cos(Math.toRadians(d.landingSimStatus.getRocketWorldPosition().getLatitudeDeg()))
          * Math.sin(b / 2) * Math.sin(b / 2);
      double c2 = 2 * Math.atan2(Math.sqrt(a2), Math.sqrt(1 - a2));
      double distance = R * c2 * 1000; // convert to meters
      allDistancesM.add(distance);
      sumM += distance;
    }

    meanR = sumR / allDistancesR.size();
    meanM = sumM / allDistancesM.size();

    // Calculate the standard deviation
    sumR = 0;
    sumM = 0;
    int n = allDistancesR.size();
    for (int i = 0; i < n; i++) {
      sumR += Math.pow(allDistancesR.get(i) - meanR, 2);
      sumM += Math.pow(allDistancesM.get(i) - meanM, 2);
    }

    double stdMeansR = 0;
    double stdMeansM = 0;
    stdMeansR = sumR / (n - 1);
    stdMeansM = sumM / (n - 1);
    stdevM = Math.sqrt(stdMeansM);
    stdevR = Math.sqrt(stdMeansR);
  }

  /**
   * Writes simulation data to a CSV file.
   */
  public void writeCsvData() {
    FileWriter writer;
    try {
      writer = new FileWriter(csvFileName);
      // First row is the attributes/characteristics of the CSV
      // Removed from heading attributes: Launch Rod Angle, Max Angle, Launch Rod Length, Launch Rod
      // Direction, Gimbal Angle, Launch Altitude, Launch Latitude, Luanch Longitude

      writer.write(
          "Launch Rod Angle, Flight Time, Max Altitude, Ground Hit Velocity, Landing Latitude, Landing Longitude, Optimise Launch Angle, "
              + "Distance Mean(Radians), Distance Standard Deviation(Radians), "
              + "Distance Mean(Meters), Distance Standard Deviation(Meters) \n");

      calculateMeanAndStdev(flightObjects);
      boolean line1 = true;

      // Get all instances of the simulation.
      for (FlightObjects d : flightObjects) {

        writer.write(d.startData.getLaunchRodAngle() + ", " + d.flightData.getFlightTime() + ", "
            + d.flightData.getMaxAltitude() + ", " + d.flightData.getGroundHitVelocity() + ", "
            + d.landingSimStatus.getRocketWorldPosition().getLatitudeDeg() + ", "
            + d.landingSimStatus.getRocketWorldPosition().getLongitudeDeg());
        if (line1) {
          writer.write(", " + d.launchRodAngle + ", " + meanR + ", " + stdevR + ", " + meanM + ", "
              + stdevM + "\n");
          line1 = false;
        } else {
          writer.write("\n");
        }
      }
      writer.flush();
      writer.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
