[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.vavr/vavr-gson/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.vavr/vavr-gson)
[![Build Status](https://travis-ci.org/vavr-io/vavr-gson.svg?branch=master)](https://travis-ci.org/vavr-io/vavr-gson)
[![Coverage Status](https://codecov.io/github/vavr-io/vavr-gson/coverage.svg?branch=master)](https://codecov.io/github/vavr-io/vavr-gson?branch=master)
[![Gitter Chat](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/vavr-io/vavr)

# vavr-gson
A set of GSON serialiser/deserialisers for [Vavr](http://vavr.io/) library

## Usage

### Maven

```xml
<dependency>
  <groupId>io.vavr</groupId>
  <artifactId>vavr-gson</artifactId>
  <version>0.10.2</version>
</dependency>
```

### Gradle

```groovy
compile("io.vavr:vavr-gson:0.10.2")
```

### Registering converters

```java
  GsonBuilder builder = new GsonBuilder();
  VavrGson.registerAll(builder);
  gson = builder.create();
```

### Serialization/deserialization

```java
  String json = gson.toJson(List.of(List.of(1)));
  // = [[1]]
  Object restored1 = gson.fromJson(json, List.class);
  // = List([1])
  Type type = new TypeToken<List<List<Integer>>>(){}.getType();
  Object restored2 = gson.fromJson(json, type);
  // = List(List(1))
```

## Using Developer Versions

Developer versions can be found [here](https://oss.sonatype.org/content/repositories/snapshots/io/vavr/vavr-gson).

### Maven

```xml
<dependency>
  <groupId>io.vavr</groupId>
  <artifactId>vavr-gson</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

Ensure that your `~/.m2/settings.xml` contains the following:

```xml
<profiles>
    <profile>
        <id>allow-snapshots</id>
        <activation>
            <activeByDefault>true</activeByDefault>
        </activation>
        <repositories>
            <repository>
                <id>snapshots-repo</id>
                <url>https://oss.sonatype.org/content/repositories/snapshots</url>
                <releases>
                    <enabled>false</enabled>
                </releases>
                <snapshots>
                    <enabled>true</enabled>
                </snapshots>
            </repository>
        </repositories>
    </profile>
</profiles>
```

### Gradle

```groovy
compile("io.vavr:vavr-gson:1.0.0-SNAPSHOT")
```

Ensure that your `build.gradle` contains the following:

```groovy
repositories {
    mavenCentral()
    maven {
        url "https://oss.sonatype.org/content/repositories/snapshots"
    }
}
