/*
 * JListExchanger.java
 *
 * Created on 21. Oktober 2004, 17:46
 */
package org.softsmithy.lib.swing;

import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.softsmithy.lib.swing.action.DefaultXAction;
import org.softsmithy.lib.swing.action.IconType;
import org.softsmithy.lib.swing.action.NavigationActionFactory;
import org.softsmithy.lib.swing.action.XAction;
import org.softsmithy.lib.swing.action.XActions;

/**
 *
 * @author puce
 */
class JListExchanger extends JPanel {

    private static final Logger LOG = LoggerFactory.getLogger(JListExchanger.class);

    private XAction rightAction = new DefaultXAction();
    private XAction leftAction = new DefaultXAction();

    /**
     * Creates new form JListExchanger
     */
    public JListExchanger() {
        initComponents();
        try {
            rightAction = NavigationActionFactory.FORWARD.createXAction(this, getLocale());
            leftAction = NavigationActionFactory.BACK.createXAction(this, getLocale());
        } catch (NoSuchMethodException ex) { // should not happen here
            LOG.error(ex.getMessage(), ex);
        }
        XActions.configureButton(rightButton, rightAction, IconType.SMALL_ICON, false, false);
        XActions.configureButton(leftButton, leftAction, IconType.SMALL_ICON, false, false);
        rightAction.setEnabled(false);
        leftAction.setEnabled(false);
    }

    public void navigationForward(ActionEvent e) {
    }

    /*
    if (rightAction.isEnabled()){ // to avoid programmatic misuse
      int[] oldIndices = list.getSelectedIndices();
      int[] newIndices = new int[oldIndices.length];
      for (int i=0; i<oldIndices.length; i++){
        Object obj = listModel.remove(oldIndices[i]);
        newIndices[i] = oldIndices[i] - 1;
        listModel.add(newIndices[i], obj);
      }
      list.setSelectedIndices(newIndices);
      list.ensureIndexIsVisible(newIndices[0] > 0 ? newIndices[0] - 1 : newIndices[0]);
    }
  }*/
  
  public void navigationBack(ActionEvent e){
    }

    /*
    if (leftAction.isEnabled()){ // to avoid programmatic misuse
      int[] oldIndices = list.getSelectedIndices();
      int[] newIndices = new int[oldIndices.length];
      for (int i=oldIndices.length-1; i>=0; i--){
        newIndices[i] = oldIndices[i] + 1;
        Object obj = listModel.remove(oldIndices[i]);
        listModel.add(newIndices[i], obj);
      }
      list.setSelectedIndices(newIndices);
      int lastNewIndex = newIndices[newIndices.length - 1];
      list.ensureIndexIsVisible(lastNewIndex < listModel.getSize() - 1 ? lastNewIndex + 1 : lastNewIndex);
    }
  }*/
  
  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
     */
  private void initComponents() {//GEN-BEGIN:initComponents
    java.awt.GridBagConstraints gridBagConstraints;

    leftScrollPane = new javax.swing.JScrollPane();
    jList1 = new javax.swing.JList();
    buttonPanel = new javax.swing.JPanel();
    rightButton = new javax.swing.JButton();
    leftButton = new javax.swing.JButton();
    rightPanel = new javax.swing.JPanel();
    jListBox1 = new org.softsmithy.lib.swing.JListBox();

    setLayout(new java.awt.GridBagLayout());

    leftScrollPane.setViewportView(jList1);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(leftScrollPane, gridBagConstraints);

    buttonPanel.setLayout(new java.awt.GridBagLayout());

    rightButton.setMargin(new java.awt.Insets(3, 3, 3, 3));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.insets = new java.awt.Insets(10, 10, 5, 10);
    buttonPanel.add(rightButton, gridBagConstraints);

    leftButton.setMargin(new java.awt.Insets(3, 3, 3, 3));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.insets = new java.awt.Insets(5, 10, 10, 10);
    buttonPanel.add(leftButton, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
    gridBagConstraints.weighty = 1.0;
    add(buttonPanel, gridBagConstraints);

    rightPanel.setLayout(new java.awt.BorderLayout());

    rightPanel.add(jListBox1, java.awt.BorderLayout.CENTER);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(rightPanel, gridBagConstraints);

  }//GEN-END:initComponents


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JList jList1;
  private org.softsmithy.lib.swing.JListBox jListBox1;
  private javax.swing.JButton leftButton;
  private javax.swing.JScrollPane leftScrollPane;
  private javax.swing.JButton rightButton;
  private javax.swing.JPanel rightPanel;
  // End of variables declaration//GEN-END:variables

    /*private class EmptyListListener implements ListSelectionListener{
    
    public void valueChanged(ListSelectionEvent e) {
      if (! e.getValueIsAdjusting()){
        JList list = (JList)e.getSource();
        upAction.setEnabled(selectedButNotFirst(list));
        downAction.setEnabled(selectedButNotLast(list));
      }
      
    }
    
    private boolean selectedButNotEmpty(JList list){
      return list.getMinSelectionIndex() > 0; // not -1 and not 0
    }
    
    private boolean selectedButNotLast(JList list){
      return (! list.isSelectionEmpty()) && list.getMaxSelectionIndex() < listModel.getSize() - 1;
      
    }
    
    
  }*/
}
