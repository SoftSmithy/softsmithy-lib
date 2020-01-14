Contains:

softsmithy-devlib-junit-${project.version}.jar                          - The SoftSmithy Development Utility Library - JUnit
softsmithy-devlib-junit-${project.version}-sources.jar                  - The SoftSmithy Development Utility Library - Junit source code
softsmithy-devlib-junit-persistence-${project.version}.jar              - The SoftSmithy Development Utility Library - Junit Persistence
softsmithy-devlib-junit-persistence-${project.version}-sources.jar      - The SoftSmithy Development Utility Library - JUnit Persistence source code
softsmithy-devlib-persistence-${project.version}.jar                    - The SoftSmithy Development Utility Library - Persistence
softsmithy-devlib-persistence-${project.version}-sources.jar            - The SoftSmithy Development Utility Library - Persistence source code
docs-directory                                                          - Contains the Javadoc

While the SoftSmithy Utility Library (lib) is a general utility library,
the SoftSmithy Development Utility Library (devlib) provides utility classes for
development, deployment, set up etc.
You typically won't want/ need this library on the classpath of your application
(runtime).

Note: This library has the following dependencies:
- JPA 2.2
- JUnit ${dependency.junit.version}
- SoftSmithy Utility Library v${project.version}

Have a look at CHANGES.txt for notes about changes.

The contents of the files, which originate from softsmithy.org, are subject to
the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Version 1.0 (the "License"); you may not use these files
except in compliance with the License.
(This License has been apporved by OSI.)
You can find a copy of this license in LICENSE.txt shipped with this distribution
or at https://www.opensource.org/licenses/cddl1.txt .




The newest version can be obtained via Git:
https://github.com/SoftSmithy/softsmithy-lib

git clone https://github.com/SoftSmithy/softsmithy-lib.git

Used:
OpenJDK ${java.version}
Maven 3.6.3
Apache NetBeans IDE 11.2
JPA 2.2
JUnit ${dependency.junit.version}
SoftSmithy Utility Library v${project.version}