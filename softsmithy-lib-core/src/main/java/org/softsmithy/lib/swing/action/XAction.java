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

package org.softsmithy.lib.swing.action;


import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.KeyStroke;

/**
 * XAction extends Action by defining more property keys and convenience
 * methods.<br>
 * Note: The API may change in a future version!<br>
 * Note: Eventually this belongs to the javax.swing.Action interface.
 *
 * @author    Florian Brunner
 */

public interface XAction extends Action {

  /**
   * The key used for storing a large icon for the disabled action.
   */
  public final static String LARGE_DISABLED_ICON = "LargeDisabledIcon";
  /**
   * The key used for storing a large icon for the disabled and selected action.
   */
  public final static String LARGE_DISABLED_SELECTED_ICON = "LargeDisabledSelectedIcon";
  /**
   * The key used for storing a large icon for the action.
   */
  public final static String LARGE_ICON = "LargeIcon";
  /**
   * The key used for storing a large icon for the pressed action.
   */
  public final static String LARGE_PRESSED_ICON = "LargePressedIcon";
  /**
   * The key used for storing a large icon for the action duricng rollover.
   */
  public final static String LARGE_ROLLOVER_ICON = "LargeRolloverIcon";
  /**
   * The key used for storing a large icon for the selected action during
   * rollover.
   */
  public final static String LARGE_ROLLOVER_SELECTED_ICON = "LargeRolloverSelectedIcon";
  /**
   * The key used for storing a large icon for the selected action.
   */
  public final static String LARGE_SELECTED_ICON = "LargeSelectedIcon";
  /**
   * The key used for storing the state of the action.
   */
  //public final static String SELECTED = "Selected";
  /**
   * The key used for storing a small icon for the disabled action.
   */
  public final static String SMALL_DISABLED_ICON = "SmallDisabledIcon";
  /**
   * The key used for storing a small icon for the disabled and selected action.
   */
  public final static String SMALL_DISABLED_SELECTED_ICON = "SmallDisabledSelectedIcon";
  /**
   * The key used for storing a small icon for the pressed action.
   */
  public final static String SMALL_PRESSED_ICON = "SmallPressedIcon";
  /**
   * The key used for storing a small icon for the action during rollover.
   */
  public final static String SMALL_ROLLOVER_ICON = "SmallRolloverIcon";
  /**
   * The key used for storing a small icon for the selected action during
   * rollover.
   */
  public final static String SMALL_ROLLOVER_SELECTED_ICON = "SmallRolloverSelectedIcon";
  /**
   * The key used for storing a small icon for the selected action.
   */
  public final static String SMALL_SELECTED_ICON = "SmallSelectedIcon";

  /**
   * Gets the value from the key Action.ACCELERATOR_KEY
   *
   * @return   the accelerator
   */
  public KeyStroke getAccelerator();

  /**
   * Sets the value for the key Action.ACCELERATOR_KEY
   *
   * @param accelerator  The new accelerator value
   */
  public void setAccelerator(KeyStroke accelerator);

  /**
   * Gets the value from the key Action.ACTION_COMMAND_KEY
   *
   * @return   the action command
   */
  public String getActionCommand();

  /**
   * Sets the value for the key Action.ACTION_COMMAND_KEY
   *
   * @param actionCommand  the action command
   */
  public void setActionCommand(String actionCommand);

  /**
   * Gets the value from the key XAction.LARGE_DISABLED_ICON
   *
   * @return   the large disabled icon
   */
  public Icon getLargeDisabledIcon();

  /**
   * Sets the value for the key XAction.LARGE_DISABLED_ICON
   *
   * @param icon  the large disabled icon
   */
  public void setLargeDisabledIcon(Icon icon);

  /**
   * Gets the value from the key XAction.LARGE_DISABLED_SELECTED_ICON
   *
   * @return   the large disabled selected icon
   */
  public Icon getLargeDisabledSelectedIcon();

  /**
   * Sets the value for the key XAction.LARGE_DISABLED_SELECTED_ICON
   *
   * @param icon  the large disabled selected icon
   */
  public void setLargeDisabledSelectedIcon(Icon icon);

  /**
   * Gets the value from the key XAction.LARGE_ICON
   *
   * @return   the large icon
   */
  public Icon getLargeIcon();

  /**
   * Sets the value for the key XAction.LARGE_ICON
   *
   * @param icon  the large icon
   */
  public void setLargeIcon(Icon icon);

  /**
   * Gets the value from the key XAction.LARGE_PRESSED_ICON
   *
   * @return   the large pressed icon value
   */
  public Icon getLargePressedIcon();

  /**
   * Sets the value for the key XAction.LARGE_PRESSED_ICON
   *
   * @param icon  the large pressed icon
   */
  public void setLargePressedIcon(Icon icon);

  /**
   * Gets the value from the key XAction.LARGE_ROLLOVER_ICON
   *
   * @return   the large rollover icon
   */
  public Icon getLargeRolloverIcon();

  /**
   * Sets the value for the key XAction.LARGE_ROLLOVER_ICON
   *
   * @param icon  the large rollover icon
   */
  public void setLargeRolloverIcon(Icon icon);

