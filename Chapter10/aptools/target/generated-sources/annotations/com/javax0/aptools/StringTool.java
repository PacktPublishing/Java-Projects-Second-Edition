//DO NOT EDIT! THIS IS A FLUFLU GENERATED CLASS
// DATE: Sun Jun 08 19:54:07 CEST 2014

package com.javax0.aptools;

public class StringTool {
	private final Aptools core;

	public StringTool(Aptools core) {
		this.core = core;
	}
	/**
	  Removes the quotes from the string, thus {@code "apple"} becomes
 {@code apple}, or writing the same in Java notation {@code ""apple""}
 becomes {@code "apple"}.
 <p>
 Note that this method simply chops off the first and the last characters.
 Thus {@code apple} will become {@code ppl}. If there are less than two
 characters in the string then RuntimeException will happen.
 
 @return the string without the quotes.

	 */
	public java.lang.String unquoted(){
		return core.unquoted();	
	}
	/**
	  Replace the placeholders with the actual strings.
 <p>
 This method is to be used to replace placeholders in templates with the
 actual values. Placeholders are strings in the template that start and end
 with the {@code #} character and there is a name between the two {@code #}
 characters. For example {@code #packageName#} is a typical placeholder that
 is presumably replaced by the actual name of a package.
 <p>
 The odd arguments of the method are interpreted as placeholder names, the
 even arguments, each following a placeholder name are the actual values.
 The placeholder names <b>should not</b> contain the {@code #} characters.
 They will be taken care of this method. Only the names of the placeholders
 are to be specified.
 <p>
 If there are odd number of argument strings then RuntimeException will be
 thrown.
 
 @param arg
 @return

	 */
	public java.lang.String replace(String ...  arg){
		return core.replace(arg);	
	}
	/**
	  Calculate the prefix from the name of the package. This is the string that
 has to be prepended in front of the name of the class to get the fully
 qualified class name that can be used in source files.
 
 @return the package name usable as prefix

	 */
	public java.lang.String makePrefix(){
		return core.makePrefix();	
	}

}