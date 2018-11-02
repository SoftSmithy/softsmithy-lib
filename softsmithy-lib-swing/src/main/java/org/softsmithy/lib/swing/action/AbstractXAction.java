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

import javax.swing.*;

/**
 * This class provides defaul implementations for the XAction interface.<br>
 * Note: The API may change in a future version!<br>
 * Note: Eventually this belongs to the javax.swing.Action interface.
 *
 * @author    Florian Brunner
 */

public abstract class AbstractXAction extends AbstractAction implements XAction {

  /**
   * Creates an empty AbstractXAction.
   */
  public AbstractXAction() {
    super();
  }

  /**
   * Gets the value from the key Action.ACCELERATOR_KEY
   *
   * @return   the accelerator
   */
  @Override
  public KeyStroke getAccelerator() {
    return (KeyStroke) getValue(Action.ACCELERATOR_KEY);
  }

  /**
   * Sets the value for the key Action.ACCELERATOR_KEY
   *
   * @param keyStroke    The new accelerator value
   */
  @Override
  public void setAccelerator(KeyStroke keyStroke) {
    putValue(Action.ACCELERATOR_KEY, keyStroke);
  }

  /**
   * Gets the value from the key Action.ACTION_COMMAND_KEY
   *
   * @return   the action command
   */
  @Override
  public String getActionCommand() {
    return (String) getValue(Action.ACTION_COMMAND_KEY);
  }

  /**
   * Sets the value for the key Action.ACTION_COMMAND_KEY
   *
   * @param actionCommand  the action command
   */
  @Override
  public void setActionCommand(String actionCommand) {
    putValue(Action.ACTION_COMMAND_KEY, actionCommand);
  }

