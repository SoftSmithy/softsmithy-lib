/*
 * CustomizerBarPane.java
 *
 * Created on 11. September 2002, 18:08
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 *
 * @author  puce
 */
public class JTabbedCustomizerBar extends JTabbedPane implements CustomizerBar{
  
  //  private int oldIndex = -1;
  private CustomizerBar oldTabPane = null;
  /** Creates a new instance of CustomizerBarPane */
  public JTabbedCustomizerBar() {
    this.addChangeListener(new ChangeListener(){
      public void stateChanged(ChangeEvent e){
        if (oldTabPane != null){
          oldTabPane.clearSelection();
        }
        oldTabPane = (CustomizerBar) getSelectedComponent();
      }
    });
    oldTabPane = (CustomizerBar) getSelectedComponent();
  }
  
  public void consumeSelection(JCustomizerPane pane, Point point) {
    ((CustomizerBar) getSelectedComponent()).consumeSelection(pane, point);
  }
  
  public boolean hasSelection() {
    return ((CustomizerBar) getSelectedComponent()).hasSelection();
  }
  
  public void clearSelection() {
    ((CustomizerBar) getSelectedComponent()).clearSelection();
  }
  
  /** Inserts a <code>component</code>, at <code>index</code>,
   * represented by a <code>title</code> and/or <code>icon</code>,
   * either of which may be <code>null</code>. If <code>icon</code>
   * is non-<code>null</code> and it implements
   * <code>ImageIcon</code> a corresponding disabled icon will automatically
   * be created and set on the tabbedpane.
   * Uses java.util.Vector internally, see <code>insertElementAt</code>
   * for details of insertion conventions.
   *
   * @param title the title to be displayed in this tab
   * @param icon the icon to be displayed in this tab
   * @param component The component to be displayed when this tab is clicked.
   * @param tip the tooltip to be displayed for this tab
   * @param index the position to insert this new tab
   *
   * @see #addTab
   * @see #removeTabAt
   *
   */
  public void insertTab(String title, Icon icon, Component component, String tip, int index) {
    if (! (component instanceof CustomizerBar)){
      throw new IllegalArgumentException("Component must be a CustomizerBar!");
    }
    super.insertTab(title, icon, component, tip, index);
  }
  
}
