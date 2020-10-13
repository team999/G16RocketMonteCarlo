# ENGR 301 Project G16OpenRocketExtension Architectural Design and Proof-of-Concept

**Authors:** Finn Coleman, Ethan Helliwell, David Taing, Dominic Tjiptono, Josh Trueman and Ryan Yee

## 1. Introduction
Amateur rocketry has become a popular pastime by rocket enthusiasts with amateur rockets launched globally. Model rockets are typically constructed using commercial and home-made parts and can reach altitudes as high as 100 km. Model rockets are susceptible to a variety of disturbances such as rail-whip, weathercock, motor control software malfunctions, etc. The construction of the rocket is of paramount importance to ensure the rocket can maintain stability even with the effects of such disturbances.

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

### 1.3 Changes to requirements

If the requirement has changed significantly since the requirements document, outline the changes here. Changes must be justified and supported by evidence, i.e., they must be substantiated. (max one page, only if required)

## 2. References

[1]"Developer's Guide - OpenRocket wiki", Wiki.openrocket.info, 2020. [Online]. Available: http://wiki.openrocket.info/Developer%27s_Guide. [Accessed: 12- May- 2020].

## 3. Architecture
### 3.1 Stakeholders
#### Stakeholder requirements
##### Client

- Rocket software shall be able to determine the safety of a rocket's flight with the given parameters (i.e. weather, launch site, and rocket data). In this case, being able to correctly collect data about the launch site and weather in the launch site and surroundings is important.
- The files related to rocket simulation must be human-readable.
- The files related to rocket simulation must be able to be loaded, edited, and saved properly.
- Rocket software shall be able to define an upwind rocket vector for the rocket to follow to minimise its landing distance from the launch site.

##### Software Developers / Testers

- Rocket software’s integration time should be short (1 to 5 minutes).
- Rocket software’s test cases should be accurate with high test coverage to ensure the safety of the rocket flights when the software is launched.
- Modules used in the rocket software must be well organised so that modifying/updating the software in case of errors/bugs/required changes becomes easy.

##### Wellington Region Residents

- It is essential that the rocket software is reliable for all Wellington residents and conforms to all relevant safety regulations and guidelines so that usage of the software does not harm anyone.

##### Wider Rocketry Community

- The software must be open source so that other licensed users in the community can utilise the benefits the software will provide to future rocket launches.

##### Local Council

- The rocket must operate without violating any laws or regulations related to the usage of model rockets in Wellington. This is to avoid damage to people or properties during the rocket’s operation.

##### School of Engineering and Computer Science (ECS)

- ECS requires the rocket software to be licensed correctly.

##### Partners (Other project teams) 
- The software will return results that can be communicated with mission control to determine whether the launch should go ahead or not.

#### Stakeholder Concerns Table

| Stakeholder | Concerns |
| ----------- | -------- |
| School of Engineering and Computer Science (ECS) | <ul><li>Correctness of rocket software’s licensing</li><li>Whether the rocket software follows ECS guidelines or not.</li></ul> |
| Local Council | <ul><li>Safety of rocket operation</li></ul> |
| Wider Rocket Community | <ul><li>Ability to let licensed users to make use and edit the source code of the rocket software.</li></ul> |
| Local Residents | <ul><li>Reliability of rocket software for all Wellington Region residents</li></ul> |
| Software Developers / Testers | <ul><li>Integration time</li><li>Correctness of test cases</li><li>Organisation of modules in the software</li></ul> |
| Partners | <ul><li>Simulation results can be communicated to other teams to determine launch status.</li></ul> |
| Client | <ul><li>Readability of files related to rocket software</li><li>Ability to load, save, and edit files related to rocket software properly</li><li>Accuracy of launch site and weather data collected.</li><li>Whether the rocket software can correctly determine safety of rocket flights with given parameters (i.e. weather, launch site, and rocket data) or not.
</li></ul> 

### 3.2 Architectural Viewpoints
#### Logical

This viewpoint relates to what the software provides to the end-users. End-user terminology is used. Also, a UML class diagram is used to express this viewpoint. This viewpoint frames the concerns of the customer, the wider rocketry community and partner teams as they will all be using the software as an end-user.

#### Process

This viewpoint addresses concurrency and the design aspects related to synchronization. Also, it deals with dynamic and non-functional system requirements. To express this viewpoint, a UML activity diagram can be used.

#### Physical/Circuit Architecture

This viewpoint describes the mapping from the software to the hardware. UML deployment diagrams can express physical viewpoints. This viewpoint frames the concerns posed by nearby residents and the local councils as the software's indirect effect on the hardware has the potential to cause a breach of regulations or damage to nearby property.  

