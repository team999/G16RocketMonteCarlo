<div style="page-break-after: always;"></div>

# ENGR 301 Rocket Simulation Project Proposal and Requirements Document
#### Finn Coleman, Ethan Helliwell, David Taing, Dominic Tjiptono, Josh Trueman, and Ryan Yee.

## 1. Introduction

Amateur rocketry has become a popular pastime by rocket enthusiasts with amateur rockets launched globally. Model rockets are typically constructed using commercial and home-made parts and can reach altitudes as high as 100 km. Model rockets are susceptible to a variety of disturbances such as rail-whip, weathercock, motor control software malfunctions, etc. The construction of the rocket is of paramount importance to ensure the rocket can maintain stability even with the effects of such disturbances.

One of the key aspects of amateur rocketry is safety. Careful consideration in construction and launch is always made to ensure that a rocket can not be considered dangerous. Establishing a safe range for the rocket's flight is necessary to ensure there are no threats imposed by the rocket's flight. In addition to range safety, environmental conditions such as weather and wind conditions affect the flight of the rocket and should be considered when selecting the appropriate launch site. 

### Client
The client for this project is Andre Geldenhuis. Andre Geldenhuis is an established member of the rocketry community offering extensive knowledge on rocket development.
Our methods of contact with Andre are: 
- Email: andre.geldenhuis@vuw.ac.nz
- Mattermost
- Lectures 

### 1.1 Purpose

The purpose of the rocket simulator system is to implement a rocket flight simulator to statistically predict the landing sites from multiple simulated rocket flights. This is to determine the range safety of the rocket and the suitability of the launch site.

### 1.2 Scope

The rocket simulator system will allow for multiple flight simulations to be plotted simultaneously. This will increase the efficiency of running simulations drastically. The simulations will use various weather measurements and the rocket's specifications in the simulations to plot accurate possible flight paths to ensure the simulations is accurate to real-time conditions. Once the simulations have been plotted, the software will provide an analysis of flight simulations showing likely landing sites of the rocket. The results of the simulations must also be communicated with mission control teams so the suitability of a given launch site can be determined. The software will also define an upwind rocket vector for the rocket to follow to minimise its landing distance from the launch site.

### 1.3 Product overview
#### 1.3.1 Product perspective 

The rocket simulator acts as a test environment in relation to the other products in the larger rocket system. The software is used to determine predicted flight paths based on varying parameters and weather conditions that would otherwise be left until launch. Using a simulator highly reduces the risk of the rocket posing any danger during launch and flight.

The rocket simulator will be used in all aspects of the rocket system. The simulator will be used to make a virtual design of the hardware teams rocket, it will be needed by the avionics team for simulated PID control provided a software model of the control code is provided and the simulation will also be used by mission control as the simulation will provide an analysis on the safety of a given launch site (this could be done through an API).

<img src="images/Block_Diagram.png" width="400">

#### 1.3.2 Product functions

The minimum viable product is defined as the version of the product with just enough features to satisfy early customers and receive feedback for future development. 

The minimum viable product function requirements that must be met include
- Plotting multiple Monte Carlo simulation flight landing sites
- Use real-time parameters that can be changeable in the simulation
- Communication with mission control with results of the simulations
- Must be open-source (available to use to the wider rocketry community) as per client requested

Function requirements that exceed the minimum viable product include
- Defining an upwind rocket vector for the rocket to follow
- Implementing PID control parameters using a software model of the control code implemented by the avionics team
- Implement an API that can communicate with Mission Control

#### 1.3.3 User characteristics

The software developed will not be exclusive to particular individuals as the client has specifically requested that the software be open-source so it can be used by the wider rocketry community.
Based on this request, the intended group of users for the simulation software is any user who can understand the software and its implementation in the system.
While educational level would be helpful if it is in a relevant field, technical expertise is what would most influence the usability of the simulator when modifying and running the simulation.

Technical expertise required to use the simulator for its intended purpose includes:
- Understanding of OpenRocket
- Understanding of scatterplots and general rocket parameters (e.g velocity, acceleration, etc.) 

