/*
 * DevelopmentActionFactory.java
 *
 * Created on 22. Juni 2004, 16:30
 */

package org.softsmithy.lib.swing.action;

/**
 *
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
