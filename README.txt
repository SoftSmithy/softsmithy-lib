Contains:

softsmithy-lib-awt-${project.version}.jar                        - The SoftSmithy Utility Library - AWT
softsmithy-lib-awt-${project.version}-sources.jar                - The SoftSmithy Utility Library - AWT source code
softsmithy-lib-beans-${project.version}.jar                      - The SoftSmithy Utility Library - Beans
softsmithy-lib-beans-${project.version}-sources.jar              - The SoftSmithy Utility Library - Beans source code
softsmithy-lib-core-${project.version}.jar                       - The SoftSmithy Utility Library - Core
softsmithy-lib-core-${project.version}-sources.jar               - The SoftSmithy Utility Library - Core source code
softsmithy-lib-persistence-${project.version}.jar                - The SoftSmithy Utility Library - Persistence
softsmithy-lib-persistence-${project.version}-sources.jar        - The SoftSmithy Utility Library - Persistence source code
softsmithy-lib-swing-${project.version}.jar                      - The SoftSmithy Utility Library - Swing
softsmithy-lib-swing-${project.version}-sources.jar              - The SoftSmithy Utility Library - Swing source code
softsmithy-lib-swing-customizer-${project.version}.jar           - The SoftSmithy Utility Library - Swing Customizer
softsmithy-lib-swing-customizer-${project.version}-sources.jar   - The SoftSmithy Utility Library - Swing Customizer source code
docs-directory                                                   - Contains the Javadoc, a tutorial and samples

Note: This is a pre-alpha version and the API is still subject to change! If you
have any questions or comments feel free to contact me: 
puce@users.sourceforge.net

Note: softsmithy-lib-persistence-${project.version}.jar has the following dependencies:
- JPA (just the org.softsmithy.lib.persistence package; tested with EclipseLink 2.0)


Have a look at CHANGES.txt for notes about changes.

The contents of the files, which originate from softsmithy.org, are subject to 
the COMMON DEVELOPMENT AND DISTRIBUTION LICENSE (CDDL) Version 1.0 (the "License"); you may not use these files
except in compliance with the License.
(This License has been apporved by OSI.)
You can find a copy of this license in LICENSE.txt shipped with this distribution
or at http://www.opensource.org/licenses/cddl1.txt .

--------------------------------------------------------------------------------
The following files from the "Code Syntax Highlighter" used in the tutorial:
docs/tutorial/Scripts/shBrushJava.js
docs/tutorial/Scripts/shCore.js
docs/tutorial/Styles/SyntaxHighlighter.css

origniate from:

http://www.dreamprojections.com/syntaxhighlighter/
Copyright (C) 2004 Alex Gorbatchev.

and are covered by the following license:

"This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General 
Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) 
any later version.

This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied 
warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more 
details.

You should have received a copy of the GNU Lesser General Public License along with this library; if not, write to 
the Free Software Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA"

You can find a copy of this license in docs/LGPL.txt shipped with this distribution.
--------------------------------------------------------------------------------

The newest version can be obtained via Mercurial:
https://sourceforge.net/scm/?type=hg&group_id=64833
http://softsmithy.hg.sourceforge.net/hgweb/softsmithy/lib/main-golden/

hg clone http://softsmithy.hg.sourceforge.net:8000/hgroot/softsmithy/lib/main-golden

Used:
jdk 1.7.0_09
Maven 3.0.4
NetBeans IDE 7.2