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

public class XActionWrapper extends ActionWrapper implements XAction{
  
  private XAction XAction;
  
  public XActionWrapper(XAction XAction){
    super(XAction);
    this.XAction = XAction;
  }
    @Override
  public java.lang.String getName(){
    return XAction.getName();
  }
    @Override
  public void setName(String string){
    XAction.setName(string);
  }
    @Override
  public KeyStroke getAccelerator(){
    return XAction.getAccelerator();
  }
    @Override
  public void setAccelerator(KeyStroke keyStroke){
    XAction.setAccelerator(keyStroke);
  }
    @Override
  public String getActionCommand(){
    return XAction.getActionCommand();
  }
    @Override
  public void setActionCommand(String string){
    XAction.setActionCommand(string);
  }
    @Override
  public Icon getLargeDisabledIcon(){
    return XAction.getLargeDisabledIcon();
  }
    @Override
  public void setLargeDisabledIcon(Icon icon){
    XAction.setLargeDisabledIcon(icon);
  }
    @Override
  public Icon getLargeDisabledSelectedIcon(){
    return XAction.getLargeDisabledSelectedIcon();
  }
    @Override
  public void setLargeDisabledSelectedIcon(Icon icon){
    XAction.setLargeDisabledSelectedIcon(icon);
  }
    @Override
  public Icon getLargeIcon(){
    return XAction.getLargeIcon();
  }
    @Override
  public void setLargeIcon(Icon icon){
    XAction.setLargeIcon(icon);
  }
    @Override
  public Icon getLargePressedIcon(){
    return XAction.getLargePressedIcon();
  }
    @Override
  public void setLargePressedIcon(Icon icon){
    XAction.setLargePressedIcon(icon);
  }
    @Override
  public Icon getLargeRolloverIcon(){
    return XAction.getLargeRolloverIcon();
  }
    @Override
  public void setLargeRolloverIcon(Icon icon){
    XAction.setLargeRolloverIcon(icon);
  }
    @Override
  public Icon getLargeRolloverSelectedIcon(){
    return XAction.getLargeRolloverSelectedIcon();
  }
    @Override
  public void setLargeRolloverSelectedIcon(Icon icon){
    XAction.setLargeRolloverSelectedIcon(icon);
  }
    @Override
  public Icon getLargeSelectedIcon(){
    return XAction.getLargeSelectedIcon();
  }
    @Override
  public void setLargeSelectedIcon(Icon icon){
    XAction.setLargeSelectedIcon(icon);
  }
    @Override
  public String getLongDescription(){
    return XAction.getLongDescription();
  }
    @Override
  public void setLongDescription(String string){
    XAction.setLongDescription(string);
  }
    @Override
  public int getMnemonicKey(){
    return XAction.getMnemonicKey();
  }
    @Override
  public void setMnemonicKey(int mnemonicKey){
    XAction.setMnemonicKey(mnemonicKey);
  }
    @Override
  public String getShortDescription(){
    return XAction.getShortDescription();
  }
    @Override
  public void setShortDescription(String string){
    XAction.setShortDescription(string);
  }
    @Override
  public Icon getSmallDisabledIcon(){
    return XAction.getSmallDisabledIcon();
  }
    @Override
  public void setSmallDisabledIcon(Icon icon){
    XAction.setSmallDisabledIcon(icon);
  }
    @Override
  public Icon getSmallDisabledSelectedIcon(){
    return XAction.getSmallDisabledSelectedIcon();
  }
    @Override
  public void setSmallDisabledSelectedIcon(Icon icon){
    XAction.setSmallDisabledSelectedIcon(icon);
  }
    @Override
  public Icon getSmallIcon(){
    return XAction.getSmallIcon();
  }
    @Override
  public void setSmallIcon(Icon icon){
    XAction.setSmallIcon(icon);
  }
    @Override
  public Icon getSmallPressedIcon(){
    return XAction.getSmallPressedIcon();
  }
    @Override
  public void setSmallPressedIcon(Icon icon){
    XAction.setSmallPressedIcon(icon);
  }
    @Override
  public Icon getSmallRolloverIcon(){
    return XAction.getSmallRolloverIcon();
  }
    @Override
  public void setSmallRolloverIcon(Icon icon){
    XAction.setSmallRolloverIcon(icon);
  }
    @Override
  public Icon getSmallRolloverSelectedIcon(){
    return XAction.getSmallRolloverSelectedIcon();
  }
    @Override
  public void setSmallRolloverSelectedIcon(Icon icon){
    XAction.setSmallRolloverSelectedIcon(icon);
  }
    @Override
  public Icon getSmallSelectedIcon(){
    return XAction.getSmallSelectedIcon();
  }
    @Override
  public void setSmallSelectedIcon(Icon icon){
    XAction.setSmallSelectedIcon(icon);
  }


}