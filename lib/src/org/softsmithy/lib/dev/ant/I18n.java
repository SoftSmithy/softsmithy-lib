/*
 * I18n.java
 *
 * Created on 30. Oktober 2002, 16:31
 */

package org.softsmithy.lib.dev.ant;

import java.io.*;
import java.util.*;
import java.util.jar.*;
import java.util.regex.*;
import java.util.zip.*;
import org.softsmithy.lib.io.*;

/**
 *
 * @author  puce
 */
public class I18n {
  
  private static final String DEFAULT = "default";
  /** Holds value of property targetDir. */
  private final File targetDir;
  private final FileFilter propertiesFilter = new ORFileFilter(DirectoryFilter.getInstance(), new ExtensionFileFilter("properties"));
  private final Pattern pattern = Pattern.compile("_.*");
  private final Map jars = new HashMap();
  
  /** Holds value of property jarName. */
  private String jarName;
  
  /** Creates a new instance of I18n */
  public I18n(String jarName, String targetDir){
    this.jarName = jarName;
    this.targetDir = new File(targetDir);
    if (this.targetDir.exists() && ! this.targetDir.isDirectory()){
      throw new IllegalArgumentException("targetDir must be a directory!");
    }
    if (! this.targetDir.exists()){
      this.targetDir.mkdirs();
    }
  }
  
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    try{
      I18n i18n = new I18n(args[0], args[1]);
      List dirs = new ArrayList();
      for (int i=2; i<args.length; i++){
        File dir = new File(args[i]);
        if (dir.isDirectory()){
          dirs.add(dir);
        }
      }
      try{
        i18n.groupFiles((File[]) dirs.toArray(new File[dirs.size()]));
      } catch (FileNotFoundException ex1){
        ex1.printStackTrace();
      } catch (IOException ex2){
        ex2.printStackTrace();
      }
      i18n.closeAll();
    } catch (IOException ex){
      ex.printStackTrace();
    }
  }
  
  public void groupFiles(File[] files) throws FileNotFoundException, IOException{
    for (int i=0; i<files.length; i++){
      if (files[i].isDirectory()){
        groupFiles(files[i].listFiles(propertiesFilter));
      } else if (files[i].isFile()){
        groupFile(files[i]);
      }
    }
  }
  
  //  public void groupFiles(File dir){
  //    if (dir.isDirectory()){
  //      File[] files = dir.listFiles(propertiesFilter);
  //
  //      groupFiles(dir.listFiles(DirectoryFilter.getInstance()));
  //    }
  //  }
  
  public void groupFile(File file) throws FileNotFoundException, IOException{
    //StringTokenizer tokenizer = new StringTokenizer(Files.getName(file), "_");
    if (file.exists() && file.isFile()){
      String name = Files.getName(file);
      Matcher matcher = pattern.matcher(name);
      String localeString;
      if (matcher.find()){
        localeString = matcher.group();
        localeString = localeString.replaceFirst("_", "");
        localeString = localeString.replaceAll("_", "-");
      } else {
        localeString = DEFAULT;
      }
      if (! jars.containsKey(localeString)){
        jars.put(localeString, new JarOutputStream(new BufferedOutputStream(
        new FileOutputStream(new File(getTargetDir(), getJarName()+"-"+localeString+".jar"))), new Manifest()));
      }
      JarOutputStream jos = (JarOutputStream) jars.get(localeString);
      InputStream is = new BufferedInputStream(new FileInputStream(file));
      byte[] b = new byte[(int) file.length()];
      is.read(b);
      jos.putNextEntry(new JarEntry(file.getPath()));
      jos.write(b);
    }
  }
  
  /** Getter for property targetDir.
   * @return Value of property targetDir.
   *
   */
  public File getTargetDir() {
    return this.targetDir;
  }
  
  /** Getter for property jarName.
   * @return Value of property jarName.
   *
   */
  public String getJarName() {
    return this.jarName;
  }
  
  public void closeAll() throws IOException{
    for(Iterator i=jars.keySet().iterator(); i.hasNext();){
      ((JarOutputStream) jars.get(i.next())).close();
    }
  }
  
}
