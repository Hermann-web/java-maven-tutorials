# Create a Simple Maven Project using Command Line

Creating a simple Maven project from the command line using Maven

Project Link - https://www.favtuts.com/create-a-simple-maven-project-using-command-line/

# Maven Command Line to create project

```
mvn archetype:generate -DgroupId=com.favtuts.simpleproject -DartifactId=maven-cli-simple-project -Dpackage=com.favtuts.simpleproject -Dversion=1.0-SNAPSHOT -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

# How to run this project?
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd maven-cli-simple-project
$ mvn install
$ java -cp target/maven-cli-simple-project-1.0-SNAPSHOT.jar com.favtuts.simpleproject.App
```