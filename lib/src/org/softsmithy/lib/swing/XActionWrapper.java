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

package org.softsmithy.lib.swing;

import java.beans.*;
import javax.swing.*;

public class XActionWrapper extends ActionWrapper implements XAction{
  
  private XAction XAction;
  
  public XActionWrapper(XAction XAction){
    super(XAction);
    this.XAction = XAction;
  }
  public java.lang.String getName(){
    return XAction.getName();
  }
  public void setName(String string){
    XAction.setName(string);
  }
  public KeyStroke getAccelerator(){
    return XAction.getAccelerator();
  }
  public void setAccelerator(KeyStroke keyStroke){
    XAction.setAccelerator(keyStroke);
  }
  public String getActionCommand(){
    return XAction.getActionCommand();
  }
  public void setActionCommand(String string){
    XAction.setActionCommand(string);
  }
  public Icon getLargeDisabledIcon(){
    return XAction.getLargeDisabledIcon();
  }
  public void setLargeDisabledIcon(Icon icon){
    XAction.setLargeDisabledIcon(icon);
  }
  public Icon getLargeDisabledSelectedIcon(){
    return XAction.getLargeDisabledSelectedIcon();
  }
  public void setLargeDisabledSelectedIcon(Icon icon){
    XAction.setLargeDisabledSelectedIcon(icon);
  }
  public Icon getLargeIcon(){
    return XAction.getLargeIcon();
  }
  public void setLargeIcon(Icon icon){
    XAction.setLargeIcon(icon);
  }
  public Icon getLargePressedIcon(){
    return XAction.getLargePressedIcon();
  }
  public void setLargePressedIcon(Icon icon){
    XAction.setLargePressedIcon(icon);
  }
  public Icon getLargeRolloverIcon(){
    return XAction.getLargeRolloverIcon();
  }
  public void setLargeRolloverIcon(Icon icon){
    XAction.setLargeRolloverIcon(icon);
  }
  public Icon getLargeRolloverSelectedIcon(){
    return XAction.getLargeRolloverSelectedIcon();
  }
  public void setLargeRolloverSelectedIcon(Icon icon){
    XAction.setLargeRolloverSelectedIcon(icon);
  }
  public Icon getLargeSelectedIcon(){
    return XAction.getLargeSelectedIcon();
  }
  public void setLargeSelectedIcon(Icon icon){
    XAction.setLargeSelectedIcon(icon);
  }
  public String getLongDescription(){
    return XAction.getLongDescription();
  }
  public void setLongDescription(String string){
    XAction.setLongDescription(string);
  }
  public int getMnemonicKey(){
    return XAction.getMnemonicKey();
  }
  public void setMnemonicKey(int mnemonicKey){
    XAction.setMnemonicKey(mnemonicKey);
  }
  public String getShortDescription(){
    return XAction.getShortDescription();
  }
  public void setShortDescription(String string){
    XAction.setShortDescription(string);
  }
  public Icon getSmallDisabledIcon(){
    return XAction.getSmallDisabledIcon();
  }
  public void setSmallDisabledIcon(Icon icon){
    XAction.setSmallDisabledIcon(icon);
  }
  public Icon getSmallDisabledSelectedIcon(){
    return XAction.getSmallDisabledSelectedIcon();
  }
  public void setSmallDisabledSelectedIcon(Icon icon){
    XAction.setSmallDisabledSelectedIcon(icon);
  }
  public Icon getSmallIcon(){
    return XAction.getSmallIcon();
  }
  public void setSmallIcon(Icon icon){
    XAction.setSmallIcon(icon);
  }
  public Icon getSmallPressedIcon(){
    return XAction.getSmallPressedIcon();
  }
  public void setSmallPressedIcon(Icon icon){
    XAction.setSmallPressedIcon(icon);
  }
  public Icon getSmallRolloverIcon(){
    return XAction.getSmallRolloverIcon();
  }
  public void setSmallRolloverIcon(Icon icon){
    XAction.setSmallRolloverIcon(icon);
  }
  public Icon getSmallRolloverSelectedIcon(){
    return XAction.getSmallRolloverSelectedIcon();
  }
  public void setSmallRolloverSelectedIcon(Icon icon){
    XAction.setSmallRolloverSelectedIcon(icon);
  }
  public Icon getSmallSelectedIcon(){
    return XAction.getSmallSelectedIcon();
  }
  public void setSmallSelectedIcon(Icon icon){
    XAction.setSmallSelectedIcon(icon);
  }


}

