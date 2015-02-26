Contains:

softsmithy-devlib-core-${project.version}.jar              - The SoftSmithy Development Utility Library
softsmithy-devlib-core-${project.version}-sources.jar      - The SoftSmithy Development Utility Library source code
docs-directory                                          - Contains the Javadoc

While the SoftSmithy Utility Library (lib) is a general utility library,
the SoftSmithy Development Utility Library (devlib) provides utility classes for
development, deployment, set up etc.
You typically won't want/ need this library on the classpath of your application
(runtime).

Note: This is a pre-alpha version and the API is still subject to change! If you
have any questions or comments feel free to contact me: 
puce@users.sourceforge.net

Note: This library has the following dependencies:
- JPA 2.0
- JUnit ${dependency.junit.version}
- SoftSmithy Utility Library v${project.version}

Have a look at CHANGES.txt for notes about changes.

The contents of the files, which originate from softsmithy.org, are subject to
the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Version 1.0 (the "License"); you may not use these files
except in compliance with the License.
(This License has been apporved by OSI.)
You can find a copy of this license in LICENSE.txt shipped with this distribution
or at http://www.opensource.org/licenses/cddl1.txt .




The newest version can be obtained via Mercurial:
http://sourceforge.net/p/softsmithy/softsmithy-lib

hg clone http://hg.code.sf.net/p/softsmithy/softsmithy-lib softsmithy-lib

Used:
Oracle JDK ${java.version}
Maven 3.0.5
NetBeans IDE 8.0.2
JPA 2.0
JUnit ${dependency.junit.version}
SoftSmithy Utility Library v${project.version}