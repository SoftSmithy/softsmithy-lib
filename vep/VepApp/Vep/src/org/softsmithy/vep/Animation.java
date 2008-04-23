/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.softsmithy.vep;

/**
 *
 * @author puce
 */
public class Animation {

    private VisualField visualField;
    private AnimationMode animationMode;

    public VisualField getVisualField() {
        return visualField;
    }

    public AnimationMode getAnimationMode() {
        return animationMode;
    }

    public void setAnimationMode(AnimationMode animationMode) {
        this.animationMode = animationMode;
    }
}
