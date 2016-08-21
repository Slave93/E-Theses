# Application specification
E- Theses is a platform for theses management.
There are two types of users, administrators and ordinary users(usually students).
Administrators have full control over all system entities(user profiles, theses, comments, tags, fields of study...),
while ordinary users have control only over their profiles.
Ordinary users can search for theses by many criterias, download theses, comment on theses...
Administrators can upload theses, connect theses with student profiles, remove theses, comments, tags... and manage all other entities.

# Arhitecture
Back-end of the application is implemented using Java programming laguage and Spring framework as a REST Web service.
Front-end of the application is implemented using JavaScript and Angular framework. 

# Documentation
In-depth documentation is placed at the root of the respository: documentation.pdf. Documentation is written is Serbian language, because
it was created as a part of a student project at the Faculty of Organizational Sciences.

# Deployment
Build script is placed in pom.xml file at the root of the project folder. Maven can be used to generate war file wich should be deployed to a Java application
server(ex. Tomcat or JBoss). Before running application mysql database named "e_theses" must be created, and pzr.properties file should be modified
to contains right database information. After deploying application to a Web server path to home page will be http://serverAddress:8080/e_theses.
REST services are exposed relative to url: http://serverAddress:8080/e_theses/webapi. Detailed endpoint documentation can be found in documentation.pdf file
at the root of the repository.

