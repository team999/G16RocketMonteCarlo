package nz.ac.vuw.ecs.group16.openrocketextension;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Logger;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.document.Simulation;
import net.sf.openrocket.file.GeneralRocketLoader;
import net.sf.openrocket.file.RocketLoadException;
import net.sf.openrocket.gui.main.Splash;
import net.sf.openrocket.gui.main.SwingExceptionHandler;
import net.sf.openrocket.plugin.PluginModule;
import net.sf.openrocket.rocketcomponent.Rocket;
import net.sf.openrocket.simulation.FlightData;
import net.sf.openrocket.simulation.SimulationOptions;
import net.sf.openrocket.simulation.SimulationStatus;
import net.sf.openrocket.simulation.exception.SimulationException;
import net.sf.openrocket.startup.Application;
import net.sf.openrocket.startup.GuiModule;
import net.sf.openrocket.util.WorldCoordinate;
import nz.ac.vuw.ecs.group16.openrocketextension.CSVReader.CSVInData;

/**
 * Main monte-carlo generation class. Takes simulation options and outputs simulation data.
 * 
 * @author David, Ryan, Finn, Josh, Dominic
 *
 */
public class SimulationGenerator {
  private static final Logger logger = Logger.getLogger(SimulationGenerator.class.getName());

  /**
   * Stores all objects from one simulation run.
   * 
   * @author Finn, David
   *
   */
  public class FlightObjects {
    FlightData flightData = null;
    SimulationStatus landingSimStatus = null;
    SimulationStatus maxHeightSimStatus = null;
    double launchRodAngle = 0;
    SimulationOptions startData = null;

    public FlightObjects(FlightData fd, SimulationStatus ls, SimulationStatus ms, double lra,
        SimulationOptions startData) {
      this.flightData = fd;
      this.landingSimStatus = ls;
      this.maxHeightSimStatus = ms;
      this.launchRodAngle = lra;
      this.startData = startData;
    }
  }

  // Generates random data
  private Random randomMachine = new Random();

  File file;
  Rocket rocket = new Rocket();
  OpenRocketDocument doc = null;
  GeneralRocketLoader loader;

  int count;
  int[] variations = {10, 10, 10, 10, 10};

  public SimulationGenerator() {
    logger.info("Starting SimulationGenerator...");

    // OR init code. Required to run sims.
    Splash.init();

    SwingExceptionHandler exceptionHandler = new SwingExceptionHandler();
    Application.setExceptionHandler(exceptionHandler);
    exceptionHandler.registerExceptionHandler();

    // Load motors etc.log.info("Loading databases");
    logger.info("Loading databases... (motors, rocket components etc.)");
    GuiModule guiModule = new GuiModule();
    Module pluginModule = new PluginModule();
    Injector injector = Guice.createInjector(guiModule, pluginModule);
    Application.setInjector(injector);

    guiModule.startLoader();
  }

  /**
   * Creates an OR sim options object from given data.
   * 
   * @param launchRodAngle
   * @param launchRodDirection
   * @param launchRodLength
   * @param altitude
   * @param launchLatitude
   * @param launchLongitude
   * @param maximumAngle
   * @param windAverage
   * @param windDirection
   * @param intensity
   * @param launchTemperature
   * @param launchPressure
   * @return
   */
  private SimulationOptions customSimulation(double launchRodAngle, double launchRodDirection,
      double launchRodLength, double altitude, double launchLatitude, double launchLongitude,
      double maximumAngle, double windAverage, double windDirection, double intensity,
      double launchTemperature, double launchPressure) {
    logger.info("Creating custumSimulation...");
    SimulationOptions customSim = new SimulationOptions(this.rocket);
    // Start at 0
    customSim.setLaunchRodAngle(launchRodAngle);

    customSim.setLaunchRodDirection(launchRodDirection);
    customSim.setLaunchRodLength(launchRodLength);
    customSim.setLaunchAltitude(altitude);
    // Set to 0 for lat and long
    customSim.setLaunchLatitude(launchLatitude);
    customSim.setLaunchLongitude(launchLongitude);

    // Calculate this
    customSim.setMaximumStepAngle(maximumAngle);

    customSim.setWindSpeedAverage(windAverage);
    customSim.setWindDirection(windDirection);
    customSim.setWindTurbulenceIntensity(intensity);
    customSim.setLaunchTemperature(launchTemperature);
    customSim.setLaunchPressure(launchPressure);

    return customSim;
  }

  /**
   * Loads in an ORK file of rocket data and saves to this.
   * 
   * @param path to ork file
   */
  public void loadOrk(String path) {
    logger.info("Load Ork...");
    this.file = new File(path);
    this.loader = new GeneralRocketLoader(this.file);
    try {
      this.doc = this.loader.load();
      this.rocket = this.doc.getRocket();
      logger.info("Successfully loaded ork file: " + rocket.getName());

    } catch (RocketLoadException e) {
      // TODO Auto-generated catch block
      logger.warning("Failed to load ork file: " + rocket.getName() + " check file name");
    }
  }

