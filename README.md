# MojoHaus JAX-WS Maven Plugin

This is the [jaxws-maven-plugin](http://www.mojohaus.org/jaxws-maven-plugin/).

**This is a work around release for Java 9 to 11 compatibility**

It is based on the 2.6-SNAPSHOT branch from 2018-11-21 including the [PR from @theit](https://github.com/mojohaus/jaxws-maven-plugin/pull/71) for Java 9 support.

Please note the changed Maven `<groupId>` which is now `com.helger.maven`.

Latest release: **2.6** from *2018-11-21*

This plugin is meant to work as a direct replacement to `org.codehaus.mojo:jaxws-maven-plugin:2.5`

## News and Noteworthy

* v2.6.1 - work in progress
    * Integrated https://github.com/mojohaus/jaxws-maven-plugin/issues/19
    * Added Java 12 support
* v2.6 - 2018-11-21
    * Initial release 

## Maven usage

```xml
<plugin>
  <groupId>com.helger.maven</groupId>
  <artifactId>jaxws-maven-plugin</artifactId>
  <version>2.6</version>
  <configuration>
    ...
  </configuration>
</plugin>
```
