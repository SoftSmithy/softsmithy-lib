package puce.swing.border;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * http://www.javaworld.com/javaworld/jw-04-2002/jw-0426-designpatterns_p.html
 */

public class HandleBorder extends AbstractBorder {
    protected Color lineColor;
    protected int thick;
    
    public HandleBorder() {
        this(Color.black, 6);
    }
    public HandleBorder(Color lineColor, int thick) {
        this.lineColor = lineColor;
        this.thick = thick;
    }
    public void paintBorder(Component c, Graphics g, int x,
    int y, int w, int h) {
        Graphics copy = g.create();
        if(copy != null) {
            try {
                copy.translate(x,y);
                paintRectangle(c,copy,w,h);
                paintHandles(c,copy,w,h);
            }
            finally {
                copy.dispose();
            }
        }
    }
    public Insets getBorderInsets() {
        return new Insets(thick,thick,thick,thick);
    }
    protected void paintRectangle(Component c, Graphics g,
    int w, int h) {
        g.setColor(lineColor);
        g.drawRect(thick/2,thick/2,w-thick-1,h-thick-1);
    }
    protected void paintHandles(Component c, Graphics g,
    int w, int h) {
        g.setColor(lineColor);
        
        g.fillRect(0,0,thick,thick); // upper left
        g.fillRect(w-thick,0,thick,thick); // upper right
        g.fillRect(0,h-thick,thick,thick); // lower left
        g.fillRect(w-thick,h-thick,thick,thick); // lower right
        g.fillRect(w/2-thick/2,0,thick,thick); // mid top
        g.fillRect(0,h/2-thick/2,thick,thick); // mid left
        g.fillRect(w/2-thick/2,h-thick,thick,thick); // mid bottom
        g.fillRect(w-thick,h/2-thick/2,thick,thick); // mid right
    }
}
