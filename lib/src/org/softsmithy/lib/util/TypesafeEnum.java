package org.softsmithy.lib.util;

import java.util.*;
/**
 * Base class for typsafe enums.<br>
 * Helps documenting the purpose of a subclass.<br>
 * Subclasses should have only private (if fixed), protected (if extensible) or
 * package-private (if extensible only inside the package) constructors
 * to implement the typesafe enum pattern.<br>
 * More to read about the typesafe enum pattern: Item 21 (chapter 5, p.104) of
 * the Java Series Book <a href="http://developer.java.sun.com/developer/Books/effectivejava/index.html">
 * Effective Java Programming Language Guide</a> by Joshua Bloch.<br>
 * Serialization note: Subclasses implementing Serializable should deserialize to
 * the rigth static field as the deserialized version of an object of this class would
 * neither have set the right name attribute (only the parameterless constructor gets called)
 * nor would it fulfill the equals contract.<br>
 * eg.:<br>
 *  <pre>
 *  import java.io.Serializable;
 *  import java.io.ObjectStreamException;
 *
 *  import puce.util.TypesafeEnum;
 *
 *  public class Foo extends TypesafeEnum implements Serializable{
 *
 *    private Foo(String name){
 *      super(name);
 *    }
 *
 *    public static final Foo BAR = new Foo("bar"){
 *      private Object readResolve() throws ObjectStreamException{
 *        return Foo.BAR;
 *      }
 *    }
 *  }
 *  </pre>
 *  Note:This serialization tip has not been tested yet!
 *  <br><br>
 * Copyright:    Copyright (c) 2002
 *
 * @author Florian Brunner
 */

public class TypesafeEnum {

  private final String fName;
  private boolean initialized = false;

  protected TypesafeEnum(String name){
    fName = name;
  }

  protected TypesafeEnum(){
    this("");
  }


  public String toString(){
    return fName;
  }

  // Overrride-prevention methods
  /**
   * All equal objects of the enumerated type are also identical
   * (a.equals(b) if and only if a==b).
   */
  public final boolean equals(Object obj){
    return super.equals(obj);
  }

  public final int hashCode(){
    return super.hashCode();
  }

}