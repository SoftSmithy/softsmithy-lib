/*
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package org.softsmithy.lib.swing;

import javax.swing.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 *
 * @author  puce
 */
public class JHtmlCustomizer extends JTextCustomizer {
  
  private static final String HTML_START = "<html><body><div align='";
  private static final String HTML_END = "</div></body></html>";
  
  private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
  private String text = "";
  
  /** Creates a new instance of JLabelCustomizer */
  public JHtmlCustomizer() {
    setStateManager(new HiddenStateManager(this));
    setEditor(new JEditorPane("text/html", HTML_START+HTML_END));
    setEditorScrollable(true);
    setComponent(new JEditorPane("text/html", HTML_START+HTML_END));
    //System.out.println(getEditor().getText());
  }
  
  public void setText(String text) {
    String newText = text;
    JEditorPane ep = (JEditorPane) getEditor();
    //System.out.println(ep.getContentType());
    //System.out.println(ep.getText());//.replaceAll("\n", "<br>"));
    
    // note: JEditorPane removes the last (and only the last) \n in the body text if existing
    System.out.println("1: "+newText);
    newText = newText.replaceFirst("(?s).*?<body>\\s{1,5}(<div.*?>\\s{1,7})?", "");
    System.out.println("2: "+newText);
    newText = newText.replaceFirst("(?s)(\\s{0,5}</div>)?\\s{0,3}</body>.*?$", "");
    System.out.println("3: "+newText);
    //labelText = labelText.replaceFirst("(?s)^\\s{4}", "");
    //labelText = labelText.replaceFirst("(?s)\\s$", "");
    newText = newText.replaceAll("\n", "<br>");
    System.out.println("4: "+newText);
    this.text = newText;
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
    configureHtmlText();
    //JLabel label = (JLabel) component; // if component is not a label, super.setComponent throws an Exception
    //label.setVerticalAlignment(SwingConstants.TOP);
  }
  
  
  public String getText() {
    //    JEditorPane editorPane = (JEditorPane) getComponent();
    //    return editorPane != null ? editorPane.getText() : "";
    return text;
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
      editorPane.setText(createHtmlText());//text.replaceAll("\n", "<br>"));
      //System.out.println(createHtmlText()+" -> ");
      //System.out.println(editorPane.getText());
    }
  }
  
  private String createHtmlText(){
    return HTML_START + horizontalAlignment.getHtmlConstant() + "'>" + getText() + HTML_END;
  }
  
  
  
  
}
