package puce.awt.layout;

import puce.swing.customizer.*;

public interface TableConstraints extends CustomizerConstraints{
  
  public int getX(TableLayout tl);
  public int getY(TableLayout tl);
  public int getWidth(TableLayout tl);
  public int getHeight(TableLayout tl);
 
}
