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
 * BigDecimals.java
 *
 * Created on 26. September 2003, 14:54
 */

package org.softsmithy.lib.math;

import java.math.*;

/**
 *
 * @author  puce
 */
public class BigDecimals {
  
  public static final BigDecimal ZERO = BigDecimal.valueOf(0);
  public static final BigDecimal ONE = BigDecimal.valueOf(1);
  public static final BigDecimal MIN_DOUBLE = new BigDecimal(Double.MIN_VALUE);
  public static final BigDecimal MAX_DOUBLE = new BigDecimal(Double.MAX_VALUE);
  public static final BigDecimal MIN_NEGATIVE_DOUBLE = new BigDecimal(Doubles.MIN_NEGATIVE_VALUE);
  public static final BigDecimal MAX_NEGATIVE_DOUBLE = new BigDecimal(Doubles.MAX_NEGATIVE_VALUE);
  public static final BigDecimal MIN_FLOAT = new BigDecimal(Float.MIN_VALUE);
  public static final BigDecimal MAX_FLOAT = new BigDecimal(Float.MAX_VALUE);
  public static final BigDecimal MIN_NEGATIVE_FLOAT = new BigDecimal(Floats.MIN_NEGATIVE_VALUE);
  public static final BigDecimal MAX_NEGATIVE_FLOAT = new BigDecimal(Floats.MAX_NEGATIVE_VALUE);
  
  /** Creates a new instance of BigDecimals */
  private BigDecimals() {
  }
  
  
}
