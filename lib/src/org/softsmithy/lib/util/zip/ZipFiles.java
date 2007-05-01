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

/*
 * ZipFiles.java
 *
 * Created on 28. Februar 2007, 20:34
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package org.softsmithy.lib.util.zip;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.softsmithy.lib.io.Files;
import org.softsmithy.lib.io.Streams;
import org.softsmithy.lib.util.Strings;

/**
 * A utility class for zip files.
 * @author brunner
 */
public class ZipFiles {
    
    /** Creates a new instance of ZipFiles */
    private ZipFiles() {
    }
    
    
    /**
     * Extracts a zip file to a specified directory.
     * @param zipFile the zip file to extract
     * @param toDir the target directory
     * @throws java.io.IOException
     */
    public static void extract(ZipFile zipFile, File toDir) throws IOException{
        if (! toDir.exists()){
            toDir.mkdirs();
        }
        Enumeration entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = (ZipEntry) entries.nextElement();
            if (zipEntry.isDirectory()) {
                File dir = new File(toDir, zipEntry.getName());
                if (! dir.exists()){ // make sure also empty directories get created!
                    dir.mkdirs();
                }
            } else {
                extract(zipFile, zipEntry, toDir);
            }
        }
    }
    
    /**
     * Extracts an entry of a zip file to a specified directory.
     * @param zipFile the zip file to extract from
     * @param zipEntry the entry of the zip file to extract
     * @param toDir the target directory
     * @throws java.io.IOException
     */
    public static void extract(ZipFile zipFile, ZipEntry zipEntry, File toDir) throws IOException {
        File file = new File(toDir, zipEntry.getName());
        File parentDir = file.getParentFile();
        if (! parentDir.exists()){
            parentDir.mkdirs();
        }
        
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try{
            InputStream istr = zipFile.getInputStream(zipEntry);
            bis = new BufferedInputStream(istr);
            FileOutputStream fos = new FileOutputStream(file);
            bos  = new BufferedOutputStream(fos);
            Streams.copy(bis, bos);
        } finally {
            if (bis !=  null){
                bis.close();
            }
            if (bos != null){
                bos.close();
            }
        }
    }
    
    // must not be absolute!? name must use '/' as path delimiter!?
    /**
     * Creates a valid ZipEntry name from a file. A valid ZipEntry name is not absolute
     * and uses '/' as file separator not the platform specific one!<br>
     * If the file is absolute the root directory will not appear in the name!<br>
     * The file must not be a root directory!
     * @param file the file the entry name gets created from. <br>
     * May be an absolute path but must not be a root directory
     * @return a valid ZipEntry name
     */
    public static String createEntryName(File file){
        String[] allPathNames = Files.getPathNames(file);
        String[] pathNames;
        if (file.isAbsolute()){
            if (file.getParentFile() != null){
                pathNames = new String[allPathNames.length - 1];
                System.arraycopy(allPathNames, 1, pathNames, 0, pathNames.length);
            } else {
                throw new IllegalArgumentException("The file must not be a root directory!");
            }
        } else {
            pathNames = allPathNames;
        }
        return Strings.join(pathNames, "/");
    }
}
