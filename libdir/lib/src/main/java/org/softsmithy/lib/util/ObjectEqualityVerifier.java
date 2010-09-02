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
 * ObjectEqualityVerifier.java
 *
 * Created on 11. Mai 2007, 00:33
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

/**
 *
 * @author puce
 */
// Not yet used
public class ObjectEqualityVerifier implements EqualityVerifier{
    
    /** Creates a new instance of ObjectEqualityVerifier */
    public ObjectEqualityVerifier() {
    }

    @Override
    public boolean equals(Object a, Object b) {
        return a.equals(b);
    }
    
}
