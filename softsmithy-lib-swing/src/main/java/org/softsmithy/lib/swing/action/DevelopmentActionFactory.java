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
 *
 * @author puce
 */
public enum DevelopmentActionFactory implements StandardActionFactory {

    APPLET("developmentApplet"),
    APPLICATION("developmentApplication"),
    APPLICATION_DEPLOY("developmentApplicationDeploy"),
    BEAN("developmentBean"),
    BEAN_ADD("developmentBeanAdd"),
    ENTERPRISE_JAVA_BEAN("developmentEnterpriseJavaBean"),
    ENTERPRISE_JAVA_BEAN_JAR("developmentEnterpriseJavaBeanJar"),
    HOST("developmentHost"),
    J2EE_APPLICATION("developmentJ2EEApplication"),
    J2EE_APPLICATION_CLIENT("developmentJ2EEApplicationClient"),
    J2EE_APPLICATION_CLIENT_ADD("developmentJ2EEApplicationClientAdd"),
    J2EE_SERVER("developmentJ2EEServer"),
    JAR("developmentJar"),
    JAR_ADD("developmentJarAdd"),
    SERVER("developmentServer"),
    WAR("developmentWar"),
    WAR_ADD("developmentWarAdd"),
    WEB_COMPONENT("developmentWebComponent"),
    WEB_COMPONENT_ADD("developmentWebComponentAdd");

    private final String name;

    DevelopmentActionFactory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
