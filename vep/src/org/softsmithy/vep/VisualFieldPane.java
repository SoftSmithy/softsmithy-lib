/*
 * VisualFieldPane.java
 *
 * Created on 4. Mai 2006, 23:22
 */

package org.softsmithy.vep;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.Timer;
import org.softsmithy.lib.swing.icon.FullZooming;
import org.softsmithy.lib.swing.icon.XIcon;
import org.softsmithy.lib.swing.icon.XIcons;

/**
 *
 * @author  florian.brunner
 */
public class VisualFieldPane extends javax.swing.JPanel {
    
    private VisualField visualField;
    private Timer timer;
    private int currentIndex = 0;
    /**
     * Holds value of property visualFieldDisplayed.
     */
    private boolean visualFieldDisplayed = true;
    /**
     * Holds value of property visualFieldTest.
     */
    private VisualFieldTest visualFieldTest;
    private List<XIcon> visualFieldImages;
    private Map<XIcon, XIcon> scaledVisualFieldImages = new HashMap<XIcon, XIcon>();
    
    /** Creates new form VisualFieldPane */
    public VisualFieldPane() {
        initComponents();
        visualField = new VisualField(50, 24, 3, 1, 100, 100, 100, 100, 200, 200, 200, 300, 300);
        timer = new Timer(0, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextImage();
            }
        });
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                scaledVisualFieldImages.clear();
                displayCurrentImage();
            }
            
        });
    }
    
    private synchronized void nextImage(){
        if (currentIndex == (visualFieldImages.size() - 1)){
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        displayCurrentImage();
    }
    
    public synchronized void start(){
        currentIndex = 0;
        displayCurrentImage();
        timer.start();
    }
    
    public void stop(){
        timer.stop();
        currentIndex = 0;
        displayCurrentImage();
    }
    
    private synchronized  void displayCurrentImage(){
        XIcon currentIcon = visualFieldImages.get(currentIndex);
        XIcon icon;
        if (scaledVisualFieldImages.containsKey(currentIcon)){
            icon = scaledVisualFieldImages.get(currentIcon);
        } else {
            icon = XIcons.calculateScaledIcon(currentIcon,
                    FullZooming.RESPECTING_ASPECT_RATIO_INSTANCE,
                    this);
            scaledVisualFieldImages.put(currentIcon, icon);
        }
        if (visualFieldDisplayed){
            visualFieldImageLabel.setIcon(icon);
        }
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        visualFieldImageLabel = new javax.swing.JLabel();

        setLayout(new java.awt.BorderLayout());

        visualFieldImageLabel.setBackground(new java.awt.Color(0, 0, 0));
        visualFieldImageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(visualFieldImageLabel, java.awt.BorderLayout.CENTER);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel visualFieldImageLabel;
    // End of variables declaration//GEN-END:variables
    
    
    
    /**
     * Getter for property visualFieldTest.
     * @return Value of property visualFieldTest.
     */
    public VisualFieldTest getVisualFieldTest() {
        return this.visualFieldTest;
    }
    
    /**
     * Setter for property visualFieldTest.
     * @param visualFieldTest New value of property visualFieldTest.
     */
    public void setVisualFieldTest(VisualFieldTest visualFieldTest) {
        this.visualFieldTest = visualFieldTest;
        visualFieldImages = visualField.createImages(visualFieldTest);
        scaledVisualFieldImages.clear();
    }
    
    
    /**
     * Setter for property frequency.
     * @param frequency New value of property frequency.
     */
    public void setFrequency(int frequency) {
        timer.setDelay(1000/frequency);
    }
    
    /**
     * Getter for property firstColor.
     * @return Value of property firstColor.
     */
    public Color getPrimaryColor() {
        return visualField.getPrimaryColor();
    }
    
    /**
     * Setter for property firstColor.
     * @param firstColor New value of property firstColor.
     */
    public void setPrimaryColor(Color primaryColor) {
        visualField.setPrimaryColor(primaryColor);
        recreateImages();
    }
    
    
    /**
     * Getter for property secondColor.
     * @return Value of property secondColor.
     */
    public Color getSecondaryColor() {
        return visualField.getSecondaryColor();
    }
    
    /**
     * Setter for property secondColor.
     * @param secondColor New value of property secondColor.
     */
    public void setSecondaryColor(Color secondaryColor) {
        visualField.setSecondaryColor(secondaryColor);
        recreateImages();
    }
    
    
    public Color getDeviderColor() {
        return visualField.getDeviderColor();
    }
    
    
    public void setDeviderColor(Color deviderColor) {
        visualField.setDeviderColor(deviderColor);
        recreateImages();
    }
    
    public Color getFixationColor() {
        return visualField.getFixationColor();
    }
    
    
    public void setFixationColor(Color fixationColor) {
        visualField.setFixationColor(fixationColor);
        recreateImages();
    }
    
    private void recreateImages(){
        visualFieldImages = visualField.createImages(visualFieldTest);
        scaledVisualFieldImages.clear();
        displayCurrentImage();
    }
    
    
    
    /**
     * Getter for property disabledColor.
     * @return Value of property disabledColor.
     */
    public Color getDisabledColor() {
        return visualFieldImageLabel.getBackground();
    }
    
    /**
     * Setter for property disabledColor.
     * @param disabledColor New value of property disabledColor.
     */
    public void setDisabledColor(Color disabledColor) {
        visualFieldImageLabel.setBackground(disabledColor);
    }
    
    
    
    /**
     * Getter for property visualFieldDisplayed.
     * @return Value of property visualFieldDisplayed.
     */
    public boolean isVisualFieldDisplayed() {
        return this.visualFieldDisplayed;
    }
    
    /**
     * Setter for property visualFieldDisplayed.
     * @param visualFieldDisplayed New value of property visualFieldDisplayed.
     */
    public synchronized void setVisualFieldDisplayed(boolean visualFieldDisplayed) {
        this.visualFieldDisplayed = visualFieldDisplayed;
        visualFieldImageLabel.setOpaque(! visualFieldDisplayed);
        if (visualFieldDisplayed){
            displayCurrentImage();
        } else {
                visualFieldImageLabel.setIcon(null);
        }
    }
    
}
