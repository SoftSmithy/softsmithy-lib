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
 * CustomizerListener.java
 *
 * Created on 30. August 2002, 16:40
 */

package org.softsmithy.lib.awt.event;

import java.util.*;

/**
 * The listener interface for receiving component layout events.
 * @author puce
 */
public interface ComponentLayoutListener extends EventListener {
    
    
    /**
     * Invoked when a component has been layed out.
     * @param e a component layout event
     */
    void componentLayouted(ComponentLayoutEvent e);
    
    
}
