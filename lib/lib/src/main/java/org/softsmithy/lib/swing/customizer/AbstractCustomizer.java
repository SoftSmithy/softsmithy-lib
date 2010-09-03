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
 * AbstractCustomizer.java
 *
 * Created on 6. Februar 2003, 17:45
 */

package org.softsmithy.lib.swing.customizer;

import java.util.*;
import org.softsmithy.lib.swing.*;

/**
 * The base class of components with visually customizable properties support.
 * @author puce
 */
public abstract class AbstractCustomizer extends JStyledPanel {
    
    /**
     * The supported visually customizable bound properties. (Strings)
     */
    private Set customizableProperties = Collections.EMPTY_SET;
    
    /**
     * Creates a new instance of this class.
     */
    public AbstractCustomizer() {
    }
    
    /**
     * Gets the supported visually customizable bound properties. (Strings)
     * @return the supported visually customizable bound properties (Strings)
     */
    public Set getCustomizableProperties() {
        return this.customizableProperties;
    }
    
    /**
     * Sets the supported visually customizable bound properties. (Strings) </br>
     * This property is used by different other classes to find which properties
     * to listen for. A set of bound property names is expected (Set of Strings).
     * @param customizableProperties the supported visually customizable bound properties (Strings)
     */
    public void setCustomizableProperties(Set customizableProperties) {
        this.customizableProperties = customizableProperties;
    }
    
    // better place for this method?
    // really on AbstractCustomizer or just on subclasses?
    /**
     * Gets the common supported visually customizable bound properties of the
     * specified customizers. (Strings)
     * @param customizers the customizers (AbstractCustomizer)
     * @return the common supported visually customizable bound properties of the
     * specified customizers (Strings)
     */
    public static Set getCommonCustomizableProperties(Collection customizers) {
        Set properties = Collections.EMPTY_SET;
        Iterator i = customizers.iterator();
        if (i.hasNext()){
            AbstractCustomizer customizer = (AbstractCustomizer) i.next();
            properties = new LinkedHashSet(customizer.getCustomizableProperties());
            for (;i.hasNext();){
                AbstractCustomizer custom = (AbstractCustomizer) i.next();
                properties.retainAll(custom.getCustomizableProperties());
            }
        }
        return properties;
    }
    
    
    
}