#### Development

This viewpoint relates to the software’s static organisation. UML component diagrams can express development viewpoints. This viewpoint frames the concerns from Software Developers / Testers in regards to module organisation and correctness.

#### Hardware Architecture

This viewpoint relates to the means and purposes for the interaction between the software and systems with the hardware. It also takes into account the ways the hardware can make the overall system able to function. This frames the concerns from Partner teams in the project as the software must be able to integrate smoothly with other teams operations.

#### Scenarios

This viewpoint describes sequences of interactions between objects and processes. Also, all other views in the system are stuck together. Other purposes of this viewpoint are to illustrate and validate the architecture and for identification of elements. This frames the concerns posed by Partner teams and software developers/testers who will need a deeper understanding of the software's architecture as opposed to just any end-user.

### 4. Architectural Views

You should include views from the following viewpoints (from Kruchten's 4+1 model):

 * Logical
 * Development
 * Process
 * Physical 
 * Scenarios - present scenarios illustrating how two of your most important use cases are supported by your architecture

As appropriate you should include the following viewpoints:

 * Circuit Architecture
 * Hardware Architecture

Each architectural view should include at least one architectural model. If architectural models are shared across views, refer back to the first occurrence of that model in your document, rather than including a separate section for the architectural models.

### 4.1 Logical
![Class Diagram](images/UML_Class_Diagram.png)
The software is divided into 2 main sections, the graphical display sections and the internal data sections.
The data section consists of the following classes:
- Integration: This class interfaces with OpenRocket and pulls simulation data out to format and store as Data.
- Data: This class stores simulations and with handle processing OpenRocket data.
- Simulation: This class will store data for a single simulation event and will contain methods to extract different attributes of the event.
- Logger: This is a general purpose logger class to log the data flow and program events in the plugin.

The graphing and display section consists of the remaining classes:
- Display: This class manages UI windows, frames and displaying. It holds the different views of the table and current graph.
- UserInput: This class handles user inputs on the plugin window, such as changing graph or selecting a single simulation to view.
- GraphicalView: This is an abstract class that is used to render the display in the graphing windows based on the simulation data.
- MapView: This class extends GraphicalView and will grab a map from the Google API and underlay it under simulation plots.
- LineGraphView: This class extended GraphicalView and will plot simulation data from the data class as a time vs altitude lien graph.
- DataTable: This class will process data from the data class and sort it into a table and calculate averages. THis will be rendered by the display on the right of the window.

### 4.2 Development
There are few key software components of the system.
- OpenRocket: The OR core program is used as a backend base for our extension. Its simulation functions are used and queried by the extension layer on top. There will be some minor additions and modifications to the OR software backend to make it more compatible with the extension.
- G16OpenRocketExtension: The extension provides a GUI framework to display simulation data. The data is fetched from the OR core software, which is made compatible with minor changes.
- Google Maps API: This is an external framework to be accessed by the extension via the API gateway. Data such as static map underlay images will be fetched.

### 4.3 Process
The process of using our OpenRocket plugin involves inputting variables and generating multiple simulations. These simulations can be displayed in the Line Graph view (e.g multiple lines showing altitude vs time) or the Landing Site Map view. For each view, the user can choose to display a single landing site/line of data or all landing sites/lines of data. The user can choose to generate a new set of simulations.

![Process Diagram](images/Updated_Section_4.3_Process_Diagram.png)
![GUI Map Diagram](images/GUI_map_design.png)
![GUI Graph Diagram](images/GUI_graph_design.png)

### 4.4 Physical 
Our G16 plugin will depend on the OpenRocket software to generate simulations. We will use Google Maps to underlay our Landing Site Map once the simulations have been generated.

![Deployment Diagram](images/Deployment_Diagram.png)

### 4.5 Scenarios
##### Use Case 1: Jacqueline has started up OpenRocket which is using our plugin and wants to generate the multiple simulations.

| User | System |
| ------ | ------ |
| User will input their rocket and environment data into OpenRocket | |
| | The system will display an input area to allow the user to enter this data |
| | The system will generate multiple simulations based on the user's inputted data |

##### Use Case 2: Blake has generated his simulations and would like to see all the visual representations of the outputs.

| User | System |
| ------ | ------ |
| User wants to see a visual display of the simulation data | |
| | System displays a default window showing the landing sites of all simulations |
| | The window of the system will contain tabs to allow the user to switch between the line graph data and landing site map |
| | The window will contain a button to allow the user to generate a new set of simulations |
| | The window will contain the table of the data and a "Display All" button on the right-hand side |
| The user wants to switch to show the line graph data | |
| | The system will switch tabs to display data as a line graph |
| The user wants to switch to show the landing site map | |
| | The system will switch tabs to display data as the landing site map |

##### Use Case 3: Aaron has generated his simulations and the window is showing all data points on the landing site map by default. However, he would like to see a specific landing site on the map.

| User | System |
| ------ | ------ |
| User wants to see the data of a specific landing site on the map. | |
| | System highlights data on the right-hand side of the window when the user selects the landing site on the map |
| User wants to see a specific landing site on the map from a given line of data | |
| | System only displays a specific landing site on the map when the user selects a single line of data |

##### Use Case 4: Denise is currently viewing the landing site map. She would like to spread the data out for a better view by zooming in on the map. Denise zooms in too far and wants to display the data points in a tighter group, so she uses the zoom out feature.

| User | System |
| ------ | ------ |
| Denise wants to zoom in on the landing site map | |
| | The system will display a zoom-in button which Denise clicks to zoom in on the landing site map |
| Denise wants to zoom out on the landing site map | |
| | The system will display a zoom out button which Denise clicks to zoom out on the landing site map |

##### Use Case 5: Timothy is currently viewing the landing site map. However, he wants to shift his current view to see more of the surrounding environment, so he uses the movement arrows to shift his view of the map.

| User | System |
| ------ | ------ |
| Timothy wants to shift his view of the landing site map | |
| | The system will display movement arrow buttons which Timothy clicks to move his view of the landing site map |

## 5. Development Schedule
### 5.1 Schedule

Identified dates for key project deliverables:

***Architectural Prototype***
An architectural prototype of the project will be completed by 18th June 2020.

***Minimum Viable Product (First deployment to the Client)***
The minimum viable product will be completed by 22nd August 2020.

***Final Releases (After further improvements required by the Client)***
The product will be released by 15th October 2020.

### 5.2 Budget and Procurement
#### 5.2.1 Budget

Below is the presented budget for the project, showing the amount of expenditure the project requires and the date on which it will be incurred. Each budget item has been substantiated by reference to the fulfilment of project goals.

As of the requirements document, there have been no foreseen incurring costs to this project that would require a budget of any kind. We are working on personal devices and university supplied equipment. We are using open source programs such as Eclipse, Gitlab, and OpenRocket as these are suitable to complete our project. 

As previously stated, in the unlikely event that we require paid software or hardware testing components to complete the project and there is no viable free alternative, we will discuss with the senior manager.

| Item                | Purpose of Item | Cost Per Unit |
|---------------------|-----------------|---------------|
| Personal Computer   | Hardware for working remotely or locally, there will be one for each member and each will be different. This is to make the team more familiar with their devices and is the main platform for all the following software required.             | $0.00 NZD - March 25 |
| University Computer | These hardware devices are not as essential or accessible for the project but will be required to maintain workability with the University and when personal computers are unavailable              | $0.00 NZD - March 25 |
| OpenRocket Software | To understand how the users should interact with our plugin, the system of the original parent software should be fully understood. The Monte-Carlo simulation must flow from the data in the main system which is why this is possibly the most important item for reaching project goals             | $0.00 NZD - March 25    |
| Eclipse/IntelliJ    | These are standard IDE's for developing Java which we have chosen as our primary programming language for the plugin. They can integrate with Gitlab and OpenRocket's software packages which keeps the Monte-Carlo package consistent.            | $0.00 NZD - March 25    |
| Gitlab              | This software is a web-based tool that allows our team to continuously work remotely and makes our project open source. Gitlab includes a standardized way of integrating/updating work to maintain the management of the project | $0.00 NZD - March 25   |
| MatterMost          | A communication tool which has kept our team connected with the client and other teams alike to maintain alignment with project standards. The tool also keeps a log of all our Gitlab activity.              | $0.00 NZD - March 25   |
| Google Maps API     | The API is going to overlay in our plugin so that the user can get a more immersive and accurate result for simulation plotting             | $0.00 NZD - March 25   |
| Discord             | This has allowed us to continue to communicate effectively throughout the past months. As we have been restricted in our homes               | $0.00 NZD - March 25   |

#### 5.2.2 Procurement

Below is a table of goods or services that will be required to deliver project goals and specify how they are to be procured (e.g. from the School or from an external organisation). These include software applications, libraries, training or other infrastructure, including open-source software. Each item has been justified with procurement substantiated regarding the fulfilment of project goals.

##### Software
| Item                | Source   | Related Project Goals |
|---------------------|----------|-----------------------|
| OpenRocket Software | http://openrocket.info/ Desktop app       | Consistency and plugin integration with the main system                    |
| Eclipse/IntelliJ    | https://www.eclipse.org/ide/ and https://www.jetbrains.com/idea/ Desktop app      | Plugin development and testing                   |
| Gitlab              | https://about.gitlab.com/ Web-based      | Project management and plugin development                  |
| MatterMost          | https://mattermost.com/ Web-based      | Project management and communication with other teams and client                       |
| Discord             | https://discord.com/ Web-based/Desktop app     | Communication between team members and remote development                   |


##### Hardware
| Item                | Source              | Related Project Goals |
|---------------------|---------------------|-----------------------|
| Personal Computer (PC) | Personal Acquirement   | Planning, programming, running, and testing the software and the project management |
| University Computer | ECS Victoria University | Same goal for PC item but less responsibility for software being used   |


### 5.3 Risks 

Below are the ten most important risk's as follows from the requirements document. A few changes have been made to the ordering and impacts after further consideration was made.

| Risk                                 | Type          | Likelihood    | Impact         | Mitigations                                                                                                                                                                             |
|--------------------------------------|---------------|---------------|----------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1. Group member leaves                  | Resource      | Unlikely      | High           | Immediate rescheduling and time analysis. Reallocate responsibilities and potentially reduce the scope of the project.                                                                  |
| 2. Software requirements inflation      | Management    | Likely        | Moderate       | Constantly involve the customer in the project to minimise the scope. Evaluate the necessity of features before adding them to the to-do list.                                          |
| 3. Early errors found late in Project   | Management    | Equally Likely| Moderate       | Audit each member's work and follow the project plan closely throughout the development of the plugin. Write a lot of tests and constantly check plugin with other teams and customer               |
| 4. Loss of project data/work            | Technology    | Unlikely      | Moderate - High| Backup and save work externally if it is locally hosted, keep multiple copies. If externally hosted you could create multiple branches and download locally in case access is lost         |
| 5. Motivation burnout                   | Resource      | Likely        | Moderate       | Account for lack of motivation in out time management. Change up a member's tasks to avoid tedium.                                                                                      |
| 6. Group member gets sick               | Health/Safety | Equally Likely| Low - Moderate | Extra work must be distributed amongst other healthy team members. Try and assist the sick member and if they can do work from home then try to keep up with team                        |
| 7. Extended loss of internet for member | Technology    | Unlikely      | Low - Moderate | Situation dependent. Work from uni if possible or source alternate internet source.                                                                                                     |
| 8. Lack of knowledge/skill/ability      | Resource      | Unlikely      | low - Moderate | Try and follow all the lectures, course updates or forum posts, Mattermost messages and ask more experienced team members for help. Gather resources collectively and share new knowledge amongst each other.                              |
| 9. Programming strain injury            | Health/Safety | Unlikely      | Low            | Enforce regular breaks from long periods of programming. Ensure members are aware of good ergonomic practices. Ensure frequently run tests and other dev actions have clicks minimised. |      
| 10. Scheduling flaws                    | Management    | Likely        | Low            | Involve the whole group in planning and scheduling. Reevaluate our timeframes as the project progresses and check in on progress with members.                                          |

### 5.4 Health and Safety

***Computer-related health and safety***

Computer health best practices should be followed at all times to avoid accidents. These include maintaining good ergonomics, taking regular breaks and checking for hazards.

The project does not require work or testing at an external workplace or site or testing with any human or animal subjects.

As this project will be developed at Victoria University campus, we need to follow the ECS Health and Safety policies.

#### 5.4.1 Safety Plans

Safety Plans may be required for some projects, depending on project requirements.

COVID contact tracing throughout our in-person sessions should be done, and if any member of the team is sick they should not come into contact with us. In this case, the member could sit in using discord and work could be done from home.

As of now (08/06/2020), the COVID pandemic has lowered to level one and therefore the safety plan should be dismissed unless the serious cause for concern is needed or the level increases again in New Zealand. 

**The project requirements do not involve the risk of death, serious harm, or injury.**

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


| Name | Contributions |
| ------ | ------ |
| Finn Coleman | 4.1, 4.2 |
| Ethan Helliwell | 5.1, 5.2, 5.3, 5.4 |
| David Taing | 4.3, 4.4, 4.5 |
| Dominic Tjiptono | 3.1, 3.2 |
| Josh Trueman | 3.1, 3.2 |
| Ryan Yee | 4.3, 4.4, 4.5 |

Other sections that aren't stated above were transferred from the Project Requirements Document.
(1.1, 1.2, 1.3, 2, 6.1, 6.2)

---


