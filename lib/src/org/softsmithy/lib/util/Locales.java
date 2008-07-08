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
 * Locales.java
 *
 * Created on 12. M�rz 2003, 23:04
 */

package org.softsmithy.lib.util;

import java.text.*;
import java.util.*;

/**
 * An utility class for Locales.
 *
 * @author  puce
 */
public class Locales {
  
  /** Creates a new instance of Locales */
  private Locales() {
  }
  
  
    /**
     * Sorts an array of Locales according to the specified LocaleDisplay and in the 
     * specified Locale.
     * @param locales the locales to sort
     * @param display the LocaleDisplay used to sort
     * @param inLocale the Locale to sort in
     * @return the sorted locales
     */
  public static Locale[] sort(Locale[] locales, LocaleDisplay display, Locale inLocale){
    LocaleCollationKey[] keys = getLocaleCollationKeys(locales, display, inLocale);
    Arrays.sort(keys);
    Locale[] sortedLocales = new Locale[locales.length];
    for (int i=0; i<locales.length; i++){
      sortedLocales[i] = keys[i].getLocale();
    }
    return sortedLocales;
  }
  
  private static LocaleCollationKey[] getLocaleCollationKeys(Locale[] locales, LocaleDisplay display, Locale inLocale){
    Collator collator = Collator.getInstance(inLocale);
    LocaleCollationKey[] keys = new LocaleCollationKey[locales.length];
    for (int i=0; i<locales.length; i++){
      keys[i] = new LocaleCollationKey(locales[i], collator.getCollationKey(display.getString(locales[i], inLocale)));
    }
    return keys;
  }
  
//    public static Locale getLocale(LanguageCode lc) {
//    return new Locale(lc.getA2());
//  }
//    
//  public static Locale getLocale(LanguageCode lc, CountryCode cc) {
//    return new Locale(lc.getA2(), cc.getA2());
//  }
//  
//    public static Locale getLocale(LanguageCode lc, CountryCode cc, String variant) {
//    return new Locale(lc.getA2(), cc.getA2(), variant);
//  }
  
  private static class LocaleCollationKey implements Comparable{
    
    /** Holds value of property locale. */
    private Locale locale;
    
    /** Holds value of property collationKey. */
    private CollationKey collationKey;
    
    public LocaleCollationKey(Locale locale, CollationKey collationKey){
      this.locale = locale;
      this.collationKey = collationKey;
    }
    
    /** Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.<p>
     *
     * In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of <i>expression</i>
     * is negative, zero or positive.
     *
     * The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)<p>
     *
     * The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.<p>
     *
     * Finally, the implementer must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.<p>
     *
     * It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     *
     * @param   o the Object to be compared.
     * @return  a negative integer, zero, or a positive integer as this object
     * 		is less than, equal to, or greater than the specified object.
     *
     * @throws ClassCastException if the specified object's type prevents it
     *         from being compared to this Object.
     *
     */
    public int compareTo(Object o) {
      LocaleCollationKey key = (LocaleCollationKey) o;
      return collationKey.compareTo(key.getCollationKey());
    }
    
    /** Getter for property locale.
     * @return Value of property locale.
     *
     */
    public Locale getLocale() {
      return this.locale;
    }
    
    /** Getter for property collationKey.
     * @return Value of property collationKey.
     *
     */
    public CollationKey getCollationKey() {
      return this.collationKey;
    }
    
    /** Returns a hash code value for the object. This method is
     * supported for the benefit of hashtables such as those provided by
     * <code>java.util.Hashtable</code>.
     * <p>
     * The general contract of <code>hashCode</code> is:
     * <ul>
     * <li>Whenever it is invoked on the same object more than once during
     *     an execution of a Java application, the <tt>hashCode</tt> method
     *     must consistently return the same integer, provided no information
     *     used in <tt>equals</tt> comparisons on the object is modified.
     *     This integer need not remain consistent from one execution of an
     *     application to another execution of the same application.
     * <li>If two objects are equal according to the <tt>equals(Object)</tt>
     *     method, then calling the <code>hashCode</code> method on each of
     *     the two objects must produce the same integer result.
     * <li>It is <em>not</em> required that if two objects are unequal
     *     according to the {@link java.lang.Object#equals(java.lang.Object)}
     *     method, then calling the <tt>hashCode</tt> method on each of the
     *     two objects must produce distinct integer results.  However, the
     *     programmer should be aware that producing distinct integer results
     *     for unequal objects may improve the performance of hashtables.
     * </ul>
     * <p>
     * As much as is reasonably practical, the hashCode method defined by
     * class <tt>Object</tt> does return distinct integers for distinct
     * objects. (This is typically implemented by converting the internal
     * address of the object into an integer, but this implementation
     * technique is not required by the
     * Java<font size="-2"><sup>TM</sup></font> programming language.)
     *
     * @return  a hash code value for this object.
     * @see     java.lang.Object#equals(java.lang.Object)
     * @see     java.util.Hashtable
     *
     */
    @Override
    public int hashCode() {
      int result = 17;
      result = 37*result + collationKey.hashCode();
      return result;
    }
    
    /** Indicates whether some other object is "equal to" this one.
     * <p>
     * The <code>equals</code> method implements an equivalence relation:
     * <ul>
     * <li>It is <i>reflexive</i>: for any reference value <code>x</code>,
     *     <code>x.equals(x)</code> should return <code>true</code>.
     * <li>It is <i>symmetric</i>: for any reference values <code>x</code> and
     *     <code>y</code>, <code>x.equals(y)</code> should return
     *     <code>true</code> if and only if <code>y.equals(x)</code> returns
     *     <code>true</code>.
     * <li>It is <i>transitive</i>: for any reference values <code>x</code>,
     *     <code>y</code>, and <code>z</code>, if <code>x.equals(y)</code>
     *     returns  <code>true</code> and <code>y.equals(z)</code> returns
     *     <code>true</code>, then <code>x.equals(z)</code> should return
     *     <code>true</code>.
     * <li>It is <i>consistent</i>: for any reference values <code>x</code>
     *     and <code>y</code>, multiple invocations of <tt>x.equals(y)</tt>
     *     consistently return <code>true</code> or consistently return
     *     <code>false</code>, provided no information used in
     *     <code>equals</code> comparisons on the object is modified.
     * <li>For any non-null reference value <code>x</code>,
     *     <code>x.equals(null)</code> should return <code>false</code>.
     * </ul>
     * <p>
     * The <tt>equals</tt> method for class <code>Object</code> implements
     * the most discriminating possible equivalence relation on objects;
     * that is, for any reference values <code>x</code> and <code>y</code>,
     * this method returns <code>true</code> if and only if <code>x</code> and
     * <code>y</code> refer to the same object (<code>x==y</code> has the
     * value <code>true</code>).
     * <p>
     * Note that it is generally necessary to override the <tt>hashCode</tt>
     * method whenever this method is overridden, so as to maintain the
     * general contract for the <tt>hashCode</tt> method, which states
     * that equal objects must have equal hash codes.
     *
     * @param   obj   the reference object with which to compare.
     * @return  <code>true</code> if this object is the same as the obj
     *          argument; <code>false</code> otherwise.
     * @see     #hashCode()
     * @see     java.util.Hashtable
     *
     */
    @Override
    public boolean equals(Object obj) {
      if (obj == this){
        return true;
      }
      if (!(obj instanceof LocaleCollationKey)){
        return false;
      }
      LocaleCollationKey lck = (LocaleCollationKey) obj;
      return (collationKey == lck.collationKey ||
      (collationKey != null && collationKey.equals(lck.collationKey)));
    }
    
  }
  
}
