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
 * LocaleDisplay.java
 *
 * Created on 12. März 2003, 22:50
 */

package org.softsmithy.lib.util;

import java.util.*;

/**
 * This class specifies, which display String should be used with Locales 
 * (see strategy pattern).<br/>
 * <br/>
 * Note: Is this really a TypesafeEnum? Better way??
 * @author puce
 */
public abstract class LocaleDisplay extends TypesafeEnum {
  
  /** Creates a new instance of LocaleDisplay */
  private LocaleDisplay(String name) {
    super(name);
  }
  
  
    /**
     * Gets a display String for the specified Locale in the Locale specified.
     * @param locale the Locale
     * @param inLocale the Locale used for the translation
     * @return a localized display String of the specified Locale
     */
  public abstract String getString(Locale locale, Locale inLocale);
  
    /**
     * Selects the Locale.getDisplayName method.
     */
  public static final LocaleDisplay NAME = new LocaleDisplay("name"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayName(inLocale);
    }
  };
  
    /**
     * Selects the Locale.getDisplayLanguage method.
     */
  public static final LocaleDisplay LANGUAGE = new LocaleDisplay("language"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayLanguage(inLocale);
    }
  };
  
    /**
     * Selects the Locale.getDisplayCountry method.
     */
  public static final LocaleDisplay COUNTRY = new LocaleDisplay("country"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayCountry(inLocale);
    }
  };
  
    /**
     * Selects the Locale.getDisplayVariant method.
     */
  public static final LocaleDisplay VARIANT = new LocaleDisplay("variant"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayVariant(inLocale);
    }
  };
}
