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
     * The supported visually customizable bound properties.
     */
    private Set<String> customizableProperties = Collections.emptySet();
    
    /**
     * Creates a new instance of this class.
     */
    public AbstractCustomizer() {
    }
    
    /**
     * Gets the supported visually customizable bound properties.
     * @return the supported visually customizable bound properties
     */
    public Set<String> getCustomizableProperties() {
        return this.customizableProperties;
    }
    
    /**
     * Sets the supported visually customizable bound properties.<br>
     * This property is used by different other classes to find which properties
     * to listen for. A set of bound property names is expected.
     * @param customizableProperties the supported visually customizable bound properties
     */
    public void setCustomizableProperties(Set<String> customizableProperties) {
        this.customizableProperties = customizableProperties;
    }
    
    // better place for this method?
    // really on AbstractCustomizer or just on subclasses?
    /**
     * Gets the common supported visually customizable bound properties of the
     * specified customizers.
     * @param customizers the customizers
     * @return the common supported visually customizable bound properties of the
     * specified customizers
     */
    public static Set<String> getCommonCustomizableProperties(Collection<? extends AbstractCustomizer> customizers) {
        Set<String> properties = Collections.emptySet();
        Iterator<? extends AbstractCustomizer> i = customizers.iterator();
        if (i.hasNext()){
            AbstractCustomizer customizer = i.next();
            properties = new LinkedHashSet<>(customizer.getCustomizableProperties());
            for (;i.hasNext();){
                AbstractCustomizer custom = i.next();
                properties.retainAll(custom.getCustomizableProperties());
            }
        }
        return properties;
    }
    
    
    
}
