# Official plugin is available

It seems like the Mojohaus plugin itself is no longer maintained.
See https://github.com/mojohaus/jaxws-maven-plugin/issues/54#issuecomment-504335749 for details.

Please try the official Maven plugin from https://github.com/eclipse-ee4j/metro-jax-ws/tree/master/jaxws-ri/jaxws-maven-plugin via
```xml
  <dependency>
    <groupId>com.sun.xml.ws</groupId>
    <artifactId>jaxws-maven-plugin</artifactId>
    <version>2.3.2</version>
  </dependency> 
```
before you use this plugin.

# MojoHaus JAX-WS Maven Plugin

This is a fork of the MojoHaus [jaxws-maven-plugin](http://www.mojohaus.org/jaxws-maven-plugin/).
It is based on the 2.6-SNAPSHOT branch from 2018-11-21 including the [PR from @theit](https://github.com/mojohaus/jaxws-maven-plugin/pull/71) for Java 9 support.

**This is a work around release for Java 9 to 12 compatibility**

Please note the changed Maven `<groupId>` which is now `com.helger.maven`.

This plugin is meant to work as a direct replacement to `org.codehaus.mojo:jaxws-maven-plugin:2.5`

## News and Noteworthy

* v2.6.1 - 2019-05-07
    * Integrated https://github.com/mojohaus/jaxws-maven-plugin/issues/19
    * Added Java 12 support
* v2.6 - 2018-11-21
    * Initial release 

## Maven usage

```xml
<plugin>
  <groupId>com.helger.maven</groupId>
  <artifactId>jaxws-maven-plugin</artifactId>
  <version>2.6.1</version>
  <configuration>
    ...
  </configuration>
</plugin>
```
