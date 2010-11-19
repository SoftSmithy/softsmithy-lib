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
 * DevelopmentActionFactory.java
 *
 * Created on 22. Juni 2004, 16:30
 */

package org.softsmithy.lib.swing.action;

/**
 * http://java.sun.com/developer/techDocs/hi/repository/TBG_Development.html
 * @author  puce
 */
public class DevelopmentActionFactory extends StandardActionFactory{
  
  /** Creates a new instance of DevelopmentActionFactory */
  private DevelopmentActionFactory(String name) {
    super(name);
  }
  
  public static final DevelopmentActionFactory APPLET = new DevelopmentActionFactory("developmentApplet");
  public static final DevelopmentActionFactory APPLICATION = new DevelopmentActionFactory("developmentApplication");
  public static final DevelopmentActionFactory APPLICATION_DEPLOY = new DevelopmentActionFactory("developmentApplicationDeploy");
  public static final DevelopmentActionFactory BEAN = new DevelopmentActionFactory("developmentBean");
  public static final DevelopmentActionFactory BEAN_ADD = new DevelopmentActionFactory("developmentBeanAdd");
  public static final DevelopmentActionFactory ENTERPRISE_JAVA_BEAN = new DevelopmentActionFactory("developmentEnterpriseJavaBean");
  public static final DevelopmentActionFactory ENTERPRISE_JAVA_BEAN_JAR = new DevelopmentActionFactory("developmentEnterpriseJavaBeanJar");
  public static final DevelopmentActionFactory HOST = new DevelopmentActionFactory("developmentHost");
  public static final DevelopmentActionFactory J2EE_APPLICATION = new DevelopmentActionFactory("developmentJ2EEApplication");
  public static final DevelopmentActionFactory J2EE_APPLICATION_CLIENT = new DevelopmentActionFactory("developmentJ2EEApplicationClient");
  public static final DevelopmentActionFactory J2EE_APPLICATION_CLIENT_ADD = new DevelopmentActionFactory("developmentJ2EEApplicationClientAdd");
  public static final DevelopmentActionFactory J2EE_SERVER = new DevelopmentActionFactory("developmentJ2EEServer");
  public static final DevelopmentActionFactory JAR = new DevelopmentActionFactory("developmentJar");
  public static final DevelopmentActionFactory JAR_ADD = new DevelopmentActionFactory("developmentJarAdd");
  public static final DevelopmentActionFactory SERVER = new DevelopmentActionFactory("developmentServer");
  public static final DevelopmentActionFactory WAR = new DevelopmentActionFactory("developmentWar");
  public static final DevelopmentActionFactory WAR_ADD = new DevelopmentActionFactory("developmentWarAdd");
  public static final DevelopmentActionFactory WEB_COMPONENT = new DevelopmentActionFactory("developmentWebComponent");
  public static final DevelopmentActionFactory WEB_COMPONENT_ADD = new DevelopmentActionFactory("developmentWebComponentAdd");
}
