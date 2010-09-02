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
 * BigIntegers.java
 *
 * Created on 10. September 2003, 18:25
 */

package org.softsmithy.lib.math;

import java.math.*;

/**
 *
 * @author  puce
 */
public class BigIntegers {
  
  public static final BigInteger MIN_INTEGER = BigInteger.valueOf(Integer.MIN_VALUE);
  public static final BigInteger MAX_INTEGER = BigInteger.valueOf(Integer.MAX_VALUE);
  public static final BigInteger MIN_LONG = BigInteger.valueOf(Long.MIN_VALUE);
  public static final BigInteger MAX_LONG = BigInteger.valueOf(Long.MAX_VALUE);
  public static final BigInteger MIN_SHORT = BigInteger.valueOf(Short.MIN_VALUE);
  public static final BigInteger MAX_SHORT = BigInteger.valueOf(Short.MAX_VALUE);
  public static final BigInteger MIN_BYTE = BigInteger.valueOf(Byte.MIN_VALUE);
  public static final BigInteger MAX_BYTE = BigInteger.valueOf(Byte.MAX_VALUE);
  
  /** Creates a new instance of BigIntegers */
  private BigIntegers() {
  }
  
}
