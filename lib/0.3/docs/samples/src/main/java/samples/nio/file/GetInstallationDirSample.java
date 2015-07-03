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
package samples.nio.file;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.softsmithy.lib.nio.file.JarFiles;

/**
 * Gets the &lt;install-dir&gt;. <br/> <br/> The jar is expected at: &lt;install-dir&gt/docs/samples/dist/
 *
 * @author puce
 */
public class GetInstallationDirSample {

    /**
     * Gets the &lt;install-dir&gt;. <br/> <br/> The jar is expected at: &lt;install-dir&gt/docs/samples/dist/
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        GetInstallationDirSample getInstallationDirSample = new GetInstallationDirSample();
        getInstallationDirSample.printInstallDirPath();
    }

    public void printInstallDirPath() throws IOException, URISyntaxException {
        URI jarURI = JarFiles.getJarURI(GetInstallationDirSample.class);
        Path jarPath = Paths.get(jarURI);
        Path installDirPath = jarPath.getParent().getParent().getParent().getParent();

        System.out.println(installDirPath);
    }
}
