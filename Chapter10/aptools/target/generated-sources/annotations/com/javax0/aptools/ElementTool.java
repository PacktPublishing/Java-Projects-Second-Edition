//DO NOT EDIT! THIS IS A FLUFLU GENERATED CLASS
// DATE: Sun Jun 08 19:54:07 CEST 2014

package com.javax0.aptools;

public class ElementTool {
	private final Aptools core;

	public ElementTool(Aptools core) {
		this.core = core;
	}
	/**
	  Get the list of the methods from the element.
 
 @return list of the method elements in form of ExecutableElements

	 */
	public java.util.List<javax.lang.model.element.ExecutableElement> getMethods(){
		return core.getMethods();	
	}
	/**
	  Get the name of the class that this element represents.

	 */
	public java.lang.String getClassName(){
		return core.getClassName();	
	}
	/**
	  True if this element is abstract.

	 */
	public boolean isAbstract(){
		return core.elementIsAbstract();	
	}
	/**
	  Get the name of the package that this element (class) is in.

	 */
	public java.lang.String getPackageName(){
		return core.getPackageName();	
	}
	/**
	  Get the named annotation from this element.
 
 @param annotationFullyQualifiedName
          the name of the annotation containing the package and the class
          name of the annotation interface.

	 */
	public javax.lang.model.element.AnnotationMirror getTheAnnotation(String annotationFullyQualifiedName){
		return core.getTheAnnotation(annotationFullyQualifiedName);	
	}
	/**
	  True if the element has the annotation of the given name.
 
 @param annotationFullyQualifiedName
          the name of the annotation containing the package and the class
          name of the annotation interface.

	 */
	public boolean hasAnnotation(String annotationFullyQualifiedName){
		return core.hasAnnotation(annotationFullyQualifiedName);	
	}

}