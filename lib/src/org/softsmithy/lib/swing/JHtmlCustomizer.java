/*
 *                  Sun Public License Notice
 *
 * The contents of this file are subject to the Sun Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. A copy of the License is available at
 * http://www.sun.com/
 *
 * The Original Code is SoftSmithy Utility Library. The Initial Developer of the
 * Original Code is Florian Brunner (Sourceforge.net user: puce). All Rights Reserved.
 *
 * Contributor(s): .
 */

/*
 * JLabelCustomizer.java
 *
 * Created on 5. September 2002, 17:11
 */

package org.softsmithy.lib.swing;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import javax.swing.text.html.*;
import org.softsmithy.lib.swing.customizer.*;

/**
 * A basic HTML text customizer using JEditorPane to display and edit the HTML
 * text.
 * @author  puce
 */
public class JHtmlCustomizer extends AbstractTextCustomizer {
    
    /**
     * The HTML start tags.
     */
    private static final String HTML_START = "<html><body>"; //<div align='";
    
    /**
     * The HTML end tags.
     */
    private static final String HTML_END = "</body></html>"; //"</font></div></body></html>";
    
    /**
     * The "insert HTML break" key string.
     */
    private static final String insertHtmlBreakAction = "insert-html-break";
    
    /**
     * The horizontal alignment of the text.
     */
    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.LEFT;
    
    /**
     * The HTML body.
     */
    private String htmlBody = "";
    
    /**
     * The attribute set.
     */
    private MutableAttributeSet attributeSet = new SimpleAttributeSet();
    
    /**
     * True, if this customizer is initialized, else false.
     */
    private boolean inited = false;
    
    /**
     * Creates a new instance of this class.
     */
    public JHtmlCustomizer() {
        super(new JEditorPane("text/html", HTML_START+HTML_END));
        setStateManager(new HiddenStateManager(this));
        setEditor(new JEditorPane("text/html", HTML_START+HTML_END));
        setEditorScrollable(true);
        //System.out.println("ENTER KeyStroke: " + getEditor().getInputMap().get(KeyStroke.getKeyStroke("ENTER")));
        getEditor().getInputMap().put(KeyStroke.getKeyStroke("ENTER"), insertHtmlBreakAction);
        getEditor().getActionMap().put(insertHtmlBreakAction, new InsertHtmlBreakAction());
        //setComponent(new JEditorPane("text/html", HTML_START+HTML_END));
        inited = true;
        //setBackground(getBackground());
        setForeground(getForeground());
        setFont(getFont());
        setHorizontalAlignment(getHorizontalAlignment());
        //System.out.println(getEditor().getText());
    }
    
    /**
     * Sets the HTML text. This method extracts the HTML body from the text and replaces
     * "&amp;lt;br&amp;gt;" with "&lt;br&gt;".
     * @param text the HTML text
     */
    public void setText(String text) {
        String newText = text;
        JEditorPane ep = (JEditorPane) getEditor();
        //System.out.println(ep.getContentType());
        //System.out.println(ep.getText());//.replaceAll("\n", "<br>"));
        
        // note: JEditorPane removes the last (and only the last) \n in the body text if existing
        //System.out.println("1: "+newText);
        newText = newText.replaceFirst("(?s).*?<body>\\s{1,5}(<div.*?>\\s{1,7})?", "");
        //System.out.println("2: "+newText);
        newText = newText.replaceFirst("(?s)(\\s{0,5}</div>)?\\s{0,3}</body>.*?$", "");
        //System.out.println("3: "+newText);
        //labelText = labelText.replaceFirst("(?s)^\\s{4}", "");
        //labelText = labelText.replaceFirst("(?s)\\s$", "");
        
        //newText = newText.replaceAll("\n", "<br>");
        newText = newText.replaceAll("&lt;br&gt;", "<br>");
        //System.out.println("4: "+newText);
        setHtmlBody(newText);
    }
    
    /**
     * Sets and configures the JEditorPane to wrap.
     * @param component the JEditorPane to wrap
     * @throws IllegalArgumentException if the component is not a JEditorPane
     */
    public void setComponent(JComponent component) {
        if (! (component instanceof JEditorPane)){
            throw new IllegalArgumentException("comp must be a JEditorPane");
        }
        super.setComponent(component);
        //if (component instanceof JEditorPane){
        JEditorPane editorPane = (JEditorPane) component;
        editorPane.setEditable(false);
        if (inited){
            configureHtmlText();
        }
        //JLabel label = (JLabel) component; // if component is not a label, super.setComponent throws an Exception
        //label.setVerticalAlignment(SwingConstants.TOP);
        // }
    }
    
    /**
     * Gets the HTML body.
     * @return the HTML body
     */
    public String getText() {
        //    JEditorPane editorPane = (JEditorPane) getComponent();
        //    return editorPane != null ? editorPane.getText() : "";
        return getHtmlBody();
    }
    
    /**
     * Sets the horizontal alignment of the text. This method should not
     * fire any events!
     * @param alignment the horizontal alignment of the text
     */
    protected void setHorizontalAlignmentOnly(HorizontalAlignment alignment) {
        horizontalAlignment = alignment;
        if (inited){
            StyleConstants.setAlignment(attributeSet, alignment.getStyleConstant(this.getComponentOrientation()));
            configureHtmlText();
        }
    }
    
