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
 * EqualityVerifier.java
 *
 * Created on 11. Mai 2007, 00:24
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util;

/**
 *
 * Useful for generated classes, which don't override the equals method (e.g. some JAXB class generators)
 *
 * @author puce
 */
// TODO use generics with jdk v1,5
public interface EqualityVerifier {
    
    public boolean equals(Object a, Object b);
    
}
