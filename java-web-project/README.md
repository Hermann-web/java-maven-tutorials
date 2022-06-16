# Maven - How to create a Java web application project
Maven 3, Spring 5 MVC, JUnit 5, Logback and Jetty web server. A simple web project to display a current date.

Project Link - https://www.favtuts.com/maven-how-to-create-a-java-web-application-project/


## 1. How to run this project?

### 1.1 Test it with Jetty web server.
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd java-web-project 
$ mvn jetty:run
```
Access http://localhost:8080


### 1.2 Create a WAR file for deployment :
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd java-web-project 
$ mvn package or mvn war:war
```
A WAR is generated at 'target/finalName'
