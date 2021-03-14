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
package org.softsmithy.lib.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author puce
 */
public class ResourceLoaderTest {

    @Test
    public void testGetResourceRelative() throws URISyntaxException, IOException {
        System.out.println("getResource");
        ResourceLoader resourceLoader = new ResourceLoader(ResourceLoaderTest.class);
        URL resourceUrl = resourceLoader.getResource("testResource.txt");
        assertNotNull(resourceUrl);
        List<String> allLines = Files.readAllLines(Paths.get(resourceUrl.toURI()), Charset.forName("UTF-8"));
        assertEquals(allLines, Arrays.asList("Test"));
    }

    @Test
    public void testGetResourceAbsolute() throws URISyntaxException, IOException {
        System.out.println("getResource");
        ResourceLoader resourceLoader = new ResourceLoader(ResourceLoaderTest.class);
        URL resourceUrl = resourceLoader.getResource("/org/softsmithy/lib/util/testResource.txt");
        assertNotNull(resourceUrl);
        List<String> allLines = Files.readAllLines(Paths.get(resourceUrl.toURI()), Charset.forName("UTF-8"));
        assertEquals(Arrays.asList("Test"), allLines);
    }

    @Test
    public void testGetResourceAsStreamRelatve() throws IOException {
        System.out.println("getResourceAsStream");
        ResourceLoader resourceLoader = new ResourceLoader(ResourceLoaderTest.class);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceLoader.getResourceAsStream(
                        "testResource.txt"), Charset.forName("UTF-8")))) {
            String line = br.readLine();
            assertEquals("Test", line);
        }
    }

    @Test
    public void testGetResourceAsStreamAbsolute() throws IOException {
        System.out.println("getResourceAsStream");
        ResourceLoader resourceLoader = new ResourceLoader(ResourceLoaderTest.class);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(resourceLoader.getResourceAsStream(
                        "/org/softsmithy/lib/util/testResource.txt"), Charset.forName("UTF-8")))) {
            String line = br.readLine();
            assertEquals("Test", line);
        }
    }
}