#### 1.3.4 Limitations 

The accuracy of the simulation is limited due to the unpredictability of external forces on the rocket. The simulation will use real-time parameters but by the time the rocket is ready to launch, conditions will have changed. Even a small change in launch conditions could affect the rocket due to the smaller size of the rocket.
The safety and security of the simulation results on the launch are also limited due to the simulation providing a prediction rather than a certainty. The simulation will give the most probable landing zones given different parameters however the rocket is not certain to land in such landing sites which has the potential to become a danger however greatly reduced than if no simulations were tested.

## 2. References

[1]"Developer's Guide - OpenRocket wiki", Wiki.openrocket.info, 2020. [Online]. Available: http://wiki.openrocket.info/Developer%27s_Guide. [Accessed: 12- May- 2020].

## 3. Specific requirements

### 3.1 External interfaces

#### Website about Weather

This website contains weather data of the area around where the rocket needs to be launched. Our team members will get the data from this website 6 hours before the rocket launch time to ensure that the data collected is accurate. Accuracy of weather data is important as the data relates to some of the important parameters for the project (i.e. whether it is sunny, raining, or cloudy, air pressure, humidity, temperature, latitude, and longitude, wind direction, and wind speed). Wind direction and speed influence how the rocket launches as the rocket may launch with lower height due to deflection from high wind speed. Also, high wind speed can make the rocket land further from the launch site and is an important environmental factor relating to the safety of the launch.

#### Google Maps

Use the Google Maps API as an underlay for our map that produces landing sites. The centre of the map will be our launching site.

### 3.2 Functions

Stakeholder requirements:

The requirements of our rocket software are as below:

- Be able to determine whether the rocket’s flight will be safe or not from the given parameters (i.e. weather, launch site, and rocket data) and from Monte-Carlo simulations of rocket launches performed as they can generate statistically meaningful landing zone probabilities and flight performance.
- Be able to define an upwind rocket vector for the rocket to follow to minimise its landing distance from the launch site.
- Be able to integrate better than ‘ORK’ file XML documents by using its API.
- Be able to integrate quickly (around 1 to 5 minutes) by using its API. Longer time for the integration process might be needed for improved accuracy.

#### Use Cases

The purpose of our software is to simulate rocket launches without the need of launching any real physical rockets as tests, which can be unsafe and expensive. We may not know what will happen when launching a real rocket, so our software will help determine a safe launch before launching a real rocket. Below are the software’s use cases:

- Prelaunch safety checks
- Assisting/influencing Rocket Design
- Rocket Physics Education
- Open source modification of our software

##### Launchs

Once the software is launched, it should be able to correctly simulate rocket launches as the accuracy of its outputs are extremely important as it relates to the safety of launching real rockets. This is as inaccurate outputs will cause the users to make incorrect judgments which can be unsafe and make them lose a lot of money.

##### Flight Simulation

This is the main functionality of the software. Accurate flight simulation results should be able to be produced regardless of the parameters of the rocket and the surrounding areas (e.g. the rocket’s motor configuration and the weather of the surrounding areas). This is as it might be risky to implement flight simulations in real life just to find out what will happen in a particular situation.

##### File Operation

The software must be able to ensure that files related to rocket simulation must be able to be opened, edited, and saved properly. Loading and saving the files are crucial as we might need to save the files containing flight simulation data in order to edit them after some time instead of starting our work from the beginning due to losing data. It would be even worse if we have forgotten what we have done to the files as we have to rethink what to do to simulate rocket flights.

##### Rocket Optimization

Apart from designing the rocket, optimizing the performance of the rocket is also important. Through this function, the software must allow the users to select which parameter of the rocket should be edited in order to optimize the rocket's performance.

##### Rocket Recovery

During rocket simulation in the software, it should be able to account for the parachute deployment and indicate to the user when the deployment occurs. This will help the user avoid having any objects (including the rocket) damaged and people harmed. In general, recovery systems include one or more techniques from break-apart, streamer, featherweight, parachute, gliding, and helicopter, however, OpenRocket has only implemented parachute recovery. So this is what our software will be using.  

