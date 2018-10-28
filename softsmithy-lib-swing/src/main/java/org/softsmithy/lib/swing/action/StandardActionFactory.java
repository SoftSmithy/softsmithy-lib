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
import org.softsmithy.lib.util.*;

/**
 * <ul>
 *      <li><a href="https://www.oracle.com/technetwork/java/index-138612.html">Java look and feel Graphics Repository</a></li>
 *      <li><a href="https://www.oracle.com/technetwork/java/jlf-135985.html">Java Look and Feel Design Guidelines</a>
 *          <ul>
 *              <li><a href="https://www.oracle.com/technetwork/java/appendix-141053.html">Appendix A: Keyboard Shortcuts, Mnemonics, and Other Keyboard Operations</a></li>
 *              <li><a href="https://www.oracle.com/technetwork/java/appendix-136844.html">Appendix B: Graphics Repository</a></li>
 *              <li><a href="https://www.oracle.com/technetwork/java/appendix-136063.html">Appendix C: Localization Word Lists</a></li>
 *          </ul>
 *      </li>
 * </ul>
 *
 * @author puce
 */
public abstract class StandardActionFactory extends TypesafeEnum {

    private static final String RB_BASE_NAME = "org.softsmithy.lib.swing.action.StandardActions";
    //private static final Map standardActions = new HashMap();

    /**
     * Creates a new instance of StandardActionFactory
     */
    protected StandardActionFactory(String name) {
        super(name);
    }

    /**
     * Creates an XAction for this standard action.
     *
     * @param target this object is expected to implement the method <code>public void &lt;action name/ toString()&gt;(ActionEvent e)</code>
     * @param locale the locale
     * @return a configured XAction
     */
    public XAction createXAction(Object target, Locale locale) throws NoSuchMethodException {
        return XActions.createXAction(toString(), target, ResourceBundle.getBundle(RB_BASE_NAME, locale));
    }

    /**
     * Configures an XAction for this standard action.
     *
     * @param action the XAction to be configured
     * @param locale the locale
     */
    public void configureXAction(XAction action, Locale locale) {
        //    if (! standardActions.containsKey(locale)){
        //      standardActions.put(locale, new HashMap());
        //    }
        //    if (! ((Map) standardActions.get(locale)).containsKey(toString())){
        XActions.configureXAction(action, toString(), ResourceBundle.getBundle(RB_BASE_NAME, locale));
        //      ((Map) standardActions.get(locale)).put(name, standardAction);
        //    } else {
        //      standardAction = (XAction) ((Map) standardActions.get(locale)).get(name);
        //    }
        //    return standardAction;
        //  }
    }

}
