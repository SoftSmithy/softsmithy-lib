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
 * ANDFileFilter.java
 *
 * Created on 30. Oktober 2002, 23:23
 */
package org.softsmithy.lib.io;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author puce
 */
public class ANDFileFilter extends CompoundFileFilter {

    /**
     * Creates a new instance of ANDFileFilter
     *
     * @param first the first file filter
     * @param second the second file filter
     */
    public ANDFileFilter(FileFilter first, FileFilter second) {
        super(first, second);
    }

    /**
     * Tests whether or not the specified abstract pathname should be included in a pathname list.
     *
     * @param pathname The abstract pathname to be tested
     * @return  <code>true</code> if and only if <code>pathname</code> should be included
     *
     */
    @Override
    public boolean accept(File pathname) {
        return getFirstFilter().accept(pathname) && getSecondFilter().accept(pathname);
    }

}
