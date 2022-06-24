# Create a Simple Maven Web Application using Command Line

Creating a Maven web application from the command line using Maven

Project Link - https://www.favtuts.com/create-a-simple-maven-web-application-using-command-line/

# Maven Command Line to create project

```
mvn archetype:generate -DgroupId=com.favtuts.simpleweb -DartifactId=maven-cli-simple-webapp -Dpackage=com.favtuts.simpleweb -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-webapp -DinteractiveMode=false
```

# How to run this project?
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd maven-cli-simple-webapp
$ mvn jetty:run

Access:  http://localhost:8080/maven-cli-simple-webapp/simple
```