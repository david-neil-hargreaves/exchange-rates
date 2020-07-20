# exchange-rates-server

This application provides RESTful services for currencies and exchange rates.

## Getting Started

### Quick Start
To build and run the application:

* mvn clean install -DskipITs
* cd exchange-rates-application 
* ../mvnw spring-boot:run                                                                                                               

### Prerequisites

Ensure that your environment has the following software installed.
| Item | Details | Download U.R.L. |
| ------------ | ------------- | ------------ |
| Java JDK | Java Platform Standard Edition Development Kit (JDK) version 1.8 upwards.  This is required for building the application, not for running it. | [www.oracle.com](https://www.oracle.com/java/technologies/javase-jdk8-downloads.html)       |
| Java JRE | Java Platform Standard Edition Runtime Environment (JRE) version 1.8 upwards.  This is required for running the application.  Note that a Java JDK installation includes a JRE. | [www.oracle.com](https://www.oracle.com/java/technologies/javase-jre8-downloads.html)       |
| Maven | Apache Maven version 3.6.3 upwards.  This is required for building and running the application. | [maven.apache.ord](https://maven.apache.org/download.cgi) |

### Installing
To build and install the application navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* mvn clean install -DskipITs

## Running the tests

### Unit tests
The unit tests are executed automatically when the application is built.

They can also be executed independently.  To do this navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* mvn test 

### Integration tests
The integration tests require the starting of the Angular server for the companion exchange-rates-client project. 

After starting the exchange-rates-client Angular server navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:

* mvn clean install

### Coding style tests
Checkstyle is used to check that the code adheres to a given set of coding standards.  These are configured in the pom.xml file (in the top-level directory).  The Checkstyle configuration used is that used by Google (google_checks.xml), with certain deviations as defined in the checkstyle-suppressions.xml file.

Checkstyle is run automatically when the application is built.

The target directory contains these Checkstyle files when the build completes:
|File|Contents|
|---|---|
|checkstyle-checker.xml|The Checkstyle configuration used (i.e. currently google_checks.xml).|
|checkstyle-suppressions.xml|Deviations from the Checkstyle configuration.| 
|checkstyle-result.xml|Checkstyle execution results.|

Checkstyle can also be executed independently.  To do this navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following command:
* mvn verify -DskipITs

### Test coverage checks
Jacoco is used to check that the code is sufficiently covered by unit tests. This is configured by the pom.xml file in the coverage directory.

Jacoco is run automatically when the application is built.

After building the application execute the following commands in order to produce the Jacoco reports:
* cd coverage
* mvn jacoco:report-aggregate

Within the coverage directory this has produced the target/site/jacoco-aggregate sub-directory to hold the Jacoco reports.

This, in turn, has five separate sub-directories for each of the four Java modules:
* exchange-rates-application
* exchange-rates-model
* exchange-rates-services
* exchange-rates-utilities
* exchange-rates-web

Each of these contains an index.html file which can be opened in a Web browser to show a report of unit test coverage for that module. 


## Configuration
Source configuration files are stored in online-shop-application/src/main/resources.

In the built application these can be found in online-shop-application/target/classes.

There are two configuration files:

|File|Contents|
|---|---|
|log4j2.xml|This configures logging for the application using the Log4j2 framework (note that this is substantially different from Log4j version 1). Logging can be configured to a file, the console, e-mail, database.  For details on how to configure Log4j2 for logging see [logging.apache.org]( https://logging.apache.org/log4j/2.x/manual/configuration.html).|
|exchange-rates.properties|This is a Java properties file allowing configuration of certain application details in 'key-value' format.|

## Running the Application
To run the application navigate to the top-level project directory (i.e. the one containing this README.md file) and then issue the following commands:
* cd exchange-rates-application 
* ../mvnw spring-boot:run    

## I.D.E. Settings
If developing the application in an IDE (integrated development environment) then some settings are advisory.

The exact settings will vary by IDE.  These are applicable to Eclipse:

|Item|Setting|
|---|---|
|Preferences --> Java --> Code Style --> Formatter|Import the eclipse-java-google-style.xml file supplied in the project.  This uses Google coding standards so that when the Format button is used code will be formatted in alignment with the Checkstyle configuration for the project.|
|Preferences --> Java --> Code Style --> Organize Imports|Remove any explicit ordering of packages.  This will then sort the imports alphabetically, matching the order used by the Checkstyle configuration.|
|Preferences --> Java --> Compiler --> Compiler compliance level|1.8|
|Preferences --> Java --> Installed JREs|Ensure that the JRE 1.8 is added and checked as the default.|

### Installing Lombok in Eclipse
Lombok is a framework used to reduce boilerplate code with respect to getters, setters, constructors etc.

It should be installed in Eclipse so that the I.D.E. can recognise getters, setters etc. even when they are not explicitly defined in the code.

In order to do this from the command line execute the following command to displays the Maven repository directory for the current user:
* mvn help:evaluate -Dexpression=settings.localRepository -q -DforceStdout

Now navigate (cd) into that directory.

From there navigate into the directory holding the Lombok jar in use by the project i.e. currently org/projectlombok/lombok/1.18.12.

Now execute the following command:

* java -jar lombok-1.18.12.jar

This brings up a dialogue box.  It may detect the Eclipse installation automatically or you may have to browse to find the eclipse.exe (or other executable).  Click the Install/Update button.

To check that Lombok was successfully installed restart Eclipse and select Help and then About Eclipse IDE.

Something like the following will be shown in the text:
* Lombok v1.18.12 "Envious Ferret" is installed. https://projectlombok.org/

### Installing Lombok in Other IDEs
Lombok can be installed in ther IDEs.  However, the installation process varies by IDE.

## Further Development
Productionising of the system e.g. configuration of the host, port etc.

## Built With
[Maven](https://maven.apache.org/) - Dependency Management

## Authors
David Hargreaves# exchange-rates-server

