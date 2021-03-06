/*
 *         COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Notice
 *
 * The contents of this file are subject to the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL)
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.opensource.org/licenses/cddl1.txt
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (GitHub user: puce77). All Rights Reserved.
 *
 * Contributor(s): .
 */
package org.softsmithy.lib.util;

import java.util.EventObject;

/**
 * A close event.
 *
 * @author puce
 */
public class CloseEvent extends EventObject {

    private static final long serialVersionUID = 4350767329150470528L;

    /**
     * Create a new instance of this class.
     *
     * @param source the source of this event
     */
    public CloseEvent(Object source) {
        super(source);
    }

}
