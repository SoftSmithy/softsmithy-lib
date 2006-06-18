/*
 * NewMain.java
 *
 * Created on 24. Mai 2006, 14:05
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.vep;

/**
 *
 * @author florian.brunner
 */
public class NewMain {
    
    /** Creates a new instance of NewMain */
    public NewMain() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Integer i=Integer.valueOf(1);
Integer j=Integer.valueOf("1");
System.out.println(i.equals(j));
Boolean b1=Boolean.valueOf(true);
Boolean b2=Boolean.valueOf("true");
System.out.println(b1.equals(b2));
    }
    
}
