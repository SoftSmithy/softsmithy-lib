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
package org.softsmithy.lib.text;

import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 *
 * @author puce
 */
public class ResourceBundleLocalizer<T> implements Localizer<T> {
    private final ResourceBundle resourceBundle;
    private final Map<T, String> resourceBundleKeys;

    public ResourceBundleLocalizer(ResourceBundle resourceBundle, Map<T, String> resourceBundleKeys) {
        this.resourceBundle = resourceBundle;
        this.resourceBundleKeys = resourceBundleKeys;
    }

    @Override
    public String getDisplayString(T o, Locale inLocale) {
        return resourceBundle.getString(resourceBundleKeys.get(o));
    }
    
}
