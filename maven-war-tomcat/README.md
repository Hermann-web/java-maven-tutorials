# Maven - How to create a Java web application project
Maven 3, Spring 5 MVC, JUnit 5, Logback and Jetty web server. A simple web project to display a current date.

Project Link - https://www.favtuts.com/maven-how-to-create-a-java-web-application-project/


# How to deploy Maven based war file to Tomcat

Use Maven-Tomcat plugin to package and deploy a WAR file to Tomcat, both in Tomcat 6 and 7.

Project Link - https://www.favtuts.com/how-to-deploy-maven-based-war-file-to-tomcat/


# 1. How to run this project?

## 1.1 Test it with Jetty web server.
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd maven-war-tomcat
$ mvn jetty:run
```
Access http://localhost:8080


## 1.2 Create a WAR file for deployment :
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd maven-war-tomcat
$ mvn clean package or mvn war:war
```
A WAR is generated at 'target/finalName'


# 2. How to deploy webapp to Tomcat

## 2.1 Tomcat 7

```
mvn tomcat7:deploy 
mvn tomcat7:undeploy 
mvn tomcat7:redeploy 
```

## 2.2 Tomcat 6

```
mvn tomcat6:deploy 
mvn tomcat6:undeploy 
mvn tomcat6:redeploy 
```