package puce.lang.reflect;

import java.util.*;

/**
 * A utility class for Classes.
 *
 * @author    Florian Brunner
 * @created   March 7, 2002
 * @see       java.lang.Class
 */
public class Classes {

  /**
   * No public constructor.
   */
  private Classes() { }


  /**
   * Tests if the specified class implements (directly or indirectly) the
   * specified interface.
   *
   * @param aClass                        the specified class
   * @param anInterface                   the specified interface
   * @return                              true if the specified class implements
   *                                      (directly or indirectly) the specified
   *                                      interface; false otherwise
   * @extension IllegalArgumentException  if the parameter anInterface is not an interface
   */
  public static boolean implementsInterface(Class aClass, Class anInterface) {
    if (!anInterface.isInterface()) {
      throw new IllegalArgumentException(anInterface.getName() + " is not an interface!");
    }
    boolean impl = false;
    Class current = aClass;
    while ((current != null) && (!impl)) {
      impl = Arrays.asList(current.getInterfaces()).contains(anInterface);
      current = current.getSuperclass();
    }
    return impl;
  }

  /**
   * Tests if the first class extends (directly or indirectly) the second class.
   *
   * @param subclass                      the subclass
   * @param superclass                    the superclass
   * @return                              true if subclass extends (directly or
   *                                      indirectly) the superclass; false otherwise
   */
  public static boolean extendsClass(Class subclass, Class superclass) {
    boolean ext = false;
    for (Class current = subclass.getSuperclass(); (current != null) && (!ext); current = current.getSuperclass()) {
      ext = current.equals(superclass);

    }
    return ext;
  }

}