##### Site Data Collection

Relevant data about the rocket launching site coordinates will be obtained through API calls and calculation, such as launch site maps, the gravitational force on the rocket, and weather. This is as the data contains some parameters which influence the performance of the rocket during the launch. For example, wind speed and whether it rains in the site or not both influence the direction of the rocket’s flight and the height the rocket can reach.

### 3.3 Usability Requirements

#### Goal
The goal for this rocket simulator software is to create a program that can estimate and show how any rocket will perform during real-time flight. This software package is open source and should be easily accessible to the whole rocket lab community. 

#### Scenarios

##### Installation

Easy installation and uninstallation are two important things for the software - we do not want to waste time working out how to get the software working as running rocket simulations is our priority for this project. Below are the requirements needed to be met for easy software installation and uninstallation.

- Ensure that the number of files in the packaged software is minimal.
- Minimize the number of dependencies needed in the packaged software to ensure that the users do not have to install too many other packages. This allows the packaged software to work on their devices.


##### Allow users to input their rocket data
As part of the system usability requirement, this software needs to produce multiple simulations for any rocket. The program needs to allow users to be able to enter their own rocket data into the system to generate the simulations. 

##### Change environments data
Before launching the rockets, the software needs to be run on the site to obtain the most accurate data for the launch. 
This means that users must be able to update their current environment data such as the current wind speed, 
temperature and the humidity to allow the simulation to be as accurate as possible. 

##### Produce output logs 
A good practice for our software is the use of Log Files. A Log File is a file that records the events that occur within the software. 
In our case, this is an important feature that we can use to observe the data produced by the simulator. 
There are many uses for this such as to compare simulation outputs, detect abnormal data, or even to try to reproduce output from the same data. 

##### UI
It should only display the required functions for the simulations such as the rocket input and the weather. 
The UI's main focus is to produce a map of the landing sites, altitude graph, and results in a table for the user.


### 3.4 Performance requirements

##### Process speed

Speed is an important factor for our simulation software. It is important since our software will be run at the launch site where we can obtain the most accurate local data for the simulation.
The simulator must be able to generate a simulation within a reasonable time frame so we can run a quick test before the launch. It is not ideal to wait for hours to get the simulation results. Our customer Andre Geldenhuis requires our software to take between 1-5 minutes to produce the simulations.

##### UI

- The UI must display feedback of the user's input within a response time of 0.2 seconds. 
- The UI will be able to scale by the user to match their preference.

##### Error handling
The software must be able to properly handle errors. It should not fail when errors are happening during the simulations. The system should log these errors which can be used to fix these problems[1].

##### Accuracy
For the simulation to produce an accurate result, the user data input must also be accurate. 
An example of this is rocket data such as weight or the current local weather and the current site terrain data.
The output produced must be within a certain range of confidence intervals in order for the simulation to produce reliable data.

### 3.5 Logical database requirements

#### UML Class Diagram
A rough diagram of the potential UML. Below is the code used to create via:
https://plantuml.com/ 

![alt text](images/PlantUML_Simulation.jpg)


##### Class Responsibility/Collaboraion Cards (CRC)
This is a brief outline of the classes that will be developed in the project.
The classes are live documents and are subject to change in time (name of classes included). Each class has been discussed
further and in order of size and importance. 

| Class: Application |   |
| -------- | :--------------------------: |
| Interprets Input commands   |   Input     |
| Knows its state  |   Output     |
| Retrieves stored data  |   Storage     |
| Runs simulations  |        |

The Application is the most important and largest component of the project.
It holds all the simulation programming and is responsible for collaborating with the other three classes, sending data backward and forward. It will potentially
be divided into further classes however for simplicity the class is the main.
The class will also need to be efficient as it implements all the features.


| Class: Output |   |
| -------- | :--------------------------: |
| Displays GUI of application (API)  | Application              |

