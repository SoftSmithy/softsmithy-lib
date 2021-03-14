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
package org.softsmithy.lib.swing.customizer.border;

import javax.swing.border.AbstractBorder;
import java.awt.*;

/**
 * http://www.javaworld.com/javaworld/jw-04-2002/jw-0426-designpatterns_p.html
 */
public class HandleBorder extends AbstractBorder {

    private final Color fLineColor;
    private final int fThick;
    private final int fLineInset;
    private final Handle fNHandle;
    private final Handle fNEHandle;
    private final Handle fEHandle;
    private final Handle fSEHandle;
    private final Handle fSHandle;
    private final Handle fSWHandle;
    private final Handle fWHandle;
    private final Handle fNWHandle;
    private final Handle[] fHandles;

    public HandleBorder() {
        this(Color.black, 6);
    }

    public HandleBorder(Color lineColor, int lineInset) {
        this(6, lineColor, lineInset);
    }

    public HandleBorder(Color lineColor) {
        this(6, lineColor);
    }

    public HandleBorder(int thick, Color lineColor) {
        this(thick, lineColor, thick / 2);
    }

    public HandleBorder(int thick) {
        this(thick, Color.black);
    }

    public HandleBorder(final int thick, Color lineColor, int lineInset) {
        fLineColor = lineColor;
        fThick = thick;
        fLineInset = lineInset;
        fNHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(w / 2 - thick / 2, 0, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fNEHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(w - thick, 0, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fEHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(w - thick, h / 2 - thick / 2, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fSEHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(w - thick, h - thick, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fSHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(w / 2 - thick / 2, h - thick, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fSWHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(0, h - thick, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fWHandle = new Handle(thick) {
            private Rectangle fRect;

            @Override
            public void setRect(int w, int h) {
                fRect = new Rectangle(0, h / 2 - thick / 2, thick, thick);
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fNWHandle = new Handle(thick) {
            private final Rectangle fRect = new Rectangle(0, 0, thick, thick);

            @Override
            public void setRect(int w, int h) {
            }

            @Override
            protected Rectangle getRect() {
                return fRect;
            }
        };
        fHandles = new Handle[]{fNHandle, fNEHandle, fEHandle, fSEHandle, fSHandle,
            fSWHandle, fWHandle, fNWHandle};
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x,
            int y, int w, int h) {
        Graphics copy = g.create();
        if (copy != null) {
            try {
                copy.translate(x, y);
                paintRectangle(c, copy, w, h);
                paintHandles(c, copy, w, h);
            } finally {
                copy.dispose();
            }
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return getBorderInsets(c, new Insets(0, 0, 0, 0));
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = fLineInset + 1;
        return insets;
    }

    protected void paintRectangle(Component c, Graphics g, int w, int h) {
        g.setColor(fLineColor);
        g.drawRect(fLineInset, fLineInset, w - 2 * fLineInset - 1, h - 2 * fLineInset - 1);
    }

    protected void paintHandles(Component c, Graphics g, int w, int h) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(fLineColor);

        for (Handle fHandle : fHandles) {
            fHandle.paint(c, g2, w, h);
        }
        /* g.fill(upperLeft); // upper left
        g.fillRect(w-thick,0,thick,thick); // upper right
        g.fillRect(0,h-thick,thick,thick); // lower left
        g.fillRect(w-thick,h-thick,thick,thick); // lower right
        g.fillRect(w/2-thick/2,0,thick,thick); // mid top
        g.fillRect(0,h/2-thick/2,thick,thick); // mid left
        g.fillRect(w/2-thick/2,h-thick,thick,thick); // mid bottom
        g.fillRect(w-thick,h/2-thick/2,thick,thick); // mid right*/
    }

    public Handle getNHandle() {
        return fNHandle;
    }

    public Handle getNEHandle() {
        return fNEHandle;
    }

    public Handle getEHandle() {
        return fEHandle;
    }

    public Handle getSEHandle() {
        return fSEHandle;
    }

    public Handle getSHandle() {
        return fSHandle;
    }

    public Handle getSWHandle() {
        return fSWHandle;
    }

    public Handle getWHandle() {
        return fWHandle;
    }

    public Handle getNWHandle() {
        return fNWHandle;
    }

    public Handle getHandleAt(Point p) {
        Handle handle = null;
        for (Handle fHandle : fHandles) {
            if (fHandle.contains(p)) {
                handle = fHandle;
                break;
            }
        }
        /*if (p.x < thick){
         if (p.y < thick){
         } else if (p.y >= (h/2-thick/2)){}
         } else if (p.x >= (w/2-thick/2) && p.x < (w/2+thick/2)){
         } else if (p.x >= w-thick){
         }*/
        return handle;
    }

    public Handle[] getHandles() {
        return fHandles;
    }

    public static abstract class Handle {

        private final int fThick;

        public Handle(int thick) {
            fThick = thick;
        }

        public void paint(Component c, Graphics2D g, int w, int h) {
            setRect(w, h);
            g.fill(getRect());
        }

        public abstract void setRect(int w, int h);

        protected abstract Rectangle getRect();

        public boolean contains(Point p) {
            return getRect().contains(p);
        }
    }
}
