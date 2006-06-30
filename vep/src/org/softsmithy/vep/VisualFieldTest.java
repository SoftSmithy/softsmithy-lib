/*
 * VisualFieldTest.java
 *
 * Created on 18. Juni 2006, 15:29
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.vep;

import java.util.List;
import org.softsmithy.lib.swing.icon.XIcon;

/**
 *
 * @author florian.brunner
 */
public enum VisualFieldTest {
    LEFT{
        public List<XIcon> getImages(VisualField visualField) {
            return visualField.getLeftImages();
        }
        
    },
    RIGHT{
        public List<XIcon> getImages(VisualField visualField) {
            return  visualField.getRightImages();
        }
        
    },
    LEFT_HALF{
        public List<XIcon> getImages(VisualField visualField) {
            return visualField.getLeftHalfmages();
        }
        
    },
    RIGHT_HALF{
        public List<XIcon> getImages(VisualField visualField) {
            return visualField.getRightHalfImages();
        }
        
    },
     MIDDLE{
        public List<XIcon> getImages(VisualField visualField) {
            return visualField.getMiddleImages();
        }
        
    },
     FULL{
        public List<XIcon> getImages(VisualField visualField) {
            return visualField.getFullImages();
        }
        
    };
    
    public abstract List<XIcon> getImages(VisualField visualField);
}
