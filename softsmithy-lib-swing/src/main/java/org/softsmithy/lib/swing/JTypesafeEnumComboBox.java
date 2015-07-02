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


/*
 * JTypesafeEnumComboBox.java
 *
 * Created on 25. November 2002, 15:44
 */

package org.softsmithy.lib.swing;

import java.util.Locale;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ListCellRenderer;
import org.softsmithy.lib.util.TypesafeEnum;

/**
 *
 * @author  puce
 */
@Deprecated
public class JTypesafeEnumComboBox extends JComboBox {
  
  private boolean inited = false;
  
  /** Creates a new instance of JTypesafeEnumComboBox */
  public JTypesafeEnumComboBox() {
    this(new TypesafeEnumComboBoxModel());
  }
  
  public JTypesafeEnumComboBox(Class typesafeEnumClass, Locale locale) {
    this();
    setLocale(locale);
    setModel(new TypesafeEnumComboBoxModel(typesafeEnumClass, getLocale()));
  }
  
  public JTypesafeEnumComboBox(TypesafeEnum[] typesafeEnums, Locale locale) {
    this();
    setLocale(locale);
    setModel(new TypesafeEnumComboBoxModel(typesafeEnums, getLocale()));
  }
  
  public JTypesafeEnumComboBox(TypesafeEnumComboBoxModel model) {
    super(model);
    setRenderer(new TypesafeEnumListCellRenderer(getLocale()));
    inited = true;
  }
  
  /** Sets the data model that the <code>JComboBox</code> uses to obtain
   * the list of items.
   *
   * @param aModel the <code>ComboBoxModel</code> that provides the
   * 	displayed list of items
   */
//     * @beaninfo
//   *        bound: true
//   *  description: Model that the combo box uses to get data to display.
  @Override
  public void setModel(ComboBoxModel aModel) {
    if (! (aModel instanceof TypesafeEnumComboBoxModel)){
      throw new IllegalArgumentException("aModel must be a TypesafeEnumComboBoxModel");
    }
    super.setModel(aModel);
  }
  
  /** Sets the locale of this component.  This is a bound property.
   * @param l the locale to become this component's locale
   * @see #getLocale
   * @since JDK1.1
   *
   */
  @Override
  public void setLocale(Locale l) {
    super.setLocale(l);
    TypesafeEnumListCellRenderer renderer = (TypesafeEnumListCellRenderer) getRenderer();
    if (renderer != null){
      renderer.setLocale(l);
    }
  }
  
  /** Returns true if the <code>JComboBox</code> is editable.
   * By default, a combo box is not editable.
   *
   * @return true if the <code>JComboBox</code> is editable, else false
   *
   */
  @Override
  public boolean isEditable() {
    return false;
  }
  
  /** Sets the renderer that paints the list items and the item selected from the list in
   * the JComboBox field. The renderer is used if the JComboBox is not
   * editable. If it is editable, the editor is used to render and edit
   * the selected item.
   * <p>
   * The default renderer displays a string or an icon.
   * Other renderers can handle graphic images and composite items.
   * <p>
   * To display the selected item,
   * <code>aRenderer.getListCellRendererComponent</code>
   * is called, passing the list object and an index of -1.
   *
   * @param aRenderer  the <code>ListCellRenderer</code> that
   * 			displays the selected item
   * @see #setEditor
   */
//     * @beaninfo
//   *      bound: true
//   *     expert: true
//   *  description: The renderer that paints the item selected in the list.
  @Override
  public void setRenderer(ListCellRenderer aRenderer) {
    if (inited && ! (aRenderer instanceof TypesafeEnumListCellRenderer)){
      throw new IllegalArgumentException("aRenderer must be a TypesafeEnumListCellRenderer");
    }
    super.setRenderer(aRenderer);
  }
  
}
