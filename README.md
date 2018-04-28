# WebLookAndFeel
A Java Look & Feel for Swing application, with similar style to web pages.

# How to use with maven

You can add maven dependency using [jitpack.io](https://jitpack.io/) repository in pom.xml file
```xml
<repository>
  <id>jitpack.io</id>
  <url>https://jitpack.io</url>
</repository>
```
...adding the WebLookAndFeel dependency
```xml
<dependency>
  <groupId>com.github.margelperetto</groupId>
  <artifactId>WebLookAndFeel</artifactId>
  <version>master-SNAPSHOT</version>
</dependency>
```
..and finally, settting the LAF in your main application method
```java
public static void main(String[] args) throws Exception {
  UIManager.setLookAndFeel(new WebLookAndFeel());
  new AppFrame().setVisible(true);
}
```

# Example
To run an example application, visit https://github.com/margelperetto/TestWebLookAndFeel

![alt text](https://github.com/margelperetto/weblaf/blob/master/screenshots/complex_form.gif "ScreenShot ComplexForm")
