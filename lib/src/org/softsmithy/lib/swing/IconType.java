package org.softsmithy.lib.swing;

import java.util.*;
import org.softsmithy.lib.util.TypesafeEnum;

/**
 * A typesafe enum class. Defines some icon types. <br>
 * Note: This class may change package in a future version.
 *
 * @author    Florian Brunner
 */

public class IconType extends TypesafeEnum {

  /**
   * Creates a new icon type.
   *
   * @param name  the name of this icon type
   */
  private IconType(String name) {
    super(name);
  }

  /**
   * Large icon type.
   */
  public final static IconType LARGE_ICON = new IconType("largeIcon");
  /**
   * Small icon type.
   */
  public final static IconType SMALL_ICON = new IconType("smallIcon");
  /**
   * No icon type.
   */
  public final static IconType NO_ICON = new IconType("noIcon");

  private final static IconType[] PRIVATE_VALUES = {LARGE_ICON, SMALL_ICON, NO_ICON};

  /**
   * A list of all defined icon types.
   */
  public final static List VALUES =
      Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
}
