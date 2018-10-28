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
 * CustomStyleProvider.java
 *
 * Created on 30. Januar 2003, 18:57
 */
package org.softsmithy.lib.swing.style;

import java.util.*;

/**
 *
 * @author puce
 */
public class CustomStyleProvider implements StyleProvider {

    private final CustomStyle style;

    /**
     * Creates a new instance of CustomStyleProvider
     * @param style the custom style
     */
    public CustomStyleProvider(CustomStyle style) {
        this.style = style;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Style getStyle(Styleable styleable) {
        minimizeCustomStyleProviderObjects();
        return getStyle();
    }

    public CustomStyle getStyle() {
        return style;
    }

    private void minimizeCustomStyleProviderObjects() {
        if (getStyle().getStyleProvider() != this) {
            getStyle().setStyleProvider(this);
        }
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        return getStyle().getName();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString(Locale locale) {
        return getStyle().getName(locale);
    }

}