  /**
   * Gets the value from the key XAction.LARGE_ROLLOVER_SELECTED_ICON
   *
   * @return   the large rollover selected icon
   */
  public Icon getLargeRolloverSelectedIcon();

  /**
   * Sets the value for the key XAction.LARGE_ROLLOVER_SELECTED_ICON
   *
   * @param icon  the large rollover selected icon
   */
  public void setLargeRolloverSelectedIcon(Icon icon);

  /**
   * Gets the value from the key XAction.LARGE_SELECTED_ICON
   *
   * @return   the large selected icon
   */
  public Icon getLargeSelectedIcon();

  /**
   * Sets the value for the key XAction.LARGE_SELECTED_ICON
   *
   * @param icon  the large selected icon
   */
  public void setLargeSelectedIcon(Icon icon);

  /**
   * Gets the value from the key Action.LONG_DESCRIPTION
   *
   * @return   the long description
   */
  public String getLongDescription();

  /**
   * Sets the value for the key Action.LONG_DESCRIPTION
   *
   * @param longDescription  the long description
   */
  public void setLongDescription(String longDescription);

  /**
   * Gets the value from the key Action.MNEMONIC_KEY
   *
   * @return   the menmonic key as an int value defined by KeyEvent
   */
  public int getMnemonicKey();

  /**
   * Sets the value for the key Action.MNEMONIC_KEY
   *
   * @param menmonicKey  The new mnemonicKey value
   */
  public void setMnemonicKey(int menmonicKey);

  /**
   * Gets the value from the key Action.NAME
   *
   * @return   the name
   */
  public String getName();

  /**
   * Sets the value for the key Action.NAME
   *
   * @param name  the name
   */
  public void setName(String name);

  /**
   * Gets the value from the key Action.SHORT_DESCRIPTION
   *
   * @return   the short description
   */
  public String getShortDescription();

  /**
   * Sets the value for the key Action.SHORT_DESCRIPTION
   *
   * @param shortDescription  the short description
   */
  public void setShortDescription(String shortDescription);

  /**
   * Gets the value from the key XAction.SMALL_DISABLED_ICON
   *
   * @return   the small disabled icon
   */
  public Icon getSmallDisabledIcon();

  /**
   * Sets the value for the key XAction.SMALL_DISABLED_ICON
   *
   * @param icon  the small disabled icon
   */
  public void setSmallDisabledIcon(Icon icon);

  /**
   * Gets the value from the key XAction.SMALL_DISABLED_SELECTED_ICON
   *
   * @return   the small disabled selected icon
   */
  public Icon getSmallDisabledSelectedIcon();

  /**
   * Sets the value for the key XAction.SMALL_DISABLED_SELECTED_ICON
   *
   * @param icon  the small disabled selected icon
   */
  public void setSmallDisabledSelectedIcon(Icon icon);

  /**
   * Gets the value from the key Action.SMALL_ICON
   *
   * @return   the small icon
   */
  public Icon getSmallIcon();

  /**
   * Sets the value for the key Action.SMALL_ICON
   *
   * @param icon  the small icon
   */
  public void setSmallIcon(Icon icon);

  /**
   * Gets the value from the key XAction.SMALL_PRESSED_ICON
   *
   * @return   the small pressed icon
   */
  public Icon getSmallPressedIcon();

  /**
   * Sets the value for the key XAction.SMALL_PRESSED_ICON
   *
   * @param icon  the small pressed icon
   */
  public void setSmallPressedIcon(Icon icon);

  /**
   * Gets the value from the key XAction.SMALL_ROLLOVER_ICON
   *
   * @return   the small rollover icon
   */
  public Icon getSmallRolloverIcon();

  /**
   * Sets the value for the key XAction.SMALL_ROLLOVER_ICON
   *
   * @param icon  the small rollover icon
   */
  public void setSmallRolloverIcon(Icon icon);

  /**
   * Gets the value from the key XAction.SMALL_ROLLOVER_SELECTED_ICON
   *
   * @return   the small rollover selected icon
   */
  public Icon getSmallRolloverSelectedIcon();

  /**
   * Sets the value for the key XAction.SMALL_ROLLOVER_SELECTED_ICON
   *
   * @param icon  the small rollover selected icon
   */
  public void setSmallRolloverSelectedIcon(Icon icon);

  /**
   * Gets the value from the key XAction.SMALL_SELECTED_ICON
   *
   * @return   the small selected icon
   */
  public Icon getSmallSelectedIcon();

  /**
   * Sets the value for the key XAction.SMALL_SELECTED_ICON
   *
   * @param icon  the small selected icon
   */
  public void setSmallSelectedIcon(Icon icon);

  /** 
   * Gets the value of the key XAction.SELECTED
   * @return if selected
   *
   */
  //public boolean isSelected();
  
  /** 
   * Sets the value for the key XAction.SELECTED
   * @param selected if selected
   *
   */
  //public void setSelected(boolean selected);
  
  //public void addActionListener(ActionListener l);


  //public void removeActionListener(ActionListener l);
}
