/*
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package puce.swing;

import javax.swing.*;

/**
 *
 * @author  puce
 */
public class JLabelCustomizer extends JEditableCustomizer {
  
  private static final String HTML_START = "<html><body>";
  private static final String HTML_END = "</body></html>";
  
  /** Creates a new instance of JLabelCustomizer */
  public JLabelCustomizer() {
    setEditor(new JEditorPane("text/html", ""));
  }
  
  public String getText() {
    JLabel label = (JLabel) getComponent();
    return label != null ? label.getText() : "";
  }
  
  public void setText(String text) {
    JEditorPane ep = (JEditorPane) getEditor();
    System.out.println(ep.getContentType());
    System.out.println(ep.getText().replaceAll("\n", "<br>"));
    
    JLabel label = (JLabel) getComponent();
    if (label != null){
      String labelText = text.replaceFirst("(?s).*<body>\\s{5}", "");
      labelText = labelText.replaceFirst("(?s)\\s{3}</body>.*", "");
      labelText = labelText.replaceAll("\n", "<br>");
      labelText = HTML_START + labelText + HTML_END;
      label.setText(labelText);//text.replaceAll("\n", "<br>"));
      System.out.println(label.getText());
    }
  }
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  public void setComponent(JComponent component) {
    if (! (component instanceof JLabel)){
      throw new IllegalArgumentException("comp must be a JLabel");
    }
    JLabel label = (JLabel) component;
    label.setVerticalAlignment(SwingConstants.TOP);
    super.setComponent(label);
  }
  
}
