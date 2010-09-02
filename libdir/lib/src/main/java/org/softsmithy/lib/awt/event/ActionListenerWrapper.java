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



package org.softsmithy.lib.awt.event;

import java.awt.event.*;

/**
 * Wrapps an ActionListener. </br>
 * E.g. can be used as a base class for decorators.
 */
public class ActionListenerWrapper implements ActionListener{
    
    /**
     * The wrapped ActionListener.
     */
    private ActionListener actionListener;
    
    /**
     * Creates a new instance of this class.
     * @param actionListener the wrapped ActionListener
     */
    public ActionListenerWrapper(ActionListener actionListener){
        this.actionListener = actionListener;
    }
    
    /**
     * Delegates this method to the wrapped ActionListener.
     * @param actionEvent an ActionEvent
     */
    public void actionPerformed(ActionEvent actionEvent){
        actionListener.actionPerformed(actionEvent);
    }
}
