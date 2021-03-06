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
package org.softsmithy.lib.internal;

import java.util.Locale;
import org.softsmithy.lib.util.TypesafeEnum;

@Deprecated
public class TypesafeEnumItem {

    /**
     * Holds value of property fontStyle.
     */
    private final TypesafeEnum typesafeEnum;
    /**
     * Holds value of property locale.
     */
    private final Locale locale;

    public TypesafeEnumItem(TypesafeEnum typesafeEnum, Locale locale) {
        this.typesafeEnum = typesafeEnum;
        this.locale = locale;
        //System.out.println("item created");
    }

    public TypesafeEnum getTypesafeEnum() {
        return this.typesafeEnum;
    }

    /**
     * Returns a string representation of the object. In general, the
     * <code>toString</code> method returns a string that "textually represents" this object. The result should be a
     * concise but informative representation that is easy for a person to read. It is recommended that all subclasses
     * override this method. <p> The
     * <code>toString</code> method for class
     * <code>Object</code> returns a string consisting of the name of the class of which the object is an instance, the
     * at-sign character `
     * <code>@</code>', and the unsigned hexadecimal representation of the hash code of the object. In other words, this
     * method returns a string equal to the value of: <blockquote>      <pre>
     * getClass().getName() + '
     * @' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     *
     */
    @Override
    public String toString() {
        //System.out.println("toString start");
        return getTypesafeEnum().getDisplayString(getLocale());
    }

    /**
     * Getter for property locale.
     *
     * @return Value of property locale.
     *
     */
    public Locale getLocale() {
        return this.locale;
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of hashtables such as those
     * provided by
     * <code>java.util.Hashtable</code>. <p> The general contract of
     * <code>hashCode</code> is: <ul> <li>Whenever it is invoked on the same object more than once during an execution
     * of a Java application, the <tt>hashCode</tt> method must consistently return the same integer, provided no
     * information used in <tt>equals</tt> comparisons on the object is modified. This integer need not remain
     * consistent from one execution of an application to another execution of the same application. <li>If two objects
     * are equal according to the <tt>equals(Object)</tt> method, then calling the
     * <code>hashCode</code> method on each of the two objects must produce the same integer result. <li>It is
     * <em>not</em> required that if two objects are unequal according to the
     * {@link java.lang.Object#equals(java.lang.Object)} method, then calling the <tt>hashCode</tt> method on each of
     * the two objects must produce distinct integer results. However, the programmer should be aware that producing
     * distinct integer results for unequal objects may improve the performance of hashtables. </ul> <p> As much as is
     * reasonably practical, the hashCode method defined by class <tt>Object</tt> does return distinct integers for
     * distinct objects. (This is typically implemented by converting the internal address of the object into an
     * integer, but this implementation technique is not required by the Java<font size="-2"><sup>TM</sup></font>
     * programming language.)
     *
     * @return a hash code value for this object.
     * @see java.lang.Object#equals(java.lang.Object)
     * @see java.util.Hashtable
     *
     */
    @Override
    public int hashCode() {
        int hashCode = 17;
        hashCode = 37 * hashCode + typesafeEnum.hashCode();
        hashCode = 37 * hashCode + locale.hashCode();
        return hashCode;
    }

    /**
     * Indicates whether some other object is "equal to" this one. <p> The
     * <code>equals</code> method implements an equivalence relation: <ul> <li>It is <i>reflexive</i>: for any reference
     * value
     * <code>x</code>,
     * <code>x.equals(x)</code> should return
     * <code>true</code>. <li>It is <i>symmetric</i>: for any reference values
     * <code>x</code> and
     * <code>y</code>,
     * <code>x.equals(y)</code> should return
     * <code>true</code> if and only if
     * <code>y.equals(x)</code> returns
     * <code>true</code>. <li>It is <i>transitive</i>: for any reference values
     * <code>x</code>,
     * <code>y</code>, and
     * <code>z</code>, if
     * <code>x.equals(y)</code> returns
     * <code>true</code> and
     * <code>y.equals(z)</code> returns
     * <code>true</code>, then
     * <code>x.equals(z)</code> should return
     * <code>true</code>. <li>It is <i>consistent</i>: for any reference values
     * <code>x</code> and
     * <code>y</code>, multiple invocations of <tt>x.equals(y)</tt> consistently return
     * <code>true</code> or consistently return
     * <code>false</code>, provided no information used in
     * <code>equals</code> comparisons on the object is modified. <li>For any non-null reference value
     * <code>x</code>,
     * <code>x.equals(null)</code> should return
     * <code>false</code>. </ul> <p> The <tt>equals</tt> method for class
     * <code>Object</code> implements the most discriminating possible equivalence relation on objects; that is, for any
     * reference values
     * <code>x</code> and
     * <code>y</code>, this method returns
     * <code>true</code> if and only if
     * <code>x</code> and
     * <code>y</code> refer to the same object (
     * <code>x==y</code> has the value
     * <code>true</code>). <p> Note that it is generally necessary to override the <tt>hashCode</tt> method whenever
     * this method is overridden, so as to maintain the general contract for the <tt>hashCode</tt> method, which states
     * that equal objects must have equal hash codes.
     *
     * @param obj the reference object with which to compare.
     * @return  <code>true</code> if this object is the same as the obj argument; <code>false</code> otherwise.
     * @see #hashCode()
     * @see java.util.Hashtable
     *
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TypesafeEnumItem)) {
            return false;
        }
        TypesafeEnumItem item = (TypesafeEnumItem) obj;
        return (typesafeEnum == item.typesafeEnum)
                && (locale == item.locale || (locale != null && locale.equals(item.locale)));
    }
}