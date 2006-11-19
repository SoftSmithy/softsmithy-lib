/*
 * VisualField.java
 *
 * Created on 4. Mai 2006, 23:13
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.vep;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.softsmithy.lib.swing.icon.XIcon;
import org.softsmithy.lib.swing.icon.XImageIcon;
import org.softsmithy.lib.util.XMath;

/**
 *
 * @author florian.brunner
 */
public class VisualField {
    
    
    private XIcon defaultVisualField;
    
    private final int diameter;
    private final List<List<Area>> segments;
    private final Area fixation;
    private final int deviderCircleIndex;

    private Color deviderColor = Color.GRAY;

    private Color fixationColor = Color.BLACK;
    
    /** Creates a new instance of VisualField */
    public VisualField(double fixationRadius, int nSections, int deviderCircleIndex, double... radiusInc) {
        this.deviderCircleIndex = deviderCircleIndex;
        double centerCoord = XMath.sum(radiusInc) + fixationRadius;
        Point2D.Double center = new Point2D.Double(centerCoord, centerCoord);
        double radius = fixationRadius;
        fixation = new Area(new Ellipse2D.Double(center.x - radius, center.y - radius, 2*radius, 2*radius));
        
        
        List<Area> circles = new ArrayList<Area>();
        for (int i=0; i<radiusInc.length; i++){
            radius += radiusInc[i];
            Area circle = new Area(new Ellipse2D.Double(center.x - radius, center.y - radius, 2*radius, 2*radius));
            //circle.subtract(fixation);
            for (Area area : circles){
                circle.subtract(area);
            }
            circles.add(circle);
        }
        double angle = ((double) 360)/ nSections;
        Area section = new Area(new Arc2D.Double(circles.get(radiusInc.length-1).getBounds2D(), 90, angle, Arc2D.PIE ));
        segments = new ArrayList<List<Area>>();
        
        AffineTransform at = AffineTransform.getRotateInstance(2*Math.PI / nSections, center.x, center.y);
        
        for (int i=0; i<nSections; i++){
            section.transform(at);
            List<Area> sectionSegments = new ArrayList<Area>();
            for (int j=0; j<radiusInc.length; j++){
                Area segment = new Area(section);
                segment.subtract(fixation);
                for (int k=0; k<radiusInc.length; k++){
                    if (j != k){
                        segment.subtract(circles.get(k));
                    }
                }
                sectionSegments.add(segment);
            }
            segments.add(sectionSegments);
        }
        diameter = (int) Math.ceil(2*radius);
        createDefaultImage();
    }
    
    public int getNSections(){
        return segments.size();
    }
    
    
    
    private void createDefaultImage() {
        defaultVisualField = new XImageIcon(createImage(Collections.EMPTY_SET, false, false));
    }
    
    private Image createImage(Set<Integer> colorSwitchingIndices, boolean inner, boolean outer) {
        BufferedImage bi = new BufferedImage(diameter, diameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D big = bi.createGraphics();
        big.setBackground(new Color(0,0,0, 0)); //needed???
        Color oldColor = big.getColor();
        
        boolean sectionStartPrimary = true;
        for (int i=0; i<segments.size(); i++){
            boolean primary = sectionStartPrimary;
            List<Area> sectionSegments = segments.get(i);
            int j = 0;
            for (Area segment : sectionSegments){
                if (j == deviderCircleIndex){
                    big.setColor(deviderColor);
                } else {
                    if (((inner && j == 0) || (!(inner && outer) && (j == deviderCircleIndex + 1))) && colorSwitchingIndices.contains(i)){
                        primary = !primary;
                    }
                    if (primary){
                        big.setColor(getPrimaryColor());
                    } else {
                        big.setColor(getSecondaryColor());
                    }
                    primary = !primary;
                }
                
                big.fill(segment);
                j++;
            }
            sectionStartPrimary = !sectionStartPrimary;
        }
        big.setColor(deviderColor);
        big.fillRect(diameter/2 - fixation.getBounds().width/2, 0, fixation.getBounds().width, diameter);
        big.setColor(fixationColor);
        big.fill(fixation);
        big.setColor(oldColor);
        return bi;
    }
    
    private XIcon createXIcon(Set<Integer> nonColorSwitchingIndices, boolean inner, boolean outer) {
        return new XImageIcon(createImage(nonColorSwitchingIndices, inner, outer));
    }
    
    
    public List<XIcon> createImages(VisualFieldTest visualFieldTest) {
        return Arrays.asList(defaultVisualField, createXIcon(visualFieldTest.getColorSwitchingIndices(getNSections()), visualFieldTest.isInner(), visualFieldTest.isOuter()));
    }
    
    /**
     * Holds value of property firstColor.
     */
    private Color primaryColor = Color.lightGray;
    
    /**
     * Getter for property firstColor.
     * @return Value of property firstColor.
     */
    public Color getPrimaryColor() {
        return this.primaryColor;
    }
    
    /**
     * Setter for property firstColor.
     * @param firstColor New value of property firstColor.
     */
    public void setPrimaryColor(Color primaryColor) {
        this.primaryColor = primaryColor;
        createDefaultImage();
    }
    
    /**
     * Holds value of property secondColor.
     */
    private Color secondaryColor = Color.red;
    
    /**
     * Getter for property secondColor.
     * @return Value of property secondColor.
     */
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }
    
    /**
     * Setter for property secondColor.
     * @param secondColor New value of property secondColor.
     */
    public void setSecondaryColor(Color secondaryColor) {
        this.secondaryColor = secondaryColor;
        createDefaultImage();
    }

    public Color getDeviderColor() {
        return deviderColor;
    }

    public void setDeviderColor(Color deviderColor) {
        this.deviderColor = deviderColor;
        createDefaultImage();
    }

    public Color getFixationColor() {
        return fixationColor;
    }

    public void setFixationColor(Color fixationColor) {
        this.fixationColor = fixationColor;
        createDefaultImage();
    }
    
    
    
    
    
}
