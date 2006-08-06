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
 * Isthis really a TypesafeEnum? Better way??
 * @author  puce
 */
public abstract class LocaleDisplay extends TypesafeEnum {
  
  /** Creates a new instance of LocaleDisplay */
  private LocaleDisplay(String name) {
    super(name);
  }
  
  public abstract String getString(Locale locale, Locale inLocale);
  
  public static final LocaleDisplay NAME = new LocaleDisplay("name"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayName(inLocale);
    }
  };
  
  public static final LocaleDisplay LANGUAGE = new LocaleDisplay("language"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayLanguage(inLocale);
    }
  };
  
  public static final LocaleDisplay COUNTRY = new LocaleDisplay("country"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayCountry(inLocale);
    }
  };
  
  public static final LocaleDisplay VARIANT = new LocaleDisplay("variant"){
    public String getString(Locale locale, Locale inLocale){
      return locale.getDisplayVariant(inLocale);
    }
  };
}
