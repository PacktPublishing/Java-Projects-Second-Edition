scriapt
=======

Annotation processor starting external script during compilation

This annotation processor does something very simple thing. You annotate any class of your project using the annotation:

```
import com.javax0.scriapt.CompileScript;
...

@CompileScript("scriptToRun.extension")
class MyClass(){
...
```

and the processor will execute the script for you. The only requirement is that the processor should be on the
classpath of the compiler during compilation and this should also be true for the script interpreter. If you use
JavaScript, then this is already OK if you use Java 6 or newer that I hope for your sake.

To have the annotation processor on the classpath you can specify the dependency

```
		<dependency>
			<groupId>com.javax0.scriapt</groupId>
			<artifactId>scriapt</artifactId>
			<version>1.0.0</version>
			<scope>compile</scope>
		</dependency>
```

in your maven `pom.xml` Note however that this annotation processor is NOT a maven plugin and thus you can use it with
ant, buildr, gradle or just using simple javac. It only happens that I usually use maven and thus I can tell you
what to do if you use maven. If you use some other build tool you should put the annotation processor on the compiler
classpath during compilation time some different way.

What can you do with the executing script during compilation? Have a look at the project

https://github.com/verhas/scriapt-samples
