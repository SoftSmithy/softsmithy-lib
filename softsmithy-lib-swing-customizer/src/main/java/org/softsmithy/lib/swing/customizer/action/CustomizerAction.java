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
 * CustomizerAction.java
 *
 * Created on 2. Juni 2004, 02:04
 */
package org.softsmithy.lib.swing.customizer.action;

import java.util.Set;
import org.softsmithy.lib.swing.action.XAction;

/**
 *
 * @author puce
 */
public interface CustomizerAction extends XAction {

    public Set<String> getNeededCustomizableProperties();

    public void setNeededCustomizableProperties(Set<String> neededCustomizableProperties);
}
