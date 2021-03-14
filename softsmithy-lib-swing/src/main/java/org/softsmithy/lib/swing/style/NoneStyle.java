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
 * AbstractNoneStyle.java
 *
 * Created on 11. Juli 2003, 18:37
 */
package org.softsmithy.lib.swing.style;

import java.awt.*;
import java.util.Locale;

/**
 *
 * @author puce
 */
public class NoneStyle implements Style {

    /**
     * Holds value of property styleable.
     */
    private final Styleable styleable;

    /**
     * Creates a new instance of AbstractNoneStyle
     * @param styleable the styleable
     */
    public NoneStyle(Styleable styleable) {
        this.styleable = styleable;
    }

    @Override
    public Color getBackground() {
        return getStyleable().getDefaultBackground();
    }

    @Override
    public Font getFont() {
        return getStyleable().getDefaultFont();
    }

    @Override
    public Color getForeground() {
        return getStyleable().getDefaultForeground();
    }

    @Override
    public boolean isOpaque() {
        return getStyleable().isDefaultOpaque();
    }

    @Override
    public String getName(Locale locale) {
        return Styles.getNoneStyleName(locale);
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public StyleProvider getStyleProvider() {
        return NoneStyleProvider.INSTANCE;
    }

    @Override
    public void startListening() {
    }

    @Override
    public void stopListening() {
    }

    /**
     * Getter for property styleable.
     *
     * @return Value of property styleable.
     *
     */
    public Styleable getStyleable() {
        return this.styleable;
    }
}
