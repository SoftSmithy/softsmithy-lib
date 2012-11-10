/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
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


import java.awt.Color;
import java.awt.Font;
import java.beans.DefaultPersistenceDelegate;
import java.beans.Encoder;
import java.beans.Expression;
import java.beans.PersistenceDelegate;
import java.beans.XMLEncoder;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Locale;
import org.softsmithy.lib.util.Singleton;
import org.softsmithy.lib.util.TypesafeEnum;
//import org.softsmithy.lib.swing.style.*;

/**
 *
 * @author  puce
 */
public class XMLEncoderX extends XMLEncoder {
  
  /** Creates a new instance of XMLEncoder */
  public XMLEncoderX(OutputStream out) {
    super(out);
    setPersistenceDelegate(TypesafeEnum.class, new TypesafeEnumPersitanceDelegate()); // does not work for subclasses??
    setPersistenceDelegate(Singleton.class, new SingletonPersitanceDelegate());
    setPersistenceDelegate(Locale.class, new XDefaultPersistenceDelegate(new String[]{"language", "country", "variant"})); // this is not defined in j2sdk?!
    // TODO: SoftSMithy Swing dependency...
//    setPersistenceDelegate(CustomStyleProvider.class, new XDefaultPersistenceDelegate(new String[]{"style"}));
    setPersistenceDelegate(Color.class, new XDefaultPersistenceDelegate(new String[]{"red", "green", "blue", "alpha"}));
    setPersistenceDelegate(Font.class, new XDefaultPersistenceDelegate(new String[]{"name", "style", "size"}));
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
  @Override
  public PersistenceDelegate getPersistenceDelegate(Class<?> type) {
    PersistenceDelegate retValue;
    if (type != null && TypesafeEnum.class.isAssignableFrom(type)){
      retValue = super.getPersistenceDelegate(TypesafeEnum.class);
    } else  if (type != null && Singleton.class.isAssignableFrom(type)){
      retValue = super.getPersistenceDelegate(Singleton.class);
    } else{
      retValue = super.getPersistenceDelegate(type);
    }
    //    if (retValue instanceof java.beans.DefaultPersistenceDelegate && type.getSuperclass() != null){
    //      retValue = super.getPersistenceDelegate(type.getSuperclass());
    //    }
    return retValue;
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
    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
      Class<?> cls = oldInstance.getClass();
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
  }
  
  private static class SingletonPersitanceDelegate extends DefaultPersistenceDelegate{
    
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
    @Override
    protected Expression instantiate(Object oldInstance, Encoder out) {
      try{
        Field instance = oldInstance.getClass().getField("INSTANCE");
        if (isReallySingleton(instance, oldInstance)){
          return new Expression(instance, "get", new Object[]{null});
        }
      } catch(Exception ex){
      }
      return super.instantiate(oldInstance, out);
    }
    
    private boolean isReallySingleton(Field instance, Object oldInstance) throws IllegalAccessException{
      return (Modifier.isStatic(instance.getModifiers())
      && instance.getType().isAssignableFrom(oldInstance.getClass())
      && oldInstance.equals(instance.get(null)));
    }
  }
  
}
