/*
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package org.softsmithy.lib.swing;

import javax.swing.*;

/**
 *
 * @author  puce
 */
public class JHtmlCustomizer extends JTextCustomizer {
  
  private static final String HTML_START = "<html><body><div align='";
  private static final String HTML_END = "</div></body></html>";
  
  private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
  private String editorText = "";
  
  /** Creates a new instance of JLabelCustomizer */
  public JHtmlCustomizer() {
    setEditor(new JEditorPane("text/html", HTML_START+HTML_END));
    setEditorScrollable(true);
    setComponent(new JEditorPane("text/html", HTML_START+HTML_END));
    System.out.println(getEditor().getText());
  }
  
  public void setText(String text) {
    JEditorPane ep = (JEditorPane) getEditor();
    System.out.println(ep.getContentType());
    System.out.println(ep.getText());//.replaceAll("\n", "<br>"));
    
    // note: JEditorPane removes the last (and only the last) \n in the body text if existing
    editorText = text.replaceFirst("(?s).*<body>\\s{1,5}", "");
    editorText = editorText.replaceFirst("(?s)\\s{0,3}</body>.*", "");
    //labelText = labelText.replaceFirst("(?s)^\\s{4}", "");
    //labelText = labelText.replaceFirst("(?s)\\s$", "");
    editorText = editorText.replaceAll("\n", "<br>");
    configureHtmlText();
  }
  
  /** Setter for property fComponent.
   * @param fComponent New value of property fComponent.
   *
   */
  public void setComponent(JComponent component) {
    if (! (component instanceof JEditorPane)){
      throw new IllegalArgumentException("comp must be a JEditorPane");
    }
    super.setComponent(component);
    JEditorPane editorPane = (JEditorPane) component;
    editorPane.setEditable(false);
    //JLabel label = (JLabel) component; // if component is not a label, super.setComponent throws an Exception
    //label.setVerticalAlignment(SwingConstants.TOP);
  }
  
  
  public String getText() {
    JEditorPane editorPane = (JEditorPane) getComponent();
    return editorPane != null ? editorPane.getText() : "";
  }
  
  public void setHorizontalAlignment(HorizontalAlignment alignment) {
    horizontalAlignment = alignment;
    configureHtmlText();
  }
  
  public HorizontalAlignment getHorizontalAlignment() {
    return horizontalAlignment;
  }
  
  private void configureHtmlText(){
    JEditorPane editorPane = (JEditorPane) getComponent();
    if (editorPane != null){
      String text = HTML_START + horizontalAlignment.getHtmlConstant() + "'>" + editorText + HTML_END;
      editorPane.setText(text);//text.replaceAll("\n", "<br>"));
      System.out.println(editorPane.getText());
    }
  }
  
}
