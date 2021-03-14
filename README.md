# SoftSmithy Utility Library
A Java utility library.

Documentation: https://www.softsmithy.org/softsmithy-lib

| Major Version | Comments |
| ------------- | ------- |
| v3 | <ul><li>Java SE 11</li></ul> |
| v2 | <ul><li>Java SE 8</li><li>JUnit 5</li></ul> |
| v1 | <ul><li>Java SE 8</li><li>JUnit 4</li></ul> |

## Maven Dependency Management

```xml
    <dependencyManagement>
        <dependencies>
            [...] 
            <dependency>
                <groupId>org.softsmithy.lib</groupId>
                <artifactId>softsmithy-lib</artifactId>
                <version>2.0</version> <!-- replace with the current version -->
                <scope>import</scope>
                <type>pom</type>
            </dependency>
            [...]
        </dependencies>
    </dependencyManagement>
```

## Build the project from sources
```bash
mvn clean install
```
Please note that the develop branch (SNAPSHOT version) of the project might depend on SNAPSHOT versions of other projects.

If you don't want to build the dependent projects as well, please make sure to define a proxy in your [Maven Repository Manager](https://maven.apache.org/repository-management.html) to the following Maven Repository: https://oss.sonatype.org/content/repositories/snapshots/ and include it in your [single group](https://help.sonatype.com/repomanager3/formats/maven-repositories#MavenRepositories-ConfiguringApacheMaven).