  /**
   * Generates all simulation options from the CSV input data.
   * 
   * @param dataFromCSV
   * @return all sim options
   */
  public ArrayList<Simulation> generateSimulationOptions(CSVInData dataFromCSV) {

    ArrayList<Simulation> newSims = new ArrayList<>();

    // Constant var
    double launchAltitude = dataFromCSV.launchAltitude;
    double launchLatitude = dataFromCSV.launchLatitude;
    double launchLongitude = dataFromCSV.launchLongitude;
    double launchMaxAngle = dataFromCSV.maximumAngle;
    double windAverage = dataFromCSV.windSpeed;
    double windIntensity = dataFromCSV.windTurbulence;
    double launchRodLength = dataFromCSV.launchRodLength;

    // Make a list of SimulatonOptions that will be used to generate Simulation
    // objects
    for (int i = 0; i < this.count; i++) {
      // Find variations for each parameter
      double launchRodAngleVaried = getGaussian(dataFromCSV.launchRodAngle, variations[0]);
      double windDirectionVaried = getGaussian(dataFromCSV.windDirection, variations[1]);
      double launchRodDirectionVaried = getGaussian(dataFromCSV.launchRodDirection, variations[2]);
      double launchTemperatureVaried = getGaussian(dataFromCSV.launchTemperature, variations[3]);
      double launchPressureVaried = getGaussian(dataFromCSV.launchAirPressure, variations[4]);


      SimulationOptions customSim =
          customSimulation(launchRodAngleVaried, launchRodDirectionVaried, launchRodLength,
              launchAltitude, launchLatitude, launchLongitude, launchMaxAngle, windAverage,
              windDirectionVaried, windIntensity, launchTemperatureVaried, launchPressureVaried);

      newSims.add(new Simulation(this.rocket, customSim));
    }
    return newSims;
  }

  /**
   * Takes decimal variance factor and outputs a varied value within the normal dist.
   * 
   * @param aMean
   * @param aVariance
   * @return gaussian varied value
   */
  private double getGaussian(double aMean, double aVariance) {
    logger.info("Getting Gaussian variables");
    if (aVariance == 0)
      return aMean;
    return aMean + randomMachine.nextGaussian() * aVariance * aMean;
  }

  /**
   * Generates sim options from input data, runs the sims, and outputs an array of data.
   * 
   * @param dataFromCsv
   * @return list of flight data
   */
  public ArrayList<FlightObjects> generateSimulation(CSVInData dataFromCsv) {
    logger.info("Generating Simulations with CSV data...");
    ArrayList<Simulation> allSims = generateSimulationOptions(dataFromCsv);

    ArrayList<FlightObjects> allData = new ArrayList<>();

    // Running each sim
    for (Simulation s : allSims) {
      try {
        SimulationOptions startData = s.getOptions();
        WorldCoordinate worldCoord = new WorldCoordinate(s.getOptions().getLaunchLatitude(),
            s.getOptions().getLaunchLongitude(), s.getOptions().getLaunchAltitude());

        LandingSimulationListener landingSimListener = new LandingSimulationListener();
        MaxHeightSimulationListener maxHeightSimListener = new MaxHeightSimulationListener();
        s.simulate(landingSimListener, maxHeightSimListener);
        s.getOptions().getLaunchRodAngle();

        allData.add(new FlightObjects(s.getSimulatedData(), LandingSimulationListener.simStatus,
            MaxHeightSimulationListener.simStatus, s.getOptions().getLaunchRodAngle(), startData));

        logger.info("Successfully added simulation data");

      } catch (SimulationException e) {
        logger.warning("warning adding simulation data to array");
      }
    }

    return allData;
  }

  /**
   * Checks that the ORK path specified in the options is valid.
   * 
   * @param path
   * @return true if valid
   */
  public static boolean isOrkPathValid(String path) {

    try {
      if (!path.endsWith(".ork")) {
        return false;
      }

      Paths.get(path);

    } catch (InvalidPathException ex) {
      return false;
    }

    return true;
  }

  /**
   * Checks if specified input path is valid and can be read.
   * 
   * @param path
   * @return true if valid
   */
  public static boolean isInputPathValid(String path) {

    try {
      if (!path.endsWith(".csv")) {
        path = path + ".csv";
      }

      Paths.get(path);

    } catch (InvalidPathException ex) {
      return false;
    }

    return true;
  }

  /**
   * Checks if specified input path is valid and can be read.
   * 
   * @param path
   * @return valid path with .csv on end
   */
  public static String isOutputPathValid(String path) {
    try {
      if (!path.endsWith(".csv")) {
        path = path + ".csv";
      }
      Files.isWritable(Paths.get(path));
    } catch (InvalidPathException ex) {
      System.out.println("ERROR: Invalid argument [1]. Must enter a valid input path");
      System.exit(1);
    }
    return path;
  }

