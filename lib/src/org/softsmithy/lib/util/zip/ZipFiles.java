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
import org.softsmithy.lib.io.Streams;

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
    //public static String getEntryName(File file){}
}
