package puce.awt.layout;

import java.awt.*;
import puce.swing.customizer.*;

public interface TableConstraints extends CustomizerConstraints{
  
  int getX(Component comp, TableLayout tl);
  int getY(Component comp, TableLayout tl);
  int getWidth(Component comp, TableLayout tl);
  int getHeight(Component comp, TableLayout tl);
  
  Rectangle getAbsoluteBounds(Component comp, TableLayout tl);
  Rectangle getRelativeBounds(Component comp, TableLayout tl);
  void setRelativeBounds(Rectangle bounds, TableLayout tl);
  
}
