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
 * ComparatorEqualityVerifier.java
 *
 * Created on 11. Mai 2007, 00:30
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

import java.util.Comparator;

/**
 *
 * @author puce
 */
// Not yet used!
public class ComparatorEqualityVerifier implements EqualityVerifier{

    private final Comparator comparator;
    
    /** Creates a new instance of ComparatorEqualityVerifier */
    public ComparatorEqualityVerifier(Comparator comparator) {
        this.comparator = comparator;
    }

    public boolean equals(Object a, Object b) {
        // TODO complete (null, exception...)
        return Comparables.isEqual(a, b, comparator);
    }
    
}
