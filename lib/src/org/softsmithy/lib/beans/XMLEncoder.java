/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * XMLEncoder.java
 *
 * Created on 8. Oktober 2002, 15:06
 */

package org.softsmithy.lib.beans;

import java.beans.*;
import java.io.*;
import java.lang.reflect.*;
import org.softsmithy.lib.swing.*;

/**
 *
 * @author  puce
 */
public class XMLEncoder extends java.beans.XMLEncoder {
  
  /** Creates a new instance of XMLEncoder */
  public XMLEncoder(OutputStream out) {
    super(out);
    setPersistenceDelegate(org.softsmithy.lib.util.TypesafeEnum.class, new TypesafeEnumPersitanceDelegate()); // does not work for subclasses??
  }
  
  /** Returns the persistence delegate for the given type.
   * The persistence delegate is calculated
   * by applying the following of rules in order:
   * <ul>
   * <li>
   * If the type is an array, an internal persistence
   * delegate is returned which will instantiate an
   * array of the appropriate type and length, initializing
   * each of its elements as if they are properties.
   * <li>
   * If the type is a proxy, an internal persistence
   * delegate is returned which will instantiate a
   * new proxy instance using the static
   * "newProxyInstance" method defined in the
   * Proxy class.
   * <li>
   * If the BeanInfo for this type has a <code>BeanDescriptor</code>
   * which defined a "persistenceDelegate" property, this
   * value is returned.
   * <li>
   * In all other cases the default persistence delegate
   * is returned. The default persistence delegate assumes
   * the type is a <em>JavaBean</em>, implying that it has a nullary constructor
   * and that its state may be characterized by the matching pairs
   * of "setter" and "getter" methods returned by the Introspector.
   * </ul>
   *
   * @param  type The type of the object.
   * @return The persistence delegate for this type of object.
   *
   * @see #setPersistenceDelegate
   * @see java.beans.Introspector#getBeanInfo
   * @see java.beans.BeanInfo#getBeanDescriptor
   *
   */
  public PersistenceDelegate getPersistenceDelegate(Class type) {
    PersistenceDelegate retValue;
    if (type != null && org.softsmithy.lib.util.TypesafeEnum.class.isAssignableFrom(type)){
      retValue = new TypesafeEnumPersitanceDelegate();
    } else {
    retValue = super.getPersistenceDelegate(type);
    }
//    if (retValue instanceof java.beans.DefaultPersistenceDelegate && type.getSuperclass() != null){
//      retValue = super.getPersistenceDelegate(type.getSuperclass());
//    }
    return retValue;
  }
  
  /** Write an XML representation of the specified object to the output.
   *
   * @param o The object to be written to the stream.
   *
   * @see XMLDecoder#readObject
   *
   */
  public void writeObject(Object o) {
    super.writeObject(o);
  }
  
  private static class TypesafeEnumPersitanceDelegate extends DefaultPersistenceDelegate{
    
    /** Returns an expression whose value is <code>oldInstance</code>.
     * This method is used to characterize the constructor
     * or factory method that should be used to create the given object.
     * For example, the <code>instantiate</code> method of the persistence
     * delegate for the <code>Field</code> class could be defined as follows:
     * <pre>
     * Field f = (Field)oldInstance;
     * return new Expression(f, f.getDeclaringClass(), "getField", new Object[]{f.getName()});
     * </pre>
     * Note that we declare the value of the returned expression so that
     * the value of the expression (as returned by <code>getValue</code>)
     * will be identical to <code>oldInstance</code>.
     *
     * @param oldInstance The instance that will be created by this expression.
     * @param out The stream to which this expression will be written.
     * @return An expression whose value is <code>oldInstance</code>.
     *
     */
    protected Expression instantiate(Object oldInstance, Encoder out) {
      System.out.println("inside");
      Class cls = oldInstance.getClass();
      Field[] fields = cls.getFields();
      for(int i = 0; i < fields.length; i++) {
        Field field = fields[i];
        if (Modifier.isStatic(field.getModifiers()) && field.getType().isAssignableFrom(cls)){
          try{
//            try{
//              field = field.getType().getField(field.getName());
//            } catch(NoSuchFieldException ex){
//              //ignore it
//            }
            if (oldInstance.equals(field.get(null))){
              return new Expression(field, "get", new Object[]{null});
            }
          } catch(IllegalAccessException ex){
            // ignore it
          }
        }
      }
      return super.instantiate(oldInstance, out);
    }
    
    /** Returns true if an <em>equivalent</em> copy of <code>oldInstance</code> may be
     * created by applying a series of statements to <code>newInstance</code>.
     * In the specification of this method, we mean by equivalent that the modified instance
     * is indistinguishable from <code>oldInstance</code> in the behavior
     * of the relevant methods in its public API. [Note: we use the
     * phrase <em>relevant</em> methods rather than <em>all</em> methods
     * here only because, to be strictly correct, methods like <code>hashCode</code>
     * and <code>toString</code> prevent most classes from producing truly
     * indistinguishable copies of their instances].
     * <p>
     * The default behavior returns <code>true</code>
     * if the classes of the two instances are the same.
     *
     * @param oldInstance The instance to be copied.
     * @param newInstance The instance that is to be modified.
     * @return True if an equivalent copy of <code>newInstance</code> may be
     *         created by applying a series of mutations to <code>oldInstance</code>.
     *
     */
    protected boolean mutatesTo(Object oldInstance, Object newInstance) {
      boolean retValue;
      
      retValue = super.mutatesTo(oldInstance, newInstance);
      return retValue;
    }
    
    /** The <code>writeObject</code> is a single entry point to the persistence
     * and is used by a <code>Encoder</code> in the traditional
     * mode of delegation. Although this method is not final,
     * it should not need to be subclassed under normal circumstances.
     * <p>
     * This implementation first checks to see if the stream
     * has already encountered this object. Next the
     * <code>mutatesTo</code> method is called to see if
     * that candidate returned from the stream can
     * be mutated into an accurate copy of <code>oldInstance</code>.
     * If it can, the <code>initialize</code> method is called to
     * perform the initialization. If not, the candidate is removed
     * from the stream, and the <code>instantiate</code> method
     * is called to create a new candidate for this object.
     *
     * @param oldInstance The instance that will be created by this expression.
     * @param out The stream to which this expression will be written.
     * @return An expression whose value is <code>oldInstance</code>.
     *
     */
    public void writeObject(Object oldInstance, Encoder out) {
      super.writeObject(oldInstance, out);
    }
    
  }
}
