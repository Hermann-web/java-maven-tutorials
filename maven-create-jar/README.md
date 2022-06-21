# How to create a jar file with Maven
Use Maven build tool, to create a single executable Jar, and how to deal with the project’s dependencies.

Project Link - https://www.favtuts.com/how-to-create-a-jar-file-with-maven/

# Maven command to create project

```
$ mvn archetype:generate -DgroupId=com.favtuts.core.utils -DartifactId=dateUtils -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

# How to run this project?

## 1.1 Build JAR file and dependency-jars
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd maven-create-jar/dateUtils
$ mvn clean package
```

## 1.2 Run the JAR file with external log4j configuration :
```
$ java -jar -Dlog4j.configuration=file:/full_path/log4j.properties target/dateutils.jar
```