//DO NOT EDIT! THIS IS A FLUFLU GENERATED CLASS
// DATE: Sun Jun 08 19:54:07 CEST 2014

package com.javax0.aptools;

public class AnnotationTool {
	private final Aptools core;

	public AnnotationTool(Aptools core) {
		this.core = core;
	}
	/**
	  Get the value of the annotation as string. This is the default string, when
 an annotation does not have many parameters, only one. If there are more
 than one parameters than this is the one named `value`.
 
 @return the string specified as value.

	 */
	public java.lang.String getStringValue(){
		return core.getStringValue();	
	}
	/**
	 
	 */
	public java.lang.String getStringValue(String name){
		return core.getStringValue(name);	
	}

}