The Output class is the other half of the GUI for the rocket simulation. It not only
displays static elements such as the tools and window, but also is responsible for
retrieving data from the Application class and presenting it to the users in graphs, tables
and logs. The output will be the second-largest class to develop as aesthetics of the
window design and appropriate formatting need to be considered.


| Class: Data |   |
| -------- | :--------------------------: |
| Stores application data   | Application      |

Data will store logs of the program runtime processes like errors and inputs through the Application class. There will also be simulation data for reporting afterwards and data for testing the program's functions (unnecessary for the users) for developers.


| Class: Input |   |
| -------- | :--------------------------: |
| Gets user input (listener)  |  Application     |
| Stores stack of inputs |             |

This class will listen to all the user inputs/commands, there may be an existing solution through OpenRocket although new inputs will need to be designed for the new features. This should be able to undo and redo old inputs depending on the task being undertaken through the use of a stack and adapt to changes in the application task like states.

A general description of any other items that will limit the supplier's options, including:    
a) Regulatory policies; 
b) Hardware limitations (e.g., signal timing requirements); 
c) Interfaces to other applications;    
d) Parallel operation;  
e) Audit functions; 
f) Control functions;   
g) Higher-order language requirements;  
i) Quality requirements (e.g., reliability);    
j) Criticality of the application;  
k) Safety and security considerations;  
l) Physical/mental considerations.  

OpenRocket will require that for the project we adhere to the regulatory policy that they have in their guidelines. This means the security, coding conventions, and style, there will also be a general regulatory policy for copyright and laws
that restrict the potential of the program.

There will be few hardware limitations as the program will not be too demanding
on the user's machines and no special hardware (e.g servers, radios) will be used.

There will be a restriction on the supplier through OpenRocket interfaces as the two need to link. This was not necessarily a requirement although making our standalone program is possible. There is also the potential for network transition if time allows, so interface interaction from the project could be limited there. 

Higher-order language requirements have not been considered greatly here as we will
try to keep to the same standards as OpenRocket in Java, which is quite diverse.

Safety and security of the program are not too vital as the program is not protecting
user data and is not responsible for user data. Rocket simulation data could be
changed but as it is going to be offline it is hardly a constraint.

### 3.6 Design constraints

##### Open Source
The project requirements specify that the Monte-Carlo Simulation program must be open source and this means that all code will need to be available for sourcing
online. Although, this can only be carried out once the final version of the software
is complete to also abide by assignment plagiarism standards. 

##### Report format
Reports will need to be done for each simulation taking place and therefore formatting is a design constraint on the project because there are standard ways of producing
a specific report. For example with regards to error reports, these must trace
what has happened, giving the appropriate info in the correct format. Another report could be created during the development of the software and related to
changes in code, new bugs, etc. Therefore, the correct format of these progress
reports should be maintained. 

##### Data naming
Much like the report, conventions on data naming will constrain the design of
the project but this will only have a minor effect as this will already be
accounted for when programming and creating content, for example, reports. 

##### Accounting procedures
No accounting will be taking place in this project and therefore there will be
no design constraint. 

#### Physical construction
As this is a software program, we do not have any physical construction constraints. 

### 3.7 Nonfunctional system attributes

#### Open Source Product
Our product will be Open Source Rocket Simulation Software. It will be available
to not only our customer Andre Geldenhuis but the wider community of
Rocket Simulator users. Making our software open source means that it can be modified and expanded on by others. Maintainability and flexibility
are key attributes for this discussed further down.

#### Programming Language
We will be writing our rocket simulation software in Java. This is a language 
our team is all familiar with it.

#### Flexibility
Our product needs to be flexible so that if there are new features to be added to the software, then our product can accommodate these additions. This means we can minimise the changes that need to be made to our existing software. The program doesn't rely on large sections of code.

