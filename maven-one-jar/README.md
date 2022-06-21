# Maven – Create a fat Jar file – One-JAR example
use Maven build tool, One-JAR plugin to create a single Jar together with its dependency Jars into a single executable Jar file, so called fat Jar

Project Link - https://www.favtuts.com/maven-create-a-fat-jar-file-one-jar-example/

# Create project in Maven

```
$ mvn archetype:generate -DgroupId=com.favtuts.core.utils -DartifactId=dateUtils -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
```

Make it support Eclipse.
```
$ cd dateUtils
$ mvn eclipse:eclipse
```

# How to run this project?

## 1.1 Build JAR file
```
$ git clone https://github.com/favtuts/java-maven-tutorials.git
$ cd maven-create-jar/dateUtils
$ mvn clean package
```

## 1.2 Run the JAR file
```
$ java -jar target/dateutils.jar
$ java -jar -Dlog4j.configuration=file:/full_path/log4j.properties target/dateutils.jar
```