  /**
   * Parses the count value from the user.
   * 
   * @param args
   * @param index
   * @return number from param stirng
   */
  private int parseNum(String args, int index) {
    try {
      int num = Integer.parseInt(args);
      return num;
    } catch (Exception e) {
      System.out.println("ERROR: Invalid argument [" + index + "]. Must enter a number for count");
      System.exit(1);
    }
    return 0;

  }

  /**
   * Takes the flight data from sims and gets the best launch angle based on distance from launch.
   * 
   * @param lat
   * @param lon
   * @param outputData
   * @return optimum radians angle
   */
  public double getOptimalLaunchAngle(double lat, double lon, ArrayList<FlightObjects> outputData) {
    final int R = 6371; // Radius of the earth
    FlightObjects bestSoFar = null;
    double minDist = Double.MAX_VALUE;
    for (FlightObjects f : outputData) {
      double latDistance =
          Math.toRadians(f.landingSimStatus.getRocketWorldPosition().getLatitudeDeg() - lat);
      double lonDistance =
          Math.toRadians(f.landingSimStatus.getRocketWorldPosition().getLongitudeDeg() - lon);
      double a =
          Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(Math.toRadians(lat))
              * Math
                  .cos(Math.toRadians(f.landingSimStatus.getRocketWorldPosition().getLatitudeDeg()))
              * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
      double distance = R * c * 1000; // convert to meters

      if (distance < minDist) {
        minDist = distance;
        bestSoFar = f;
      }
    }

    return bestSoFar.launchRodAngle;
  }

  /**
   * Takes command line params for ORK file, input file, output file, count, and optionally variance
   * values.
   * 
   * @param args
   */
  public static void main(String[] args) {

    // Initialising a simulation generator and CSV reader to allow the user to read
    // CSV data
    SimulationGenerator simGen = new SimulationGenerator();
    int count = 0;
    String orkPath = args[0];
    if (!isOrkPathValid(orkPath)) {
      System.out.println("ERROR: Invalid argument [0]. Must enter a valid ork path");
      System.exit(1);
    }

    String inputPath = args[1];
    if (!isInputPathValid(inputPath)) {
      System.out.println("ERROR: Invalid argument [1]. Must enter a valid input path");
      System.exit(1);
    }
    String outputPath = isOutputPathValid(args[2]);

    try {
      count = Integer.parseInt(args[3]);

    } catch (Exception e) {
      System.out.println("ERROR: Invalid argument [3]. Must enter a number for count");
      System.exit(1);
    }

    if (args.length > 4) {
      // Check if the option to change the variation are valid. Must contain the type
      // and their value

      if (!((args.length - 4) % 2 == 0)) {
        System.out.println(
            "ERROR: Invalid argument for optional distributions parameter. Missing the type or their value.");
        System.exit(1);
      }
      for (int i = 4; i < args.length; i += 2) {
        if (args[i].equals("-v1")) {
          simGen.variations[0] = simGen.parseNum(args[i + 1], i + 1);
        } else if (args[i].equals("-v2")) {
          simGen.variations[1] = simGen.parseNum(args[i + 1], i + 1);
        } else if (args[i].equals("-v3")) {
          simGen.variations[2] = simGen.parseNum(args[i + 1], i + 1);
        } else if (args[i].equals("-v4")) {
          simGen.variations[3] = simGen.parseNum(args[i + 1], i + 1);
        } else if (args[i].equals("-v5")) {
          simGen.variations[4] = simGen.parseNum(args[i + 1], i + 1);
        } else {
          System.out
              .println("ERROR: Invalid option distribution. Please use -v1,-v2,-v3,-v4 or -v5.");
          System.exit(1);
        }
      }
    }

    CSVReader csvReader = new CSVReader();
    ArrayList<FlightObjects> outputData = new ArrayList<FlightObjects>();
    simGen.count = count;

    simGen.loadOrk(orkPath);
    try {
      csvReader.readCSV(inputPath);
    } catch (IllegalArgumentException | IllegalAccessException | IOException e) {
      e.printStackTrace();
    }

    outputData = simGen.generateSimulation(csvReader.CSVData);

    double optimalLaunchAngle = simGen.getOptimalLaunchAngle(csvReader.CSVData.launchLatitude,
        csvReader.CSVData.launchLongitude, outputData);

    for (FlightObjects d : outputData) {
      System.out.printf(
          "Flight time: %f\tMax height: %f\tMax height time: %f\tGround hit vel: %f\tLanding co-ords: %f %f\n",
          d.flightData.getFlightTime(), d.flightData.getMaxAltitude(),
          d.maxHeightSimStatus.getMaxAltTime(), d.flightData.getGroundHitVelocity(),
          d.landingSimStatus.getRocketWorldPosition().getLatitudeDeg(),
          d.landingSimStatus.getRocketWorldPosition().getLongitudeDeg());
    }

    CSVWriter csvWriter = new CSVWriter(outputPath, outputData);

    // Writing CSV data
    try {
      csvWriter.writeCsvData();
      logger.info("Successfully written CSV data.");
    } catch (Exception e) {
      logger.info("Failed to write CSV data.");
    }
  }
}
