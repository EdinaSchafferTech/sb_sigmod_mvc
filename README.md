sb_sigmod_mvc
A Spring Boot MVC web application for processing and displaying author data from SIGMOD Records XML files.
Overview
This application processes author information stored in a SIGMOD Records XML file and allows the user to work with the processed data through a web interface.
The application can read data from an XML file or from a MySQL database. The user can select the desired data source from the main page and view the processed author data in the web interface.
Technologies
Java
Spring Boot
Spring MVC
Thymeleaf
HTML
XML
JDOM
MySQL
Maven
Features
Read and process author data from a SIGMOD Records XML file
Read author data from a MySQL database
Select the data source from the main page
Enter the full path of an XML file provided by the user
Check whether the specified XML file exists
Process the XML structure using JDOM
Identify articles containing an author with position="01"
Count all authors belonging to those articles
Aggregate author occurrences
Display authors in alphabetical order
Search for authors by name
Display the results on an HTML web page
Export processed author data to the MySQL database
Export processed author data to an XML file
Handle invalid data sources and invalid XML file paths with error handling
XML Processing
The application processes the SIGMOD Records XML structure using JDOM.
The relevant structure is:
SigmodRecord → issue → articles → article → authors → author
For each article, the application checks whether an author with position="01" exists. If such an author is found, all authors belonging to that article are included in the counting process.
The resulting author data is aggregated and displayed in alphabetical order.
Data Sources
The application supports two data sources:
XML file
MySQL database
When XML is selected, the user provides the full path to the XML file. The application checks the specified path before attempting to read the file.
When the database is selected, the application reads the processed author data from MySQL.
Search
The application provides a search function for filtering authors by name.
The search can be performed after selecting the data source, and the matching author records are displayed on the web page.
Export
The application provides an export option that allows processed author data to be stored in:
MySQL database
XML file
The XML export is handled using JDOM.
Web Interface
The user interface is implemented with HTML and Thymeleaf.
The main page allows the user to:
Select the data source
Enter an XML file path
Load data
Search for authors
View the processed results
Select an export destination
The author results are displayed in a table containing the author name and occurrence count.
Error Handling
The application includes error handling for invalid data source selections and invalid XML file paths.
If the database does not contain data, the user is redirected back to the main page with an error message.
If the specified XML path does not contain a valid XML file, the user is also redirected back to the main page with an error message.
Project Structure
The application follows a Spring Boot MVC structure with separate components for:
Controller
Service
Repository
Model
DTO
XML processing
HTML/Thymeleaf views
Project
This project was developed as part of a Java full-stack programming course and demonstrates the use of Spring Boot MVC, XML processing, database access, Thymeleaf, and JDOM in a web application.