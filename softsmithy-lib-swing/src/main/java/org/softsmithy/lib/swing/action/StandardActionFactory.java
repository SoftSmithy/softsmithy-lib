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
 * StandardActionFactory.java
 *
 * Created on 2. Juni 2004, 02:34
 */
package org.softsmithy.lib.swing.action;

import java.util.*;

/**
 * <ul>
 * <li>http://java.sun.com/developer/techDocs/hi/repository/index.html</li>
 * <li>http://java.sun.com/products/jlf/ed2/guidelines.html</li>
 * <li>http://java.sun.com/products/jlf/ed2/book/Appendix.A.html</li>
 * <li>http://java.sun.com/products/jlf/ed2/book/Appendix.A2.html</li>
 * <li>http://java.sun.com/products/jlf/ed2/book/Appendix.C.html</li>
 * </ul>
 *
 * @author puce
 */
public interface StandardActionFactory {

    String getName();

    /**
     * Creates an XAction for this standard action.
     *
     * @param target this object is expected to implement the method <code>public void &lt;action name/ toString()&gt;(ActionEvent e)</code>
     * @param locale the locale
     * @return a configured XAction
     * @throws java.lang.NoSuchMethodException
     */
    public default XAction createXAction(Object target, Locale locale) throws NoSuchMethodException {
        return XActions.createXAction(getName(), target, ResourceBundle.getBundle("org.softsmithy.lib.swing.action.StandardActions", locale));
    }

    /**
     * Configures an XAction for this standard action.
     *
     * @param action the XAction to be configured
     * @param locale the locale
     */
    public default void configureXAction(XAction action, Locale locale) {
        //    if (! standardActions.containsKey(locale)){
        //      standardActions.put(locale, new HashMap());
        //    }
        //    if (! ((Map) standardActions.get(locale)).containsKey(toString())){
        XActions.configureXAction(action, getName(), ResourceBundle.getBundle("org.softsmithy.lib.swing.action.StandardActions", locale));
        //      ((Map) standardActions.get(locale)).put(name, standardAction);
        //    } else {
        //      standardAction = (XAction) ((Map) standardActions.get(locale)).get(name);
        //    }
        //    return standardAction;
        //  }
    }

}
