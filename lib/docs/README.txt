Contains:

softsmithy.jar - The Softsmithy Utility Library
softsmithy-i18n.jar - localized property files ("en" and "de" so far)
docs-directory - contains the Javadoc and this readme-file

Note: This is a pre-alpha version and the API is still subject to change! If you
have any questions or comments feel free to contact me: 
puce@users.sourceforge.net

Changes:
v20030319:  -org.softsmithy.lib.swing.JPropertyTable added
            -org.softsmithy.lib.swing.JCustomizerPropertyTable added
            -org.softsmithy.lib.swing.JCustomizerPropertyPane removed
            -org.softsmithy.lib.swing.customizer.CustomizerPropertyTable removed
            -org.softsmithy.lib.util.ResourceBundleCache removed
            -redraw problem after resizing a JCustomizer solved
            -java.util.Locale support classes:
              -org.softsmithy.lib.util.Locales (added)
              -org.softsmithy.lib.beans.XMLEncoderX
              -org.softsmithy.lib.swing.JXTable
            -several convenience constructors for JCustomizer and its subclasses
v04032003:  -Fixed some bugs with setting styles
            -Added a property table model for arbitrary beans 
             (org.softsmithy.lib.swing.table.PropertyTableModel)
            -org.softsmithy.lib.swing.customizer.SelectionManager listens now 
             for PropertyChangeEvents on the active customizer and updates the 
             others
v17022003:  -Styles (+ support) added 
             (org.softsmithy.lib.swing.customizer.Style)
            -org.softsmithy.lib.beans.XMLEncoder -> 
             org.softsmithy.lib.beans.XMLEncoderX to avoid name clashes with
             java.beans
            -org.softsmithy.lib.beans.XMLEncoderX supports the new marker 
             interface org.softsmithy.lib.util.Singleton
            -org.softsmithy.lib.io.CompundFileFilter -> 
             org.softsmithy.lib.io.CompoundFileFilter (typo)
