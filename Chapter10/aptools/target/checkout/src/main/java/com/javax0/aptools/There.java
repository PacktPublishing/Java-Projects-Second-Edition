package com.javax0.aptools;

/**
 * Helper class to check object and string existence.
 * 
 */
public class There {

  /**
   * Checks that the object array exists.
   * 
   * @param o
   *          the object array
   * @return {@code true} if the array is not null and has at least one element.
   */
  public static boolean is(Object[] o) {
    return o != null && o.length > 0;
  }

  /**
   * This method is same as {@link #is(Object[])}. This is complimentary method
   * to make a call more readable when the argument is a variable or an
   * expression that can be read plural e.g.: {@code There.is(strings)} is less
   * readable than {@code There.are(strings)}.
   */
  public static boolean are(Object[] o) {
    return is(o);
  }

  /**
   * Checks that the string exists
   * 
   * @param s
   *          is the string to check
   * @return {@code true} if the string is not null and is not zero length
   */
  public static boolean is(String s) {
    return s != null && s.length() > 0;
  }

  /**
   * Checks that the object exists.
   * 
   * @param s
   *          the object
   * @return {@code true} if the object is not null
   */
  public static boolean is(Object s) {
    return s != null;
  }
}