#### Portability
Our product will be portable meaning it can be used on different operating
systems (Linux and Windows will be the main two to develop our software. The
software will be exported as a JAR file which is used as a plugin for
OpenRocket.

#### Reusability
Our product will need to have reusable components to allow our program to be
built from small modular parts. A key example is writing code in functions/methods. These can be reused in different parts of the program where appropriate. 

#### Reliability
Our product will need to be reliable which requires us to put our program under a testing framework. The rocket simulation software relies on parameters provided
by the user. However, some of these user inputs could cause our program to
behave erratically or run poorly. Errors need to be handled through the use of
exceptions. Variables need to be stored safely such that they are not overwriting existing data that is being used.

Our product will be used to launch a small scale rocket and needs to be reliable to maximise the safety of people and the rocket being used. This means
data has been transferred to and from the software safely so that simulations
are run properly and can be used with confidence.

Overall, good software practices need to be followed to ensure the program is 
reliable and fault-tolerant from any actions within or out of the user's
control.

#### Performance
The performance of our rocket simulation software is important. Data will be
constantly used in different parts of the program, and we need to ensure it is
operating as fast as possible. The software's performance is integral in making
sure any movement of the rocket can be adjusted in time when responding to
external environmental factors such as wind.

#### Maintainability
Our product will need to be maintainable. This means that 
every team member's code for the rocket simulation software is authored 
appropriately, so that sections of code are identifiable and we can communicate. 
Maintainable software contains code that is well documented and easy to read.
This allows other team members to save time understanding code that wasn't
written by them. Since we are using Java to write our rocket simulation
software, we can use CheckStyle and enable other settings to ensure
sections are documented with JavaDocs.

#### Security
The product we are making will be made available to everyone, that is, our customer and the community who uses rocket simulation software. This means
we do not want to store any sensitive information in the source code of our
product as this can be misused.

### 3.8 Physical and Environmental Requirements
Our software extends OpenRocket, so if the user's computer can run OpenRocket without any performance issues, then they can run our program.
OpenRocket requires at least Java version 6 to run, but no later than Java version 8.

### 3.9 Supporting information

#### Installation (README.md file)
Our software package will contain a README.md file detailing how to install our software.

#### JavaDoc
Our program will contain JavaDocs detailing information on how the main 
program works.

#### Manual
We will include a general manual on how to use our software so the user can
access our features.

## 4. Verification

### 4.1 External interfaces:

To verify that the external interfaces from google maps and weather data are meet for the requirements. This will have to be done manually by comparing the actual data against the result from the API that our software grabs. 

### 4.2 Usability Requirements

Installation - To ensure that the installation is easy for the users. This software will be in a single jar file for the user to import into their OpenRocket software. This way the user would not have to import a lot of individual file into their program. 

User Input - To ensure that the user can enter their rocket's information into the simulator. There will be a GUI popup window with texts box area which update as user inputs their data. The GUI will also display information about the simulation which will contain the user input information. 

Change environments data - The changes in the GUI window

Logging - Manually analyse if the logging file contains proper information such as the user's input and any errors that occur[1].

### 4.3 Performane requirements

Performace test - Log run time of simulations generated. 

UI response time - Program a timer to measure the difference between clicking the button and new UI data being sent. 

Error handling - Using unit testing and running the simulations until it converges to check if the simulations produce errors.

Calculation testing - Unit testing on key calculation methods using a large range of valid data.

Platforms testing - Manually run the jar files on Linux(Arch and Ubuntu), Window and Mac. 

### 4.4 Design constraints

Open source - To ensure that the software is open source, we will be using different GitLab account to try access the software. 

Report format - We will manually check the report generated from the software to make sure it meets the required format. 

Data naming - We will be using CheckStyle and manually looking through the code to ensure this. 

### 4.5 Nonfunction system attributes

Programming language - The team agreement on using java to create this software. 

Flexibility - To ensure our software is flexible and easy to extends. We will use the low coupling and high cohesion approach when implementing the software. This will limit our internal data-flow and we could test this by performing data-flow analysis. 


## 5. Development schedule.

### 5.1 Schedule

***Architectural Prototype***
An architectural prototype of the project will be completed by 18th June 2020.

***Minimum Viable Product***
The minimum viable product will be completed by 22nd August 2020.

***Final Releases***
The product will be released by 15th October 2020.


### 5.2 Budget

Currently, there is no budget for this project. We are developing with free and open-source software on computer systems we already own.

In the unlikely event that we require paid software or hardware testing components to complete the project and there is no viable free alternative, we will have a discussion with the senior manager.

### 5.3 Risks

| Risk | Type | Likelihood | Impact | Mitigations |
| ------ | ------ | ------ | ------ | ------ |
| Group member leaves | Resource | Unlikely | Moderate | Immediate rescheduling and time analysis. Reallocate responsibilities and potentially reduce the scope of the project. |
| Motivation burnout | Resource | Likely | Low - Moderate | Account for lack of motivation in out time management. Change up a member's tasks to avoid tedium. |
| Programming strain injury | Health/Safety | Possibly | Low | Enforce regular breaks from long periods of programming. Ensure members are aware of good ergonomic practices. Ensure frequently run tests and other dev actions have clicks minimised. |
| Extended loss of internet for member | Technology | Very Unlikely | Low | Situation dependent. Work from uni if possible or source alternate internet source. |
| Scheduling flaws | Management | Likely | Low | Involve the whole group in planning and scheduling. Reevaluate our timeframes as the project progresses and check in on progress with members. |
| Software requirements inflation | Management | Possibly | Low - Moderate | Constantly involve the customer in the project to minimise the scope. Evaluate the necessity of features before adding them to the to-do list. |

### 5.4 Health and Safety

***Computer-related health and safety***

Computer health best practices should be followed at all times to avoid accidents. These include maintaining good ergonomics, taking regular breaks and checking for hazards.

The project does not require work or testing at an external workplace or site or testing with any human or animal subjects.

As this project will be developed at Victoria University campus, we need to follow the ECS Health and Safety policies.


#### 5.4.1 Safety Plans

The project requirements do not involve the risk of death, serious harm, or injury.

## 6. Appendices
### 6.1 Assumptions and dependencies 

The following describes the Assumptions and dependencies of the Rocket Simulation.
- The rocket simulation depends on OpenRocket, the software we will be modifying. Without OpenRocket, the software to be designed becomes largely out of scope.
- The simulator is run on Java and therefore is dependent on the user owning Java.
- As parameters such as wind speed, direction and air pressure can unpredictably change throughout the flight of the rocket, the simulation assumes that these parameters are constant.
- The simulation depends on correct flight path calculations. Incorrect flight path calculations would lead to largely inaccurate simulations rendering the simulation redundant.
- The rocket simulation depends on processing speed. The simulation is expected to be run at the launch site so it is expected that the simulations are generated before changes in weather or environment can occur.
- The rocket simulation assumes that the rocket will fly without events such as motor malfunction, tailfin breaks or parachute deployment issues.

### 6.2 Acronyms and abbreviations

IEEE (Institute of Electrical and Electronics Engineers): Professional association for electrical, electronic and other related engineering disciplines.

XML (Extensible Markup Language): A language that defines a set of rules for encoding documents in both human and machine-readable format.

API (Application Programming Interface): An interface that allows for interactions between multiple software intermediaries.

UI (User Interface): The series of screens, pages and visual elements the user will see when running the program.

UML (Unified Modelling Language): A modelling language used by developers to visualise the design of the system.

CRC (Class Responsibility Collaboration): A development tool used to describe how software components collaborate and what they can do individually.

JAR (Java ARchive): A Java package file format typically aggregating multiple Java class files and supporting resources.

## 7. Contributions

A one-page statement of contributions, including a list of each member of the group and what they contributed to this document.

| Name | Contributions |
| ------ | ------ |
| Finn Coleman | 5.1, 5.2, 5.3, 5.4 |
| Ethan Helliwell | 3.5, 3.6 | 
| David Taing | 3.3, 3.4, 4 |
| Dominic Tjiptono | 3.1, 3.2 |
| Josh Trueman | 1.1, 1.2, 1.3.1, 1.3.2, 1.3.3, 1.3.4, 6.1, 6.2|
| Ryan Yee | 2, 3.7, 3.8, 3.9 |
---

