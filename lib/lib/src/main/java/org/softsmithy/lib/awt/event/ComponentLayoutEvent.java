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
 * CustomizerEvent.java
 *
 * Created on 30. August 2002, 15:42
 */

package org.softsmithy.lib.awt.event;

import java.awt.*;
import java.util.*;

/**
 * An event which indicates that a component has been layed out.
 * @author puce
 */
public class ComponentLayoutEvent extends EventObject {
    
    /**
     * The new bounds of the component.
     */
    private final Rectangle bounds;
    
    /**
     * The component which has been layed out.
     */
    private Component component;
    
    /**
     * Creates a new instance of this class.
     * @param source the source of this event
     * @param component the component which has been layed out
     * @param bounds the new bounds of the component.
     */
    public ComponentLayoutEvent(Object source, Component component, Rectangle bounds) {
        super(source);
        this.component = component;
        this.bounds = bounds;
    }
    
    /**
     * Gets the new bounds of the component.
     * @return the new bounds of the component
     */
    public Rectangle getBounds() {
        return this.bounds;
    }
    
    /**
     * Gets the component which has been layed out.
     * @return the component which has been layed out
     */
    public Component getComponent() {
        return this.component;
    }
    
}
