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
  public static final BigDecimal MIN_FLOAT = new BigDecimal(Float.MIN_VALUE);
  public static final BigDecimal MAX_FLOAT = new BigDecimal(Float.MAX_VALUE);
  
  /** Creates a new instance of BigDecimals */
  private BigDecimals() {
  }
  
  
}
