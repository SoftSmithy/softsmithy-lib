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
public class JHtmlCustomizer extends JLabelCustomizer {
  
  private static final String HTML_START = "<html><body>";
  private static final String HTML_END = "</body></html>";
  
  /** Creates a new instance of JLabelCustomizer */
  public JHtmlCustomizer() {
    setEditor(new JEditorPane("text/html", ""));
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
    super.setComponent(component);
    JLabel label = (JLabel) component; // if component is not a label, super.setComponent throws an Exception
    label.setVerticalAlignment(SwingConstants.TOP);
  }
  
}
