//DO NOT EDIT! THIS IS A FLUFLU GENERATED CLASS
// DATE: Sun Jun 08 20:54:47 CEST 2014

package com.javax0.aptools;

public class MethodTool {
	private final Aptools core;

	public MethodTool(Aptools core) {
		this.core = core;
	}
	/**
	 
	 */
	public java.lang.String getJavadoc(){
		return core.getJavadoc();	
	}
	/**
	  Get the number of the parameters of the method.

	 */
	public int getTheNumberOfParameters(){
		return core.getTheNumberOfParameters();	
	}
	/**
	  Get an array of lists containing the annotations of the arguments.

	 */
	public java.util.List<? extends javax.lang.model.element.AnnotationMirror>[] getParameterAnnotations(){
		return core.getParameterAnnotations();	
	}
	/**
	  Get an argument name that can be used as the {@code i}-th argument.
 
 @param i
          zero based index of the argument
 @return

	 */
	public java.lang.String getArgumentName(int i){
		return core.getArgumentName(i);	
	}
	/**
	  Return a string that can be used in a Java code as the argument list for
 the method.
 
 @return

	 */
	public java.lang.String createParamList(){
		return core.createParamList();	
	}
	/**
	 
	 */
	public java.lang.String getReturnType(){
		return core.getReturnType();	
	}
	/**
	 
	 */
	public java.lang.String getName(){
		return core.getName();	
	}
	/**
	  Create and return the string that can be used in the generated Java source
 code as argument list (argument types and argument names separated by
 commas).

	 */
	public java.lang.String createArgList(){
		return core.createArgList();	
	}
	/**
	  True if the method is abstract.

	 */
	public boolean isAbstract(){
		return core.methodIsAbstract();	
	}

}