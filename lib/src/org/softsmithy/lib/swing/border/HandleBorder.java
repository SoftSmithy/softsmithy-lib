package puce.swing.border;

import javax.swing.border.*;
import java.awt.*;

/**
 * http://www.javaworld.com/javaworld/jw-04-2002/jw-0426-designpatterns_p.html
 */

public class HandleBorder extends AbstractBorder {
  
  private final Color fLineColor;
  private final int fThick;
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
  
  public HandleBorder(Color lineColor) {
    this(lineColor, 6);
  }
  
  public HandleBorder(int thick) {
    this(Color.black, thick);
  }
  
  public HandleBorder(Color lineColor, final int thick) {
    fLineColor = lineColor;
    fThick = thick;
    fNHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(w/2-thick/2,0,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fNEHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(w-thick,0,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fEHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(w-thick,h/2-thick/2,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fSEHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(w-thick,h-thick,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fSHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(w/2-thick/2,h-thick,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fSWHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(0,h-thick,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fWHandle = new Handle(thick){
      private Rectangle fRect;
      public void setRect(int w, int h){
        fRect = new Rectangle(0,h/2-thick/2,thick,thick);
      }
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fNWHandle = new Handle(thick){
      private final Rectangle fRect = new Rectangle(0,0,thick,thick);
      public void setRect(int w, int h){}
      protected Rectangle getRect(){
        return fRect;
      }
    };
    fHandles = new Handle[]{fNHandle,fNEHandle,fEHandle,fSEHandle,fSHandle,
    fSWHandle,fWHandle,fNWHandle};
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
    return new Insets(fThick,fThick,fThick,fThick);
  }
  protected void paintRectangle(Component c, Graphics g, int w, int h) {
    g.setColor(fLineColor);
    g.drawRect(fThick/2,fThick/2,w-fThick-1,h-fThick-1);
  }
  protected void paintHandles(Component c, Graphics g, int w, int h) {
    Graphics2D g2 = (Graphics2D) g;
    g2.setColor(fLineColor);
    
    for (int i=0; i<fHandles.length; i++){
      fHandles[i].paint(c, g2, w, h);
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
  
  public Handle getNWHandle(){
    return fNWHandle;
  }
  
  public Handle getHandleAt(Point p){
    Handle handle = null;
    for (int i=0; i<fHandles.length; i++){
      if (fHandles[i].contains(p)){
        handle = fHandles[i];
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
  
  
  
  
  public static abstract class Handle{
    
    private final int fThick;
    
    public Handle(int thick){
      fThick = thick;
    }
    
    public void paint(Component c, Graphics2D g, int w, int h){
      setRect(w, h);
      g.fill(getRect());
    }
    
    public abstract void setRect(int w, int h);
    protected abstract Rectangle getRect();
    
    public boolean contains(Point p){
      return getRect().contains(p);
    }
  }
}