    /**
     * Gets the horizontal alignment of the text.
     * @return the horizontal alignment of the text
     */
    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }
    
    /**
     * Configures the wrapped JEditorPane to display the HTML body.
     */
    private void configureHtmlText(){
        JEditorPane editorPane = (JEditorPane) getComponent();
        if (editorPane != null){
            editorPane.setDocument(editorPane.getEditorKit().createDefaultDocument());
            editorPane.setText(createHtmlText());//text.replaceAll("\n", "<br>"));
            HTMLDocument doc=(HTMLDocument)editorPane.getDocument();
            doc.setParagraphAttributes(0,doc.getLength(),attributeSet,true);
            //System.out.println(createHtmlText()+" -> ");
            //System.out.println(editorPane.getText());
        }
    }
    
    /**
     * Creates the HTML text from the HTML body.
     * @return the HTML text
     */
    private String createHtmlText(){
        StringBuffer htmlText = new StringBuffer(HTML_START);
        //    htmlText.append(horizontalAlignment.getHtmlConstant()).append("'>");
        //    htmlText.append("<font face='").append(getFont().getFamily()).append("' "); // or better getFont().getName()???
        //    htmlText.append("size='").append(getFont().getSize()).append("' ");
        //    htmlText.append("color='#");
        //    String redHex = Integer.toHexString(getForeground().getRed());
        //    if (redHex.length() == 1){
        //      htmlText.append("0");
        //    }
        //    htmlText.append(redHex);
        //    String greenHex = Integer.toHexString(getForeground().getGreen());
        //    if (greenHex.length() == 1){
        //      htmlText.append("0");
        //    }
        //    htmlText.append(greenHex);
        //    String blueHex = Integer.toHexString(getForeground().getBlue());
        //    if (blueHex.length() == 1){
        //      htmlText.append("0");
        //    }
        //    htmlText.append(blueHex).append("'>");
        //    if (getFont().isBold()){
        //      htmlText.append("<b>");
        //    }
        //    if (getFont().isItalic()){
        //      htmlText.append("<i>");
        //    }
        htmlText.append(getText());
        //    if (getFont().isItalic()){
        //      htmlText.append("</i>");
        //    }
        //    if (getFont().isBold()){
        //      htmlText.append("</b>");
        //    }
        htmlText.append(HTML_END);
        //System.out.println("Family: "+getFont().getFamily());
        //System.out.println("Name: "+getFont().getName());
        //System.out.println("FontName: "+getFont().getFontName());
        //System.out.println("html: " + htmlText.toString());
        return htmlText.toString();
    }
    
    
    /**
     * Sets the forground color of this customizer.
     * @param c the forground color
     */
    public void setDefaultForeground(Color c) {
        super.setDefaultForeground(c);
        if (inited){
            StyleConstants.setForeground(attributeSet, c);
            configureHtmlText();
            repaint();
        }
    }
    
    
    /**
     * Sets the font of this customizer.
     * @param f the font
     */
    public void setDefaultFont(Font f) {
        super.setDefaultFont(f);
        if (inited){
            StyleConstants.setFontFamily(attributeSet, f.getFamily());
            StyleConstants.setFontSize(attributeSet, f.getSize());
            StyleConstants.setBold(attributeSet, f.isBold());
            StyleConstants.setItalic(attributeSet, f.isItalic());
            configureHtmlText();
            repaint();
        }
    }
    
    
    /**
     * Gets the HTML body.
     * @return the HTML body
     */
    public String getHtmlBody() {
        return htmlBody;
    }
    
    
    /**
     * Sets the HTML body.
     * @param htmlBody the HTML body
     */
    public void setHtmlBody(String htmlBody) {
        String newHtmlBody = htmlBody;
        //newHtmlBody = newHtmlBody.replaceAll("\n", "");
        //newHtmlBody = newHtmlBody.replaceAll(" +", " ");
        this.htmlBody = newHtmlBody;
        configureHtmlText();
    }
    
    /** Sets the background color of this component.
     * <p>
     * The background color affects each component differently and the
     * parts of the component that are affected by the background color
     * may differ between operating systems.
     *
     * @param c the color to become this component's color;
     * 		if this parameter is <code>null</code>, then this
     * 		component will inherit the background color of its parent
     * @see #getBackground
     * @since JDK1.0
     * @beaninfo
     *       bound: true
     *
     */
    //  public void setBackground(Color c) {
    //    super.setBackground(c);
    //    if (inited){
    //      StyleConstants.setBackground(attributeSet, c); // probably not really needed, but for constancy
    //      configureHtmlText();
    //    repaint();
    //    }
    //  }
    
    /**
     * A text action, which replaces the selection with "&lt;br&gt;\n".
     */
    private static class InsertHtmlBreakAction extends TextAction {
        
        /**
         * Creates this object with the appropriate identifier.
         */
        public InsertHtmlBreakAction() {
            super(insertHtmlBreakAction);
        }
        
        /**
         * The operation to perform when this action is triggered.
         *
         * @param e the action event
         */
        public void actionPerformed(ActionEvent e) {
            JTextComponent target = getTextComponent(e);
            if (target != null) {
                if ((! target.isEditable()) || (! target.isEnabled())) {
                    UIManager.getLookAndFeel().provideErrorFeedback(target);
                    return;
                }
                target.replaceSelection("<br>\n");
            }
        }
    }
}
