package org.softsmithy.lib.swing;

import java.awt.event.ActionEvent;
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
  public KeyStroke getAccelerator() {
    return (KeyStroke) getValue(Action.ACCELERATOR_KEY);
  }

  /**
   * Sets the value for the key Action.ACCELERATOR_KEY
   *
   * @param keyStroke    The new accelerator value
   */
  public void setAccelerator(KeyStroke keyStroke) {
    putValue(Action.ACCELERATOR_KEY, keyStroke);
  }

  /**
   * Gets the value from the key Action.ACTION_COMMAND_KEY
   *
   * @return   the action command
   */
  public String getActionCommand() {
    return (String) getValue(Action.ACTION_COMMAND_KEY);
  }

  /**
   * Sets the value for the key Action.ACTION_COMMAND_KEY
   *
   * @param actionCommand  the action command
   */
  public void setActionCommand(String actionCommand) {
    putValue(Action.ACTION_COMMAND_KEY, actionCommand);
  }

  /**
   * Gets the value from the key XAction.LARGE_DISABLED_ICON
   *
   * @return   the large disabled icon
   */
  public Icon getLargeDisabledIcon() {
    return (Icon) getValue(XAction.LARGE_DISABLED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_DISABLED_ICON
   *
   * @param icon  the large disabled icon
   */
  public void setLargeDisabledIcon(Icon icon) {
    putValue(XAction.LARGE_DISABLED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_DISABLED_SELECTED_ICON
   *
   * @return   the large disabled selected icon
   */
  public Icon getLargeDisabledSelectedIcon() {
    return (Icon) getValue(XAction.LARGE_DISABLED_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_DISABLED_SELECTED_ICON
   *
   * @param icon  the large disabled selected icon
   */
  public void setLargeDisabledSelectedIcon(Icon icon) {
    putValue(XAction.LARGE_DISABLED_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_ICON
   *
   * @return   the large icon
   */
  public Icon getLargeIcon() {
    return (Icon) getValue(XAction.LARGE_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_ICON
   *
   * @param icon  the large icon
   */
  public void setLargeIcon(Icon icon) {
    putValue(XAction.LARGE_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_PRESSED_ICON
   *
   * @return   the large pressed icon value
   */
  public Icon getLargePressedIcon() {
    return (Icon) getValue(XAction.LARGE_PRESSED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_PRESSED_ICON
   *
   * @param icon  the large pressed icon
   */
  public void setLargePressedIcon(Icon icon) {
    putValue(XAction.LARGE_PRESSED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_ROLLOVER_ICON
   *
   * @return   the large rollover icon
   */
  public Icon getLargeRolloverIcon() {
    return (Icon) getValue(XAction.LARGE_ROLLOVER_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_ROLLOVER_ICON
   *
   * @param icon  the large rollover icon
   */
  public void setLargeRolloverIcon(Icon icon) {
    putValue(XAction.LARGE_ROLLOVER_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_ROLLOVER_SELECTED_ICON
   *
   * @return   the large rollover selected icon
   */
  public Icon getLargeRolloverSelectedIcon() {
    return (Icon) getValue(XAction.LARGE_ROLLOVER_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_ROLLOVER_SELECTED_ICON
   *
   * @param icon  the large rollover selected icon
   */
  public void setLargeRolloverSelectedIcon(Icon icon) {
    putValue(XAction.LARGE_ROLLOVER_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.LARGE_SELECTED_ICON
   *
   * @return   the large selected icon
   */
  public Icon getLargeSelectedIcon() {
    return (Icon) getValue(XAction.LARGE_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.LARGE_SELECTED_ICON
   *
   * @param icon  the large selected icon
   */
  public void setLargeSelectedIcon(Icon icon) {
    putValue(XAction.LARGE_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key Action.LONG_DESCRIPTION
   *
   * @return   the long description
   */
  public String getLongDescription() {
    return (String) getValue(Action.LONG_DESCRIPTION);
  }

  /**
   * Sets the value for the key Action.LONG_DESCRIPTION
   *
   * @param longDescription  the long description
   */
  public void setLongDescription(String longDescription) {
    putValue(Action.LONG_DESCRIPTION, longDescription);
  }

  /**
   * Gets the value from the key Action.MNEMONIC_KEY
   *
   * @return   the menmonic key as an int value defined by KeyEvent
   */
  public int getMnemonicKey() {
    return ((Integer) getValue(Action.MNEMONIC_KEY)).intValue();
  }

  /**
   * Sets the value for the key Action.MNEMONIC_KEY
   *
   * @param mnemonicKey  The new mnemonicKey value
   */
  public void setMnemonicKey(int mnemonicKey) {
    putValue(Action.MNEMONIC_KEY, new Integer(mnemonicKey));
  }

  /**
   * Gets the value from the key Action.NAME
   *
   * @return   the name
   */
  public String getName() {
    return (String) getValue(Action.NAME);
  }

  /**
   * Sets the value for the key Action.NAME
   *
   * @param name  the name
   */
  public void setName(String name) {
    putValue(Action.NAME, name);
  }

  /**
   * Gets the value from the key Action.SHORT_DESCRIPTION
   *
   * @return   the short description
   */
  public String getShortDescription() {
    return (String) getValue(Action.SHORT_DESCRIPTION);
  }

  /**
   * Sets the value for the key Action.SHORT_DESCRIPTION
   *
   * @param shortDescription  the short description
   */
  public void setShortDescription(String shortDescription) {
    putValue(Action.SHORT_DESCRIPTION, shortDescription);
  }

  /**
   * Gets the value from the key XAction.SMALL_DISABLED_ICON
   *
   * @return   the small disabled icon
   */
  public Icon getSmallDisabledIcon() {
    return (Icon) getValue(XAction.SMALL_DISABLED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_DISABLED_ICON
   *
   * @param icon  the small disabled icon
   */
  public void setSmallDisabledIcon(Icon icon) {
    putValue(XAction.SMALL_DISABLED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_DISABLED_SELECTED_ICON
   *
   * @return   the small disabled selected icon
   */
  public Icon getSmallDisabledSelectedIcon() {
    return (Icon) getValue(XAction.SMALL_DISABLED_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_DISABLED_SELECTED_ICON
   *
   * @param icon  the small disabled selected icon
   */
  public void setSmallDisabledSelectedIcon(Icon icon) {
    putValue(XAction.SMALL_DISABLED_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key Action.SMALL_ICON
   *
   * @return   the small icon
   */
  public Icon getSmallIcon() {
    return (Icon) getValue(Action.SMALL_ICON);
  }

  /**
   * Sets the value for the key Action.SMALL_ICON
   *
   * @param icon  the small icon
   */
  public void setSmallIcon(Icon icon) {
    putValue(Action.SMALL_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_PRESSED_ICON
   *
   * @return   the small pressed icon
   */
  public Icon getSmallPressedIcon() {
    return (Icon) getValue(XAction.SMALL_PRESSED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_PRESSED_ICON
   *
   * @param icon  the small pressed icon
   */
  public void setSmallPressedIcon(Icon icon) {
    putValue(XAction.SMALL_PRESSED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_ROLLOVER_ICON
   *
   * @return   the small rollover icon
   */
  public Icon getSmallRolloverIcon() {
    return (Icon) getValue(XAction.SMALL_ROLLOVER_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_ROLLOVER_ICON
   *
   * @param icon  the small rollover icon
   */
  public void setSmallRolloverIcon(Icon icon) {
    putValue(XAction.SMALL_ROLLOVER_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_ROLLOVER_SELECTED_ICON
   *
   * @return   the small rollover selected icon
   */
  public Icon getSmallRolloverSelectedIcon() {
    return (Icon) getValue(XAction.SMALL_ROLLOVER_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_ROLLOVER_SELECTED_ICON
   *
   * @param icon  the small rollover selected icon
   */
  public void setSmallRolloverSelectedIcon(Icon icon) {
    putValue(XAction.SMALL_ROLLOVER_SELECTED_ICON, icon);
  }

  /**
   * Gets the value from the key XAction.SMALL_SELECTED_ICON
   *
   * @return   the small selected icon
   */
  public Icon getSmallSelectedIcon() {
    return (Icon) getValue(XAction.SMALL_SELECTED_ICON);
  }

  /**
   * Sets the value for the key XAction.SMALL_SELECTED_ICON
   *
   * @param icon  the small selected icon
   */
  public void setSmallSelectedIcon(Icon icon) {
    putValue(XAction.SMALL_SELECTED_ICON, icon);
  }

}
