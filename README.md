README

## How to run the Rocket Simulation Software

1. Make sure you have setup the following
   1. Java Version 8
   2. .ork rocket file
   3. .csv flight variables file

2. Navigate to the file location using the command line

3. for the next command line input, there are 10 important arguments
    1. jarname.jar - the name of the simulation software jar file
    2. filepath/orkfile.ork - file path to your .ork file that has the rocket configuration
    3. filepath/csvfile.csv - file path to your .csv file that has average flight parameter values
    4. outputpath - the file path to the simulation output file that has a gaussian distribution
    5. numberOfSims - number of simulation you want to run
    6. standardDeviationFromAverage1 - double whole number representing the % away from the average csv value (can leave these at 0 for no variance)
    7. standardDeviationFromAverage2 - etc...
    8. etc...
    10. standardDeviationFromAverage5 - etc...

4. Type 'java -jar jarname.jar filepath/orkfile.ork filepath/csvfile.csv outputpath numberOfSims -v1 standardDeviationFromAverage1 -v2 standardDeviationFromAverage2 -v3... etc...'

e.g "java -jar SimulationGeneratorExecutable.jar src/main/resources/RocketDesign.ork src/main/resources/dummyData.csv output.csv 10 -v1 2"

5. The command line will then output a lot of logging data and finally finish with "successfully written CSV data"


## Units of Data

### Data Read In

- Launch Rod Angle: Radians (°)
	Maximum Angle: 1/3 PI (60°)
	Minimum Angle: -1/3 PI (-60°)
- Launch Rod Length: Centimetres (cm)
- Launch Rod Direction: Degrees (°)
- Launch Altitude: Metres (m)
- Launch Latitude: Degrees North (°N)
- Launch Longitude: Degrees East (°E)
- Maximum Angle: Degrees (°)
- Wind Direction: Degrees (°)
- Wind Turbulence: Percentage (%)
- Launch Temperature: Degrees Celsius (°C)
- Launch Air Pressure: Millibar (mbar)

### Data Written Out

- Time (s)
- Altitude (m)
- Vertical velocity (m/s)
- Vertical acceleration (m/s²)
- Total velocity (m/s)
- Total acceleration (m/s²)
- Position East of launch (m)
- Position North of launch (m)
- Lateral distance (m)
- Lateral direction (°)
- Lateral velocity (m/s)
- Lateral acceleration (m/s²)
- Latitude (°)
- Longitude (°)
- Gravitational acceleration (m/s²)
- Angle of attack (°)
- Roll rate (°/s)
- Pitch rate (°/s)
- Yaw rate (°/s)
- Mass (g)
- Propellant mass (g)
- Longitudinal moment of inertia (kg·m²)
- Rotational moment of inertia (kg·m²)
- CG location (cm)
- Mach number (?)
- Reynolds number (?)
- Thrust (N)
- Drag force (N)
- Drag coefficient (?)
- Axial drag coefficient (?)
- Friction drag coefficient (?)
- Pressure drag coefficient (?)
- Base drag coefficient (?)
- Coriolis acceleration (m/s²)
- Reference length (cm)
- Reference area (cm²) 
- Vertical orientation (zenith) (°)
- Lateral orientation (azimuth) (°)
- Wind velocity (m/s)
- Air temperature (°C)
- Air pressure (mbar)
- Speed of sound (m/s)
- Simulation time step (s)
- Computation time (s)