  /**
   * Gets the value from the key XAction.LARGE_DISABLED_ICON
   *
   * @return   the large disabled icon
   */
  @Override
  public Icon getLargeDisabledIcon() {
    return (Icon) getValue(XAction.LARGE_DISABLED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_DISABLED_ICON
   *
   * @param icon  the large disabled icon
   */
  @Override
  public void setLargeDisabledIcon(Icon icon) {
    putValue(XAction.LARGE_DISABLED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_DISABLED_SELECTED_ICON
   *
   * @return   the large disabled selected icon
   */
  @Override
  public Icon getLargeDisabledSelectedIcon() {
    return (Icon) getValue(XAction.LARGE_DISABLED_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_DISABLED_SELECTED_ICON
   *
   * @param icon  the large disabled selected icon
   */
  @Override
  public void setLargeDisabledSelectedIcon(Icon icon) {
    putValue(XAction.LARGE_DISABLED_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_ICON
   *
   * @return   the large icon
   */
  @Override
  public Icon getLargeIcon() {
    return (Icon) getValue(XAction.LARGE_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_ICON
   *
   * @param icon  the large icon
   */
  @Override
  public void setLargeIcon(Icon icon) {
    putValue(XAction.LARGE_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_PRESSED_ICON
   *
   * @return   the large pressed icon value
   */
  @Override
  public Icon getLargePressedIcon() {
    return (Icon) getValue(XAction.LARGE_PRESSED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_PRESSED_ICON
   *
   * @param icon  the large pressed icon
   */
  @Override
  public void setLargePressedIcon(Icon icon) {
    putValue(XAction.LARGE_PRESSED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_ROLLOVER_ICON
   *
   * @return   the large rollover icon
   */
  @Override
  public Icon getLargeRolloverIcon() {
    return (Icon) getValue(XAction.LARGE_ROLLOVER_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_ROLLOVER_ICON
   *
   * @param icon  the large rollover icon
   */
  @Override
  public void setLargeRolloverIcon(Icon icon) {
    putValue(XAction.LARGE_ROLLOVER_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_ROLLOVER_SELECTED_ICON
   *
   * @return   the large rollover selected icon
   */
  @Override
  public Icon getLargeRolloverSelectedIcon() {
    return (Icon) getValue(XAction.LARGE_ROLLOVER_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_ROLLOVER_SELECTED_ICON
   *
   * @param icon  the large rollover selected icon
   */
  @Override
  public void setLargeRolloverSelectedIcon(Icon icon) {
    putValue(XAction.LARGE_ROLLOVER_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_SELECTED_ICON
   *
   * @return   the large selected icon
   */
  @Override
  public Icon getLargeSelectedIcon() {
    return (Icon) getValue(XAction.LARGE_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_SELECTED_ICON
   *
   * @param icon  the large selected icon
   */
  @Override
  public void setLargeSelectedIcon(Icon icon) {
    putValue(XAction.LARGE_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key Action.LONG_DESCRIPTION
   *
   * @return   the long description
   */
  @Override
  public String getLongDescription() {
    return (String) getValue(Action.LONG_DESCRIPTION);
  }

  /**
   * Sets the value for the key Action.LONG_DESCRIPTION
   *
   * @param longDescription  the long description
   */
  @Override
  public void setLongDescription(String longDescription) {
    putValue(Action.LONG_DESCRIPTION, longDescription);
  }

  /**
   * Gets the value from the key Action.MNEMONIC_KEY
   *
   * @return   the menmonic key as an int value defined by KeyEvent
   */
  @Override
  public int getMnemonicKey() {
    return (Integer) getValue(Action.MNEMONIC_KEY);
  }

  /**
   * Sets the value for the key Action.MNEMONIC_KEY
   *
   * @param mnemonicKey  The new mnemonicKey value
   */
  @Override
  public void setMnemonicKey(int mnemonicKey) {
    putValue(Action.MNEMONIC_KEY, mnemonicKey);
  }

  /**
   * Gets the value from the key Action.NAME
   *
   * @return   the name
   */
  @Override
  public String getName() {
    return (String) getValue(Action.NAME);
  }

  /**
   * Sets the value for the key Action.NAME
   *
   * @param name  the name
   */
  @Override
  public void setName(String name) {
    putValue(Action.NAME, name);
  }

  /**
   * Gets the value from the key Action.SHORT_DESCRIPTION
   *
   * @return   the short description
   */
  @Override
  public String getShortDescription() {
    return (String) getValue(Action.SHORT_DESCRIPTION);
  }

  /**
   * Sets the value for the key Action.SHORT_DESCRIPTION
   *
   * @param shortDescription  the short description
   */
  @Override
  public void setShortDescription(String shortDescription) {
    putValue(Action.SHORT_DESCRIPTION, shortDescription);
  }

  /**
   * Gets the value from the key XAction.SMALL_DISABLED_ICON
   *
   * @return   the small disabled icon
   */
  @Override
  public Icon getSmallDisabledIcon() {
    return (Icon) getValue(XAction.SMALL_DISABLED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_DISABLED_ICON
   *
   * @param icon  the small disabled icon
   */
  @Override
  public void setSmallDisabledIcon(Icon icon) {
    putValue(XAction.SMALL_DISABLED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_DISABLED_SELECTED_ICON
   *
   * @return   the small disabled selected icon
   */
  @Override
  public Icon getSmallDisabledSelectedIcon() {
    return (Icon) getValue(XAction.SMALL_DISABLED_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_DISABLED_SELECTED_ICON
   *
   * @param icon  the small disabled selected icon
   */
  @Override
  public void setSmallDisabledSelectedIcon(Icon icon) {
    putValue(XAction.SMALL_DISABLED_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key Action.SMALL_ICON
   *
   * @return   the small icon
   */
  @Override
  public Icon getSmallIcon() {
    return (Icon) getValue(Action.SMALL_ICON);
  }

  /**
   * Sets the value for the key Action.SMALL_ICON
   *
   * @param icon  the small icon
   */
  @Override
  public void setSmallIcon(Icon icon) {
    putValue(Action.SMALL_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_PRESSED_ICON
   *
   * @return   the small pressed icon
   */
  @Override
  public Icon getSmallPressedIcon() {
    return (Icon) getValue(XAction.SMALL_PRESSED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_PRESSED_ICON
   *
   * @param icon  the small pressed icon
   */
  @Override
  public void setSmallPressedIcon(Icon icon) {
    putValue(XAction.SMALL_PRESSED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_ROLLOVER_ICON
   *
   * @return   the small rollover icon
   */
  @Override
  public Icon getSmallRolloverIcon() {
    return (Icon) getValue(XAction.SMALL_ROLLOVER_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_ROLLOVER_ICON
   *
   * @param icon  the small rollover icon
   */
  @Override
  public void setSmallRolloverIcon(Icon icon) {
    putValue(XAction.SMALL_ROLLOVER_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_ROLLOVER_SELECTED_ICON
   *
   * @return   the small rollover selected icon
   */
  @Override
  public Icon getSmallRolloverSelectedIcon() {
    return (Icon) getValue(XAction.SMALL_ROLLOVER_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_ROLLOVER_SELECTED_ICON
   *
   * @param icon  the small rollover selected icon
   */
  @Override
  public void setSmallRolloverSelectedIcon(Icon icon) {
    putValue(XAction.SMALL_ROLLOVER_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_SELECTED_ICON
   *
   * @return   the small selected icon
   */
  @Override
  public Icon getSmallSelectedIcon() {
    return (Icon) getValue(XAction.SMALL_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_SELECTED_ICON
   *
   * @param icon  the small selected icon
   */
  @Override
  public void setSmallSelectedIcon(Icon icon) {
    putValue(XAction.SMALL_SELECTED_ICON, icon);
  }
 
}
